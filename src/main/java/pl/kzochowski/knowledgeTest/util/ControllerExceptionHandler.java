package pl.kzochowski.knowledgeTest.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.kzochowski.knowledgeTest.service.UserService;

@ControllerAdvice
public class ControllerExceptionHandler {

    //todo add Error response object

    @ResponseBody
    @ExceptionHandler(UserService.UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.OK)
    String userAlreadyExists(UserService.UserAlreadyExistsException exception) {
        exception.printStackTrace();
        return exception.getMessage();
    }
}
