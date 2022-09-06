package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JTextField userName,password;
    JButton login,cancel;
    Login(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel user=new JLabel("User:");
        user.setBounds(40,20,100,30);
        add(user);
        
        JLabel pass=new JLabel("Password:");
        pass.setBounds(40,70,100,30);
        add(pass);
        
        userName=new JTextField();
        userName.setBounds(160,20,150,30);
        add(userName);
        
        password=new JTextField();
        password.setBounds(160,70,150,30);
        add(password);
        
        login=new JButton("Login");
        login.setBounds(40,150,130,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        add(login);
        login.addActionListener(this);
        
        cancel=new JButton("Cancel");
        cancel.setBounds(180,150,130,30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        add(cancel);
        cancel.addActionListener(this);
        
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/profilelogo.jpg"));
        Image i2=i1.getImage().getScaledInstance(230,230 , Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(330,0,230,230);
        add(image);
        setBounds(500,200,600,300);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== login){
            String username=userName.getText();
            String pw=password.getText();
            
            try{
                Conn c=new Conn();
                
                String query="select * from login where username='"+username+"'and password='"+pw+"'";
                ResultSet rs=c.s.executeQuery(query);
                
                if(rs.next()){
                    setVisible(false);
                    new Dashboard();
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    setVisible(false);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()==cancel){
            setVisible(false);
        }
    }
    public static void main(String args[]){
        new Login();
    }
}
