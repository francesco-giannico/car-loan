package business.model;

import business.entity.Entity;
import business.entity.Auto.Fascia.Fascia;
import business.model.Exception.CommonException;
import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOFascia;

import java.util.ArrayList;
import java.util.List;
public class ModelFascia implements Model{
	private DaoFactory daofactory;
	@Override
	public void Inserimento(Entity parameter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		// TODO Auto-generated method stub
		
	}

	public List<Fascia> getAll(){
		List<Fascia> l=new ArrayList<Fascia>();
		try {
			daofactory = DaoFactory.getDaoFactory(1);
			l=((DAOFascia)daofactory.getDao("DAOFascia")).getAll();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public Entity lettura(int id) {
		try {
			daofactory = DaoFactory.getDaoFactory(1);
			return ((DAOFascia)daofactory.getDao("DAOFascia")).lettura(id);
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
