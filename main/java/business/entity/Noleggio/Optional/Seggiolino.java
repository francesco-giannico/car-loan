package business.entity.Noleggio.Optional;

public class Seggiolino extends OptionalAuto{
	/**
	 * Il numero di seggiolini 
	 */
	private int numeroSeggiolini;

	public Seggiolino(Integer id, float prezzo, String descrizione, String nome,int numeroSeggiolini) {
		super(id, prezzo, descrizione, nome);
		this.numeroSeggiolini=numeroSeggiolini;
		// TODO Auto-generated constructor stub
	}


	public int getnumero(){
		return numeroSeggiolini;
	}
	
	@Override
	public String toString(){
		return String.valueOf(numeroSeggiolini);
	}
}
