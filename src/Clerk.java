import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Clerk extends JFrame{
	JLabel usrpp,usrnm,enbill,upbill;
	ImageIcon usrpic,enbi,upbi;
	JButton enbil,upbil,opt2bt;
	JPanel p2;
	JTextField cust_idtf,  pmonthtf,ustf;
	JComboBox servtytf;
	String[] serv;
	Clerk(String nm){
		this.setTitle("Billing Managment System | Clerk Panel");
		Image icon = Toolkit.getDefaultToolkit().getImage(".\\Icons\\invoice.png");
		this.setIconImage(icon);
		this.setResizable(false);
		this.setSize(900,650);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choose = JOptionPane.showConfirmDialog(null,
                        "Do you really want to exit the application ?",
                        "Confirm Close", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (choose == JOptionPane.YES_OPTION) {
                    e.getWindow().dispose();
                    System.out.println("close");
                  
                } else {
                    System.out.println("do nothing");
                }
            }
        });
		this.setLayout(null);
		this.getContentPane().setBackground(Color.decode("#43A99A"));
		
		usrpic = new ImageIcon(".\\Icons\\user.png");
		usrpp = new JLabel(usrpic);
		usrpp.setBounds(400, 10, 100, 100);
		this.add(usrpp);
		usrnm = new JLabel(nm);
		usrnm.setBounds(408, 75, 300, 100);
		usrnm.setFont(new Font("Serif",Font.BOLD,25));
		usrnm.setForeground(Color.white);
		this.add(usrnm);
		
		enbi = new ImageIcon(".\\Icons\\write.png");
		enbil = new JButton(enbi);
		enbil.setBackground(Color.decode("#43A99A"));
		enbil.setBorder(null);
		enbil.setBounds(15, 200, 50, 50);
		enbill = new JLabel("Enter Bill Info");
		enbill.setBounds(85, 200, 120, 50);
		this.add(enbill);
		this.add(enbil);
		
		
		p2 = new JPanel();
		p2.setLayout(null);
		p2.setBounds(300, 170, 550, 400);
		p2.setBackground(Color.white);
		this.add(p2);
		
		JPanel opt2 = new JPanel();
		opt2.setLayout(null);
		opt2.setBounds(0, 0,550,400);
		
		JLabel cust_id, servty, pmonth,us,rate;
		
		cust_id = new JLabel("User ID: ");
		cust_id.setBounds(20, 3, 100, 100);
		opt2.add(cust_id);
		cust_idtf = new JTextField();
		cust_idtf.setBounds(100, 35, 200, 30);
		cust_idtf.setBorder(null);
		opt2.add(cust_idtf);
		servty = new JLabel("Service Type: ");
		servty.setBounds(20,95,100,100);
		opt2.add(servty);
		serv = new String[] {"Select Service Type","Electricity","Water","Land-Line"};
		servtytf = new JComboBox(serv);
		servtytf.setBounds(100, 130, 200, 30);
		servtytf.setBorder(null);
		opt2.add(servtytf);
		pmonth = new JLabel("Month: ");
		pmonth.setBounds(20, 195, 100, 100);
		opt2.add(pmonth);
		pmonthtf = new JTextField();
		pmonthtf.setBounds(100, 230, 200, 30);
		pmonthtf.setBorder(null);
		opt2.add(pmonthtf);
		
		us = new JLabel("Usage: ");
		us.setBounds(20, 290, 100, 100);
		opt2.add(us);
		ustf = new JTextField();
		ustf.setBounds(100, 325, 200, 30);
		ustf.setBorder(null);
		opt2.add(ustf);
		p2.add(opt2);
		p2.setVisible(true);
		opt2bt = new JButton("Add");
		opt2bt.setBorder(null);
		opt2bt.setBackground(Color.decode("#43A99A"));
		opt2bt.setForeground(Color.WHITE);
		opt2bt.setBounds(400,160,100,50);
		opt2.add(opt2bt);
		tt hand = new tt();
		opt2bt.addActionListener(hand);
		this.setVisible(true);
	}
	
	public class tt implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			double elect = 6.00;
			double water = 3.0;
			double landl = 0.50;
			double rate;
			if(servtytf.getSelectedIndex() == 1) {
				rate = 6.0;
			}
			else if(servtytf.getSelectedIndex() == 2) {
				rate = 3.0;
			}
			else if(servtytf.getSelectedIndex() == 3) {
				rate = 0.50;
			}else
				rate = 0;
			System.out.println("act working");
			String conString = "jdbc:sqlserver://DESKTOP-5PUB4T3;Database=BMS;IntegratedSecurity=true";
			try {
				Connection con = DriverManager.getConnection(conString);
				System.out.println("Connection Established");
				Statement stmt = con.createStatement();
				ResultSet rs;  
				if(servtytf.getSelectedIndex() > 0) {
					stmt.executeUpdate("exec insbill @id =" + Integer.parseInt(cust_idtf.getText()) + ", @sty = " + servtytf.getSelectedIndex() + ", @mnth = " + Integer.parseInt(pmonthtf.getText()) + ", @usg =" + Integer.parseInt(ustf.getText()) +",  @rate = " + rate + ",@amnt = " + (rate * Integer.parseInt(ustf.getText())) +"");
					JOptionPane.showMessageDialog(null, "Added Successfully!");
				}else
					JOptionPane.showMessageDialog(null, "Select the service type.");
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(null, "User Doesn't exist","Faild!",JOptionPane.ERROR_MESSAGE);
				System.out.println("Error Connecting to the database");
				e.printStackTrace();
		}	
			
		//insbill @id int,@sty int,@mnth int,@usg float,@rate float,@amnt float	
		}
		
	}
}
