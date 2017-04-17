package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.pagamento.CartaDiCredito;
import business.entity.pagamento.tipiCircuiti;


public class DAOCartaDiCredito implements DAO{


	private  DaoFactory daofactory;

	public DAOCartaDiCredito(DaoFactory dao){
		this.daofactory = dao;		
	}

	@Override
	public ResultSet creazione(Entity x) {
		String INSERT = "INSERT INTO CartaDiCredito"
				+ "(IBAN,NumeroCarta,DataScadenza,Circuito,IDCliente)" 
				+ "  values('?','?','?','?','?')";
		
		String insertQuery = INSERT;
		
		CartaDiCredito carta= (CartaDiCredito)x;

        insertQuery = queryReplaceFirst(insertQuery, carta.getIBAN());
        insertQuery = queryReplaceFirst(insertQuery, carta.getNumeroCarta());
        insertQuery = queryReplaceFirst(insertQuery, carta.getDataScadenza().toString());
        insertQuery = queryReplaceFirst(insertQuery, carta.getCircuito().toString());
        insertQuery = queryReplaceFirst(insertQuery, String.valueOf(carta.getIDCliente()));
		
        
        Connection connection= Connection.getConnection(daofactory);
	        
	        ResultSet idList = null;
	        
			try {
				 idList = connection.executeUpdate(insertQuery);
				 if(idList!=null)
					 AlertView.getAlertView("Carta di credito inserita con successo",AlertType.INFORMATION);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 AlertView.getAlertView("Non è stato possibile inserire la carta di credito" , AlertType.ERROR);
			}
			finally{
				try {
					idList.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return idList;

	}

	@Override
	public void aggiornamento(Entity x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity lettura(int id) {
		String readQuery = "Select * from CartaDiCredito where IDIban = '?'";
		readQuery = queryReplaceFirst(readQuery,String.valueOf(id));

		Connection connection= Connection.getConnection(daofactory);
		CartaDiCredito carta=null;
		  ResultSet readQueryResultSet=null;
		     try {
				readQueryResultSet = connection.executeRead(readQuery);	
				
				if(readQueryResultSet!=null){
					while(readQueryResultSet.next())
						carta=  ottieniCarta(readQueryResultSet);}
			 } catch (SQLException e) {
				e.printStackTrace();
				AlertView.getAlertView("Non è stato possibile leggere la carta " , AlertType.ERROR);
			 }
			 finally{
				try {
					readQueryResultSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
				}
			}

		    return carta;
	}
	
	
	public List<CartaDiCredito> getAllByCliente(int id){
		String read = "select IDIban,IBAN,NumeroCarta,DataScadenza,Circuito,IDCliente from CartaDiCredito where IDCliente='?'";
		String readQuery = read;
		readQuery = queryReplaceFirst(readQuery,String.valueOf(id));
	
		Connection connection= Connection.getConnection(daofactory);
        
	   List<CartaDiCredito> risultato = null;
	   ResultSet readQueryResultSet=null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoCarte(readQueryResultSet);
		 } catch (SQLException e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere le carte di credito del cliente " , AlertType.ERROR);
		 }
		 finally{
			try {
				readQueryResultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}

	    return risultato;
	}
	
	
	private List<CartaDiCredito> creaElencoCarte(ResultSet resultset) throws SQLException{
		List<CartaDiCredito> risult= new ArrayList<CartaDiCredito>();
		while(resultset.next()){
			CartaDiCredito carta= ottieniCarta(resultset);
			risult.add(carta);
		}
		return risult;
	}
	
	
	private CartaDiCredito ottieniCarta(ResultSet resultset) throws SQLException{
		return  new CartaDiCredito(resultset.getInt(6),resultset.getDate(4).toLocalDate(),
					resultset.getString(2),resultset.getString(3),tipiCircuiti.toCircuito(resultset.getString(5)),resultset.getInt(1));
	}
}
