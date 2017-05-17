package view;

import domain.Student;

import domain.validators.ValidatorException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.StudentService;
import utils.Observable;
import utils.Observer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class StudentViewController implements Observer<Student>{
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;


    StudentService service;
    ObservableList<Student> model;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public StudentViewController() {

    }

    public void setService(StudentService service ) {
        this.service=service;
        this.model=FXCollections.observableArrayList(service.getAllStudents());
        studentTable.setItems(model);
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        // Initialize the student table with the three columns.
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
    }

    @FXML
    public void handleSaveStudent()
    {
        showStudentEditDialog(null);
    }

    public void showStudentEditDialog(Student student) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StudentViewController.class.getResource("EditStudentView.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            EditStudentViewController controller = loader.getController();
            controller.setService(service, dialogStage,student);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable<Student> observable) {
        StudentService service=(StudentService)observable;
        model.setAll(service.getAllStudents());
    }
}
