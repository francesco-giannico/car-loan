package presentation.mvp.view.controller.generale.luoghi;



import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import MessaggiFinestra.AlertView;
import business.entity.Utente;
import business.entity.UtenteCorrente;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Luoghi.Agenzia;
import business.entity.Luoghi.Sede;
import business.model.Exception.CommonException;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;
public class Nuova_Sede extends Schermata{
	@FXML
	protected TextField nome;
	@FXML
	protected TextField num_telefono;
	@FXML
	protected TextField indirizzo;
	@FXML
	protected TableView<Agenzia> agenzie;
	
	protected TableView<Sede> ts;
	protected ObservableList<TableColumn<Agenzia,?>> agenzia;
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		agenzia=agenzie.getColumns();
		bindValuesAgenzia();
		initTable();
	}
	
	@SuppressWarnings("unchecked")
	protected void initTable() {
		List<Agenzia> agenzie_list;
		try {
			Utente u=UtenteCorrente.getUtente();
			if(u instanceof Amministratore){
			 agenzie_list=(List<Agenzia>)presenter.processRequest("getAllAgenzie", null);
			}
			else{// E' un supervisore agenzia quindi può solamente aggiungere sedi alla propria agenzia
				SupervisoreAgenzia s=(SupervisoreAgenzia)u;
				Agenzia a=(Agenzia)presenter.processRequest("leggiAgenzia", s.getIDAgenzia());
				agenzie_list=new LinkedList<Agenzia>();
				agenzie_list.add(a);
			}
			
			caricaTabella(agenzie_list);
			agenzie.getSelectionModel().selectFirst();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void bindValuesAgenzia() {
		agenzia.get(0).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Agenzia) cellData.getValue()).getIDAgenzia()));
		agenzia.get(1).setCellValueFactory(cellData -> new SimpleStringProperty(((Agenzia) cellData.getValue()).getNome()));
		agenzia.get(2).setCellValueFactory(cellData -> new SimpleStringProperty(((Agenzia) cellData.getValue()).getNumTelefono()));
		
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
				presenter.processRequest("InserisciSede", new Sede(indir, tel, n,agenzia_scelta.getIDAgenzia()));
				Utente u=UtenteCorrente.getUtente();
				if(u instanceof Amministratore){
					schermataGenerale.caricaTabella((List<Sede>)presenter.processRequest("getAllSedi",null),ts);
				}
				else{// E' un supervisore agenzia quindi può solamente aggiungere sedi alla propria agenzia
					SupervisoreAgenzia s=(SupervisoreAgenzia)u;
					Agenzia a=(Agenzia)presenter.processRequest("leggiAgenzia", s.getIDAgenzia());
					schermataGenerale.caricaTabella((List<Sede>)presenter.processRequest("getAllSediByAgenzia",a.getIDAgenzia()),ts);
				}
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
	
