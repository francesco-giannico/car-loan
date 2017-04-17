package business.model;

import java.util.ArrayList;
import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOSede;
import business.entity.Entity;
import business.entity.Luoghi.Sede;
import business.model.Exception.CommonException;

public class ModelSede implements Model{

	private DaoFactory daofactory;





	@Override
	public void Inserimento(Entity parameter) throws CommonException {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOSede)daofactory.getDao("DAOSede")).creazione(parameter);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOSede)daofactory.getDao("DAOSede")).aggiornamento(parameter);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	public List<Sede> getAll(){
		List<Sede> l=new ArrayList<Sede>();
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			l= ((DAOSede)daofactory.getDao("DAOSede")).getAll();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  l;
	}

	public List<Sede> getAllSediByAgenzia(int id){
		List<Sede> l=new ArrayList<Sede>();
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			l= ((DAOSede)daofactory.getDao("DAOSede")).getAllSediByAgenzia(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	@Override
	public Entity lettura(int id) {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOSede)daofactory.getDao("DAOSede")).lettura(id);
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
