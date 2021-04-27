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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Informationcontroller implements Initializable {

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
	private Label txtid;

	@FXML
	private Label txtname;

	@FXML
	private Label txtgender;

	@FXML
	private Label txtage;

	@FXML
	private Label txtpnumber;

	@FXML
	private Label txtjob;

	@FXML
	private Button btnmodify;

	private static String loginid = Logincontroller.getinstance().loginid();

	public static String updateid = null;

	DAO dao = DAO.getDB();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Member temp = dao.getmember(Logincontroller.getinstance().loginid());

		logid.setText("환영합니다 " + loginid + "님");
		txtid.setText(temp.getId());
		txtname.setText(temp.getName());
		txtgender.setText(temp.getGender());
		txtage.setText(temp.getAge());
		txtpnumber.setText(temp.getPnumber());
		txtjob.setText(temp.getJob());

	}

	// 수정 버튼을 눌렀을때
	public void modifyAction() {

		updateid = txtid.getText();

		// 회원수정 여부 물어보기
		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.setContentText("회원정보 수정을 하시겠습니까???");
		alert.setTitle("알림");
		alert.setHeaderText("회원정보 수정");

		// 확인 또는 취소 버튼을 눌렀을때
		Optional<ButtonType> bresult = alert.showAndWait();

		if (bresult.get() == ButtonType.OK) {

			try {

				Parent parent = FXMLLoader.load(getClass().getResource("informationupdate.fxml"));

				Stage stage = (Stage) btnlogout.getScene().getWindow();

				Scene scene = new Scene(parent);

				stage.setScene(scene);

				stage.setResizable(false);

				stage.show();

			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {

			return;

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
