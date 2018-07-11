package Servlet;

import Controller.DBAccessor;
import Model.Diagram;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddDiagram extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //获取表单信息
        String username = req.getParameter("username");
        String name = req.getParameter("diagramname");
        String path = req.getParameter("diagrampath");
        //获取用户
        User user = DBAccessor.getInstance().getUser(username);
        //获取图表
        Diagram diagram = new Diagram(true);
        diagram.setName(name);
        diagram.setPath(path);
        //插入图表
        if(DBAccessor.getInstance().addDiagram(user,diagram)){
            System.out.println("插入图表成功！");
        }
    }
}
