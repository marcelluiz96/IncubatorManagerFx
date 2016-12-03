package marcel.IncubatorManagerFx.views;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import marcel.IncubatorManagerFx.app.IncubatorManagementApp;
import marcel.IncubatorManagerFx.app.IncubatorOverviewApp;
import marcel.IncubatorManagerFx.dao.IncubatorHibernateDAO;
import marcel.IncubatorManagerFx.entity.Incubator;
import marcel.IncubatorManagerFx.entity.User;

public class IncubatorOverviewController implements Initializable {

	IncubatorOverviewApp incubatorOverviewApp;

	//Layout items
	@FXML Button btAdminPanel;

	List<Button> incubatorButtons;
	//Panes
	@FXML private GridPane gridPane;

	IncubatorHibernateDAO incubatorDAO;

	List<Incubator> incubators;

	List<Label> incubatorNames;
	List<Label> incubatorLocation;
	List<Label> incubatorNoise;

	public void initialize(URL arg0, ResourceBundle arg1) {
//		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		incubatorDAO = new IncubatorHibernateDAO();
		incubators = incubatorDAO.getUserIncubators(getLoggedOnUser());

		//Hiding the button from non-admins
		if (!getLoggedOnUser().isDeveloper()) {
			btAdminPanel.setVisible(false);
		}

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
	//TODO update labels based



	public User getLoggedOnUser() {
		return LoginController.getUserLoggedOn();
	}

	public IncubatorOverviewApp getIncubatorOverviewApp() {
		return incubatorOverviewApp;
	}

	public void setIncubatorOverviewApp(IncubatorOverviewApp incubatorOverviewApp) {
		this.incubatorOverviewApp = incubatorOverviewApp;
	}

	public Button getBtAdminPanel() {
		return btAdminPanel;
	}

	public void setBtAdminPanel(Button btAdminPanel) {
		this.btAdminPanel = btAdminPanel;
	}

	public List<Button> getIncubatorButtons() {
		return incubatorButtons;
	}

	public void setIncubatorButtons(List<Button> incubatorButtons) {
		this.incubatorButtons = incubatorButtons;
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
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

	public List<Label> getIncubatorNames() {
		return incubatorNames;
	}

	public void setIncubatorNames(List<Label> incubatorNames) {
		this.incubatorNames = incubatorNames;
	}

	public List<Label> getIncubatorLocation() {
		return incubatorLocation;
	}

	public void setIncubatorLocation(List<Label> incubatorLocation) {
		this.incubatorLocation = incubatorLocation;
	}

	public List<Label> getIncubatorNoise() {
		return incubatorNoise;
	}

	public void setIncubatorNoise(List<Label> incubatorNoise) {
		this.incubatorNoise = incubatorNoise;
	}


}
