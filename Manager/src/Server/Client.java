package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	
	// 필드
	Socket socket;
	
	// 생성자
	public Client(Socket socket) {
		
		this.socket = socket;
		recive();
		
	}

	private void recive() {
		
		// 한개의 쓰레드 만들기
		Runnable thread = new Runnable() {
			
			@Override
			public void run() {
				
				try {
					
					while(true) {
						
						InputStream in = socket.getInputStream();
						byte[] buffer = new byte[1000];
								// 1000개의 바이트를 입력받기
						
						int length = in.read(buffer);
								
						// 메시지 없으면 반복 끝내기
						if(length == -1) {
							throw new IOException();
						}
						
						System.out.println("[ 메시지 수신 성공 ]"
								+ socket.getRemoteSocketAddress() // 전송해준 소켓 주소
								+ " : " + Thread.currentThread().getName()); // 전송해준 스레드 이름
						
						String message = new String(buffer, 0, length, "UTF-8");
						
						// 받은 메시지를 모든 클라이언트로부터 전송하기
						for(Client client : Main.clients) {
									// 새로운 임시객체 : 리스트컬렉션 // 리스트 객체들을 한개씩 꺼내면서 리스트내 객체수만큼 반복
							client.send(message);
							
						}
						
					}
					
				}catch (Exception e) {
					try {
						
						System.out.println("[ 메시지 수신 오류 ]"
								+ socket.getRemoteSocketAddress()
								+ " : " + Thread.currentThread().getName());
						
						Main.clients.remove(Client.this);
						socket.close();
						
					}catch (Exception e2) {
						e2.getStackTrace();
					}
				}
				
			}
		};
		Main.threadpool.submit(thread); // 스레드풀에 현재 만들어진 쓰레드 추가
	}
	
	// 연결된 모든 클라이언트에게 전송하는 메시지를 전송 메소드
	public void send(String message) {
		
		Runnable thread = new Runnable() {
			
			@Override
			public void run() {
				
				try {
					
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					System.out.println( "송신 성공");
					
					out.write(buffer);
					out.flush();
					
				}catch (Exception e) {
					
					try {
						
						System.out.println("[ 메시지 송신 오류 ]"
								+ socket.getRemoteSocketAddress()
								+ " : " + Thread.currentThread().getName());
						
						Main.clients.remove(Client.this);
						socket.close();
						
					}catch (Exception e2) {
						e2.getStackTrace();
					}
					
				}
				
			}
		};
		Main.threadpool.submit(thread);
	}
	

}
