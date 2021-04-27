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

	// 뒤로가기 버튼 클릭했을때
	public void back() throws Exception {

		btnfindpw.getScene().getWindow().hide();

		Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setResizable(false);

		stage.show();

	}

	// 비밀번호 찾기 버튼 클릭했을때
	public void findpwAction() {

		DAO dao = DAO.getDB();

		String result = dao.getpw(findid.getText(), findname.getText(), findpnumber.getText());

		if (result.equals("2")) { // DB 정보 없음

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("입력하신 정보와 동일한 정보가 없습니다.");
			alert.setHeaderText("PW 찾기 실패");

			alert.showAndWait();

		} else if (result.equals("0")) { // DB 오류

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("DB 오류\n관리자에게 문의해 주세요.");
			alert.setHeaderText("PW 찾기 실패");

			alert.showAndWait();

		} else { // 패스워드 찾기 성공

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText(findid.getText() + "계정의 비밀번호는 : " + result);
			alert.setHeaderText("PW 찾기 성공");

			alert.showAndWait();

		}

	}

}
