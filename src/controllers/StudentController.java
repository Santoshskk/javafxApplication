package controllers;

import comparators.StudentComporatorCijferAflopend;
import comparators.StudentComporatorCijfersOplopend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import models.Docent;
import models.Student;
import practicumopdracht.MainApplication;
import views.StudentView;
import views.View;

import java.util.List;
import java.util.Optional;

public class StudentController extends Controller{

    private StudentView view;

    private List<Student> student;

    public StudentController() {
        view = new StudentView();

        ObservableList <Docent> docents = FXCollections.observableArrayList(MainApplication.getDocentDAO().getAll());

        view.getComboBoxDocent().setItems(docents);

        view.getComboBoxDocent().getSelectionModel().selectedItemProperty().addListener((observableValue, club, studenten) -> {
            setUpCombobox(studenten);
            view.getListView().refresh();
        });

        view.getSchakelButton().setOnAction(actionEvent -> handelSchakelButton());
        view.getNieuwButton().setOnAction(actionEvent -> handelNieuwButton());
        view.getNieuwButton().disableProperty().bind(view.getListView().getSelectionModel().selectedItemProperty().isNull());
        view.getVerwijderButton().setOnAction(actionEvent -> handleVerwijderButton());
        view.getOpslaanButton().setOnAction(actionEvent -> handelOpslaanButton());

        view.getListView().getSelectionModel().selectedItemProperty().addListener((observableValue, oldItem, newItem) -> {
            if (newItem != null) {
                // Update invoervelden met de waardes van het geselecteerde model
                view.getTextField().setText(newItem.getNaam());
                view.getTextFieldStudentnummer().setText(String.valueOf(newItem.getStudentnummer()));
                view.getTextFieldGekregenCijfer().setText(String.valueOf(newItem.getGekregenCijfer()));
                view.getTextAreaFeedback().setText(newItem.getFeedback());
            }
        });

        // dit zijn de comparators

        view.getCijferAflopend().setOnAction(actionEvent -> {
            FXCollections.sort(view.getListView().getItems(), new StudentComporatorCijferAflopend());
        });
        view.getCijferOplopend().setOnAction(actionEvent -> {
            FXCollections.sort(view.getListView().getItems(), new StudentComporatorCijfersOplopend());
        });
    }

    /**
     * dit slaat alles op in de listview en dao
     */
    private void handelOpslaanButton(){
        Student student;
        Alert alert= new Alert(Alert.AlertType.WARNING);
        StringBuilder errorMessages = new StringBuilder();
        int counterror= 0;

        String naam= view.getTextField().getText();
        String studentnummerString = view.getTextFieldStudentnummer().getText();
        String gekregenCijferString= view.getTextFieldGekregenCijfer().getText();
        String feedback = view.getTextAreaFeedback().getText();

        //naam
        if(naam.isEmpty() || !naam.matches("^[a-zA-Z][a-zA-Z\\s]*$")){
            view.getTextField().setStyle("-fx-border-color: red");
            errorMessages.append("-Voer een geldige naam in. Zorg dat de naam begint met een letter .\n");
            counterror++;
        }else{
           view.getTextField().setStyle("-fx-border-color: green");
        }

        //studentnummer
        try {
            Integer.parseInt(studentnummerString);
            view.getTextFieldStudentnummer().setStyle("-fx-border-color: green");

        } catch (NumberFormatException exception) {
            view.getTextFieldStudentnummer().setStyle("-fx-border-color: red");
           errorMessages.append("-Voer een geldige studentnummer in zonder letters.\n");
           counterror++;
        }

        //gekregencijfer
        try{
            Double.parseDouble(gekregenCijferString);
            view.getTextFieldGekregenCijfer().setStyle("-fx-border-color: green");

        }catch (NumberFormatException exception){
            view.getTextFieldGekregenCijfer().setStyle("-fx-border-color: red");
            errorMessages.append("-Voer een geldige cijfer in.\n");
            counterror++;
        }

        //feedback
        if(feedback.isEmpty()){
            view.getTextAreaFeedback().setStyle("-fx-border-color: red");
            errorMessages.append("-Geef feedback.\n");
            counterror++;
        }else{
            view.getTextAreaFeedback().setStyle("-fx-border-color: green");
        }

        //als alles goed is saved hij het naar de desbetreffende dao

        if(counterror==0){
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Opgeslagen!");
            alert.showAndWait();

            student = view.getListView().getSelectionModel().getSelectedItem();
            //als iets geselecteerd is dan zal hij het updaten anders niet
            if (student == null) {
                student = new Student( view.getComboBoxDocent().getSelectionModel().getSelectedItem(), naam, Integer.parseInt(studentnummerString),Double.parseDouble(gekregenCijferString),feedback);

                view.getListView().getItems().add(student);

            } else {
                student.setNaam(naam);
                student.setStudentnummer(Integer.parseInt(studentnummerString));
                student.setGekregenCijfer(Double.parseDouble(gekregenCijferString));
                student.setFeedback(feedback);

                view.getListView().refresh();
            }

            MainApplication.getStudentDAO().addOrUpdate(student);
            MainApplication.getStudentDAO().save();

        }else {
            alert.setContentText(errorMessages.toString());
            alert.showAndWait();
        }


    }
    /**
     * schakeld van de detail naar de master
     */
    private void handelSchakelButton() {
        DocentController docentController = new DocentController();
        MainApplication.switchController(docentController);
        //bij het opstarten is het standaard georderd op cijfer oplopend
        FXCollections.sort(view.getListView().getItems(), new StudentComporatorCijferAflopend());
    }

    /**
     * leegt alle velden zodat er iets getypt kan woren
     */
    private void handelNieuwButton(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Nieuwe student toevoegen?");
        Optional<ButtonType> result = alert.showAndWait();
        //isPresent is voor de NosuchElement exeption
        if (result.isPresent() && result.get() == ButtonType.OK) {
           leegAlleInvoervelden();
        }
    }

    /**
     * verwijder de gekozen listview item daarna leegt hij alle velden
     */
    private void handleVerwijderButton(){
        Student gekozenStudent = view.getListView().getSelectionModel().getSelectedItem();
        if (gekozenStudent == null) {
            return;
        }
        //error code met de keuze om het te verwijderen as er op OK geklikt word
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Weet je zeker dat deze student wilt verwijderen?");
        Optional<ButtonType> result = alert.showAndWait();
        //isPresent is voor de NosuchElement exeption
        if (result.isPresent() && result.get() == ButtonType.OK) {
            student.remove(gekozenStudent);
            MainApplication.getStudentDAO().delete(gekozenStudent);
            view.getListView().setItems(FXCollections.observableArrayList(student));
            leegAlleInvoervelden();
        }

        view.getListView().refresh();

    }

    /**
     * geeft de gekozen master weer in de combobox
     * @param docent
     */
    public void setUpCombobox(Docent docent) {
        setStudent(MainApplication.getStudentDAO().getAllFor(docent));
        ObservableList<Student> spelerObservableList = FXCollections.observableArrayList(student);
        view.getListView().setItems(spelerObservableList);
        view.getComboBoxDocent().setValue(docent);

    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }


    /**
     * Leeg alle invoervelden.
     */
    public void leegAlleInvoervelden(){
        view.getTextField().clear();
        view.getTextField().setStyle(null);
        view.getTextFieldStudentnummer().clear();
        view.getTextFieldStudentnummer().setStyle(null);

        view.getTextFieldGekregenCijfer().clear();
        view.getTextFieldGekregenCijfer().setStyle(null);

        view.getTextAreaFeedback().clear();
        view.getTextAreaFeedback().setStyle(null);
        view.getListView().getSelectionModel().clearSelection();
    }

    @Override
    public View getView() {
        return view;
    }
}

