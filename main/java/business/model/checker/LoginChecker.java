package business.model.checker;

import business.entity.Entity;
import business.entity.Login;
import business.model.Exception.CommonException;

public class LoginChecker implements Checker{
	
	private static final int MIN_USER_VALUE = 2;
	private static final int MAX_USER_VALUE = 30;
	private static final int MIN_PASS_VALUE = 2;
	private static final int MAX_PASS_VALUE =30;
	private Login login;
	private boolean isValid;
	
	
	@Override
	public void check(Entity entity) throws CommonException {
		login= (Login) entity;
		checkUsername();
		checkPassword();
	}

	public void checkUsername() throws CommonException{
		int length;
		if(login.getUsername()!=null){
        length = login.getUsername().length();

        isValid = (length >= MIN_USER_VALUE)
                && (length <= MAX_USER_VALUE);

        if (!isValid) {
        	throw new CommonException("Username non valido");
        }
		}
		else
			throw new CommonException("Username vuoto");
	}
	
	public void checkPassword()throws CommonException{
		int length;
		if(login.getPassword()!=null){
        length = login.getPassword().length();

        isValid = (length >= MIN_PASS_VALUE)
                && (length <= MAX_PASS_VALUE);

        if (!isValid) {
        	throw new CommonException("Password non valida");
        }
		}
		else
			throw new CommonException("Password vuota");
	}
}
