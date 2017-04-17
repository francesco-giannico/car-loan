package utility;

import java.lang.reflect.InvocationTargetException;

import business.model.Exception.CommonException;
import javafx.stage.Modality;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.ReturnableStage;
import presentation.mvp.view.controller.Schermata;

public class Finestra {
	/**
	 * <p>permette di visualizzare finestre basandosi sul presenter passato, sui parametri di quella finestra </p>
	 * @param presenter
	 * @param FXMLParameter
	 * @param operation
	 * @param modality
	 * @throws NomeClienteNonValido 
	 */
	
	public static void visualizzaFinestra(Presenter presenter,ParametriFXML FXMLParameter,Schermata chiamante,String schermata,Modality modality){
		ReturnableStage stager;
		try {
			stager = (ReturnableStage) presenter.processRequest(schermata,FXMLParameter);
			stager.setControllerChiamante(chiamante);
			stager.showWindow(modality);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			e.printStackTrace();
		}
	}

	
}

