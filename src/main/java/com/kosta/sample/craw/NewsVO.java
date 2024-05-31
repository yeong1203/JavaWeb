package com.kosta.sample.craw;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class NewsVO {
	private int seq;
	private String href;
	private String img;
	private String title;
	private String ref;
	private String regdate;
	
	// @NoArgsConstructor ==> pulic NewVO() {}	기본생성자 대신
	// @AllArgsConstructor => Constructor 생성하는 것 대신!
	
}
