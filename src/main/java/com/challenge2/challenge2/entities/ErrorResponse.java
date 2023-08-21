package com.challenge2.challenge2.entities;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;


@Builder
public record ErrorResponse (String message, Timestamp timestamp, String httpStatus){
}
