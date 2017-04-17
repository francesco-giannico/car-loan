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
import business.entity.Gestori.Operatore;
import business.model.Exception.CommonException;


public class DAOOperatore implements DAO{

	private  DaoFactory daofactory;
	
	
	public DAOOperatore(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	@Override
	public ResultSet creazione(Entity x) throws CommonException {
		String query="Insert into Operatore(Nome,Cognome,Sesso,DataNascita,Indirizzo,CodiceFiscale,NumCell,NumFisso,"
				+"Assunto,IDSede) values('?','?','?','?','?','?','?','?','1',?)";	
		Operatore s=(Operatore)x;
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
		ResultSet r=null;
		try {
			r=c.executeUpdate(query);
			if(r!=null)
				AlertView.getAlertView("Operatore inserito con successo", AlertType.INFORMATION);
			else
				throw new CommonException("Operatore non inserito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}

	@Override
	public void aggiornamento(Entity x) throws CommonException {
		String query="Update Operatore Set Nome='?',Cognome='?',Sesso='?',DataNascita='?',Indirizzo='?',CodiceFiscale='?',"+
				"NumCell='?',NumFisso='?',Assunto='?',IDSede=? where IDOperatore=?";
		Operatore s=(Operatore)x;
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
				AlertView.getAlertView("Operatore aggiornato con successo", AlertType.INFORMATION);
			else
				throw new CommonException("Operatore non aggiornato");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public Entity lettura(int id){
	String QUERY= "Select * from Operatore where IDOperatore='?' ";
	 Connection connection= Connection.getConnection(daofactory);
	 
	 String readQuery = QUERY;
	 		 
	 readQuery = queryReplaceFirst(readQuery,String.valueOf(id));
	 
     ResultSet readQueryResultSet = null;
     Operatore  risultato = null;
     try {
		readQueryResultSet = connection.executeRead(readQuery);	
		if(readQueryResultSet.next()){
			risultato= ottieniOperatore(readQueryResultSet);
		}
	 } catch (SQLException e) {
		e.printStackTrace();
		AlertView.getAlertView("Non è stato possibile leggere l'operatore " , AlertType.ERROR);
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
	
	private Operatore ottieniOperatore(ResultSet resultset) {
		 try {
			return new Operatore(resultset.getInt(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getDate(5).toLocalDate(),resultset.getString(6),
					 	resultset.getString(7),resultset.getString(8),resultset.getString(9),resultset.getBoolean(10),resultset.getInt(11));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}

	public List<Operatore> getAll() {
		String query="Select * from Operatore";
		Connection c=Connection.getConnection(this.daofactory);
		ResultSet r=null;
		List<Operatore> l=new LinkedList<Operatore>();
		try {
			r=c.executeRead(query);
			l=creaElencoOperatori(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	private List<Operatore> creaElencoOperatori(ResultSet r) {
		List<Operatore> l=new LinkedList<Operatore>();
		if(r!=null){
			try {
				while(r.next()){
					l.add(ottieniOperatore(r));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return l;
	}

	public List<Operatore> getAllOperatoriBySede(int idsede) {
		String query="Select * from Operatore where IDSede="+idsede;
		Connection c=Connection.getConnection(this.daofactory);
		ResultSet r=null;
		List<Operatore> l=new LinkedList<Operatore>();
		try {
			r=c.executeRead(query);
			l=creaElencoOperatori(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public Operatore leggiOperatoreByCodiceFiscale(String f) {
		String query="Select * from Operatore where CodiceFiscale='?'";
		query=queryReplaceFirst(query, f);
		Connection c=Connection.getConnection(this.daofactory);
		ResultSet r=null;
		try {
			r=c.executeRead(query);
			if(r!=null){
				r.next();
				return ottieniOperatore(r);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
