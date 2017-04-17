package business.model;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOOptional;

import java.util.ArrayList;
import java.util.List;

import business.entity.Entity;
import business.entity.Noleggio.Optional.Optional;
import business.model.Exception.CommonException;

public class ModelOptional implements Model{
	private DaoFactory daofactory;
	
	@Override
	public void Inserimento(Entity parameter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		// TODO Auto-generated method stub
		
	}


	
	public List<Optional> getAll(){
		List<Optional> l=new ArrayList<Optional>();
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			l= ((DAOOptional) daofactory.getDao("DAOOptional")).getAll();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return l;	
	}
	public List<Optional> getAllByNoleggio(int id){
		List<Optional> l=new ArrayList<Optional>();
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			l= ((DAOOptional) daofactory.getDao("DAOOptional")).getAllByNoleggio(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return l;
	}
	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entity> ricerca(Entity parameter) {
		// TODO Auto-generated method stub
		return new ArrayList<Entity>();
	}
	

}
