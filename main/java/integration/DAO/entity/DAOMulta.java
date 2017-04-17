package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Noleggio.Multa;
import business.entity.Noleggio.StatoMulta;


public class DAOMulta implements DAO{

	
	private  DaoFactory daofactory;
	
	
	public DAOMulta(DaoFactory dao){
		this.daofactory = dao;		
	}
	@Override
	public ResultSet creazione(Entity x) {
		
		String insertQuery = "INSERT INTO Multa"
				+ "(DataMulta,DataScadenza,Importo,Note,Stato,IDNoleggio)"
				+ "	values('?','?','?','?','?','?');";
		
		Multa multa= (Multa)x;

        insertQuery = queryReplaceFirst(insertQuery, multa.getDataMulta().toString());
        
        insertQuery= queryReplaceFirst(insertQuery,multa.getDataScadenza().toString());

        insertQuery= queryReplaceFirst(insertQuery,String.valueOf(multa.getImporto()));

        insertQuery= queryReplaceFirst(insertQuery,multa.getNote());

        insertQuery= queryReplaceFirst(insertQuery,multa.getStato().toString());

        insertQuery= queryReplaceFirst(insertQuery,String.valueOf(multa.getIdNoleggio()));
        
   
    
        Connection connection= Connection.getConnection(daofactory);
        
        ResultSet idList = null;
        
		try {
			 idList = connection.executeUpdate(insertQuery);
			 if(idList!=null)
				 AlertView.getAlertView("Multa inserita con successo",AlertType.INFORMATION);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 AlertView.getAlertView("Non è stato possibile inserire la multa" , AlertType.ERROR);
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
	public void aggiornamento(Entity x) {
		Multa multa= (Multa)x;
		String updateQuery= "UPDATE Multa set "
				+ "DataPagamento='?', UlterioreAddebitoRitardi='?',Stato='?',Note='?' where IDMulta='?'";
		updateQuery= queryReplaceFirst(updateQuery, multa.getDataPagamento().toString());
		updateQuery= queryReplaceFirst(updateQuery, String.valueOf(multa.getUlterioreAddebito()));
		updateQuery= queryReplaceFirst(updateQuery, multa.getStato().toString());
		updateQuery= queryReplaceFirst(updateQuery, multa.getNote());
		updateQuery= queryReplaceFirst(updateQuery, String.valueOf(multa.getIDMulta()));
		
		Connection connection= Connection.getConnection(daofactory);
        
        ResultSet idList = null;
        try {
			 idList = connection.executeUpdate(updateQuery);
			 if(idList!=null)
				 AlertView.getAlertView("Multa chiusa con successo", AlertType.INFORMATION);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 AlertView.getAlertView("Non è stato possibile aggiornare la multa" , AlertType.ERROR);
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

	@Override
	public Entity lettura(int id) {
		
		return null;
	}
	public List<Multa> getAllMulteByNoleggio(int id){
		String readQuery= "Select * from Multa where IDNoleggio= '?'";
		readQuery= queryReplaceFirst(readQuery,String.valueOf(id));
		List<Multa> multe=null;
		Connection connection= Connection.getConnection(daofactory);
        
        ResultSet idList = null;
        try {
			 idList = connection.executeRead(readQuery);
			 multe=creaElencoMulta(idList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 AlertView.getAlertView("Non è stato possibile inserire la multa" , AlertType.ERROR);
		}
		finally{
			try {
				idList.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return  multe;
	}
	
	private List<Multa> creaElencoMulta(ResultSet resultset) {
		List<Multa> multe=new ArrayList<Multa>();
		if(resultset!=null){
		try {
			while(resultset.next()){
				multe.add(ottieniMulta(resultset));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return multe;
	}
	
	private Multa ottieniMulta(ResultSet resultset){
		try {
			LocalDate data=null;
			if(resultset.getDate(7)!=null){
				data= resultset.getDate(7).toLocalDate();
			}
			return new Multa(resultset.getInt(1),resultset.getDate(2).toLocalDate(),
					resultset.getFloat(3),StatoMulta.toState(resultset.getString(5)),resultset.getString(4),
					resultset.getInt(9),resultset.getDate(6).toLocalDate(),resultset.getFloat(8),data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int countMulteAperteByNoleggio(int id){
		String count= "Select count(IDMulta) as Numeromulte from Multa where IDNoleggio='?' and Stato='aperto'";
		count= queryReplaceFirst(count,String.valueOf(id));
		int numero=0;
		Connection connection= Connection.getConnection(daofactory);
        
        ResultSet countElements = null;
        try {
        	countElements = connection.executeRead(count);
			 if(countElements!=null){
				 while(countElements.next()){
					 numero= countElements.getInt("numeromulte");
				 } 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 AlertView.getAlertView("Non è stato possibile inserire la multa" , AlertType.ERROR);
		}
		finally{
			try {
				countElements.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return numero;
		
	}
}
