package presentation.mvp.view.controller.generale.noleggio.multa;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import presentation.mvp.view.controller.generale.noleggio.multa.VisualizzaMulte;
import presentation.mvp.view.controller.Schermata;
import business.entity.Entity;
import business.entity.Noleggio.Multa;
import business.entity.Noleggio.StatoMulta;
import business.model.Exception.CommonException;
import MessaggiFinestra.AlertView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ChiudiMulta extends Schermata{
	@FXML
	private TextField txtRitardo;
	@FXML
	private TextArea textAreaNote;
	private LocalDate dPagamento;
	private Multa multa;
	
	@SuppressWarnings("unchecked")
	@FXML
	public void btnConferma(ActionEvent e){
		
		try {
			prendiDatiDaView();
			try {
				presenter.processRequest("CheckMultaChiusura", multa);
				presenter.processRequest("ChiusuraMulta", multa);
				((VisualizzaMulte)this.getChiamante()).caricaTabella((List<Multa>)presenter.processRequest("getAllMulteByNoleggio",multa.getIdNoleggio()));
			
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			chiudiFinestra();
		}
		catch(CommonException e1){
			e1.showMessage();
		}
		
		
	}
	@FXML
	public void btnCancella(ActionEvent e){
	Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
			if(result.isPresent() && result.get() == ButtonType.OK)
				this.chiudiFinestra();
	}

	private void prendiDatiDaView() throws CommonException{
		try{
			if(!txtRitardo.isDisabled())
				multa.setUlterioreAddebito((Float.parseFloat(txtRitardo.getText())));
			}
			catch(NumberFormatException e){
				throw new CommonException("L'addebito ulteriore deve essere composto da soli numeri");	
			}
			multa.setNote(textAreaNote.getText());
			multa.setDataPagamento(dPagamento);
			multa.setStato(StatoMulta.Chiuso);
	}
	

	@SuppressWarnings("static-access")
	public void initData(Entity x){
		multa=(Multa)x;
		textAreaNote.setText(((Multa)multa).getNote());
		//data di oggi
		dPagamento=dPagamento.of(LocalDate.now().getYear(),LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
		if(dPagamento.isBefore(multa.getDataScadenza())){
			txtRitardo.setDisable(true);
		}
	}
}
