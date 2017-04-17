package presentation.mvp.view.controller.generale.noleggio.optional;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import business.entity.Entity;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.Optional.Assicurazione_KASKO;
import business.entity.Noleggio.Optional.GuidatoreAggiuntivo;
import business.entity.Noleggio.Optional.Optional;
import business.entity.Noleggio.Optional.Seggiolino;
import business.model.Exception.CommonException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.Finestra;
import utility.ParametriFXML;

public class VisualizzaOptional extends Schermata{
	  @FXML
	  private TableView<Optional> tbOptionalScelti;
	  @FXML
	  private TableColumn<Optional,String> nomeOptScelti;
	  @FXML
	  private TableColumn<Optional,String> descOptScelti;
	
	@SuppressWarnings("rawtypes")
	@FXML
	public void btnVisualizzaGuidatori(ActionEvent e){

			FXMLParameter.setTitolo("Optional");
		    FXMLParameter.setRidimensionabile(false);
		    FXMLParameter.setEntity(((SchermataGenerale)this.getChiamante()).getEntitaElementoSelezionato("Noleggio"));
			Finestra.visualizzaFinestra(presenter,FXMLParameter,this,"MostraSchermataVisualizzaGuidatori",Modality.APPLICATION_MODAL);	
	}
	
	private void bindingValuesOptional(){
		nomeOptScelti.setCellValueFactory(cellData ->  new SimpleStringProperty(((Optional) cellData.getValue()).getNome()));
		descOptScelti.setCellValueFactory(cellData ->  new SimpleStringProperty(((Optional) cellData.getValue()).getDescrizione()));
	}
	  
	  public void btnOk(ActionEvent e){
		  chiudiFinestra();
	  }
/**
	 * <p>Carica la tabella dei guidatori </p>
	 * @return
	 */
	private void caricaTabella(List<Optional> list){
		ObservableList<Optional> obsList= FXCollections.observableList(list);
		tbOptionalScelti.setItems(obsList);
	}
	
	@FXML
	private Label lblprezzoOptScelti;
	@FXML
	private Label lblLimiteCoperturaScelto;
	@FXML
	private Label lblNumSeggioliniScelti;
	@FXML
	private Label lblNumGuidAggiuntivi;
	
	private class ItemSelectedOptScelti implements ChangeListener<Optional>{

		@Override
		public void changed(ObservableValue<? extends Optional> observable,
				Optional oldValue, Optional newValue) {
			lblNumSeggioliniScelti.setText("");
			lblLimiteCoperturaScelto.setText("");
			lblNumGuidAggiuntivi.setText("");
			if(newValue!=null)
				lblprezzoOptScelti.setText(String.valueOf(newValue.getPrezzo()));
			if(newValue instanceof Seggiolino){
				lblNumSeggioliniScelti.setText(String.valueOf(((Seggiolino) newValue).getnumero()));
			}
			else if( newValue instanceof Assicurazione_KASKO){
				lblLimiteCoperturaScelto.setText(String.valueOf(((Assicurazione_KASKO)newValue).getCopertura()));
			}
			else if( newValue instanceof GuidatoreAggiuntivo){
				lblNumGuidAggiuntivi.setText(String.valueOf(((GuidatoreAggiuntivo)newValue).getNumero_guidatori()));
			}
		}
	}
	@FXML
	private Button btnVGuidatore;
	@SuppressWarnings("unchecked")
	@Override
	public void initData(Entity entity){
		try {
			List<Optional> optional= (List<Optional>)presenter.processRequest("getAllOptionalByNoleggio",((Noleggio)entity).getIDNoleggio());
			caricaTabella(optional);
			ObservableList<Optional> listItem= tbOptionalScelti.getItems();
			if(listItem!=null && !listItem.isEmpty()) {
				for(Optional op: listItem){
					if(op instanceof GuidatoreAggiuntivo){
						btnVGuidatore.setVisible(true);
						break;
					}
					else {
						btnVGuidatore.setVisible(false);
					}
				}
			}
			else {
				btnVGuidatore.setVisible(false);
			}
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
		bindingValuesOptional();
		tbOptionalScelti.getSelectionModel().selectedItemProperty().addListener( new ItemSelectedOptScelti());
	}
}
