package business.model;

import java.util.ArrayList;
import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOMulta;
import business.entity.Entity;
import business.entity.Noleggio.Multa;
import business.model.Exception.CommonException;

public class ModelMulta implements Model{
	private DaoFactory daofactory;


	



	@Override
	public void Inserimento(Entity parameter) {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			((DAOMulta) daofactory.getDao("DAOMulta")).creazione(parameter);;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			((DAOMulta) daofactory.getDao("DAOMulta")).aggiornamento(parameter);;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
    public List<Multa> getAllMulteByNoleggio(int id){
    	List<Multa> l=new ArrayList<Multa>();
    	try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			l= ((DAOMulta) daofactory.getDao("DAOMulta")).getAllMulteByNoleggio(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return l;	
    }

    public int countMulteAperteByNoleggio(int id){
			if(daofactory==null)
				try {
					daofactory= DaoFactory.getDaoFactory(1);
					return ((DAOMulta) daofactory.getDao("DAOMulta")).countMulteAperteByNoleggio( id);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return -1;
    }
	@Override
	public List<Entity> ricerca(Entity parameter) {
		// TODO Auto-generated method stub
		return new ArrayList<Entity>();
	}

}
