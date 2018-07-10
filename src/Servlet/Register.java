package Servlet;

import Controller.DBAccessor;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Boolean codeImageIsRight = false;
        Boolean registerIsSucced = false;

        //检查验证码
        String clientCheckCode = req.getParameter("validateCode");//接收客户端浏览器提交上来的验证码
        clientCheckCode = clientCheckCode.toLowerCase();
        String serverCheckCode = (String) req.getSession().getAttribute("checkCode");//从服务器端的session中取出验证码
        serverCheckCode = serverCheckCode.toLowerCase();
        if (clientCheckCode.equals(serverCheckCode)) {//将客户端验证码和服务器端验证比较，如果相等，则表示验证通过
            System.out.println("验证码验证通过！");
            codeImageIsRight = true;
        }else {
            System.out.println("验证码验证失败！");
        }

        //检查用户名和密码
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String mail = req.getParameter("mail");

        //获取数据库连接
        DBAccessor accessor = DBAccessor.getInstance();
        accessor.getConnection();

        //插入数据
        User temp = new User(true);
        temp.setName(name);
        temp.setPassword(password);
        temp.setMail(mail);
        if(accessor.addUser(temp))
            registerIsSucced = true;

        //跳转至用户主界面
        if(codeImageIsRight && registerIsSucced){
            System.out.println("注册成功！");
            resp.sendRedirect("diagrams.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req,resp);
    }
}
