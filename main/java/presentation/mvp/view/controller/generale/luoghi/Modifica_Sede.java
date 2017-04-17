package presentation.mvp.view.controller.generale.luoghi;



import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Luoghi.Agenzia;
import business.entity.Luoghi.Sede;
import business.model.Exception.CommonException;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.generale.SchermataGenerale;
public class Modifica_Sede extends Nuova_Sede{
	private Sede sede_coinvolta;
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		agenzia=agenzie.getColumns();
		bindValuesAgenzia();
		initTable();
		
	}
	
	public void initData(Entity entity){
		this.sede_coinvolta=(Sede)entity;
		nome.setText(sede_coinvolta.getNome());
		num_telefono.setText(sede_coinvolta.getNumeroTelefono());
		indirizzo.setText(sede_coinvolta.getIndirizzo());
		for(Agenzia a:agenzie.getItems()){
			if(a.getIDAgenzia()==sede_coinvolta.getIDAgenzia()){
				agenzie.getSelectionModel().select(a);
				break;
			}
		}
	}
	
	@FXML
	public void btnAnnulla(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
	@SuppressWarnings("unchecked")
	@FXML
	public void btnConferma(ActionEvent e){
	
			try {
				SchermataGenerale<Sede> schermataGenerale = (SchermataGenerale<Sede>)this.getChiamante();
				ts= ((SchermataGenerale<Sede>)schermataGenerale).getTable("Sede");
				String n=nome.getText();
				if(n==null || n.isEmpty())
				throw new CommonException("Il nome non può essere vuoto");
				String tel=num_telefono.getText();
				if(tel==null)
					tel="";
				String indir=indirizzo.getText();
				if(indir==null || indir.isEmpty())
					throw new CommonException("L'indirizzo non può essere vuoto");
				Agenzia agenzia_scelta=this.agenzie.getSelectionModel().getSelectedItem();
				
				presenter.processRequest("AggiornaSede", new Sede(sede_coinvolta.getIDSede(),indir, tel, n,agenzia_scelta.getIDAgenzia()));
				schermataGenerale.caricaTabella((List<Sede>)presenter.processRequest("getAllSedi",null),ts);
				chiudiFinestra();
				
			}
			catch (CommonException e1) {
				// TODO Auto-generated catch block
				e1.showMessage();
			}
			 catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | NoSuchMethodException
						| SecurityException | IllegalArgumentException
						| InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
	}
	protected void caricaTabella(List<Agenzia> list){
		ObservableList<Agenzia> obsList= FXCollections.observableList(list);
		agenzie.setItems(obsList);
	}
}
	
