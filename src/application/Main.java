package application;

import application.Util.SystemUtil;
import application.dao.ProjectDao;
import application.model.Project;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.ibatis.session.SqlSession;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
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

        SqlSession session = SystemUtil.openAndGetSession();
        ProjectDao projectDao = session.getMapper(ProjectDao.class);

        Project project = new Project();
        project.setProjectName("我的天");

//        projectDao.insert(project);
        System.out.println(projectDao.countAll());

        List<Project> projects = projectDao.selectAll();
        Iterator<Project> iterator = projects.iterator();
        while (iterator.hasNext()) {
            Project p = iterator.next();
            System.out.println("id:" + p.getProjectID() + "project:" + p.getProjectName());
        }
        session.commit();
        session.close();
        resourceBundle = ResourceBundle.getBundle("application.Lan", new Locale(SystemUtil.getLanguage()));
        //加载布局
        primaryStage.setTitle(resourceBundle.getString("application.title"));
        URL location = getClass().getResource("fxml/mainpage.fxml");
        FXMLLoader loader = new FXMLLoader(location, resourceBundle);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(location);
        BorderPane root = loader.load(location.openStream());
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
