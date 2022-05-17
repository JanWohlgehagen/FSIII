import be.Helbredstilstand;
import be.HelbredstilstandsUnderkategori;
import gui.controller.AdminDashboardController;
import gui.controller.LoginController;
import gui.util.AdminDashboardScene;
import gui.util.ISceneLoader;
import gui.util.LoginScene;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /**
        ISceneLoader<AdminDashboardController> test = new AdminDashboardScene();
        test.loadNewScene(primaryStage);
         **/


        ISceneLoader<LoginController> loginScene =  new LoginScene();
        loginScene.loadNewScene(primaryStage);
        LoginController loginController = loginScene.getController();

        loginController.setPrimaryStage(primaryStage);


    }

    public static void main(String[] args) {
        launch(args);
    }


}
