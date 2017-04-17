package business.model;

import java.util.ArrayList;
import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOAgenzia;
import business.entity.Entity;
import business.entity.Luoghi.Agenzia;
import business.model.Exception.CommonException;

public class ModelAgenzia implements Model{
	private DaoFactory daofactory;
	

	
	@Override
	public void Inserimento(Entity parameter) throws CommonException {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOAgenzia)daofactory.getDao("DAOAgenzia")).creazione(parameter);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOAgenzia)daofactory.getDao("DAOAgenzia")).aggiornamento(parameter);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public Entity lettura(int id) {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOAgenzia)daofactory.getDao("DAOAgenzia")).lettura(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Agenzia> getAll(){
		List<Agenzia> r=new ArrayList<Agenzia>();
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			r= ((DAOAgenzia)daofactory.getDao("DAOAgenzia")).getAll();
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public List<Entity> ricerca(Entity parameter) {
		// TODO Auto-generated method stub
		return new ArrayList<Entity>();
	}
}
