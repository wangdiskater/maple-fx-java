import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @Author dwang
 * @Description 主程序
 * @create 2023/3/22 16:40
 * @Modified By:
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
        System.out.println("App" + Thread.currentThread());

    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Maple小助手");
        System.out.println("run application !" + Thread.currentThread());  // Thread[JavaFX Application Thread,5,main]  ,UI线程

        URL mainUrl = getClass().getResource("/main.fxml");
        System.out.println(mainUrl.getPath());

        Parent root = FXMLLoader.load(mainUrl);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

}
