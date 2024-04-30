package com.javaexpress.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
public class ErrorAPI {
    private Integer statusCode;
    private String title;
    private String status;
    private String details;
    private LocalDateTime localDateTime;


}
