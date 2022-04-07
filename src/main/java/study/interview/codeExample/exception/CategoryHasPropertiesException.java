package study.interview.codeExample.exception;

public class CategoryHasPropertiesException extends RuntimeException{

    public CategoryHasPropertiesException(Integer categoryId) {
        super("Category, id " + categoryId + ", has properties and can't be deleted");
    }

}
