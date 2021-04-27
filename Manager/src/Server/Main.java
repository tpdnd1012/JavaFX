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
	
	// 서버를 구동시켜서 클라이언트의 연결을 기다리는 메소드
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
		// 클라이언트가 접속할 때까지 계속 기다리는 스레드
		Runnable thread = new Runnable() {
			
			@Override
			public void run() {
				
				while(true) {
					
					try {
					
						Socket socket = serverSocket.accept();
						clients.add(new Client(socket));
					
						System.out.println("[ 클라이언트 접속 ]"
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
		threadpool = Executors.newCachedThreadPool(); // 스레드풀 초기화
		threadpool.submit(thread); // 접속을 기다리는 스레드풀을 추가
	}
	
	// 서버의 작동을 중지시키는 메소드
	public void stopServer() {
		
		try {
			
			// 현재 작동 중인 모든 소켓 닫기
			Iterator<Client> iterator = clients.iterator();
			
			while(iterator.hasNext()) { // 하나하나의 클라이언트에 접근
				
				Client client = iterator.next(); // 특정한 클라이언트에 접근을해서
				client.socket.close(); // 해당 클라이언트 닫기
				iterator.remove(); // iterator 에서도 연결이 끊긴 클라이언트 제거
				
			}
			
			// 서버 소켓 객체 닫기
			if(serverSocket != null && !serverSocket.isClosed()) {
				// 서버소켓이 null값이 아니 현재 소켓이 열려있는 상태라면
				
				serverSocket.close(); // 닫기
				
			}
			
			// 스레드 풀 종료하기
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
			
			// 컨트롤러 없이 fx:id 불러오기
			Button btnstart = (Button)parent.lookup("#btnstart");
			TextArea txtserver = (TextArea)parent.lookup("#txtserver");
			
			// 액션 메소드를 정의하고 람다식으로 바로 실행
			btnstart.setOnAction(event -> {
				
				if(btnstart.getText().equals("서버실행")) { // 서버 시작 버튼을 눌렀을때
					
					startServer("127.0.0.1", 5001); // 고정 ip, 포트 // 서버 시작 메소드 호출
					
					System.out.println("서버 실행");
					
					Platform.runLater(() -> { // runLater( (인수생략) -> { 코드 }
						
						String message = "[ 서버시작 ]\n";
						txtserver.appendText(message); // 해당 컨트롤러에 텍스트 추가
						btnstart.setText("서버종료");
						
					});
					
				}else { // 서버종료 버튼을 눌렀을때
					
					stopServer(); // 서버종료 메소드 호출
					
					System.out.println("서버 종료");
					
					Platform.runLater(() -> {
						
						String message = "[ 서버종료 ]\n";
						txtserver.appendText(message);
						btnstart.setText("서버실행");
						
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
	
	// 프로그램의 진입점
	public static void main(String[] args) {
		launch(args);
	}

}
