package application;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
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

public class Logincontroller implements Initializable {

	@FXML
	private TextField loginid;

	@FXML
	private TextField loginpassword;

	@FXML
	private Button btnlogin;

	@FXML
	private Button btnfindpassword;

	@FXML
	private Button btnsignup;

	@FXML
	private Button btnfindid;

	String logid = "";

	public String logid() {

		return logid;

	}

	// 로그인 성공한 데이터를 다른 컨트롤러 넘기기
	private static Logincontroller instance; // 해당 클래스로 임의 객체

	public Logincontroller() { // 생성자

		instance = this;

	}

	public static Logincontroller getinstance() { // 객체 변환 메소드

		return instance;

	}

	public String loginid() { // 로그인 id 반환해주는 메소드

		return loginid.getText();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	// 회원가입 버튼 클릭
	public void signupAction() throws Exception {

		btnlogin.getScene().getWindow().hide();

		Parent parent = FXMLLoader.load(getClass().getResource("Signup.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setResizable(false);

		stage.show();

	}

	// 아이디 찾기 버튼 클릭
	public void findidAction() throws Exception {

		btnlogin.getScene().getWindow().hide();

		Parent parent = FXMLLoader.load(getClass().getResource("Findid.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setResizable(false);

		stage.show();

	}

	// 비밀번호 찾기 버튼 클릭
	public void findpwAction() throws Exception {

		btnlogin.getScene().getWindow().hide();

		Parent parent = FXMLLoader.load(getClass().getResource("Findpw.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setResizable(false);

		stage.show();

	}

	// 로그인 버튼을 클릭했을때
	public void loginAction() {

		DAO dao = DAO.getDB();

		int result = dao.login(loginid.getText(), loginpassword.getText());

		if (result == 1) {

			try {

				btnlogin.getScene().getWindow().hide();

				Parent parent = FXMLLoader.load(getClass().getResource("Main.fxml"));

				Stage stage = new Stage();
				Scene scene = new Scene(parent);

				stage.setScene(scene);
				stage.setResizable(false);

				stage.show();

			} catch (Exception e) {
				// TODO: handle exception
			}

		} else if (result == 2) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("아이디 혹은 비밀번호가 다릅니다.");

			alert.setHeaderText("로그인 실패");

			alert.showAndWait();

		} else {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("DB오류\n관리자에게 문의해주세요.");

			alert.setHeaderText("로그인 실패");

			alert.showAndWait();

		}

	}

}
