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

	@FXML // 가입 버튼을 클릭했을때
	public void signupAction(ActionEvent e) throws Exception {

		if (txtid.getText().equals("") || txtpassword.getText().equals("") || txtname.getText().equals("")) {

			// 경고창 띄우기 Alert
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("입력 안된 사항이 있습니다.");
			alert.setHeaderText("가입실패");

			alert.showAndWait();

			return;

		} else {

			Member temp = new Member(txtid.getText(), txtpassword.getText(), txtname.getText());

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

			// 가입 성공후 로그인 창으로
			btnback.getScene().getWindow().hide(); // 해당버튼이 존재하는 윈도우창 숨기기

			Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));

			Stage stage = new Stage();
			Scene scene = new Scene(parent);

			stage.setScene(scene);
			stage.show();

		}
	}
}
