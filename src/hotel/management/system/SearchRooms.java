package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.proteanit.sql.*;
import java.sql.*;

public class SearchRooms extends JFrame implements ActionListener{
    JTable table;
    JButton back,submit;
    JComboBox bedType;
    JCheckBox available;
    SearchRooms(){
        getContentPane().setBackground(Color.WHITE);
        
        JLabel text=new JLabel("Search for Room");
        text.setFont(new Font("Tahoma",Font.BOLD,20));
        text.setBounds(400,20,200,30);
        add(text);
        
        JLabel lblbed=new JLabel("Bed Type");
        lblbed.setBounds(50,70,100,20);
        add(lblbed);
        
        bedType=new JComboBox(new String[]{"Single","Double"});
        bedType.setBounds(150,70,150,25);
        bedType.setBackground(Color.WHITE);
        add(bedType);
        
        available =new JCheckBox("Only Display Available");
        available.setBounds(650,70,150,25);
        available.setBackground(Color.WHITE);
        add(available);        
        
        JLabel l1=new JLabel("Room Number");
        l1.setBounds(50,170,100,20);
        add(l1);
        
        JLabel l2=new JLabel("Price");
        l2.setBounds(270,170,100,20);
        add(l2);
        
        JLabel l3=new JLabel("Availability");
        l3.setBounds(450,170,100,20);
        add(l3);
        
        JLabel l4=new JLabel("Status");
        l4.setBounds(670,170,100,20);
        add(l4);
        
        JLabel l5=new JLabel("Bed Types");
        l5.setBounds(870,170,100,20);
        add(l5);
        
        table=new JTable();
        table.setBounds(0,200,1000,300);
        add(table);
        
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from rooms");
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
                String q1="select * from rooms where bed ='"+bedType.getSelectedItem()+"'";
                String q2="select * from rooms where availability='Available' and bed ='"+bedType.getSelectedItem()+"'";
                Conn c=new Conn();
                ResultSet rs;
                if(available.isSelected()){
                    rs=c.s.executeQuery(q2);
                }else{
                     rs=c.s.executeQuery(q1);
                }
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
        new SearchRooms();
    }
}
