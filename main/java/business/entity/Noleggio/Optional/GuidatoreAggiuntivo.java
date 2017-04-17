package business.entity.Noleggio.Optional;


public class GuidatoreAggiuntivo extends OptionalNoleggio{
	private int numero_guidatori;
	public GuidatoreAggiuntivo(Integer id,float prezzo, String descrizione,String nome,int numeroGuidatori) {
		super(id,prezzo, descrizione,nome);
		numero_guidatori=numeroGuidatori;
	}
	public int getNumero_guidatori() {
		return numero_guidatori;
	}

}
