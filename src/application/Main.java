package application;

import application.controller.MenuController;
import application.log.SystemUtil;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

//    private final Node folderIcon = new ImageView(
//            new Image(getClass().getResourceAsStream("application/resource/icons/folder.png"))
//    );

    /**
     * 国际化资源文件加载
     */
    ResourceBundle resourceBundle;

    @Override
    public void start(Stage primaryStage) throws Exception{

        resourceBundle = ResourceBundle.getBundle("application.Lan", new Locale(SystemUtil.getLanguage()));
        //加载布局
        primaryStage.setTitle(resourceBundle.getString("application.title"));
        URL location = getClass().getResource("fxml/mainpage.fxml");
        FXMLLoader loader = new FXMLLoader(location, resourceBundle);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(location);
        BorderPane root = loader.load(location.openStream());
        primaryStage.setScene(new Scene(root, 600, 275));

        //加载菜单
//        MenuBar menuBar = FXMLLoader.load(getClass().getResource("fxml/menu.fxml"), resourceBundle);
//        root.setTop(menuBar);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
