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
import business.entity.Gestori.SupervisoreSede;
import business.model.Exception.CommonException;


public class DAOSupervisoreS implements DAO{
	private  DaoFactory daofactory;
	
	
	public DAOSupervisoreS(DaoFactory dao){
		this.daofactory = dao;		
	}

	@Override
	public ResultSet creazione(Entity x) throws CommonException {
		String query="Insert into SupervisoreSede(Nome,Cognome,Sesso,DataNascita,Indirizzo,CodiceFiscale,NumCell,NumFisso,"
				+"Assunto,IDSede) values('?','?','?','?','?','?','?','?','1',?)";
		SupervisoreSede s=(SupervisoreSede)x;
		query=queryReplaceFirst(query, s.getNome());
		query=queryReplaceFirst(query, s.getCognome());
		query=queryReplaceFirst(query, s.getSesso());
		query=queryReplaceFirst(query, s.getDataNascita().toString());
		query=queryReplaceFirst(query, s.getIndirizzo());
		query=queryReplaceFirst(query, s.getCodiceFiscale());
		query=queryReplaceFirst(query, s.getNumCell());
		query=queryReplaceFirst(query, s.getNumFisso());
		query=queryReplaceFirst(query, String.valueOf(s.getIDSede()));
		Connection c=Connection.getConnection(this.daofactory);
		ResultSet r;
		try {
			r=c.executeUpdate(query);
			if(r!=null)
				AlertView.getAlertView("Supervisore Sede inserito con successo", AlertType.INFORMATION);
			else
				throw new CommonException("Supervisore sede non inserito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiornamento(Entity x) throws CommonException {
		String query="Update SupervisoreSede Set Nome='?',Cognome='?',Sesso='?',DataNascita='?',Indirizzo='?',CodiceFiscale='?',"+
				"NumCell='?',NumFisso='?',Assunto='?',IDSede=? where IDSupervisoreSede=?";
		SupervisoreSede s=(SupervisoreSede)x;
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
		
		query=queryReplaceFirst(query, String.valueOf(s.getIDSede()));
		query=queryReplaceFirst(query, String.valueOf(s.getIdUtente()));
		
		Connection c=Connection.getConnection(this.daofactory);
		ResultSet r=null;
		try {
			r=c.executeUpdate(query);
			if(r!=null)
				AlertView.getAlertView("Supervisore Sede aggiornato con successo", AlertType.INFORMATION);
			else
				throw new CommonException("Supervisore sede non aggiornato");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public Entity lettura(int id){
	String QUERY= "Select * from SupervisoreSede where IDSupervisoreSede='?' ";
	 Connection connection= Connection.getConnection(daofactory);
	 
	 String readQuery = QUERY;
	 		 
	 readQuery = queryReplaceFirst(readQuery,String.valueOf(id));
	 
     ResultSet readQueryResultSet = null;
     SupervisoreSede  risultato = null;
     try {
		readQueryResultSet = connection.executeRead(readQuery);	
		if(readQueryResultSet!=null &&readQueryResultSet.next()){
			risultato= ottieniSupS(readQueryResultSet);
		}
	 } catch (SQLException e) {
		e.printStackTrace();
		AlertView.getAlertView("Non è stato possibile leggere il supervisore della sede " , AlertType.ERROR);
	 }
	     return risultato;
	}
	
	private SupervisoreSede ottieniSupS(ResultSet resultset) throws SQLException{
		 return new SupervisoreSede(resultset.getInt(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getDate(5).toLocalDate(),resultset.getString(6),
				 	resultset.getString(7),resultset.getString(8),resultset.getString(9),resultset.getBoolean(10),resultset.getInt(11));
	}

	public List<SupervisoreSede> getAll_bySede(int idSede){
		String query="Select * from SupervisoreSede where IDSede="+String.valueOf(idSede);
		Connection c=Connection.getConnection(this.daofactory);
		ResultSet r=null;
		try {
			r=c.executeRead(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return creaElencoSupervisoriSede(r);
	}

	private List<SupervisoreSede> creaElencoSupervisoriSede(ResultSet r) {
		List<SupervisoreSede> l=new LinkedList<SupervisoreSede>();
		if(r!=null){
		try {
			while(r.next()){
			l.add(ottieniSupS(r));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return l;
	}

	public List<SupervisoreSede> getAll() {
		String query="Select * from SupervisoreSede";
		Connection c=Connection.getConnection(this.daofactory);
		ResultSet r=null;
		try {
			 r=c.executeRead(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return creaElencoSupervisoriSede(r);
	}

	public SupervisoreSede leggiSupervisoreSedeByCodiceFiscale(String c) {
		String query="Select * from SupervisoreSede where CodiceFiscale='?'";
		query=queryReplaceFirst(query, c);
		Connection co=Connection.getConnection(this.daofactory);
		try {
			ResultSet r=co.executeRead(query);
			if(r.next())
			return ottieniSupS(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
