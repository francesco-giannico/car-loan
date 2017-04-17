package presentation.mvp.view.controller.generale;


import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.Finestra;
import utility.ParametriFXML;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.StatoContratto;
import business.model.Exception.CommonException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TabContratto {
	
	private ObservableList<TableColumn<Contratto,?>> contratto;
		
	private TableView<Contratto> tbContratto;

	private Schermata schermata;
	
	private ParametriFXML FXMLParameter;
	
	private Presenter presenter;
	
	/**
	 * 
	 * @param schermata importante per settare la view
	 */
	public void NuovoContratto(){
		FXMLParameter.setTitolo("Nuovo Contratto");
	    FXMLParameter.setRidimensionabile(false);
		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataNuovoContratto",Modality.APPLICATION_MODAL);
	
	}		
	@SuppressWarnings("rawtypes")
	public void ModificaContratto() throws CommonException{
		
	    if(((SchermataGenerale<?>) schermata).getElemSelezionato("Contratto")< 0){
	    		throw new CommonException("Nessun elemento selezionato");
	    }
	    else{
	    	FXMLParameter.setTitolo("Modifica Contratto");
		    FXMLParameter.setRidimensionabile(false);
		    FXMLParameter.setEntity(((SchermataGenerale) schermata).getEntitaElementoSelezionato("Contratto"));
	    	if(((Contratto)tbContratto.getSelectionModel().getSelectedItem()).getStato().toString().equals(StatoContratto.Aperto.toString())){
	    		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataModificaContratto",Modality.APPLICATION_MODAL);
	    	}
	    	else{
	    		throw new CommonException("Operazione non disponibile per questo contratto");
	    	}
	    }
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void ChiudiContratto() throws CommonException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		Contratto contrattoo=null;
		if(((SchermataGenerale<?>) schermata).getElemSelezionato("Contratto")< 0){
    		throw new CommonException("Nessun elemento selezionato");
		}
		contrattoo= (Contratto) ((SchermataGenerale) schermata).getEntitaElementoSelezionato("Contratto");
		if(!(contrattoo.getStato().toString().equals(StatoContratto.Aperto.toString()))){
			throw new CommonException("Operazione non disponibile per questo contratto");
		}
		List<Noleggio> noleggiAperti= (List<Noleggio>)presenter.processRequest("getNoleggiAperti", contrattoo.getIDContratto());
		if(!noleggiAperti.isEmpty()){
			throw new CommonException("Ci sono dei noleggi aperti , non è possibile fare questa scelta");
		}
		List<Noleggio> noleggi  = (List<Noleggio>)presenter.processRequest("getNoleggiByContratto", contrattoo.getIDContratto());
		for(Noleggio n: noleggi){
			if((int)presenter.processRequest("countMulteAperteByNoleggio", n.getIDNoleggio())>0){
				throw new CommonException("Ci sono dei noleggi con delle multe aperte,è necessario  pagarle prima di poter chiudere contratto");
			}
		}
		contrattoo.setDataChiusura(LocalDate.now());//imposto la data di chiusura se il valore scelto è annullato
		FXMLParameter.setTitolo("Chiudi Contratto");
	    FXMLParameter.setRidimensionabile(false);
	    FXMLParameter.setEntity(((SchermataGenerale) schermata).getEntitaElementoSelezionato("Contratto"));
		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataChiusuraContratto",Modality.APPLICATION_MODAL);			
	}
	    	
	    		
	/**
	 * <p>Effettua il binding con i singoli campi della tabella</p>
	 */
	public void bindingValuesContratto(){

		
		contratto.get(0).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Contratto) cellData.getValue()).getIDContratto()));
		
		contratto.get(1).setCellValueFactory(cellData -> new SimpleStringProperty(((Contratto) cellData.getValue()).getStato().toString()));
		
		contratto.get(2).setCellValueFactory(cellData -> new SimpleStringProperty(((Contratto) cellData.getValue()).getNote()));
		
		contratto.get(3).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Contratto) cellData.getValue()).getDataCreazione()));
		
		contratto.get(4).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Contratto) cellData.getValue()).getDataChiusura()));
	
	}

	
	
	public 
	TabContratto(TableView<Contratto> tbContratto,Schermata schermata){
		contratto= tbContratto.getColumns();
		
		this.schermata=schermata;
		this.tbContratto=tbContratto;
		
		//
		bindingValuesContratto();
		
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
	}

	
	
}
