package cn.icanci.domain;

import lombok.Data;

@Data
public class UserSpace {
	private Long id;
	private Long userId;
	private String userMessages;
	private String userImage;
	private Long outputTime;
	private String outPrint;
}
