package maxim.express.format;

/**
 * 解析ExpressionToken出错时抛出
 * @author maxim
 */
public class FormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2156367068320450613L;
	public FormatException() {
		super();
	}

	public FormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public FormatException(String message) {
		super(message);
	}

	public FormatException(Throwable cause) {
		super(cause);
	}
}
