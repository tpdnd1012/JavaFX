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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class informationupdatecontroller implements Initializable {

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
	private Button btnmodify;

	@FXML
	private TextField txtid;

	@FXML
	private TextField txtpw;

	@FXML
	private TextField txtname;

	@FXML
	private TextField txtage;

	@FXML
	private TextField txtpnumber;

	@FXML
	private TextField txtjob;

	@FXML
	private RadioButton man;

	@FXML
	private ToggleGroup gender;

	@FXML
	private RadioButton woman;

	private static String loginid = Logincontroller.getinstance().loginid();

	private static String pkid = Informationcontroller.updateid;

	DAO dao = DAO.getDB();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Member temp = dao.getmember(Logincontroller.getinstance().loginid());

		logid.setText("환영합니다 " + loginid + "님");

		Member member = dao.modifyselect(pkid);

		if (member.getGender().equals("Man")) {

			man.setSelected(true);

		}

		if (member.getGender().equals("Woman")) {

			woman.setSelected(true);

		}

		txtid.setText(member.getId());
		txtpw.setText(member.getPw());
		txtname.setText(member.getName());
		txtage.setText(member.getAge());
		txtpnumber.setText(member.getPnumber());
		txtjob.setText(member.getJob());

	}

	// 변경버튼 눌렀을때
	public void modifycomplete() {

		String gender = "";

		if (man.isSelected()) {

			gender = "Man";

		}

		if (woman.isSelected()) {

			gender = "Woman";

		}

		int result = dao.modifycomplete(txtid.getText(), txtpw.getText(), txtname.getText(), gender, txtage.getText(),
				txtpnumber.getText(), txtjob.getText(), pkid);

		if (result == 1) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("회원 수정 완료");
			alert.setHeaderText("회원 수정");

			alert.showAndWait();
			
			logoutAction();

		} else {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("DB 오류\n관리자에게 문의해주세요.");
			alert.setHeaderText("회원 수정");

			alert.showAndWait();

		}

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

	// 회원탈퇴
	public void deleteAction() {

		logid.getScene().getWindow().hide();

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("delete.fxml"));

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
