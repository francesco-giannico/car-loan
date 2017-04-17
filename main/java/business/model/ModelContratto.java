package business.model;

import java.util.ArrayList;
import java.util.List;

import integration.DAO.DaoFactory;
import business.entity.Entity;
import business.entity.Noleggio.Contratto;
import business.model.Exception.CommonException;
import integration.DAO.entity.DAOContratto;

public class ModelContratto implements Model{
	private DaoFactory daofactory;
	
	@Override
	public void Inserimento(Entity parameter) {
		try {
		   if(daofactory==null)
		 	daofactory= DaoFactory.getDaoFactory(1);
		  ((DAOContratto) daofactory.getDao("DAOContratto")).creazione(parameter);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			((DAOContratto) daofactory.getDao("DAOContratto")).aggiornamento(parameter);;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}	
	}

	public List<Contratto> getAll(){
		List<Contratto> l=new ArrayList<Contratto>();
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			l= ((DAOContratto) daofactory.getDao("DAOContratto")).getAll();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return l;	
	}


	@Override
	public Entity lettura(int id) {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			return (Contratto) ((DAOContratto) daofactory.getDao("DAOContratto")).lettura(id);
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
