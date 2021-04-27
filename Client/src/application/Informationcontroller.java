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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Informationcontroller implements Initializable {

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
	private Label txtid;

	@FXML
	private Label txtname;

	@FXML
	private Label txtgender;

	@FXML
	private Label txtage;

	@FXML
	private Label txtpnumber;

	@FXML
	private Label txtjob;

	@FXML
	private Button btnmodify;

	private static String loginid = Logincontroller.getinstance().loginid();

	public static String updateid = null;

	DAO dao = DAO.getDB();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Member temp = dao.getmember(Logincontroller.getinstance().loginid());

		logid.setText("ȯ���մϴ� " + loginid + "��");
		txtid.setText(temp.getId());
		txtname.setText(temp.getName());
		txtgender.setText(temp.getGender());
		txtage.setText(temp.getAge());
		txtpnumber.setText(temp.getPnumber());
		txtjob.setText(temp.getJob());

	}

	// ���� ��ư�� ��������
	public void modifyAction() {

		updateid = txtid.getText();

		// ȸ������ ���� �����
		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.setContentText("ȸ������ ������ �Ͻðڽ��ϱ�???");
		alert.setTitle("�˸�");
		alert.setHeaderText("ȸ������ ����");

		// Ȯ�� �Ǵ� ��� ��ư�� ��������
		Optional<ButtonType> bresult = alert.showAndWait();

		if (bresult.get() == ButtonType.OK) {

			try {

				Parent parent = FXMLLoader.load(getClass().getResource("informationupdate.fxml"));

				Stage stage = (Stage) btnlogout.getScene().getWindow();

				Scene scene = new Scene(parent);

				stage.setScene(scene);

				stage.setResizable(false);

				stage.show();

			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {

			return;

		}

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

	// ȸ��Ż��
	public void deleteAction() {

		logid.getScene().getWindow().hide();

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("delete.fxml"));

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
