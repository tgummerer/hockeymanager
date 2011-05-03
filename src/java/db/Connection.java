
package db;

import java.sql.*;

public class Connection {

	//Instances of Database
    private java.sql.Connection conn;
    private String dbname;
    private String password;
    private String user;
    private static Connection currentConnection = null;

	private Connection() throws ClassNotFoundException{
    	setDriver();
		dbname = "hockeymanager";
		password = "postgres";
		user = "postgres";
    }

    public void startConnection() throws SQLException{
    	setConnection();
    }

	public void setDbname(String databaseName) {
		dbname = databaseName;
	}

	public void setPassword(String pass) {
        this.password = pass;
    }

	 public void setUsername(String user) {
	    this.user = user;
	 }

	private void setDriver() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
	}


	private void setConnection() throws SQLException{

		//Variables for the connection
		String host = "localhost:";
		String port = "5432";
		String connectionUrl = "jdbc:postgresql://" + host + port + "/" + dbname;
		//Connection to Database
        conn = DriverManager.getConnection(connectionUrl, user, password);
	}

	public PreparedStatement prepareStatement(String query) throws SQLException {
		return conn.prepareStatement(query);
	}

    
	public static Connection getConnection() throws ClassNotFoundException{
		if(currentConnection == null){
			currentConnection = new Connection();
		}
		return currentConnection;
	}


	public static void closeConnection() throws SQLException{
		currentConnection.close();
		currentConnection = null;
	}


	private void close() throws SQLException {
		conn.close();
	}

}