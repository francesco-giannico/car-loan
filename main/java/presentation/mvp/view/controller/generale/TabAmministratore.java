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
import business.entity.Gestori.Amministratore;
import business.model.Exception.CommonException;

import java.time.LocalDate;
public class TabAmministratore {
	private ObservableList<TableColumn<Amministratore,?>> amministratore;
	private Schermata schermata;
	private ParametriFXML FXMLParameter;
	private Presenter presenter;
	
	
	
	TabAmministratore(TableView<Amministratore> tbAmministratore,Schermata schermata){
		amministratore= tbAmministratore.getColumns();
		this.schermata= schermata;
		 presenter = new Presenter();
		 FXMLParameter = new ParametriFXML(null,false);
		bindingValuesAmministratore();
		
	}
	private void bindingValuesAmministratore() {
		amministratore.get(0).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Amministratore) cellData.getValue()).getIdUtente()));
		amministratore.get(1).setCellValueFactory(cellData -> new SimpleStringProperty(((Amministratore) cellData.getValue()).getNome()));
		amministratore.get(2).setCellValueFactory(cellData -> new SimpleStringProperty(((Amministratore) cellData.getValue()).getCognome()));
		amministratore.get(3).setCellValueFactory(cellData -> new SimpleStringProperty(((Amministratore) cellData.getValue()).getSesso()));
		amministratore.get(4).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Amministratore) cellData.getValue()).getDataNascita()));
		amministratore.get(5).setCellValueFactory(cellData -> new SimpleStringProperty(((Amministratore) cellData.getValue()).getIndirizzo()));
		amministratore.get(6).setCellValueFactory(cellData -> new SimpleStringProperty(((Amministratore) cellData.getValue()).getCodiceFiscale()));
		amministratore.get(7).setCellValueFactory(cellData -> new SimpleStringProperty(((Amministratore) cellData.getValue()).getNumFisso()));
		amministratore.get(8).setCellValueFactory(cellData -> new SimpleStringProperty(((Amministratore) cellData.getValue()).getNumCell()));
		
	}
	public void NuovoAmministratore() {
		FXMLParameter.setTitolo("Inserimento nuovo amministratore");
		Finestra.visualizzaFinestra(presenter, FXMLParameter, schermata, "MostraSchermataNuovoAmministratore",Modality.APPLICATION_MODAL);
		
	}
	public void ModificaAmministratore() throws CommonException{
		 if(((SchermataGenerale<?>) schermata).getElemSelezionato("Amministratore")< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
	    	FXMLParameter.setTitolo("Modifica amministratore");
	    	FXMLParameter.setEntity(((SchermataGenerale<?>) schermata).getEntitaElementoSelezionato("Amministratore"));
	    	Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataModificaAmministratore",Modality.APPLICATION_MODAL);
	    }
		
	}
}
