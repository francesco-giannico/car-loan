package presentation.mvp.view.controller.generale;


import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.Finestra;
import utility.ParametriFXML;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import business.entity.Gestori.SupervisoreAgenzia;
import business.model.Exception.CommonException;

import java.time.LocalDate;
public class TabSupervisoreAgenzia {
	private ObservableList<TableColumn<SupervisoreAgenzia,?>> supervisoreagenzia;
	private TableView<SupervisoreAgenzia> table_supervisoreagenzia;
	private Schermata schermata;
	private ParametriFXML FXMLParameter;
	private Presenter presenter;
	
	
	
	TabSupervisoreAgenzia(TableView<SupervisoreAgenzia> tbsupervisoreagenzia,Schermata schermata){
		supervisoreagenzia= tbsupervisoreagenzia.getColumns();
		this.schermata= schermata;
		this.table_supervisoreagenzia=tbsupervisoreagenzia;
		 presenter = new Presenter();
		 FXMLParameter = new ParametriFXML(null,false);
		bindingValuessupervisoreagenzia();
		
	}
	private void bindingValuessupervisoreagenzia() {
		supervisoreagenzia.get(0).setCellValueFactory(cellData -> new SimpleIntegerProperty(((SupervisoreAgenzia) cellData.getValue()).getIdUtente()));
		supervisoreagenzia.get(1).setCellValueFactory(cellData -> new SimpleStringProperty(((SupervisoreAgenzia) cellData.getValue()).getNome()));
		supervisoreagenzia.get(2).setCellValueFactory(cellData -> new SimpleStringProperty(((SupervisoreAgenzia) cellData.getValue()).getCognome()));
		supervisoreagenzia.get(3).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((SupervisoreAgenzia) cellData.getValue()).getDataNascita()));
		supervisoreagenzia.get(4).setCellValueFactory(cellData -> new SimpleStringProperty(((SupervisoreAgenzia) cellData.getValue()).getIndirizzo()));
		supervisoreagenzia.get(5).setCellValueFactory(cellData -> new SimpleStringProperty(((SupervisoreAgenzia) cellData.getValue()).getCodiceFiscale()));
		supervisoreagenzia.get(6).setCellValueFactory(cellData -> new SimpleStringProperty(((SupervisoreAgenzia) cellData.getValue()).getNumCell()));
		supervisoreagenzia.get(7).setCellValueFactory(cellData -> new SimpleStringProperty(((SupervisoreAgenzia) cellData.getValue()).getNumFisso()));
		
	}
	public void NuovoSupervisoreAgenzia() {
		FXMLParameter.setTitolo("Inserimento nuovo Supervisore Agenzia");
		Finestra.visualizzaFinestra(presenter, FXMLParameter, schermata, "MostraSchermataNuovoSupervisoreAgenzia",Modality.APPLICATION_MODAL);
		
	}
	public void ModificaSupervisoreAgenzia() throws CommonException{
		 if(((SchermataGenerale<?>) schermata).getElemSelezionato("SupervisoreAgenzia")< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
	    	FXMLParameter.setTitolo("Modifica Supervisore Agenzia");
	    	FXMLParameter.setEntity(((SchermataGenerale<?>) schermata).getEntitaElementoSelezionato("SupervisoreAgenzia"));
	    	Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataModificaSupervisoreAgenzia",Modality.APPLICATION_MODAL);
	    }
		
	}
}
