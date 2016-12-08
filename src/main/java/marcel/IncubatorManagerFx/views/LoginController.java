package marcel.IncubatorManagerFx.views;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import marcel.IncubatorManagerFx.app.IncubatorOverviewApp;
import marcel.IncubatorManagerFx.app.LoginViewApp;
import marcel.IncubatorManagerFx.dao.UserHibernateDAO;
import marcel.IncubatorManagerFx.entity.User;

public class LoginController implements Initializable {
	
	private LoginViewApp loginViewApp;
	
	private UserHibernateDAO userHibernateDAO;
	
	private static User userLoggedOn;
	
	public LoginController() {
		userHibernateDAO = new UserHibernateDAO();
	}
	
	@FXML
	private TextField txLogin;
	
	@FXML
	private PasswordField txPassword;
	
	@FXML
	private Button btLogin;
	
	@FXML
	private Button btExit;
	
	@FXML
	private Button btResetPassword;

	public void initialize(URL location, ResourceBundle resources) {
		initializeButtonEffects();
		
	}
	
	@FXML
	private void actionLogin(ActionEvent event) {
		String login = txLogin.getText();
		String password = txPassword.getText();
		
		User user = userHibernateDAO.findUser(login, password);
		
		if (user != null) {
			//Usuário encontrado
			userLoggedOn = user;
			try {
				new IncubatorOverviewApp().start(new Stage());
				Stage stage = (Stage) btLogin.getScene().getWindow();
			    stage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Login or password are incorrect!");
			alert.setContentText("Please verify if the credentials were correctly written, and whether the "
					+ "CAPS LOCK key is active.");
			alert.show();	
		}
	}
	
	@FXML
	private void actionResetPassword() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Function not yet implemented");
		alert.setHeaderText("The reset password functionality isn't adequate for usage yet");
		alert.setContentText("Please contact the database manager, or the IT team responsible for your hospital");
		alert.show();	
	}
	
	@FXML
	private void actionExit() {
		Platform.setImplicitExit(false);
        loginViewApp.getStage().close();
        Platform.exit();
        System.exit(0);
	}
	
	private void initializeButtonEffects() {
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(0.0);

		btLogin.setEffect(colorAdjust);

		btLogin.setOnMouseEntered(e -> {

			Timeline fadeInTimeline = new Timeline(
					new KeyFrame(Duration.seconds(0), 
							new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)), 
					new KeyFrame(Duration.seconds(0.35), new KeyValue(colorAdjust.brightnessProperty(), -0.2, Interpolator.LINEAR)
							));
			fadeInTimeline.setCycleCount(1);
			fadeInTimeline.setAutoReverse(false);
			fadeInTimeline.play();

		});

		btLogin.setOnMouseExited(e -> {

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
		
		btExit.setEffect(colorAdjust2);

		btExit.setOnMouseEntered(e -> {

			Timeline fadeInTimeline = new Timeline(
					new KeyFrame(Duration.seconds(0), 
							new KeyValue(colorAdjust2.brightnessProperty(), colorAdjust2.brightnessProperty().getValue(), Interpolator.LINEAR)), 
					new KeyFrame(Duration.seconds(0.35), new KeyValue(colorAdjust2.brightnessProperty(), -0.2, Interpolator.LINEAR)
							));
			fadeInTimeline.setCycleCount(1);
			fadeInTimeline.setAutoReverse(false);
			fadeInTimeline.play();

		});

		btExit.setOnMouseExited(e -> {

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
		
		btResetPassword.setEffect(colorAdjust3);

		btResetPassword.setOnMouseEntered(e -> {

			Timeline fadeInTimeline = new Timeline(
					new KeyFrame(Duration.seconds(0), 
							new KeyValue(colorAdjust3.brightnessProperty(), colorAdjust3.brightnessProperty().getValue(), Interpolator.LINEAR)), 
					new KeyFrame(Duration.seconds(0.35), new KeyValue(colorAdjust3.brightnessProperty(), -0.2, Interpolator.LINEAR)
							));
			fadeInTimeline.setCycleCount(1);
			fadeInTimeline.setAutoReverse(false);
			fadeInTimeline.play();

		});

		btResetPassword.setOnMouseExited(e -> {

			Timeline fadeOutTimeline = new Timeline(
					new KeyFrame(Duration.seconds(0), 
							new KeyValue(colorAdjust3.brightnessProperty(), colorAdjust3.brightnessProperty().getValue(), Interpolator.LINEAR)), 
					new KeyFrame(Duration.seconds(0.5), new KeyValue(colorAdjust3.brightnessProperty(), 0, Interpolator.LINEAR)
							));
			fadeOutTimeline.setCycleCount(1);
			fadeOutTimeline.setAutoReverse(false);
			fadeOutTimeline.play();

		});
	}

	public LoginViewApp getLoginViewApp() {
		return loginViewApp;
	}

	public void setLoginViewApp(LoginViewApp loginViewApp) {
		this.loginViewApp = loginViewApp;
	}

	public UserHibernateDAO getUsuarioHibernateDAO() {
		return userHibernateDAO;
	}

	public void setUsuarioHibernateDAO(UserHibernateDAO usuarioHibernateDAO) {
		this.userHibernateDAO = usuarioHibernateDAO;
	}

	public static User getUserLoggedOn() {
		return userLoggedOn;
	}

	public static void setUserLoggedOn(User userLoggedOn) {
		LoginController.userLoggedOn = userLoggedOn;
	}

	public TextField getTxLogin() {
		return txLogin;
	}

	public void setTxLogin(TextField txLogin) {
		this.txLogin = txLogin;
	}

	public PasswordField getTxPassword() {
		return txPassword;
	}

	public void setTxPassword(PasswordField txPassword) {
		this.txPassword = txPassword;
	}

	public Button getBtLogin() {
		return btLogin;
	}

	public void setBtLogin(Button btLogin) {
		this.btLogin = btLogin;
	}

	public Button getBtExit() {
		return btExit;
	}

	public void setBtExit(Button btExit) {
		this.btExit = btExit;
	}

	public Button getBtResetPassword() {
		return btResetPassword;
	}

	public void setBtResetPassword(Button btResetPassword) {
		this.btResetPassword = btResetPassword;
	}
	
	
	

	
	
	

}
