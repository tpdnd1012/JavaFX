package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Maincontroller implements Initializable {

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
	
	Socket socket;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		setuserid(Logincontroller.getinstance().loginid());

	}

	public void setuserid(String loginid) {

		this.logid.setText("ȯ���մϴ� " + loginid + "��");

	}

	// �α׾ƿ�
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

	// ȸ��Ż��
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

	// ȸ������
	public void informationAction() {

		logid.getScene().getWindow().hide();

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("information.fxml"));

			Stage stage = new Stage();
			Scene scene = new Scene(parent);

			stage.setScene(scene);
			stage.setResizable(false);

			stage.show();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// ä���ϱ�
	public void chattingAction() {

		logid.getScene().getWindow().hide();

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("Chatting.fxml"));

			Stage stage = new Stage();
			Scene scene = new Scene(parent);

			stage.setScene(scene);
			stage.setResizable(false);

			stage.show();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// Ŭ���̾�Ʈ ���α׷� ���� �޼ҵ�
	/*public void startClient(String IP, int port) {
		
		Thread thread = new Thread() {
			
			public void run() {
				
				try {
					
					socket = new Socket(IP, port);
					
					
				} catch (IOException e) {
					if(!socket.isClosed()) {
						
						stopClient();
						System.out.println("[ ���� ���� ���� ]");
						Platform.exit();
						
					}
				}
				
			}
			
		};
		thread.start();
	}*/

	// Ŭ���̾�Ʈ ���α׷� ���� �޼ҵ�
	/*public void stopClient() {
		
		try {
			
			if(socket != null && !socket.isClosed()) {
				
				socket.close();
				
			}
			
		}catch (Exception e) {
			e.getStackTrace();
		}
		
	}*/
	
	// �����κ��� �޽����� ���޹޴� �޼ҵ�
	/*public void receive() {
		
		while(true) {
			
			try {
				
				InputStream in = socket.getInputStream();
				byte[] buffer = new byte[1000];
				int length = in.read(buffer);
				
				if(length == -1) throw new IOException();
				
				String message = new String(buffer, 0, length, "UTF-8");
				
				Platform.runLater(() -> {
					txtclient.appendText(message);
				});
				
			}catch (Exception e) {
				stopClient();
				break;
			}
			
		}
			
	} */
	
	// �����κ��� �޽����� �����ϴ� �޼ҵ�
	/*public void send(String message) {
			
		Thread thread = new Thread() {
			
			public void run() {
				
				try {
					
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					out.write(buffer);
					out.flush();
					
				}catch (Exception e) {
					stopClient();
				}
				
			}
			
		};
		thread.start();
	}*/

}
