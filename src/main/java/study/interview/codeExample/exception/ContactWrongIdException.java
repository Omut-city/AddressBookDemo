package study.interview.codeExample.exception;

public class ContactWrongIdException extends RuntimeException {

    public ContactWrongIdException(Integer expectedContactId, Integer actualContactId) {
        super("Expected find contact with id = '" + expectedContactId + "' but found contact with id = '" + actualContactId + "'");
    }

}
