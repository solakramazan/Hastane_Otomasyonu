package Databases;
import java.sql.*;
import java.util.ResourceBundle.Control;




public class DBConnection {
	static ConnectDatabase db = new ConnectDatabase(ConnectDatabase.CreateConnectionString("desperado.database.windows.net", "hastane_otomasyon", "desperado", "Beşiktaş1903.", ConnectDatabase.ConnectionType.SQL), ConnectDatabase.ConnectionType.SQL).Connect();
	
	public static ConnectDatabase getConnection() { return db;}

	public Connection connDb() {
		// TODO Auto-generated method stub
		return null;
	}
}

