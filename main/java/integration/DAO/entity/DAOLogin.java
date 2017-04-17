
package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;

import java.sql.ResultSet;
import java.sql.SQLException;







import utility.Crittografia;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;
import business.entity.Entity;
import business.entity.Login;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.Operatore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import business.model.Exception.CommonException;


public class DAOLogin implements DAO{
	

	private  DaoFactory daofactory;
	
	
	public DAOLogin(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	
	@Override
	public ResultSet creazione(Entity x){
		String INSERT = "INSERT INTO Credenziali(Username,Password,IDSupervisoreAgenzia,IDSupervisoreSede,IDAmministratore,IDOperatore) values('?','?',?,?,?,?);";
		
		String insertQuery = INSERT;
				
		Login login= (Login)x;

        insertQuery = queryReplaceFirst(insertQuery, login.getUsername());
        
        insertQuery= queryReplaceFirst(insertQuery,Crittografia.CriptaPassword(login.getPassword()));
        String s=login.getSupA();
        if(s==null)
        	insertQuery= queryReplaceFirst(insertQuery,"null");
        else
        insertQuery= queryReplaceFirst(insertQuery,s);
        s=login.getSupS();
        if(s==null)
        	 insertQuery= queryReplaceFirst(insertQuery,"null");
        else
        insertQuery= queryReplaceFirst(insertQuery,s);
        s=login.getAmministratore();
        if(s==null)
        	insertQuery= queryReplaceFirst(insertQuery,"null");
        else
        insertQuery= queryReplaceFirst(insertQuery,s);
        s=login.getOperatore();
        if(s==null)
        	insertQuery= queryReplaceFirst(insertQuery,"null");
        else
        insertQuery= queryReplaceFirst(insertQuery,s);
        Connection connection= Connection.getConnection(daofactory);
        ResultSet idList = null;
		try {
			 idList = connection.executeUpdate(insertQuery);
		} catch (SQLException e) {
			
			e.printStackTrace();
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
		Login login=(Login)x;
		
		String INSERT = "Update Credenziali set Username='?'";
		String where="where ";
		
		String insertQuery = INSERT;

        insertQuery = queryReplaceFirst(insertQuery, login.getUsername());
        String pswrd=login.getPassword();
        if(!"".equals(pswrd)){
        	insertQuery+=",Password='?'";
        insertQuery= queryReplaceFirst(insertQuery,Crittografia.CriptaPassword(pswrd));
        }
        insertQuery+=" "+where;
       String n=login.getAmministratore();
       if(n!=null){
    	   insertQuery+="idamministratore='?'";
    	   insertQuery=queryReplaceFirst(insertQuery,n);
       }
       n=login.getSupA();
       if(n!=null){
    	   insertQuery+="idsupervisoreagenzia='?'";
    	   insertQuery=queryReplaceFirst(insertQuery,n);
       }
       n=login.getSupS();
       if(n!=null){
    	   insertQuery+="idsupervisoresede='?'";
    	   insertQuery=queryReplaceFirst(insertQuery,n);
       }
       
       n=login.getOperatore();
       if(n!=null){
    	   insertQuery+="idoperatore='?'";
    	   insertQuery=queryReplaceFirst(insertQuery,n);
       }
        Connection connection= Connection.getConnection(daofactory);
        ResultSet idList = null;
		try {
			 idList = connection.executeUpdate(insertQuery);
		} catch (SQLException e) {
			
			e.printStackTrace();
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
	


	
	public Entity  autenticazione(Entity x){
		
		
		Login login= (Login)x;
		
		String Autenticazione = "select IDSupervisoreAgenzia,IDSupervisoreSede,IDAmministratore,IDOperatore from Credenziali where Username='?' and Password='?'";
		
		String insertQuery = Autenticazione;
	

        insertQuery = queryReplaceFirst(insertQuery, login.getUsername());
        
        insertQuery = queryReplaceFirst(insertQuery, login.getPassword());
        
		Connection connection= Connection.getConnection(daofactory);
        Entity result = null;
		ResultSet idList = null;
		//se non è vuoto il resultset.
		try {
			idList = connection.executeRead(insertQuery);
			
			if(idList!=null && idList.next()){
				
				if(idList.getInt(1)>0){//è un supervisoreAgenzia
					DAOSupervisoreA daoSupervisoreA ;
					daoSupervisoreA= (DAOSupervisoreA) daofactory.getDao("DAOSupervisoreA");
					result= daoSupervisoreA.lettura(idList.getInt(1));
				}
				
				else if(idList.getInt(2)>0){//è un supervisore sede
					DAOSupervisoreS daoSupervisoreS ;
					daoSupervisoreS= (DAOSupervisoreS) daofactory.getDao("DAOSupervisoreS");
					result=daoSupervisoreS.lettura(idList.getInt(2));
				}
				else if(idList.getInt(3)>0){// è un amministratore
					DAOAmministratore daoAmministratore ;
					daoAmministratore= (DAOAmministratore) daofactory.getDao("DAOAmministratore");
					result=daoAmministratore.lettura(idList.getInt(3));
				}
				else if(idList.getInt(4)>0){// è un operatore
					DAOOperatore daoOperatore;
					daoOperatore= (DAOOperatore) daofactory.getDao("DAOOperatore");
					result=daoOperatore.lettura(idList.getInt(4));
				}	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(idList!=null)
				try {
					idList.close();	
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return  result;
	}


	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	public void verifica_credenziali(Entity x) throws CommonException {
		Login l =(Login)x;
		String query="Select * from Credenziali where Username='?'";
		query=queryReplaceFirst(query, l.getUsername());
		Connection c=Connection.getConnection(this.daofactory);
		try {
			ResultSet r=c.executeRead(query);
			if(r.next())
				throw new CommonException("L'username è già stato scelto");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Legge l'username partendo dall'istanza di un utente
	 * @param x
	 * @return
	 */
	public String getUsername(Entity x){
		String query="Select username from Credenziali where";
		String ret = null;
		if(x instanceof Amministratore){
			Amministratore a=(Amministratore)x;
			query+=" IDAmministratore =?";
			query=queryReplaceFirst(query, String.valueOf(a.getIdUtente()));
		}
		else if(x instanceof SupervisoreAgenzia){
			SupervisoreAgenzia s=(SupervisoreAgenzia)x;
			query+=" IDSupervisoreagenzia =?";
			query=queryReplaceFirst(query, String.valueOf(s.getIdUtente()));
			
		}
		else if(x instanceof SupervisoreSede){
			SupervisoreSede s=(SupervisoreSede)x;
			query+=" IDSupervisoresede =?";
			query=queryReplaceFirst(query, String.valueOf(s.getIdUtente()));
		}
		else {
			Operatore s=(Operatore)x;
			query+=" IDOperatore =?";
			query=queryReplaceFirst(query, String.valueOf(s.getIdUtente()));
		}
		
	Connection c=Connection.getConnection(this.daofactory);
	try {
		ResultSet r=c.executeRead(query);
		if(r!=null && r.next())
			ret=r.getString(1);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return ret;
	}
	
}
