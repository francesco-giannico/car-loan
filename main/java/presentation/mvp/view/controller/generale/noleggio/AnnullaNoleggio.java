package presentation.mvp.view.controller.generale.noleggio;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.Disponibilita;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.StatoNoleggio;
import business.model.Exception.CommonException;
import MessaggiFinestra.AlertView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;

public class AnnullaNoleggio extends Schermata{
	@FXML
	private TextArea textAreaAnnulla;
	@SuppressWarnings("rawtypes")
	private SchermataGenerale schermata;
	private TableView<Noleggio> tw;
	Noleggio noleggio;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void btnConferma(ActionEvent e){
		schermata= (SchermataGenerale) this.getChiamante();
		tw= schermata.getTable("Noleggio");
		noleggio.setNote(textAreaAnnulla.getText());
		noleggio.setFineNoleggio(LocalDate.now());
		noleggio.setStato(StatoNoleggio.annullato);
		try {
			presenter.processRequest("VerificaAnnullaNoleggio", noleggio);
			presenter.processRequest("AnnullaNoleggio", noleggio);
			Autoveicolo auto= (Autoveicolo) presenter.processRequest("letturaAutoveicolo", noleggio.getIdAuto());
			auto.setDisponibilita(Disponibilita.Disponibile);
			presenter.processRequest("AggiornamentoAutoveicolo", auto);
			AlertView.getAlertView("Noleggio annullato con successo",AlertType.INFORMATION);
			schermata.caricaTabella((List<Noleggio>)presenter.processRequest("getAllNoleggi",null), tw);
			chiudiFinestra();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e2) {
			e2.printStackTrace();
		}
	}
	@FXML
	public void btnIndietro(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Se inseriti,perderai tutti i dati ",AlertType.CONFIRMATION);
			if(result.isPresent() && result.get() == ButtonType.OK)
				this.chiudiFinestra();
	}
	
	@Override
	public void initData(Entity entity){
			noleggio=(Noleggio)entity;
	}
}
