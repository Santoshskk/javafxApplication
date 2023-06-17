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
import models.Student;

/**
 * The type Student view.
 */
public class StudentView extends View{

    private GridPane gridPane;
    //menu voor het sorteren
    private BorderPane borderPaneSorteren;
    private Menu sorteerMenu;
    private MenuBar menuBar;
    private MenuItem cijferAflopend;
    private MenuItem cijferOplopend;

    //ComboboxClub
    private ComboBox<Docent> comboBoxDocent;
    private Label labelcomboboxDocent;
    private HBox hBoxComboboxDocent;

    //TextField
    private Label labelTextField;
    private TextField textField;
    private HBox hBoxTextField;

    //TextField Studentnummer
    private Label labelTextFieldStudentnummer;
    private TextField textFieldStudentnummer;
    private HBox hBoxTextFieldStudentnummer;

    //TextField gekregenCijfer
    private Label labelTextFieldGekregenCijfer;
    private TextField textFieldGekregenCijfer;
    private HBox hBoxTextFieldGekregenCijfer;

    //TextArea Feedback
    private Label labelTextAreaFeedback;
    private TextArea textAreaFeedback;
    private HBox hBoxTextAreaFeedback;

    //Opslaan button
    private Button opslaanButton;
    private HBox hBoxButton;

    //Listview
    private ListView<Student> listView;
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

        // het aanmaken van de sorteer menu
        menuBar = new MenuBar();


        sorteerMenu = new Menu("Sorteren");
        cijferAflopend = new MenuItem("Hoog-Laag cijfer");
        cijferOplopend = new MenuItem(" Laag-Hoog cijfer");
        borderPaneSorteren=new BorderPane();

        sorteerMenu.getItems().addAll(cijferAflopend, cijferOplopend);
        menuBar.getMenus().add(sorteerMenu);
        borderPaneSorteren.setTop(menuBar);
        borderPaneSorteren.setMaxWidth(80);


        //Het  aanmaken van de Combobox
        labelcomboboxDocent = new Label("Docent: ");
        labelcomboboxDocent.setPadding(new Insets(0, 52, 5, 10));

        comboBoxDocent = new ComboBox<>();
        comboBoxDocent.setMinWidth(500);


        hBoxComboboxDocent = new HBox(labelcomboboxDocent, comboBoxDocent);
        hBoxComboboxDocent.setPadding(new Insets(10, 0, 5, 10));

        //TextField
        labelTextField = new Label("Naam:");
        labelTextField.setPadding(new Insets(0, 58, 5, 10));

        textField = new TextField();
        textField.setMinWidth(500);

        hBoxTextField = new HBox(labelTextField, textField);
        hBoxTextField.setPadding(new Insets(10, 0, 5, 10));



        //TextField Studentnummer
        labelTextFieldStudentnummer = new Label("Studentnummer:");
        labelTextFieldStudentnummer.setPadding(new Insets(0, 15, 5, 10));

        textFieldStudentnummer = new TextField();
        textFieldStudentnummer.setMinWidth(500);

        hBoxTextFieldStudentnummer = new HBox(labelTextFieldStudentnummer, textFieldStudentnummer);
        hBoxTextFieldStudentnummer.setPadding(new Insets(10, 0, 5, 10));

        //TextField gekregenCijfer
        labelTextFieldGekregenCijfer = new Label("Gekregen Cijfer:");
        labelTextFieldGekregenCijfer.setPadding(new Insets(0, 17, 5, 10));

        textFieldGekregenCijfer = new TextField();
        textFieldGekregenCijfer.setMinWidth(500);

        hBoxTextFieldGekregenCijfer = new HBox(labelTextFieldGekregenCijfer, textFieldGekregenCijfer);
        hBoxTextFieldGekregenCijfer.setPadding(new Insets(10, 0, 5, 10));

        //TextField
        labelTextAreaFeedback = new Label("FeedBack:");
        labelTextAreaFeedback.setPadding(new Insets(0, 42, 5, 10));

        textAreaFeedback = new TextArea();
        textAreaFeedback.setMinWidth(500);
        textAreaFeedback.setMaxHeight(25);

        hBoxTextAreaFeedback = new HBox(labelTextAreaFeedback, textAreaFeedback);
        hBoxTextAreaFeedback.setPadding(new Insets(10, 0, 5, 10));

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

        schakelButton = new Button("Terug naar Docent");
        schakelButton.setPadding(new Insets(5, 75, 5, 75));

        hBoxButtons = new HBox(nieuwButton, verwijderButton, schakelButton);
        hBoxButtons.setPadding(new Insets(5, 20, 5, 20));
        hBoxButtons.setSpacing(15);
        hBoxButtons.setAlignment(Pos.BOTTOM_CENTER);

        //alles onder elkaar
        vBox = new VBox(
                hBoxComboboxDocent,
                hBoxTextField,
                hBoxTextFieldStudentnummer,
                hBoxTextFieldGekregenCijfer,
                hBoxTextAreaFeedback,
                hBoxListview
        );
        vBox1 = new VBox(hBoxButton, hBoxListview, hBoxButtons);



        //Het sorteren van alle Nodes
        gridPane.add(borderPaneSorteren,0,0);
        gridPane.add(vBox, 0, 1);
        gridPane.add(vBox1, 0, 2);

        return gridPane;
    }

    /**
     * Gets combo box docent.
     *
     * @return the combo box docent
     */
    public ComboBox<Docent> getComboBoxDocent() {
        return comboBoxDocent;
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
     * Gets text field studentnummer.
     *
     * @return the text field studentnummer
     */
    public TextField getTextFieldStudentnummer() {
        return textFieldStudentnummer;
    }

    /**
     * Gets text field gekregen cijfer.
     *
     * @return the text field gekregen cijfer
     */
    public TextField getTextFieldGekregenCijfer() {
        return textFieldGekregenCijfer;
    }

    /**
     * Gets text area feedback.
     *
     * @return the text area feedback
     */
    public TextArea getTextAreaFeedback() {
        return textAreaFeedback;
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
     * Gets list view.
     *
     * @return the list view
     */
    public ListView<Student> getListView() {
        return listView;
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
     * Gets cijfer aflopend.
     *
     * @return the cijfer aflopend
     */
    public MenuItem getCijferAflopend() {
        return cijferAflopend;
    }

    /**
     * Gets cijfer oplopend.
     *
     * @return the cijfer oplopend
     */
    public MenuItem getCijferOplopend() {
        return cijferOplopend;
    }
}
