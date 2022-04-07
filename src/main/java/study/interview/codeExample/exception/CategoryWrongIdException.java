package study.interview.codeExample.exception;

public class CategoryWrongIdException extends RuntimeException {

    public CategoryWrongIdException(int expectedCategoryId, int actualCategoryId) {
        super("Expected find category with id = '" + expectedCategoryId + "' but found category with id = '" + actualCategoryId + "'");
    }

}
