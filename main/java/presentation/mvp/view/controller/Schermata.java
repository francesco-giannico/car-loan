package presentation.mvp.view.controller;

import integration.DAO.connection.Connection;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Entity;
import MessaggiFinestra.AlertView;
import presentation.mvp.view.Presenter;
import utility.Finestra;
import utility.ParametriFXML;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 * <p>Tutti i controller estendono schermata</p>
 * @author francesco
 *
 */
public abstract class Schermata implements Initializable{
	

	protected Presenter presenter ;
	
	protected  ParametriFXML FXMLParameter;

	/**
	 * <p>Utile per gestire la finestra grafica</p>
	 */
	protected Stage stage;
	
	protected Schermata chiamante;
	
	protected void nascondiFinestra(){
		stage.hide();
	}
	protected void chiudiFinestra(){
		 stage.close();
	 }
	
	public void setStage(Stage stage,boolean hand){
		this.stage=stage;
		if(hand==false){
		stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, 
                new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
						if(result.isPresent() && result.get() == ButtonType.OK)
							chiudiFinestra();
							event.consume();//ignoro l'evento
					}	
                });
		}
		if(hand==true){
			stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>()
		        {
		            @Override
		            public void handle(WindowEvent window)
		            {
		                Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?",AlertType.CONFIRMATION);
       		 
						if(result.isPresent() && result.get() == ButtonType.OK){
							chiudiFinestra();
							FXMLParameter.setTitolo("Login");
							FXMLParameter.setHand(true);
							FXMLParameter.setRidimensionabile(false);
							Finestra.visualizzaFinestra(presenter,FXMLParameter,null,"MostraLogin",Modality.WINDOW_MODAL);
							try {
								Connection.chiudiConnessione();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else{
							window.consume();//ignoro l'evento
						}
		            }
		        });
		} 
	}
	public Stage getStage(){
		return stage;
	}
	
	public void setChiamante(Schermata chiamante){

		this.chiamante=chiamante;
		
	}
	public Schermata getChiamante(){
		return chiamante;
	}
	public void initData(Entity x){
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false,false);	
		
	}
}
