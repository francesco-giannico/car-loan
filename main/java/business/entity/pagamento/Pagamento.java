package business.entity.pagamento;

import business.entity.Entity;

public abstract class Pagamento extends Entity{
   private float depositoCauzinale;
   private float importo;
   private float detrazioneAggiuntiva;
   private float acconto;
   private int idPagamento;
   private int idCarta;

   
	public int getIdCarta() {
	return idCarta;
}
public void setIdCarta(int idCarta) {
	this.idCarta = idCarta;
}
	public int getIdPagamento() {
	return idPagamento;
}
public void setIdPagamento(int idPagamento) {
	this.idPagamento = idPagamento;
}
	public float getAcconto() {
	return acconto;
}
public void setAcconto(float acconto) {
	this.acconto = acconto;
}
	public float getDepositoCauzinale() {
		return depositoCauzinale;
	}
	public void setDepositoCauzinale(float depositoCauzinale) {
		this.depositoCauzinale = depositoCauzinale;
	}
	public float getImporto() {
		return importo;
	}
	public void setImporto(float importo) {
		this.importo = importo;
	}
	public float getDetrazioneAggiuntiva() {
		return detrazioneAggiuntiva;
	}
	public void setDetrazioneAggiuntiva(float detrazioneAggiuntiva) {
		this.detrazioneAggiuntiva = detrazioneAggiuntiva;
	}
	
		public Pagamento(float depositoCauzinale, float importo,
			float detrazioneAggiuntiva, float acconto, int idPagamento,
			int idCarta) {
		super();
		this.depositoCauzinale = depositoCauzinale;
		this.importo = importo;
		this.detrazioneAggiuntiva = detrazioneAggiuntiva;
		this.acconto = acconto;
		this.idPagamento = idPagamento;
		this.idCarta = idCarta;
	}
		public Pagamento(float depositoCauzinale, float detrazioneAggiuntiva) {
			super();
			this.depositoCauzinale = depositoCauzinale;
			this.detrazioneAggiuntiva = detrazioneAggiuntiva;
		}
		public Pagamento(){}
}
