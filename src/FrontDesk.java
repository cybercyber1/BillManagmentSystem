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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrontDesk extends JFrame{
	JLabel usrpp,usrnm,enbill,chpsll;
	ImageIcon usrpic,chps;
	JButton enbil,chpsl,opt1bt,ser;
	JPanel p;
	JTextField opt1untf,det1,det2,det3;
	FrontDesk(String x){
		this.setTitle("Billing Managment System | Front Desk Panel");
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
		usrnm = new JLabel(x);
		usrnm.setBounds(408, 75, 300, 100);
		usrnm.setFont(new Font("Serif",Font.BOLD,25));
		usrnm.setForeground(Color.white);
		this.add(usrnm);
		
		chps = new ImageIcon(".\\Icons\\update.png");
		chpsl = new JButton(chps);
		chpsl.setBackground(Color.decode("#43A99A"));
		chpsl.setBorder(null);
		chpsl.setBounds(15, 320, 50, 50);
		chpsll = new JLabel("Update Payment Status");
		chpsll.setBounds(85, 320, 120, 50);
		this.add(chpsl);
		this.add(chpsll);
		
		
		p = new JPanel();
		p.setLayout(null);
		p.setBounds(300, 170, 550, 400);
		p.setBackground(Color.WHITE);
		this.add(p);
		
		
		JPanel opt1 = new JPanel();
		opt1.setLayout(null);
		opt1.setBounds(0, 0,550,400);
		p.add(opt1);
		
		JLabel opt1un = new JLabel("User ID: ");
		opt1un.setBounds(20,30,100,100);
		opt1.add(opt1un);
		opt1untf = new JTextField();
		opt1untf.setBounds(130, 65, 200, 30);
		opt1untf.setBorder(null);
		opt1.add(opt1untf);
		
		ser = new JButton("Search");
		ser.setBounds(380, 65, 100, 30);
		ser.setBorder(null);
		ser.setBackground(Color.decode("#43A99A"));
		ser.setForeground(Color.WHITE);
		opt1.add(ser);
		
		displ u = new displ();
		ser.addActionListener(u);
		
		det1 = new JTextField();
		det2 = new JTextField();
		det3 = new JTextField();
		det1.setEditable(false);
		det2.setEditable(false);
		det3.setEditable(false);
		
		det1.setBackground(Color.white);
		det2.setBackground(Color.white);
		det3.setBackground(Color.white);
		
		det1.setBounds(30, 150, 150, 30);
		det2.setBounds(200, 150,150,30);
		det3.setBounds(370,150,150,30);
		det1.setBorder(null);
		det2.setBorder(null);
		det3.setBorder(null);
		opt1.add(det1);
		opt1.add(det2);
		opt1.add(det3);
		
		opt1bt = new JButton("Update");
		opt1bt.setBorder(null);
		opt1bt.setBackground(Color.decode("#43A99A"));
		opt1bt.setForeground(Color.WHITE);
		opt1bt.setBounds(200,300,200,50);
		opt1.add(opt1bt);
		dd h = new dd();
		opt1bt.addActionListener(h);
		
		
		this.setVisible(true);
	}
	public class dd implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String conString = "jdbc:sqlserver://DESKTOP-5PUB4T3;Database=BMS;IntegratedSecurity=true";
			try {
				Connection con = DriverManager.getConnection(conString);
				System.out.println("Connection Established");
				Statement stmt = con.createStatement();
				stmt.executeUpdate("exec pmst @id = " + Integer.parseInt(opt1untf.getText()) + "");
				JOptionPane.showMessageDialog(null, "Successfully Upadate");
			}catch(SQLException k) {
				k.printStackTrace();
				JOptionPane.showMessageDialog(null, "Failed!", "Select one type from the rolls.",JOptionPane.ERROR_MESSAGE);
				
		}
			
		}
		
	}
	
	public class displ implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String conString = "jdbc:sqlserver://DESKTOP-5PUB4T3;Database=BMS;IntegratedSecurity=true";
			try {
				Connection con = DriverManager.getConnection(conString);
				System.out.println("Connection Established");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("exec frfd @idd = " + Integer.parseInt(opt1untf.getText()) + "");
				while(rs.next()) {
					det1.setText(rs.getString("full_name"));
					det2.setText("" + rs.getDouble("totalamount"));
					det3.setText("" + rs.getDouble("paid"));
					int b = rs.getInt("paid");
					if(b == 0)
						det3.setText("Unpaid");
					else
						det3.setText("Paid");
				}
			}catch(SQLException k) {
				k.getSuppressed();
				
		}
			
		}
		
	}
}
