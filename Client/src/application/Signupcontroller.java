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

	// 뒤로가기 버튼 클릭했을때
	public void back() throws Exception {

		btnsignup.getScene().getWindow().hide();

		Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setResizable(false);

		stage.show();

	}

	// 가입 버튼을 클릭했을때
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

			// 경고창 띄우기
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("입력 안된 사항이 있습니다.");
			alert.setHeaderText("회원가입 실패");

			alert.showAndWait();

			return;

		} else {

			Member temp = new Member(signid.getText(), signpassword.getText(), signname.getText(), gender,
					signage.getText(), signphonenumber.getText(), signjob.getText());

			DAO dao = DAO.getDB();

			int result = dao.setMember(temp);

			if (result == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("가입 성공 하셨습니다.\n확인 클릭시 로그인창으로 이동합니다.");

				alert.setHeaderText("가입성공");

				alert.showAndWait();

			} else {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("가입 실패 하셨습니다.\n동일한 아이디가 존재합니다.");

				alert.setHeaderText("가입실패");

				alert.showAndWait();

			}
			
			// 가입후 로그인창으로 이동
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
