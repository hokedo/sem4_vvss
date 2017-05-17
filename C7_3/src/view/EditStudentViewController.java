package view;

import domain.Student;
import domain.validators.ValidatorException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.StudentService;

/**
 * Created by camelia on 11/15/2016.
 */
public class EditStudentViewController {

    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldFirstName;
    @FXML
    private TextField textFieldLastName;
    @FXML
    private TextField textFieldEmail;

    private StudentService service;
    static Stage dialogStage;
    Student student;

    @FXML
    private void initialize() {
    }


    public void setService(StudentService service,  Stage stage, Student s) {
        this.service = service;
        this.dialogStage=stage;
        this.student=s;

    }

    @FXML
    public void handleSave(){
        String id=textFieldId.getText();
        String nume=textFieldLastName.getText();
        String pren=textFieldFirstName.getText();
        String email=textFieldEmail.getText();
        Student s=new Student(id,nume,pren,email);
        try {
            Student  saved=service.save(s);
            if (saved==null) {
                showMessage(Alert.AlertType.INFORMATION, "Salvare cu succes", "Studentul a fost adaugat!");
                clearFields();
            }
            else
                showErrorMessage("Exista deja un student cu acest id!");
        } catch (ValidatorException e1) {
            showErrorMessage(e1.getMessage());
        }
        dialogStage.close();

    }

    private void clearFields() {
        textFieldFirstName.setText("");
        textFieldLastName.setText("");
        textFieldEmail.setText("");
        textFieldId.setText("");
    }

    @FXML
    public void handleCancel(){
        dialogStage.close();
    }


    static void showMessage(Alert.AlertType type, String header, String text){
        Alert message=new Alert(type);
        message.setHeaderText(header);
        message.setContentText(text);
        message.initOwner(dialogStage);
        message.showAndWait();
    }

    static void showErrorMessage(String text){
        Alert message=new Alert(Alert.AlertType.ERROR);
        message.initOwner(dialogStage);
        message.setTitle("Mesaj eroare");
        message.setContentText(text);
        message.showAndWait();
    }
}
