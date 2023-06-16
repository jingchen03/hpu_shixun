package com.ruoyi.web.controller.guahao;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.sql.MSSqlDBUtils;
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
import java.sql.PreparedStatement;

@RestController
@RequestMapping("/api")
public class chuangkou {
    @PostMapping("/chuangkou")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        InputStream input = request.getInputStream();
//        //请求的数据为java.io.BufferedReader@5a648b44不可以直接使用---------------------
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
        //键和键值的形式
        System.out.println(jsonObject);
        //上方实现字符串变成字典,数据库的接口可以放在这里，一个全参构造的类接口
        // ---------------------------------------------------------------------------
        String case_number=jsonObject.get("case_number").toString();//1
        String real_name=jsonObject.get("real_name").toString();//2
        String gender=jsonObject.get("gender").toString();//3
        String age=jsonObject.get("age").toString();//4
        String birthdate=jsonObject.get("birthdate").toString();//5
        String card_number=jsonObject.get("card_number").toString();//6
        String home_address=jsonObject.get("home_address").toString();//7
        String settle_category_id=jsonObject.get("settle_category_id").toString();//8
        String visit_date=jsonObject.get("visit_date").toString();//9
        String noon=jsonObject.get("noon").toString();//10
        String deptment_id=jsonObject.get("deptment_id").toString();//11
        String regist_level_id=jsonObject.get("regist_level_id").toString();//12
        String employee_id=jsonObject.get("employee_id").toString();//13
        String is_book=jsonObject.get("is_book").toString();//14
        String regist_money=jsonObject.get("regist_money").toString();//15
        String regist_method=jsonObject.get("regist_method").toString();//16
        String sql = "INSERT INTO [register] (case_number,real_name,gender,age,birthdate,card_number,home_address,settle_category_id,visit_date,noon,deptment_id,regist_level_id,employee_id,is_book,regist_money,regist_method) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        try {
            PreparedStatement ps = MSSqlDBUtils.getPreparedStatement(sql);
            ps.setString(1, case_number);
            ps.setString(2, real_name);
            ps.setString(3, gender);
            ps.setInt(4, Integer.parseInt(age));
            ps.setString(5, birthdate);
            ps.setString(6, card_number);
            ps.setString(7, home_address);
            ps.setString(8, settle_category_id);
            ps.setString(9, visit_date);
            ps.setString(10, noon);
            ps.setString(11, deptment_id);
            ps.setString(12, regist_level_id);
            ps.setString(13, employee_id);
            ps.setString(14, is_book);
            ps.setString(15, regist_money);
            ps.setString(16, regist_method);
            int rowCount = ps.executeUpdate(); // 执行 SQL 语句
            if (rowCount > 0) {
                System.out.println("插入成功");
            } else {
                System.out.println("插入失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
