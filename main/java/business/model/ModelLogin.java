package business.model;



import java.util.ArrayList;
import java.util.List;

import business.entity.Entity;
import business.model.Exception.CommonException;
import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOLogin;

public class ModelLogin implements Model {
	private DaoFactory daofactory;


	
	
	public Entity autenticazione(Entity parameter){
		Entity ent=null;
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);			
			return ((DAOLogin) daofactory.getDao("DAOLogin")).autenticazione(parameter);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return ent;
	}

	@Override
	public void Inserimento(Entity parameter) {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);			
		 ((DAOLogin) daofactory.getDao("DAOLogin")).creazione(parameter);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void aggiornamento(Entity parameter) {
		
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);			
		 ((DAOLogin) daofactory.getDao("DAOLogin")).aggiornamento(parameter);;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Verifica che l'username da registrare non è già presente nel database
	 * @param l
	 */
	public void VerificaCredenziali(Entity l) throws CommonException{
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);			
			 ((DAOLogin) daofactory.getDao("DAOLogin")).verifica_credenziali(l);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getUsername(Entity x){
		String ret = null;
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);			
		ret=	 ((DAOLogin) daofactory.getDao("DAOLogin")).getUsername(x);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return ret;
	}


	@Override
	public List<Entity> ricerca(Entity parameter) {
		// TODO Auto-generated method stub
		return new ArrayList<Entity>();
	}
	
}
