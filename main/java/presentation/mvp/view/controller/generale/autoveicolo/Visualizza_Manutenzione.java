package presentation.mvp.view.controller.generale.autoveicolo;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.time.LocalDate;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.manutenzione.ManutenzioneOrdinaria;
import business.entity.Auto.manutenzione.ManutenzioneStraordinaria;
import business.model.Exception.CommonException;
import presentation.mvp.view.Presenter;
import utility.ParametriFXML;

public class Visualizza_Manutenzione extends Nuova_Manutenzione{
	private Autoveicolo a;
	private ObservableList<TableColumn<ManutenzioneOrdinaria,?>> manutenzioni_ord;
	private ObservableList<TableColumn<ManutenzioneStraordinaria,?>> manutenzioni_stra;
	@FXML
	TableView<ManutenzioneOrdinaria> table_manutenzioni_ord;
	@FXML
	TableView<ManutenzioneStraordinaria> table_manutenzioni_stra;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter=new ParametriFXML(null, false);
		manutenzioni_ord=table_manutenzioni_ord.getColumns();
		manutenzioni_stra=table_manutenzioni_stra.getColumns();
		
		
		}
	@SuppressWarnings("unchecked")
	public void initData(Entity x){
		this.a=(Autoveicolo)x;
		InputStream i=null;
		try {
			i = (InputStream)presenter.processRequest("leggiImmagineAutoveicolo", a.getIDauto());
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(i!=null)
			immagine.setImage(new Image(i));
		else
			immagine.setImage(null);
		targa.setText(a.getTarga());
		modello.setText(a.getModello());
		
		manutenzioni_ord.get(0).setCellValueFactory(cellData ->  new SimpleIntegerProperty(((ManutenzioneOrdinaria) cellData.getValue()).getIDManutenzione()));
		manutenzioni_ord.get(1).setCellValueFactory(cellData ->  new SimpleObjectProperty<LocalDate>(((ManutenzioneOrdinaria) cellData.getValue()).getDatainizio()));
		manutenzioni_ord.get(2).setCellValueFactory(cellData ->  new SimpleObjectProperty<LocalDate>(((ManutenzioneOrdinaria) cellData.getValue()).getDataFine()));
		manutenzioni_ord.get(3).setCellValueFactory(cellData ->  new SimpleStringProperty(((ManutenzioneOrdinaria) cellData.getValue()).getNote()));
		
		manutenzioni_stra.get(0).setCellValueFactory(cellData ->  new SimpleIntegerProperty(((ManutenzioneStraordinaria) cellData.getValue()).getIDManutenzione()));
		manutenzioni_stra.get(1).setCellValueFactory(cellData ->  new SimpleObjectProperty<LocalDate>(((ManutenzioneStraordinaria) cellData.getValue()).getDatainizio()));
		manutenzioni_stra.get(2).setCellValueFactory(cellData ->  new SimpleObjectProperty<LocalDate>(((ManutenzioneStraordinaria) cellData.getValue()).getDataFine()));
		manutenzioni_stra.get(3).setCellValueFactory(cellData ->  new SimpleStringProperty(((ManutenzioneStraordinaria) cellData.getValue()).getNote()));
		
		
		try {
			List<ManutenzioneOrdinaria> l=(List<ManutenzioneOrdinaria>) presenter.processRequest("getAllManutenzioni_ordinarie", a.getIDauto());
			List<ManutenzioneStraordinaria> l2=(List<ManutenzioneStraordinaria>) presenter.processRequest("getAllManutenzioni_straordinarie", a.getIDauto());
			if(l.isEmpty() && l2.isEmpty())
				AlertView.getAlertView("Quest'auto non ha mai subito manutenzioni di nessun tipo", AlertType.ERROR);
			else{
				caricatabella_ord(l, table_manutenzioni_ord);
				caricatabella_stra(l2, table_manutenzioni_stra);
				
			}
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void caricatabella_ord(List<ManutenzioneOrdinaria> manutenzioni,TableView<ManutenzioneOrdinaria> t){
		ObservableList<ManutenzioneOrdinaria> obsList= FXCollections.observableList(manutenzioni);
		t.setItems(obsList);
		t.getSelectionModel().selectFirst();
	}
	private void caricatabella_stra(List<ManutenzioneStraordinaria> manutenzioni,TableView<ManutenzioneStraordinaria> t){
		ObservableList<ManutenzioneStraordinaria> obsList= FXCollections.observableList(manutenzioni);
		t.setItems(obsList);
		t.getSelectionModel().selectFirst();
	}
	@FXML
	public void btntornaindietro(ActionEvent e){
		chiudiFinestra();
	}
}
