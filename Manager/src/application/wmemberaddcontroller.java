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

public class wmemberaddcontroller implements Initializable {

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
	private RadioButton man;

	@FXML
	private ToggleGroup a;

	@FXML
	private RadioButton woman;

	@FXML
	private TextField txtname;

	@FXML
	private TextField txtage;

	@FXML
	private TextField txttel;

	@FXML
	private TextField txtjob;

	@FXML
	private Button btncomplete;

	private static String loginid = Logincontroller.getinstance().txtid();

	DAO dao = DAO.getDB();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Member temp = dao.getmember(Logincontroller.getinstance().txtid());

		logid.setText("ȯ���մϴ� " + temp.getName() + "��");

	}

	// ȸ���߰�
	public void addAction() {

		String gender = "";

		if (woman.isSelected()) {

			gender = "woman";

		}
		if (man.isSelected()) {

			gender = "man";

		}

		wmember temp = new wmember(gender, txtname.getText(), Integer.parseInt(txtage.getText()), txttel.getText(),
				txtjob.getText());

		int result = dao.wmemberadd(temp);

		if (result == 1) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("ȸ�� ��� �Ϸ�");
			alert.setHeaderText("ȸ�� ���");

			alert.showAndWait();

		} else {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("��ϵǾ��ִ� ȸ���� ��ȭ��ȣ�� �ֽ��ϴ�. [ ����ó �ߺ� �Ұ� ]");
			alert.setHeaderText("ȸ�� ���");

			alert.showAndWait();

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
