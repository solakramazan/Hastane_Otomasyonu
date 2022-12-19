package Model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Databases.ConnectDatabase;
import Databases.DBConnection;

public class Bashekim extends User{
	
    
	public Bashekim(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		// TODO Auto-generated constructor stub
	}
	public Bashekim() {}
    public ArrayList<User> getDoctorList(){
     ArrayList<User> list= new ArrayList<>();
     ConnectDatabase con = DBConnection.getConnection();
	User obj;
	try {
	ResultSet rs = con.getData("SELECT * FROM users WHERE type = 'doktor' ");
	while(rs.next()){
		obj = new User(rs.getInt("id"), rs.getString("tcno"),rs.getString("name"),rs.getString("password"),rs.getString("type"));
		list.add(obj);
	}
	}catch (SQLException e) {
		e.printStackTrace();
	}finally {
		
	}
	return list;
	}
     
    }
