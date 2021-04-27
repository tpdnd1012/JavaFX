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
	
	// �α��� ������ ������ �ٸ� ��Ʈ�ѷ� �ѱ��
	private static Logincontroller instance; // �ش� Ŭ������ ���� ��ü
	
	public Logincontroller() { // ������
		
		instance = this;
		
	}
	
	public static Logincontroller getinstance() { // ��ü��ȯ �޼ҵ�
		
		return instance;
		
	}
	
	public String txtid() { //�α��� id ��ȯ���ִ� �޼ҵ�
		
		return txtid.getText();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	// ȸ������ ��ư Ŭ��������
	public void signupAction(ActionEvent e) throws Exception {

		btnlogin.getScene().getWindow().hide();

		Parent parent = FXMLLoader.load(getClass().getResource("signup.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setTitle("ȸ������");
		stage.setResizable(false);

		stage.show();

	}

	@FXML // ��й�ȣ ã�� ��ư�� Ŭ�������� // �޼ҵ� ����
	public void findAction(ActionEvent e) throws Exception {

		btnlogin.getScene().getWindow().hide(); // �ش��ư�� �����ϴ� ������â �����

		Parent parent = FXMLLoader.load(getClass().getResource("findpassword.fxml"));

		Stage stage = new Stage();
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.show();

	}
	
	// �α��� ��ư�� Ŭ��������
	public void loginAction(ActionEvent e) {
		
		DAO dao = DAO.getDB();
		
		int result = dao.login(txtid.getText(), txtpassword.getText());
		
		if(result == 1) { // �α��� ����
			
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
			
		}else if(result == 2) { // �α��� ���� [DB���� ����]
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("���̵� Ȥ�� ��й�ȣ�� �ٸ��ϴ�.");
			
			alert.setHeaderText("�α��� ����");
			
			alert.showAndWait();
			
		}else { // DB����
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("DB����\n�����ڿ��� �������ּ���.");
			
			alert.setHeaderText("�α��� ����");
			
			alert.showAndWait();
			
		}
		
	}

}
