package business.model;

import java.util.ArrayList;
import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOOperatore;
import business.entity.Entity;
import business.entity.Gestori.Operatore;
import business.model.Exception.CommonException;

public class ModelOperatore implements Model{

	private DaoFactory daofactory;



	@Override
	public void Inserimento(Entity parameter) throws CommonException {
		if(daofactory==null)
			try {
				daofactory= DaoFactory.getDaoFactory(1);

				((DAOOperatore) daofactory.getDao("DAOOperatore")).creazione(parameter);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		if(daofactory==null)
			try {
				daofactory= DaoFactory.getDaoFactory(1);

				((DAOOperatore) daofactory.getDao("DAOOperatore")).aggiornamento(parameter);;
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@Override
	public Entity lettura(int id) {
		
			if(daofactory==null)
				try {
					daofactory= DaoFactory.getDaoFactory(1);

					return (Operatore) ((DAOOperatore) daofactory.getDao("DAOOperatore")).lettura(id);
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return null;
	}

	@Override
	public List<Entity> ricerca(Entity parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Operatore> getAll(){
		List<Operatore> l=new ArrayList<Operatore>();
		if(daofactory==null)
			try {
				daofactory= DaoFactory.getDaoFactory(1);
				l=  ((DAOOperatore) daofactory.getDao("DAOOperatore")).getAll();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	return l;
	}
	public List<Operatore> getAllOperatoriBySede(int idsede){
		List<Operatore> l=new ArrayList<Operatore>();
		if(daofactory==null)
			try {
				daofactory= DaoFactory.getDaoFactory(1);

				l= ((DAOOperatore) daofactory.getDao("DAOOperatore")).getAllOperatoriBySede(idsede);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	return l;
	}
	
	public Operatore leggiOperatoreByCodiceFiscale(String f){
		if(daofactory==null)
			try {
				daofactory= DaoFactory.getDaoFactory(1);

				return  ((DAOOperatore) daofactory.getDao("DAOOperatore")).leggiOperatoreByCodiceFiscale(f);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	return null;
	}
}