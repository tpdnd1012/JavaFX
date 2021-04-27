package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	
	// �ʵ�
	Socket socket;
	
	// ������
	public Client(Socket socket) {
		
		this.socket = socket;
		recive();
		
	}

	private void recive() {
		
		// �Ѱ��� ������ �����
		Runnable thread = new Runnable() {
			
			@Override
			public void run() {
				
				try {
					
					while(true) {
						
						InputStream in = socket.getInputStream();
						byte[] buffer = new byte[1000];
								// 1000���� ����Ʈ�� �Է¹ޱ�
						
						int length = in.read(buffer);
								
						// �޽��� ������ �ݺ� ������
						if(length == -1) {
							throw new IOException();
						}
						
						System.out.println("[ �޽��� ���� ���� ]"
								+ socket.getRemoteSocketAddress() // �������� ���� �ּ�
								+ " : " + Thread.currentThread().getName()); // �������� ������ �̸�
						
						String message = new String(buffer, 0, length, "UTF-8");
						
						// ���� �޽����� ��� Ŭ���̾�Ʈ�κ��� �����ϱ�
						for(Client client : Main.clients) {
									// ���ο� �ӽð�ü : ����Ʈ�÷��� // ����Ʈ ��ü���� �Ѱ��� �����鼭 ����Ʈ�� ��ü����ŭ �ݺ�
							client.send(message);
							
						}
						
					}
					
				}catch (Exception e) {
					try {
						
						System.out.println("[ �޽��� ���� ���� ]"
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
		Main.threadpool.submit(thread); // ������Ǯ�� ���� ������� ������ �߰�
	}
	
	// ����� ��� Ŭ���̾�Ʈ���� �����ϴ� �޽����� ���� �޼ҵ�
	public void send(String message) {
		
		Runnable thread = new Runnable() {
			
			@Override
			public void run() {
				
				try {
					
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					System.out.println( "�۽� ����");
					
					out.write(buffer);
					out.flush();
					
				}catch (Exception e) {
					
					try {
						
						System.out.println("[ �޽��� �۽� ���� ]"
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
