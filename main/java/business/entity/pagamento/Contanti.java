package business.entity.pagamento;

public class Contanti extends Pagamento{

	public Contanti(float depositoCauzinale, float importo,
			float detrazioneAggiuntiva, float acconto, int idPagamento,
			int idCarta) {
		super(depositoCauzinale,importo,detrazioneAggiuntiva,acconto,idPagamento,idCarta);
	}
	public Contanti(){}
}
