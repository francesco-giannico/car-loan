package business.model;

import java.util.ArrayList;
import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOSupervisoreS;
import business.entity.Entity;
import business.entity.Gestori.SupervisoreSede;
import business.model.Exception.CommonException;

public class ModelSupervisoreS implements Model{

	private DaoFactory daofactory;



	@Override
	public void Inserimento(Entity parameter) throws CommonException {
		try{
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
				 ((DAOSupervisoreS) daofactory.getDao("DAOSupervisoreS")).creazione(parameter);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		try{
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
				((DAOSupervisoreS) daofactory.getDao("DAOSupervisoreS")).aggiornamento(parameter);;
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public Entity lettura(int id) {
		try{
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
				return (SupervisoreSede) ((DAOSupervisoreS) daofactory.getDao("DAOSupervisoreS")).lettura(id);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		return null;
	}
public List<SupervisoreSede> getAll_bysede(int id){
	List<SupervisoreSede> l=new ArrayList<SupervisoreSede>();
	try{
		if(daofactory==null)
			daofactory= DaoFactory.getDaoFactory(1);
			l= (List<SupervisoreSede>) ((DAOSupervisoreS) daofactory.getDao("DAOSupervisoreS")).getAll_bySede(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	return l;
}


public List<SupervisoreSede> getAll(){
	List<SupervisoreSede> l=new ArrayList<SupervisoreSede>();
	try{
		if(daofactory==null)
			daofactory= DaoFactory.getDaoFactory(1);
			l= (List<SupervisoreSede>) ((DAOSupervisoreS) daofactory.getDao("DAOSupervisoreS")).getAll();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	return l;
}


public SupervisoreSede leggiSupervisoreSedeByCodiceFiscale(String c){
	try{
		if(daofactory==null)
			daofactory= DaoFactory.getDaoFactory(1);
			return((DAOSupervisoreS) daofactory.getDao("DAOSupervisoreS")).leggiSupervisoreSedeByCodiceFiscale(c);
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
