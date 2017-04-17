package presentation.mvp.view.controller.generale;


import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.Finestra;
import utility.ParametriFXML;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import business.entity.Luoghi.Agenzia;
import business.model.Exception.CommonException;
public class TabAgenzia {
	private ObservableList<TableColumn<Agenzia,?>> agenzia;
	private Schermata schermata;
	private ParametriFXML FXMLParameter;
	private Presenter presenter;
	TabAgenzia(TableView<Agenzia> tbAgenzia,Schermata schermata){
		agenzia= tbAgenzia.getColumns();
		this.schermata= schermata;
		bindingValuesAgenzia();
		 presenter = new Presenter();
		 FXMLParameter = new ParametriFXML(null,false);
	}
	private void bindingValuesAgenzia() {
		agenzia.get(0).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Agenzia) cellData.getValue()).getIDAgenzia()));
		agenzia.get(1).setCellValueFactory(cellData -> new SimpleStringProperty(((Agenzia) cellData.getValue()).getNome()));
		agenzia.get(2).setCellValueFactory(cellData -> new SimpleStringProperty(((Agenzia) cellData.getValue()).getNumTelefono()));
		
		
	}
	public void NuovaAgenzia() {
		FXMLParameter.setTitolo("Inserimento nuova agenzia");
		Finestra.visualizzaFinestra(presenter, FXMLParameter, schermata, "MostraSchermataNuovaAgenzia",Modality.APPLICATION_MODAL);
		
	}
	public void ModificaAgenzia() throws CommonException{
		 if(((SchermataGenerale<?>) schermata).getElemSelezionato("Agenzia")< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
	    	FXMLParameter.setTitolo("Modifica agenzia");
	    	FXMLParameter.setEntity(((SchermataGenerale<?>) schermata).getEntitaElementoSelezionato("Agenzia"));
	    	Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataModificaAgenzia",Modality.APPLICATION_MODAL);
	    }
		
	}
}
