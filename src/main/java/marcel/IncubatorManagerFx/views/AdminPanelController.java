package marcel.IncubatorManagerFx.views;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import marcel.IncubatorManagerFx.app.IncubatorManagementApp;
import marcel.IncubatorManagerFx.entity.User;

public class AdminPanelController implements Initializable {

	private IncubatorManagementApp incubatorManagementApp;

	@FXML Button btNewIncubator;
	@FXML Button btNewuser;
	@FXML Button btAlarmLogs;
	@FXML Button btReturnToOverview;

	@FXML Label currentTime;
	@FXML Label hospitalName;

	@FXML LineChart<Date, Number> lineChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		hospitalName.setText(getLoggedOnUser().getHospital().getName());
		bindTimeLabelToTime();
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

	public LineChart<Date, Number> getLineChart() {
		return lineChart;
	}

	public void setLineChart(LineChart<Date, Number> lineChart) {
		this.lineChart = lineChart;
	}





}
