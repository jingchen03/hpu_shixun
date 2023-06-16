package com.ruoyi.web.controller.guahao;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


@RestController
@RequestMapping("/api")
public class MyController {
  @GetMapping("/data")
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=UTF-8");
    System.out.println("这个是get请求");
    response.getWriter().write("get请求");
  }

  @PostMapping("/data")
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=UTF-8");


    InputStream input = request.getInputStream();
    //请求的数据为java.io.BufferedReader@5a648b44不可以直接使用---------------------
    BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      sb.append(line);
    }
    //上方实现数据转化为字符串
    //----------------------------------------------------------------------------
    String jsonString = sb.toString();
    JSONObject jsonObject = JSONObject.parseObject(jsonString);
    String fieldValue = jsonObject.getString("field102");
    //键和键值的形式
    System.out.println(fieldValue);
    //上方实现字符串变成字典,数据库的接口可以放在这里，一个全参构造的类接口
    // ---------------------------------------------------------------------------
  }
}