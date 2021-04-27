package application;

import java.net.URL;
import java.util.ResourceBundle;

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

public class Findpwcontroller implements Initializable {

	@FXML
	private TextField findid;

	@FXML
	private TextField findpnumber;

	@FXML
	private Button btnfindpw;

	@FXML
	private Button btnback;

	@FXML
	private TextField findname;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	// �ڷΰ��� ��ư Ŭ��������
	public void back() throws Exception {

		btnfindpw.getScene().getWindow().hide();

		Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setResizable(false);

		stage.show();

	}

	// ��й�ȣ ã�� ��ư Ŭ��������
	public void findpwAction() {

		DAO dao = DAO.getDB();

		String result = dao.getpw(findid.getText(), findname.getText(), findpnumber.getText());

		if (result.equals("2")) { // DB ���� ����

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("�Է��Ͻ� ������ ������ ������ �����ϴ�.");
			alert.setHeaderText("PW ã�� ����");

			alert.showAndWait();

		} else if (result.equals("0")) { // DB ����

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("DB ����\n�����ڿ��� ������ �ּ���.");
			alert.setHeaderText("PW ã�� ����");

			alert.showAndWait();

		} else { // �н����� ã�� ����

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText(findid.getText() + "������ ��й�ȣ�� : " + result);
			alert.setHeaderText("PW ã�� ����");

			alert.showAndWait();

		}

	}

}
