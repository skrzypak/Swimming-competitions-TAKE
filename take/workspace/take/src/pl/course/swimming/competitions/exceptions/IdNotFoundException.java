package pl.course.swimming.competitions.exceptions;

/**
 * Id not exists in database exception
 * @version 1.0
 * @category Exception
 * */
public class IdNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public IdNotFoundException(Long id, String itemName) {
		super(itemName + " with ID [ " + id + " ] not exists");
	}

}