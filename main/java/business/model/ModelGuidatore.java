package business.model;

import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOGuidatore;
import business.entity.Entity;
import business.entity.Noleggio.Optional.Guidatore;
import business.model.Exception.CommonException;

public class ModelGuidatore implements Model{
	private DaoFactory daofactory;
	@Override
	public void Inserimento(Entity parameter) {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOGuidatore)daofactory.getDao("DAOGuidatore")).creazione(parameter);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Guidatore> getAllByNoleggio(int idNoleggio){
		List<Guidatore>l=new ArrayList<Guidatore>();
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			l=((DAOGuidatore)daofactory.getDao("DAOGuidatore")).getAllByNoleggio(idNoleggio);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public List<Entity> ricerca(Entity parameter) {
		// TODO Auto-generated method stub
		return new ArrayList<Entity>();
	}
}
