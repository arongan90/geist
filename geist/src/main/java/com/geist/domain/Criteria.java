package com.geist.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/* *
 * 검색 기준
 * 담당 : 김호영
 */

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum;
	private int amount;
	
	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
