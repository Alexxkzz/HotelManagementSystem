
package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class AddCustomer extends JFrame implements ActionListener{
    JComboBox comboid;
    JTextField tfnumber,tfname,tfcountry,tfdeposit;
    JRadioButton male,female;
    Choice croom;
    JLabel checkintime;
    JButton submit,cancel;
    
    AddCustomer(){
        setLayout(null);
        
        JLabel heading=new JLabel("New Customer Form");
        heading.setBounds(50,10,200,40);
        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        JLabel lblid=new JLabel("ID");
        lblid.setBounds(10,80,160,30);
        lblid.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(lblid);
        
        String idarray[]={"Passport","Adhar Card","Driving Licence","Voter ID"};
         comboid=new JComboBox(idarray);
        comboid.setBounds(190,80,160,30);
        add(comboid);
        
        JLabel lblnumber=new JLabel("Number");
        lblnumber.setBounds(10,130,160,30);
        lblnumber.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(lblnumber);
        
        tfnumber=new JTextField();
        tfnumber.setBounds(190,130,160,30);
        add(tfnumber);
        
        JLabel lblname=new JLabel("Name");
        lblname.setBounds(10,180,160,30);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(lblname);
        
        tfname=new JTextField();
        tfname.setBounds(190,180,160,30);
        add(tfname);
        
        JLabel lblgender=new JLabel("Gender");
        lblgender.setBounds(10,230,160,30);
        lblgender.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(lblgender);
                
        male=new JRadioButton("Male");
        male.setBounds(190,230,80,30);
        male.setBackground(Color.WHITE);
        male.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(male);
              
        female=new JRadioButton("Female");
        female.setBounds(270,230,80,30);
        female.setBackground(Color.WHITE);
        female.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(female);
        
        ButtonGroup gen=new ButtonGroup();
        gen.add(male);
        gen.add(female);
        
         JLabel lblcountry=new JLabel("Country");
        lblcountry.setBounds(10,280,160,30);
        lblcountry.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(lblcountry);
        
        tfcountry=new JTextField();
        tfcountry.setBounds(190,280,160,30);
        add(tfcountry);
        
        JLabel lblallocated=new JLabel("Room No");
        lblallocated.setBounds(10,330,160,30);
        lblallocated.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(lblallocated);
        
        croom=new Choice();
        
        try{
            Conn conn=new Conn();
            String query="select * from rooms";
            ResultSet rs=conn.s.executeQuery(query);
            while(rs.next()){
                croom.add(rs.getString("roomnumber"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        croom.setBounds(190,330,160,30);
        add(croom);
        
        JLabel lbltime=new JLabel("Check");
        lbltime.setBounds(10,380,160,30);
        lbltime.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(lbltime);
        
        Date date=new Date();
        
         checkintime=new JLabel("" + date);
        checkintime.setBounds(190,380,180,30);
        checkintime.setFont(new Font("Tahoma",Font.PLAIN,13));
        add(checkintime);
        
        JLabel lbldeposit=new JLabel("Deposit");
        lbldeposit.setBounds(10,430,160,30);
        lbldeposit.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(lbldeposit);
        
        tfdeposit=new JTextField();
        tfdeposit.setBounds(190,430,160,30);
        add(tfdeposit);
        
        submit=new JButton("Add");
        submit.setBounds(10,490,150,30);
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLACK);
        submit.addActionListener(this);
        add(submit);
        
        cancel=new JButton("Back");
        cancel.setBounds(180,490,150,30);
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/addcustomer.jpg"));
        Image i2=i1.getImage().getScaledInstance(500,400, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(350,80,500,400);
        add(image);
        
        getContentPane().setBackground(Color.WHITE);
        setBounds(250,100,850,610);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==submit){
            String id=(String) comboid.getSelectedItem();
            String number=tfnumber.getText();
            String name=tfname.getText();
            String gender="";
            if(male.isSelected()){
                gender="male";
            }else if(female.isSelected()){
                gender="female";
            }
            String country=tfcountry.getText();
            String room=croom.getSelectedItem();
            String time=checkintime.getText();
            String deposit=tfdeposit.getText();
            
            try{
                Conn c=new Conn();
                String query="insert into customer values ('"+id+"','"+number+"','"+name+"','"+gender+"','"+country+"','"+room+"','"+time+"','"+deposit+"' )";
                String query2="update rooms set availability ='Occupied' where roomnumber ='"+room+"'";
                c.s.executeUpdate(query);
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "New Customer Added Succesfully");
                setVisible(false);
                new Reception().setVisible(true);
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource()==cancel){
            setVisible(false);
            new Reception().setVisible(true);
        }
    }
    public static void main(String args[]){
    new AddCustomer();
    }
}
