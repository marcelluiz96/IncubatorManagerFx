package marcel.IncubatorManagerFx.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import marcel.IncubatorManagerFx.dao.IncubatorHibernateDAO;
import marcel.IncubatorManagerFx.datamodel.IncubatorDataModel;
import marcel.IncubatorManagerFx.entity.Incubator;
import marcel.IncubatorManagerFx.views.IncubatorOverviewController;

public class IncubatorOverviewApp extends Application {

	private Stage stage;
	private AnchorPane rootLayout;
	private ObservableList<IncubatorDataModel> observableIncubatorList = FXCollections.observableArrayList(); 

	public IncubatorOverviewApp() {
		for (Incubator incubator : new IncubatorHibernateDAO().listAll(Incubator.class)) {
			observableIncubatorList.add(new IncubatorDataModel(incubator));
		}
	}

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

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public AnchorPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(AnchorPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	public ObservableList<IncubatorDataModel> getObservableIncubatorList() {
		return observableIncubatorList;
	}

	public void setObservableIncubatorList(ObservableList<IncubatorDataModel> observableIncubatorList) {
		this.observableIncubatorList = observableIncubatorList;
	}

}
