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

public class Findidcontroller implements Initializable {

	@FXML
	private TextField findname;

	@FXML
	private TextField findpnumber;

	@FXML
	private Button btnfindid;

	@FXML
	private Button btnback;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	// �ڷΰ��� ��ư Ŭ��������
	public void back() throws Exception {

		btnfindid.getScene().getWindow().hide();

		Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setResizable(false);

		stage.show();

	}

	// ���̵� ã�� ��ư�� Ŭ��������
	public void findidAction() {

		DAO dao = DAO.getDB();

		String result = dao.getid(findname.getText(), findpnumber.getText());

		if (result.equals("2")) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("�Է��Ͻ� ������ ������ ������ �����ϴ�.");
			alert.setHeaderText("ID ã�� ����");

			alert.showAndWait();

		} else if (result.equals("0")) { // DB ����

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("DB ����\n�����ڿ��� ������ �ּ���.");
			alert.setHeaderText("ID ã�� ����");

			alert.showAndWait();

		} else { // �н����� ã�� ����

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText(findname.getText() + "���� ID : " + result);
			alert.setHeaderText("ID ã�� ����");

			alert.showAndWait();

		}

	}

}
