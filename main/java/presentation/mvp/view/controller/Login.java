package presentation.mvp.view.controller;



import java.lang.reflect.InvocationTargetException;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Utente;
import business.entity.UtenteCorrente;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.Operatore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import business.model.Exception.CommonException;
import utility.Crittografia;
import utility.Finestra;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

public class Login extends Schermata{
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPsw;
	
	@FXML
	public void btnLogin(ActionEvent e){
		
		business.entity.Login credenziali;
		try {

			credenziali = new business.entity.Login(txtUsername.getText(),txtPsw.getText());
			
			//non crittografo la password per verificare se rispetta i campi
			presenter.processRequest("VerificaLogin", credenziali);//controlal solo se sono corretti , non se nel db esistono!
			
			//la password viene crittografata e settata
			credenziali.setPassword(Crittografia.CriptaPassword(credenziali.getPassword()));
			
			Utente x=(Utente) presenter.processRequest("login",credenziali);
			

		   
			if(x!=null){
				 FXMLParameter.setRidimensionabile(false);
				 FXMLParameter.setHand(true);
					this.chiudiFinestra();
					UtenteCorrente.setUtente(x);
				/*Esito del log in positivo*/
				if(x instanceof Amministratore){
					FXMLParameter.setTitolo("Amministratore");
					Finestra.visualizzaFinestra(this.presenter,FXMLParameter,this,"MostraSchermataGenerale",Modality.APPLICATION_MODAL);
				}
				else if(x instanceof SupervisoreAgenzia){
					FXMLParameter.setTitolo("Supervisore Agenzia");
					Finestra.visualizzaFinestra(this.presenter,FXMLParameter,this,"MostraSchermataGenerale",Modality.APPLICATION_MODAL);
				}
				else if(x instanceof SupervisoreSede){
					FXMLParameter.setTitolo("Supervisore Sede");
				
					Finestra.visualizzaFinestra(this.presenter, FXMLParameter,this,"MostraSchermataGenerale",Modality.APPLICATION_MODAL);	
				}
				else if(x instanceof Operatore){
				    FXMLParameter.setTitolo("Operatore");
					Finestra.visualizzaFinestra(this.presenter,FXMLParameter,this,"MostraSchermataGenerale",Modality.APPLICATION_MODAL);
				}
			}
			else{
				AlertView.getAlertView("Autenticazione fallita : Ricontrollare l'Username e la password inserite",AlertType.ERROR);
			}
		}
		catch (InvocationTargetException e1){
				new CommonException(e1.getTargetException().getMessage()).showMessage();
			
		}
		catch(CommonException e1){
			e1.showMessage();
		}
		catch (InstantiationException | IllegalAccessException| ClassNotFoundException | NoSuchMethodException| SecurityException | IllegalArgumentException
			   e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	@FXML
	public void btnEsci(ActionEvent e){
		this.chiudiFinestra();
	}


	@Override
	public void initData(Entity x) {
		// TODO Auto-generated method stub
		
	}
}
