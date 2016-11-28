package marcel.IncubatorManagerFx.views;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import marcel.IncubatorManagerFx.app.IncubatorOverviewApp;
import marcel.IncubatorManagerFx.app.LoginViewApp;
import marcel.IncubatorManagerFx.dao.UserHibernateDAO;
import marcel.IncubatorManagerFx.entity.User;

public class LoginController implements Initializable {
	
	private LoginViewApp loginViewApp;
	
	private UserHibernateDAO usuarioHibernateDAO;
	
	private static User userLoggedOn;
	
	public LoginController() {
		usuarioHibernateDAO = new UserHibernateDAO();
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
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void actionLogin(ActionEvent event) {
		String login = txLogin.getText();
		String password = txPassword.getText();
		
		User user = usuarioHibernateDAO.findUser(login, password);
		
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

	public LoginViewApp getLoginViewApp() {
		return loginViewApp;
	}

	public void setLoginViewApp(LoginViewApp loginViewApp) {
		this.loginViewApp = loginViewApp;
	}

	public UserHibernateDAO getUsuarioHibernateDAO() {
		return usuarioHibernateDAO;
	}

	public void setUsuarioHibernateDAO(UserHibernateDAO usuarioHibernateDAO) {
		this.usuarioHibernateDAO = usuarioHibernateDAO;
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
