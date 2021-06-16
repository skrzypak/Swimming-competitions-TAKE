package pl.course.swimming.competitions.exceptions;

public class DuplicatException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DuplicatException(String itemName, String value) {
		super("Item: " + itemName + " [ " + value + " ] allready exists");
	}

}
