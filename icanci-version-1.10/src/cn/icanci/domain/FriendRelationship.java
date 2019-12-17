package cn.icanci.domain;

import lombok.Data;

@Data
public class FriendRelationship {
	private Long id;
	private Long userId1;
	private Long userId2;
}
