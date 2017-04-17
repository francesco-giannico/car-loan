package integration.DAO.entity;
import static utility.QueryStringReplacer.queryReplaceFirst;
import integration.DAO.DaoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;



import java.util.List;

import business.entity.Entity;
import business.entity.Auto.Fascia.Fascia;
import business.entity.Auto.Fascia.Lusso;
import business.entity.Auto.Fascia.Suv;
import business.entity.Auto.Fascia.Utlitaria;
import business.model.Exception.CommonException;
import integration.DAO.connection.Connection;
public class DAOFascia implements DAO {
	DaoFactory daofactory;
	public DAOFascia(DaoFactory dao){
		this.daofactory = dao;		
	}
	@Override
	public ResultSet creazione(Entity x) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiornamento(Entity entity) throws CommonException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity lettura(int id) {
		String read = "Select * from Fascia where IDFascia='?'";
		read= queryReplaceFirst(read,String.valueOf(id));
		Connection c=Connection.getConnection(daofactory);
		try {
			ResultSet r=c.executeRead(read);
			if (r!=null){
				while(r.next()){
						
				 switch(r.getString(4).toLowerCase()){
					case "suv":
						return new Suv(r.getInt(1),r.getFloat(2),r.getString(3),r.getString(4),r.getFloat(5));
					case "lusso":
						return new Lusso(r.getInt(1),r.getFloat(2),r.getString(3),r.getString(4),r.getFloat(5));
					case "utilitaria":
						return new Utlitaria(r.getInt(1),r.getFloat(2),r.getString(3),r.getString(4),r.getFloat(5));
						default:
							return null;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Fascia> getAll(){
		String query="Select * from Fascia";
		Connection c=Connection.getConnection(daofactory);
		List<Fascia> fasce =new LinkedList<Fascia>();
		try {
			ResultSet r=c.executeRead(query);
			if(r!=null){
				
				while(r.next()){
					
					switch(r.getString(4).toLowerCase()){
					case "suv":
						fasce.add(new Suv(r.getInt(1),r.getFloat(2),r.getString(3),r.getString(4),r.getFloat(5)));
						break;
					case "lusso":
						fasce.add(new Lusso(r.getInt(1),r.getFloat(2),r.getString(3),r.getString(4),r.getFloat(5)));
						break;
					case "utilitaria":
						fasce.add(new Utlitaria(r.getInt(1),r.getFloat(2),r.getString(3),r.getString(4),r.getFloat(5)));
						break;
						default :
							
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fasce;
	}
}
