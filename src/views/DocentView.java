package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Docent;

/**
 * The type Docent view.
 */
public class DocentView extends View {
    private GridPane gridPane;

    //De menu knoppen
    private BorderPane borderPaneBestanden;
    private MenuBar menuBarKnoppen;
    private Menu menu;
    private MenuItem menuItemLaden;
    private MenuItem menuItemOpslaan;
    private MenuItem menuItemAfsluiten;

    //sorteren
    private HBox hBoxSorteerEnBestand;
    private BorderPane borderPaneSorteren;
    private MenuBar menuBarKnoppenSorteren;
    private Menu menuSorteren;
    private MenuItem aflopend;
    private MenuItem oplopend;
    private MenuItem datumOrde;
    //Texfield
    private Label labelTextField;
    private TextField textField;
    private HBox hBoxTextField;

    //Textfieldvak
    private Label labelTextFieldVak;
    private TextField textFieldVak;
    private HBox hBoxTextFieldVak;

    //DatePicker
    private Label labelDatePicker;
    private DatePicker datePicker;
    private HBox hBoxDatePicker;

    //CheckBox
    private Label labelCheckbox;
    private CheckBox checkbox;
    private HBox hBoxCheckbox;


    //Opslaan button
    private Button opslaanButton;
    private HBox hBoxButton;

    //Listview
    private ListView<Docent> listView;
    private HBox hBoxListview;

    //nieuw, verwijder en schakel Buttons.
    private Button nieuwButton;
    private Button verwijderButton;
    private Button schakelButton;
    private HBox hBoxButtons;

    //VBoxes
    private VBox vBox;
    private VBox vBox1;
    @Override
    protected Parent initializeView() {
        gridPane = new GridPane();

        //Menu knoppen
        menu = new Menu("Bestand");
        menuItemLaden = new  MenuItem("Laden");
        menuItemOpslaan = new MenuItem("Opslaan");
        menuItemAfsluiten = new MenuItem("Afsluiten");
        menuBarKnoppen = new MenuBar();
        borderPaneBestanden = new BorderPane();

        menu.getItems().addAll(menuItemLaden, menuItemOpslaan, menuItemAfsluiten);
        menuBarKnoppen.getMenus().add(menu);
        borderPaneBestanden.setTop(menuBarKnoppen);
        borderPaneBestanden.setMaxWidth(180);

        //Menu sorteer knoppen
        menuSorteren = new Menu("Soorteren");
        datumOrde = new MenuItem("Oud-Nieuw Datum");
        aflopend = new  MenuItem("A-Z Oplopend");
        oplopend = new MenuItem("A-Z Aflopend");
        menuBarKnoppenSorteren = new MenuBar();
        borderPaneSorteren = new BorderPane();

        menuSorteren.getItems().addAll(datumOrde, aflopend, oplopend);
        menuBarKnoppen.getMenus().add(menuSorteren);
        borderPaneSorteren.setTop(menuBarKnoppenSorteren);
        borderPaneSorteren.setMaxWidth(180);

//        Het maken van de TextField
        labelTextField = new Label("Naam:");
        labelTextField.setPadding(new Insets(0, 15, 5, 10));

        textField = new TextField();
        textField.setMinWidth(500);

        hBoxTextField = new HBox(labelTextField, textField);
        hBoxTextField.setPadding(new Insets(10, 0, 5, 60));

        //Het maken van de TextFieldVak
        labelTextFieldVak = new Label("Vak:");
        labelTextFieldVak.setPadding(new Insets(0, 15, 5, 70));

        textFieldVak = new TextField();
        textFieldVak.setMinWidth(500);

        hBoxTextFieldVak = new HBox(labelTextFieldVak, textFieldVak);
        hBoxTextFieldVak.setPadding(new Insets(10, 0, 5, 10));

        //Het maken van de DatePicker
        labelDatePicker = new Label("Aangenomen op:");
        labelDatePicker.setPadding(new Insets(0, 8, 5, 10));

        datePicker = new DatePicker();
        datePicker.setMinWidth(500);

        hBoxDatePicker = new HBox(labelDatePicker, datePicker);
        hBoxDatePicker.setPadding(new Insets(10, 0, 5, 10));

        //Het maken van de Check box
        labelCheckbox = new Label("Stagaire:");
        labelCheckbox.setPadding(new Insets(0, 8, 5, 55));

        checkbox = new CheckBox();
        hBoxCheckbox = new HBox(labelCheckbox, checkbox);

        hBoxCheckbox.setPadding(new Insets(10, 0, 5, 10));


        //Het maken van de Opslaan button
        opslaanButton = new Button("Opsalaan");
        opslaanButton.setPadding(new Insets(5, 0, 5, 0));
        opslaanButton.setMinWidth(600);

        hBoxButton = new HBox(opslaanButton);
        hBoxButton.setPadding(new Insets(10, 0, 5, 20));

        //Het maken van de Listview
        listView = new ListView<>();
        listView.setMinWidth(600);
        listView.setMaxHeight(160);

        hBoxListview = new HBox(listView);
        hBoxListview.setPadding(new Insets(10, 0, 0, 20));


        //Het maken van de nieuw, verwijder en de schakel Button.
        nieuwButton = new Button("Nieuw");
        nieuwButton.setPadding(new Insets(5, 55, 5, 55));

        verwijderButton = new Button(" Verwijder");
        verwijderButton.setPadding(new Insets(5, 60, 5, 60));

        schakelButton = new Button("Terug naar Studenten");
        schakelButton.setPadding(new Insets(5, 65, 5, 65));

        hBoxButtons = new HBox(nieuwButton, verwijderButton, schakelButton);
        hBoxButtons.setPadding(new Insets(5, 20, 5, 20));
        hBoxButtons.setSpacing(15);
        hBoxButtons.setAlignment(Pos.BOTTOM_CENTER);



//        //alles onder elkaar
        vBox = new VBox(hBoxTextField,hBoxTextFieldVak,hBoxDatePicker,hBoxCheckbox);
        vBox1 = new VBox(hBoxButton, hBoxListview, hBoxButtons);

        // de sorteer en bestand naast elkaar
        hBoxSorteerEnBestand = new HBox(borderPaneBestanden, borderPaneSorteren);
        //Het sorteren van alle Nodes
        gridPane.add(hBoxSorteerEnBestand,0,0);
        gridPane.add(vBox, 0, 1);
        gridPane.add(vBox1, 0, 2);

        return gridPane;
    }

    /**
     * Gets text field.
     *
     * @return the text field
     */
    public TextField getTextField() {
        return textField;
    }

    /**
     * Gets text field vak.
     *
     * @return the text field vak
     */
    public TextField getTextFieldVak() {
        return textFieldVak;
    }

    /**
     * Gets date picker.
     *
     * @return the date picker
     */
    public DatePicker getDatePicker() {
        return datePicker;
    }

    /**
     * Gets checkbox.
     *
     * @return the checkbox
     */
    public CheckBox getCheckbox() {
        return checkbox;
    }

    /**
     * Gets opslaan button.
     *
     * @return the opslaan button
     */
    public Button getOpslaanButton() {
        return opslaanButton;
    }

    /**
     * Gets nieuw button.
     *
     * @return the nieuw button
     */
    public Button getNieuwButton() {
        return nieuwButton;
    }

    /**
     * Gets aflopend.
     *
     * @return the aflopend
     */
    public MenuItem getAflopend() {
        return aflopend;
    }

    /**
     * Gets oplopend.
     *
     * @return the oplopend
     */
    public MenuItem getOplopend() {
        return oplopend;
    }

    /**
     * Gets datum orde.
     *
     * @return the datum orde
     */
    public MenuItem getDatumOrde() {
        return datumOrde;
    }

    /**
     * Gets verwijder button.
     *
     * @return the verwijder button
     */
    public Button getVerwijderButton() {
        return verwijderButton;
    }

    /**
     * Gets schakel button.
     *
     * @return the schakel button
     */
    public Button getSchakelButton() {
        return schakelButton;
    }

    /**
     * Gets list view.
     *
     * @return the list view
     */
    public ListView<Docent> getListView() {
        return listView;
    }

    /**
     * Gets menu item laden.
     *
     * @return the menu item laden
     */
    public MenuItem getMenuItemLaden() {
        return menuItemLaden;
    }

    /**
     * Gets menu item opslaan.
     *
     * @return the menu item opslaan
     */
    public MenuItem getMenuItemOpslaan() {
        return menuItemOpslaan;
    }

    /**
     * Gets menu item afsluiten.
     *
     * @return the menu item afsluiten
     */
    public MenuItem getMenuItemAfsluiten() {
        return menuItemAfsluiten;
    }
}
