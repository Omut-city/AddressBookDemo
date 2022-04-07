package study.interview.codeExample.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import study.interview.codeExample.exception.CategoryHasPropertiesException;
import study.interview.codeExample.exception.CategoryNotFoundException;
import study.interview.codeExample.exception.CategoryWrongIdException;
import study.interview.codeExample.exception.ContactNotFoundException;
import study.interview.codeExample.exception.ContactWrongIdException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ContactWrongIdException.class)
    public ModelAndView handleContactWrongIdException(ContactWrongIdException e) {
        log.error("ContactWrongIdException handler executed");
        ModelAndView modelAndView = new ModelAndView("errorPage");
        modelAndView.addObject("javaVersion", System.getProperty("java.version"));
        modelAndView.addObject("error", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(CategoryWrongIdException.class)
    public ModelAndView handleCategoryWrongIdException(CategoryWrongIdException e) {
        log.error("CategoryWrongIdException handler executed");
        ModelAndView modelAndView = new ModelAndView("errorPage");
        modelAndView.addObject("javaVersion", System.getProperty("java.version"));
        modelAndView.addObject("error", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public ModelAndView handleContactNotFoundException(ContactNotFoundException e) {
        log.error("ContactNotFoundException handler executed");
        ModelAndView modelAndView = new ModelAndView("errorPage");
        modelAndView.addObject("javaVersion", System.getProperty("java.version"));
        modelAndView.addObject("error", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ModelAndView handleCategoryNotFoundException(CategoryNotFoundException e) {
        log.error("CategoryNotFoundException handler executed");
        ModelAndView modelAndView = new ModelAndView("errorPage");
        modelAndView.addObject("javaVersion", System.getProperty("java.version"));
        modelAndView.addObject("error", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(CategoryHasPropertiesException.class)
    public ModelAndView handleCategoryHasPropertiesException(Exception e) {
        log.error("CategoryHasPropertiesException handler executed");
        ModelAndView modelAndView = new ModelAndView("errorPage");
        modelAndView.addObject("javaVersion", System.getProperty("java.version"));
        modelAndView.addObject("error", e.getMessage());
        return modelAndView;
    }

}
