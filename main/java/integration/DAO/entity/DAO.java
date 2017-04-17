package integration.DAO.entity;

import java.sql.ResultSet;

import business.entity.Entity;
import business.model.Exception.CommonException;

public interface DAO {
		public abstract ResultSet creazione(Entity x ) throws CommonException;
		public abstract void aggiornamento(Entity entity) throws CommonException;
		public abstract Entity lettura(int id) throws CommonException;
}
