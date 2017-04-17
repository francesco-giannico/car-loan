package presentation.mvp.view.controller.generale.luoghi;


import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import MessaggiFinestra.AlertView;
import business.entity.UtenteCorrente;
import business.entity.Luoghi.Agenzia;
import business.model.Exception.CommonException;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import business.entity.Gestori.Amministratore;

public class Nuova_Agenzia extends Schermata{
	@FXML
	TextField nome;
	@FXML
	TextField num_telefono;
	private TableView<Agenzia> tw;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		
	}
	
	@FXML
	public void btnAnnulla(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
	@SuppressWarnings("unchecked")
	@FXML
	public void btnConferma(ActionEvent e){
		
		SchermataGenerale<Agenzia> schermataGenerale = (SchermataGenerale<Agenzia>)this.getChiamante();
		tw= ((SchermataGenerale<Agenzia>)schermataGenerale).getTable("Agenzia");
	
		String n=nome.getText();
		try {
			if(n==null || n.isEmpty()){
				throw new CommonException("Il nome non può essere vuoto");
			}
			else{
				String tel=num_telefono.getText();
				if(tel==null)
					tel="";
				presenter.processRequest("InserisciAgenzia", new Agenzia(n, tel, ((Amministratore)UtenteCorrente.getUtente()).getIDDitta()));
				schermataGenerale.caricaTabella((List<Agenzia>)presenter.processRequest("getAllAgenzie",null), tw);
				chiudiFinestra();
			}
		}
		catch (CommonException e1) {
			e1.showMessage();
		}
		catch(InvocationTargetException e1){
			new CommonException(e1.getTargetException().getMessage()).showMessage();
		}
		catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
	
