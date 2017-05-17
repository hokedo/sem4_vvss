package sample;

import domain.Student;

import domain.validators.StudentValidator;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import repository.StudentFileRepository;
import service.StudentService;
import view.StudentViewController;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main extends Application {
	final static Logger logger = LogManager.getLogger(Main.class.getName());
    BorderPane rootLayout;
    Stage primaryStage;
    StudentFileRepository repo;
    StudentService service;

    @Override
    public void start(Stage primaryStage) {
        Validator<Student> vali= new StudentValidator();

        repo=new StudentFileRepository(vali,"./src/data/Students.txt");
        service=new StudentService(repo);

        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("Student Management System");

        initRootLayout();
        initStudentView();
        logger.info("Am terminat de initalizat");
    }

    private void initStudentView() {
        try {
            // Load student view.
            FXMLLoader loader = new FXMLLoader();
            String path = "/view/StudentView.fxml";
            logger.info(path);
            loader.setLocation(Main.class.getResource(path));
            AnchorPane studentView = (AnchorPane) loader.load();
            rootLayout.setCenter(studentView);

            //set the service and the model for controller class
            StudentViewController viewCtrl=loader.getController();
            viewCtrl.setService(service);
            service.addObserver(viewCtrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            //Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
