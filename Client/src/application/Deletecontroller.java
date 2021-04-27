package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Deletecontroller implements Initializable {

	@FXML
	private Label logid;

	@FXML
	private Button btnchatting;

	@FXML
	private Button btnlogout;

	@FXML
	private Button btninformation;

	@FXML
	private Button btndelete;

	@FXML
	private TextField deleteid;

	@FXML
	private PasswordField deletepw;

	@FXML
	private Button btncompletion;

	DAO dao = DAO.getDB();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		setuserid(Logincontroller.getinstance().loginid());

	}

	public void setuserid(String loginid) {

		this.logid.setText("ȯ���մϴ� " + loginid + "��");

	}

	// �α׾ƿ�
	public void logoutAction() {

		logid.getScene().getWindow().hide();

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));

			Stage stage = new Stage();
			Scene scene = new Scene(parent);

			stage.setScene(scene);
			stage.setResizable(false);

			stage.show();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// Ż���ư ��������
	public void deleteAction() {

		// ���� ���� �����
		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.setContentText(" ���� Ż�� �Ͻðڽ��ϱ�? ");
		alert.setTitle("�˸�");
		alert.setHeaderText(" ȸ��Ż�� ");

		// Ȯ�� �Ǵ� ��� ��ư�� ��������
		Optional<ButtonType> bresult = alert.showAndWait();

		if (bresult.get() == ButtonType.OK) {

			int result = dao.deletemember(deleteid.getText(), deletepw.getText());

			if (deleteid.getText().equals("") || deletepw.getText().equals("")) {

				// ���â ����
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setContentText("�Է� �ȵ� ������ �ֽ��ϴ�.");
				alert1.setHeaderText("ȸ��Ż�� ����");

				alert1.showAndWait();

				return;

			} else {

				if (result == 1) {
					Alert alert2 = new Alert(AlertType.INFORMATION);

					alert2.setContentText(" ���������� Ż�� �Ǿ����ϴ� ");
					alert2.setTitle("�˸�");
					alert2.setHeaderText(" ȸ��Ż�� ");
					alert2.showAndWait();

					logoutAction();

				} else {
					Alert alert2 = new Alert(AlertType.INFORMATION);

					alert2.setContentText(" �����ͺ��̽� ���� \n �����ڿ��� ���ǹٶ��ϴ� ");
					alert2.setTitle("�˸�");
					alert2.setHeaderText(" ȸ��Ż�� ");
					alert2.showAndWait();
				}
			}

		} else { // ���

			return;

		}

	}

	// ȸ������
	public void informationAction() {

		logid.getScene().getWindow().hide();

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("information.fxml"));

			Stage stage = new Stage();
			Scene scene = new Scene(parent);

			stage.setScene(scene);
			stage.setResizable(false);

			stage.show();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
