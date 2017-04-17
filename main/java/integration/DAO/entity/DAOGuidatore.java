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
import business.entity.Noleggio.Optional.Guidatore;
import business.model.Exception.CommonException;

public class DAOGuidatore implements DAO{
	private  DaoFactory daofactory;

	public DAOGuidatore(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	@Override
	public ResultSet creazione(Entity x)  {
		Guidatore guidatore= (Guidatore)x;
		String insertQuery = "insert into Guidatore "
						+"(Nome,Cognome,Indirizzo,CodFiscale,NumeroPatente,IDNoleggio) "
						+ "values('?','?','?','?','?','?');";
		insertQuery= queryReplaceFirst(insertQuery,guidatore.getNome());
		insertQuery= queryReplaceFirst(insertQuery,guidatore.getCognome());
		insertQuery= queryReplaceFirst(insertQuery,guidatore.getIndirizzo());
		insertQuery= queryReplaceFirst(insertQuery,guidatore.getCodFiscale());
		insertQuery= queryReplaceFirst(insertQuery,guidatore.getPatenteGuida());
		insertQuery= queryReplaceFirst(insertQuery,String.valueOf(guidatore.getIdNoleggio()));
		Connection connection= Connection.getConnection(daofactory);
		 
		ResultSet  insertQueryResultSet = null;
		try {
				insertQueryResultSet = connection.executeUpdate(insertQuery);	
				if(insertQueryResultSet==null){
					throw new CommonException("non è stato agguinto il guidatore");
				}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				throw new CommonException("non è stato agguiunto il guidatore");
			} catch (CommonException e1) {
				e1.printStackTrace();
			}
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.showMessage();
		}
		finally{
			try {
				insertQueryResultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		
		return insertQueryResultSet;
	}

	@Override
	public void aggiornamento(Entity entity) throws CommonException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<Guidatore> getAllByNoleggio(int idNoleggio){
		 String readQuery = "Select * from Guidatore where IDNoleggio='?'";
		 
		 readQuery= queryReplaceFirst(readQuery,String.valueOf(idNoleggio));
		 
		 Connection connection= Connection.getConnection(daofactory);
		 
		ResultSet readQueryResultSet = null;
		List<Guidatore> risultato = null;
		try {
				readQueryResultSet = connection.executeRead(readQuery);	
				risultato= creaElencoGuidatori(readQueryResultSet);
			
		} catch (SQLException e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere il guidatore " , AlertType.ERROR);
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
	
	
	private List<Guidatore> creaElencoGuidatori(ResultSet resultset){
		List<Guidatore> risultato = new LinkedList<>();
		Guidatore guidatore;
        try {
         if(resultset!=null){
            while (resultset.next()) {
                guidatore= ottieniGuidatore(resultset);
                risultato.add(guidatore);
            }
         }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
		return risultato;
	}
	
	private Guidatore ottieniGuidatore(ResultSet resultset){
	    Guidatore guidatore= new Guidatore();
	    try {
			guidatore.setCodFiscale(resultset.getString(5));
			guidatore.setNome(resultset.getString(2));
			guidatore.setCognome(resultset.getString(3));;
			guidatore.setId(resultset.getInt(1));
			guidatore.setIndirizzo(resultset.getString(4));
			guidatore.setIdNoleggio(resultset.getInt(7));
			guidatore.setPatenteGuida(resultset.getString(6));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guidatore;
	    
	}
}
