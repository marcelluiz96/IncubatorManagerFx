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
import javafx.util.Duration;
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
	@FXML private ScrollPane scrollPane;

	IncubatorHibernateDAO incubatorDAO;

	List<Incubator> incubators;

	List<Label> incubatorNames;
	List<Label> incubatorLocation;
	List<Label> incubatorNoise;

	public void initialize(URL arg0, ResourceBundle arg1) {
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		incubatorDAO = new IncubatorHibernateDAO();
		incubators = incubatorDAO.getUserIncubators(getLoggedOnUser());

		loadIncubatorsOnGrid();

		//Hiding the button from non-admins
		if (!getLoggedOnUser().isDeveloper()) {
			btAdminPanel.setVisible(false);
		}

	}

	private void loadIncubatorsOnGrid() {
		int colIndex = 0;
		int rowIndex = 0;
		int height = 283;

		gridPane.getChildren().clear();
		gridPane.setGridLinesVisible(true);
		gridPane.setMinHeight(height);

		for (Incubator incubator : incubators) {
			Image image;

			File file = new File("resources//images//babyincubator.jpg");
			image = new Image(file.toURI().toString());

			ImageView img = new ImageView(image);
			img.setFitWidth(265);
			img.setFitHeight(280);

			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setBrightness(0.0);

			img.setEffect(colorAdjust);

			img.setOnMouseEntered(e -> {

				Timeline fadeInTimeline = new Timeline(
						new KeyFrame(Duration.seconds(0), 
								new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)), 
						new KeyFrame(Duration.seconds(0.5), new KeyValue(colorAdjust.brightnessProperty(), -0.3, Interpolator.LINEAR)
								));
				fadeInTimeline.setCycleCount(1);
				fadeInTimeline.setAutoReverse(false);
				fadeInTimeline.play();

			});

			Tooltip t = new Tooltip(incubator.getName());
			Tooltip.install(img.getParent(), t);

			img.setOnMouseExited(e -> {

				Timeline fadeOutTimeline = new Timeline(
						new KeyFrame(Duration.seconds(0), 
								new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)), 
						new KeyFrame(Duration.seconds(0.5), new KeyValue(colorAdjust.brightnessProperty(), 0, Interpolator.LINEAR)
								));
				fadeOutTimeline.setCycleCount(1);
				fadeOutTimeline.setAutoReverse(false);
				fadeOutTimeline.play();

			});

			img.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if (event.getClickCount() == 2) {
						//Open popup
					}
				}

			});


			Label labelNome = new Label(incubator.getName());
			labelNome.setMinWidth(265);
			labelNome.setTextAlignment(TextAlignment.CENTER);
			labelNome.setAlignment(Pos.BOTTOM_CENTER);
			labelNome.setContentDisplay(ContentDisplay.CENTER);
			labelNome.setStyle("-fx-background-color: rgba(100, 100, 100, 0.7);-fx-text-fill:#FFF;-fx-font-size: 20; -fx-font-style: italic;-fx-background-radius: 2;");

			GridPane.setHalignment(labelNome, HPos.CENTER);
			GridPane.setValignment(labelNome, VPos.BOTTOM);


			if (colIndex == 5) {
				colIndex = 0;
				rowIndex++;
				height += 280;
				gridPane.setMinHeight(height);
			}
			Label noiseLabel = new Label("0");
			incubatorNoise.add(noiseLabel);
			gridPane.add(noiseLabel, colIndex, rowIndex);
			gridPane.add(img, colIndex, rowIndex);
			gridPane.add(labelNome, colIndex, rowIndex);
			colIndex++;
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

	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(ScrollPane scrollPane) {
		this.scrollPane = scrollPane;
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
