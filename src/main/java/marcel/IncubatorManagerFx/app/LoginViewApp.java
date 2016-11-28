package marcel.IncubatorManagerFx.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import marcel.IncubatorManagerFx.views.LoginController;

public class LoginViewApp extends Application {

	private Stage stage;
	private AnchorPane rootLayout;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("Apollo V1.0 by MIH");

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});

		initRootLayout();

	}

	private void initRootLayout() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(LoginViewApp.class.getResource("/marcel/IncubatorManagerFx/views/LoginView.fxml"));
		rootLayout = (AnchorPane) loader.load();

		Scene scene = new Scene(rootLayout);
		stage.setScene(scene);

		LoginController controller = loader.getController();
		controller.setLoginViewApp(this);

		stage.show();
	}

	public static void main( String[] args ) {
		launch(args);
	}
}
