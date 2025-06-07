package Katsu.Katsu_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String, String> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace(); // 콘솔에 예외 찍기

        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return response;
    }

}
