package business.model;

import java.util.ArrayList;
import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOAmministratore;
import business.entity.Entity;
import business.entity.Gestori.Amministratore;
import business.model.Exception.CommonException;

public class ModelAmministratore implements Model{
	private DaoFactory daofactory;
	@Override
	public void Inserimento(Entity parameter) throws CommonException {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			((DAOAmministratore) daofactory.getDao("DAOAmministratore")).creazione(parameter);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		
	}



	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			((DAOAmministratore) daofactory.getDao("DAOAmministratore")).aggiornamento(parameter);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		
		
	}

	@Override
	public Entity lettura(int id) {
		try {
		if(daofactory==null)
			daofactory= DaoFactory.getDaoFactory(1);
		return (Amministratore) ((DAOAmministratore) daofactory.getDao("DAOAmministratore")).lettura(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public List<Amministratore> getAll(){
		List<Amministratore> r=new ArrayList<Amministratore>();
		try{
		if(daofactory==null)
			daofactory= DaoFactory.getDaoFactory(1);
		r=  ((DAOAmministratore) daofactory.getDao("DAOAmministratore")).getAll();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public Entity leggiAmministratoreByCodiceFiscale(String c){
		try{
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			return ((DAOAmministratore) daofactory.getDao("DAOAmministratore")).leggiAmministratoreByCodiceFiscale(c);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		return null;
	}




	@Override
	public List<Entity> ricerca(Entity parameter) {
		// TODO Auto-generated method stub
		return new ArrayList<Entity>();
	}
}
