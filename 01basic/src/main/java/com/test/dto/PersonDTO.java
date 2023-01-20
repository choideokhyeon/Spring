package com.test.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//어노테이션
//@Data : Getter Setter,ToString 한번에 적용
//@NoArgsConstructor : 디폴트 생성자
//@AllArgsConstructor : 모든 값을 받는 생성자

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class PersonDTO {
	private String name;
	private String age;
	private String addr;
}
