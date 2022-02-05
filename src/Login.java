import java.awt.*;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{
	ImageIcon logo = new ImageIcon();
	JLabel title,usrnm,passwd, usrtype,pro1,pro2,loha;
	JTextField un;
	JPasswordField psswd;
	JButton submit;
	String ut[];
	JComboBox ty;
	Login(){
		this.setTitle("Billing Managment System | Login");
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
		/*Pay your bills in one place.*/
		logo = new ImageIcon(".\\Icons\\logo.png");
		loha = new JLabel(logo);
		loha.setBounds(590,140,150,150);
		this.add(loha);
		pro1 = new JLabel("Pay Your Bills");
		pro1.setBounds(570, 280, 200, 50);
		pro1.setFont(new Font("Serif",Font.BOLD,30));
		pro1.setForeground(Color.WHITE);
		pro2 = new JLabel("In One Place!");
		pro2.setBounds(640, 320, 200, 50);
		pro2.setFont(new Font("Serif",Font.BOLD,30));
		pro2.setForeground(Color.WHITE);
		this.add(pro1);
		this.add(pro2);
		
		JPanel p1 = new JPanel();
		p1.setBounds(0,0,450, 650);
		p1.setLayout(null);
		this.add(p1);
		
		//title
		title = new JLabel("Login");
		title.setBounds(200, 100, 100, 50);
		title.setFont(new Font("Serif",Font.BOLD,25));
		title.setForeground(Color.BLACK);
		p1.add(title);
		
		usrnm = new JLabel("User Name");
		usrnm.setBounds(100, 170, 100, 20);
		p1.add(usrnm);
		
		un = new JTextField();
		un.setBounds(120,200,200,35);
		un.setBorder(null);
		p1.add(un);
		
		passwd = new JLabel("Password");
		passwd.setBounds(100,265,150,20);
		p1.add(passwd);
		
		psswd = new JPasswordField();
		psswd.setBounds(120,295,200,35);
		psswd.setBorder(null);
		p1.add(psswd);
				/*usrnm.setBounds(x, y, width, height);*/
		usrtype = new JLabel("User Type");
		usrtype.setBounds(100, 360, 100, 20);
		p1.add(usrtype);
		
		ut = new String[] {"Select User Type","Customer","Admin","Fr-Desk","DE Clerk"};
		
		ty = new JComboBox(ut);
		ty.setBounds(120, 390, 200, 35);
		ty.setBorder(null);
		p1.add(ty);
		
		submit = new JButton("Login");
		submit.setBounds(120, 455, 190, 35);
		submit.setBorder(null);
		submit.setBackground(Color.decode("#43A99A"));
		submit.setForeground(Color.WHITE);
		p1.add(submit);
		ButtonHandler handler = new ButtonHandler();
		submit.addActionListener(handler);
		this.setVisible(true);
	}
	public class ButtonHandler implements ActionListener{
		String conString = "jdbc:sqlserver://DESKTOP-5PUB4T3;Database=BMS;IntegratedSecurity=true";
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Connection con = DriverManager.getConnection(conString);
				System.out.println("Connection Established");
				Statement stmt = con.createStatement();
				ResultSet rs;
				if(ty.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "You didn't select a user type","Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(ty.getSelectedIndex() > 1) {
					rs = stmt.executeQuery("exec ver_usr @nameh = '" + un.getText()+"', @uty ="+ty.getSelectedIndex()+", @passh = '"+psswd.getText()+"'");
					if(rs.next() == true) {
						if(ty.getSelectedIndex() == 2) {
							Admin a1 = new Admin(un.getText());
						}else if(ty.getSelectedIndex() == 3) {
							FrontDesk f1 = new FrontDesk(un.getText());
						}else if(ty.getSelectedIndex() == 4){
							Clerk  c1 = new Clerk(un.getText());
						}
					}else {
							JOptionPane.showMessageDialog(null, "Check Your user name and password","Login Faild!", JOptionPane.ERROR_MESSAGE);
						}
				}else if(ty.getSelectedIndex() == 1) {
					rs = stmt.executeQuery("exec ver_cust @nameh = '" + un.getText()+"',@passh ='"+psswd.getText()+"'");
					if(rs.next() == true) {
						if(rs.getInt("account_status") == 1) {
							Customer c1 = new Customer(rs.getInt(1));
							int x = c1.csid;
							System.out.println(x);
						}else {
							JOptionPane.showMessageDialog(null, "This Account is Deactivated","Login Faild!", JOptionPane.ERROR_MESSAGE);
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Check Your user name and password","Login Faild!", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				//System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getInt(4) + " " + rs.getInt(5));
			}catch(SQLException t) {
				System.out.println("Error Connecting to the database");
				t.printStackTrace();
			}
			
		}
		
	}
}

	
	


			
			
			




		
		
		
		
		
		
		
		
		
/*//THE ABOVE IS THE GUI PART
		String conString = "jdbc:sqlserver://DESKTOP-5PUB4T3;Database=BMS;IntegratedSecurity=true";
		try {
			Connection con = DriverManager.getConnection(conString);
			System.out.println("Connection Established");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from usr");
			while(rs.next())
			System.out.println(rs.getString(2));
		}catch(SQLException e) {
			System.out.println("Error Connecting to the database");
			e.printStackTrace();
		}*/
		
		
		
		

