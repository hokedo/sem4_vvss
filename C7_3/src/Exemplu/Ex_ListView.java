package Exemplu;/**
 * Created by camelia on 11/17/2016.
 */

import domain.Student;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import utils.Observable;

public class Ex_ListView extends Application {

    private ObservableList<Student> studs= FXCollections.observableArrayList();
    @Override
    public void start(Stage stage) {
        studs.add(new Student("1","Pop","Dan1","agfd@jj"));
        studs.add(new Student("2","Stan","Dan2","agfd@jj"));
        studs.add(new Student("3","Anton","Dan3","agfd@jj"));
        initUI(stage);

    }

    private void initUI(Stage stage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 300, 200);
//
//        ListView<Student> list=new ListView<>();
//        list.setItems(studs);
//        root.getChildren().add(list);
//        list.setCellFactory(new Callback<ListView<Student>, ListCell<Student>>() {
//            @Override
//            public ListCell<Student> call(ListView<Student> param) {
//                System.out.println("in updateItem");
//                ListCell<Student> cell=new ListCell<Student>()
//                {
//                    @Override
//                    protected void updateItem(Student s, boolean empty) {
//                        super.updateItem(s, empty);
//                        if (s!=null)
//                            setText(s.getFirstName()+" "+s.getLastName());
//
//                    }
//                };
//                return cell;
//            }
//        });
//        Button b=new Button("ok");
//        root.getChildren().add(b);
//        b.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                studs.add(new Student("45","andrei","nistor","gdhgh"));
//            }
//        });
//
        TableView<Student> t=new TableView<>();
        TableColumn<Student,String> c=new TableColumn<>("prenume");
        t.getColumns().add(c);
        t.setItems(studs);

//        c.setCellFactory(new Callback<TableColumn<Student, String>, TableCell<Student, String>>() {
//            @Override
//            public TableCell<Student, String> call(TableColumn<Student, String> param) {
//                TableCell cell=new TableCell<Student, String>(){
//                    public void updateItem(String item, boolean empty) {
//                        //System.out.println(item);
//                        super.updateItem(item, empty);
//                        setText(item);
//                    }
//                };
//                cell.setFont(new Font(23));
//                cell.setAlignment(Pos.CENTER);
//                return cell;
//            }
//        });
//        //c.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        c.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> param) {
                Student s=param.getValue();
                return new SimpleStringProperty(s.getFirstName()+s.getLastName());
            }
        });
        root.getChildren().add(t);
        stage.setTitle("Welcome to JavaFX!!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
