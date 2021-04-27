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

		logid.setText("ȯ���մϴ� " + temp.getName() + "��");

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

	// ������ư ��������
	public void deleteAction(ActionEvent e) {

		// ���� ���� �����
		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.setContentText("���� ���� �Ͻðڽ��ϱ�???");
		alert.setTitle("�˸�");
		alert.setHeaderText("ȸ�� ����");

		// Ȯ�� �Ǵ� ��� ��ư�� ��������
		Optional<ButtonType> bresult = alert.showAndWait();

		if (bresult.get() == ButtonType.OK) {

			// ���̺��� ����
			ObservableList<wmember> wmembers;
			ObservableList<wmember> wmembersdelete;

			wmembers = wmemberlist.getItems();
			wmembersdelete = wmemberlist.getSelectionModel().getSelectedItems();

			DAO dao = DAO.getDB();
			dao.wmemberdelete(wmembersdelete.get(0).getTel());

			// ���̺��� ����
			wmembersdelete.forEach(wmembers::remove);

		} else { // ��ҹ�ư

			return;

		}

	}

	// ȸ���߰�
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

	// �α׾ƿ�
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

	// ȸ������
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

	// ȸ�����
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

	// ȸ������
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
