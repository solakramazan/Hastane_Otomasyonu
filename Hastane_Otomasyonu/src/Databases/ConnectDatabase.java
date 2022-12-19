package Databases;


import java.sql.*;

public class ConnectDatabase {

    String connectionString;

    Connection connection;

    ConnectionType type;

    public ConnectDatabase(String connectionString, ConnectionType type) {
        this.connectionString = connectionString;
        this.type = type;
    }

    public static String CreateConnectionString(ConnectionIP ipAdress, String databaseName, String Username, String Password, ConnectionType type) {
        if(type == ConnectionType.SQL)
            return "jdbc:sqlserver://"+ ipAdress.ipAdress +";databaseName=" + databaseName + ";encryption=false;user=" + Username + ";password=" + Password;
        else if(type == ConnectionType.MySql)
            return "jdbc:mysql://" +ipAdress.ipAdress + ":" + ipAdress.port + "/" + databaseName +"$" + Username + "-" + Password;
        else
            return "";
    }

    public static String CreateConnectionString(String ipAdress, String databaseName, String Username, String Password, ConnectionType type) {
        if(type == ConnectionType.SQL)
            return "jdbc:sqlserver://"+ ipAdress +";databaseName=" + databaseName + ";encryption=false;user=" + Username + ";password=" + Password;
        else if(type == ConnectionType.MySql)
            return "jdbc:mysql://" +ipAdress + ":" + "3306/" + databaseName +"$" + Username + "-" + Password;
        else
            return "";
    }
    public ConnectDatabase Connect() {
        try {
            if(type == ConnectionType.SQL)
                connection = DriverManager.getConnection(connectionString);
            else if(type == ConnectionType.MySql)
            {
                String rawData = connectionString.substring(connectionString.indexOf('$') + 1, connectionString.toCharArray().length);
                String username = rawData.substring(0, rawData.indexOf('-'));
                String password = rawData.substring(rawData.indexOf('-') + 1, rawData.toCharArray().length);
                connection = DriverManager.getConnection(connectionString.substring(0, connectionString.indexOf('$')), username, password);
            }
        }catch (Exception e){}
        return this;
    }

    public ResultSet getData(String sql) {
        try {
            Statement state = connection.createStatement();
            return state.executeQuery(sql);
        }
        catch (SQLException e){}
        return null;
    }

    public void sendData(String sql) {
        try {
            Statement state = connection.createStatement();
            state.execute(sql);
            state.close();
        }catch (SQLException e) {}
    }
    public String initializeResult(ResultSet st, String[] columnNames) {
        StringBuilder rawData = new StringBuilder();
        while(true) {
            try {
                if (!st.next()) break;
                rawData.append("(");
                for(String c : columnNames)
                    rawData.append(" ").append(st.getString(c));
                rawData.append(")\n");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return rawData.toString();
    }
    public enum ConnectionType {
        SQL, Access, MySql
    }
    public static class ConnectionIP {
        String ipAdress = "localhost";
        String port = "3306";
        public ConnectionIP(String ipAdress) {
            this.ipAdress = ipAdress;
        }

        public ConnectionIP(String ipAdress, String port) {
            this.ipAdress = ipAdress;
            this.port = port;
        }
    }
}