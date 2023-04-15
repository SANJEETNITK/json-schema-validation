package com.springboot.rabbitmqexample.multiDataTypeQueue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageStatus implements Serializable {

    private Message order;
    private String status;

    private String message;

}
