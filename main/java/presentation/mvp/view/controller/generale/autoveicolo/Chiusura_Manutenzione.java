package presentation.mvp.view.controller.generale.autoveicolo;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.ParametriFXML;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Utente;
import business.entity.UtenteCorrente;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.manutenzione.Manutenzione;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import business.entity.Luoghi.Sede;
import business.model.Exception.CommonException;

public class Chiusura_Manutenzione extends Nuova_Manutenzione{
	private Manutenzione m;
	private Autoveicolo auto;
	private ObservableList<TableColumn<Manutenzione,?>> manutenzioni;
	@FXML
	private AnchorPane Pane;
	@FXML
	private Label datafine;
	@FXML
	TableView<Manutenzione> table_manutenzioni;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter=new ParametriFXML(null, false);
		manutenzioni=table_manutenzioni.getColumns();
		
		}
	@SuppressWarnings("unchecked")
	public void initData(Entity x){
		this.auto=(Autoveicolo)x;
		InputStream i=auto.getImmagine_stream();
		if(i!=null)
			immagine.setImage(new Image(i));
		else
			immagine.setImage(null);
		targa.setText(auto.getTarga());
		modello.setText(auto.getModello());
		manutenzioni.get(0).setCellValueFactory(cellData ->  new SimpleIntegerProperty(((Manutenzione) cellData.getValue()).getIDManutenzione()));
		manutenzioni.get(1).setCellValueFactory(cellData ->  new SimpleObjectProperty<LocalDate>(((Manutenzione) cellData.getValue()).getDatainizio()));
		try {
			List<Manutenzione>manutenzioni=(List<Manutenzione>)presenter.processRequest("getAllManutenzioni_ordinarie_aperte",this.auto.getIDauto());
			manutenzioni.addAll((List<Manutenzione>)presenter.processRequest("getAllManutenzioni_straordinarie_aperte",this.auto.getIDauto()));
			caricatabella(manutenzioni);
			if(manutenzioni.isEmpty()){
				AlertView.getAlertView("Non ci sono manutenzioni aperte per quest'auto", AlertType.ERROR);
				Pane.setDisable(true);
			}
			else{
			table_manutenzioni.getSelectionModel().selectedItemProperty().addListener(new ItemSelectedManutenzione());
			table_manutenzioni.getSelectionModel().selectFirst();
			m=table_manutenzioni.getSelectionModel().getSelectedItem();
			}
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private void caricatabella(List<Manutenzione> manutenzioni){
	ObservableList<Manutenzione> obsList= FXCollections.observableList(manutenzioni);
	table_manutenzioni.setItems(obsList);
	table_manutenzioni.getSelectionModel().selectFirst();
}
@SuppressWarnings("unchecked")
@FXML
public void btnconferma(ActionEvent e){
	try {
		m.setDataFine(LocalDate.now());
		presenter.processRequest("VerificaManutenzione", m);
		presenter.processRequest("ChiusuraManutenzione",m);
		SchermataGenerale<Autoveicolo> schermataGenerale = (SchermataGenerale<Autoveicolo>)this.getChiamante();
		tw= ((SchermataGenerale<Autoveicolo>)schermataGenerale).getTable("Autoveicolo");
		Utente utente = UtenteCorrente.getUtente();
		List<Integer> lista=null;
		if(utente instanceof Amministratore)
		schermataGenerale.caricaTabella((List<Autoveicolo>)presenter.processRequest("getAllAutoByFascia",schermataGenerale.getFascia().getIDFascia()), tw);
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
	}
	
	 catch (CommonException e1) {
		// TODO Auto-generated catch block
		e1.showMessage();
	}
	catch(InvocationTargetException e1){
		new CommonException(e1.getTargetException().getMessage()).showMessage();
	}
	catch( ClassNotFoundException e1){
		new CommonException("File AC.xml corrotto").showMessage();
	}
	catch (InstantiationException | IllegalAccessException
			| NoSuchMethodException
			| SecurityException | IllegalArgumentException
			e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
}




/**
 * Listener 
 */

class ItemSelectedManutenzione implements ChangeListener<Manutenzione>{

	@Override
	public void changed(ObservableValue<? extends Manutenzione> observable,Manutenzione oldValue, Manutenzione newValue) {
		motivo.setText(newValue.getNote());
		m=newValue;
	}
	
}
}
