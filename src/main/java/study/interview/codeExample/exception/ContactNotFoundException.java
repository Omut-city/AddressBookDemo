package study.interview.codeExample.exception;

public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException(Integer contactId) {
        super("Contact with id = '" + contactId + "' not found");
    }

}
