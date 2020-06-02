package com.geist.myPage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geist.myPage.domain.MypageDTO;
import com.geist.myPage.mapper.MypageMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

/* *
 * 마이페이지
 * 담당 : 김현선
 */

@Service
public class MypageServiceImpl implements MypageService {

	@Setter(onMethod_ = @Autowired)
	private MypageMapper mapper;

	@Override
	public MypageDTO read(Long emp_no) {
		return mapper.read(emp_no);
	}

	@Override
	public int modify(MypageDTO dto) {
		return mapper.modify(dto);
	}
}
