import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

public class Admin extends JFrame{
	ImageIcon usrpic, chusra,recu,reus,upcu,decu,lecu;
	JButton chusral,recul,upcul,decul,lecul,reusl,opt1bt,opt2bt,opt3bt,opt3btd,info1b;
	JLabel usrpp,usrnm, chusrall,recull,upcull,decull,lecull,reusll;
	JTable t1;	
	JPanel p,p2,p3,p4;
	JTextField opt1untf,opt1uttf,opt1pstf,opt3untf;
	JLabel unm, fnm, phn,ps;
	JTextField unmtf, fnmtf, phntf,pstf, info1,info2,info3,info4,info0;
	String []ut;
	JComboBox ty;
	Admin(String usr){
		
		this.setTitle("Billing Managment System | Administrator Panel");
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
		usrnm = new JLabel(usr);
		usrnm.setBounds(408, 75, 100, 100);
		usrnm.setFont(new Font("Serif",Font.BOLD,25));
		usrnm.setForeground(Color.white);
		this.add(usrnm);

		
		reus = new ImageIcon(".\\Icons\\voting.png");
		reusl = new JButton(reus);
		reusl.setBackground(Color.decode("#43A99A"));
		reusl.setBorder(null);
		reusl.setBounds(15, 150, 50, 50);
		reusll = new JLabel("Add a user account");
		reusll.setBounds(85, 150, 120, 50);
		this.add(reusll);
		this.add(reusl);
		addus addusr = new addus();
		reusl.addActionListener(addusr);
		
		
		recu = new ImageIcon(".\\Icons\\register.png");
		recul = new JButton(recu);
		recul.setBackground(Color.decode("#43A99A"));
		recul.setBorder(null);
		recul.setBounds(15, 230, 50, 50);
		recull = new JLabel("Register Customer");
		recull.setBounds(85, 230, 120, 50);
		this.add(recul);
		this.add(recull);
		addcs addcs = new addcs();
		recul.addActionListener(addcs);
		

		
		decu = new ImageIcon(".\\Icons\\deactivate.png");
		decul = new JButton(decu);
		decul.setBackground(Color.decode("#43A99A"));
		decul.setBorder(null);
		decul.setBounds(15, 410, 50, 50);
		decull = new JLabel("Deactivate Account");
		decull.setBounds(85, 410, 120, 50);
		this.add(decul);
		this.add(decull);
		dec decusr = new dec();
		decul.addActionListener(decusr);
		
		lecu = new ImageIcon(".\\Icons\\list.png");
		lecul = new JButton(lecu);
		lecul.setBackground(Color.decode("#43A99A"));
		lecul.setBorder(null);
		lecul.setBounds(15, 500, 50, 50);
		lecull = new JLabel("Cusomer Info");
		lecull.setBounds(85, 500, 120, 50);
		this.add(lecul);
		this.add(lecull);
		lcs lis = new lcs();
		lecul.addActionListener(lis);
		
		p = new JPanel();
		p.setLayout(null);
		p.setBounds(300, 170, 550, 400);
		p.setBackground(Color.WHITE);
		this.add(p);
		
		
		JPanel opt1 = new JPanel();
		opt1.setLayout(null);
		opt1.setBounds(0, 0,550,400);
		
		
		
		
		JLabel opt1un = new JLabel("User Name: ");
		opt1un.setBounds(20,30,100,100);
		opt1.add(opt1un);
		opt1untf = new JTextField();
		opt1untf.setBounds(130, 65, 200, 30);
		opt1untf.setBorder(null);
		opt1.add(opt1untf);
		
		
		
		
		JLabel opt1ut = new JLabel("User Type: ");
		opt1ut.setBounds(20,95,100,100);
		opt1.add(opt1ut);
		
		ut = new String[] {"Select User Type","","Admin","Fr-Desk","DE Clerk"};
		
		ty = new JComboBox(ut);
		ty.setBounds(130, 130, 200, 30);
		opt1.add(ty);
		
		/*opt1uttf = new JTextField();
		opt1uttf.setBounds(130, 130, 200, 30);
		opt1uttf.setBorder(null);
		opt1.add(opt1uttf);*/
		
		JLabel opt1ps = new JLabel("Password: ");
		opt1ps.setBounds(20,160,100,100);
		opt1.add(opt1ps);
		opt1pstf = new JTextField();
		opt1pstf.setBounds(130, 195, 200, 30);
		opt1pstf.setBorder(null);
		opt1.add(opt1pstf);
		opt1bt = new JButton("Add");
		opt1bt.setBorder(null);
		opt1bt.setBackground(Color.decode("#43A99A"));
		opt1bt.setForeground(Color.WHITE);
		opt1bt.setBounds(200,300,200,50);
		opt1.add(opt1bt);
		p.add(opt1);
		p.setVisible(false);
		insu ins = new insu();
		opt1bt.addActionListener(ins);
		
		
		p2 = new JPanel();
		p2.setLayout(null);
		p2.setBounds(300, 170, 550, 400);
		p2.setBackground(Color.white);
		this.add(p2);
		
		JPanel opt2 = new JPanel();
		opt2.setLayout(null);
		opt2.setBounds(0, 0,550,400);
		
		
		unm = new JLabel("User Name: ");
		unm.setBounds(20, 3, 100, 100);
		opt2.add(unm);
		unmtf = new JTextField();
		unmtf.setBounds(100, 35, 200, 30);
		unmtf.setBorder(null);
		opt2.add(unmtf);
		fnm = new JLabel("Full Name: ");
		fnm.setBounds(20,95,100,100);
		opt2.add(fnm);
		fnmtf = new JTextField();
		fnmtf.setBounds(100, 130, 200, 30);
		fnmtf.setBorder(null);
		opt2.add(fnmtf);
		phn = new JLabel("Phone No: ");
		phn.setBounds(20, 195, 100, 100);
		opt2.add(phn);
		phntf = new JTextField();
		phntf.setBounds(100, 230, 200, 30);
		phntf.setBorder(null);
		opt2.add(phntf);
		opt2bt = new JButton("Add");
		opt2bt.setBorder(null);
		opt2bt.setBackground(Color.decode("#43A99A"));
		opt2bt.setForeground(Color.WHITE);
		opt2bt.setBounds(400,160,100,50);
		opt2.add(opt2bt);
		incu cuh = new incu();
		opt2bt.addActionListener(cuh);
		p2.add(opt2);
		
		ps = new JLabel("Password: ");
		ps.setBounds(20, 290, 100, 100);
		opt2.add(ps);
		pstf = new JTextField();
		pstf.setBounds(100, 325, 200, 30);
		pstf.setBorder(null);
		opt2.add(pstf);
		p2.add(opt2);
		p2.setVisible(false);
		
		p3 = new JPanel();
		p3.setLayout(null);
		p3.setBounds(300, 170, 550, 400);
		p3.setBackground(Color.white);
		this.add(p3);
		
		JPanel opt3 = new JPanel();
		opt3.setLayout(null);
		opt3.setBounds(0, 0,550,400);
		
		JLabel opt3un = new JLabel("User ID: ");
		opt3un.setBounds(20,30,100,100);
		opt3.add(opt3un);
		opt3untf = new JTextField();
		opt3untf.setBounds(130, 65, 200, 30);
		opt3untf.setBorder(null);
		opt3.add(opt3untf);
		opt3bt = new JButton("Deactivate");
		opt3bt.setBorder(null);
		opt3bt.setBackground(Color.RED);
		opt3bt.setForeground(Color.WHITE);
		opt3bt.setBounds(100,160,100,50);
		opt3.add(opt3bt);
		opt3btd = new JButton("Activate");
		opt3btd.setBorder(null);
		opt3btd.setBackground(Color.decode("#43A99A"));
		opt3btd.setForeground(Color.WHITE);
		opt3btd.setBounds(250,160,100,50);
		act acctu = new act();
		opt3btd.addActionListener(acctu);
		opt3.add(opt3btd);
		p3.add(opt3);
		p3.setVisible(false);
		dece dc = new dece();
		opt3bt.addActionListener(dc);
		
		p4 = new JPanel();
		p4.setLayout(null);
		p4.setBounds(300, 170, 550, 400);
		p4.setBackground(Color.white);
		this.add(p4);
		
		JPanel opt4 = new JPanel();
		opt4.setLayout(null);
		opt4.setBounds(0, 0,550,400);
		
		info1 = new JTextField();
		info1 = new JTextField();
		info1.setBounds(50, 40, 150, 30);
		info1.setBorder(null);
		info1b = new JButton("Search");
		info1b.setBounds(300, 40, 150, 30);
		opt4.add(info1);
		opt4.add(info1b);
		p4.add(opt4);
		xx x1 = new xx();
		info1b.addActionListener(x1);
		
		info0 = new JTextField();
		info0 = new JTextField();
		info0.setBounds(200, 140, 200, 30);
		info0.setBorder(null);
		info0.setEditable(false);
		info0.setBackground(Color.decode("#43A99A"));
		opt4.add(info0);
		
		info2 = new JTextField();
		info2 = new JTextField();
		info2.setBounds(200, 200, 200, 30);
		info2.setBorder(null);
		info2.setEditable(false);
		info2.setBackground(Color.decode("#43A99A"));
		opt4.add(info2);
		
		info3 = new JTextField();
		info3 = new JTextField();
		info3.setBounds(200, 260, 200, 30);
		info3.setBorder(null);
		info3.setEditable(false);
		info3.setBackground(Color.decode("#43A99A"));
		opt4.add(info3);
		
		info4 = new JTextField();
		info4 = new JTextField();
		info4.setBounds(200, 320, 200, 30);
		info4.setBorder(null);
		info4.setEditable(false);
		info4.setBackground(Color.decode("#43A99A"));
		opt4.add(info4);
		p4.setVisible(false);
		this.setVisible(true);
	}
	public class addus implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("I'm working");
			p.setVisible(true);
			p2.setVisible(false);
			p3.setVisible(false);
			p4.setVisible(false);
			
		}
		
	}
	
	public class addcs implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("I'm working");
			p2.setVisible(true);
			p.setVisible(false);
			p3.setVisible(false);
			p4.setVisible(false);
			
		}
		
	}
	
	public class dec implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			p2.setVisible(false);
			p.setVisible(false);
			p3.setVisible(true);
			p4.setVisible(false);
		}
		
	}
	
	public class lcs implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("I'm working");
			p2.setVisible(false);
			p.setVisible(false);
			p3.setVisible(false);
			p4.setVisible(true);
		}
		
	}
	
	public class insu implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			System.out.println(opt1untf.getText() + ty.getSelectedIndex() + opt1pstf.getText());
			/*exec ver_usr @nameh = 'sa', @uty = 2, @passh = '123'*/
			String conString = "jdbc:sqlserver://DESKTOP-5PUB4T3;Database=BMS;IntegratedSecurity=true";
			try {
				Connection con = DriverManager.getConnection(conString);
				System.out.println("Connection Established");
				Statement stmt = con.createStatement();
				ResultSet rs;  
				int a = 0;
				if(ty.getSelectedIndex() > 1) {
				a = stmt.executeUpdate("exec insert_usr @nameh = '" + opt1untf.getText() +"', @uty = " + ty.getSelectedIndex() + ", @passh = '" + opt1pstf.getText() +"'");
				if(a > 0)
					JOptionPane.showMessageDialog(null, "Successfully Registered  a user");
				else
					JOptionPane.showMessageDialog(null, "Failed to register a user!");
				}else {
					JOptionPane.showMessageDialog(null, "Failed!", "Select one type from the rolls.",JOptionPane.ERROR_MESSAGE);
				}
			}catch(SQLException e) {
				System.out.println("Error Connecting to the database");
				e.printStackTrace();
			
		}
		
	}
	}
	public class incu implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String conString = "jdbc:sqlserver://DESKTOP-5PUB4T3;Database=BMS;IntegratedSecurity=true";
			try {
				Connection con = DriverManager.getConnection(conString);
				System.out.println("Connection Established");
				Statement stmt = con.createStatement();
				ResultSet rs;  
				int a = 0;
				a =stmt.executeUpdate("exec insert_cust @nameh = '" + unmtf.getText() +"', @fnameh = '" + fnmtf.getText() + "',@phn = '" + phntf.getText() + "', @pss = '" + pstf.getText() +"'");
				if(a > 0) {
					JOptionPane.showMessageDialog(null, "Successfully Registered!");
				}else {
					JOptionPane.showMessageDialog(null, "Check If you filled every box correctly.","Failed!",JOptionPane.ERROR_MESSAGE);
				}
			}catch(SQLException e) {
				System.out.println("Error Connecting to the database");
				e.printStackTrace();
			
		}
		
	}
	}
	public class dece implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			System.out.println("am working");
			/*exec ver_usr @nameh = 'sa', @uty = 2, @passh = '123'*/
			String conString = "jdbc:sqlserver://DESKTOP-5PUB4T3;Database=BMS;IntegratedSecurity=true";
			try {
				Connection con = DriverManager.getConnection(conString);
				System.out.println("Connection Established");
				Statement stmt = con.createStatement();
				ResultSet rs;  
				stmt.executeUpdate("exec deac @idd = " + Integer.parseInt(opt3untf.getText()) + "");
				JOptionPane.showMessageDialog(null, "Deactivated Successfuly!");
			}catch(SQLException e) {
				System.out.println("Error Connecting to the database");
				e.printStackTrace();
			
		}
		
	}
	}
	
	public class act implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			/*exec ver_usr @nameh = 'sa', @uty = 2, @passh = '123'*/
			String conString = "jdbc:sqlserver://DESKTOP-5PUB4T3;Database=BMS;IntegratedSecurity=true";
			try {
				Connection con = DriverManager.getConnection(conString);
				System.out.println("Connection Established");
				Statement stmt = con.createStatement();
				ResultSet rs;  
				int a = 0;
				a = stmt.executeUpdate("exec acti @idd = " + Integer.parseInt(opt3untf.getText()) + "");
				System.out.println(a);
				if(a > 0)
					JOptionPane.showMessageDialog(null, "Activated Successfuly!");
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Faild!","Check for your inputs",JOptionPane.ERROR_MESSAGE);
				System.out.println("Error Connecting to the database");
					
			
		}
		
	}
	}
	public class xx implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			System.out.println("act working");
			/*exec ver_usr @nameh = 'sa', @uty = 2, @passh = '123'*/
			String conString = "jdbc:sqlserver://DESKTOP-5PUB4T3;Database=BMS;IntegratedSecurity=true";
			String FullName = "";
			String Phno = "";
			int accs = 0;
			int id = 0;
			try {
				Connection con = DriverManager.getConnection(conString);
				System.out.println("Connection Established");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("Select * from customer where uname = '" + info1.getText()+ "'");
				while(rs.next()) {
					id = rs.getInt("id");
					FullName = rs.getString("full_name");
					Phno = rs.getString("phone_num");
					accs = rs.getInt("account_status");
				info0.setText("ID - " + id);
				info2.setText("Name - " + FullName);
				info3.setText("Phone - "+ Phno);
				info4.setText("Acc. Status " + accs);
				}
			}catch(SQLException e) {
				System.out.println("Error Connecting to the database");
				e.printStackTrace();
			
		}
		
	}
	}
}

