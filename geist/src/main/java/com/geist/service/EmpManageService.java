package com.geist.service;

import java.util.List;

import com.geist.domain.Criteria;
import com.geist.domain.EmpViewTableVO;

/* *
 * 사원 관리 페이지
 * 담당 : 김호영
 */

public interface EmpManageService {
	public List<EmpViewTableVO> getList(Criteria cri);
}
