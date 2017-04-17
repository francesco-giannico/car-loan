package integration.DAO.connection;


import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import integration.DAO.DaoFactory;
import integration.DAO.DatabaseConnectionException;
import config.ConfiguratorDBReader;
import config.ConfiguratorReader;

public class Connection {
	private final static String PATH = "config/config.cfg";
	private  String host;
	private  String db;
	private String port;
	private  String username;
	private  String password;
	private  String connessione;
	private  String vendor;
	private DaoFactory daofactory;
	private static   Connection con;
	private static  java.sql.Connection connessione_remota;
	private ConfiguratorReader reader;	
	
	private Connection(DaoFactory a){
		reader=new ConfiguratorDBReader(PATH);
		this.daofactory=a;
		host=reader.getField(ConfiguratorDBReader.HOST);
		db=reader.getField(ConfiguratorDBReader.DATABASE);
		username=reader.getField(ConfiguratorDBReader.USERNAME);
		password=reader.getField(ConfiguratorDBReader.PASSWORD);
		port=reader.getField(ConfiguratorDBReader.PORT);
		this.connessione="jdbc:";
		
		switch(this.daofactory.getClass().getSimpleName()){
		case "MySqlDaoFactory":
			vendor="mysql";
			break;
		 default:
			vendor="mysql";
		}
		this.connessione+=vendor+"://"+host+":"+port+"/"+db;
		
	}
	public static Connection getConnection(DaoFactory a){
		if(con==null)
			con=new Connection(a);
		return  con;
	}
	
	/**
	 * Permette di aprire una connessione al database.
	 * 
	 * @return la connessione creata.
	 * @throws SQLException
	 */
	private  void connetti() throws SQLException {
		if(connessione_remota==null){
		try {
			switch(vendor){
			case "mysql":
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						break;
			default:
					throw new DatabaseConnectionException("Could not find vendor");
			}
		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		}
		catch(InstantiationException | IllegalAccessException| ClassNotFoundException e){
			e.printStackTrace();
		}
		connessione_remota = DriverManager.getConnection(connessione, username, password);
		}
	}
	
	/**
	 * Chiude la connessione al database.
	 * @throws SQLException 
	 * 
	 */
	public static void chiudiConnessione() throws SQLException {
		if (connessione_remota != null && !connessione_remota.isClosed()){
			connessione_remota.close();
			connessione_remota=null;
		}
	}
	
	

	public ResultSet executeUpdate(String query) throws SQLException{
		  
		this.connetti();
		ResultSet result=null;
		if(query!=null && !query.isEmpty()){
			PreparedStatement st;
			
			try {
				st=connessione_remota.prepareStatement("use "+db+";");
				st.execute();
				st=connessione_remota.prepareStatement(query);
				st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
				result=st.getGeneratedKeys();
		
			} catch (SQLException e) {
				AlertView.getAlertView(e.getMessage(), AlertType.ERROR);
			}
			
		}
	return result;
	}
	

	public ResultSet executeRead(String query) throws SQLException{
		this.connetti();
		ResultSet result=null;
		if(query!=null && !query.isEmpty()){
			PreparedStatement st;
			
			try {
				st=connessione_remota.prepareStatement("use "+db+";");
				st.execute();
				result=st.executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	return result;
	}
	/**
	 * Used to insert/update non text values such as binary
	 * @param query
	 * @param i
	 * @return
	 * @throws SQLException 
	 */
	public ResultSet executeUpdate_binary(String query,InputStream i) throws SQLException{
		this.connetti();
		ResultSet r=null;
		
		try{
		if(query!=null && !query.isEmpty() && i!=null){
			PreparedStatement st;
			st=connessione_remota.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setBinaryStream(1, i);
			st.executeUpdate();
			r=st.getResultSet();
		}
		
		
		}
		 catch (SQLException e) {
				AlertView.getAlertView(e.getMessage(), AlertType.ERROR);
			}
		return r;
		
	}
}
