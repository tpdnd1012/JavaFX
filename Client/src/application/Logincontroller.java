package application;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Logincontroller implements Initializable {

	@FXML
	private TextField loginid;

	@FXML
	private TextField loginpassword;

	@FXML
	private Button btnlogin;

	@FXML
	private Button btnfindpassword;

	@FXML
	private Button btnsignup;

	@FXML
	private Button btnfindid;

	String logid = "";

	public String logid() {

		return logid;

	}

	// �α��� ������ �����͸� �ٸ� ��Ʈ�ѷ� �ѱ��
	private static Logincontroller instance; // �ش� Ŭ������ ���� ��ü

	public Logincontroller() { // ������

		instance = this;

	}

	public static Logincontroller getinstance() { // ��ü ��ȯ �޼ҵ�

		return instance;

	}

	public String loginid() { // �α��� id ��ȯ���ִ� �޼ҵ�

		return loginid.getText();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	// ȸ������ ��ư Ŭ��
	public void signupAction() throws Exception {

		btnlogin.getScene().getWindow().hide();

		Parent parent = FXMLLoader.load(getClass().getResource("Signup.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setResizable(false);

		stage.show();

	}

	// ���̵� ã�� ��ư Ŭ��
	public void findidAction() throws Exception {

		btnlogin.getScene().getWindow().hide();

		Parent parent = FXMLLoader.load(getClass().getResource("Findid.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setResizable(false);

		stage.show();

	}

	// ��й�ȣ ã�� ��ư Ŭ��
	public void findpwAction() throws Exception {

		btnlogin.getScene().getWindow().hide();

		Parent parent = FXMLLoader.load(getClass().getResource("Findpw.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setResizable(false);

		stage.show();

	}

	// �α��� ��ư�� Ŭ��������
	public void loginAction() {

		DAO dao = DAO.getDB();

		int result = dao.login(loginid.getText(), loginpassword.getText());

		if (result == 1) {

			try {

				btnlogin.getScene().getWindow().hide();

				Parent parent = FXMLLoader.load(getClass().getResource("Main.fxml"));

				Stage stage = new Stage();
				Scene scene = new Scene(parent);

				stage.setScene(scene);
				stage.setResizable(false);

				stage.show();

			} catch (Exception e) {
				// TODO: handle exception
			}

		} else if (result == 2) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("���̵� Ȥ�� ��й�ȣ�� �ٸ��ϴ�.");

			alert.setHeaderText("�α��� ����");

			alert.showAndWait();

		} else {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("DB����\n�����ڿ��� �������ּ���.");

			alert.setHeaderText("�α��� ����");

			alert.showAndWait();

		}

	}

}
