package business.model;

import java.util.ArrayList;
import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOCartaDiCredito;
import business.entity.Entity;
import business.entity.pagamento.CartaDiCredito;
import business.model.Exception.CommonException;

public class ModelCartaDiCredito implements Model{

	private DaoFactory daofactory;



	

	@Override
	public void Inserimento(Entity parameter) {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOCartaDiCredito)daofactory.getDao("DAOCartaDiCredito")).creazione(parameter);
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
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			return (CartaDiCredito) ((DAOCartaDiCredito) daofactory.getDao("DAOCartaDiCredito")).lettura(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<CartaDiCredito> getAllByCliente(int id){
		List<CartaDiCredito> l=new ArrayList<CartaDiCredito>();
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			l=((DAOCartaDiCredito)daofactory.getDao("DAOCartaDiCredito")).getAllByCliente(id);
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
