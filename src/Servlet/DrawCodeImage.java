package Servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class DrawCodeImage extends HttpServlet {
    private static final int WIDTH = 120;//生成的图片的宽度
    private static final int HEIGHT = 30;//生成的图片的高度

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
     }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String drawCodeImage = req.getParameter("drawCodeImage");//接收页面传递的drawCodeImage标识
        //1.在内存中创建一张图片
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
        //2.得到图片
        Graphics g = bi.getGraphics();
        //3.设置图片的背影色
        setBackGround(g);
        //4.设置图片的边框
        setBorder(g);
        //5.在图片上画干扰线
        drawRandomLine(g);
        //6.写在图片上随机数
        //String random = drawRandomNum((Graphics2D) g,"nl");//生成数字和字母组合的验证码图片
        //String random = drawRandomNum((Graphics2D) g,"n");//生成纯数字的验证码图片
        //String random = drawRandomNum((Graphics2D) g,"l");//生成纯字母的验证码图片
        String random = drawRandomNum((Graphics2D) g,drawCodeImage);//根据客户端传递的drawCodeImage标识生成验证码图片
        //7.将随机数存在session中
        req.getSession().setAttribute("checkcode", random);
        //8.设置响应头通知浏览器以图片的形式打开
        resp.setContentType("image/jpeg");//等同于response.setHeader("Content-Type", "image/jpeg");
        //9.设置响应头控制浏览器不要缓存
        resp.setDateHeader("expries", -1);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");
        //10.将图片写给浏览器
        ImageIO.write(bi, "jpg", resp.getOutputStream());
    }

    /**
     * 设置图片的背景色
     * @param g 设置背景的图像
     */
    private void setBackGround(Graphics g) {
        // 设置颜色
        g.setColor(Color.WHITE);
        // 填充区域
        g.fillRect(0, 0, WIDTH, HEIGHT);
     }

    /**
     * 设置图片的边框
     * @param g 设置边框的图像
     */
    private void setBorder(Graphics g) {
        // 设置边框颜色
        g.setColor(Color.BLUE);
        // 边框区域
        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
    }

    /**
     * 在图片上画随机线条
     * @param g 画随机线条的图像
     */
    private void drawRandomLine(Graphics g) {
        // 设置颜色
        g.setColor(Color.GREEN);
        // 设置线条个数并画线
        for (int i = 0; i < 7; i++) {
            int x1 = new Random().nextInt(WIDTH);
            int y1 = new Random().nextInt(HEIGHT);
            int x2 = new Random().nextInt(WIDTH);
            int y2 = new Random().nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /*** 画随机字符
     * @param g 画随机字符的图像
     * @param drawCodeImage 随机字符的种类
     * @return 成功返回创建的随机字符，失败返回空字符串
     */
    private String drawRandomNum(Graphics2D g,String drawCodeImage) {
        // 设置颜色
        g.setColor(Color.RED);
        // 设置字体
        g.setFont(new Font("宋体", Font.BOLD, 20));
        //数字和字母的组合
        String baseNumLetter = "0123456789ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        //纯数字
        String baseNum = "0123456789";
        //纯字母
        String baseLetter = "ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        //createTypeFlag[0]==null表示没有传递参数
        if (null != drawCodeImage) {
            if (drawCodeImage.equals("nl")) {
                // 截取汉字
                return createRandomChar(g, baseNumLetter);
            }else if (drawCodeImage.equals("n")) {
                // 截取数字和字母的组合
                return createRandomChar(g, baseNum);
            }else if (drawCodeImage.equals("l")) {
                // 截取数字
                return createRandomChar(g, baseLetter);
            }
        }else {
            // 默认截取数字和字母的组合
            return createRandomChar(g, baseNumLetter);
        }
        return "";
    }

    /**
     * 创建随机字符
     * @param g 创建随机字符的图像
     * @param baseChar 用来创建图像的字符集
     * @return 随机字符
     */
    private String createRandomChar(Graphics2D g,String baseChar) {
        StringBuffer sb = new StringBuffer();
        int x = 5;
        String ch ="";
        // 控制字数
        for (int i = 0; i < 4; i++) {
            // 设置字体旋转角度
            int degree = new Random().nextInt() % 30;
            ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
            sb.append(ch);
            // 正向角度
            g.rotate(degree * Math.PI / 180, x, 20);
            g.drawString(ch, x, 20);
            // 反向角度
            g.rotate(-degree * Math.PI / 180, x, 20);
            x += 30;
        }
        return sb.toString();
    }
}
