package presentation.controller;
import java.lang.reflect.InvocationTargetException;

import presentation.mvp.view.ViewDispatcher;
import business.delegate.Delegate;
import business.model.Exception.CommonException;




public class CarLoanAC implements ApplicationController {
    private static String SHOW_GUI_SYNTAX = "Mostra[a-zA-Z]+";
	private Delegate delegate ; 
	private ViewDispatcher viewdispatcher;
	@Override
	public Object handleRequest(String request, Object parameter) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, CommonException {
		if (request.matches(SHOW_GUI_SYNTAX)) {
			viewdispatcher = new ViewDispatcher("presentation/mvp/view/Interfacce.xml"); 
           return viewdispatcher.dispatch(request,parameter);
		}
        else {  
			delegate= new Delegate("presentation/AC.xml");
			return delegate.doTask(request, parameter);
        }		
	}
}