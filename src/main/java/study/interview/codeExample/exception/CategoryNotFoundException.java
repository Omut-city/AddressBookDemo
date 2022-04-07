package study.interview.codeExample.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Integer categoryId) {
        super("Category with id = '" + categoryId + "' not found");
    }

}
