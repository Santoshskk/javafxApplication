package practicumopdracht;

import controllers.Controller;
import controllers.DocentController;
import controllers.StudentController;
import data.DocentDAO;
import data.DocentDummyDAO;
import data.StudentDAO;
import data.StudentDummyDAO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.DocentView;
import views.StudentView;

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

        docentDAO = new DocentDummyDAO();
        studentDAO = new StudentDummyDAO();
        MainApplication.getDocentDAO().load();
        MainApplication.getDocentDAO().save();
        MainApplication.getStudentDAO().load();
        MainApplication.getStudentDAO().save();

        stage.setTitle(String.format(TITEL, Main.studentNaam));
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);


        stage.setTitle(String.format(TITEL, Main.studentNaam));
        MainApplication.stage = stage;
        switchController(new DocentController());


    }

    public static void switchController(Controller controller) {
        Scene scene = new Scene(controller.getView().getRoot(), WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public static DocentDAO getDocentDAO() {
        return docentDAO;
    }

    public static StudentDAO getStudentDAO() {
        return studentDAO;
    }
}
