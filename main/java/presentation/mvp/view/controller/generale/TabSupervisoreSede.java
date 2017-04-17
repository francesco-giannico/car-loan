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
import business.entity.Gestori.SupervisoreSede;
import business.model.Exception.CommonException;

import java.time.LocalDate;
public class TabSupervisoreSede {
	private ObservableList<TableColumn<SupervisoreSede,?>> supervisoresede;
	private TableView<SupervisoreSede> table_supervisoresede;
	private Schermata schermata;
	private ParametriFXML FXMLParameter;
	private Presenter presenter;
	
	
	
	TabSupervisoreSede(TableView<SupervisoreSede> tbsupervisoresede,Schermata schermata){
		supervisoresede= tbsupervisoresede.getColumns();
		this.schermata= schermata;
		this.table_supervisoresede=tbsupervisoresede;
		 presenter = new Presenter();
		 FXMLParameter = new ParametriFXML(null,false);
		bindingValuessupervisoresede();
		
	}
	private void bindingValuessupervisoresede() {
		supervisoresede.get(0).setCellValueFactory(cellData -> new SimpleIntegerProperty(((SupervisoreSede) cellData.getValue()).getIdUtente()));
		supervisoresede.get(1).setCellValueFactory(cellData -> new SimpleStringProperty(((SupervisoreSede) cellData.getValue()).getNome()));
		supervisoresede.get(2).setCellValueFactory(cellData -> new SimpleStringProperty(((SupervisoreSede) cellData.getValue()).getCognome()));
		supervisoresede.get(3).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((SupervisoreSede) cellData.getValue()).getDataNascita()));
		supervisoresede.get(4).setCellValueFactory(cellData -> new SimpleStringProperty(((SupervisoreSede) cellData.getValue()).getIndirizzo()));
		supervisoresede.get(5).setCellValueFactory(cellData -> new SimpleStringProperty(((SupervisoreSede) cellData.getValue()).getCodiceFiscale()));
		supervisoresede.get(6).setCellValueFactory(cellData -> new SimpleStringProperty(((SupervisoreSede) cellData.getValue()).getNumCell()));
		supervisoresede.get(7).setCellValueFactory(cellData -> new SimpleStringProperty(((SupervisoreSede) cellData.getValue()).getNumFisso()));
		
	}
	public void NuovoSupervisoreSede() {
		FXMLParameter.setTitolo("Inserimento nuovo Supervisore Sede");
		Finestra.visualizzaFinestra(presenter, FXMLParameter, schermata, "MostraSchermataNuovoSupervisoreSede",Modality.APPLICATION_MODAL);
		
	}
	public void ModificaSupervisoreSede() throws CommonException{
		 if(((SchermataGenerale<?>) schermata).getElemSelezionato("SupervisoreSede")< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
	    	FXMLParameter.setTitolo("Modifica Supervisore Sede");
	    	FXMLParameter.setEntity(((SchermataGenerale<?>) schermata).getEntitaElementoSelezionato("SupervisoreSede"));
	    	Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataModificaSupervisoreSede",Modality.APPLICATION_MODAL);
	    }
		
	}
}
