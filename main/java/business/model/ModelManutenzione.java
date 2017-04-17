package business.model;

import java.util.ArrayList;
import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOManutenzione;
import business.entity.Entity;
import business.entity.Auto.manutenzione.ManutenzioneOrdinaria;
import business.entity.Auto.manutenzione.ManutenzioneStraordinaria;
import business.model.Exception.CommonException;

public class ModelManutenzione implements Model{
	private DaoFactory daofactory;
	
	public void chiusuraManutenzione(Entity parameter) throws CommonException{
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOManutenzione)daofactory.getDao("DAOManutenzione")).aggiornamento(parameter);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Inserimento(Entity parameter) throws CommonException {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOManutenzione)daofactory.getDao("DAOManutenzione")).creazione(parameter);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		//Non implementata
	}
	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ManutenzioneOrdinaria> getAll_ordinarie(int auto_id){
		List<ManutenzioneOrdinaria> l=new ArrayList<ManutenzioneOrdinaria>();
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			l=((DAOManutenzione)daofactory.getDao("DAOManutenzione")).getAll_ordinarie(auto_id);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	return l;	
	}

	public List<ManutenzioneStraordinaria> getAll_straordinarie_aperte(int auto_id){
		List<ManutenzioneStraordinaria> l =new ArrayList<ManutenzioneStraordinaria>();
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			l=((DAOManutenzione)daofactory.getDao("DAOManutenzione")).getAll_straordinarie_aperte(auto_id);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	return l;	
	}
	
	public List<ManutenzioneOrdinaria> getAll_ordinarie_aperte(int auto_id){
		List<ManutenzioneOrdinaria> l=new ArrayList<ManutenzioneOrdinaria>();
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			l= ((DAOManutenzione)daofactory.getDao("DAOManutenzione")).getAll_ordinarie_aperte(auto_id);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	return l;	
	}

	public List<ManutenzioneStraordinaria> getAll_straordinarie(int auto_id){
		List<ManutenzioneStraordinaria> l =new ArrayList<ManutenzioneStraordinaria>();
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			l=((DAOManutenzione)daofactory.getDao("DAOManutenzione")).getAll_straordinarie_aperte(auto_id);
			
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
