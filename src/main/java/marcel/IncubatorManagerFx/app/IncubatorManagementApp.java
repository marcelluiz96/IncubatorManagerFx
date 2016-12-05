package marcel.IncubatorManagerFx.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import marcel.IncubatorManagerFx.views.AdminPanelController;
import marcel.IncubatorManagerFx.views.CreateAccountDialogController;
import marcel.IncubatorManagerFx.views.CreateIncubatorDialogController;
import marcel.IncubatorManagerFx.views.IncubatorOverviewController;

public class IncubatorManagementApp extends Application{

	private Stage stage;
	private AnchorPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("Luna V1.0 - Incubator Management");
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


		stage.show();
	}
	
	public boolean showCreateIncubatorDialog() {
		  try {
			    // Load the fxml file and create a new stage for the popup
			    FXMLLoader loader = new FXMLLoader(LoginViewApp.class.getResource("/marcel/IncubatorManagerFx/views/CreateIncubatorDialogView.fxml"));
			    AnchorPane page = (AnchorPane) loader.load();
			    Stage dialogStage = new Stage();
			    dialogStage.setTitle("Edit Person");
			    dialogStage.initModality(Modality.WINDOW_MODAL);
			    dialogStage.initOwner(stage);
			    Scene scene = new Scene(page);
			    dialogStage.setScene(scene);

			    // Set the person into the controller
			    CreateIncubatorDialogController controller = loader.getController();
			    controller.setDialogStage(dialogStage);
			    

			    // Show the dialog and wait until the user closes it
			    dialogStage.showAndWait();

			    return controller.isOkClicked();

			  } catch (IOException e) {
			    // Exception gets thrown if the fxml file could not be loaded
			    e.printStackTrace();
			    return false;
			  }
	}
	
	public boolean showCreateAccountDialog() {
		  try {
			    // Load the fxml file and create a new stage for the popup
			    FXMLLoader loader = new FXMLLoader(LoginViewApp.class.getResource("/marcel/IncubatorManagerFx/views/CreateAccountDialogView.fxml"));
			    AnchorPane page = (AnchorPane) loader.load();
			    Stage dialogStage = new Stage();
			    dialogStage.setTitle("Edit Person");
			    dialogStage.initModality(Modality.WINDOW_MODAL);
			    dialogStage.initOwner(stage);
			    Scene scene = new Scene(page);
			    dialogStage.setScene(scene);

			    // Set the person into the controller
			    CreateAccountDialogController controller = loader.getController();
			    controller.setDialogStage(dialogStage);
			    

			    // Show the dialog and wait until the user closes it
			    dialogStage.showAndWait();

			    return controller.isOkClicked();

			  } catch (IOException e) {
			    // Exception gets thrown if the fxml file could not be loaded
			    e.printStackTrace();
			    return false;
			  }
	}
}
