package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.proteanit.sql.*;
import java.sql.*;

public class Room extends JFrame implements ActionListener{
    JTable table;
    JButton back;
    Room(){
        getContentPane().setBackground(Color.WHITE);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/room.jpg"));
        Image i2=i1.getImage().getScaledInstance(500, 450, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(450,40,500,450);
        add(image);
        
        JLabel l1=new JLabel("Room Number");
        l1.setBounds(0,10,100,20);
        add(l1);
        
        JLabel l2=new JLabel("Availability");
        l2.setBounds(100,10,100,20);
        add(l2);
        
        JLabel l3=new JLabel("Status");
        l3.setBounds(200,10,100,20);
        add(l3);
        
        JLabel l4=new JLabel("Price");
        l4.setBounds(300,10,100,20);
        add(l4);
        
        JLabel l5=new JLabel("Bed Types");
        l5.setBounds(400,10,100,20);
        add(l5);
        
        table=new JTable();
        table.setBounds(0,40,500,400);
        add(table);
        
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from rooms");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        back=new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(200,500,120,30);
        add(back);
        
        setLayout(null);
        setBounds(150,100,1000,600);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Reception();
    }
    public static void main(String args[]){
        new Room();
    }
}
