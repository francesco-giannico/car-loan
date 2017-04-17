package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Cliente;
import business.entity.Entity;

public class DAOCliente implements DAO{

	private  DaoFactory daofactory;

	public DAOCliente(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	@Override
	public ResultSet creazione(Entity x) {
		String INSERT = "INSERT INTO Cliente"
				+ "(Nome,Cognome,Sesso,DataEmissPatente,DataNascita,"
				+ "Indirizzo,CodFiscale,NumCell,NumTel,PatenteGuida,DataScadPatente,PartitaIva,Email) "
				+ "values('?','?','?','?','?','?','?','?','?','?','?','?','?');";
		
		String insertQuery = INSERT;
				
		Cliente cliente= (Cliente)x;

        insertQuery = queryReplaceFirst(insertQuery, cliente.getNome());
        
        insertQuery= queryReplaceFirst(insertQuery,cliente.getCognome());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getSesso());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getDataEmissPatente().toString());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getDatanascita().toString());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getIndirizzo());
        
        insertQuery= queryReplaceFirst(insertQuery,cliente.getCodFiscale());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getNumCell());
      
        insertQuery= queryReplaceFirst(insertQuery,cliente.getNumTel());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getPatenteGuida());
        
        insertQuery= queryReplaceFirst(insertQuery,cliente.getDataScadPatente().toString());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getPartitaIva());
        
        insertQuery= queryReplaceFirst(insertQuery,cliente.getEmail());
    
        Connection connection= Connection.getConnection(daofactory);
        
        ResultSet idList = null;
        
		try {
			 idList = connection.executeUpdate(insertQuery);
			 if(idList!=null)
				 AlertView.getAlertView("Cliente inserito con successo",AlertType.INFORMATION);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 AlertView.getAlertView("Non è stato possibile inserire il cliente" , AlertType.ERROR);
		}
		finally{
			try {
				idList.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return idList;
	}
	
	@Override
	public Entity lettura(int id) {
		 String QUERY= "Select IDCliente,Nome,Cognome,Sesso,DataEmissPatente,DataNascita,"
		 			+ "Indirizzo,CodFiscale,NumCell,NumTel,PatenteGuida,DataScadPatente,PartitaIva,Email from Cliente where IDCliente='?' ";
		 Connection connection= Connection.getConnection(daofactory);
		 
		 String readQuery = QUERY;
		 		 
		 readQuery = queryReplaceFirst(readQuery,String.valueOf(id));
		 
	     ResultSet readQueryResultSet = null;
	     Cliente  risultato = null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			if(readQueryResultSet.next()){
				risultato= ottieniCliente(readQueryResultSet);
			}
		 } catch (SQLException e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere il cliente " , AlertType.ERROR);
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

	

	public List<Cliente> getAll(){		
		 String readQuery = "Select IDCliente,Nome,Cognome,Sesso,DataEmissPatente,DataNascita,"
				 			+ "Indirizzo,CodFiscale,NumCell,NumTel,PatenteGuida,DataScadPatente,PartitaIva,Email from Cliente";
		 Connection connection= Connection.getConnection(daofactory);
	        
	     ResultSet readQueryResultSet = null;
	     List<Cliente> risultato = null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoClienti(readQueryResultSet);
		 } catch (SQLException e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere i clienti " , AlertType.ERROR);
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
	
	public List<Cliente> creaElencoClienti(ResultSet resultset){
		List<Cliente> risultato = new LinkedList<>();
        try {
         if(resultset!=null){
            while (resultset.next()) {
            	ottieniCliente(resultset);
                Cliente cliente= ottieniCliente(resultset);
                risultato.add(cliente);
            }
         }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
		return risultato;
	}
	public Cliente ottieniCliente(ResultSet resultset) throws SQLException{
	  return  new Cliente(resultset.getInt("IdCliente"),resultset.getString("Nome"),resultset.getString("Cognome"),
			  resultset.getString("Sesso"),resultset.getDate("DataEmissPatente").toLocalDate(),resultset.getDate("DataNascita").toLocalDate(),resultset.getString("Indirizzo"),
			  resultset.getString("CodFiscale"),resultset.getString("NumCell"),resultset.getString("NumTel"),resultset.getString("PatenteGuida"),resultset.getDate("DataScadPatente").toLocalDate(),
			 resultset.getString("PartitaIva"), resultset.getString("Email"));
	}
	
	@Override
	public void aggiornamento(Entity parameter) {
		Cliente cliente= (Cliente) parameter;
		String UPDATE = "Update  Cliente SET"
				+ " Nome ='?',Cognome='?',Sesso='?',DataEmissPatente='?',DataNascita='?',"
				+ "Indirizzo='?',CodFiscale='?',NumCell='?',NumTel='?',PatenteGuida='?',DataScadPatente='?',PartitaIva='?',Email='?' "
				+ "where IDCliente= '?';";
		
		String updateQuery = UPDATE;
		
		updateQuery = queryReplaceFirst(updateQuery, cliente.getNome());
        
		updateQuery= queryReplaceFirst(updateQuery,cliente.getCognome());

		updateQuery= queryReplaceFirst(updateQuery,cliente.getSesso());

		updateQuery= queryReplaceFirst(updateQuery,cliente.getDataEmissPatente().toString());

		updateQuery= queryReplaceFirst(updateQuery,cliente.getDatanascita().toString());

		updateQuery= queryReplaceFirst(updateQuery,cliente.getIndirizzo());
        
		updateQuery= queryReplaceFirst(updateQuery,cliente.getCodFiscale());

		updateQuery= queryReplaceFirst(updateQuery,cliente.getNumCell());
      
		updateQuery= queryReplaceFirst(updateQuery,cliente.getNumTel());

		updateQuery= queryReplaceFirst(updateQuery,cliente.getPatenteGuida());
        
		updateQuery= queryReplaceFirst(updateQuery,cliente.getDataScadPatente().toString());

		updateQuery= queryReplaceFirst(updateQuery,cliente.getPartitaIva());
        
		updateQuery= queryReplaceFirst(updateQuery,cliente.getEmail());

		updateQuery= queryReplaceFirst(updateQuery,String.valueOf(cliente.getId()));
		
        Connection connection= Connection.getConnection(daofactory);
        
        ResultSet idList = null;
        
		try {
			 idList = connection.executeUpdate(updateQuery);
			 if(idList!=null)
				 AlertView.getAlertView("Cliente aggiornato con successo",AlertType.INFORMATION);
		} catch (SQLException e) {
			e.printStackTrace();
			 AlertView.getAlertView("Non è stato possibile aggiornare il cliente" , AlertType.ERROR);
		}
		finally{
			try {
				idList.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
