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
import business.entity.Gestori.SupervisoreSede;
import business.entity.Luoghi.Sede;
import business.model.Exception.CommonException;
public class TabSede {
	private ObservableList<TableColumn<Sede,?>> sede;
	private TableView<Sede> table_sede;
	private TableView<SupervisoreSede> table_supsede;
	private ObservableList<TableColumn<SupervisoreSede,?>> supsede;
	private Schermata schermata;
	private ParametriFXML FXMLParameter;
	private Presenter presenter;
	TabSede(TableView<Sede> tbSede,Schermata schermata,TableView<SupervisoreSede> t){
		sede= tbSede.getColumns();
		this.schermata= schermata;
		this.table_sede=tbSede;
		 presenter = new Presenter();
		 FXMLParameter = new ParametriFXML(null,false);
		 this.table_supsede=t;
		 supsede=table_supsede.getColumns();
		bindingValuesSede();
		
	}
	private void bindingValuesSede() {
		sede.get(0).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Sede) cellData.getValue()).getIDSede()));
		sede.get(1).setCellValueFactory(cellData -> new SimpleStringProperty(((Sede) cellData.getValue()).getNome()));
		sede.get(2).setCellValueFactory(cellData -> new SimpleStringProperty(((Sede) cellData.getValue()).getNumeroTelefono()));
		sede.get(3).setCellValueFactory(cellData -> new SimpleStringProperty(((Sede) cellData.getValue()).getIndirizzo()));
		///
		supsede.get(0).setCellValueFactory(cellData -> new SimpleIntegerProperty(((SupervisoreSede) cellData.getValue()).getIdUtente()));
		supsede.get(1).setCellValueFactory(cellData -> new SimpleStringProperty(((SupervisoreSede) cellData.getValue()).getNome()));
		supsede.get(2).setCellValueFactory(cellData -> new SimpleStringProperty(((SupervisoreSede) cellData.getValue()).getCognome()));
		supsede.get(3).setCellValueFactory(cellData -> new SimpleStringProperty(((SupervisoreSede) cellData.getValue()).getCodiceFiscale()));
		
	}
	public void NuovaSede() {
		FXMLParameter.setTitolo("Inserimento nuova sede");
		Finestra.visualizzaFinestra(presenter, FXMLParameter, schermata, "MostraSchermataNuovaSede",Modality.APPLICATION_MODAL);
		
	}
	public void ModificaSede() throws CommonException{
		 if(((SchermataGenerale<?>) schermata).getElemSelezionato("Sede")< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
	    	FXMLParameter.setTitolo("Modifica sede");
	    	FXMLParameter.setEntity(((SchermataGenerale<?>) schermata).getEntitaElementoSelezionato("Sede"));
	    	Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataModificaSede",Modality.APPLICATION_MODAL);
	    }
		
	}
}
