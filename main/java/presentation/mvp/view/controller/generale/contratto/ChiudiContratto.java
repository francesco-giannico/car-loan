package presentation.mvp.view.controller.generale.contratto;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import business.entity.Entity;
import business.entity.Noleggio.Contratto;

import business.entity.Noleggio.StatoContratto;
import business.model.Exception.CommonException;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.ParametriFXML;

public class ChiudiContratto extends NuovoContratto{
	

	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@FXML
	public void btnConferma(ActionEvent event){
		SchermataGenerale scChiamante= (SchermataGenerale) this.getChiamante();
			try {

				contratto = prendiDatiDaView();
				presenter.processRequest("VerificaContratto", contratto);
				presenter.processRequest("ModificaContratto", contratto);
				//Prendo la schermata che ha chiamato questo metodo , li passo l'elemento selezionato , il cliente da modificare e la tabella su cui lavorare
				scChiamante.caricaTabella((List<Contratto>)presenter.processRequest("getAllContratti",null), scChiamante.getTable("Contratto"));
				chiudiFinestra();
			}
			catch(CommonException e){
				e.showMessage();
			}
			catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException  e) {
				e.printStackTrace();
			}	
		}
	@Override
	public Contratto prendiDatiDaView() {
		
		contratto.setStato(StatoContratto.Chiuso);
		contratto.setNote(textNote.getText());
			
		return contratto;
	}
	 
	@Override
	public void initData(Entity x){
		textNote.setText(((Contratto)x).getNote());
		contratto=(Contratto)x;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
	}
	
}
