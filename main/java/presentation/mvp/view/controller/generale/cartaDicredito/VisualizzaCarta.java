package presentation.mvp.view.controller.generale.cartaDicredito;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import business.entity.Cliente;
import business.entity.Entity;
import business.entity.pagamento.CartaDiCredito;
import business.model.Exception.CommonException;
import javafx.beans.property.SimpleObjectProperty;
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

public class VisualizzaCarta extends Schermata{
  @FXML
  private TableView<CartaDiCredito> tbCarte;
  @FXML
  private TableColumn<CartaDiCredito,String> iban;
  @FXML
  private TableColumn<CartaDiCredito,String> numCarta;
  @FXML
  private TableColumn<CartaDiCredito,LocalDate> dScadenza;
  @FXML
  private TableColumn<CartaDiCredito,String> circuito;
  
  
  public void btnOk(ActionEvent e){
	  chiudiFinestra();
  }
  /**
	 * <p>Carica la tabella dei clienti graficamente</p>
	 * @param listaClienti
	 * @return
	 */
	private void caricaTabella(List<CartaDiCredito> list){
		ObservableList<CartaDiCredito> obsList= FXCollections.observableList(list);
		tbCarte.setItems(obsList);
	}
	private void bindingValuesCarta(){
		iban.setCellValueFactory(cellData ->  new SimpleStringProperty(((CartaDiCredito) cellData.getValue()).getIBAN()));
		numCarta.setCellValueFactory(cellData ->  new SimpleStringProperty(((CartaDiCredito) cellData.getValue()).getNumeroCarta()));
		dScadenza.setCellValueFactory(cellData ->  new SimpleObjectProperty<LocalDate>(((CartaDiCredito) cellData.getValue()).getDataScadenza()));
		circuito.setCellValueFactory(cellData ->  new SimpleStringProperty(((CartaDiCredito) cellData.getValue()).getCircuito().toString()));
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void initData(Entity entity){
		try {
			caricaTabella((List<CartaDiCredito>)presenter.processRequest("getCarteByCliente",((Cliente)entity).getId()));
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false,false);	
		bindingValuesCarta();
	
		
	}
  
  
}
