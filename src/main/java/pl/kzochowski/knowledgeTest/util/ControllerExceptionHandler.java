package pl.kzochowski.knowledgeTest.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.kzochowski.knowledgeTest.service.CategoryService;
import pl.kzochowski.knowledgeTest.service.UserService;

@ControllerAdvice
public class ControllerExceptionHandler {

    //todo add Error response object

    @ResponseBody
    @ExceptionHandler(UserService.UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String userAlreadyExists(UserService.UserAlreadyExistsException exception) {
        exception.printStackTrace();
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CategoryService.CategoryDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String categoryDoesNotExist(CategoryService.CategoryDoesNotExistException exception) {
        exception.printStackTrace();
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CategoryService.CategoryAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String categoryAlreadyExist(CategoryService.CategoryAlreadyExistsException exception) {
        exception.printStackTrace();
        return exception.getMessage();
    }
}
