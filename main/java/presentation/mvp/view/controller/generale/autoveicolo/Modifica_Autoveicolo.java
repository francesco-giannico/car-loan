package presentation.mvp.view.controller.generale.autoveicolo;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Utente;
import business.entity.UtenteCorrente;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.Fascia.Fascia;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import business.entity.Luoghi.Sede;
import business.model.Exception.CommonException;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.ParametriFXML;

public class Modifica_Autoveicolo extends Nuovo_Autoveicolo{
	private Autoveicolo auto_coinvolta;
	public void initialize(URL arg0, ResourceBundle arg1) {
	presenter=new Presenter();
	FXMLParameter=new ParametriFXML(null, false);
	}
	@SuppressWarnings("unchecked")
	public void initData(Entity entity){
		this.auto_coinvolta=(Autoveicolo)entity;
		try {
		this.btnchoose.setTooltip(new Tooltip("Clicca e scegli l'immagine"));
		/*Numero posti*/
		ObservableList<Integer> list=FXCollections.observableArrayList(1,2,3,4,5,6,7,9);
		this.nposti.setItems(list);
		this.nposti.getSelectionModel().select(list.indexOf(this.auto_coinvolta.getNroPosti()));
		//Disponibilita
		ObservableList<String> lista=FXCollections.observableArrayList("Disponibile","NonDisponibile","ManutenzioneOrdinaria","ManutenzioneStraordinaria","DaManutenere");
		this.Disponibilita.setItems(lista);
		this.Disponibilita.getSelectionModel().select(lista.indexOf(this.auto_coinvolta.getDisponibilita().toString()));
		//Fasce
		this.fasce=(LinkedList<Fascia>) presenter.processRequest("getAllFasce", null);
		 LinkedList<String> temp=new LinkedList<String>();
		 int i=0;
		 for(int j=0;j<this.fasce.size();j++){
			 temp.add(this.fasce.get(j).getNome());
			 int elimina=this.auto_coinvolta.getFascia();
			 Fascia f=this.fasce.get(j);
			 if(f.getIDFascia()==elimina)
				 i=j;
				 
		 }
		 lista=FXCollections.observableArrayList(temp);
		 this.fascia.setItems(lista);
		 this.fascia.getSelectionModel().selectedIndexProperty().addListener(new Choiceboxlistener());
		 this.fascia.getSelectionModel().select(i);
		 //Cambio
		 this.cambio.setItems(FXCollections.observableArrayList("Manuale","Automatico"));
		 String s=this.auto_coinvolta.getCambio();
		 if(s.equals("Manuale"))
			 this.cambio.getSelectionModel().select(0);
		 else
			 this.cambio.getSelectionModel().select(1);
		 //Sedi
		 sedi=(ArrayList<Sede>) presenter.processRequest("getAllSedi", null);
		 nome.setCellValueFactory(cellData ->  new  SimpleStringProperty(((Sede) cellData.getValue()).getNome()));
		 indirizzo.setCellValueFactory(cellData ->  new  SimpleStringProperty(((Sede) cellData.getValue()).getIndirizzo()));
		 identifier.setCellValueFactory(cellData ->new ReadOnlyObjectWrapper<Integer>(((Sede)cellData.getValue()).getIDSede()));
		 ObservableList<Sede> obsList= FXCollections.observableList(sedi);
		 tablesedi.setItems(obsList);
		 for(int j=0;j<obsList.size();j++){
			 if(obsList.get(j).getIDSede()==this.auto_coinvolta.getCodiceSedDisp()){
				 tablesedi.getSelectionModel().select(j);
				 break;
			 }
		 }
		 targa.setText(this.auto_coinvolta.getTarga());
		 marca.setText(this.auto_coinvolta.getMarca());
		 modello.setText(this.auto_coinvolta.getModello());;
		 alimprinc.setText(this.auto_coinvolta.getAlimPrincipale());
		 alimsec.setText(this.auto_coinvolta.getAlimSec());
		 colore.setText(this.auto_coinvolta.getColore());
		 immatricolazione.setValue(this.auto_coinvolta.getImmatricolazione());
		 cilindrata.setText(String.valueOf(this.auto_coinvolta.getCilindrata()));
		 potenza.setText(String.valueOf(this.auto_coinvolta.getPotenza()));
		 numtelaio.setText(this.auto_coinvolta.getNroTelaio());
		 kmpercorsi.setText(String.valueOf(this.auto_coinvolta.getUltimoKm()));
		 capienza.setText(String.valueOf(this.auto_coinvolta.getCapPortaBagnagli()));
		 scadenzaass.setValue(this.auto_coinvolta.getDataScadAssic());
		 prezzo.setText(String.valueOf(this.auto_coinvolta.getPrezzo()));
		 optional_auto.setText(this.auto_coinvolta.getOptionalAuto());
		 danni_futili.setText(this.auto_coinvolta.getDanni().getDanniFutili());
		 danni_gravi.setText(this.auto_coinvolta.getDanni().getDanniGravi());
		 Note.setText(this.auto_coinvolta.getNote());
		 InputStream in=this.auto_coinvolta.getImmagine_stream();
		 if(in!=null)
		 vistaimmagine.setImage(new Image(in));
		 
		} 
		 catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException | CommonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 }
	
	class Choiceboxlistener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable,Number oldValue, Number newValue) {
		descrizione_fascia.setText(fasce.get((int)newValue).getDescrizione());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@FXML
	public void btnconferma_click(ActionEvent e){
		SchermataGenerale<Autoveicolo> schermataGenerale = (SchermataGenerale<Autoveicolo>)this.getChiamante();
		tw= ((SchermataGenerale<Autoveicolo>)schermataGenerale).getTable("Autoveicolo");
		try {
			Autoveicolo auto_da_aggiornare=prendiDatiDaView();
			auto_da_aggiornare.setIDauto(auto_coinvolta.getIDauto());
			presenter.processRequest("VerificaAutoveicolo", auto_da_aggiornare);
			presenter.processRequest("AggiornamentoAutoveicolo", auto_da_aggiornare);
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
					lista.add(( schermataGenerale).getFascia().getIDFascia());
					tw.getItems().clear();
					schermataGenerale.caricaTabella((List<Autoveicolo>)presenter.processRequest("getAllAutoBySedeAndFascia",lista), tw);
				}
				AlertView.getAlertView("Autoveicolo aggiornato con successo", AlertType.INFORMATION);
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
			new CommonException(e1.getTargetException().getMessage()).showMessage();
		}
		catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException e1) {
			e1.printStackTrace();
		}
	}
	
}
