package com.geist.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.geist.domain.AttendanceViewVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

/* *
 * 출결 페이지
 * 담당 : 김호영
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class AttendanceMapperTests {
	@Setter(onMethod_ = @Autowired)
	private AttendanceMapper mapper;
	
	@Test
	public void pagingTest() {
		int emp_no = 2;
		List<AttendanceViewVO> list = mapper.getList(emp_no);
		list.forEach(table -> log.info(table));
	}
}
