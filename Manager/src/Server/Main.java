package Server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static ExecutorService threadpool;
	public static Vector<Client> clients = new Vector<Client>();
	
	ServerSocket serverSocket;
	
	// ������ �������Ѽ� Ŭ���̾�Ʈ�� ������ ��ٸ��� �޼ҵ�
	public void startServer(String IP, int port) {
		
		try {
			
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(IP, port));
			
		}catch (Exception e) {
			e.getStackTrace();
			
			if(!serverSocket.isClosed()) {
				
				stopServer();
				
			}
			return;
		}
		// Ŭ���̾�Ʈ�� ������ ������ ��� ��ٸ��� ������
		Runnable thread = new Runnable() {
			
			@Override
			public void run() {
				
				while(true) {
					
					try {
					
						Socket socket = serverSocket.accept();
						clients.add(new Client(socket));
					
						System.out.println("[ Ŭ���̾�Ʈ ���� ]"
								+ socket.getRemoteSocketAddress()
								+ " : " + Thread.currentThread().getName());
					
					}catch (Exception e) {
						if(!serverSocket.isClosed()) {
							
							stopServer();
							
						}
						break;
					}
				}
				
			}
		};
		threadpool = Executors.newCachedThreadPool(); // ������Ǯ �ʱ�ȭ
		threadpool.submit(thread); // ������ ��ٸ��� ������Ǯ�� �߰�
	}
	
	// ������ �۵��� ������Ű�� �޼ҵ�
	public void stopServer() {
		
		try {
			
			// ���� �۵� ���� ��� ���� �ݱ�
			Iterator<Client> iterator = clients.iterator();
			
			while(iterator.hasNext()) { // �ϳ��ϳ��� Ŭ���̾�Ʈ�� ����
				
				Client client = iterator.next(); // Ư���� Ŭ���̾�Ʈ�� �������ؼ�
				client.socket.close(); // �ش� Ŭ���̾�Ʈ �ݱ�
				iterator.remove(); // iterator ������ ������ ���� Ŭ���̾�Ʈ ����
				
			}
			
			// ���� ���� ��ü �ݱ�
			if(serverSocket != null && !serverSocket.isClosed()) {
				// ���������� null���� �ƴ� ���� ������ �����ִ� ���¶��
				
				serverSocket.close(); // �ݱ�
				
			}
			
			// ������ Ǯ �����ϱ�
			if(threadpool != null && !threadpool.isShutdown()) {
				
				threadpool.shutdown();
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		try {
			
			Parent parent = FXMLLoader.load(getClass().getResource("server.fxml"));
			
			// ��Ʈ�ѷ� ���� fx:id �ҷ�����
			Button btnstart = (Button)parent.lookup("#btnstart");
			TextArea txtserver = (TextArea)parent.lookup("#txtserver");
			
			// �׼� �޼ҵ带 �����ϰ� ���ٽ����� �ٷ� ����
			btnstart.setOnAction(event -> {
				
				if(btnstart.getText().equals("��������")) { // ���� ���� ��ư�� ��������
					
					startServer("127.0.0.1", 5001); // ���� ip, ��Ʈ // ���� ���� �޼ҵ� ȣ��
					
					System.out.println("���� ����");
					
					Platform.runLater(() -> { // runLater( (�μ�����) -> { �ڵ� }
						
						String message = "[ �������� ]\n";
						txtserver.appendText(message); // �ش� ��Ʈ�ѷ��� �ؽ�Ʈ �߰�
						btnstart.setText("��������");
						
					});
					
				}else { // �������� ��ư�� ��������
					
					stopServer(); // �������� �޼ҵ� ȣ��
					
					System.out.println("���� ����");
					
					Platform.runLater(() -> {
						
						String message = "[ �������� ]\n";
						txtserver.appendText(message);
						btnstart.setText("��������");
						
					});
					
				}
				
			});
			
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setResizable(false);
			
			stage.show();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	// ���α׷��� ������
	public static void main(String[] args) {
		launch(args);
	}

}
