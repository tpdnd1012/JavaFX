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

	@FXML // 뒤로가기 버튼을 클릭했을때 // 메소드 적용
	public void back(ActionEvent e) throws Exception {

		btnback.getScene().getWindow().hide(); // 해당버튼이 존재하는 윈도우창 숨기기

		Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.show();

	}

	// 비밀번호 찾기 버튼을 클릭했을때
	public void findAction(ActionEvent e) {

		DAO dao = DAO.getDB();

		String result = dao.getpassword(findid.getText(), findname.getText());

		if (result.equals("2")) { // DB 정보 없음

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("입력하신 정보와 동일한 정보가 없습니다.");
			alert.setHeaderText("패스워드 찾기 실패");

			alert.showAndWait();

		} else if (result.equals("0")) { // DB 오류

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("DB 오류\n관리자에게 문의해 주세요.");
			alert.setHeaderText("패스워드 찾기 실패");

			alert.showAndWait();

		} else { // 패스워드 찾기 성공

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText(findid.getText() + "계정의 비밀번호는 : " + result);
			alert.setHeaderText("패스워드 찾기 성공");

			alert.showAndWait();

		}

	}
}
