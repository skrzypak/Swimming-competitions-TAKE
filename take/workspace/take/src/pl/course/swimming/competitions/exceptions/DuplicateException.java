package pl.course.swimming.competitions.exceptions;

/**
 * Duplicate item in database exception
 *
 * @version 1.0
 * @category Exception
 */
public class DuplicateException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new Duplicate exception.
	 *
	 * @param itemName the item name
	 * @param value    the value
	 */
	public DuplicateException(String itemName, String value) {
		super("Item: " + itemName + " [ " + value + " ] allready exists");
	}

}
