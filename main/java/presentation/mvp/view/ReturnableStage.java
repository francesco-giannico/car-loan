package presentation.mvp.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentation.mvp.view.controller.Schermata;
import utility.ParametriFXML;

public class ReturnableStage extends Stage {
   
	private Scene scene;
    private Parent root;
    private ParametriFXML param;
    private Schermata schermata;

	public ReturnableStage(String schemeResource,Object parameter) {
    		param= (ParametriFXML) parameter;
       
    		
        	FXMLLoader loader=new FXMLLoader();
        	
			loader.setLocation(getClass().getResource(schemeResource));
			
			try {
				root= loader.load();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.schermata = loader.getController();
			schermata.setStage(this,param.getHandChiusura());
           	schermata.initData(param.getEntity());
            scene = new Scene(root);
            
			this.setTitle(param.getTitolo());
			
			this.setResizable(param.isRidimensionabile());
			
			setScene(scene);	
    }
	
	
	/**
	 * <p>Salvo la finestra chiamante in modo tale da poter invocare i suoi metodi</p>
	 * @param stage
	 */
	public void setControllerChiamante(Schermata chiamante){
		schermata.setChiamante(chiamante);
	}
    public void showWindow(Modality modality) {
    	initModality(modality);
    	show();
    }
}