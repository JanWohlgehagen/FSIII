import gui.controller.LoginController;
import gui.util.ISceneLoader;
import gui.util.LoginScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ISceneLoader<LoginController> loginScene =  new LoginScene();
        loginScene.loadNewScene(primaryStage);
        LoginController loginController = loginScene.getController();

        loginController.setPrimaryStage(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }


}
