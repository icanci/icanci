package cn.icanci.exception;

public class LogicException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * 自定义异常
	 * 
	 * @param message 异常信息
	 * @param cause   根本原因
	 */
	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 	自定义异常
	 * 
	 * @param message  异常信息
	 */
	public LogicException(String message) {
		super(message);
	}

}
