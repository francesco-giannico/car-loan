package presentation.mvp.view.controller.generale.cliente;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Cliente;
import business.entity.Entity;
import business.model.Exception.CommonException;
import MessaggiFinestra.AlertView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.ParametriFXML;
public class NuovoCliente extends Schermata{

	@FXML
	protected TextField txtNome;
	@FXML
	protected TextField txtCognome;
	@FXML
	protected RadioButton rdMaschio;
	@FXML
	protected RadioButton rdFemmina;
	@FXML
	protected DatePicker dEmissPatente;
	@FXML
	protected DatePicker dNascita;
	@FXML
	protected TextField txtIndirizzo;
	@FXML
	protected TextField txtCodFisc;
	@FXML
	protected TextField txtNumCel;
	@FXML
	protected TextField txtNumTel;
	@FXML
	protected TextField txtPatGuida;
	@FXML
	protected TextField txtPartIva;
	@FXML
	protected TextField txtEmail;

	protected DatePicker dScadPatente = new DatePicker();
	
	private TableView<Cliente> tw;
	protected final ToggleGroup group = new ToggleGroup();
	protected Cliente cliente;
	@FXML
	public void btnCancella(ActionEvent event){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void btnConferma(ActionEvent event){
		tw=  ((SchermataGenerale)this.getChiamante()).getTable("Cliente");
		try {
				Cliente cliente= prendiDatiDaView();
				presenter.processRequest("VerificaCliente", cliente);	
				presenter.processRequest("InserimentoCliente", cliente);
				//Chiama il metodo della schermata che ha chiamato questa schermata per settare nella tabella dei clienti i clienti ricavati
				((SchermataGenerale)this.getChiamante()).caricaTabella((List<Cliente>)presenter.processRequest("getAllClienti",null), tw);
				chiudiFinestra();
				
		}
		catch(CommonException e){
			e.showMessage();
		}
		catch(InvocationTargetException e){
			new CommonException(e.getTargetException().getMessage()).showMessage();
		}
		catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				 e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>Prende i valori inseriti e ci crea un istanza.</p>
	 * @return
	 */
	
	public Cliente prendiDatiDaView() throws CommonException{
		LocalDate dParam= null;
		cliente=new Cliente();
		if(txtNome.getText().isEmpty() || txtCognome.getText().isEmpty() || dNascita.getValue()==null ||  dEmissPatente.getValue() ==null ||
				txtIndirizzo.getText().isEmpty() ||txtCodFisc.getText().isEmpty() ||  txtNumCel.getText().isEmpty() ||  txtPatGuida.getText().isEmpty() || txtEmail.getText().isEmpty())
		
		{
			throw new CommonException("Prima di procedere è necessario compilare tutti i campi obbligatori");
		}
		else {
			
			cliente.setNome(txtNome.getText());
			
			cliente.setCognome(txtCognome.getText());
			
			cliente.setSesso(((RadioButton)group.getSelectedToggle()).getText());
		System.out.println(((RadioButton)group.getSelectedToggle()).getText());
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		
		rdMaschio.setToggleGroup(group);
		rdMaschio.setSelected(true);
		
		rdFemmina.setToggleGroup(group);
		
		dEmissPatente.setValue(LocalDate.of(1980,1,1));
		dNascita.setValue(LocalDate.of(1980,1,1));
	}
}
