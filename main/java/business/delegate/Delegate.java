package business.delegate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import presentation.mvp.view.controller.Schermata;
import business.entity.Entity;
import business.model.Model;
import business.model.Exception.CommonException;
import business.model.checker.Checker;
import utility.ReaderXML;

public class Delegate {
    private ReaderXML reader;
    private Model  model;
    private Checker checker; 
    private Schermata schermata;
    private List<String> service_method;
    private Method method;
	private Object result;
    
    public Delegate(String path){
    	reader= new ReaderXML(path);
    }
    public Object doTask(String serviceName,Object parameter) throws  CommonException, InstantiationException,
    	IllegalAccessException,ClassNotFoundException, NoSuchMethodException, SecurityException, 
    		IllegalArgumentException, InvocationTargetException{
    	service_method = reader.read(serviceName);
    	if(Class.forName(service_method.get(0)).newInstance() instanceof Checker){
    		checker = (Checker) Class.forName(service_method.get(0)).newInstance();
    		method = checker.getClass().getMethod(service_method.get(1),Entity.class);
    		result=  method.invoke(checker, parameter); 		
    	}
    	else if(Class.forName(service_method.get(0)).newInstance() instanceof Model){
    		model = (Model) Class.forName(service_method.get(0)).newInstance();
    		if(parameter==null){
    			method = model.getClass().getMethod(service_method.get(1));
    			result=  method.invoke(model);
    		}
    		else if(parameter instanceof Entity){
    			method = model.getClass().getMethod(service_method.get(1), Entity.class);
    			result=  method.invoke(model, parameter);
    		}
    		else if(parameter instanceof List){//nel caso passi un intero
    			method = model.getClass().getMethod(service_method.get(1), List.class);
    			result=  method.invoke(model, parameter);
    		}
    		else if(parameter instanceof String){
    			method = model.getClass().getMethod(service_method.get(1), String.class);
    			result=  method.invoke(model, parameter);
    		}
    		else {
    			method = model.getClass().getMethod(service_method.get(1), int.class);
    			result=  method.invoke(model, parameter);
    		}
    			
    	}
    	else {
    		schermata= (Schermata) Class.forName(service_method.get(0)).newInstance();
    		if(parameter instanceof Schermata){
    			method = schermata.getClass().getMethod(service_method.get(1), Schermata.class);
    			result=  method.invoke(schermata,parameter);
    		}
    	}
    	return result;
    }
}
