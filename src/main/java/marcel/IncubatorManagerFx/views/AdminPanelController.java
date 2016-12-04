package marcel.IncubatorManagerFx.views;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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

	@FXML LineChart<String, Number> lineChart;

	ObservableList<XYChart.Data<String, Integer>> xyList = FXCollections.observableArrayList(); //
	ObservableList<String> xAxisCategories = FXCollections.observableArrayList(); //Categories which are displayed on the X Axis 

	private Task<Date> task;
	private XYChart.Series series;
	private CategoryAxis xAxis;
	private int lastObservedSize;
	int i;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		hospitalName.setText(getLoggedOnUser().getHospital().getName());
		bindTimeLabelToTime();
		bindXYList();

		xAxis = new CategoryAxis();
		xAxis.setLabel("X");

		final NumberAxis yAxis = new NumberAxis(); //y Axis
		lineChart = new LineChart<>(xAxis, yAxis);

		lineChart.setTitle("Incubator noise chart");
		lineChart.setAnimated(false);

		task = new Task<Date>() {
			@Override
			protected Date call() throws Exception {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException iex) {
						Thread.currentThread().interrupt();
					}

					if (isCancelled()) {
						break;
					}

					updateValue(new Date());
				}
				return new Date();
			}
		};

		task.valueProperty().addListener(new ChangeListener<Date>() {
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Random random = new Random();

			@Override
			public void changed(ObservableValue<? extends Date> observable, Date oldDate, Date newDate) {
				String strDate = dateFormat.format(newDate);
				xAxisCategories.add(strDate);

				xyList.add(new XYChart.Data(strDate, Integer.valueOf(newDate.getMinutes() + random.nextInt(1312))));

			}
		});

		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(task);

		xAxis.setCategories(xAxisCategories);

		series = new XYChart.Series<>(xyList);
		series.setName("Serie 001");

		lineChart.getData().add(series);

		i = 0;
	}

	private void bindXYList() {
		xyList.addListener((ListChangeListener<XYChart.Data<String, Integer>>) change -> {
			if (change.getList().size() - lastObservedSize > 10) {
				lastObservedSize += 10;
				xAxis.getCategories().remove(0, 10);
			}
		});
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

	@FXML
	private void actionNewIncubator(ActionEvent event) {
		incubatorManagementApp.showCreateIncubatorDialog();
	}

	@FXML
	private void actionNewUser(ActionEvent event) {
		incubatorManagementApp.showCreateAccountDialog();
	}

	@FXML
	private void actionReturnToOverview(ActionEvent event) {
		incubatorManagementApp.showCreateAccountDialog();
	}

	@FXML
	private void actionAlarmLogs(ActionEvent event) {
		incubatorManagementApp.showCreateAccountDialog();
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

	public LineChart<String, Number> getLineChart() {
		return lineChart;
	}

	public void setLineChart(LineChart<String, Number> lineChart) {
		this.lineChart = lineChart;
	}

	public ObservableList<XYChart.Data<String, Integer>> getXyList() {
		return xyList;
	}

	public void setXyList(ObservableList<XYChart.Data<String, Integer>> xyList) {
		this.xyList = xyList;
	}

	public ObservableList<String> getxAxisCategories() {
		return xAxisCategories;
	}

	public void setxAxisCategories(ObservableList<String> xAxisCategories) {
		this.xAxisCategories = xAxisCategories;
	}

	public Task<Date> getTask() {
		return task;
	}

	public void setTask(Task<Date> task) {
		this.task = task;
	}

	public XYChart.Series getSeries() {
		return series;
	}

	public void setSeries(XYChart.Series series) {
		this.series = series;
	}

	public CategoryAxis getxAxis() {
		return xAxis;
	}

	public void setxAxis(CategoryAxis xAxis) {
		this.xAxis = xAxis;
	}

	public int getLastObservedSize() {
		return lastObservedSize;
	}

	public void setLastObservedSize(int lastObservedSize) {
		this.lastObservedSize = lastObservedSize;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}






}
