package com.springboot.centralizedExceptionDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/nullpointer")
    public String generateException() {
        String str = null;
        int len = str.length();
        return "Null pointer exception generated";
    }

    @GetMapping("/arithmetic")
    public String generateDivideByZeroException() {
        int v = 1 / 0;
        return "Divide by zero exception generated!";
    }

    @GetMapping("/numberformat")
    public String generateNumberFormatException() {
        String s = "abc";
        int v = Integer.parseInt(s);
        return "Number format exception generated!";
    }

}
