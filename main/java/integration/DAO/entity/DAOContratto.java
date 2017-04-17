package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.UtenteCorrente;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.Operatore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.StatoContratto;
import business.model.Exception.CommonException;

public class DAOContratto implements DAO{

	private  DaoFactory daofactory;

	public DAOContratto(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	@Override
	public ResultSet creazione(Entity x) {
		String INSERT = "INSERT INTO Contratto "
				+ "( ?,Stato,DataCreazione,Note,IDCliente) "
				+ "values ('?','?','?','?','?');";
		
		String insertQuery = INSERT;
				
		Contratto contratto= (Contratto)x;	
		
		if(UtenteCorrente.getUtente() instanceof Operatore){
        	insertQuery= queryReplaceFirst(insertQuery,"IDOperatore");
        	insertQuery= queryReplaceFirst(insertQuery,contratto.getIDOperatore().toString());
       
        }
        else if(UtenteCorrente.getUtente() instanceof Amministratore){
        	insertQuery= queryReplaceFirst(insertQuery,"IDAmministratore");
        	insertQuery= queryReplaceFirst(insertQuery,contratto.getIDAmministratore().toString());
        	
        }
        else if(UtenteCorrente.getUtente() instanceof SupervisoreSede){
        	insertQuery= queryReplaceFirst(insertQuery,"IDSupervisoreSede");
        	insertQuery= queryReplaceFirst(insertQuery,contratto.getIDSupervisoreSede().toString());
        }
        else if(UtenteCorrente.getUtente() instanceof SupervisoreAgenzia){
        	insertQuery= queryReplaceFirst(insertQuery,"IDSupervisoreAgenzia");
			insertQuery= queryReplaceFirst(insertQuery,contratto.getIDSupervisoreAgenzia().toString());
		}
        	
        insertQuery = queryReplaceFirst(insertQuery, contratto.getStato().toString());
        
        insertQuery= queryReplaceFirst(insertQuery,contratto.getDataCreazione().toString());
        
        insertQuery= queryReplaceFirst(insertQuery,contratto.getNote());

        insertQuery= queryReplaceFirst(insertQuery,String.valueOf(contratto.getIdCliente()));
        
        
        
        Connection connection= Connection.getConnection(daofactory);
        
        ResultSet idList = null;
        
		try {
			 idList = connection.executeUpdate(insertQuery);
			 if(idList!=null)
				 AlertView.getAlertView("contratto inserito con successo",AlertType.INFORMATION);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 AlertView.getAlertView("Non è stato possibile inserire il contratto" , AlertType.ERROR);
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
	public void aggiornamento(Entity entity) throws CommonException{

	
		Contratto contratto= (Contratto) entity;
		String UPDATE = "UPDATE  Contratto  SET "
							+ "Stato= '?', Note = '?' ";
		String 	WHERE= " WHERE IDContratto = '?'";
		String updateQuery = UPDATE;
	
	
		updateQuery = queryReplaceFirst(updateQuery, contratto.getStato().toString());
		
        updateQuery= queryReplaceFirst(updateQuery,contratto.getNote());

        if(!contratto.getStato().toString().equals(StatoContratto.Aperto.toString())){
    		updateQuery+=", DataChiusura='?'";
    		updateQuery= queryReplaceFirst(updateQuery,contratto.getDataChiusura().toString());
        }        	
        updateQuery+=WHERE;
        updateQuery= queryReplaceFirst(updateQuery,String.valueOf(contratto.getIDContratto()));
     
     
        Connection connection= Connection.getConnection(daofactory);
        
        ResultSet idList = null;
        
		try {
			 idList = connection.executeUpdate(updateQuery);
			 if(idList!=null)
				 AlertView.getAlertView("Contratto aggiornato con successo",AlertType.INFORMATION);
		} catch (SQLException e) {
			e.printStackTrace();
			 AlertView.getAlertView("Non è stato possibile aggiornare il contratto" , AlertType.ERROR);
		}
		finally{
			try {
				idList.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	public List<Contratto> getAll(){		
		 String readQuery = "Select IDContratto, Stato , DataCreazione,Note,DataChiusura,IDcliente,IDoperatore,"
		 					+ "IDSupervisoreAgenzia,IDsupervisoresede, IDamministratore from Contratto";

		 Connection connection= Connection.getConnection(daofactory);
	        
	     ResultSet readQueryResultSet = null;
	     List<Contratto> risultato = null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoContratti(readQueryResultSet);
		 } catch (SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere i contratti" , AlertType.ERROR);
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
	
	public List<Contratto> creaElencoContratti(ResultSet resultset) throws InstantiationException, IllegalAccessException{
	 
		List<Contratto> risultato = new LinkedList<>();

        try {
         if(resultset!=null){
            while (resultset.next()) {
                Contratto contratto = ottieniContratto(resultset);
                risultato.add(contratto);
            	
            }
         }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
		return risultato;
	}
	@Override
	public Entity lettura(int id) {
		 String readQuery= "Select * from Contratto where IDContratto='?'";
		readQuery = queryReplaceFirst(readQuery,String.valueOf(id));
		 Connection connection= Connection.getConnection(daofactory);
	        Contratto contratto=null;
	     ResultSet readQueryResultSet = null;
	     try {
				readQueryResultSet = connection.executeRead(readQuery);	
				if(readQueryResultSet!=null){
					while(readQueryResultSet.next()){
					contratto= ottieniContratto(readQueryResultSet);}
					}
			 } catch (SQLException e) {
				e.printStackTrace();
				AlertView.getAlertView("Non è stato possibile leggere i contratti" , AlertType.ERROR);
			 }
			 finally{
				try {
					readQueryResultSet.close();
					
					} catch (SQLException e) {
						e.printStackTrace();
				}
			}

		    return contratto;
	}
	
	
	private Contratto ottieniContratto(ResultSet resultset) throws SQLException{
		   String sParam= null;
	        int iParam;
		Contratto contratto = new Contratto();
        iParam= resultset.getInt("IDContratto");
        contratto.setIDContratto(iParam);
        
        iParam= resultset.getInt("IDOperatore");
        contratto.setIDOperatore(iParam);
        
        iParam= resultset.getInt("IDSupervisoreAgenzia");
        contratto.setIDSupervisoreAgenzia(iParam);  
        
        iParam= resultset.getInt("IDSupervisoreSede");
        contratto.setIDSupervisoreSede(iParam);
        
        iParam= resultset.getInt("IDAmministratore");
        contratto.setIDAmministratore(iParam);
        
        iParam= resultset.getInt("IDCliente");//leggo l'id del cliente e ora prendo la sua istanza.facendo una specie di join
        contratto.setIdCliente(iParam);
       
        sParam= resultset.getString("Stato");
        contratto.setStato(StatoContratto.toStato(sParam));
        
        sParam= resultset.getString("Note");
        contratto.setNote(sParam);
   
        contratto.setDataCreazione(resultset.getDate("DataCreazione").toLocalDate());
        if(resultset.getDate("DataChiusura")!=null)
        	contratto.setDataChiusura(resultset.getDate("DataChiusura").toLocalDate());
        return contratto;
	}
}
