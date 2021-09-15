package pl.course.swimming.competitions.exceptions;

/**
 * Id not exists in database exception
 *
 * @version 1.0
 * @category Exception
 */
public class IdNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new Id not found exception.
	 *
	 * @param id       the id
	 * @param itemName the item name
	 */
	public IdNotFoundException(Long id, String itemName) {
		super(itemName + " with ID [ " + id.toString() + " ] not exists");
	}

}