package com.example.remilty.model;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseMessage {
    private String message;
    private int statusCode;
}
