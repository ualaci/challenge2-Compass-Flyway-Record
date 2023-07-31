package com.challenge2.challenge2.restControllers.controllersAdvices;

import com.challenge2.challenge2.exceptions.BadRequestException;
import com.challenge2.challenge2.exceptions.InvalidRoleException;
import org.modelmapper.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.challenge2.challenge2.entities.ErrorResponse;
import com.challenge2.challenge2.exceptions.NotFoundException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ClassesRestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), new Timestamp(System.currentTimeMillis()), HttpStatus.NOT_FOUND.name());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globlalExceptionHandler(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Something went wrong", new Timestamp(System.currentTimeMillis()), HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), new Timestamp(System.currentTimeMillis()), HttpStatus.BAD_REQUEST.name());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorResponse errorDetails = new ErrorResponse(
                "Total Errors:" + ex.getErrorCount() + " First Error:" + ex.getFieldError().getDefaultMessage(), new Timestamp(System.currentTimeMillis()), HttpStatus.BAD_REQUEST.name());


        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRoleException(InvalidRoleException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),
                new Timestamp(System.currentTimeMillis()), HttpStatus.BAD_REQUEST.name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

        }
