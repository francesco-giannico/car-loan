package integration.DAO.entity;

import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;
import static utility.QueryStringReplacer.queryReplaceFirst;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.Disponibilita;
import business.entity.Auto.Danni;
import business.model.Exception.CommonException;

public class DAOAutoveicolo implements DAO{
private DaoFactory dao;
public DAOAutoveicolo(DaoFactory dao) {
	this.dao=dao;
}

	@Override
	public ResultSet creazione(Entity x) throws CommonException {
		String insert="insert into Autoveicolo (Targa,Marca,Modello,AlimPrincipale,Colore,Cambio,Immatricolazione,"
				+ "Cilindrata,Potenza,NroPosti,NroTelaio,Disponibilita,UltimoKm,CapPortaBagagli,Note,DataScadAssic,OptionalAuto,Prezzo,DanniFutili,DanniGravi,IDSede,IDFascia)";
		String values=" values('?','?','?','?','?','?','?',?,?,?,'?','?',?,?,'?','?','?',?,'?','?',?,?)";
		Autoveicolo a=(Autoveicolo)x;
		values=queryReplaceFirst(values, a.getTarga());
		values=queryReplaceFirst(values, a.getMarca());
		values=queryReplaceFirst(values, a.getModello());
		values=queryReplaceFirst(values, a.getAlimPrincipale());
		values=queryReplaceFirst(values, a.getColore());
		values=queryReplaceFirst(values, a.getCambio());
		values=queryReplaceFirst(values, a.getImmatricolazione().toString());
		values=queryReplaceFirst(values, String.valueOf(a.getCilindrata()));
		values=queryReplaceFirst(values, String.valueOf(a.getPotenza()));
		values=queryReplaceFirst(values, String.valueOf(a.getNroPosti()));
		values=queryReplaceFirst(values, a.getNroTelaio());
		values=queryReplaceFirst(values, a.getDisponibilita().toString());
		values=queryReplaceFirst(values, String.valueOf(a.getUltimoKm()));
		values=queryReplaceFirst(values, String.valueOf(a.getCapPortaBagnagli()));
		values=queryReplaceFirst(values, a.getNote());
		values=queryReplaceFirst(values, a.getDataScadAssic().toString());
		values=queryReplaceFirst(values, a.getOptionalAuto());
		values=queryReplaceFirst(values, String.valueOf(a.getPrezzo()));
		values=queryReplaceFirst(values, a.getDanni().getDanniFutili());
		values=queryReplaceFirst(values, a.getDanni().getDanniGravi());
		values=queryReplaceFirst(values, String.valueOf(a.getCodiceSedDisp()));
		values=queryReplaceFirst(values, String.valueOf(a.getFascia()));
		
		ResultSet s=null;
		Connection connection=Connection.getConnection(dao);
		try {
			 s=connection.executeUpdate(insert+values);
			if(s!=null && s.next()){
			AlertView.getAlertView("Autoveicolo inserito con successo",AlertType.INFORMATION);
			
			String image=a.getImmagine();
			if(!image.isEmpty()){
				InputStream i=new FileInputStream(new File(image));
				String query="update Autoveicolo Set Immagine=? Where IDAuto="+s.getInt(1);
				s=null;
				s=connection.executeUpdate_binary(query, i);
				if(s!=null)
					throw new CommonException("Non e' stato possibile inserire l'immagine dell'auto :|. \n Probabilmente qualche errore nei permessi in cui esso risiede");
			}
			}
			else
				throw new CommonException("Non e' stato possibile inserire l'autoveicolo");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new CommonException("Il file selezionato non e' stato trovato");
		}
		
		return s;
	}

	@Override
	public void aggiornamento(Entity x) throws CommonException {
		String update="UPDATE Autoveicolo SET Targa='?',Marca='?',Modello='?',AlimPrincipale='?',Colore='?',Cambio='?',Immatricolazione='?',"
				+ "Cilindrata=?,Potenza=?,NroPosti=?,NroTelaio='?',Disponibilita='?',UltimoKm=?,CapPortaBagagli=?,Note='?',DataScadAssic='?',OptionalAuto='?',Prezzo=?,DanniFutili='?',DanniGravi='?',IDSede=?,IDFascia=?";
		String where=" where IDAuto=?";
		Autoveicolo a=(Autoveicolo)x;
		update=queryReplaceFirst(update, a.getTarga());
		update=queryReplaceFirst(update, a.getMarca());
		update=queryReplaceFirst(update, a.getModello());
		update=queryReplaceFirst(update, a.getAlimPrincipale());
		update=queryReplaceFirst(update, a.getColore());
		update=queryReplaceFirst(update, a.getCambio());
		update=queryReplaceFirst(update, a.getImmatricolazione().toString());
		update=queryReplaceFirst(update, String.valueOf(a.getCilindrata()));
		update=queryReplaceFirst(update, String.valueOf(a.getPotenza()));
		update=queryReplaceFirst(update, String.valueOf(a.getNroPosti()));
		update=queryReplaceFirst(update, a.getNroTelaio());
		update=queryReplaceFirst(update, a.getDisponibilita().toString());
		update=queryReplaceFirst(update, String.valueOf(a.getUltimoKm()));
		update=queryReplaceFirst(update, String.valueOf(a.getCapPortaBagnagli()));
		update=queryReplaceFirst(update, a.getNote());
		update=queryReplaceFirst(update, a.getDataScadAssic().toString());
		update=queryReplaceFirst(update, a.getOptionalAuto());
		update=queryReplaceFirst(update, String.valueOf(a.getPrezzo()));
		update=queryReplaceFirst(update, a.getDanni().getDanniFutili());
		update=queryReplaceFirst(update, a.getDanni().getDanniGravi());
		update=queryReplaceFirst(update, String.valueOf(a.getCodiceSedDisp()));
		update=queryReplaceFirst(update, String.valueOf(a.getFascia()));
		where=queryReplaceFirst(where, String.valueOf(a.getIDauto()));
		ResultSet s=null;
		Connection connection=Connection.getConnection(dao);
		try {
			boolean updated=false;
			 s=connection.executeUpdate(update+where);
			if(s==null)
				updated=false;
			else
				updated=true;
			
			/*Faccio due update: 1 per tutti i campi tranne l'immagine e 1 per l'immagine(è più comodo)*/
			String image=a.getImmagine();
			if( image!=null && !image.isEmpty()){//<-- Ha scelto una nuova immagine, quindi bisogna aggiornare
				update="UPDATE Autoveicolo SET Immagine=? WHERE IDAuto="+a.getIDauto();
				s=null;
				InputStream i=new FileInputStream(new File(image));
				connection.executeUpdate_binary(update,i);
				updated=true;
				}
			if(!updated)
				throw new CommonException("Autoveicolo non aggiornato");
		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new CommonException("Il file selezionato non e' stato trovato");
		}
	}
 
	@Override
	public Entity lettura(int id) throws CommonException {
		String query="Select * from Autoveicolo where IDAuto=?";
		query=queryReplaceFirst(query, String.valueOf(id));
		Connection conn=Connection.getConnection(dao);
		Autoveicolo auto=null;
		try {
			ResultSet r=conn.executeRead(query);
			if(r!=null && r.next()){
				auto=ottieniAutoveicolo(r);
			}
			else
				throw new CommonException("Non è stato possibile trovare l'auto richiesta");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return auto;
	}

	public List<Autoveicolo> getAll(){
		
			 String readQuery = "Select * from Autoveicolo";

			 Connection connection= Connection.getConnection(dao);
		        
		     ResultSet readQueryResultSet = null;
		     List<Autoveicolo> risultato = null;
		     try {
				readQueryResultSet = connection.executeRead(readQuery);	
				risultato= creaElencoAuto(readQueryResultSet);
			 } catch (SQLException  e) {
				e.printStackTrace();
				AlertView.getAlertView("Non è stato possibile leggere le auto" , AlertType.ERROR);
			 }
			 finally{
				try {
					readQueryResultSet.close();
					//connection.chiudiConnessione();
					} catch (SQLException e) {
						e.printStackTrace();
				}
			}

		    return risultato;
		
		
	}
	
	public List<Autoveicolo> getAllAutoByFascia(int id){
		
		 String readQuery = "Select * from Autoveicolo where IDFascia=?";
		 readQuery=queryReplaceFirst(readQuery,String.valueOf(id));
		 
		 Connection connection= Connection.getConnection(dao);
	        
	     ResultSet readQueryResultSet = null;
	     List<Autoveicolo> risultato = null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoAuto(readQueryResultSet);
		 } catch (SQLException  e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere le auto" , AlertType.ERROR);
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
	
	public List<Autoveicolo> getAllAutoByFasciaAssicurazione(int id){
		
		 String readQuery = "Select * from Autoveicolo where IDFascia=? and Disponibilita='Disponibile' and datediff(DataScadAssic,curdate())>27;";
		 readQuery=queryReplaceFirst(readQuery,String.valueOf(id));
		 
		 Connection connection= Connection.getConnection(dao);
	        
	     ResultSet readQueryResultSet = null;
	     List<Autoveicolo> risultato = null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoAuto(readQueryResultSet);
		 } catch (SQLException  e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere le auto" , AlertType.ERROR);
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
	private List<Autoveicolo> creaElencoAuto(ResultSet readQueryResultSet) {
		List<Autoveicolo> leauto=new LinkedList<Autoveicolo>();
		if(readQueryResultSet!=null){
			try {
				while(readQueryResultSet.next()){
					Autoveicolo a= ottieniAutoveicolo(readQueryResultSet);
					leauto.add(a);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return leauto;
	}
	
	private Autoveicolo ottieniAutoveicolo(ResultSet resultset) throws SQLException{
		Autoveicolo a=new Autoveicolo();
		a.setIDauto(resultset.getInt(1));
		a.setTarga(resultset.getString(2));
		a.setMarca(resultset.getString(3));
		a.setModello(resultset.getString(4));
		a.setAlimPrincipale(resultset.getString(5));
		a.setAlimSec(resultset.getString(6));
		a.setColore(resultset.getString(7));
		a.setCambio(resultset.getString(8));
		a.setImmatricolazione(resultset.getDate(9).toLocalDate());
		a.setCilindrata(resultset.getInt(10));
		a.setPotenza(resultset.getInt(11));
		a.setNroPosti(resultset.getInt(12));
		a.setNroTelaio(resultset.getString(13));
		a.setDisponibilita(Disponibilita.toDisponibilita(resultset.getString(14)));
		a.setUltimoKm(resultset.getInt(15));
		a.setCapPortaBagnagli(resultset.getInt(16));
		a.setNote(resultset.getString(17));
		InputStream i=resultset.getBinaryStream(18);
		if(i!=null){
			a.setImmagine_stream(i);
		}
		a.setDataScadAssic(resultset.getDate(19).toLocalDate());
		a.setOptionalAuto(resultset.getString(20));
		a.setPrezzo(resultset.getFloat(21));
		Danni danni=new Danni(resultset.getString(22), resultset.getString(23));
		a.setDanni(danni);
		a.setCodiceSedDisp(resultset.getInt(24));
		a.setFascia(resultset.getInt(25));
		return a;
	}
/**
 * Utile per leggere soltanto l'immagine per la tabella
 * @param id
 * @return
 */
	public InputStream leggi_immagine(int id) {
		String query="Select Immagine from Autoveicolo where IDAuto=?";
		query=queryReplaceFirst(query, String.valueOf(id));
		Connection connection=Connection.getConnection(dao);
		InputStream i=null;
		ResultSet readQueryResultSet=null;
	     try {
	    	  readQueryResultSet = connection.executeRead(query);
	    	  if(readQueryResultSet.next())
	    	 i=readQueryResultSet.getBinaryStream(1);
		 } catch (SQLException  e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere l'auto" , AlertType.ERROR);
		 }
		 finally{
			
			try {
				if(readQueryResultSet!=null)
				readQueryResultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}

		return i;
	}
	
	
	public List<Autoveicolo> getAllAutoDisponibiliBySede(int id){
		 String readQuery = "Select * from Autoveicolo where IDSede=? and Disponibilita='Disponibile'";
		 readQuery=queryReplaceFirst(readQuery,String.valueOf(id));
		 
		 Connection connection= Connection.getConnection(dao);
	        
	     ResultSet readQueryResultSet = null;
	     List<Autoveicolo> risultato = null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoAuto(readQueryResultSet);
		 } catch (SQLException  e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere le auto" , AlertType.ERROR);
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
	public List<Autoveicolo> getAllAutoDisponibiliBySedeAndFascia(List<Entity> lista){
		 String readQuery = "Select * from Autoveicolo where IDSede=? and Disponibilita='Disponibile' and IDFascia=?";
		 readQuery=queryReplaceFirst(readQuery,String.valueOf(lista.get(0)));
		 readQuery=queryReplaceFirst(readQuery,String.valueOf(lista.get(1)));
		 Connection connection= Connection.getConnection(dao);
	        
	     ResultSet readQueryResultSet = null;
	     List<Autoveicolo> risultato = null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoAuto(readQueryResultSet);
		 } catch (SQLException  e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere le auto" , AlertType.ERROR);
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
	public List<Autoveicolo> getAllAutoBySedeAndFascia(List<Entity> lista){
		 String readQuery = "Select * from Autoveicolo where IDSede=?  and IDFascia=?";
		 readQuery=queryReplaceFirst(readQuery,String.valueOf(lista.get(0)));
		 readQuery=queryReplaceFirst(readQuery,String.valueOf(lista.get(1)));
		 Connection connection= Connection.getConnection(dao);
	        
	     ResultSet readQueryResultSet = null;
	     List<Autoveicolo> risultato = null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoAuto(readQueryResultSet);
		 } catch (SQLException  e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere le auto" , AlertType.ERROR);
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
	public List<Autoveicolo> getAllAutoDisponibiliBySedeAndFasciaAndAssicurazione(List<Entity> lista){
		 String readQuery = "Select * from Autoveicolo where IDSede='?' and Disponibilita='Disponibile' and IDFascia='?' and datediff(DataScadAssic,curdate())>27;";
		 readQuery=queryReplaceFirst(readQuery,String.valueOf(lista.get(0)));
		 readQuery=queryReplaceFirst(readQuery,String.valueOf(lista.get(1)));
		 Connection connection= Connection.getConnection(dao);
	        
	     ResultSet readQueryResultSet = null;
	     List<Autoveicolo> risultato = null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoAuto(readQueryResultSet);
		 } catch (SQLException  e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere le auto" , AlertType.ERROR);
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
