package marcel.IncubatorManagerFx.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import marcel.IncubatorManagerFx.views.IncubatorOverviewController;

public class IncubatorOverviewApp extends Application {

private Stage stage;
private AnchorPane rootLayout;	

@Override
public void start(Stage primaryStage) throws Exception {
	stage = primaryStage;
	stage.setTitle("Incubator Manager");
	stage.setResizable(false);
//	this.stage.getIcons().add(new Image("file:resources/images/NetflixLogo.png"));

	initRootLayout();

}

private void initRootLayout() throws IOException {
	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LoginViewApp.class.getResource("/marcel/IncubatorManagerFx/views/IncubatorOverviewView.fxml"));
	rootLayout = (AnchorPane) loader.load();
	
	Scene scene = new Scene(rootLayout);
	stage.setScene(scene);

	IncubatorOverviewController controller = loader.getController();
	controller.setIncubatorOverviewApp(this);
	
//	controller.getIvLogo().setImage(new Image("file:resources/images/NetflixLogo.png"));

	stage.show();
}



}
