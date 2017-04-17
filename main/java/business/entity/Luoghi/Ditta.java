package business.entity.Luoghi;

public class Ditta {
	private String PartitaIVA;
	private String NomeDitta;
	private String LogoDitta;
	private String Telefono;
	private String IDDitta;
	public Ditta(String partitaIVA, String nomeDitta, String logoDitta,
			String telefono, String iDDitta) {
		super();
		PartitaIVA = partitaIVA;
		NomeDitta = nomeDitta;
		LogoDitta = logoDitta;
		Telefono = telefono;
		IDDitta = iDDitta;
	}
	public String getPartitaIVA() {
		return PartitaIVA;
	}
	public void setPartitaIVA(String partitaIVA) {
		PartitaIVA = partitaIVA;
	}
	public String getNomeDitta() {
		return NomeDitta;
	}
	public void setNomeDitta(String nomeDitta) {
		NomeDitta = nomeDitta;
	}
	public String getLogoDitta() {
		return LogoDitta;
	}
	public void setLogoDitta(String logoDitta) {
		LogoDitta = logoDitta;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getIDDitta() {
		return IDDitta;
	}
	public void setIDDitta(String iDDitta) {
		IDDitta = iDDitta;
	}
	
}
