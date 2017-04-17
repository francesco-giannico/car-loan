package presentation.mvp.view.controller.generale;

import java.time.LocalDate;

import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.Finestra;
import utility.ParametriFXML;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import MessaggiFinestra.AlertView;
import business.entity.Cliente;
import business.model.Exception.CommonException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
public class TabClienti{
	
	private ObservableList<TableColumn<Cliente, ?>> cliente;
	
	

	private Schermata schermata;
	
	private ParametriFXML FXMLParameter;
	
	private Presenter presenter;
	
	public void NuovoCliente(){
		FXMLParameter.setTitolo("Nuovo Cliente");
	    FXMLParameter.setRidimensionabile(false);
		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataNuovoCliente",Modality.APPLICATION_MODAL);
	}
	
	@SuppressWarnings("rawtypes")
	public void ModificaCliente() throws CommonException{
		
	    if(((SchermataGenerale<?>) schermata).getElemSelezionato("Cliente")< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
	    	FXMLParameter.setTitolo("Modifica Cliente");
		    FXMLParameter.setRidimensionabile(false);
		    FXMLParameter.setEntity(((SchermataGenerale) schermata).getEntitaElementoSelezionato("Cliente"));
	    	Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataModificaCliente",Modality.APPLICATION_MODAL);	
	    }
	}
	public void NuovaCartaCredito(){
		if(((SchermataGenerale<?>) schermata).getElemSelezionato("Cliente")< 0){
	    		try {
					throw new CommonException("Nessun elemento selezionato");
				} catch (CommonException e) {
					AlertView.getAlertView("nessun elemento selezionato", AlertType.ERROR);
				}
	    }
		else {
			FXMLParameter.setTitolo("Nuova Carta Di Credito");
		    FXMLParameter.setRidimensionabile(false);
			Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataInserimentoCartaCredito",Modality.APPLICATION_MODAL);
		}
	}
	@SuppressWarnings("rawtypes")
	public void VisualizzaCartaCredito(){
		if(((SchermataGenerale<?>) schermata).getElemSelezionato("Cliente")< 0){
    		try {
				throw new CommonException("Nessun elemento selezionato");
			} catch (CommonException e) {
				e.showMessage();
			}
		}
		else{
			FXMLParameter.setTitolo("Visualizza Carte di credito");
			FXMLParameter.setRidimensionabile(false);
			FXMLParameter.setEntity(((SchermataGenerale) schermata).getEntitaElementoSelezionato("Cliente"));
			Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataVisualizzaCartaCredito",Modality.APPLICATION_MODAL);
		}
	}
	
	/**
	 * <p>Effettua il binding con i singoli campi della tabella</p>
	 */
	public void bindingValuesCliente(){
		
		cliente.get(0).setCellValueFactory(cellData -> new SimpleStringProperty( ((Cliente) cellData.getValue()).getCodFiscale()));
		
		cliente.get(0).setCellValueFactory(cellData -> new SimpleStringProperty(((Cliente) cellData.getValue()).getCodFiscale()));
		
		cliente.get(1).setCellValueFactory(cellData -> new SimpleStringProperty(((Cliente) cellData.getValue()).getNome()));
		
		cliente.get(2).setCellValueFactory(cellData -> new SimpleStringProperty(((Cliente) cellData.getValue()).getCognome()));
		
		cliente.get(3).setCellValueFactory(cellData -> new SimpleStringProperty(((Cliente) cellData.getValue()).getSesso()));
		
		cliente.get(4).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Cliente) cellData.getValue()).getDatanascita()));
		
		cliente.get(5).setCellValueFactory(cellData -> new SimpleStringProperty(((Cliente) cellData.getValue()).getIndirizzo()));
	
		cliente.get(6).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Cliente) cellData.getValue()).getDataEmissPatente()));

		cliente.get(7).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Cliente) cellData.getValue()).getDataScadPatente()));

		cliente.get(8).setCellValueFactory(cellData -> new SimpleStringProperty(((Cliente) cellData.getValue()).getPatenteGuida()));
		
		cliente.get(9).setCellValueFactory(cellData -> new SimpleStringProperty(((Cliente) cellData.getValue()).getNumCell()));

		cliente.get(10).setCellValueFactory(cellData -> new SimpleStringProperty(((Cliente) cellData.getValue()).getNumTel()));

		cliente.get(11).setCellValueFactory(cellData -> new SimpleStringProperty(((Cliente) cellData.getValue()).getPartitaIva()));

		cliente.get(12).setCellValueFactory(cellData -> new SimpleStringProperty(((Cliente) cellData.getValue()).getEmail()));
		

	}

	
	TabClienti(TableView<Cliente> tbCliente,Schermata schermata){
		cliente= tbCliente.getColumns();
		
		this.schermata= schermata;
		
		
		bindingValuesCliente();
		
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
	}
	
	
}
