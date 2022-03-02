package client;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame
{
    private String username = "123";
    private String password = "123";

    JFrame window;
    JTextField user;
    JTextField pwd;
    JButton Login;
    JButton register;

    public Login()
    {
        window = new JFrame ("登陆界面");
        window.setLayout(null);
        window.setSize(640,490);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closs

        window.setLayout(null);
        window.setResizable(false);

        JLabel username_label = new JLabel("用户名");
        username_label.setBounds(100,100,100,50);
        window.add(username_label);

        JLabel password_label = new JLabel("密码");
        password_label.setBounds(100,200,100,50);
        window.add(password_label);

        user = new JTextField();
        user.setBounds(150,100,300,50);
        window.add(user);

        pwd = new JPasswordField();
        pwd.setBounds(150,200,300,50);
        window.add(pwd);

        Login = new JButton("登陆");
        Login.setBounds(300,300,100,50);
        window.add(Login);

        register = new JButton("注册");
        register.setBounds(150,300,100,50);
        window.add(register);

        window.setVisible(true);

        this.username=user.getName();

        Login.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String name=user.getText();
                String password=pwd.getText();
                if(password.equals("123"))
                {
//                    System.out.println("登陆成功");
//                    Client_window c1 = new Client_window(name);
//                    window.setVisible(false);
                }
                else
                {
                    System.out.println("密码错误");
                }
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=user.getText();
                String password=pwd.getText();
                //玩家进行注册
                System.out.println(name);
            }
        });

    }





    public static void main(String[] args){
        Login login = new Login();
    }


}
