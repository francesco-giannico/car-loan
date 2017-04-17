package presentation.mvp.view.controller.generale.noleggio.multa;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Noleggio.Multa;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.StatoMulta;
import business.model.Exception.CommonException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.ParametriFXML;

public class NuovaMulta extends Schermata{
	@FXML
	private DatePicker dMulta;
	@FXML
	private DatePicker dScadenzaMulta;
	@FXML
	private TextField txtImporto;
	@FXML
	private TextArea txtAreaNote;
	private Multa multa= new Multa();
	private Noleggio noleggio;
	@FXML
	public void btnConferma(ActionEvent e){
		
		try {
			prendiDatiDaView();
			try {
				presenter.processRequest("CheckMulta", multa);
				presenter.processRequest("InserimentoMulta", multa);
				chiudiFinestra();
			} 
			catch(InvocationTargetException e1){
				new CommonException(((InvocationTargetException) e1).getTargetException().getMessage()).showMessage();
			}
			
			catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					 e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		catch(CommonException e1){
			e1.showMessage();
		}
	}
	
	
	private void prendiDatiDaView() throws CommonException{
		if(txtImporto.getText().isEmpty()){
			throw new CommonException("Compilare prima tutti i campi obbligatori");
		}
			multa.setDataMulta(dMulta.getValue());
		try{
				float importo=(Float.parseFloat(txtImporto.getText()));
				multa.setImporto(importo);
		}
		catch(NumberFormatException e){
			throw new CommonException("L'importo deve essere costituito da soli numeri");
		}
		multa.setNote(txtAreaNote.getText());
		multa.setStato(StatoMulta.Aperto);
		multa.setDataScadenza(dScadenzaMulta.getValue());
		multa.setIdNoleggio(noleggio.getIDNoleggio());
}
	
	@FXML
	public void btnCancella(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
	@FXML
	public void checkDMulta(ActionEvent e){
		try {
			LocalDate lDate= dMulta.getValue();
			if(lDate!=null){
				if(lDate.isBefore(LocalDate.now())){
					dMulta.setValue(LocalDate.now());
				}
			}
			else 
				throw new CommonException("Data di inizio non impostata");
		} catch (CommonException e1) {
			e1.showMessage();
		}
	}
	@FXML
	public void checkDMultaScadenza(ActionEvent e){
		try {
			LocalDate lDate= dScadenzaMulta.getValue();
			if(lDate!=null){
				if(lDate.isBefore(LocalDate.now())){
					dScadenzaMulta.setValue(LocalDate.now());
				}
			}
			else 
				throw new CommonException("Data scadenza non impostata");
		} catch (CommonException e1) {
			e1.showMessage();
		}
	}
	@Override
	public void initData(Entity x){
		noleggio = (Noleggio)x;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false,false);
		dMulta.setValue(LocalDate.now());
		dScadenzaMulta.setValue(LocalDate.now().plusDays(2));
	
	}
}
