package application;

import application.Util.SystemUtil;
import application.dao.*;
import application.impl.*;
import application.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.ibatis.session.SqlSession;
import sun.security.provider.SHA;

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

        testTables();
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



    public void testTables() {
//        SqlSession session = SystemUtil.openAndGetSession();
        //        一下测试Clause数据库连接
//        ClauseDao clauseDao = new ClauseDaoImpl();
//        List<Clause> clauses = clauseDao.selectAll();
//        for (int i = 0; i < clauses.size(); i++) {
//            System.out.println(clauses.get(i).toString());
//        }
//        以下测试Line;
//        LineDao lineDao = new LineDaoImpl();
//        List<Line> lines = lineDao.selectAll();
//        for (int i = 0; i < lines.size(); i++) {
//            System.out.println(lines.get(i).toString());
//        }
//        以下测试Parameter数据库连接
//        ParameterDao parameterDao = new ParameterDaoImpl();
//        List<Parameter> parameters = parameterDao.selectAll();
//        Iterator<Parameter> it = parameters.iterator();
//        while (it.hasNext()) {
//            Parameter p = it.next();
//            System.out.println(p.toString());
//        }
//        以下测试Project数据库连接.
//        ProjectDao projectDao = new ProjectDaoImpl();
//        Project project = new Project();
//        project.setDescription("dijie");
//        projectDao.insert(project);
//        System.out.println(project.toString());
//        ProjectDao projectDao = session.getMapper(ProjectDao.class);
//
//        Project project = new Project();
//        project.setProjectName("我的天");
//
//        projectDao.insert(project);
//
//        List<Project> projects = projectDao.selectAll();
//        Iterator<Project> iterator = projects.iterator();
//        while (iterator.hasNext()) {
//            Project p = iterator.next();
//            System.out.println(p.toString());
//        }
//        以下测试Shape连接数据库;
//        ShapeDao shapeDao = new ShapeDaoImpl();
//        List<Shape> shapes = shapeDao.selectAll();
//        Iterator<Shape> iterator = shapes.iterator();
//        while (iterator.hasNext()) {
//            Shape s = iterator.next();
//            System.out.println(s.toString());
//        }
//        session.commit();
//        session.close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
