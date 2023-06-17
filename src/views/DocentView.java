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

public class DocentView extends View {
    private GridPane gridPane;

    //De menu knoppen
    private BorderPane borderPaneBestanden;
    private MenuBar menuBarKnoppen;
    private Menu menu;
    private MenuItem menuItemLaden;
    private MenuItem menuItemOpslaan;
    private MenuItem menuItemAfsluiten;

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
        borderPaneBestanden.setMaxWidth(80);

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


        //Het sorteren van alle Nodes
        gridPane.add(borderPaneBestanden,0,0);
        gridPane.add(vBox, 0, 1);
        gridPane.add(vBox1, 0, 2);

        return gridPane;
    }

    public TextField getTextField() {
        return textField;
    }

    public TextField getTextFieldVak() {
        return textFieldVak;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }

    public Button getOpslaanButton() {
        return opslaanButton;
    }

    public Button getNieuwButton() {
        return nieuwButton;
    }

    public Button getVerwijderButton() {
        return verwijderButton;
    }

    public Button getSchakelButton() {
        return schakelButton;
    }

    public ListView<Docent> getListView() {
        return listView;
    }

    public MenuItem getMenuItemLaden() {
        return menuItemLaden;
    }

    public MenuItem getMenuItemOpslaan() {
        return menuItemOpslaan;
    }

    public MenuItem getMenuItemAfsluiten() {
        return menuItemAfsluiten;
    }
}
