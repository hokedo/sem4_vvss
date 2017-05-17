package Exemplu;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by camelia on 11/16/2016.
 */
public class View2Controller {

    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField textFieldPasword;

    @FXML
    public void handleLogin() {
        User u=new User(textFieldUsername.getText(), textFieldPasword.getText());
        //...
        System.out.println(u);
    }


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {

    }
}
