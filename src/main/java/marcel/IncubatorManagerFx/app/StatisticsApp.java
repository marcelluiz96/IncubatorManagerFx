package marcel.IncubatorManagerFx.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import marcel.IncubatorManagerFx.dao.LogHibernateDAO;
import marcel.IncubatorManagerFx.datamodel.LogDataModel;
import marcel.IncubatorManagerFx.entity.Log;
import marcel.IncubatorManagerFx.views.IncubatorOverviewController;
import marcel.IncubatorManagerFx.views.StatisticsController;

public class StatisticsApp extends Application{
	
	private AnchorPane rootLayout;
	private Stage stage;
	private ObservableList<LogDataModel> observableLogList = FXCollections.observableArrayList();
	private int initialTab;
	
	public StatisticsApp(int initialTab) {
		for (Log log : new LogHibernateDAO().alarmsToday()) {
			observableLogList.add(new LogDataModel(log));
		}
		
		this.initialTab = initialTab;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("Luna v1.0 - Statistics");
		stage.setResizable(false);

		initRootLayout();
		
	}
	
	private void initRootLayout() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StatisticsApp.class.getResource("/marcel/IncubatorManagerFx/views/StatisticsView.fxml"));
		rootLayout = (AnchorPane) loader.load();

		Scene scene = new Scene(rootLayout);
		stage.setScene(scene);

		StatisticsController controller = loader.getController(); //ENVIAR INT AQUI
		controller.setStatisticsApp(this);
		controller.setInitialTab(this.initialTab);
		controller.selectTab();
		stage.show();
	}

	public AnchorPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(AnchorPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public ObservableList<LogDataModel> getObservableLogList() {
		return observableLogList;
	}

	public void setObservableLogList(ObservableList<LogDataModel> observableLogList) {
		this.observableLogList = observableLogList;
	}

	public int getInitialTab() {
		return initialTab;
	}

	public void setInitialTab(int initialTab) {
		this.initialTab = initialTab;
	}
	
	

}
