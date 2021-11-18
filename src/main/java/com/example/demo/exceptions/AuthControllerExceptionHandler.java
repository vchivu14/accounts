package com.example.demo.exceptions;

import com.example.demo.DemoApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AuthControllerExceptionHandler {
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    private String createErrorMessage(HttpStatus httpStatus, Exception ex, WebRequest request) {
        return String.valueOf(new AuthErrorMessage(httpStatus.value(), new Date(), ex.getMessage(),
                request.getDescription(false)));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String resourceNotFoundException(ResourceNotFoundException ex, WebRequest request, Model model) {
        var httpStatus = HttpStatus.NOT_FOUND;
        logger.info(createErrorMessage(httpStatus,ex,request));
        String message = "The resource you're looking for is not here, please go back and try again";
        model.addAttribute("message", message);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String globalExceptionHandler(Exception ex, WebRequest request, Model model) {
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        logger.info(createErrorMessage(httpStatus,ex,request));
        String message = "Your request has raised an error, please go back and try again";
        model.addAttribute("message", message);
        return "error";
    }

}
