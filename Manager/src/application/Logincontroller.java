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

public class Logincontroller implements Initializable {

	@FXML
	private TextField txtid;

	@FXML
	private Button btnlogin;

	@FXML
	private Button btnsignup;

	@FXML
	private Button btnfindpw;

	@FXML
	private PasswordField txtpassword;
	
	String logid = "";
	
	public String loginid() {
		
		return logid;
		
	}
	
	// 로그인 성공한 데이터 다른 컨트롤러 넘기기
	private static Logincontroller instance; // 해당 클래스로 임의 객체
	
	public Logincontroller() { // 생성자
		
		instance = this;
		
	}
	
	public static Logincontroller getinstance() { // 객체변환 메소드
		
		return instance;
		
	}
	
	public String txtid() { //로그인 id 반환해주는 메소드
		
		return txtid.getText();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	// 회원가입 버튼 클릭했을때
	public void signupAction(ActionEvent e) throws Exception {

		btnlogin.getScene().getWindow().hide();

		Parent parent = FXMLLoader.load(getClass().getResource("signup.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setTitle("회원가입");
		stage.setResizable(false);

		stage.show();

	}

	@FXML // 비밀번호 찾기 버튼을 클릭했을때 // 메소드 적용
	public void findAction(ActionEvent e) throws Exception {

		btnlogin.getScene().getWindow().hide(); // 해당버튼이 존재하는 윈도우창 숨기기

		Parent parent = FXMLLoader.load(getClass().getResource("findpassword.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.show();

	}
	
	// 로그인 버튼을 클릭했을때
	public void loginAction(ActionEvent e) {
		
		DAO dao = DAO.getDB();
		
		int result = dao.login(txtid.getText(), txtpassword.getText());
		
		if(result == 1) { // 로그인 성공
			
			try {
				
				btnlogin.getScene().getWindow().hide();
				
				Parent parent = FXMLLoader.load(getClass().getResource("wedding.fxml"));
				
				Stage stage = new Stage();
				Scene scene = new Scene(parent);
				
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				
			}catch (Exception e2) {
				// TODO: handle exception
			}
			
		}else if(result == 2) { // 로그인 실패 [DB정보 없음]
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("아이디 혹은 비밀번호가 다릅니다.");
			
			alert.setHeaderText("로그인 실패");
			
			alert.showAndWait();
			
		}else { // DB오류
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("DB오류\n관리자에게 문의해주세요.");
			
			alert.setHeaderText("로그인 실패");
			
			alert.showAndWait();
			
		}
		
	}

}
