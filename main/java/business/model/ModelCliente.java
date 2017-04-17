package business.model;



import java.util.ArrayList;
import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOCliente;
import business.entity.Cliente;
import business.entity.Entity;

public class ModelCliente implements Model{
	private DaoFactory daofactory;

	@Override
	public void Inserimento(Entity parameter) {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			((DAOCliente) daofactory.getDao("DAOCliente")).creazione(parameter);;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void aggiornamento(Entity parameter) {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			((DAOCliente) daofactory.getDao("DAOCliente")).aggiornamento(parameter);;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Entity lettura(int id) {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			return (Cliente) ((DAOCliente) daofactory.getDao("DAOCliente")).lettura(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
	public List<Cliente> getAll(){
		List<Cliente> l=new ArrayList<Cliente>();
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			l=((DAOCliente) daofactory.getDao("DAOCliente")).getAll();	
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
