package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.proteanit.sql.*;
import java.sql.*;

public class Department extends JFrame implements ActionListener{
    JTable table;
    JButton back;
    Department(){
        getContentPane().setBackground(Color.WHITE);
       
        
        JLabel l1=new JLabel("Department");
        l1.setBounds(180,10,100,20);
        add(l1);
        
        JLabel l2=new JLabel("Budget");
        l2.setBounds(370,10,100,20);
        add(l2);

        table=new JTable();
        table.setBounds(0,40,700,300);
        add(table);
        
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from department");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        back=new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(280,360,120,30);
        add(back);
        
        setLayout(null);
        setBounds(100,100,700,500);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Reception();
    }
    public static void main(String args[]){
        new Department();
    }
}

