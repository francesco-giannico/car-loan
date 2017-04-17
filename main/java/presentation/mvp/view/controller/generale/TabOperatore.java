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
import business.entity.Gestori.Operatore;
import business.model.Exception.CommonException;

import java.time.LocalDate;
public class TabOperatore {
	private ObservableList<TableColumn<Operatore,?>> operatore;
	private Schermata schermata;
	private ParametriFXML FXMLParameter;
	private Presenter presenter;
	
	
	
	TabOperatore(TableView<Operatore> tboperatore,Schermata schermata){
		operatore= tboperatore.getColumns();
		this.schermata= schermata;
		 presenter = new Presenter();
		 FXMLParameter = new ParametriFXML(null,false);
		bindingValuesOperatore();
		
	}
	private void bindingValuesOperatore() {
		operatore.get(0).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Operatore) cellData.getValue()).getIdUtente()));
		operatore.get(1).setCellValueFactory(cellData -> new SimpleStringProperty(((Operatore) cellData.getValue()).getNome()));
		operatore.get(2).setCellValueFactory(cellData -> new SimpleStringProperty(((Operatore) cellData.getValue()).getCognome()));
		operatore.get(3).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Operatore) cellData.getValue()).getDataNascita()));
		operatore.get(4).setCellValueFactory(cellData -> new SimpleStringProperty(((Operatore) cellData.getValue()).getIndirizzo()));
		operatore.get(5).setCellValueFactory(cellData -> new SimpleStringProperty(((Operatore) cellData.getValue()).getCodiceFiscale()));
		operatore.get(6).setCellValueFactory(cellData -> new SimpleStringProperty(((Operatore) cellData.getValue()).getNumCell()));
		operatore.get(7).setCellValueFactory(cellData -> new SimpleStringProperty(((Operatore) cellData.getValue()).getNumFisso()));
		
	}
	public void NuovoOperatore() {
		FXMLParameter.setTitolo("Inserimento nuovo Operatore");
		Finestra.visualizzaFinestra(presenter, FXMLParameter, schermata, "MostraSchermataNuovoOperatore",Modality.APPLICATION_MODAL);
		
	}
	public void ModificaOperatore() throws CommonException{
		 if(((SchermataGenerale<?>) schermata).getElemSelezionato("Operatore")< 0)
	    		throw new CommonException("Nessun elemento selezionato");
	    
	    else{
	    	FXMLParameter.setTitolo("Modifica Operatore");
	    	FXMLParameter.setEntity(((SchermataGenerale<?>) schermata).getEntitaElementoSelezionato("Operatore"));
	    	Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataModificaOperatore",Modality.APPLICATION_MODAL);
	    }
		
	}
}
