package presentation.mvp.view.controller.generale.contratto;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Cliente;
import business.entity.Utente;
import business.entity.UtenteCorrente;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.Operatore;
import business.entity.Gestori.SupervisoreSede;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.StatoContratto;
import business.model.Exception.CommonException;
import MessaggiFinestra.AlertView;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.ParametriFXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.beans.property.SimpleStringProperty;


public class NuovoContratto extends Schermata{
	
	private DatePicker dCreazione;	
	@FXML
	private DatePicker dChiusura;
	@FXML
	private TableView<Cliente> tbcliente;	
	@FXML
	protected TextArea textNote;
	@FXML
	private Button btnCancella;
	@FXML
	private Button btnConferma;
	@FXML
	private TableView<Contratto> tw;
	@FXML
	private TableColumn<Cliente,String> codFiscale;
	@FXML
	private TableColumn<Cliente,String> nome;
	@FXML
	private TableColumn<Cliente,String> cognome;
	
	protected Contratto contratto;
	@FXML
	public void btnCancella(ActionEvent event){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void btnConferma(ActionEvent event) throws CommonException{
			try { 
				if(tbcliente.getSelectionModel().getSelectedIndex()< 0)
	    			throw new CommonException("Nessun cliente selezionato");
	    			tw= ((SchermataGenerale)this.getChiamante()).getTable("Contratto");
					contratto= prendiDatiDaView();
					presenter.processRequest("VerificaContratto", contratto);	
					presenter.processRequest("InserimentoContratto", contratto);
					//Chiama il metodo della schermata che ha chiamato questa schermata per settare nella tabella dei clienti i clienti ricavati
					((SchermataGenerale)this.getChiamante()).caricaTabella((List<Contratto>)presenter.processRequest("getAllContratti",null), tw);
				chiudiFinestra();
			}
			catch(CommonException e){
				e.showMessage();
			}
			catch(InvocationTargetException e){

				new CommonException(((InvocationTargetException) e).getTargetException().getMessage()).showMessage();
			}
			catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException e) {
				e.printStackTrace();
			}
		 
	}
	
	public Contratto prendiDatiDaView() throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, CommonException{

		Contratto contratto = new Contratto();
		contratto.setIdCliente((tbcliente.getSelectionModel().getSelectedItem().getId()));//prende l'id del cliente selezionato
		
		contratto.setNote(textNote.getText());
		
		contratto.setStato(StatoContratto.Aperto);
		
		
		Utente utente= UtenteCorrente.getUtente();
		//qui setto l'id dell'utente del sistema
		if(utente instanceof Operatore){
			contratto.setIDOperatore(utente.getIdUtente());
			contratto.setIDAmministratore(0);
			contratto.setIDSupervisoreAgenzia(0);
			contratto.setIDSupervisoreSede(0);
		}
		else if(utente instanceof Amministratore){
			contratto.setIDAmministratore(utente.getIdUtente());
			contratto.setIDOperatore(0);
			contratto.setIDSupervisoreAgenzia(0);
			contratto.setIDSupervisoreSede(0);
			}
		else if(utente instanceof SupervisoreSede){
			contratto.setIDSupervisoreSede(utente.getIdUtente());
			contratto.setIDAmministratore(0);
			contratto.setIDOperatore(0);
			contratto.setIDSupervisoreAgenzia(0);}
		else {
			contratto.setIDSupervisoreAgenzia(utente.getIdUtente());
			contratto.setIDAmministratore(0);
			contratto.setIDOperatore(0);
			contratto.setIDSupervisoreSede(0);
		}
		contratto.setDataCreazione(dCreazione.getValue());
	
		return contratto;
	}

	private boolean caricaTabella(List<Cliente> list){
		ObservableList<Cliente> obsList= FXCollections.observableList(list);
		tbcliente.setItems(obsList);
		return true;
	}
	
	private void bindingValues(){
		codFiscale.setCellValueFactory(cellData -> new SimpleStringProperty((cellData.getValue()).getCodFiscale()));
		nome.setCellValueFactory(cellData -> new SimpleStringProperty((cellData.getValue()).getNome()));
		cognome.setCellValueFactory(cellData -> new SimpleStringProperty((cellData.getValue()).getCognome()));	
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		dCreazione= new DatePicker();
		dCreazione.setValue(LocalDate.now());//setto il valore di default della data di creazione.
	
		bindingValues();
	
		try {
			caricaTabella((List<Cliente>)presenter.processRequest("getAllClienti",null));
		}
		catch(CommonException e){
			e.showMessage();
		}
		catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
