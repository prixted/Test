package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.model.vo.Member;

// DAO
// 1) DB에 접속(연결)
// 2) Controller에서 전달받은 데이터를 SQL구문을 통해 DB에 전송 및 결과받기
// 3) SELECT 구문을 제외한 DML의 경우 트랙잭션 처리(COMMIT,ROLLBACK)


public class MemberDao {

	
	/**
	 * 1. 사용자 전체 정보 조회
	 * @return
	 */
	public ArrayList<Member> selectAll() {
		
		// 처리된 결과의 결과(조회된 회원들)을 받아줄 ArrayList 선언
		ArrayList<Member> list = new ArrayList<Member>();
		// DB의 연결 정보를 담는 객체 선언
		Connection conn = null;
		// SQL문을 전송하고, 그에 해당하는 결과를 받는 객체 선언
		Statement stmt = null;
		// 쿼리 실행 후 조회된 결과 값들이 실질적으로 담길 ResultSet 객체 선언
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER";
		
		try {
			// 1.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE"
												, "JDBC"
												, "JDBC");
			// 3.
			stmt = conn.createStatement();
			// 4.5.
			rset = stmt.executeQuery(sql);
			
			// 6.
			while(rset.next()) {
				Member m = new Member();
				
				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				
				// 리스트에 해당 회원 한 행씩 추가
				list.add(m);
				
				
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) { 
			e.printStackTrace();
		} finally {
			// 자원 반납
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}


	/**
	 * 2. 아이디로 조회
	 * @param inputId
	 * @return
	 */
	public Member selectOne(String inputId) {
		Member mem = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = '"+ inputId + "'"; 
		
		try {
			
			// 1.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE"
												, "JDBC"
												, "JDBC");
			// 3.
			stmt = conn.createStatement();
			// 4.5.
			rset = stmt.executeQuery(sql);	//SELECT는 executeQuery(), I,U,D는 executeUpdate()
			
			if(rset.next()) {
				mem = new Member();
				
				mem.setUserId(rset.getString("USERID"));
				mem.setPassword(rset.getString("PASSWORD"));
				mem.setUserName(rset.getString("USERNAME"));
				mem.setGender(rset.getString("GENDER"));
				mem.setAge(rset.getInt("AGE"));
				mem.setEmail(rset.getString("EMAIL"));
				mem.setPhone(rset.getString("PHONE"));
				mem.setAddress(rset.getString("ADDRESS"));
				mem.setHobby(rset.getString("HOBBY"));
				mem.setEnrollDate(rset.getDate("ENROLLDATE"));
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return mem;
	}
	
	/**
	 * 3. 이름으로 검색
	 * @param inputName
	 * @return
	 */
	public ArrayList<Member> selectByName(String inputName) {
		ArrayList<Member> list = new ArrayList<Member>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
//		String sql = "SELECT * FROM MEMBER WHERE USERNAME = '"+inputName+"'";
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%"+inputName+"%'";
		
		try {
			// 1. JDBC 등록 처리
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. conn
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE"
					, "JDBC"
					, "JDBC");

			// 3. stmt
			stmt = conn.createStatement(); 
			
			// 4. 담을 객체
			rset = stmt.executeQuery(sql);	
			
			while(rset.next()) {
				Member m = new Member();
				
				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));

				list.add(m);
				
			}
			
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	/**
	 * 4. 회원 추가 기능
	 * @param m
	 * @return
	 */
	public int insertMember(Member m){

		
		// 처리 된 결과의 결과를 받아 줄 변수를 선언(처리된 행의 갯수)
		int result = 0;
		
		// DB의 연결 정보를 담는 객체 선언
		Connection conn = null;
		
		// SQL문을 전송하고 그에 해당하는 결과를 받는 객체 선언
		Statement stmt = null;
		
		// 실행할 쿼리문(완성형태) --> 끝에 세미콜론은 작성X!!!
		String sql = "INSERT INTO MEMBER VALUES("
					+" '" + m.getUserId() + "'"
					+", '" + m.getPassword() + "'"
					+", '" + m.getUserName() + "'"
					+", '" + m.getGender() + "'"
					+", '" + m.getAge() + "'"
					+", '" + m.getEmail() + "'"
					+", '" + m.getPhone() + "'"
					+", '" + m.getAddress() + "'"
					+", '" + m.getHobby() + "'"
					+", SYSDATE)";
		
		try {
			// 1) jdbc dirver 등록처리
			// 해당 디렉토리를 찾지 못하면, ClassNotFoundException이 발생
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");
			
			// 2) Connection 객체 생성 : DB에 연결하는 통행권 역할을 하는 Connection 객체 
			// 						 --> DB에 연결(url, id, pwd)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE"
												, "JDBC"
												, "JDBC");
			/*
			 * jdbc:oracle:thin --> JDBC드라이버가 thin타입임을 의미한다.
			 * @127.0.0.1 --> 오라클이 설치된 서버의  ip(127.0.0.1은 로컬호스트 .. 자기자신 @localhost로 대체 가능)
			 * 1521       --> 오라클 전용 포트번호
			 * XE         --> 접속할 오라클 DB명
			 * id         --> 사용자 ID
			 * pwd        --> 암호
			 */
			
			// 3) Statement 객체 생성 : 쿼리문 실행하기 위해서
			stmt = conn.createStatement();
			
			// 4, 5) 쿼리문 전송, 실행 결과 받기
			
			// Insert, Update, Delete구문은 executeUpdate() 메소드 사용
			result = stmt.executeUpdate(sql);
			
			System.out.println(result);
			
			// 6) 트랙잭션 처리(Insert, Update, Delete 작업은 반드시!!)
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	

	/**
	 * 5. 회원 정보 수정
	 * @param m
	 * @return
	 */
	public int updateMember(Member m) {
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "UPDATE MEMBER SET "
								+ "PASSWORD = '"+m.getPassword()+"', "
								+ "EMAIL = '"+m.getEmail()+"', "
								+ "PHONE = '"+m.getPhone()+"', "
								+ "ADDRESS = '"+m.getAddress()+"' "
								+ " WHERE USERID = '"+m.getUserId()+"'";
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE"
					, "JDBC"
					, "JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally { 
			try {
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	
	
		return result; 
	}
	
	
	
	
	/**
	 * 6. 회원 탈퇴(회원정보 삭제)
	 * @param inputId
	 * @return
	 */
	public int deleteMember(String inputId) {
		Scanner sc = new Scanner(System.in);
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID = '"+inputId+"'";
		System.out.print(inputId+ "님, 정말 회원탈퇴 하시겠습니까?(Y/n) : ");
		String str1 = sc.next().toUpperCase();
		
		if("Y".equals(str1)) {
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
	
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE"
						, "JDBC"
						, "JDBC");
				
				stmt = conn.createStatement();
				
				result = stmt.executeUpdate(sql);
				
				// 6) 트랙잭션 처리(Insert, Update, Delete 작업은 반드시!!)
				if(result > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}
	
				
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
				
			}
			
			
			return result;
			
		}
		
		else {
			return 0;
		}

	} 
}













