package presentation.mvp.view.controller.generale.cartaDicredito;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Cliente;
import business.entity.pagamento.CartaDiCredito;
import business.entity.pagamento.tipiCircuiti;
import business.model.Exception.CommonException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import MessaggiFinestra.AlertView;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import presentation.mvp.view.controller.generale.noleggio.NuovoNoleggio;
import utility.ParametriFXML;

public class NuovaCarta extends Schermata {
	@FXML private TextField txtIban;
	@FXML private TextField txtNumCarta;
	@FXML private DatePicker dScadenza;
	@FXML private ChoiceBox<tipiCircuiti> choiceCircuito;
	private Schermata scChiamante;
	private Cliente cliente;
	@SuppressWarnings("rawtypes")
	@FXML
	public void btnConferma(ActionEvent e){
		if(this.getChiamante() instanceof SchermataGenerale){
			scChiamante= (SchermataGenerale) this.getChiamante();
			cliente= (Cliente)((SchermataGenerale) scChiamante).getEntitaElementoSelezionato("Cliente");
		}
		else {
			 scChiamante = (NuovoNoleggio)this.getChiamante();
			cliente= (Cliente)((NuovoNoleggio) scChiamante).getEntitaElementoSelezionato();
		}
		try {

				CartaDiCredito carta= prendiDatiDaView();
				presenter.processRequest("VerificaCarta", carta);	
				presenter.processRequest("InserimentoCarta", carta);
				if(scChiamante instanceof NuovoNoleggio){
					((NuovoNoleggio) scChiamante).refreshCarteCredito();
				}
				chiudiFinestra();
		}
		catch(CommonException e1){
			AlertView.getAlertView(e1.getMessage(), AlertType.ERROR);
		}
		catch(InvocationTargetException e1){
			new CommonException(e1.getTargetException().getMessage()).showMessage();
		}
		catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException e1) {
			e1.printStackTrace();
		}
	 }
	

	public CartaDiCredito prendiDatiDaView() throws CommonException{
		CartaDiCredito carta= new CartaDiCredito();
		if(txtIban.getText().isEmpty() || txtNumCarta.getText().isEmpty() || dScadenza.getValue()==null){
			throw new CommonException("Prima di procedere, setta i campi obbligatori");
		}
		carta.setCircuito(choiceCircuito.getSelectionModel().getSelectedItem());
		carta.setIBAN(txtIban.getText());
		carta.setDataScadenza(dScadenza.getValue());
		carta.setNumeroCarta(txtNumCarta.getText());
		carta.setIDCliente(cliente.getId());
		return carta;
	}

	@FXML
	public void btnCancella(ActionEvent event){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false,false);	
		settaChoiceBox();
	}
	
	public void settaChoiceBox(){
		ObservableList<tipiCircuiti> choice = FXCollections.observableArrayList(tipiCircuiti.getAllCircuiti());
		choiceCircuito.setItems(choice);
		choiceCircuito.getSelectionModel().selectFirst();
	}
}
