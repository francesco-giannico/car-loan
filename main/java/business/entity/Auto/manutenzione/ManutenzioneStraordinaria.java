package business.entity.Auto.manutenzione;

import java.time.LocalDate;

public class ManutenzioneStraordinaria extends Manutenzione{
	public ManutenzioneStraordinaria(int iDManutenzione, LocalDate datainizio,
			LocalDate dataFine, String note,int i) {
		super(iDManutenzione, datainizio, dataFine, note,i);
	}
	public ManutenzioneStraordinaria( LocalDate datainizio,
			LocalDate dataFine, String note,int i) {
		super(datainizio, dataFine, note,i);
	}
	
	public ManutenzioneStraordinaria(int iDManutenzione, LocalDate datainizio, String note,int i) {
		super(iDManutenzione, datainizio, note,i);
	}
}
