package com.geist.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

/* *
 * 메인 페이지
 * 담당 : 김호영
 */

@Controller
@Log4j
public class MainController {
	@RequestMapping({"/", "/login"})
	public String login() {
		log.info("로그인 페이지 이동");
		return "/page/login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping("/register")
	public String register() {
		log.info("회원가입 페이지 이동");
		return "/page/register";
	}
	
	@RequestMapping("/idSearch")
	public String idSearch() {
		log.info("아이디 찾기 페이지 이동");
		return "/page/idfind";
	}
	
	@RequestMapping("/pwSearch")
	public String pwSearch() {
		log.info("비밀번호 찾기 페이지 이동");
		return "/page/pwdfind";
	}
	
	@RequestMapping("/main")
	public String main() {
		log.info("메인 페이지 이동");
		return "/page/index";
	}
	
	@RequestMapping("/attendance")
	public String attendance() {
		log.info("출결 페이지 이동");
		return "/page/attendance-page";
	}
	
	@RequestMapping("/empManage")
	public String empManage() {
		log.info("사원 관리 페이지 이동");
		return "/page/admin-page";
	}
	
	@RequestMapping("/joinRequest")
	public String joinRequest() {
		log.info("가입 승인 페이지 이동");
		return "/page/admin-page-approve";
	}
	
	@RequestMapping("/myPage")
	public String myPage() {
		log.info("마이페이지 이동");
		return "/page/Mypage-page";
	}
	
	@RequestMapping("/myPage/detail")
	public String myPagedetail() {
		log.info("마이페이지 수정 이동");
		return "/page/Mypage-page-write";
	}
	
	@RequestMapping("/notice")
	public String notice() {
		log.info("공지사항 페이지 이동");
		return "/page/document-page";
	}
	
	@RequestMapping("/project")
	public String project() {
		log.info("프로젝트 페이지 이동");
		return "/page/project-page";
	}
	
	@RequestMapping("/approvalRequest")
	public String approvalRequest() {
		log.info("결재 요청 페이지 이동");
		return "/page/approval-request";
	}
	
	@RequestMapping("/approvalSearch")
	public String approvalSearch() {
		log.info("결재 조회 페이지 이동");
		return "/page/approval-search";
	}
	
	@RequestMapping("/approvalAgree")
	public String approvalAgree() {
		log.info("결재 승인 페이지 이동");
		return "/page/approval-agree";
	}
	
	@RequestMapping("/address")
	public String address() {
		log.info("주소록 페이지 이동");
		return "/page/address-page";
	}
	
}
