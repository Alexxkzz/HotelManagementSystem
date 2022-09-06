package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.proteanit.sql.*;
import java.sql.*;

public class Pickup extends JFrame implements ActionListener{
    JTable table;
    JButton back,submit;
    Choice carType;
    JCheckBox available;
    Pickup(){
        getContentPane().setBackground(Color.WHITE);
        
        JLabel text=new JLabel("Pickup Service");
        text.setFont(new Font("Tahoma",Font.BOLD,20));
        text.setBounds(400,20,200,30);
        add(text);
        
        JLabel lblbed=new JLabel("Type Of Car");
        lblbed.setBounds(50,70,100,20);
        add(lblbed);
        
        carType=new Choice();
        carType.setBounds(150,70,150,25);
        carType.setBackground(Color.WHITE);
        add(carType);
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from drivers");
            while(rs.next()){
                carType.add(rs.getString("carmodel"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel l1=new JLabel("Driver Name");
        l1.setBounds(50,170,100,20);
        add(l1);
        
        JLabel l2=new JLabel("Age");
        l2.setBounds(220,170,100,20);
        add(l2);
        
        JLabel l3=new JLabel("Gender");
        l3.setBounds(390,170,100,20);
        add(l3);
        
        JLabel l4=new JLabel("Carcompany");
        l4.setBounds(540,170,100,20);
        add(l4);
        
        JLabel l5=new JLabel("Carmodel");
        l5.setBounds(730,170,100,20);
        add(l5);
        
         JLabel l6=new JLabel("Availabel");
        l6.setBounds(880,170,100,20);
        add(l6);
        
        table=new JTable();
        table.setBounds(0,200,1000,300);
        add(table);
        
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from drivers");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        submit=new JButton("Search");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setBounds(300,500,120,30);
        add(submit);
        
        back=new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(550,500,120,30);
        add(back);
        
        setLayout(null);
        setBounds(150,100,1000,600);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==submit){
            try{
                String q1="select * from drivers where carmodel ='"+carType.getSelectedItem()+"'";
                
                Conn c=new Conn();
                ResultSet rs;
                rs=c.s.executeQuery(q1);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            setVisible(false);
            new Reception();
        }
        
    }
    public static void main(String args[]){
        new Pickup();
    }
}
