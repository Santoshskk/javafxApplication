package practicumopdracht;

import controllers.Controller;
import controllers.DocentController;
import controllers.StudentController;
import data.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.DocentView;
import views.StudentView;

/**
 * The type Main application.
 */
public class MainApplication extends Application {

    private static final String TITEL= "Practicumopdracht OOP2 - %s";
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static Stage stage;

    private static DocentDAO docentDAO;
    private static StudentDAO studentDAO;



    @Override
    public void start(Stage stage) {
        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

//        docentDAO = new DocentDummyDAO();
//        studentDAO = new StudentDummyDAO();

//        docentDAO = new DocentTextDAO();
//        studentDAO = new StudentTextDAO();

        docentDAO = new BinaryDocentDAO();
        studentDAO = new ObjectStudentDAO();


        stage.setTitle(String.format(TITEL, Main.studentNaam));
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);


        stage.setTitle(String.format(TITEL, Main.studentNaam));
        MainApplication.stage = stage;
        switchController(new DocentController());


    }

    /**
     * Switch controller.
     *
     * @param controller the controller
     */
    public static void switchController(Controller controller) {
        Scene scene = new Scene(controller.getView().getRoot(), WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Gets docent dao.
     *
     * @return the docent dao
     */
    public static DocentDAO getDocentDAO() {
        return docentDAO;
    }

    /**
     * Gets student dao.
     *
     * @return the student dao
     */
    public static StudentDAO getStudentDAO() {
        return studentDAO;
    }
}
