package marcel.IncubatorManagerFx.views;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Duration;
import marcel.IncubatorManagerFx.app.IncubatorManagementApp;
import marcel.IncubatorManagerFx.app.IncubatorOverviewApp;
import marcel.IncubatorManagerFx.dao.IncubatorHibernateDAO;
import marcel.IncubatorManagerFx.dao.LogHibernateDAO;
import marcel.IncubatorManagerFx.datamodel.IncubatorDataModel;
import marcel.IncubatorManagerFx.entity.Incubator;
import marcel.IncubatorManagerFx.entity.Log;
import marcel.IncubatorManagerFx.entity.LogType;
import marcel.IncubatorManagerFx.entity.User;
import marcel.IncubatorManagerFx.utils.JsonReader;

public class IncubatorOverviewController implements Initializable {

	IncubatorOverviewApp incubatorOverviewApp;
	Timeline timeline;
	Thread thread;
	Task<Void> taskFetchDweetNoise;

	private boolean isPaused = false;

	//Layout items
	@FXML Button btAdminPanel;
	@FXML Button btPauseMonitoring;
	@FXML TableView<IncubatorDataModel> tvIncubators;

	//Columns
	@FXML private TableColumn<IncubatorDataModel, String> columnUniqueCode;
	@FXML private TableColumn<IncubatorDataModel, String> columnLocation;
	@FXML private TableColumn<IncubatorDataModel, String> columnStatus;
	@FXML private TableColumn<IncubatorDataModel, String> columnName;
	@FXML private TableColumn<IncubatorDataModel, Integer> columnNoise;

	IncubatorHibernateDAO incubatorDAO;
	LogHibernateDAO logDAO;

	List<Incubator> incubators;

	public void initialize(URL arg0, ResourceBundle arg1) {
		//		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		incubatorDAO = new IncubatorHibernateDAO();
		logDAO = new LogHibernateDAO();
		incubators = incubatorDAO.getUserIncubators(getLoggedOnUser());

		//Hiding the button from non-admins
		if (!getLoggedOnUser().isDeveloper()) {
			btAdminPanel.setVisible(false);
		}

		initializeIncubatorTable();
		initializeFetchingTimeline();

	}

	private void initializeIncubatorTable() {
		incubators = incubatorDAO.listAll(Incubator.class); //TODO fetch user list without lazy loading



		columnName.setCellValueFactory(cellData -> cellData.getValue().getNameColumnProperty());
		columnUniqueCode.setCellValueFactory(cellData -> cellData.getValue().getUniqueCodeColumnProperty());
		columnLocation.setCellValueFactory(cellData -> cellData.getValue().getLocationColumnProperty());
		columnStatus.setCellValueFactory(cellData -> cellData.getValue().getStatusColumnProperty());

		columnNoise.setCellFactory(column -> {
			return new TableCell<IncubatorDataModel,Integer>() {
				@Override
				protected void updateItem(Integer item, boolean empty) {
					super.updateItem(item, empty);
					setGraphic(null);

					TableRow<IncubatorDataModel> currentRow = getTableRow();

					if (!isEmpty()) {
						Integer i = Integer.valueOf(item);
						if(i < 35) 
							setStyle("-fx-background-color:#A9CF55");
						else if (i < 45)
							setStyle("-fx-background-color:#F7E867");
						else
							setStyle("-fx-background-color:#F14440");

						setText(String.valueOf(i));
					}
				}
			};
		});

		columnNoise.setCellValueFactory(cellData -> cellData.getValue().getNoiseColumnProperty().asObject());

	}

	private void initializeFetchingTimeline() {
		timeline = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				taskFetchDweetNoise = new Task<Void>() {

					@Override
					protected Void call() throws Exception {
						ObservableList<IncubatorDataModel> list = incubatorOverviewApp.getObservableIncubatorList();

						for(IncubatorDataModel incubator : list) {
							int currentNoise = incubator.getNoiseColumnProperty().get();
							int newNoise = JsonReader.getIncubatorSound(incubator.getUniqueCodeColumnProperty().get());
							if (newNoise != -1) {
								if (newNoise != currentNoise) {
									incubator.setNoiseColumnProperty(new SimpleIntegerProperty(newNoise));		
								}
							}
							Date date = new Date();
							incubator.setStatusColumnProperty(new SimpleStringProperty("Updated at " + new SimpleDateFormat("HH:mm:ss").format(date)));

							//Logging
							if (newNoise != -1) {
								Log log = new Log();
								log.setDate(date);
								log.setIncubatorName(incubator.getUniqueCodeColumnProperty().get());
								log.setNoiseInDb(newNoise);
								if (newNoise >= 60) log.setLogType(LogType.ALARM);
								else log.setLogType(LogType.COMMON);
								logDAO.persist(log);
							}
						} 


						return null;
					}

					@Override
					protected void succeeded() {
						for(IncubatorDataModel incubator : incubatorOverviewApp.getObservableIncubatorList()) {
							tvIncubators.getColumns().get(3).setVisible(false);
							tvIncubators.getColumns().get(3).setVisible(true);
							tvIncubators.getColumns().get(4).setVisible(false);
							tvIncubators.getColumns().get(4).setVisible(true);
						}
					}
				};
				thread = new Thread(taskFetchDweetNoise);
				thread.setDaemon(true);
				thread.start();
			}		
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}


	@FXML
	private void actionManagementPanel(ActionEvent event) {
		try {
			new IncubatorManagementApp().start(new Stage());
			Stage stage = (Stage) btAdminPanel.getScene().getWindow();
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void actionPauseMonitoring(ActionEvent event) {
		if (!isPaused) {
			timeline.pause();
			btPauseMonitoring.setText("Resume Monitoring");
			btPauseMonitoring.getStyleClass().add("greenbt");
			btPauseMonitoring.getStyleClass().remove("redbt");
		} else {
			timeline.play();
			btPauseMonitoring.setText("Pause Monitoring");
			btPauseMonitoring.getStyleClass().add("redbt");
			btPauseMonitoring.getStyleClass().remove("greenbt");
		}
		isPaused = !isPaused;

	}


	public User getLoggedOnUser() {
		return LoginController.getUserLoggedOn();
	}

	public IncubatorOverviewApp getIncubatorOverviewApp() {
		return incubatorOverviewApp;
	}

	public void setIncubatorOverviewApp(IncubatorOverviewApp incubatorOverviewApp) {
		this.incubatorOverviewApp = incubatorOverviewApp;
		tvIncubators.setItems(incubatorOverviewApp.getObservableIncubatorList());

	}

	public Button getBtAdminPanel() {
		return btAdminPanel;
	}

	public void setBtAdminPanel(Button btAdminPanel) {
		this.btAdminPanel = btAdminPanel;
	}



	public IncubatorHibernateDAO getIncubatorDAO() {
		return incubatorDAO;
	}

	public void setIncubatorDAO(IncubatorHibernateDAO incubatorDAO) {
		this.incubatorDAO = incubatorDAO;
	}

	public List<Incubator> getIncubators() {
		return incubators;
	}

	public void setIncubators(List<Incubator> incubators) {
		this.incubators = incubators;
	}

	public TableView<IncubatorDataModel> getTvIncubators() {
		return tvIncubators;
	}

	public void setTvIncubators(TableView<IncubatorDataModel> tvIncubators) {
		this.tvIncubators = tvIncubators;
	}

	public TableColumn<IncubatorDataModel, String> getColumnUniqueCode() {
		return columnUniqueCode;
	}

	public void setColumnUniqueCode(TableColumn<IncubatorDataModel, String> columnUniqueCode) {
		this.columnUniqueCode = columnUniqueCode;
	}

	public TableColumn<IncubatorDataModel, String> getColumnLocation() {
		return columnLocation;
	}

	public void setColumnLocation(TableColumn<IncubatorDataModel, String> columnLocation) {
		this.columnLocation = columnLocation;
	}

	public TableColumn<IncubatorDataModel, String> getColumnStatus() {
		return columnStatus;
	}

	public void setColumnStatus(TableColumn<IncubatorDataModel, String> columnStatus) {
		this.columnStatus = columnStatus;
	}

	public TableColumn<IncubatorDataModel, String> getColumnName() {
		return columnName;
	}

	public void setColumnName(TableColumn<IncubatorDataModel, String> columnName) {
		this.columnName = columnName;
	}

	public TableColumn<IncubatorDataModel, Integer> getColumnNoise() {
		return columnNoise;
	}

	public void setColumnNoise(TableColumn<IncubatorDataModel, Integer> columnNoise) {
		this.columnNoise = columnNoise;
	}




}
