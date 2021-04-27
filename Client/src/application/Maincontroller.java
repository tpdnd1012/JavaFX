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

		this.logid.setText("환영합니다 " + loginid + "님");

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

	// 회원정보
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

	// 채팅하기
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

	// 클라이언트 프로그램 동작 메소드
	/*public void startClient(String IP, int port) {
		
		Thread thread = new Thread() {
			
			public void run() {
				
				try {
					
					socket = new Socket(IP, port);
					
					
				} catch (IOException e) {
					if(!socket.isClosed()) {
						
						stopClient();
						System.out.println("[ 서버 접속 실패 ]");
						Platform.exit();
						
					}
				}
				
			}
			
		};
		thread.start();
	}*/

	// 클라이언트 프로그램 종료 메소드
	/*public void stopClient() {
		
		try {
			
			if(socket != null && !socket.isClosed()) {
				
				socket.close();
				
			}
			
		}catch (Exception e) {
			e.getStackTrace();
		}
		
	}*/
	
	// 서버로부터 메시지를 전달받는 메소드
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
	
	// 서버로부터 메시지를 전송하는 메소드
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
