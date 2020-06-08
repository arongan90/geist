package com.geist.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geist.login.domain.LoginVO;
import com.geist.main.domain.Criteria;
import com.geist.project.domain.ProjectDTO;
import com.geist.project.domain.ProjectVO;
import com.geist.project.service.ProjectService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/*
 * 프로젝트
 * 담당 : 홍예진
 * */

@RestController
@RequestMapping("/project")
@AllArgsConstructor
@Log4j
public class ProjectController {

		private ProjectService service;
		
		//sys 계정의 모든 프로젝트 리스트 출력
		@GetMapping(value = "/projectAllList/{page}",
				produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<ProjectDTO> projectAllList(@PathVariable("page") int page, HttpServletRequest req){		
			
			//페이징처리
			Criteria cri = new Criteria(page, 10);
					
			return new ResponseEntity<ProjectDTO>(service.projectAllList(cri), HttpStatus.OK);
		}
		
		//부서별 해당하는 프로젝트 리스트 출력
		@GetMapping(value = "/projectList/{page}",
				produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<ProjectDTO> projectList(@PathVariable("page") int page, HttpServletRequest req){
			HttpSession session = req.getSession();
			
			//session처리한 사원번호로 부서 구분하기
			LoginVO no = (LoginVO)session.getAttribute("member");
			Long emp_no = no.getEmp_no();
			
			//emp_no를 통해 부서번호인 dept_no를 가져와 구분함
			int dept_no = service.projectDept(emp_no);
			
			Criteria cri = new Criteria(page, 10);
			
			return new ResponseEntity<ProjectDTO>(service.projectList(cri, dept_no), HttpStatus.OK);
		}
		
		//프로젝트를 작성하는 부분
		@PostMapping(value = "/projectWrite", consumes = "application/json",
				produces = {MediaType.TEXT_PLAIN_VALUE})
		public ResponseEntity<String> projectWrite(@RequestBody ProjectVO vo){
			
			//로그를 찍는 부분	
			log.info("projectWrite Controller");
			
			//작성시 projectWrite를 통해 내용을 저장하고 projectMWrite를 통해 부서 번호를 할당한다
			service.projectWrite(vo);
			service.projectMWrite(vo);
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH }, value = "/projectUpdate/{proj_no}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE })
		public ResponseEntity<String> projectUpdate(@RequestBody ProjectVO vo, @PathVariable("proj_no") int proj_no) {
			
			vo.setProj_no(proj_no);
			
			log.info("projectUpdate Controller");

			service.projectUpdate(vo);

			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		@GetMapping(value = "/projectUpdate/{proj_no}",  produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<ProjectVO> noticeUpdate(@PathVariable("proj_no") int proj_no){
					
			log.info("projectUpdate Controller Get()");
			
			return new ResponseEntity<ProjectVO>(service.projectRead(proj_no), HttpStatus.OK);
		}
		
		//프로젝트 삭제 부분
		@DeleteMapping(value ="/projectDelete/{proj_no}", produces = {MediaType.TEXT_PLAIN_VALUE})
		public ResponseEntity<String> remove(@PathVariable("proj_no") int proj_no){
			log.info("projectDelete Controller");
			return service.projectDelete(proj_no) == 1 ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
}

