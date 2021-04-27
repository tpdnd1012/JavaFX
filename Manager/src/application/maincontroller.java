package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class maincontroller implements Initializable {

	@FXML
	private Button btninsert;

	@FXML
	private Button btnlogout;

	@FXML
	private Button btnchatting;

	@FXML
	private Button btndelete;

	@FXML
	private Button btnupdate;

	@FXML
	private Button btnlist;

	@FXML
	private Button btninformation;

	@FXML
	private Label logid;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		setuserid(Logincontroller.getinstance().txtid());

	}

	public void setuserid(String txtid) {

		this.logid.setText("환영합니다 " + txtid + "님");

	}

	// 로그아웃
	public void logoutAction(ActionEvent e) {

		logid.getScene().getWindow().hide();

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));

			Stage stage = new Stage();
			Scene scene = new Scene(parent);

			stage.setScene(scene);
			stage.setResizable(false);

			stage.show();

		} catch (Exception e1) {
			// TODO: handle exception
		}

	}

	// 회원정보
	public void informationAction(ActionEvent e) {

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("information.fxml"));

			Stage stage = (Stage) btnlogout.getScene().getWindow();
			Scene scene = new Scene(parent);

			stage.setScene(scene);

			stage.setResizable(false);

			stage.show();

		} catch (Exception e2) {
			// TODO: handle exception
		}

	}

	// 회원추가
	public void insertAction(ActionEvent e) {

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("insert.fxml"));

			Stage stage = (Stage) btnlogout.getScene().getWindow();
			Scene scene = new Scene(parent);

			stage.setScene(scene);

			stage.setResizable(false);

			stage.show();

		} catch (Exception e2) {
			// TODO: handle exception
		}
	}

	// 회원목록
	public void listAction(ActionEvent e) {

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("list.fxml"));

			Stage stage = (Stage) btnlogout.getScene().getWindow();
			Scene scene = new Scene(parent);

			stage.setScene(scene);

			stage.setResizable(false);

			stage.show();

		} catch (Exception e2) {
			// TODO: handle exception
		}

	}

	// 회원수정
	public void updateAction(ActionEvent e) {

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("update.fxml"));

			Stage stage = (Stage) btnlogout.getScene().getWindow();
			Scene scene = new Scene(parent);

			stage.setScene(scene);

			stage.setResizable(false);

			stage.show();

		} catch (Exception e2) {
			// TODO: handle exception
		}

	}
}
