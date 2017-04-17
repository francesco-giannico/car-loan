package presentation.mvp.view.controller.generale.luoghi;


import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Luoghi.Agenzia;
import business.model.Exception.CommonException;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.generale.SchermataGenerale;

public class Modifica_Agenzia extends Nuova_Agenzia{
	private TableView<Agenzia> tw;
	private Agenzia agenzia;
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		
	}
	public void initData(Entity entity){
		this.agenzia=(Agenzia)entity;
		nome.setText(agenzia.getNome());
		num_telefono.setText(agenzia.getNumTelefono());
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
		
		SchermataGenerale<Agenzia> schermataGenerale = (SchermataGenerale<Agenzia>)this.getChiamante();
		tw= ((SchermataGenerale<Agenzia>)schermataGenerale).getTable("Agenzia");
	
		String n=nome.getText();
		try {
			if(n==null || n.isEmpty()){
				throw new CommonException("Il nome non può essere vuoto");
			}
			else{
				String tel=num_telefono.getText();
				if(tel==null)
					tel="";
				agenzia.setNome(n);
				agenzia.setNumTelefono(tel);
				presenter.processRequest("AggiornaAgenzia", agenzia);
				schermataGenerale.caricaTabella((List<Agenzia>)presenter.processRequest("getAllAgenzie",null), tw);
				chiudiFinestra();
			}
		}
		catch (CommonException e1) {
			e1.showMessage();
		}
		catch(InvocationTargetException e1){
			new CommonException(e1.getTargetException().getMessage()).showMessage();
		}
		catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
	
