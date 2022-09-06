package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;
import java.awt.event.*;

public class Checkout extends JFrame implements ActionListener {

    Choice ccustomer;
    JLabel lblroomnumber, lblcheckintime, lblcheckouttime;
    JButton checkout, back;

    Checkout() {
        getContentPane().setBackground(Color.WHITE);

        JLabel text = new JLabel("Checkout");
        text.setBounds(100, 20, 100, 30);
        text.setForeground(Color.BLUE);
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(text);

        JLabel lblid = new JLabel("Customer Id");
        lblid.setBounds(30, 80, 100, 30);
        add(lblid);

        ccustomer = new Choice();
        ccustomer.setBounds(150, 80, 125, 25);
        add(ccustomer);

        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 130, 100, 30);
        add(lblroom);

        lblroomnumber = new JLabel();
        lblroomnumber.setBounds(150, 130, 100, 30);
        add(lblroomnumber);

        JLabel lblcheckin = new JLabel("Checkin Time");
        lblcheckin.setBounds(30, 180, 100, 30);
        add(lblcheckin);

        lblcheckintime = new JLabel();
        lblcheckintime.setBounds(150, 180, 100, 30);
        add(lblcheckintime);

        JLabel lblcheckout = new JLabel("Checkout Time");
        lblcheckout.setBounds(30, 230, 100, 30);
        add(lblcheckout);

        Date date = new Date();
        lblcheckouttime = new JLabel("" + date);
        lblcheckouttime.setBounds(150, 230, 170, 30);
        add(lblcheckouttime);

        checkout = new JButton("Checkout");
        checkout.setBounds(30, 280, 100, 30);
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.addActionListener(this);
        add(checkout);

        back = new JButton("Back");
        back.setBounds(150, 280, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/checkout.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380, 20, 400, 300);
        add(image);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                ccustomer.add(rs.getString("number"));
                lblroomnumber.setText(rs.getString("room"));
                lblcheckintime.setText(rs.getString("time"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(null);
        setBounds(150, 100, 800, 400);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == checkout) {
            String query1 = "delete from customer where number='" + ccustomer.getSelectedItem() + "'";
            String query2 = "update rooms set availability='Availabel' where roomnumber='" + lblroomnumber.getText() + "'";

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Checkout Successfully");
                setVisible(false);
                new Reception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String args[]) {
        new Checkout();
    }
}
