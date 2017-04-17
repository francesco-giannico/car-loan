package business.entity.Auto.manutenzione;

import java.time.LocalDate;

public class ManutenzioneOrdinaria extends Manutenzione{

	public ManutenzioneOrdinaria(int iDManutenzione, LocalDate localDate,
			LocalDate dataFine, String note, int i) {
		super(iDManutenzione, localDate, dataFine, note, i);
	}
	public ManutenzioneOrdinaria( LocalDate localDate,
			LocalDate dataFine, String note, int i) {
		super(localDate, dataFine, note, i);
	}
	public ManutenzioneOrdinaria(int iDManutenzione, LocalDate localDate, String note, int i) {
		super(iDManutenzione, localDate,  note, i);
	}
}
