package presentation.mvp.view.controller.generale.contratto;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import MessaggiFinestra.AlertView;
import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.StatoContratto;
import business.model.Exception.CommonException;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.ParametriFXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class ModificaContratto extends NuovoContratto{
	@FXML
	private Button btnModifica;
	@FXML
	private Button btnConferma;	
	@FXML
	private ChoiceBox<StatoContratto> choiceStato;
	@FXML
	private TextArea textNote;
	
	private Contratto contratto;
	
	private boolean Aggiornare=true;
	private int lunghezzaOld;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@FXML
	public void btnConferma(ActionEvent event){
		SchermataGenerale scChiamante= (SchermataGenerale) this.getChiamante();
		contratto= (Contratto)scChiamante.getEntitaElementoSelezionato("Contratto");//ottengo le info sul cliente selezionato, ma ne cambio alcune
		lunghezzaOld= contratto.getNote().length();
		Aggiornare=true;
		
		if(Aggiornare==true){
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
		else {
			AlertView.getAlertView("Nessuna modifica da apportare", AlertType.INFORMATION);
		}	
	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Contratto prendiDatiDaView() throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, CommonException{
		contratto.setStato(choiceStato.getSelectionModel().getSelectedItem());
		contratto.setNote(textNote.getText());
		
		
		if(choiceStato.getSelectionModel().getSelectedItem().toString().equals(StatoContratto.Annullato.toString())){
			List<Noleggio> contrattiAperti= (List<Noleggio>)presenter.processRequest("getNoleggiAperti", contratto.getIDContratto());
			if(contrattiAperti.size()>0){
				throw new CommonException("Ci sono dei noleggi aperti , non è possibile fare questa scelta");
			}
			else 
				contratto.setDataChiusura(LocalDate.now());//imposto la data di chiusura se il valore scelto è annullato	
		}

		//controllo se bisogna aggiornare
		if(contratto.getStato().toString().equals(StatoContratto.Aperto.toString()) && contratto.getNote().length()==lunghezzaOld){
			Aggiornare=false;
		}
		return contratto;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		settaChoiceBox();	
	}
	 
	@Override
	public void initData(Entity x){
		textNote.setText(((Contratto)x).getNote());
	}
	public void settaChoiceBox(){
		ObservableList<StatoContratto> choice = FXCollections.observableArrayList(StatoContratto.getAllStates());
		choice.remove(1); 
		choiceStato.setItems(choice);
		choiceStato.getSelectionModel().selectFirst();
	}
	
}
