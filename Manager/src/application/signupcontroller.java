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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class signupcontroller implements Initializable {

	@FXML
	private TextField txtid;

	@FXML
	private Button btnsignup;

	@FXML
	private Button btnback;

	@FXML
	private PasswordField txtpassword;

	@FXML
	private TextField txtname;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void back(ActionEvent e) throws Exception {

		btnsignup.getScene().getWindow().hide();

		Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setResizable(false);

		stage.show();

	}

	@FXML // ���� ��ư�� Ŭ��������
	public void signupAction(ActionEvent e) throws Exception {

		if (txtid.getText().equals("") || txtpassword.getText().equals("") || txtname.getText().equals("")) {

			// ���â ���� Alert
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("�Է� �ȵ� ������ �ֽ��ϴ�.");
			alert.setHeaderText("���Խ���");

			alert.showAndWait();

			return;

		} else {

			Member temp = new Member(txtid.getText(), txtpassword.getText(), txtname.getText());

			DAO dao = DAO.getDB();

			int result = dao.setMember(temp);

			

			
			if (result == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("���� ���� �ϼ̽��ϴ�.\nȮ�� Ŭ���� �α���â���� �̵��մϴ�.");

				alert.setHeaderText("���Լ���");

				alert.showAndWait();

			} else {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("���� ���� �ϼ̽��ϴ�.\n������ ���̵� �����մϴ�.");

				alert.setHeaderText("���Խ���");

				alert.showAndWait();

			}

			// ���� ������ �α��� â����
			btnback.getScene().getWindow().hide(); // �ش��ư�� �����ϴ� ������â �����

			Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));

			Stage stage = new Stage();
			Scene scene = new Scene(parent);

			stage.setScene(scene);
			stage.show();

		}
	}
}
