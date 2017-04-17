package presentation.mvp.view;

import java.lang.reflect.InvocationTargetException;

import business.model.Exception.CommonException;
import presentation.controller.FrontController;
import presentation.controller.FrontControllerFactory;
/**
 * <p>Chiama il frontController con la richiesta.</p>
 *
 */
public class Presenter {
	
	private FrontController carloanFC;
	
	public Object processRequest(String request, Object parameter) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException,CommonException{
		carloanFC = FrontControllerFactory.getFrontController();
		return carloanFC.processRequest(request, parameter);
	}	
}
