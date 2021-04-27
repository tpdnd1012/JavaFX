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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class listupdatecontroller implements Initializable {

	@FXML
	private Button btninsert;

	@FXML
	private Button btnlogout;

	@FXML
	private Button btnchatting;

	@FXML
	private Button btnlist;

	@FXML
	private Button btninformation;

	@FXML
	private Button btnupdate;

	@FXML
	private Label logid;

	@FXML
	private RadioButton man;

	@FXML
	private ToggleGroup a;

	@FXML
	private RadioButton woman;

	@FXML
	private TextField txtname;

	@FXML
	private TextField txttel;

	@FXML
	private TextField txtjob;

	@FXML
	private Button btnlistupdate;

	@FXML
	private TextField txtage;

	private static String loginid = Logincontroller.getinstance().txtid();

	DAO dao = DAO.getDB();

	private static String pktel = updatecontroller.updatetel;

	// 수정 버튼을 누른후 DB에서 데이터 불러와서 수정칸에 출력
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Member temp = dao.getmember(Logincontroller.getinstance().txtid());

		logid.setText("환영합니다 " + temp.getName() + "님");

		wmember member = dao.updateselect(updatecontroller.updatetel);

		if (member.getGender().equals("man")) {

			man.setSelected(true);

		}

		if (member.getGender().equals("woman")) {

			woman.setSelected(true);

		}

		txtname.setText(member.getName());
		txtage.setText(member.getAge() + "");
		txttel.setText(member.getTel());
		txtjob.setText(member.getJob());

	}

	// 변경버튼을 눌렀을때
	public void listupdate() {

		String gender = "";

		if (man.isSelected()) {

			gender = "man";

		}

		if (woman.isSelected()) {

			gender = "woman";

		}

		DAO dao = DAO.getDB();

		int result = dao.updatecompletion(gender, txtname.getText(), Integer.parseInt(txtage.getText()),
				txttel.getText(), txtjob.getText(), pktel);

		if (result == 1) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("회원 수정 완료");
			alert.setHeaderText("회원 수정");

			alert.showAndWait();

		} else {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("DB 오류\n관리자에게 문의해주세요.");
			alert.setHeaderText("회원 수정");

			alert.showAndWait();

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

}
