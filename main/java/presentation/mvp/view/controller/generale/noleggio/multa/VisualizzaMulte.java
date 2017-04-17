package presentation.mvp.view.controller.generale.noleggio.multa;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import business.entity.Entity;
import business.entity.Noleggio.Multa;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.StatoMulta;
import business.model.Exception.CommonException;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.Finestra;
import utility.ParametriFXML;

public class VisualizzaMulte extends Schermata{
	@FXML
	private TableView<Multa> tbMulta;
	@FXML
	private TableColumn<Multa,Integer> IDMulta;
	@FXML
	private TableColumn<Multa,LocalDate>DataMulta;
	@FXML
	private TableColumn<Multa,LocalDate>DataScadenza;
	@FXML
	private TableColumn<Multa,LocalDate>DataPagamento;
	@FXML
	private TableColumn<Multa,Float>Importo;
	@FXML
	private TableColumn<Multa,Float>CostoAggiuntivo;
	@FXML
	private TableColumn<Multa,String>Stato;
	@FXML
	private TableColumn<Multa,String>Note;
	private Noleggio noleggio;
	@FXML
	public void btnOk(ActionEvent e){
		chiudiFinestra();
	}

	private void bindingValuesMulta(){
		IDMulta.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(((Multa)cellData.getValue()).getIDMulta()));
		DataMulta.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Multa)cellData.getValue()).getDataMulta()));
		DataScadenza.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Multa)cellData.getValue()).getDataScadenza()));
		DataPagamento.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(((Multa)cellData.getValue()).getDataPagamento()));
		Importo.setCellValueFactory(cellData -> new SimpleObjectProperty<Float>(((Multa)cellData.getValue()).getImporto()));
		CostoAggiuntivo.setCellValueFactory(cellData -> new SimpleObjectProperty<Float>(((Multa)cellData.getValue()).getUlterioreAddebito()));
		Stato.setCellValueFactory(cellData -> new SimpleStringProperty(((Multa)cellData.getValue()).getStato().toString()));
		Note.setCellValueFactory(cellData -> new SimpleStringProperty(((Multa)cellData.getValue()).getNote()));
	}
	/**
	 * <p>Carica la tabella dei guidatori </p>
	 * @return
	 */
	public void caricaTabella(List<Multa> list){
		ObservableList<Multa> obsList= FXCollections.observableList(list);
		tbMulta.setItems(obsList);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initData(Entity entity){
		try {
			 noleggio = (Noleggio)entity;
			List<Multa> multa= (List<Multa>)presenter.processRequest("getAllMulteByNoleggio",noleggio.getIDNoleggio());
			caricaTabella(multa);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.showMessage();
		}
	}
	
	@FXML
	public void btnChiudi(ActionEvent e){
		Multa multa= tbMulta.getSelectionModel().getSelectedItem();
		try {
			if(tbMulta.getSelectionModel().getSelectedIndex()<0){
				
					throw new CommonException("Seleziona una multa");
				
			}
			else if(multa.getStato()==StatoMulta.Aperto){
				FXMLParameter.setTitolo("Chiusura Multa");
			    FXMLParameter.setRidimensionabile(false);
			    FXMLParameter.setEntity(tbMulta.getSelectionModel().getSelectedItem());
				Finestra.visualizzaFinestra(presenter,FXMLParameter,this,"MostraSchermataChiusuraMulta",Modality.APPLICATION_MODAL);
			}
		else 
			throw new CommonException( "La multa è già stata chiusa!");
		} catch (CommonException e1) {
			e1.showMessage();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false,false);	
		bindingValuesMulta();
		}
	
}
