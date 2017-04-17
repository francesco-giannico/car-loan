package business.model.checker.cartaCredito;

import java.time.LocalDate;

import business.entity.Entity;
import business.entity.pagamento.CartaDiCredito;
import business.model.Exception.CommonException;
import business.model.checker.Checker;

public class CartaCreditoChecker implements Checker{
   private static final int IBAN_LENGTH = 27;
   private static final int  NUMCARTA_LENGTH = 12;
   private  CartaDiCredito cartaDiCredito;
	@Override
	public void check(Entity entity) throws CommonException {
		cartaDiCredito= (CartaDiCredito)entity;
		checkIban();
		checkNumCarta();
		checkData();
	}
	private void checkIban() throws CommonException{
		if(cartaDiCredito.getIBAN().length()!=IBAN_LENGTH){
			throw new CommonException("Lunghezza codice IBAN non valida");
		}
	}
	private void checkNumCarta() throws CommonException{
		if(cartaDiCredito.getNumeroCarta().length()!=NUMCARTA_LENGTH){
			throw new CommonException("Lunghezza numero carta non valida");
		}
	}
	private void checkData() throws CommonException{
		if(cartaDiCredito.getDataScadenza().isBefore(LocalDate.now())){
			throw new CommonException("Carta Di Credito Scaduta, quindi la carta non è valida");
		}
	}
}
