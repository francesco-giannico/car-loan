package business.model;

import java.util.ArrayList;
import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOSupervisoreA;
import business.entity.Entity;
import business.entity.Gestori.SupervisoreAgenzia;
import business.model.Exception.CommonException;

public class ModelSupervisoreA implements Model{

	private DaoFactory daofactory;




	@Override
	public void Inserimento(Entity parameter) throws CommonException {
		try{
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
				 ((DAOSupervisoreA)daofactory.getDao("DAOSupervisoreA")).creazione(parameter);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		try{
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
				 ((DAOSupervisoreA)daofactory.getDao("DAOSupervisoreA")).aggiornamento(parameter);;
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public Entity lettura(int id) {
		try{
		if(daofactory==null)
			daofactory= DaoFactory.getDaoFactory(1);
			return ((DAOSupervisoreA)daofactory.getDao("DAOSupervisoreA")).lettura(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Entity> ricerca(Entity parameter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<SupervisoreAgenzia> getAll(){
		List<SupervisoreAgenzia> l=new ArrayList<SupervisoreAgenzia>();
		try{
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
				l= ((DAOSupervisoreA)daofactory.getDao("DAOSupervisoreA")).getAll();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return l;
	}
	public List<SupervisoreAgenzia> getAllbyAgenzia(int idAgenzia){
		List<SupervisoreAgenzia> l=new ArrayList<SupervisoreAgenzia>();
		try{
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
				l= ((DAOSupervisoreA)daofactory.getDao("DAOSupervisoreA")).getAllByAgenzia(idAgenzia);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return l;
	}
	public SupervisoreAgenzia leggiSupervisoreAgenziaByCodiceFiscale(String c){
		try{
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
				return ((DAOSupervisoreA)daofactory.getDao("DAOSupervisoreA")).leggiSupervisoreAgenziaByCodiceFiscale(c);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
	}
}
