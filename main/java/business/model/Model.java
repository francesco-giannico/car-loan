package business.model;

import java.util.List;

import business.entity.Entity;
import business.model.Exception.CommonException;

public interface Model{
	public void Inserimento(Entity parameter) throws CommonException;
	public void aggiornamento(Entity parameter) throws CommonException;
	public Entity lettura(int id);
	public List<Entity> ricerca(Entity parameter);
}
