package Servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  登录跳转响应
 */
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Boolean codeImageIsRight = false;
        Boolean loginIsSucced = false;
        //检查验证码
        String clientCheckCode = req.getParameter("validateCode");//接收客户端浏览器提交上来的验证码
        String serverCheckCode = (String) req.getSession().getAttribute("checkCode");//从服务器端的session中取出验证码
        if (clientCheckCode.equals(serverCheckCode)) {//将客户端验证码和服务器端验证比较，如果相等，则表示验证通过
            System.out.println("验证码验证通过！");
            codeImageIsRight = true;
        }else {
            System.out.println("验证码验证失败！");
        }
        //检查用户名和密码
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        //数据库检查用户名和密码
        //if(检查成功)
            loginIsSucced = true;
        System.out.print(name);
        System.out.print(password);
        //跳转至用户主界面
        if(codeImageIsRight && loginIsSucced){
            resp.sendRedirect("diagrams.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("ee");
        doGet(req,resp);
    }
}
