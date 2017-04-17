package presentation.controller;

import java.lang.reflect.InvocationTargetException;

import business.model.Exception.CommonException;

public interface ApplicationController {
	public Object handleRequest(String request,Object parameter) throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, CommonException;
}
