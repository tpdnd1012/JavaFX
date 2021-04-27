package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

public class DAO {

	Connection conn;

	private static DAO DB = new DAO();

	public static DAO getDB() {

		return DB;

	}

	public DAO() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/Project_FX?serverTime=UTC", "root",
					"1234");

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// ȸ������ �޼ҵ�
	public int setMember(Member temp) {

		String sql = "insert into Member values(?,?,?,?,?,?,?)";

		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, temp.getId());
			pstmt.setString(2, temp.getPw());
			pstmt.setString(3, temp.getName());
			pstmt.setString(4, temp.getGender());
			pstmt.setString(5, temp.getAge());
			pstmt.setString(6, temp.getPnumber());
			pstmt.setString(7, temp.getJob());

			pstmt.executeUpdate();

			return 1; // ���� ����

		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0; // ���� ����
	}

	// ���̵�ã�� �޼ҵ�
	public String getid(String findname, String findpnumber) {

		String sql = "select id from Member where name=? and phone=?";

		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, findname);
			pstmt.setString(2, findpnumber);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				String id = rs.getString("id");

				return id;

			} else {

				return "2";

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "0";
	}

	// ��й�ȣ ã�� �޼ҵ�
	public String getpw(String findid, String findname, String findpnumber) {

		String sql = "select pw from Member where id=? and name=? and phone=?";

		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, findid);
			pstmt.setString(2, findname);
			pstmt.setString(3, findpnumber);

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
	public int login(String loginid, String loginpassword) {

		String slq = "select * from Member where id=? and pw=?";

		try {

			PreparedStatement pstmt = conn.prepareStatement(slq);

			pstmt.setString(1, loginid);
			pstmt.setString(2, loginpassword);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				return 1;

			} else {

				return 2;

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	// ȸ��Ż�� �޼ҵ�
	public int deletemember(String deleteid, String deletepw) {

		String sql = "delete from Member where id=? and pw=?";

		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, deleteid);
			pstmt.setString(2, deletepw);

			pstmt.executeUpdate();

			return 1;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	// �ش� �α��ε� ȸ������
	public Member getmember(String loginid) {

		Member member = new Member();

		String sql = "select * from Member where id=?";

		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, loginid);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				member.setId(rs.getString(1));
				member.setName(rs.getString(3));
				member.setGender(rs.getString(4));
				member.setAge(rs.getString(5));
				member.setPnumber(rs.getString(6));
				member.setJob(rs.getString(7));

				return member;

			} else {

				return member;

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return member;
	}

	// �ش� �α��ε� ȸ������(��й�ȣ���� ��ȸ)
	public Member modifyselect(String loginid) {

		Member member = new Member();

		String sql = "select * from Member where id=?";

		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, loginid);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				member.setId(rs.getString(1));
				member.setPw(rs.getString(2));
				member.setName(rs.getString(3));
				member.setGender(rs.getString(4));
				member.setAge(rs.getString(5));
				member.setPnumber(rs.getString(6));
				member.setJob(rs.getString(7));

				return member;

			} else {

				return member;

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return member;
	}
	
	// ������ ȸ������ ����
	public int modifycomplete(String id, String pw, String name, String gender, String age, String phone, String job, String pkid) {
		
		String sql = "update Member set id=?, pw=?, name=?, gender=?, age=?, phone=?, job=? where id=?";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, gender);
			pstmt.setString(5, age);
			pstmt.setString(6, phone);
			pstmt.setString(7, job);
			pstmt.setString(8, pkid);
			
			pstmt.executeUpdate();
			
			return 1;
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

}
