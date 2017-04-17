package presentation.mvp.view.controller.generale.cliente;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.ParametriFXML;
import MessaggiFinestra.AlertView;
import business.entity.Cliente;
import business.entity.Entity;
import business.model.Exception.CommonException;

public class ModificaCliente extends NuovoCliente{

	@SuppressWarnings("rawtypes")
	public void impostaView(Schermata chiamante){
		SchermataGenerale scChiamante= (SchermataGenerale)chiamante;
		Cliente cliente =  (Cliente)scChiamante.getEntitaElementoSelezionato("Cliente");
		txtIndirizzo.setText(cliente.getIndirizzo());
		txtNumCel.setText(cliente.getNumCell());
		txtNumTel.setText(cliente.getNumTel());
		txtPartIva.setText(cliente.getPartitaIva());
		txtEmail.setText(cliente.getEmail());
		
	}
	
	@Override
	@SuppressWarnings({ "unchecked","rawtypes" })
	@FXML 
	public void btnConferma(ActionEvent event){

		SchermataGenerale scChiamante= (SchermataGenerale) this.getChiamante();
		cliente= (Cliente)scChiamante.getEntitaElementoSelezionato("Cliente");//ottengo le info sul cliente selezionato, ma ne cambio alcune
	
			try {

				cliente = prendiDatiDaView();
				presenter.processRequest("VerificaCliente", cliente);
				presenter.processRequest("ModificaCliente", cliente);
				//Prendo la schermata che ha chiamato questo metodo , li passo l'elemento selezionato , il cliente da modificare e la tabella su cui lavorare
				((SchermataGenerale)this.getChiamante()).caricaTabella((List<Cliente>)presenter.processRequest("getAllClienti",null), scChiamante.getTable("Cliente"));
				chiudiFinestra();
			} 
			catch(CommonException e){
				e.showMessage();
			}
			catch(InvocationTargetException e){
				new CommonException(((InvocationTargetException) e).getTargetException().getMessage()).showMessage();
				e.printStackTrace();
			}
			catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException  e) {
				e.printStackTrace();
			}	
	
	}
	@Override
	public Cliente prendiDatiDaView() throws CommonException{
		LocalDate dParam= null;
		if(txtNome.getText().isEmpty() || txtCognome.getText().isEmpty() || dNascita.getValue()==null ||  dEmissPatente.getValue() ==null ||
				txtIndirizzo.getText().isEmpty() ||txtCodFisc.getText().isEmpty() ||  txtNumCel.getText().isEmpty() ||  txtPatGuida.getText().isEmpty() || txtEmail.getText().isEmpty())
		
		{
			throw new CommonException("Prima di procedere è necessario compilare tutti i campi obbligatori");
		}
		else {
			
			cliente.setNome(txtNome.getText());
			
			cliente.setCognome(txtCognome.getText());
			
			cliente.setSesso(((RadioButton)group.getSelectedToggle()).getText());
	
			cliente.setDatanascita(dNascita.getValue());

			cliente.setDataEmissPatente(dEmissPatente.getValue());
			
			//data scadenza patente -> dataEmissione + 10 anni.
			dParam= dEmissPatente.getValue();
			dScadPatente.setValue(LocalDate.of(dParam.getYear()+10, dParam.getMonth(),dParam.getDayOfMonth()));
			cliente.setDataScadPatente(dScadPatente.getValue());
			
			cliente.setIndirizzo(txtIndirizzo.getText());
			
			cliente.setCodFiscale(txtCodFisc.getText());
			
			cliente.setNumCell(txtNumCel.getText());
			
			cliente.setNumTel(txtNumTel.getText());
			
			cliente.setPatenteGuida(txtPatGuida.getText());
	
			cliente.setPartitaIva(txtPartIva.getText());
			
			cliente.setEmail(txtEmail.getText());
			
			return cliente;
		}
	}
	
	@Override
	public void initData(Entity entity){
		cliente =  (Cliente)entity;
		txtIndirizzo.setText(cliente.getIndirizzo());
		txtNumCel.setText(cliente.getNumCell());
		txtNumTel.setText(cliente.getNumTel());
		txtPartIva.setText(cliente.getPartitaIva());
		txtEmail.setText(cliente.getEmail());
		txtNome.setText(cliente.getNome());
		txtCognome.setText(cliente.getCognome());
		if(cliente.getSesso().equals("Maschio")){
			group.selectToggle(rdMaschio);
		}
		else 
			group.selectToggle(rdFemmina);
		dEmissPatente.setValue(cliente.getDataEmissPatente());
		dNascita.setValue(cliente.getDatanascita());
		txtCodFisc.setText(cliente.getCodFiscale());
		txtPatGuida.setText(cliente.getPatenteGuida());
	}

}
