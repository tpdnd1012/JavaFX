package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
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

public class findpasswordcontroller implements Initializable {

	@FXML
	private TextField findid;

	@FXML
	private Button btnfind;

	@FXML
	private Button btnback;

	@FXML
	private TextField findname;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML // �ڷΰ��� ��ư�� Ŭ�������� // �޼ҵ� ����
	public void back(ActionEvent e) throws Exception {

		btnback.getScene().getWindow().hide(); // �ش��ư�� �����ϴ� ������â �����

		Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.show();

	}

	// ��й�ȣ ã�� ��ư�� Ŭ��������
	public void findAction(ActionEvent e) {

		DAO dao = DAO.getDB();

		String result = dao.getpassword(findid.getText(), findname.getText());

		if (result.equals("2")) { // DB ���� ����

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("�Է��Ͻ� ������ ������ ������ �����ϴ�.");
			alert.setHeaderText("�н����� ã�� ����");

			alert.showAndWait();

		} else if (result.equals("0")) { // DB ����

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("DB ����\n�����ڿ��� ������ �ּ���.");
			alert.setHeaderText("�н����� ã�� ����");

			alert.showAndWait();

		} else { // �н����� ã�� ����

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText(findid.getText() + "������ ��й�ȣ�� : " + result);
			alert.setHeaderText("�н����� ã�� ����");

			alert.showAndWait();

		}

	}
}
