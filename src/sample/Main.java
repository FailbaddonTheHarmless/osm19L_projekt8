package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {
    String filePath;
    Integer K;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmll = new FXMLLoader(getClass().getResource("sample/sample.fxml"));
        Parent root = fxmll.load();
        Controller controller = fxmll.getController();

        TextInputDialog dialog = new TextInputDialog("C:\\Users\\Dejwu\\Downloads\\gs_neo4j_workshop-master\\OSM_Projekt#8\\src\\sample\\sample.jpg");
        dialog.setTitle("Segmentation Parameters");
        dialog.setHeaderText("Please enter DICOM FILEPATH");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            filePath = result.get();
        }
        controller.setPath(filePath);

        dialog = new TextInputDialog("3");
        dialog.setTitle("Segmentation Parameters");
        dialog.setHeaderText("Please enter number of clusters (In range <1,10>");
        result = dialog.showAndWait();
        if (result.isPresent()){
            K = Integer.parseInt(result.get());
        }
        controller.setK(K);
        controller.ini();

        primaryStage.setTitle("K-Segmentation");
        primaryStage.setScene(new Scene(controller.getPane()));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
