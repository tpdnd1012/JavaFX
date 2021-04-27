package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Deletecontroller implements Initializable {

	@FXML
	private Label logid;

	@FXML
	private Button btnchatting;

	@FXML
	private Button btnlogout;

	@FXML
	private Button btninformation;

	@FXML
	private Button btndelete;

	@FXML
	private TextField deleteid;

	@FXML
	private PasswordField deletepw;

	@FXML
	private Button btncompletion;

	DAO dao = DAO.getDB();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		setuserid(Logincontroller.getinstance().loginid());

	}

	public void setuserid(String loginid) {

		this.logid.setText("환영합니다 " + loginid + "님");

	}

	// 로그아웃
	public void logoutAction() {

		logid.getScene().getWindow().hide();

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));

			Stage stage = new Stage();
			Scene scene = new Scene(parent);

			stage.setScene(scene);
			stage.setResizable(false);

			stage.show();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// 탈퇴버튼 눌렀을때
	public void deleteAction() {

		// 삭제 여부 물어보기
		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.setContentText(" 정말 탈퇴 하시겠습니까? ");
		alert.setTitle("알림");
		alert.setHeaderText(" 회원탈퇴 ");

		// 확인 또는 취소 버튼을 눌렀을때
		Optional<ButtonType> bresult = alert.showAndWait();

		if (bresult.get() == ButtonType.OK) {

			int result = dao.deletemember(deleteid.getText(), deletepw.getText());

			if (deleteid.getText().equals("") || deletepw.getText().equals("")) {

				// 경고창 띄우기
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setContentText("입력 안된 사항이 있습니다.");
				alert1.setHeaderText("회원탈퇴 실패");

				alert1.showAndWait();

				return;

			} else {

				if (result == 1) {
					Alert alert2 = new Alert(AlertType.INFORMATION);

					alert2.setContentText(" 정상적으로 탈퇴 되었습니다 ");
					alert2.setTitle("알림");
					alert2.setHeaderText(" 회원탈퇴 ");
					alert2.showAndWait();

					logoutAction();

				} else {
					Alert alert2 = new Alert(AlertType.INFORMATION);

					alert2.setContentText(" 데이터베이스 오류 \n 관리자에게 문의바랍니다 ");
					alert2.setTitle("알림");
					alert2.setHeaderText(" 회원탈퇴 ");
					alert2.showAndWait();
				}
			}

		} else { // 취소

			return;

		}

	}

	// 회원정보
	public void informationAction() {

		logid.getScene().getWindow().hide();

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("information.fxml"));

			Stage stage = new Stage();
			Scene scene = new Scene(parent);

			stage.setScene(scene);
			stage.setResizable(false);

			stage.show();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
