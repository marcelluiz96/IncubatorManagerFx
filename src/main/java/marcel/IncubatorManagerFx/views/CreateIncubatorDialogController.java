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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import marcel.IncubatorManagerFx.dao.IncubatorHibernateDAO;
import marcel.IncubatorManagerFx.entity.Incubator;
import marcel.IncubatorManagerFx.entity.User;

public class CreateIncubatorDialogController implements Initializable {

	private IncubatorHibernateDAO incubatorHibernateDAO;
	private Stage dialogStage;
	private boolean okClicked = false;


	@FXML private TextField txName;
	@FXML private TextField txUniqueCode;
	@FXML private TextField txLocation;
	@FXML private TextArea txDescription;

	@FXML private Button btCancel;
	@FXML private Button btSave;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		incubatorHibernateDAO = new IncubatorHibernateDAO();
	}

	@FXML
	public void saveIncubator(ActionEvent event) {
		try {
			Incubator incubator = new Incubator();
			incubator.setName(txName.getText());
			incubator.setUniqueCode(txUniqueCode.getText());
			incubator.setLocation(txLocation.getText());
			incubator.setDescription(txDescription.getText());
			incubator.setHospital(getLoggedOnUser().getHospital());
			incubatorHibernateDAO.persist(incubator);

			Alert alert = new Alert(AlertType.INFORMATION, "Success", ButtonType.OK);
			alert.setTitle("Info");
			alert.setHeaderText("Incubator registered successfully!");
			alert.setContentText("Click OK to close this window");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK){
				Stage stage = (Stage) btSave.getScene().getWindow();
				stage.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR, "Error while saving the Incubator. Please try again"
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

	public IncubatorHibernateDAO getIncubatorHibernateDAO() {
		return incubatorHibernateDAO;
	}

	public void setIncubatorHibernateDAO(IncubatorHibernateDAO incubatorHibernateDAO) {
		this.incubatorHibernateDAO = incubatorHibernateDAO;
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

	public TextField getTxName() {
		return txName;
	}

	public void setTxName(TextField txName) {
		this.txName = txName;
	}

	public TextField getTxUniqueCode() {
		return txUniqueCode;
	}

	public void setTxUniqueCode(TextField txUniqueCode) {
		this.txUniqueCode = txUniqueCode;
	}

	public TextField getTxLocation() {
		return txLocation;
	}

	public void setTxLocation(TextField txLocation) {
		this.txLocation = txLocation;
	}


	public TextArea getTxDescription() {
		return txDescription;
	}

	public void setTxDescription(TextArea txDescription) {
		this.txDescription = txDescription;
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
