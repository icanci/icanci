package cn.icanci.domain;

import lombok.Data;

/**
 * 用来接收 游客或者用户对本站的一些反馈内容或者举报内容 便于迭代计划
 * @author CC
 *
 */

@Data
public class UserFeedback {
	private Long id;
	private Long feedbackTime;
	private String feedbackTheme;
	private String feedbackContent;
	private String outPrint;
}
