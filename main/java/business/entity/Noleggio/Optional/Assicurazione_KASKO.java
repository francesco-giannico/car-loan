package business.entity.Noleggio.Optional;

public class Assicurazione_KASKO extends OptionalNoleggio{
	private double copertura;
	public Assicurazione_KASKO(Integer id,float prezzo, String descrizione,String nome,double copertura) {
		super( id,prezzo, descrizione,nome);
		this.setCopertura(copertura);
	}
	public double getCopertura() {
		return copertura;
	}
	public void setCopertura(double copertura) {
		this.copertura = copertura;
	}

}
