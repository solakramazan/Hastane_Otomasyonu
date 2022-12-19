package view;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Hastane_Otomasyonu.ImageBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.Canvas;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Databases.*;
@SuppressWarnings("unused")
public class LogınGUI extends JFrame {
	static final long serialVersionUID = 1000;
	private JPanel w_pane;
	private JTextField fld_hastaSifre;
	private JTextField fld_hastaTc;
	private JTextField fld_doktorTc;
	private JPasswordField fld_doctorPass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//DBConnection.getConnection().sendData("insert into users(tcno,password,name,type) values('63454121716','kırkagac','Ramazan','bashekim')");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogınGUI frame = new LogınGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogınGUI() {
		setResizable(false);
		setTitle("Hastane_Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hoş Geldiniz");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(46, 94, 413, 45);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 145, 466, 208);
		w_pane.add(w_tabpane);
		
		JPanel w_hastalogin = new JPanel();
		w_hastalogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Girişi", null, w_hastalogin, null);
		w_hastalogin.setLayout(null);
		
		JLabel lblTc = new JLabel("T.C. Numaranız : ");
		lblTc.setFont(new Font("Arial", Font.BOLD, 16));
		lblTc.setBounds(10, 10, 140, 32);
		w_hastalogin.add(lblTc);
		
		JLabel lblifre = new JLabel("Şifre : ");
		lblifre.setFont(new Font("Arial", Font.BOLD, 16));
		lblifre.setBounds(10, 52, 140, 32);
		w_hastalogin.add(lblifre);
		
		fld_hastaSifre = new JTextField();
		fld_hastaSifre.setFont(new Font("Arial", Font.PLAIN, 16));
		fld_hastaSifre.setBounds(155, 52, 286, 33);
		w_hastalogin.add(fld_hastaSifre);
		fld_hastaSifre.setColumns(10);
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Arial", Font.PLAIN, 16));
		fld_hastaTc.setColumns(10);
		fld_hastaTc.setBounds(155, 10, 286, 33);
		w_hastalogin.add(fld_hastaTc);
		
		JButton btn_register = new JButton("KAYIT OL");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_register.setFont(new Font("Arial", Font.BOLD, 18));
		btn_register.setBounds(10, 108, 211, 50);
		w_hastalogin.add(btn_register);
		
		JButton btn_hastalogin = new JButton("GİRİŞ YAP");
		btn_hastalogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_hastalogin.setFont(new Font("Arial", Font.BOLD, 18));
		btn_hastalogin.setBounds(230, 108, 211, 50);
		w_hastalogin.add(btn_hastalogin);
		
		JPanel w_doktorgirişi = new JPanel();
		w_doktorgirişi.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Girişi", null, w_doktorgirişi, null);
		w_doktorgirişi.setLayout(null);
		
		fld_doktorTc = new JTextField();
		fld_doktorTc.setFont(new Font("Arial", Font.PLAIN, 16));
		fld_doktorTc.setColumns(10);
		fld_doktorTc.setBounds(155, 10, 286, 33);
		w_doktorgirişi.add(fld_doktorTc);
		
		JLabel lblTc_1 = new JLabel("T.C. Numaranız : ");
		lblTc_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblTc_1.setBounds(10, 10, 140, 32);
		w_doktorgirişi.add(lblTc_1);
		
		JLabel lblifre_1 = new JLabel("Şifre : ");
		lblifre_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblifre_1.setBounds(10, 52, 140, 32);
		w_doktorgirişi.add(lblifre_1);
		
		JButton btn_doktorLogin = new JButton("GİRİŞ YAP");
		btn_doktorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doktorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					ConnectDatabase d = DBConnection.getConnection();
					ResultSet rs = d.getData("Select * from users");
					try {
						while(rs.next()) {
							if(fld_doktorTc.getText().equals(rs.getString("tcno")) && fld_doctorPass.getText().equals(rs.getString("password"))) {
								Bashekim bhekim = new Bashekim();
								bhekim.setId(rs.getInt("id"));
								bhekim.setPassword("password");
								bhekim.setTcno(rs.getString("tcno"));
								bhekim.setName(rs.getString("name"));
								bhekim.setType(rs.getString("type"));
								BashekimGUI bGUI= new BashekimGUI(bhekim);
								bGUI.setVisible(true);
								dispose();			 
								
							}
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doktorLogin.setFont(new Font("Arial", Font.BOLD, 18));
		btn_doktorLogin.setBounds(10, 107, 431, 50);
		w_doktorgirişi.add(btn_doktorLogin);
		
		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(155, 53, 286, 31);
		w_doktorgirişi.add(fld_doctorPass);
		w_doktorgirişi.add(btn_doktorLogin);
		
		ImageBox canvas = new ImageBox();
		canvas.setBounds(202, 4, 90, 80);
		canvas.setLocationURL("C:\\Users\\asira\\eclipse-workspace\\Hastane_Otomasyonu\\src\\com\\Hastane_Otomasyonu\\logo.png");
		w_pane.add(canvas);
	}
}
