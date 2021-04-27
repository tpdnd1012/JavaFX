package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;;

public class DAO {

	Connection conn;
	PreparedStatement pstmt;

	private static DAO DB = new DAO(); // DB��ü ����

	public static DAO getDB() {

		return DB;

	}

	public DAO() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/review?serverTime=UTC", "root", "1234");


		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// ȸ�� �����ϴ� �޼ҵ�
	public int setMember(Member temp) {

		String SQL = "insert into wedding values(?,?,?)";

		try {

			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, temp.getId());
			pstmt.setString(2, temp.getPw());
			pstmt.setString(3, temp.getName());

			pstmt.executeUpdate();

			return 1; // SQL ���� ����

		
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}

		return 0; // SQL ���� ����
	}

	// ȸ�� ��й�ȣ ã�� �޼ҵ�
	public String getpassword(String findid, String findname) {

		String SQL = "select pw from wedding where id=? and name=?";

		try {

			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, findid);
			pstmt.setString(2, findname);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				String pw = rs.getString("pw");

				return pw;

			} else {

				return "2";

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "0";
	}
	
	// �α��� �޼ҵ�
	public int login(String logid, String logpassword) {
		
		String SQL = "select * from wedding where id=? and pw=?";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, logid);
			pstmt.setString(2, logpassword);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				return 1;
				
			}else {
				
				return 2;
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	// �α��ε� �ش� ID�� ȸ������ ã�� �޼ҵ�
	public Member getmember(String loginid) {
		
		Member member = new Member();
		
		String SQL = "select * from wedding where id=?";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, loginid);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				member.setId(rs.getString(1));
				member.setName(rs.getString(3));
				
				return member;
				
			}else {
				
				return member;
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return member;
	}
	
	// ȸ����� �޼ҵ�
	public int wmemberadd(wmember temp) {
		
		String SQL = "insert into wmember values(?,?,?,?,?)";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, temp.gender);
			pstmt.setString(2, temp.name);
			pstmt.setInt(3, temp.age);
			pstmt.setString(4, temp.tel);
			pstmt.setString(5, temp.job);
			
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	// ��� ȸ������ ��� ���
	public ObservableList<wmember> getlistwmember(){
		
		ObservableList<wmember> list = FXCollections.observableArrayList();
		
		String SQL = "select * from wmember";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				wmember temp = new wmember();
				
				temp.setGender(rs.getString(1));
				temp.setName(rs.getString(2));
				temp.setAge(rs.getInt(3));
				temp.setTel(rs.getString(4));
				temp.setJob(rs.getString(5));
				
				list.add(temp);
				
			}
			return list;
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	// �ش� ȸ�� ���� �޼ҵ�
	public void wmemberdelete(String tel) {
		
		String SQL = "delete from wmember where tel = ?";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, tel);
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	// �����ư ������ ������ ȸ�� ��ȣ ��ȸ
	public wmember updateselect(String tel) {
		
		wmember temp = new wmember();
		
		String SQL = "select * from wmember where tel = ?";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, tel);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				
				temp.setGender(rs.getString(1));
				temp.setName(rs.getString(2));
				temp.setAge(rs.getInt(3));
				temp.setTel(rs.getString(4));
				temp.setJob(rs.getString(5));
				
				
				
				
			}
			return temp;
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return temp;
		
	}
	
	// ��ϵ� ȸ�� ���� ����
	public int updatecompletion(String gender, String name, int age, String tel, String job, String pktel) {
		
		String SQL = "update wmember set gender=?, name=?, age=?, tel=?, job=? where tel = ?";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, gender);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.setString(4, tel);
			pstmt.setString(5, job);
			pstmt.setString(6, pktel);
			
			pstmt.executeUpdate();
			
			return 1; // ����
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return 0; // ����
	}

}
