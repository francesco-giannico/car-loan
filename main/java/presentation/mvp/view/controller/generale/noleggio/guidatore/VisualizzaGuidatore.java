package presentation.mvp.view.controller.generale.noleggio.guidatore;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import business.entity.Entity;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.Optional.Guidatore;
import business.model.Exception.CommonException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import utility.ParametriFXML;

public class VisualizzaGuidatore extends Schermata{
	  @FXML
	  private TableView<Guidatore> tbGuidatori;
	  @FXML
	  private TableColumn<Guidatore,String> codFiscale;
	  @FXML
	  private TableColumn<Guidatore,String> nomeECognome;
	  @FXML
	  private TableColumn<Guidatore,String> indirizzo;
	  @FXML
	  private TableColumn<Guidatore,String> patente;
	
	  
	  public void btnOk(ActionEvent e){
		  chiudiFinestra();
	  }
  /**
	 * <p>Carica la tabella dei guidatori </p>
	 * @return
	 */
	private void caricaTabella(List<Guidatore> list){
		ObservableList<Guidatore> obsList= FXCollections.observableList(list);
		tbGuidatori.setItems(obsList);
	}
	private void bindingValuesGuidatore(){
		codFiscale.setCellValueFactory(cellData ->  new SimpleStringProperty(((Guidatore) cellData.getValue()).getCodFiscale()));
		nomeECognome.setCellValueFactory(cellData ->  new SimpleStringProperty(((Guidatore) cellData.getValue()).getNome()+ " "+ ((Guidatore) cellData.getValue()).getCognome()));
		indirizzo.setCellValueFactory(cellData ->  new SimpleStringProperty(((Guidatore) cellData.getValue()).getIndirizzo()));
		patente.setCellValueFactory(cellData ->  new SimpleStringProperty(((Guidatore) cellData.getValue()).getPatenteGuida()));
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void initData(Entity entity){
		try {
			List<Guidatore> guidatori= (List<Guidatore>)presenter.processRequest("getAllGuidatoriByNoleggio",((Noleggio)entity).getIDNoleggio());
			caricaTabella(guidatori);
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false,false);	
		bindingValuesGuidatore();	
	}
}
