package presentation.mvp.view.controller.generale.autoveicolo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import MessaggiFinestra.AlertView;
import business.entity.Utente;
import business.entity.UtenteCorrente;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.Danni;
import business.entity.Auto.Fascia.Fascia;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import business.entity.Luoghi.Sede;
import business.model.Exception.CommonException;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.ParametriFXML;


public class Nuovo_Autoveicolo extends Schermata{
	@FXML
	protected TextField targa;
	@FXML
	protected TextField marca;
	@FXML
	protected TextField modello;
	@FXML
	protected TextField alimprinc;
	@FXML
	protected TextField alimsec;
	@FXML
	protected TextField colore;
	@FXML
	protected ChoiceBox<String> cambio;
	@FXML
	protected TextField cilindrata;
	@FXML
	protected TextField potenza;
	@FXML
	protected TextField numtelaio;
	@FXML
	protected TextField kmpercorsi;
	@FXML
	protected TextField capienza;
	@FXML
	protected TextField prezzo;
	@FXML
	protected DatePicker immatricolazione;
	@FXML
	protected ChoiceBox<Integer> nposti;
	@FXML
	protected ChoiceBox<String> Disponibilita;
	
	@FXML
	protected ImageView vistaimmagine;
	
	protected String immagine_path;//dal bottone [...]
	@FXML
	protected DatePicker scadenzaass;
	@FXML
	protected ChoiceBox<String> fascia;
	@FXML
	protected TextArea danni_futili;
	@FXML
	protected TextArea danni_gravi;
	@FXML
	protected TextArea optional_auto;
	@FXML
	protected TextArea Note;
	@FXML
	protected Button btnchoose;
	@FXML
	protected Button btnconferma;
	@FXML
	protected Button btnannulla;
	@FXML
	protected Label descrizione_fascia;
	
	@FXML
	protected TableView<Sede> tablesedi;
	@FXML
	protected  TableColumn<Sede, Integer> identifier;
	@FXML
	protected  TableColumn<Sede, String> nome;
	@FXML
	protected  TableColumn<Sede, String> indirizzo;
	
	
	protected LinkedList<Fascia> fasce;
	protected ArrayList<Sede> sedi;
	protected TableView<Autoveicolo> tw;
	@SuppressWarnings("unchecked")
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		presenter=new Presenter();
		try {
		FXMLParameter = new ParametriFXML(null,false);
		btnchoose.setTooltip(new Tooltip("Clicca e scegli l'immagine"));
		nposti.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,9));
		nposti.getSelectionModel().selectFirst();
		Disponibilita.setItems(FXCollections.observableArrayList("Disponibile","NonDisponibile","ManutenzioneOrdinaria","ManutenzioneStraordinaria"));
		Disponibilita.getSelectionModel().selectFirst();
		
		 fasce=(LinkedList<Fascia>) presenter.processRequest("getAllFasce", null);
		 LinkedList<String> temp=new LinkedList<String>();
		 for(Fascia f:fasce)
			 temp.add(f.getNome());
		 
		 fascia.setItems(FXCollections.observableArrayList(temp));
		 fascia.getSelectionModel().selectedIndexProperty().addListener(new Choiceboxlistener());
		 fascia.getSelectionModel().selectFirst();
		 cambio.setItems(FXCollections.observableArrayList("Manuale","Automatico"));
		 cambio.getSelectionModel().selectFirst();
		 sedi=(ArrayList<Sede>) presenter.processRequest("getAllSedi", null);
		 nome.setCellValueFactory(cellData ->  new  SimpleStringProperty(((Sede) cellData.getValue()).getNome()));
		 indirizzo.setCellValueFactory(cellData ->  new  SimpleStringProperty(((Sede) cellData.getValue()).getIndirizzo()));
		 identifier.setCellValueFactory(cellData ->new ReadOnlyObjectWrapper<Integer>(((Sede)cellData.getValue()).getIDSede()));
		 ObservableList<Sede> obsList= FXCollections.observableList(sedi);
		 tablesedi.setItems(obsList);
		 tablesedi.getSelectionModel().selectFirst();
			
		}
			catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			AlertView.getAlertView("File :Interfacce.xml corrotto",AlertType.WARNING);
		}
	}
	class Choiceboxlistener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable,Number oldValue, Number newValue) {
		descrizione_fascia.setText(fasce.get((int)newValue).getDescrizione());
		}
		
	}
	
	@FXML
	public void btnchooseclick(ActionEvent e){

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Scegli l'immagine");
		fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
		File f=fileChooser.showOpenDialog(stage);
		if(f!=null){
			try{
			InputStream i=new FileInputStream(f);
			this.vistaimmagine.setImage(new Image(i));
			this.immagine_path=f.getAbsolutePath();
			
			}
			catch (FileNotFoundException e1) {
			AlertView.getAlertView("Il file selezionato non e' stato trovato", AlertType.WARNING);
		}
		}
	}
	
	@SuppressWarnings("unchecked")
	@FXML
	public void btnconferma_click(ActionEvent e){
		SchermataGenerale<Autoveicolo> schermataGenerale = (SchermataGenerale<Autoveicolo>)this.getChiamante();
		tw= ((SchermataGenerale<Autoveicolo>)schermataGenerale).getTable("Autoveicolo");
		
		try {
			Autoveicolo auto_da_inserire=prendiDatiDaView();
			presenter.processRequest("VerificaAutoveicolo", auto_da_inserire);
			presenter.processRequest("InserimentoAutoveicolo", auto_da_inserire);
			try {
				Utente utente = UtenteCorrente.getUtente();
				List<Integer> lista=null;
				if(utente instanceof Amministratore){
					schermataGenerale.caricaTabella((List<Autoveicolo>)presenter.processRequest("getAllAutoByFascia",schermataGenerale.getFascia().getIDFascia()), tw);
				}
					else if(utente instanceof SupervisoreAgenzia){
					List<Autoveicolo> autoveicoli  = new ArrayList<Autoveicolo>();
					List<Sede> sedi = (List<Sede>)presenter.processRequest("getAllSediByAgenzia",((SupervisoreAgenzia) utente).getIDAgenzia());
					for(Sede s:sedi){
						lista=new ArrayList<Integer>();
						lista.add(s.getIDSede());
						lista.add(schermataGenerale.getFascia().getIDFascia());
						List<Autoveicolo> auto= (List<Autoveicolo>) presenter.processRequest("getAllAutoBySedeAndFascia",lista);
						autoveicoli.addAll(auto);
					}
					schermataGenerale.caricaTabella((List<Autoveicolo>)autoveicoli, tw);
				}
				else{ //Supervisore sede
					lista=new ArrayList<Integer>();
					lista.add(((SupervisoreSede)utente).getIDSede());
					lista.add(schermataGenerale.getFascia().getIDFascia());
					tw.getItems().clear();
					schermataGenerale.caricaTabella((List<Autoveicolo>)presenter.processRequest("getAllAutoBySedeAndFascia",lista), tw);
				}
				chiudiFinestra();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException | CommonException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		} 
		catch(CommonException e1){
			e1.showMessage();
		}
		catch(InvocationTargetException e1){
			//new CommonException(e1.getTargetException().getMessage()).showMessage();
			e1.printStackTrace();
		}
		catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException e1) {
			e1.printStackTrace();
		}
	}

	
	protected Autoveicolo prendiDatiDaView() throws CommonException {
	Autoveicolo temp=new Autoveicolo();
		if(this.targa.getText().isEmpty() || this.modello.getText().isEmpty() || this.marca.getText().isEmpty() || this.immatricolazione.getValue()==null || this.prezzo.getText().isEmpty())
			throw new CommonException("I campi obbligatori non devono essere vuoti!");
		String s=this.targa.getText();
		if(s==null || s.isEmpty() )
			throw new CommonException("La targa è vuota");
		else
			temp.setTarga(s);
		
		s=this.marca.getText();
		if(s.isEmpty()||s==null)
			throw new CommonException("La marca è vuota");
		else
				temp.setMarca(s);
		s=this.modello.getText();
		if(s.isEmpty()||s==null)
		throw new CommonException("Il modello è vuoto");
		 else
			temp.setModello(s);
		s=this.alimprinc.getText();
		if(s.isEmpty()||s==null)
		throw new CommonException("L'alimentazione principale è vuota");
		 else
			temp.setAlimPrincipale(s);
		LocalDate d=this.immatricolazione.getValue();
		if(d==null)
		throw new CommonException("Data immatricolazione vuota");
		 else
			 temp.setImmatricolazione(d);
		
		try{
			temp.setCilindrata(Integer.parseInt(this.cilindrata.getText()));
			}
			catch(NumberFormatException e){
				throw new CommonException("Cilindrata non valida");
			}
		try{
			 s=this.kmpercorsi.getText();
			if(s.isEmpty()||s==null)
				temp.setUltimoKm(0);
			else
				temp.setUltimoKm(Integer.parseInt(s));
		}
		catch(NumberFormatException e){
			throw new CommonException("Kilometri percorsi non validi");
		}
		try{
			s=this.potenza.getText();
			if(s.isEmpty()||s==null)
				temp.setPotenza(0);
			else
			temp.setPotenza(Integer.parseInt(this.potenza.getText()));
			}
			catch(NumberFormatException e){
				throw new CommonException("Potenza non valida");
		}
		try{
			 s=this.capienza.getText();
			if(s.isEmpty()||s==null)
				temp.setCapPortaBagnagli(0);
			else
		temp.setCapPortaBagnagli(Integer.parseInt(s));
		}
		catch(NumberFormatException e){
			throw new CommonException("Capienza non valida");
		}
		
			s=this.numtelaio.getText();
			if(s.isEmpty()||s==null)
				throw new CommonException("Il numero del telaio non può essere vuoto");
			else
			temp.setNroTelaio(s);
		
	temp.setAlimSec(this.alimsec.getText());
	temp.setColore(this.colore.getText());
	temp.setCambio(this.cambio.getSelectionModel().getSelectedItem());
	
	temp.setNroPosti(this.nposti.getSelectionModel().getSelectedItem());
	
	String disp=this.Disponibilita.getSelectionModel().getSelectedItem();
	switch(disp){
	case "Disponibile":
		temp.setDisponibilita(business.entity.Auto.Disponibilita.Disponibile);
		break;
	case "NonDisponibile":
		temp.setDisponibilita(business.entity.Auto.Disponibilita.NonDisponibile);
		break;
	case "ManutenzioneOrdinaria":
		temp.setDisponibilita(business.entity.Auto.Disponibilita.ManutenzioneOrdinaria);
		break;
	case "ManutenzioneStraordinaria":
		temp.setDisponibilita(business.entity.Auto.Disponibilita.ManutenzioneStraordinaria);
		break;
	case "DaManutenere":
		temp.setDisponibilita(business.entity.Auto.Disponibilita.DaManutenere);
		break;
	}
	if(this.immagine_path==null)
		temp.setImmagine("");
	else
	temp.setImmagine(this.immagine_path);
	d=scadenzaass.getValue();
	if(d==null)
		throw new CommonException("Data scadenza assicurazione vuota");
	else
	temp.setDataScadAssic(d);
		
	int f=this.fascia.getSelectionModel().getSelectedIndex();
	temp.setFascia(this.fasce.get(f).getIDFascia());
	String d1,d2;
	d1=this.danni_futili.getText();
	if(d1==null)
		d1="";
	d2=this.danni_gravi.getText();
	if(d2==null)
		d2="";
	temp.setDanni(new Danni(d1,d2));
	try{
	temp.setPrezzo(Float.parseFloat(this.prezzo.getText()));
	}
	catch(NumberFormatException e){
		throw new CommonException("Prezzo non valido");
	}
	Sede se=this.tablesedi.getSelectionModel().getSelectedItem();
	temp.setCodiceSedDisp(se.getIDSede());
	s=this.optional_auto.getText();
	if(s==null)
		temp.setOptionalAuto("");
	else
		temp.setOptionalAuto(s);
	
	s=this.Note.getText();
	if(s==null)
		temp.setNote("");
	else 
		temp.setNote(s);
	return temp;
	}
	
	
	@FXML
	public void btnannulla_click(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
}