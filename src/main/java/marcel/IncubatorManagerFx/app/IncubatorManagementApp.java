package marcel.IncubatorManagerFx.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import marcel.IncubatorManagerFx.views.AdminPanelController;
import marcel.IncubatorManagerFx.views.IncubatorOverviewController;

public class IncubatorManagementApp extends Application{

	private Stage stage;
	private AnchorPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("Incubator Manager");
		stage.setResizable(false);
		
		initRootLayout();
	}
	
	private void initRootLayout() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(IncubatorManagementApp.class.getResource("/marcel/IncubatorManagerFx/views/AdminPanelView.fxml"));
		rootLayout = (AnchorPane) loader.load();
		
		Scene scene = new Scene(rootLayout);
		stage.setScene(scene);

		AdminPanelController controller = loader.getController();
		controller.setIncubatorManagementApp(this);
		
//		controller.getIvLogo().setImage(new Image("file:resources/images/NetflixLogo.png"));

		stage.show();
	}
}
