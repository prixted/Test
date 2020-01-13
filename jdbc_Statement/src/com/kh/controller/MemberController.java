package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

// Controller : View에서 전달받은 데이터를 가공처리(데이터 변환 및 디코딩) 후 Dao로 전달하는 기능
// 				Dao로부터 반환받은 결과에 따라 View(출력할 화면)을 결정해서 인코딩 후 데이터 화면에 전달
public class MemberController {
	
	/**
	 * 1. 사용자 전체 조회
	 */
	public void selectAll() {
		ArrayList<Member> list = new MemberDao().selectAll();
		
		if(list.isEmpty()) {
			new MemberMenu().displayError("해당되는 데이터가 없습니다.");
		} else {
			new MemberMenu().displayMemberList(list);
		}
	}
	
	/**
	 * 2. 아이디로 검색
	 * @param inputId
	 */
	public void selectOne(String inputId) {
		Member m = new MemberDao().selectOne(inputId);
		
		if(m != null) {
			new MemberMenu().displayMember(m);
		} else {
			new MemberMenu().displayError(inputId+"에 해당하는 데이터가 없습니다.");
		}
	}
	
	
	/**
	 * 3. 이름으로 검색
	 * @param inputName
	 */
	public void selectByName(String inputName) {
		ArrayList<Member> list = new MemberDao().selectByName(inputName);
		
		if(list.isEmpty()) {
			new MemberMenu().displayError(inputName + "에 해당되는 데이터가 없습니다.");
		} else {
			new MemberMenu().displayMemberList(list);
		}
	}
	
	
	
	/**
	 * 4. 사용자 가입
	 * @param m
	 */
	public void insertMember(Member m) {
		int result = new MemberDao().insertMember(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("회원가입 성공!"); // 성공메세지 출력 View 호출
		} else {
			new MemberMenu().displayError("회원가입 실패!!"); // 실패메세지 출력 View 호출
		}
	}
	
	
	/**
	 * 5. 회원 정보 수정
	 * @param m
	 */
	public void updateMember(Member m) {
		int result = new MemberDao().updateMember(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("회원 정보 수정 완료");
		} else {
			new MemberMenu().displayError("회원 정보 수정 실패");
		}
		
	}
	
	
	/**
	 * 6. 회원탈퇴 (멤버 삭제)
	 * @param inputName
	 */
	public void deleteMember(String inputId) {
		int result = new MemberDao().deleteMember(inputId);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("회원탈되 완료되었습니다. 꺼져라!!ㅋㅋㅋㅋㅋㅋ");
		} else {
			new MemberMenu().displayError("뭔가 잘못되었다.");
		}
	}
	
	
	
	
	
	
	
	
	

}
