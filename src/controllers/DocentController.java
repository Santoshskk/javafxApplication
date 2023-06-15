package controllers;

import data.DocentDAO;
import data.DocentDummyDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import models.Docent;
import practicumopdracht.MainApplication;
import views.DocentView;
import views.View;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DocentController extends Controller{

    private DocentView view;

    private ObservableList<Docent> docentObservableList;


    public DocentController() {
        view = new DocentView();

        List<Docent> docenten = MainApplication.getDocentDAO().getAll();
        docentObservableList = FXCollections.observableArrayList(docenten);

        // Bind de observableList aan de ListView in de view
        view.getListView().setItems(docentObservableList);

        view.getListView().getSelectionModel().selectedItemProperty().addListener((observableValue, oudeDocent, nieuweDocent) -> {
            if (nieuweDocent == null) {
                handelNieuwButton();
            } else {
                view.getTextField().setText(nieuweDocent.getNaam());
                view.getTextFieldVak().setText(String.valueOf(nieuweDocent.getVak()));
                view.getDatePicker().setValue(nieuweDocent.getAangenomenOp());
                view.getCheckbox().setSelected(nieuweDocent.isStagaire());
            }

        });



        view.getSchakelButton().setOnAction(actionEvent -> handelSchakelButton());
        // Als niks is aangeklikt in de Listview zal de knop disabeld zijn
        view.getSchakelButton().disableProperty().bind(view.getListView().getSelectionModel().selectedItemProperty().isNull());
        view.getNieuwButton().setOnAction(actionEvent -> handelNieuwButton());
        view.getVerwijderButton().setOnAction(actionEvent -> handleVerwijderButton());
        view.getOpslaanButton().setOnAction(actionEvent-> handelOpslaanbutton() );


    }

    private void handelOpslaanbutton(){
        Docent docent;
        Alert alert= new Alert(Alert.AlertType.WARNING);
        StringBuilder errorMessages = new StringBuilder();
        int counterror= 0;

        String naam= view.getTextField().getText();
        String vak= view.getTextFieldVak().getText();
        LocalDate aangenomenOp= view.getDatePicker().getValue();
        boolean stagaire= view.getCheckbox().isSelected();

        //naam
        if(naam.isEmpty() || !naam.matches("^[a-zA-Z][a-zA-Z\\s]*$")){
            view.getTextField().setStyle("-fx-border-color: red");
            errorMessages.append("-Voer een geldige naam in. Zorg dat de naam begint met een letter .\n");
            counterror++;
        }else{
            view.getTextField().setStyle("-fx-border-color: green");
        }

        //vak
        if(vak.isEmpty()|| !vak.matches("^[a-zA-Z]+$")){
            view.getTextFieldVak().setStyle("-fx-border-color: red");
            errorMessages.append("-Voer een geldige vak in zonder spaties of nummers.\n");
            counterror++;
        }else{
            view.getTextFieldVak().setStyle("-fx-border-color: green");
        }

        //aangenomen op
        if(Objects.isNull(aangenomenOp)|| aangenomenOp.isAfter(LocalDate.now())){
            view.getDatePicker().setStyle("-fx-border-color: red");
            alert= new Alert(Alert.AlertType.WARNING);
            errorMessages.append("-Datum mag niet leeg zijn en in de toekomst liggen\n");
            counterror++;
        }else{
            view.getDatePicker().setStyle("-fx-border-color: green");
        }


        if(counterror==0){
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Opgeslagen!");
            alert.showAndWait();
        } else {
            alert.setContentText(errorMessages.toString());
            alert.showAndWait();
        }

        if (counterror == 0) {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Opgeslagen!");
            alert.showAndWait();

            docent = view.getListView().getSelectionModel().getSelectedItem();
            //als iets geselecteerd is dan zal hij het updaten anders niet
            if (docent == null) {
                docent = new Docent(naam,vak,stagaire,aangenomenOp);

                view.getListView().getItems().add(docent);
            } else {
                docent.setNaam(naam);
                docent.setVak(vak);
                docent.setStagaire(stagaire);
                docent.setAangenomenOp(aangenomenOp);

                view.getListView().refresh();
            }
            MainApplication.getDocentDAO().addOrUpdate(docent);
        }
    }

    private void handelSchakelButton() {
        Docent docent = view.getListView().getSelectionModel().getSelectedItem();
        StudentController studentController = new StudentController();
        studentController.setUpCombobox(docent);
        MainApplication.switchController(studentController);
    }

    private void handelNieuwButton(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Nieuwe docent toevoegen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            view.getTextField().clear();
            view.getTextFieldVak().clear();
            view.getDatePicker().setValue(null);
            view.getCheckbox().setSelected(false);
            view.getListView().getSelectionModel().clearSelection();
        }

    }

    private void handleVerwijderButton(){
        Docent docent = view.getListView().getSelectionModel().getSelectedItem();
        if (docent == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Weet je zeker dat deze docent wilt verwijderen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            view.getListView().getItems().remove(docent);
           MainApplication.getDocentDAO().delete(docent);


        }
    }


    @Override
    public View getView() {
        return view;
    }
}
