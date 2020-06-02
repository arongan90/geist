package com.geist.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.geist.main.domain.Criteria;
import com.geist.project.domain.ProjectVO;

/*
 * 프로젝트 테이블
 * 담당 : 홍예진
 * 
 *  * create table project(
proj_no number(10) not null,
proj_name varchar2(20) not null,
proj_agency varchar2(20) not null,
proj_start date,
proj_end date,
constraint pk_project primary key(proj_no)
);

create table proj_management(
proj_no number(10) not null,
dept_no number(10) not null,
constraint pk_proj_management primary key(proj_no, dept_no),
constraint fk_proj_management_proj_no foreign key(proj_no) references project(proj_no),
constraint fk_proj_management_dept_no foreign key(dept_no) references department(dept_no)
);
 * */

public interface ProjectMapper {
	
	public List<ProjectVO> projectAllList(Criteria cri);
	
	public List<ProjectVO> projectList(@Param("cri") Criteria cri,@Param("dept_no") int dept_no);
	
	public ProjectVO projectRead(int proj_no);
	
	public void projectWrite(ProjectVO vo);
	
	public void projectMWrite(ProjectVO vo);
	
	public int projectUpdate(ProjectVO vo);
	
	public int projectDelete(int proj_no);
	
	public int projectDept(Long emp_no);
	
	public int projectCount(int dept_no);
	
	public int projectAllCount();
}
