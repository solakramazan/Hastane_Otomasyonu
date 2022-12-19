package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Databases.ConnectDatabase;
import Databases.DBConnection;
import Model.Bashekim;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Helper .*;
import javax.swing.JComboBox;



public class BashekimGUI extends JFrame {
static Bashekim bashekim= new Bashekim();
private JPanel w_pane;
private JTextField textField;
private JTextField textField_1;
private JTextField textField_2;
private JTextField textField_3;
private JTable table_doktor;
private DefaultTableModel doctorModel = null;
private Object[] doctorData = null;
private JScrollPane W_scrollDoktor;
ConnectDatabase cd = DBConnection.getConnection();
private JTable table_clinic;
private JTextField fld_clinicName;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	public BashekimGUI(Bashekim bashekim) {
		doctorModel =new DefaultTableModel();
		
		
		
		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın  " + bashekim.getName());
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(10, 10, 358, 44);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.setFont(new Font("Arial", Font.ITALIC, 12));
		btnNewButton.setBounds(610, 10, 85, 29);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBackground(Color.WHITE);
		w_tab.setBounds(23, 81, 686, 354);
		w_pane.add(w_tab);
		
		JPanel w_doctor = new JPanel();
		w_doctor.setBackground(Color.WHITE);
		w_tab.addTab("Doktor Yönetimi", null, w_doctor, null);
		w_tab.setBackgroundAt(0, Color.WHITE);
		w_doctor.setLayout(null);
		
		JLabel adsoyad = new JLabel("Ad Soyad :");
		adsoyad.setBounds(489, 4, 148, 24);
		adsoyad.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		w_doctor.add(adsoyad);
		
		textField = new JTextField();
		textField.setBounds(489, 27, 182, 33);
		w_doctor.add(textField);
		textField.setColumns(10);
		
		JLabel tcno = new JLabel("T.C NO :");
		tcno.setBounds(489, 60, 138, 24);
		tcno.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		w_doctor.add(tcno);
		
		textField_1 = new JTextField();
		textField_1.setBounds(489, 81, 182, 33);
		textField_1.setColumns(10);
		w_doctor.add(textField_1);
		
		JLabel sifre = new JLabel("Şifre :");
		sifre.setBounds(489, 113, 138, 24);
		sifre.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		w_doctor.add(sifre);
		
		textField_2 = new JTextField();
		textField_2.setBounds(489, 134, 182, 33);
		textField_2.setColumns(10);
		w_doctor.add(textField_2);
		
		JButton eklebtn = new JButton("Ekle");
		eklebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Boş alan bırakmayın");
					return;
				}
				
				cd.sendData("insert into users(tcno, password, name, type) values ('" + textField_1.getText() + "','" + textField_2.getText() + "','" + textField.getText() +  "', 'doktor')");
				listTable();
				JOptionPane.showMessageDialog(null, "İşlem başarılı");
			}
		});
		eklebtn.setBounds(489, 177, 182, 33);
		eklebtn.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		w_doctor.add(eklebtn);
		
		textField_3 = new JTextField();
		textField_3.setBackground(UIManager.getColor("Button.background"));
		textField_3.setBounds(489, 241, 182, 33);
		textField_3.setColumns(10);
		w_doctor.add(textField_3);
		
		JLabel kullanıcıadı = new JLabel("Kullanıcı Adı : ");
		kullanıcıadı.setBounds(489, 220, 138, 24);
		kullanıcıadı.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		w_doctor.add(kullanıcıadı);
		
		JButton silbtn = new JButton("Sil");
		silbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cd.sendData("delete from users where id='" + textField_3.getText() + "'");
				listTable();
				JOptionPane.showMessageDialog(null, "Silme İşlemi Başarılı");
			}
		});
		silbtn.setBounds(489, 284, 182, 33);
		silbtn.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		w_doctor.add(silbtn);
		
		W_scrollDoktor = new JScrollPane();
		W_scrollDoktor.setBounds(10, 4, 459, 313);
		w_doctor.add(W_scrollDoktor);
		
		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tab.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);
		
		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 10, 247, 307);
		w_clinic.add(w_scrollClinic);
		
		table_clinic = new JTable();
		w_scrollClinic.setViewportView(table_clinic);
		
		JLabel lblNewLabel_1 = new JLabel("Poliklinik Adı :");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(267, 3, 139, 28);
		w_clinic.add(lblNewLabel_1);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setBounds(267, 41, 144, 28);
		w_clinic.add(fld_clinicName);
		fld_clinicName.setColumns(10);
		
		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_addClinic.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		btn_addClinic.setBounds(267, 79, 144, 33);
		w_clinic.add(btn_addClinic);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(416, 10, 255, 307);
		w_clinic.add(scrollPane);
		
		JComboBox select_doctor = new JComboBox();
		select_doctor.setBackground(Color.WHITE);
		select_doctor.setBounds(267, 241, 139, 33);
		//for (int = 0; i < bashekim.getDoctorList().size () ; i++ ) {
			//select_doctor.addItem(new Item(bashekim.getDoctorList().get(i).getId() , bashekim.getDoctorList().get(i).getName() ));
		//}
		w_clinic.add(select_doctor);
		
		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		btn_addWorker.setBounds(267, 284, 139, 33);
		w_clinic.add(btn_addWorker);
		
		
		
		listTable();
		
	}
	
	void listTable() {
		doctorModel = new DefaultTableModel();
		
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC NO";
		colDoctorName[3] = "Şifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for(int i=0;i<bashekim.getDoctorList().size();i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		table_doktor = new JTable(doctorModel);
		table_doktor.setFillsViewportHeight(true);
		W_scrollDoktor.setViewportView(table_doktor);
		table_doktor.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
}
