package controllers;

import comparators.DocentComparatorOplopend;
import comparators.DocentComporatorAflopend;
import comparators.DocentComporatorDatum;
import data.DocentDAO;
import data.DocentDummyDAO;
import data.DocentTextDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import models.Docent;
import models.Student;
import practicumopdracht.MainApplication;
import views.DocentView;
import views.View;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * de type Docent controller.
 */
public class DocentController extends Controller {
    private DocentView view;
    private ObservableList<Docent> docentObservableList;

    /**
     * Instantiates een new Docent controller.
     */
    public DocentController() {
        view = new DocentView();

        List<Docent> docenten = MainApplication.getDocentDAO().getAll();
        docentObservableList = FXCollections.observableArrayList(docenten);
        // Bind de observableList aan de ListView in de view
        view.getListView().setItems(docentObservableList);

        view.getListView().getSelectionModel().selectedItemProperty().addListener((observableValue, oudeDocent, nieuweDocent) -> {
            if (nieuweDocent != null) {
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
        // Als niks is aangeklikt in de Listview zal de knop disabeld zijn
        view.getNieuwButton().disableProperty().bind(view.getListView().getSelectionModel().selectedItemProperty().isNull());

        view.getVerwijderButton().setOnAction(actionEvent -> handleVerwijderButton());
        view.getOpslaanButton().setOnAction(actionEvent -> handelOpslaanbutton());

        view.getMenuItemLaden().setOnAction(actionEvent -> handelLaden());
        view.getMenuItemOpslaan().setOnAction(actionEvent -> handelOpslaan());
        view.getMenuItemAfsluiten().setOnAction(actionEvent -> handelAfsluiten());

        // dit zijn de comparators
        view.getOplopend().setOnAction(actionEvent -> {
            FXCollections.sort(view.getListView().getItems(), new DocentComparatorOplopend());
        });
        view.getAflopend().setOnAction(actionEvent -> {
            FXCollections.sort(view.getListView().getItems(), new DocentComporatorAflopend());
        });
        view.getDatumOrde().setOnAction(actionEvent -> {
            FXCollections.sort(view.getListView().getItems(), new DocentComporatorDatum());
        });

    }

    /**
     * dit laad alles van de dao
     */
    private void handelLaden() {
        Alert ladenAlert = new Alert(Alert.AlertType.CONFIRMATION);
        ladenAlert.setContentText("Wil je de gegevens laden uit de DAO's?");
        Optional<ButtonType> result = ladenAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // de load-methode
            boolean success = MainApplication.getDocentDAO().load() && MainApplication.getStudentDAO().load();
            if (!success) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setContentText("Het laden is mislukt");
                alertError.show();
            } else {
                Alert alertSucces = new Alert(Alert.AlertType.INFORMATION);
                alertSucces.setContentText("Het laden is gelukt");
                alertSucces.show();
                List<Docent> lijst = MainApplication.getDocentDAO().getAll();
                docentObservableList = FXCollections.observableArrayList(lijst);
                view.getListView().setItems(docentObservableList);
            }
        }
        //bij het opstarten is het standaard georderd op datum
        FXCollections.sort(view.getListView().getItems(), new DocentComporatorDatum());
    }

    /**
     * slaat alle input op in de dao
     */
    private void handelOpslaan() {
        Alert opslaanAlert = new Alert(Alert.AlertType.CONFIRMATION);
        opslaanAlert.setContentText("Wil je de gegevens opslaan in de DAO's?");
        Optional<ButtonType> result = opslaanAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // de save-methode
            boolean success = MainApplication.getDocentDAO().save() && MainApplication.getStudentDAO().save();
            if (!success) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setContentText("Het opslaan is mislukt");
                alertError.show();
            } else {
                Alert alertSucces = new Alert(Alert.AlertType.INFORMATION);
                alertSucces.setContentText("Het opslaan is gelukt");
                alertSucces.show();
            }
        }

    }

    /**
     * sluit alles af en vraagt om nog een keer op teslaan
     *
     */
    private void handelAfsluiten() {
        Alert afsluitenAlert = new Alert(Alert.AlertType.CONFIRMATION);
        afsluitenAlert.setContentText("Wilt je voor het afsluiten nog opslaan?");
        Optional<ButtonType> result = afsluitenAlert.showAndWait();
        //isPresent is voor de NosuchElement exeption
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                MainApplication.getDocentDAO().save();
                MainApplication.getStudentDAO().save();
            } catch (Exception e) {
                Alert alerterr = new Alert(Alert.AlertType.ERROR, "Er is een fout opgetreden bij het opslaan van de gegevens");
                alerterr.showAndWait();
            }
        }
        Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION, "Weet u zeker dat u wilt afsluiten?");
        Optional<ButtonType> closeResult = closeAlert.showAndWait();
        //isPresent is voor de NosuchElement exeption
        if (closeResult.isPresent() && closeResult.get() == ButtonType.OK) {
            Stage stage = (Stage) view.getRoot().getScene().getWindow();
            stage.close();
        }

    }

    /**
     * dit slaat alles op in de listview
     */
    private void handelOpslaanbutton() {
        Docent docent;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        StringBuilder errorMessages = new StringBuilder();
        int counterror = 0;

        String naam = view.getTextField().getText();
        String vak = view.getTextFieldVak().getText();
        LocalDate aangenomenOp = view.getDatePicker().getValue();
        boolean stagaire = view.getCheckbox().isSelected();

        //naam
        if (naam.isEmpty() || !naam.matches("^[a-zA-Z][a-zA-Z\\s]*$")) {
            view.getTextField().setStyle("-fx-border-color: red");
            errorMessages.append("-Voer een geldige naam in. Zorg dat de naam begint met een letter .\n");
            counterror++;
        } else {
            view.getTextField().setStyle("-fx-border-color: green");
        }

        //vak
        if (vak.isEmpty() || !vak.matches("^[a-zA-Z]+$")) {
            view.getTextFieldVak().setStyle("-fx-border-color: red");
            errorMessages.append("-Voer een geldige vak in zonder spaties of nummers.\n");
            counterror++;
        } else {
            view.getTextFieldVak().setStyle("-fx-border-color: green");
        }

        //aangenomen op
        if (Objects.isNull(aangenomenOp) || aangenomenOp.isAfter(LocalDate.now())) {
            view.getDatePicker().setStyle("-fx-border-color: red");
            alert = new Alert(Alert.AlertType.WARNING);
            errorMessages.append("-Datum mag niet leeg zijn en in de toekomst liggen\n");
            counterror++;
        } else {
            view.getDatePicker().setStyle("-fx-border-color: green");
        }

        //als alles goed is saved hij het naar de desbetreffende dao
        if (counterror == 0) {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Opgeslagen!");
            alert.showAndWait();

            docent = view.getListView().getSelectionModel().getSelectedItem();
            //als iets geselecteerd is dan zal hij het updaten anders niet
            if (docent == null) {
                docent = new Docent(naam, vak, stagaire, aangenomenOp);

                view.getListView().getItems().add(docent);
            } else {
                docent.setNaam(naam);
                docent.setVak(vak);
                docent.setStagaire(stagaire);
                docent.setAangenomenOp(aangenomenOp);

                view.getListView().refresh();
            }
            MainApplication.getDocentDAO().addOrUpdate(docent);
            MainApplication.getDocentDAO().save();
        } else {
            alert.setContentText(errorMessages.toString());
            alert.showAndWait();
        }
    }

    /**
     * schakeld van de master naar de detail
     */
    private void handelSchakelButton() {
        Docent docent = view.getListView().getSelectionModel().getSelectedItem();
        StudentController studentController = new StudentController();
        studentController.setUpCombobox(docent);
        MainApplication.switchController(studentController);
    }

    /**
     * leegt alle velden zodat er iets getypt kan woren
     */
    private void handelNieuwButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Nieuwe docent toevoegen?");
        Optional<ButtonType> result = alert.showAndWait();
        //isPresent is voor de NosuchElement exeption
        if (result.isPresent() && result.get() == ButtonType.OK) {
            leegAlleInvoervelden();
        }

    }

    /**
     * verwijder de gekozen listview item daarna leegt hij alle velden
     */
    private void handleVerwijderButton() {
        Docent geselecteerdeDocent = view.getListView().getSelectionModel().getSelectedItem();
        if (geselecteerdeDocent == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Weet je zeker dat deze docent wilt verwijderen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (geselecteerdeDocent != null) {
                List<Student> selectedWerknemers = MainApplication.getStudentDAO().getAllFor(geselecteerdeDocent);
                for (Student student : selectedWerknemers) {
                    MainApplication.getStudentDAO().delete(student);
                }
                docentObservableList.remove(geselecteerdeDocent);
                MainApplication.getDocentDAO().delete(geselecteerdeDocent);
            }
        }
        leegAlleInvoervelden();
        view.getListView().refresh();

    }


    /**
     * Leeg alle invoervelden.
     */
    public void leegAlleInvoervelden() {
        view.getTextField().clear();
        view.getTextField().setStyle(null);

        view.getTextFieldVak().clear();
        view.getTextFieldVak().setStyle(null);

        view.getDatePicker().setValue(null);
        view.getDatePicker().setStyle(null);

        view.getCheckbox().setSelected(false);
        view.getCheckbox().setStyle(null);
        view.getListView().getSelectionModel().clearSelection();
    }


    @Override
    public View getView() {
        return view;
    }
}
