package presentation.mvp.view;

import java.lang.reflect.InvocationTargetException;
import utility.ReaderXML;

public class ViewDispatcher {
	
	private ReaderXML reader;
	private String srcFXML;
	private ReturnableStage stage;
	
	public ViewDispatcher(String path){
		reader= new ReaderXML(path);
	}
	/**
	 * <p>Recupera l'indirizzo in cui si trova la finestra da far mostrare e poi la passa al {@link ReturnableStage} per caricarla.</p>
	 * @param serviceName
	 * @param parameter
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Object dispatch(String serviceName,Object parameter) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
	    	srcFXML= reader.read_UI(serviceName);
	    	stage= new ReturnableStage(srcFXML,parameter);
	    	return stage;
	}	
}
