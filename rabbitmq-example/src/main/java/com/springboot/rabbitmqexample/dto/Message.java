package com.springboot.rabbitmqexample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
    private String messageId;
    private String data;
    private String receiver;
    private String sender;
    private Date date;

}
