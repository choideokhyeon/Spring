package com.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto2 {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private String regdate;
	private String updatedate;
}
