package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class Signupcontroller implements Initializable {

	@FXML
	private TextField signid;

	@FXML
	private TextField signpassword;

	@FXML
	private TextField signname;

	@FXML
	private Button btnsignup;

	@FXML
	private RadioButton man;

	@FXML
	private ToggleGroup gender;

	@FXML
	private RadioButton woman;

	@FXML
	private TextField signage;

	@FXML
	private TextField signphonenumber;

	@FXML
	private TextField signjob;

	@FXML
	private Button btnback;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	// �ڷΰ��� ��ư Ŭ��������
	public void back() throws Exception {

		btnsignup.getScene().getWindow().hide();

		Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setResizable(false);

		stage.show();

	}

	// ���� ��ư�� Ŭ��������
	public void signupAction() throws Exception {

		String gender = "";

		if (man.isSelected()) {

			gender = "Man";

		}

		if (woman.isSelected()) {

			gender = "Woman";

		}

		if (signid.getText().equals("") || signpassword.getText().equals("") || signname.getText().equals("")
				|| gender.equals("") || signage.getText().equals("") || signphonenumber.getText().equals("")
				|| signjob.getText().equals("")) {

			// ���â ����
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("�Է� �ȵ� ������ �ֽ��ϴ�.");
			alert.setHeaderText("ȸ������ ����");

			alert.showAndWait();

			return;

		} else {

			Member temp = new Member(signid.getText(), signpassword.getText(), signname.getText(), gender,
					signage.getText(), signphonenumber.getText(), signjob.getText());

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
			
			// ������ �α���â���� �̵�
			btnback.getScene().getWindow().hide();
			
			Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
			
			Stage stage = new Stage();
			Scene scene = new Scene(parent);
			
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		}

	}

}
