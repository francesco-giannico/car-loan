package presentation.controller;

import java.lang.reflect.InvocationTargetException;

import business.model.Exception.CommonException;

public class CarLoanFC implements FrontController {
	private ApplicationController carloanAC;
	@Override
	public Object processRequest(String request, Object parameter) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException,CommonException {   
		carloanAC = ApplicationControllerFactory.getApplicationController();
		return carloanAC.handleRequest(request, parameter);
	}
}
