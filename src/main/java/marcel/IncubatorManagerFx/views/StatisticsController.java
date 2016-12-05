package marcel.IncubatorManagerFx.views;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Duration;
import marcel.IncubatorManagerFx.app.IncubatorManagementApp;
import marcel.IncubatorManagerFx.app.StatisticsApp;
import marcel.IncubatorManagerFx.dao.IncubatorHibernateDAO;
import marcel.IncubatorManagerFx.dao.LogHibernateDAO;
import marcel.IncubatorManagerFx.datamodel.LogDataModel;
import marcel.IncubatorManagerFx.entity.Incubator;

public class StatisticsController implements Initializable {

	private StatisticsApp statisticsApp;
	private LogHibernateDAO logDAO;

	private int initialTab = 0;
	private int chartCounter = 0;

	@FXML TabPane tabPane;

	@FXML Button btReturnToAdminPanelTab1;
	@FXML Button btReturnToAdminPanelTab2;
	@FXML Button btRefreshValuesTab1;
	@FXML Button btRefreshValuesTab2;

	@FXML TableView<LogDataModel> tvLogs;

	@FXML private TableColumn<LogDataModel, Long> columnId;
	@FXML private TableColumn<LogDataModel, String> columnLogType;
	@FXML private TableColumn<LogDataModel, String> columnDate;
	@FXML private TableColumn<LogDataModel, String> columnIncubatorName;
	@FXML private TableColumn<LogDataModel, Integer> columnNoise;

	@FXML private LineChart<String, Number> lineChart;

	//List of series
	private List<Series<String, Number>> seriesList;

	Timeline timeline;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabPane.getSelectionModel().select(initialTab);
		logDAO = new LogHibernateDAO();
		initializeTableValues();
		setupLineChart();
		//		XYChart.Series<String, Number> series = new XYChart.Series<String,Number>();
		//		series.getData().add(new XYChart.Data<Number, Number>(1,113));
		//		series.getData().add(new XYChart.Data<Number, Number>(2,53));
		//
		//		lineChart.getData().add(series);
	}

	private void setupLineChart() {
		seriesList = new ArrayList<Series<String, Number>>();
		for(Incubator incubator : new IncubatorHibernateDAO().listAll(Incubator.class)) {
			XYChart.Series<String, Number> series = new XYChart.Series<String,Number>();
			series.setName(incubator.getUniqueCode());
			lineChart.getData().add(series);

			seriesList.add(series);
		}

		timeline = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				loadSeriesData();

			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}


	private void loadSeriesData() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		for (Series<String, Number> series : seriesList) {
			if (series.getData().size() == 10)
				series.getData().remove(0);
			
			series.getData().add(new XYChart.Data<String,Number>(sdf.format(date), logDAO.getLatestNoiseFrom(series.getName())));
		}
		chartCounter++;
	}

	private void initializeTableValues() {
		columnId.setCellValueFactory(cellData -> cellData.getValue().getIdColumnProperty().asObject());
		columnLogType.setCellValueFactory(cellData -> cellData.getValue().getLogTypeColumnProperty());
		columnDate.setCellValueFactory(cellData -> cellData.getValue().getDateColumnProperty());
		columnIncubatorName.setCellValueFactory(cellData -> cellData.getValue().getUniqueCodeColumnProperty());
		columnNoise.setCellValueFactory(cellData -> cellData.getValue().getNoiseColumnProperty().asObject());

	}

	@FXML
	private void actionReturnToAdminPanel(ActionEvent event) {
		try {
			timeline.stop();
			new IncubatorManagementApp().start(new Stage());
			Stage stage = (Stage) btRefreshValuesTab1.getScene().getWindow();
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void actionRefreshValues(ActionEvent event) {
		//Chamar o método do LOG -> tabela
		loadSeriesData();
	}

	public void selectTab() {
		tabPane.getSelectionModel().select(initialTab);
	}

	public StatisticsApp getStatisticsApp() {
		return statisticsApp;
	}

	public void setStatisticsApp(StatisticsApp statisticsApp) {
		this.statisticsApp = statisticsApp;
		tvLogs.setItems(statisticsApp.getObservableLogList());
	}

	public LogHibernateDAO getLogDAO() {
		return logDAO;
	}

	public void setLogDAO(LogHibernateDAO logDAO) {
		this.logDAO = logDAO;
	}

	public int getInitialTab() {
		return initialTab;
	}

	public void setInitialTab(int initialTab) {
		this.initialTab = initialTab;
	}



}
