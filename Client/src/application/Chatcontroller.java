package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Chatcontroller implements Initializable {

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
	private TextArea txtclient;

	@FXML
	private TextField txtinput;

	@FXML
	private Button btnsend;

	private static String loginid = Logincontroller.getinstance().loginid();

	Socket socket;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		logid.setText("ȯ���մϴ� " + loginid + "��");
		
		startClient("127.0.0.1", 5001);

		btnsend.setOnAction(e -> {
			
			send(  loginid + " : " + txtinput.getText() + "\n"    );
			
			Platform.runLater(() -> {

				txtinput.setText("");
				txtinput.requestFocus();

			});
		});

	}

	// �����κ��� �޽����� ���޹޴� �޼ҵ�
	public void receive() {

		while (true) {

			try {

				InputStream in = socket.getInputStream();
				byte[] buffer = new byte[1000];
				int length = in.read(buffer);

				if (length == -1)
					throw new IOException();

				String message = new String(buffer, 0, length, "UTF-8");

				Platform.runLater(() -> {
					
					txtclient.appendText(message);
					
				});

			} catch (Exception e) {
				break;
			}

		}

	}

	// �����κ��� �޽����� �����ϴ� �޼ҵ�
	public void send(String message) {

		Thread thread = new Thread() {

			public void run() {

				try {

					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					out.write(buffer);
					out.flush();

				} catch (Exception e) {
					e.getStackTrace();
				}

			}

		};
		thread.start();
	}

	// Ŭ���̾�Ʈ ���α׷� ���� �޼ҵ�
	public void startClient(String IP, int port) {

		Thread thread = new Thread() {

			public void run() {

				try {

					socket = new Socket(IP, port);
					receive();

				} catch (IOException e) {
					if (!socket.isClosed()) {

						stopClient();
						System.out.println("[ ���� ���� ���� ]");
						Platform.exit();

					}
				}

			}

		};
		thread.start();
	}

	// Ŭ���̾�Ʈ ���α׷� ���� �޼ҵ�
	public void stopClient() {

		try {

			if (socket != null && !socket.isClosed()) {

				socket.close();

			}

		} catch (Exception e) {
			e.getStackTrace();
		}

	}

}
