package cn.edu.nju.TrainingSystem.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by baiguofeng on 2017/3/13.
 */
public class AlertInformation {

    public static void generateAlert(HttpServletResponse response, String msg) {
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().write("<script>alert('" + msg + "');history.go(-1);location.reload(true);</script>");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
