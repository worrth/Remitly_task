package com.example.remilty.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorMessage {
    private String message;
    private int statusCode;
}
