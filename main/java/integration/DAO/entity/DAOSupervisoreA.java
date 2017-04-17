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
import business.entity.Entity;
import business.entity.Gestori.SupervisoreAgenzia;
import business.model.Exception.CommonException;

public class DAOSupervisoreA implements DAO{
	private  DaoFactory daofactory;
	
	
	public DAOSupervisoreA(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	@Override
	public ResultSet creazione(Entity x) throws CommonException {
		String query="Insert into SupervisoreAgenzia(Nome,Cognome,Sesso,DataNascita,Indirizzo,CodiceFiscale,NumCell,NumFisso,"
				+"Assunto,IDAgenzia) values('?','?','?','?','?','?','?','?','1',?)";
		SupervisoreAgenzia s=(SupervisoreAgenzia)x;
		query=queryReplaceFirst(query, s.getNome());
		query=queryReplaceFirst(query, s.getCognome());
		query=queryReplaceFirst(query, s.getSesso());
		query=queryReplaceFirst(query, s.getDataNascita().toString());
		query=queryReplaceFirst(query, s.getIndirizzo());
		query=queryReplaceFirst(query, s.getCodiceFiscale());
		query=queryReplaceFirst(query, s.getNumCell());
		query=queryReplaceFirst(query, s.getNumFisso());
		query=queryReplaceFirst(query, String.valueOf(s.getIDAgenzia()));
		Connection c=Connection.getConnection(this.daofactory);
		ResultSet r;
		try {
			r=c.executeUpdate(query);
			if(r!=null)
				AlertView.getAlertView("Supervisore Agenzia inserito con successo", AlertType.INFORMATION);
			else
				throw new CommonException("Supervisore Agenzia non inserito");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void aggiornamento(Entity x) throws CommonException {
		String query="Update SupervisoreAgenzia Set Nome='?',Cognome='?',Sesso='?',DataNascita='?',Indirizzo='?',CodiceFiscale='?',"+
				"NumCell='?',NumFisso='?',Assunto='?',IDAgenzia=? where IDSupervisoreAgenzia=?";
		SupervisoreAgenzia s=(SupervisoreAgenzia)x;
		query=queryReplaceFirst(query, s.getNome());
		query=queryReplaceFirst(query, s.getCognome());
		query=queryReplaceFirst(query, s.getSesso());
		query=queryReplaceFirst(query, s.getDataNascita().toString());
		query=queryReplaceFirst(query, s.getIndirizzo());
		query=queryReplaceFirst(query, s.getCodiceFiscale());
		query=queryReplaceFirst(query, s.getNumCell());
		query=queryReplaceFirst(query, s.getNumFisso());
		if(s.isAssunto())
			query=queryReplaceFirst(query, "1");
		else
			query=queryReplaceFirst(query, "0");
		
		query=queryReplaceFirst(query, String.valueOf(s.getIDAgenzia()));
		query=queryReplaceFirst(query, String.valueOf(s.getIdUtente()));
		
		Connection c=Connection.getConnection(this.daofactory);
		ResultSet r=null;
		try {
			r=c.executeUpdate(query);
			if(r!=null)
				AlertView.getAlertView("Supervisore Agenzia aggiornato con successo", AlertType.INFORMATION);
			else
				throw new CommonException("Supervisore Agenzia non aggiornato");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	@Override
	public Entity lettura(int id){
	String QUERY= "Select * from SupervisoreAgenzia where IDSupervisoreAgenzia='?' ";
	 Connection connection= Connection.getConnection(daofactory);
	 
	 String readQuery = QUERY;
	 		 
	 readQuery = queryReplaceFirst(readQuery,String.valueOf(id));
	 
     ResultSet readQueryResultSet = null;
     SupervisoreAgenzia  risultato = null;
     try {
		readQueryResultSet = connection.executeRead(readQuery);	
		if(readQueryResultSet.next()){
			risultato= ottieniSupA(readQueryResultSet);
		}
	 } catch (SQLException e) {
		e.printStackTrace();
		AlertView.getAlertView("Non è stato possibile leggere il supervisore dell'agenzia " , AlertType.ERROR);
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
	
	private SupervisoreAgenzia ottieniSupA(ResultSet resultset) {
		SupervisoreAgenzia a=null;
		if(resultset!=null)
			try {
				
				a= new SupervisoreAgenzia(resultset.getInt(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getDate(5).toLocalDate(),resultset.getString(6),
						 	resultset.getString(7),resultset.getString(8),resultset.getString(9),resultset.getBoolean(10),resultset.getInt(11));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			a=new SupervisoreAgenzia();
		return a;
	}

	public List<SupervisoreAgenzia> getAll() {
		String query="Select * from SupervisoreAgenzia";
		Connection c=Connection.getConnection(this.daofactory);
		ResultSet r=null;
		try {
			r=c.executeRead(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return creaElencoSupervisoriagenzia(r);
	}

	private List<SupervisoreAgenzia> creaElencoSupervisoriagenzia(ResultSet r){
		List<SupervisoreAgenzia> l=new LinkedList<SupervisoreAgenzia>();
		if(r!=null){
			try {
				while(r.next()){
					l.add(ottieniSupA(r));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return l;
	}

	public List<SupervisoreAgenzia> getAllByAgenzia(int idAgenzia) {
	String query="Select  * from SupervisoreAgenzia where IDAgenzia="+idAgenzia;
	Connection c=Connection.getConnection(this.daofactory);
	ResultSet r=null;
	try {
		r=c.executeRead(query);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return creaElencoSupervisoriagenzia(r);
	}

	public SupervisoreAgenzia leggiSupervisoreAgenziaByCodiceFiscale(String c) {
		String query="Select * from SupervisoreAgenzia where CodiceFiscale='?'";
		query=queryReplaceFirst(query, c);
		Connection co=Connection.getConnection(this.daofactory);
		ResultSet r=null;
		try {
			r=co.executeRead(query);
			if(r!=null)
				r.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ottieniSupA(r);
	}
}
