package business.model.checker;

import business.entity.Entity;
import business.model.Exception.CommonException;

public interface  Checker {
	  public void check(Entity entity) throws CommonException;
}
