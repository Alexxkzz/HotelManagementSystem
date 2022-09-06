package hotel.management.system;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateRoom extends JFrame implements ActionListener{
    Choice ccustomer;
    JTextField tfroom,tfavailabel,tfstatus,tfpaid,tfpending;
    JButton check,update,back;
    UpdateRoom(){
        getContentPane().setBackground(Color.WHITE);
        
        JLabel text=new JLabel("Update Room Status");
        text.setBounds(30,20,250,30);
        text.setBackground(Color.WHITE);
        text.setFont(new Font("Tahoma",Font.PLAIN,25));
        text.setForeground(Color.BLUE);
        add(text);
        
        JLabel lblid=new JLabel("Customer ID");
        lblid.setBounds(30,80,100,20);   
        add(lblid);
        
        ccustomer=new Choice();
        ccustomer.setBounds(200,80,125,25);
        add(ccustomer);
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from customer");
            while(rs.next()){
                ccustomer.add(rs.getString("number"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JLabel lblroom=new JLabel("Room Number");
        lblroom.setBounds(30,120,100,20);   
        add(lblroom);
        
        tfroom=new JTextField();
        tfroom.setBounds(200,120,150,25);
        add(tfroom);
        
        JLabel lblname=new JLabel("Availability");
        lblname.setBounds(30,160,100,20);   
        add(lblname);
        
        tfavailabel=new JTextField();
        tfavailabel.setBounds(200,160,150,25);
        add(tfavailabel);
        
        JLabel lblclean=new JLabel("Cleaning Status");
        lblclean.setBounds(30,200,100,20);   
        add(lblclean);
        
        tfstatus=new JTextField();
        tfstatus.setBounds(200,200,150,25);
        add(tfstatus);

        check=new JButton("Check");
        check.setBounds(30,300,100,30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.addActionListener(this);
        add(check);
        
        update=new JButton("Update");
        update.setBounds(150,300,100,30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);
        
        back=new JButton("Back");
        back.setBounds(270,300,100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/hotelRoom.jpg"));
        Image i2=i1.getImage().getScaledInstance(400,300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(500,80,400,300);
        add(image);
        
        setBounds(150,100,980,500);
        setLayout(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==check){
            String id=ccustomer.getSelectedItem();
            String query="select * from customer where number='"+id+"'";
            try{
                Conn c=new Conn();
                ResultSet rs=c.s.executeQuery(query);
                while(rs.next()){
                    tfroom.setText(rs.getString("room"));                   
                }
                
                ResultSet rs2=c.s.executeQuery("select * from rooms where roomnumber ='"+tfroom.getText()+"'");
                while(rs2.next()){
                  tfavailabel.setText(rs2.getString("availability"));
                  tfstatus.setText(rs2.getString("cleaning"));
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()==update){
            String number=ccustomer.getSelectedItem();
            String room=tfroom.getText();
            String availabel=tfavailabel.getText();
            String status=tfstatus.getText();
      
            try{
                Conn c=new Conn();
                c.s.executeUpdate("update rooms set availability='"+availabel+"',cleaning='"+status+"' where roomnumber='"+room+"'");
                JOptionPane.showMessageDialog(null,"Data Updated Succesfully");
                setVisible(false);
                new Reception();
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()==back){
            setVisible(false);
            new Reception();
        }              
    }
    public static void main(String args[]){
        new UpdateRoom();
    }
}
