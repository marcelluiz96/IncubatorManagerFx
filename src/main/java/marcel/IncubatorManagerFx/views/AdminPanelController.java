package marcel.IncubatorManagerFx.views;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AdminPanelController implements Initializable{
	
	@FXML Button btNewIncubator;
	@FXML Button btNewuser;
	@FXML Button btAlarmLogs;
	@FXML Button btReturnToOverview;
	
	@FXML Label currentTime;
	@FXML Label hospitalName;
	
	@FXML LineChart<Date, Number> lineChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
