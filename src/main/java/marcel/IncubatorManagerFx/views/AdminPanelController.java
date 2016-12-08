package marcel.IncubatorManagerFx.views;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.stage.Stage;
import javafx.util.Duration;
import marcel.IncubatorManagerFx.app.IncubatorManagementApp;
import marcel.IncubatorManagerFx.app.IncubatorOverviewApp;
import marcel.IncubatorManagerFx.app.StatisticsApp;
import marcel.IncubatorManagerFx.dao.IncubatorHibernateDAO;
import marcel.IncubatorManagerFx.entity.Incubator;
import marcel.IncubatorManagerFx.entity.Log;
import marcel.IncubatorManagerFx.entity.User;

public class AdminPanelController implements Initializable {

	private IncubatorManagementApp incubatorManagementApp;
	private IncubatorHibernateDAO incubatorDAO;

	@FXML Button btNewIncubator;
	@FXML Button btNewuser;
	@FXML Button btAlarmLogs;
	@FXML Button btReturnToOverview;
	@FXML Button btStatistics;

	@FXML Label currentTime;
	@FXML Label hospitalName;
	
	@FXML Label lbTotalIncubators;
	@FXML Label lbAlarmsToday;
	@FXML Label lbnoiseMean;
	@FXML Label lbLogsToday;
	@FXML Label lbHighest;
	@FXML Label lbLowest;
	
	Timeline timeline;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		incubatorDAO = new IncubatorHibernateDAO();
		hospitalName.setText(getLoggedOnUser().getHospital().getName());
		bindTimeLabelToTime();
		initializeButtonEffects();
		loadStatisticLabels();
		
		timeline = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				loadStatisticLabels();
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

	}

	private void bindTimeLabelToTime() {
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(0),
						new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent actionEvent) {
						Calendar time = Calendar.getInstance();
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
						currentTime.setText(simpleDateFormat.format(time.getTime()));
					}
				}
						),
				new KeyFrame(Duration.seconds(1))
				);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	private void initializeButtonEffects() {
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(0.0);

		btAlarmLogs.setEffect(colorAdjust);

		btAlarmLogs.setOnMouseEntered(e -> {

			Timeline fadeInTimeline = new Timeline(
					new KeyFrame(Duration.seconds(0), 
							new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)), 
					new KeyFrame(Duration.seconds(0.35), new KeyValue(colorAdjust.brightnessProperty(), -0.2, Interpolator.LINEAR)
							));
			fadeInTimeline.setCycleCount(1);
			fadeInTimeline.setAutoReverse(false);
			fadeInTimeline.play();

		});

		btAlarmLogs.setOnMouseExited(e -> {

			Timeline fadeOutTimeline = new Timeline(
					new KeyFrame(Duration.seconds(0), 
							new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)), 
					new KeyFrame(Duration.seconds(0.5), new KeyValue(colorAdjust.brightnessProperty(), 0, Interpolator.LINEAR)
							));
			fadeOutTimeline.setCycleCount(1);
			fadeOutTimeline.setAutoReverse(false);
			fadeOutTimeline.play();

		});
		
		ColorAdjust colorAdjust2 = new ColorAdjust();
		colorAdjust2.setBrightness(0.0);
		
		btNewIncubator.setEffect(colorAdjust2);

		btNewIncubator.setOnMouseEntered(e -> {

			Timeline fadeInTimeline = new Timeline(
					new KeyFrame(Duration.seconds(0), 
							new KeyValue(colorAdjust2.brightnessProperty(), colorAdjust2.brightnessProperty().getValue(), Interpolator.LINEAR)), 
					new KeyFrame(Duration.seconds(0.35), new KeyValue(colorAdjust2.brightnessProperty(), -0.2, Interpolator.LINEAR)
							));
			fadeInTimeline.setCycleCount(1);
			fadeInTimeline.setAutoReverse(false);
			fadeInTimeline.play();

		});

		btNewIncubator.setOnMouseExited(e -> {

			Timeline fadeOutTimeline = new Timeline(
					new KeyFrame(Duration.seconds(0), 
							new KeyValue(colorAdjust2.brightnessProperty(), colorAdjust2.brightnessProperty().getValue(), Interpolator.LINEAR)), 
					new KeyFrame(Duration.seconds(0.5), new KeyValue(colorAdjust2.brightnessProperty(), 0, Interpolator.LINEAR)
							));
			fadeOutTimeline.setCycleCount(1);
			fadeOutTimeline.setAutoReverse(false);
			fadeOutTimeline.play();

		});
		
		ColorAdjust colorAdjust3 = new ColorAdjust();
		colorAdjust3.setBrightness(0.0);
		
		btNewuser.setEffect(colorAdjust3);

		btNewuser.setOnMouseEntered(e -> {

			Timeline fadeInTimeline = new Timeline(
					new KeyFrame(Duration.seconds(0), 
							new KeyValue(colorAdjust3.brightnessProperty(), colorAdjust3.brightnessProperty().getValue(), Interpolator.LINEAR)), 
					new KeyFrame(Duration.seconds(0.35), new KeyValue(colorAdjust3.brightnessProperty(), -0.2, Interpolator.LINEAR)
							));
			fadeInTimeline.setCycleCount(1);
			fadeInTimeline.setAutoReverse(false);
			fadeInTimeline.play();

		});

		btNewuser.setOnMouseExited(e -> {

			Timeline fadeOutTimeline = new Timeline(
					new KeyFrame(Duration.seconds(0), 
							new KeyValue(colorAdjust3.brightnessProperty(), colorAdjust3.brightnessProperty().getValue(), Interpolator.LINEAR)), 
					new KeyFrame(Duration.seconds(0.5), new KeyValue(colorAdjust3.brightnessProperty(), 0, Interpolator.LINEAR)
							));
			fadeOutTimeline.setCycleCount(1);
			fadeOutTimeline.setAutoReverse(false);
			fadeOutTimeline.play();

		});
		
		ColorAdjust colorAdjust4 = new ColorAdjust();
		colorAdjust4.setBrightness(0.0);
		
		btStatistics.setEffect(colorAdjust4);

		btStatistics.setOnMouseEntered(e -> {

			Timeline fadeInTimeline = new Timeline(
					new KeyFrame(Duration.seconds(0), 
							new KeyValue(colorAdjust4.brightnessProperty(), colorAdjust4.brightnessProperty().getValue(), Interpolator.LINEAR)), 
					new KeyFrame(Duration.seconds(0.35), new KeyValue(colorAdjust4.brightnessProperty(), -0.2, Interpolator.LINEAR)
							));
			fadeInTimeline.setCycleCount(1);
			fadeInTimeline.setAutoReverse(false);
			fadeInTimeline.play();

		});

		btStatistics.setOnMouseExited(e -> {

			Timeline fadeOutTimeline = new Timeline(
					new KeyFrame(Duration.seconds(0), 
							new KeyValue(colorAdjust4.brightnessProperty(), colorAdjust4.brightnessProperty().getValue(), Interpolator.LINEAR)), 
					new KeyFrame(Duration.seconds(0.5), new KeyValue(colorAdjust4.brightnessProperty(), 0, Interpolator.LINEAR)
							));
			fadeOutTimeline.setCycleCount(1);
			fadeOutTimeline.setAutoReverse(false);
			fadeOutTimeline.play();

		});
	}

	private void loadStatisticLabels() {
		lbTotalIncubators.setText(""+incubatorDAO.listAll(Incubator.class).size()); //Number of incubators
		lbAlarmsToday.setText(""+incubatorDAO.alarmsToday()); //Nº of Alarms
		
		List<Log> logsFromToday = incubatorDAO.logsToday();
		
		lbLogsToday.setText(logsFromToday.size()+""); //Logs from today
		
		//Beginning calculations for the last labels
		double average = 0;
		int highest = Integer.MIN_VALUE;
		int lowest = Integer.MAX_VALUE;
		for (Log log : logsFromToday) {
			average += log.getNoiseInDb();
			if (log.getNoiseInDb() > highest) highest = log.getNoiseInDb();
			if (log.getNoiseInDb() < lowest) lowest = log.getNoiseInDb();
		}
		average = average / logsFromToday.size();
		
		BigDecimal bd = new BigDecimal(average);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	    
		lbnoiseMean.setText(bd.doubleValue() + " Db"); //Average noise
		lbHighest.setText(highest + " Db");
		lbLowest.setText(lowest + " Db");
	}
	
	@FXML
	private void actionNewIncubator(Event event) {
		incubatorManagementApp.showCreateIncubatorDialog();
	}

	@FXML
	private void actionNewUser(Event event) {
		incubatorManagementApp.showCreateAccountDialog();
	}

	@FXML
	private void actionReturnToOverview(Event event) {
		try {
			timeline.stop();
			new IncubatorOverviewApp().start(new Stage());
			Stage stage = (Stage) btReturnToOverview.getScene().getWindow();
		    stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void actionAlarmLogs(Event event) {
		try {
			timeline.stop();
			StatisticsApp statisticsApp = new StatisticsApp(1);
			statisticsApp.setInitialTab(0);
			statisticsApp.start(new Stage());
			Stage stage = (Stage) btReturnToOverview.getScene().getWindow(); //LOG DE ALARMES AQUI
		    stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void actionStatistics(Event event) {
		try {
			StatisticsApp statisticsApp = new StatisticsApp(1);
			statisticsApp.setInitialTab(1);
			statisticsApp.start(new Stage());
			Stage stage = (Stage) btReturnToOverview.getScene().getWindow(); // CHART AQUI, ENVIAR 1
		    stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User getLoggedOnUser() {
		return LoginController.getUserLoggedOn();
	}

	public IncubatorManagementApp getIncubatorManagementApp() {
		return incubatorManagementApp;
	}

	public void setIncubatorManagementApp(IncubatorManagementApp incubatorManagementApp) {
		this.incubatorManagementApp = incubatorManagementApp;
	}

	public Button getBtNewIncubator() {
		return btNewIncubator;
	}

	public void setBtNewIncubator(Button btNewIncubator) {
		this.btNewIncubator = btNewIncubator;
	}

	public Button getBtNewuser() {
		return btNewuser;
	}

	public void setBtNewuser(Button btNewuser) {
		this.btNewuser = btNewuser;
	}

	public Button getBtAlarmLogs() {
		return btAlarmLogs;
	}

	public void setBtAlarmLogs(Button btAlarmLogs) {
		this.btAlarmLogs = btAlarmLogs;
	}

	public Button getBtReturnToOverview() {
		return btReturnToOverview;
	}

	public void setBtReturnToOverview(Button btReturnToOverview) {
		this.btReturnToOverview = btReturnToOverview;
	}

	public Label getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Label currentTime) {
		this.currentTime = currentTime;
	}

	public Label getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(Label hospitalName) {
		this.hospitalName = hospitalName;
	}
	public IncubatorHibernateDAO getIncubatorDAO() {
		return incubatorDAO;
	}

	public void setIncubatorDAO(IncubatorHibernateDAO incubatorDAO) {
		this.incubatorDAO = incubatorDAO;
	}






}
