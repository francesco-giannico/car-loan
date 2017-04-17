package presentation.mvp.view.controller.generale;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.noleggio.RicercaNoleggio;
import utility.Finestra;
import utility.ParametriFXML;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.StatoNoleggio;
import business.model.Exception.CommonException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
public class TabNoleggio {

	private ObservableList<TableColumn<Noleggio,?>> noleggio;
		
	private TableView<Noleggio> tbNoleggio;

	private Schermata schermata;
	
	private ParametriFXML FXMLParameter;
	
	private Presenter presenter;
	
	
	public void NuovoNoleggio(){
		FXMLParameter.setTitolo("Nuovo Noleggio");
	    FXMLParameter.setRidimensionabile(false);
		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataNuovoNoleggio",Modality.APPLICATION_MODAL);
	}
	
	public void AnnullaNoleggio(){
		try { 
			if(((SchermataGenerale<?>) schermata).getElemSelezionato("Noleggio")<0)
					throw new CommonException("Nessun elemento selezionato");
			
			Noleggio noleggioo= (Noleggio) ((SchermataGenerale<?>)schermata).getEntitaElementoSelezionato("Noleggio");

			noleggioo.getStato();
			noleggioo.getStato();
			if(noleggioo.getStato().toString().equals(StatoNoleggio.annullato.toString())
					|| noleggioo.getStato().toString().equals(StatoNoleggio.chiuso.toString())){
				throw new CommonException("Operazione non disponibile per questo noleggio");
			}
			if(noleggioo.getRitiro().isEqual(LocalDate.now())|| noleggioo.getRitiro().isBefore(LocalDate.now())){
				throw new CommonException("Noleggio già iniziato, non è possibile annullarlo.");
			}
			
			else if(noleggioo.getRitiro().minusDays(2).isAfter(LocalDate.now())){
				FXMLParameter.setTitolo("Annulla noleggio");
			    FXMLParameter.setRidimensionabile(false);
			    FXMLParameter.setEntity(noleggioo);
				Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataAnnullaNoleggio",Modality.APPLICATION_MODAL);
			}
			 else{
				 throw new CommonException("Non è possibile annullare: Fuori intervallo limite annullamento");
			 }
				 
		} catch (CommonException e) {
			e.showMessage();
		}
	}
	
	public void ChiudiNoleggio(){
		try {
			if(((SchermataGenerale<?>) schermata).getElemSelezionato("Noleggio")<0)
					throw new CommonException("Nessun elemento selezionato");
			
			Noleggio noleggio= (Noleggio) ((SchermataGenerale<?>)schermata).getEntitaElementoSelezionato("Noleggio");

			noleggio.getStato();
			noleggio.getStato();
			if(noleggio.getStato().toString().equals(StatoNoleggio.annullato.toString())
					|| noleggio.getStato().toString().equals(StatoNoleggio.chiuso.toString()) ){
				throw new CommonException("Operazione non disponibile per questo noleggio");
			}
			if(noleggio.getRitiro().isAfter(LocalDate.now())){
				throw new CommonException("Noleggio non ancora iniziato");
			}
			
			else if((noleggio.getRientro().isBefore(LocalDate.now()))){
				FXMLParameter.setTitolo("Termine noleggio");
			    FXMLParameter.setRidimensionabile(false);
			    FXMLParameter.setEntity(noleggio);
				Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataChiusuraNoleggio",Modality.APPLICATION_MODAL);
			}
			 else{
				 throw new CommonException("Noleggio non ancora terminato, non è possibile chiuderlo prima della data stabilita");
			 }
				 
		} catch (CommonException e) {
			e.showMessage();
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	void visualizzaOptional() throws CommonException{
		if(((SchermataGenerale<?>) schermata).getElemSelezionato("Noleggio")<0)
			throw new CommonException("Noleggio non selezionato");
		else{
		FXMLParameter.setTitolo("Optional");
	    FXMLParameter.setRidimensionabile(false);
	    FXMLParameter.setEntity(((SchermataGenerale)schermata).getEntitaElementoSelezionato("Noleggio"));
		Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataVisualizzaOptional",Modality.APPLICATION_MODAL);
		}
	}
	
	void NuovaMulta(){
		try {
			if(((SchermataGenerale<?>) schermata).getElemSelezionato("Noleggio")<0){
					throw new CommonException("Nessun elemento selezionato");
			}
			Noleggio noleggio= (Noleggio) ((SchermataGenerale<?>)schermata).getEntitaElementoSelezionato("Noleggio");
			noleggio.getStato();
			if(noleggio.getStato().toString().equals(StatoNoleggio.annullato.toString())){
				throw new CommonException("Non è possibile aprire una multa per questo noleggio in quanto è stato annullato");
			}
			else {
				FXMLParameter.setTitolo("Nuova Multa");
			    FXMLParameter.setRidimensionabile(false);
			    FXMLParameter.setEntity(noleggio);
				Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataInserimentoMulta",Modality.APPLICATION_MODAL);
			}
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.showMessage();
		}
	
	}

	void VisualizzaMulta(){
		try{
		if(((SchermataGenerale<?>) schermata).getElemSelezionato("Noleggio")<0){
				throw new CommonException("Nessun elemento selezionato");
		}
		Noleggio noleggio= (Noleggio) ((SchermataGenerale<?>)schermata).getEntitaElementoSelezionato("Noleggio");
		if(noleggio.getStato().toString().equals(StatoNoleggio.annullato.toString())){
			throw new CommonException("Non è possibile visualizzare le multe di questo noleggio in quanto è stato annullato");
		}
		else {
			FXMLParameter.setTitolo("Visualizza multe");
		    FXMLParameter.setRidimensionabile(false);
		    FXMLParameter.setEntity(((SchermataGenerale<?>)schermata).getEntitaElementoSelezionato("Noleggio"));
			Finestra.visualizzaFinestra(presenter,FXMLParameter,schermata,"MostraSchermataVisualizzaMulte",Modality.APPLICATION_MODAL);
		}
	} catch (CommonException e) {
		// TODO Auto-generated catch block
		e.showMessage();
	}
	}
	@SuppressWarnings({ "unchecked", "rawtypes"})
	void ricerca(int idContratto, StatoNoleggio stato,
		LocalDate dStart) {
	try {
		if(dStart==null && idContratto==0 
				&& stato.toString().equals(StatoNoleggio.vuoto.toString())){
			((SchermataGenerale)schermata).caricaTabella((List<Noleggio>)presenter.processRequest("getAllNoleggi", null), tbNoleggio);
		}
		else {
	    RicercaNoleggio ricercaNoleggio = new RicercaNoleggio(stato,dStart,idContratto);
		((SchermataGenerale)schermata).caricaTabella((List<Noleggio>)presenter.processRequest("RicercaNoleggio", ricercaNoleggio), tbNoleggio);
		}
	} catch (InstantiationException | IllegalAccessException
		| ClassNotFoundException | NoSuchMethodException
		| SecurityException | IllegalArgumentException
		| InvocationTargetException | CommonException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
}
	

	
	public void bindingValuesContratto(){
		noleggio.get(0).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Noleggio) cellData.getValue()).getIDNoleggio()));
		noleggio.get(1).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Noleggio) cellData.getValue()).getInizioNoleggio()));
		noleggio.get(2).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Noleggio) cellData.getValue()).getFineNoleggio()));
		noleggio.get(3).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Noleggio) cellData.getValue()).getRientro()));
		noleggio.get(4).setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Noleggio) cellData.getValue()).getRitiro()));
		noleggio.get(5).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Noleggio) cellData.getValue()).getKmBase()));
		noleggio.get(6).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Noleggio) cellData.getValue()).getKmRientro()));
		noleggio.get(7).setCellValueFactory(cellData -> new SimpleStringProperty(((Noleggio) cellData.getValue()).getStato().toString()));
		noleggio.get(8).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Noleggio) cellData.getValue()).getNumeroSettimane()));
		noleggio.get(9).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Noleggio) cellData.getValue()).getNumeroGiorni()));
		noleggio.get(10).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Noleggio) cellData.getValue()).getNumeroChilometri()));
	}


	TabNoleggio(TableView<Noleggio> tbNoleggio,Schermata schermata){
		noleggio= tbNoleggio.getColumns();
		this.schermata= schermata;
		this.tbNoleggio=tbNoleggio;
		bindingValuesContratto();
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
	}


}
