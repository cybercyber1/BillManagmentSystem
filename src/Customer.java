import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Customer extends JFrame{
	JLabel usrpp,usrnm,viewll,comll,printll;
	ImageIcon usrpic,view,com,print;
	JButton viewl,printl,coml,calc,pay;
	int csid;
	JPanel p;
	JTextField tot,tot1,tot2,tot3;
	int fs = 0;
	int sc = 0;
	int th = 0;
	int total = 0;
	Customer(int csid){
		this.csid = csid;
		this.setTitle("Billing Managment System | Customer Panel");
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
		usrnm = new JLabel("usr_id = " + csid);
		usrnm.setBounds(408, 75, 300, 100);
		usrnm.setFont(new Font("Serif",Font.BOLD,25));
		usrnm.setForeground(Color.white);
		this.add(usrnm);
		
		
		view = new ImageIcon(".\\Icons\\view.png");
		viewl = new JButton(view);
		viewl.setBackground(Color.decode("#43A99A"));
		viewl.setBorder(null);
		viewl.setBounds(15, 200, 50, 50);
		viewll = new JLabel("This Month Bill");
		viewll.setBounds(85, 200, 120, 50);
		this.add(viewll);
		this.add(viewl);
		cl j = new cl();
		viewl.addActionListener(j);
		
		p = new JPanel();
		p.setLayout(null);
		p.setBounds(300, 170, 550, 400);
		p.setBackground(Color.WHITE);
		this.add(p);
		
		
		
		
		JPanel opt1 = new JPanel();
		opt1.setLayout(null);
		opt1.setBounds(0, 0,550,400);
		p.add(opt1);
		
		tot = new JTextField();
		tot.setBounds(200, 50, 200, 30);
		tot.setBorder(null);
		tot.setEditable(false);
		tot.setBackground(Color.decode("#43A99A"));
		opt1.add(tot);
		
		tot1 = new JTextField();
		tot1.setBounds(200, 100, 200, 30);
		tot1.setBorder(null);
		tot1.setEditable(false);
		tot1.setBackground(Color.decode("#43A99A"));
		opt1.add(tot1);
		
		tot2 = new JTextField();
		tot2.setBounds(200, 150, 200, 30);
		tot2.setBorder(null);
		tot2.setEditable(false);
		tot2.setBackground(Color.decode("#43A99A"));
		opt1.add(tot2);
		
		tot3 = new JTextField();
		tot3.setBounds(200, 200, 200, 30);
		tot3.setBorder(null);
		tot3.setEditable(false);
		tot3.setBackground(Color.decode("#43A99A"));
		opt1.add(tot3);
		
		calc = new JButton("Calculate");
		pay = new JButton("Pay");
		calc.setBorder(null);
		calc.setBackground(Color.decode("#43A99A"));
		calc.setForeground(Color.WHITE);
		calc.setBounds(50,300,200,50);
		opt1.add(calc);
		
		pay.setBorder(null);
		pay.setBackground(Color.decode("#43A99A"));
		pay.setForeground(Color.WHITE);
		pay.setBounds(320,300,200,50);
		opt1.add(pay);
		p.setVisible(false);
		cc y = new cc();
		pay.addActionListener(y);
		bb x = new bb();
		calc.addActionListener(x);
		
		
		this.setVisible(true);
	}
	
	
	public class bb implements ActionListener{
		LocalDate today = LocalDate.now();
		int month = today.getMonthValue();
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String conString = "jdbc:sqlserver://DESKTOP-5PUB4T3;Database=BMS;IntegratedSecurity=true";
			try {
				
				Connection con = DriverManager.getConnection(conString);
				System.out.println("Connection Established");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("exec calsi @id =" + csid + ", @st = 1, @pmo = " + month);
				while(rs.next()) {
				fs = rs.getInt("amount");
				System.out.println(fs);
				tot.setText("Electricity - " + fs);
				}
				rs = stmt.executeQuery("exec calsi @id =" + csid + ", @st = 2, @pmo = " + month);
				while(rs.next()) {
					sc = rs.getInt("amount");
					System.out.println(sc);
					tot1.setText("Water - " + sc);
					}
				rs = stmt.executeQuery("exec calsi @id =" + csid + ", @st = 3, @pmo = " + month);
				while(rs.next()) {
					th = rs.getInt("amount");
					System.out.println(th);
					tot2.setText("Land Line - " + th);
					}
				total = fs + sc + th;
				tot3.setText("TOTAL - " + total);
				stmt.executeUpdate("exec insgenb @id = " + csid + ",@mn = " + month + ",@ttam = " + total + "");
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(null, "Already Calculated");
				e.printStackTrace();
		}
		
	}
}
	public class cc implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			pay.setText("Paid!");
			String conString = "jdbc:sqlserver://DESKTOP-5PUB4T3;Database=BMS;IntegratedSecurity=true";
			try {
				Connection con = DriverManager.getConnection(conString);
				System.out.println("Connection Established");
				Statement stmt = con.createStatement();
				stmt.executeUpdate("exec pmst @id = " + csid + "");
				
				
				
			}catch(SQLException e) {
				System.out.println("Error Connecting to the database");
				e.printStackTrace();
		}
		

			
		}
		
	}
	
	public class cl implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
		p.setVisible(true);
		}
		
	}
}
