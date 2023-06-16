package com.ruoyi.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/data")
    public void handleData(@RequestParam String field102) {
        System.out.println("Received data from client: " + field102);
    }
}