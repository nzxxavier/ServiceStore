package Controller;

import Model.Diagram;
import Model.User;
import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBAccessor {
    private final String driver = "com.mysql.jdbc.Driver";
    private String url;
    private String username;
    private String password;
    private Connection conn;
    /**
     * 使用内部类静态加载实现单例模式
     */
    private static class DBAccessorHolder{
        private static DBAccessor instance=new DBAccessor();
    }
    /**
     * 由内部类调用
     */
    private DBAccessor(){
        //在这里初始化你的数据库设置
        url = "jdbc:mysql://localhost:3306/database_nzx?serverTimezone=UTC";
        username = "nzx";
        password = "nzhx1234";
    }
    /**
     * 获取实例
     */
    public static DBAccessor getInstance(){
        return DBAccessorHolder.instance;
    }
    /**
     * 打开连接
     */
    public void getConnection(){
        try {
            //注册驱动
            Class.forName(driver);
            //打开连接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(url,username,password);
            if(conn != null)
                System.out.println("连接成功!");
            //无则创建表，有则不创建
            createTable();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 创建表：User、Diagram、UserHasDiagram
     */
    private void createTable(){
        String createUser = "CREATE TABLE user(\n" +
                "  userid VARCHAR(33) primary key,\n" +
                "  name VARCHAR(12) NOT NULL unique,\n" +
                "  password VARCHAR(12) NOT NULL,\n" +
                "  mail VARCHAR(64) \n" +
                ");";
        if(execSQL(createUser))
            System.out.println("创建用户表成功！");
        String createDiagram = "CREATE TABLE `diagram` (\n" +
                "  diagramid varchar(33) PRIMARY KEY,\n" +
                "  userid varchar(33) NOT NULL,\n" +
                "  name varchar(20) NOT NULL,\n" +
                "  path varchar(100) NOT NULL UNIQUE,\n" +
                "  CONSTRAINT `fk_userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE" +
                ");";
        if(execSQL(createDiagram))
            System.out.println("创建模型图表成功！");
    }
    /**
     * 执行SQL语句
     * @param sql 要执行的SQL语句
     * @return 执行成功返回true
     */
    private boolean execSQL(String sql){
        Boolean result = false;
        // 执行查询
        try {
            System.out.println("准备语句...");
            PreparedStatement pstm = conn.prepareStatement(sql);
            if(pstm.execute()){
                result = true;
                System.out.println("语句执行成功！");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 执行SQL查询语句
     * @param sql 要执行的SQL查询语句
     * @return 执行成功返回true
     */
    private ResultSet execQuery(String sql){
        ResultSet rs;
        // 执行查询
        try {
            System.out.println("准备查询语句...");
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            return rs;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获得所有用户
     * @return 执行成功返回true
     */
    public List<User> getAllUser(){
        String sql = "SELECT * FROM USER";
        //执行语句
        ResultSet rs = execQuery(sql);
        List<User> result = new ArrayList<>();
        try {
            while (rs.next()){
                //每行对应一个用户
                User temp = new User(false);
                //获取并设置信息
                temp.setUUID(rs.getString("UUID"));
                temp.setName(rs.getString("name"));
                temp.setPassword(rs.getString("password"));
                temp.setMail(rs.getString("mail"));
                //插入链表
                result.add(temp);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 根据名字检索用户
     * @param name 用户名
     * @return 用户
     */
    public User getUser(String name){
        User result = new User(false);
        String sql = String.format("SELECT * FROM USER WHERE name=\'%s\'",name);
        //执行语句
        ResultSet rs = execQuery(sql);
        try {
            if (rs.next()){
                result.setUUID(rs.getString("userid"));
                result.setName(rs.getString("name"));
                result.setMail(rs.getString("mail"));
                result.setPassword(rs.getString("password"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (NullPointerException ne){
            result.setUUID("");
            result.setName("");
            result.setMail("");
            result.setPassword("");
        }
        return result;
    }
    /**
     * 增加用户
     * @param user 被插入数据库的用户
     * @return 执行成功返回true
     */
    public boolean addUser(User user){
        String uuid = user.getUUID();
        String name = user.getName();
        String password = user.getPassword();
        String mail = user.getName();

        String sql = String.format("insert into user value(\'%s\',\'%s\',\'%s\',\'%s\')",uuid,name,password,mail);

        return execSQL(sql);
    }
    /**
     * 获得所有模型
     * @return 执行成功返回true
     */
    public List<Diagram> getAllDiagram(){
        String sql = "SELECT * FROM DIAGRAM";
        //执行语句
        ResultSet rs = execQuery(sql);
        List<Diagram> result = new ArrayList<>();
        try {
            while (rs.next()){
                //每行对应一个用户
                Diagram temp = new Diagram(false);
                //获取并设置信息
                temp.setUUID(rs.getString("UUID"));
                temp.setName(rs.getString("name"));
                //插入链表
                result.add(temp);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 根据用户检索模型
     * @param user 拥有这个模型的用户
     * @return 用户
     */
    public Diagram getDiagram(User user, String name){
        Diagram result = new Diagram(false);
        String sql = String.format("SELECT * FROM DIAGRAM WHERE userid=\'%s\' AND diagramid=\'%s\'",user.getUUID(),name);
        //执行语句
        ResultSet rs = execQuery(sql);
        try {
            if (rs.next()){
                result.setUUID(rs.getString("userid"));
                result.setName(rs.getString("name"));
                result.setPath(rs.getString("path"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (NullPointerException ne){
            result.setUUID("");
            result.setName("");
            result.setPath("");
        }
        return result;
    }
    /**
     * 根据用户和模型增加模型
     * @param user 增加模型的用户
     * @param diagram 增加的模型
     * @return 执行成功返回true
     */
    public boolean addDiagram(User user, Diagram diagram){
        String userid = user.getUUID();
        String diagramid = diagram.getUUID();
        String name = diagram.getName();
        String path = diagram.getPath();

        String sql = String.format("insert into diagram value(\'%s\',\'%s\',\'%s\',\'%s\')",diagramid,userid,name,path);

        return execSQL(sql);
    }
}
