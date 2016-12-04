package marcel.IncubatorManagerFx.views;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import marcel.IncubatorManagerFx.dao.IncubatorHibernateDAO;
import marcel.IncubatorManagerFx.dao.UserHibernateDAO;
import marcel.IncubatorManagerFx.entity.Incubator;
import marcel.IncubatorManagerFx.entity.User;

public class CreateAccountDialogController implements Initializable {

	private UserHibernateDAO userHibernateDAO;
	private Stage dialogStage;
	private boolean okClicked = false;


	@FXML private TextField txLogin;
	@FXML private PasswordField txPassword;
	@FXML private PasswordField txConfirmPassword;

	@FXML private Button btCancel;
	@FXML private Button btSave;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userHibernateDAO = new UserHibernateDAO();
	}

	@FXML
	public void saveAccount(ActionEvent event) {
		try {
			if (!txPassword.getText().equals(txConfirmPassword.getText())) {
				throw new Exception();
			} else {
				User user = new User();
				user.setLogin(txLogin.getText());
				user.setPassword(txPassword.getText());
				user.setHospital(getLoggedOnUser().getHospital());

				userHibernateDAO.persist(user);

				Alert alert = new Alert(AlertType.INFORMATION, "Success", ButtonType.OK);
				alert.setTitle("Info");
				alert.setHeaderText("Account registered successfully!");
				alert.setContentText("Click OK to close this window");
				Optional<ButtonType> result = alert.showAndWait();

				if (result.get() == ButtonType.OK){
					Stage stage = (Stage) btSave.getScene().getWindow();
					stage.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR, "Error while saving the User. Please try again"
					+ "later", ButtonType.OK);
			alert.show();

		}


	}

	@FXML
	public void cancel(ActionEvent event) {
		Stage stage = (Stage) btSave.getScene().getWindow();
		stage.close();
	}

	public User getLoggedOnUser() {
		return LoginController.getUserLoggedOn();
	}

	public UserHibernateDAO getUserHibernateDAO() {
		return userHibernateDAO;
	}

	public void setUserHibernateDAO(UserHibernateDAO userHibernateDAO) {
		this.userHibernateDAO = userHibernateDAO;
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
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

	public PasswordField getTxConfirmPassword() {
		return txConfirmPassword;
	}

	public void setTxConfirmPassword(PasswordField txConfirmPassword) {
		this.txConfirmPassword = txConfirmPassword;
	}

	public Button getBtCancel() {
		return btCancel;
	}

	public void setBtCancel(Button btCancel) {
		this.btCancel = btCancel;
	}

	public Button getBtSave() {
		return btSave;
	}

	public void setBtSave(Button btSave) {
		this.btSave = btSave;
	}

	



}
