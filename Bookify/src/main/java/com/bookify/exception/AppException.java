package com.bookify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.UnknownFormatConversionException;

@RestControllerAdvice
public class AppException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public Map<String,String> handleProductNotFoundException(ProductNotFoundException exception){
        Map<String,String> map = new HashMap<>();
        map.put("error message: >>",exception.getMessage());
        return map;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> businessHandler(MethodArgumentNotValidException ex)
    {
        Map<String,String> map = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            map.put(error.getField(),error.getDefaultMessage());
        });
        return map;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderItemQuantityException.class)
    public Map<String,String> handleProductNotFoundException(OrderItemQuantityException exception){
        Map<String,String> map = new HashMap<>();
        map.put("error message: >>",exception.getMessage());
        return map;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderItemNoFoundException.class)
    public Map<String,String> handleProductNotFoundException(OrderItemNoFoundException exception){
        Map<String,String> map = new HashMap<>();
        map.put("error message: >>",exception.getMessage());
        return map;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public Map<String,String> handleProductNotFoundException(CategoryNotFoundException exception){
        Map<String,String> map = new HashMap<>();
        map.put("error message: >>",exception.getMessage());
        return map;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UnknownFormatConversionException.class)
    public Map<String,String> handleProductNotFoundException(UnknownFormatConversionException exception){
        Map<String,String> map = new HashMap<>();
        map.put("error message: >>",exception.getMessage()+" Not found this order");
        return map;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderNotFoundException.class)
    public Map<String,String> handleProductNotFoundException(OrderNotFoundException exception){
        Map<String,String> map = new HashMap<>();
        map.put("error message: >>",exception.getMessage());
        return map;
    }

}
