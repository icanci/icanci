package cn.icanci.domain;

import lombok.Data;

@Data
public class User {
	private Long id;
	private String username;
	private String password;
	private String email;
	private Long jointime;
	private String headimage;
	private String sex;
	private Long age;
	private String personalizedSignature;
}
