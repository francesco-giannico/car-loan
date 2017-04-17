package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import presentation.mvp.view.controller.generale.noleggio.RicercaNoleggio;
import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.StatoNoleggio;
import business.model.Exception.CommonException;


public class DAONoleggio implements DAO{
	
	private  DaoFactory daofactory;

	public DAONoleggio(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	public int conta(){
		String count= "Select count(1) as numNoleggi from Noleggio";
	  Connection connection= Connection.getConnection(daofactory);
       
       ResultSet idList = null;
       int numero=0;
		try {
			 idList = connection.executeRead(count);
			 if(idList!=null)
				 while(idList.next()){
					 numero=idList.getInt(1);
				 }
			 else 
				 throw new CommonException("non è stato possibile contare i noleggi");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 try {
				throw new CommonException("non è stato possibile contare i noleggi");
			} catch (CommonException e1) {
				// TODO Auto-generated catch block
				e1.showMessage();
			}
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.showMessage();
		}
		finally{
			try {
				idList.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return numero;
		
	}
	@Override
	public ResultSet creazione(Entity x) {
		String insert= "INSERT INTO Noleggio"
				+ "(InizioNoleggio"
				+ ",Rientro"
				+ ",Ritiro"
				+ ",KmBase"
				+ ",Stato"
				+ ",NumeroSettimane"
				+ ",NumeroGiorni"
				+ ",Numero_chilometri"
				+ ",LuogoRestituzione,"
				+ " IDContratto"
				+ ", IDAuto"
				+ ", IDPagamento) "
				
				
				+ " values ("
				+ "'?'"
				+ ",'?'"
				+ ",'?'"
				+ ",'?'"
				+ ",'?'"
				+ ",'?'"
				+ ",'?'"
				+ ",'?'"
				+ ",'?'"
				+ ",'?'"
				+ ",'?'"
				+ ",'?');";
				
	
		
		String   insertQuery =insert;
		Noleggio noleggio= (Noleggio)x;
		
	   insertQuery = queryReplaceFirst(insertQuery,  noleggio.getInizioNoleggio().toString());
	   insertQuery = queryReplaceFirst(insertQuery, noleggio.getRientro().toString());
	   insertQuery= queryReplaceFirst(insertQuery, noleggio.getRitiro().toString());
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getKmBase()));
	   insertQuery = queryReplaceFirst(insertQuery, noleggio.getStato().toString());
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getNumeroSettimane()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getNumeroGiorni()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getNumeroChilometri()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getSedeRestituzione()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getIdcontratto()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getIdAuto()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getIdPagamento()));
	   
	   Connection connection= Connection.getConnection(daofactory);
       
       ResultSet idList = null;
       
		try {
			 idList = connection.executeUpdate(insertQuery);
			 if (idList==null){
				throw new CommonException("Errore nell'inserimento del noleggio");
			 }
			// qui assegno l'optional ad un noleggio
			 if(!noleggio.getOptional().isEmpty()){
				 insertQuery= "Insert into NoleggioOptional values ('?','?');";
				 insertQuery = queryReplaceFirst(insertQuery, String.valueOf(conta()));
				 String queryPartenza=insertQuery;
				 for(int i=0;i< noleggio.getOptional().size();i++){
					 insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getOptional().get(i)));
					 idList = connection.executeUpdate(insertQuery);
					 insertQuery = queryPartenza;
					 }
			}
		
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 AlertView.getAlertView("Non è stato possibile inserire il Noleggio" , AlertType.ERROR);
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.showMessage();
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
	public void aggiornamento(Entity entity) throws CommonException {
		Noleggio noleggio= (Noleggio)entity;
		String updateQuery = "Update Noleggio set "
								+ "note='?' , stato='annullato' , FineNoleggio='?' where IDNoleggio='?'";
		updateQuery= queryReplaceFirst(updateQuery,noleggio.getNote());
		updateQuery= queryReplaceFirst(updateQuery,noleggio.getFineNoleggio().toString());
		updateQuery= queryReplaceFirst(updateQuery,String.valueOf(noleggio.getIDNoleggio()));
		
		 
	   Connection connection= Connection.getConnection(daofactory);
       
       ResultSet update = null;
       try {
		update = connection.executeUpdate(updateQuery);
		 if (update==null){
				throw new CommonException("Errore durante l'aggiornamento del noleggio");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
       finally{
			try {
				update.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void aggiornamentoCompleto(Entity entity) throws CommonException {
		Noleggio noleggio= (Noleggio)entity;
		String updateQuery = "Update Noleggio set "
								+ "FineNoleggio='?',kmRientro='?',Stato='?',Note='?' where IDNoleggio='?'";
		updateQuery= queryReplaceFirst(updateQuery,noleggio.getFineNoleggio().toString());
		updateQuery= queryReplaceFirst(updateQuery,String.valueOf(noleggio.getKmRientro()));
		updateQuery= queryReplaceFirst(updateQuery,noleggio.getStato().toString());
		updateQuery= queryReplaceFirst(updateQuery,noleggio.getNote());
		updateQuery= queryReplaceFirst(updateQuery,String.valueOf(noleggio.getIDNoleggio()));
		 
	   Connection connection= Connection.getConnection(daofactory);
       
       ResultSet update = null;
       try {
		update = connection.executeUpdate(updateQuery);
		 if (update==null){
				throw new CommonException("Errore durante l'aggiornamento del noleggio");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
       finally{
			try {
				update.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	public List<Noleggio> getAll(){
		 String readQuery = "Select * from Noleggio";
		 
		Connection connection= Connection.getConnection(daofactory);
		 
		ResultSet readQueryResultSet = null;
		List<Noleggio> risultato = null;
		try {
			readQueryResultSet = connection.executeRead(readQuery);	
			if(readQueryResultSet!=null)
				risultato= creaElencoNoleggi(readQueryResultSet,connection);
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
		return risultato;
	}
	
	
	
	public List<Noleggio> getNoleggiAperti(int idContratto){
		 String readQuery = "Select * from Noleggio where IDContratto='?' and (Stato='Aperto' or Stato='rientro' or Stato='uscita')";
		 readQuery = queryReplaceFirst(readQuery, String.valueOf(idContratto));
		 
		Connection connection= Connection.getConnection(daofactory);
		 
		ResultSet readQueryResultSet = null;
		List<Noleggio> risultato = null;
		try {
			readQueryResultSet = connection.executeRead(readQuery);	
			if(readQueryResultSet!=null)
				risultato= creaElencoNoleggi(readQueryResultSet,connection);
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
		return risultato;
	}
	public List<Noleggio> getNoleggiByContratto(int idContratto){
		 String readQuery = "Select * from Noleggio where IDContratto='?'";
		 readQuery = queryReplaceFirst(readQuery, String.valueOf(idContratto));
		 
		Connection connection= Connection.getConnection(daofactory);
		 
		ResultSet readQueryResultSet = null;
		List<Noleggio> risultato = null;
		try {
			readQueryResultSet = connection.executeRead(readQuery);	
			if(readQueryResultSet!=null)
				risultato= creaElencoNoleggi(readQueryResultSet,connection);
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
		return risultato;
	}
	private List<Noleggio> creaElencoNoleggi(ResultSet resultset,Connection connection){
		List<Noleggio> noleggi=new LinkedList<Noleggio>();
        try {
         if(resultset!=null){
            while (resultset.next()) {
                noleggi.add((Noleggio) ottieniNoleggio(resultset,connection));
            }
         }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
		return noleggi;
	}
	private List<Entity> creaElencoNoleggi2(ResultSet resultset,Connection connection){
		List<Entity> noleggi=new LinkedList<Entity>();
        try {
         if(resultset!=null){
            while (resultset.next()) {
                noleggi.add(ottieniNoleggio(resultset,connection));
            }
         }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
		return noleggi;
	}
	private Entity ottieniNoleggio(ResultSet resultset,Connection connection) throws SQLException{
		List<Integer> idOptionals= ottieniIDOptional(resultset,connection);
		List<Integer> idMulta  = ottieniIDMulte(resultset,connection);

		LocalDate data=null;
		if(resultset.getDate(3)!=null){
			data= resultset.getDate(3).toLocalDate();
		}
		return  new Noleggio(resultset.getInt(1),
				resultset.getDate(2).toLocalDate(),
				data,
				resultset.getDate(4).toLocalDate(),
				resultset.getDate(5).toLocalDate(),
				resultset.getInt(6),
				resultset.getInt(7),
				idOptionals,
				idMulta,
				StatoNoleggio.toStatoNoleggio(resultset.getString(8)),
				resultset.getInt(9),
				resultset.getInt(10),
				resultset.getInt(11),
				resultset.getInt(12),
				resultset.getInt(13),
				resultset.getInt(14),
				resultset.getInt(15),
				resultset.getString(16));
	}
	
	private List<Integer> ottieniIDOptional(ResultSet resultset,Connection connection) throws SQLException{
		String readQuery  = "Select IDOptional from NoleggioOptional where IDNoleggio= '?'";
		readQuery = queryReplaceFirst(readQuery, String.valueOf(resultset.getInt(1)));
		
		ResultSet readQueryResultSet = null;
		List<Integer> idOptionals = null;
		try {
			readQueryResultSet = connection.executeRead(readQuery);	
			idOptionals= new ArrayList<Integer>();
			while(readQueryResultSet.next()){
				idOptionals.add(resultset.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere gli optional associati al noleggio" , AlertType.ERROR);
		}
		finally{
			try {
				readQueryResultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		return idOptionals;
	}
	
	private List<Integer> ottieniIDMulte(ResultSet resultset,Connection connection) throws SQLException{
		String readQuery  = "Select IDMulta from Multa where IDNoleggio= '?'";
		readQuery = queryReplaceFirst(readQuery, String.valueOf(resultset.getInt(1)));
		ResultSet readQueryResultSet = null;
		List<Integer> idMulta=null;
		try {
			readQueryResultSet = connection.executeRead(readQuery);	
			idMulta= new ArrayList<Integer>();
			while(readQueryResultSet.next()){
				idMulta.add(resultset.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere le multe associate al noleggio" , AlertType.ERROR);
		}
		finally{
			try {
				readQueryResultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		return idMulta;
	}
	

	public List<Entity> ricerca(Entity parameter){
		RicercaNoleggio ricnoleggio=(RicercaNoleggio) parameter;
		String ricerca1="Select * from Noleggio where IDContratto='?'";
		
		String ricerca2="Select * from Noleggio where InizioNoleggio='?'";
		String ricerca3="Select * from Noleggio where Stato='?'";
		
		
		String ricerca5="Select * from Noleggio where IDContratto='?' and InizioNoleggio='?'";
		String ricerca6="Select * from Noleggio where InizioNoleggio='?' and Stato='?'";
		String ricerca7="Select * from Noleggio where IDContratto='?' and  Stato='?'";
	
		
		String ricerca4="Select * from Noleggio where IDContratto='?' and InizioNoleggio='?' and Stato='?'";
		
		LocalDate dInizio= ricnoleggio.getdInizio();
	    int idContratto= ricnoleggio.getidContratto();
	    StatoNoleggio stato= ricnoleggio.getStato();
	    List<Entity> noleggi =null;
	    
		if(dInizio!=null && idContratto==0 && stato.toString().equals(StatoNoleggio.vuoto.toString())){
			ricerca2 =queryReplaceFirst(ricerca2,dInizio.toString());
			noleggi= interroga(ricerca2);
		}
		else if(dInizio==null && idContratto!=0 && stato.toString().equals(StatoNoleggio.vuoto.toString())){
			ricerca1 =queryReplaceFirst(ricerca1,String.valueOf(idContratto));
			noleggi= interroga(ricerca1);
		}
		else if(dInizio==null && idContratto==0 && !stato.toString().equals(StatoNoleggio.vuoto.toString())){
			ricerca3 =queryReplaceFirst(ricerca3, stato.toString());
			noleggi = interroga(ricerca3);
		}
		else if(dInizio!=null && idContratto!=0 && !stato.toString().equals(StatoNoleggio.vuoto.toString())){
			ricerca4 =queryReplaceFirst(ricerca4, String.valueOf(idContratto));	
			ricerca4 =queryReplaceFirst(ricerca4, dInizio.toString());	
			ricerca4 =queryReplaceFirst(ricerca4, stato.toString());	
			noleggi = interroga(ricerca4);
		}
		else if(dInizio!=null && idContratto!=0 && stato.toString().equals(StatoNoleggio.vuoto.toString())){
			ricerca5 =queryReplaceFirst(ricerca5,String.valueOf(idContratto));
			ricerca5 =queryReplaceFirst(ricerca5,dInizio.toString());
			noleggi = interroga(ricerca5);
		}
		else if(dInizio!=null && idContratto==0 && !stato.toString().equals(StatoNoleggio.vuoto.toString())){
			ricerca6 =queryReplaceFirst(ricerca6,dInizio.toString());
			ricerca6 =queryReplaceFirst(ricerca6,stato.toString());
			noleggi= interroga(ricerca6);
		}
		
		else if(dInizio==null && idContratto!=0 && !stato.toString().equals(StatoNoleggio.vuoto.toString())){
			ricerca7 =queryReplaceFirst(ricerca7,String.valueOf(idContratto));
			ricerca7 =queryReplaceFirst(ricerca7,stato.toString());
			noleggi= interroga(ricerca7);
		}
		return noleggi;
	}
	private List<Entity> interroga(String query){
		Connection connection= Connection.getConnection(daofactory);
		 
		ResultSet readQueryResultSet = null;
		List<Entity> risultato = null;
		try {
			readQueryResultSet = connection.executeRead(query);	
			if(readQueryResultSet!=null)
				risultato= creaElencoNoleggi2(readQueryResultSet,connection);
		} catch (SQLException e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere i noleggi" , AlertType.ERROR);
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
}
