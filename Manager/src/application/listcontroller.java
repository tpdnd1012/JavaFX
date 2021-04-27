package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class listcontroller implements Initializable {

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

	@FXML
	private TableView<wmember> wmemberlist;

	private static String loginid = Logincontroller.getinstance().txtid();

	DAO dao = DAO.getDB();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Member temp = dao.getmember(Logincontroller.getinstance().txtid());

		logid.setText("환영합니다 " + temp.getName() + "님");

		ObservableList<wmember> memberlist = dao.getlistwmember();

		TableColumn tc = wmemberlist.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory("gender"));

		tc = wmemberlist.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory("name"));

		tc = wmemberlist.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory("age"));

		tc = wmemberlist.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory("tel"));

		tc = wmemberlist.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory("job"));

		wmemberlist.setItems(memberlist);

	}

	// 삭제버튼 눌렀을때
	public void deleteAction(ActionEvent e) {

		// 삭제 여부 물어보기
		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.setContentText("정말 삭제 하시겠습니까???");
		alert.setTitle("알림");
		alert.setHeaderText("회원 삭제");

		// 확인 또는 취소 버튼을 눌렀을때
		Optional<ButtonType> bresult = alert.showAndWait();

		if (bresult.get() == ButtonType.OK) {

			// 테이블에서 삭제
			ObservableList<wmember> wmembers;
			ObservableList<wmember> wmembersdelete;

			wmembers = wmemberlist.getItems();
			wmembersdelete = wmemberlist.getSelectionModel().getSelectedItems();

			DAO dao = DAO.getDB();
			dao.wmemberdelete(wmembersdelete.get(0).getTel());

			// 테이블에서 삭제
			wmembersdelete.forEach(wmembers::remove);

		} else { // 취소버튼

			return;

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
