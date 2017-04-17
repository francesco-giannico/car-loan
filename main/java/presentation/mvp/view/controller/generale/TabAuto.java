package presentation.mvp.view.controller.generale;

import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.Disponibilita;
import business.model.Exception.CommonException;
import utility.Finestra;
import utility.ParametriFXML;

import java.time.LocalDate;
public class TabAuto {
	private ObservableList<TableColumn<Autoveicolo,?>> auto;
	private Schermata schermata;
	private Presenter presenter;
	private ParametriFXML FXMLParameter;
	
	TabAuto(TableView<Autoveicolo> tbAuto2,Schermata schermata){
		auto=tbAuto2.getColumns();
		this.schermata=schermata;
		bindindValuesAuto();
		presenter=new Presenter();
		FXMLParameter=new ParametriFXML(null, false);
	}
	void bindindValuesAuto(){
		auto.get(0).setCellValueFactory(cellData ->  new SimpleStringProperty(((Autoveicolo) cellData.getValue()).getTarga()));
		auto.get(1).setCellValueFactory(cellData ->  new SimpleStringProperty(((Autoveicolo) cellData.getValue()).getMarca()));
		auto.get(2).setCellValueFactory(cellData ->  new SimpleStringProperty(((Autoveicolo) cellData.getValue()).getModello()));
		auto.get(3).setCellValueFactory(cellData ->  new SimpleStringProperty(((Autoveicolo) cellData.getValue()).getAlimPrincipale()));
		auto.get(4).setCellValueFactory(cellData ->  new SimpleStringProperty(((Autoveicolo) cellData.getValue()).getColore()));
		auto.get(5).setCellValueFactory(cellData ->  new SimpleStringProperty(((Autoveicolo) cellData.getValue()).getCambio()));
		auto.get(6).setCellValueFactory(cellData ->  new SimpleObjectProperty<LocalDate>(((Autoveicolo) cellData.getValue()).getImmatricolazione()));
		auto.get(7).setCellValueFactory(cellData ->  new  SimpleIntegerProperty(((Autoveicolo) cellData.getValue()).getCilindrata()));
		auto.get(8).setCellValueFactory(cellData ->  new SimpleIntegerProperty(((Autoveicolo) cellData.getValue()).getFascia()));
		auto.get(9).setCellValueFactory(cellData ->  new  SimpleObjectProperty<Disponibilita>(((Autoveicolo) cellData.getValue()).getDisponibilita()));
		auto.get(10).setCellValueFactory(cellData ->  new  SimpleFloatProperty(((Autoveicolo) cellData.getValue()).getPrezzo()));
		auto.get(11).setCellValueFactory(cellData ->  new SimpleObjectProperty<LocalDate>(((Autoveicolo) cellData.getValue()).getDataScadAssic()));
		auto.get(12).setCellValueFactory(cellData ->  new SimpleIntegerProperty(((Autoveicolo) cellData.getValue()).getUltimoKm()));
		
	}
	
	
	public void NuovaAuto(){
		FXMLParameter.setTitolo("Inserimento nuovo autoveicolo");
		Finestra.visualizzaFinestra(presenter, FXMLParameter, schermata, "MostraSchermataNuovaAuto",Modality.APPLICATION_MODAL);
	}
	
	public void ModificaAuto() throws CommonException{
		 if(((SchermataGenerale<?>) schermata).getElemSelezionato("Autoveicolo")< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
	    	FXMLParameter.setTitolo("Modifica autoveicolo");
	    	FXMLParameter.setEntity(((SchermataGenerale<?>) schermata).getEntitaElementoSelezionato("Autoveicolo"));
	    	Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataModificaAutoveicolo",Modality.APPLICATION_MODAL);
	    }
	    }
	public void NuovaManutenzione() throws CommonException {
		 if(((SchermataGenerale<?>) schermata).getElemSelezionato("Autoveicolo")< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
		FXMLParameter.setTitolo("Inserimento nuova manutenzione");
		FXMLParameter.setEntity(((SchermataGenerale<?>) schermata).getEntitaElementoSelezionato("Autoveicolo"));
		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataNuovaManutenzione",Modality.APPLICATION_MODAL);
	    }
	}
	public void ChiudiManutenzione()throws CommonException {
		 if(((SchermataGenerale<?>) schermata).getElemSelezionato("Autoveicolo")< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
		FXMLParameter.setTitolo("Chiusura manutenzione");
		FXMLParameter.setEntity(((SchermataGenerale<?>) schermata).getEntitaElementoSelezionato("Autoveicolo"));
		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataChiusuraManutenzione",Modality.APPLICATION_MODAL);
	    }
		
	}
	public void VisualizzaManutenzione() throws CommonException {
		 if(((SchermataGenerale<?>) schermata).getElemSelezionato("Autoveicolo")< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
		FXMLParameter.setTitolo("Visualizza manutenzioni");
		FXMLParameter.setEntity(((SchermataGenerale<?>) schermata).getEntitaElementoSelezionato("Autoveicolo"));
		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataVisualizzaManutenzione",Modality.APPLICATION_MODAL);
	    }
		
	}
	}

