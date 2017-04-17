package business.model;


import java.util.ArrayList;
import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOPagamento;
import business.entity.Entity;
import business.entity.pagamento.Pagamento;
import business.model.Exception.CommonException;

public class ModelPagamento implements Model{
	private DaoFactory daofactory;
	@Override
	public void Inserimento(Entity parameter) throws CommonException {
		// TODO Auto-generated method stub

		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOPagamento)daofactory.getDao("DAOPagamento")).creazione(parameter);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOPagamento)daofactory.getDao("DAOPagamento")).aggiornamento(parameter);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Entity lettura(int id) {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			return (Pagamento) ((DAOPagamento) daofactory.getDao("DAOPagamento")).lettura(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.showMessage();
		}
		return null;
	}

	@Override
	public List<Entity> ricerca(Entity parameter) {
		// TODO Auto-generated method stub
		return new ArrayList<Entity>();
	}


	


}
