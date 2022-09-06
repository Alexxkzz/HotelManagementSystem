package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddRooms extends JFrame implements ActionListener{
    JButton addroom,cancel;
    JTextField tfroom,tfprice;
    JComboBox comboavailable,comboacleaning,combobed;
    AddRooms(){
        setLayout(null);
        
        JLabel heading=new JLabel("ADD ROOMS");
        heading.setFont(new Font("Tahoma",Font.BOLD,18));
        heading.setBounds(150,20,200,20);
        add(heading);
        
        JLabel lblroom=new JLabel("Room Number");
        lblroom.setBounds(60,80,120,30);
        lblroom.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lblroom);
        
         tfroom=new JTextField();
        tfroom.setBounds(200,80,150,30);
        add(tfroom);
        
        JLabel lblavailable=new JLabel("Available");
        lblavailable.setBounds(60,130,130,30);
        lblavailable.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lblavailable);
                
        String availableOptions[]={"Available","Occupied"};
         comboavailable=new JComboBox(availableOptions);
        comboavailable.setBounds(200,130,150,30);
        comboavailable.setBackground(Color.WHITE);
        add(comboavailable);
        
        JLabel lblcleaning=new JLabel("Cleaning Status");
        lblcleaning.setBounds(60,180,130,30);
        lblcleaning.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lblcleaning);
                
        String cleaningOptions[]={"Dirty","Clean"};
        comboacleaning=new JComboBox(cleaningOptions);
        comboacleaning.setBounds(200,180,150,30);
        comboacleaning.setBackground(Color.WHITE);
        add(comboacleaning);
        
         JLabel lblprice=new JLabel("Price");
        lblprice.setBounds(60,230,120,30);
        lblprice.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lblprice);
        
         tfprice=new JTextField();
        tfprice.setBounds(200,230,150,30);
        add(tfprice);
        
        JLabel lblbedtype=new JLabel("Bed Type");
        lblbedtype.setBounds(60,280,130,30);
        lblbedtype.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lblbedtype);
                
        String bedOptions[]={"Single","Double"};
         combobed=new JComboBox(bedOptions);
        combobed.setBounds(200,280,150,30);
        combobed.setBackground(Color.WHITE);
        add(combobed);
        
         addroom=new JButton("Add Room");
        addroom.setBounds(60,350,100,30);
        addroom.setBackground(Color.BLACK);
        addroom.setForeground(Color.WHITE);
        add(addroom);
        addroom.addActionListener(this);
        
         cancel=new JButton("Cancel");
        cancel.setBounds(200,350,100,30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        add(cancel);
        cancel.addActionListener(this);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/hotelRoom.jpg"));
        Image i2=i1.getImage().getScaledInstance(400,300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(400,80,400,300);
        add(image);
        
        
        getContentPane().setBackground(Color.WHITE);
        setBounds(250,100,850,540);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==addroom){
            String roomnumber=tfroom.getText();
            String price=tfprice.getText();
            String availability=(String) comboavailable.getSelectedItem();
            String cleaning=(String) comboacleaning.getSelectedItem();
            String bed=(String) combobed.getSelectedItem();
            
            try{
                Conn c=new Conn();
                String query="insert into rooms values('"+roomnumber+"','"+price+"','"+availability+"','"+cleaning+"','"+bed+"')";
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null,"Room Succesfully added");
                setVisible(false);
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource()==cancel){
            setVisible(false);
           
        }
    }
    public static void main(String args[]){
        new AddRooms();
    }
}
