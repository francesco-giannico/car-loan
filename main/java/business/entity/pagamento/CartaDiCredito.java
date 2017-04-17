package business.entity.pagamento;

import java.time.LocalDate;

public class CartaDiCredito extends Pagamento{
	private int IDCliente ; 
	private LocalDate dataScadenza;
	private String IBAN;
	private String NumeroCarta;
	private tipiCircuiti Circuito;
	private int idCarta;
	

	public int getIdCarta() {
		return idCarta;
	}


	public void setIdCarta(int idCarta) {
		this.idCarta = idCarta;
	}


	public int getIDCliente() {
		return IDCliente;
	}


	public void setIDCliente(int iDCliente) {
		IDCliente = iDCliente;
	}


	public String getNumeroCarta() {
		return NumeroCarta;
	}


	public void setNumeroCarta(String numeroCarta) {
		NumeroCarta = numeroCarta;
	}

	public CartaDiCredito(float depositoCauzinale, float importo,
			float detrazioneAggiuntiva, float acconto, int idPagamento,
			int idCarta) {
		super(depositoCauzinale,importo,detrazioneAggiuntiva,acconto,idPagamento,idCarta);
		this.idCarta=idCarta;
	}

	
	public CartaDiCredito( int iDCliente,LocalDate dataScadenza,
			String iBAN, String numeroCarta, tipiCircuiti circuito,int idCarta) {
		super();
		this.idCarta=idCarta;
		IDCliente = iDCliente;
		this.dataScadenza = dataScadenza;
		IBAN = iBAN;
		NumeroCarta = numeroCarta;
		Circuito = circuito;
	}


	public LocalDate getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}



	public CartaDiCredito(){}


	public CartaDiCredito( String IBAN, String NumeroC,
			LocalDate dataSca, tipiCircuiti Circuito) {
		// TODO Auto-generated constructor stub
		this.IBAN=IBAN;
		this.NumeroCarta=NumeroC;
		this.dataScadenza=dataSca;
		this.Circuito=Circuito;
		
	}


	public tipiCircuiti getCircuito() {
		return Circuito;
	}


	public void setCircuito(tipiCircuiti circuito) {
		Circuito = circuito;
	}


	@Override
	public String toString() {
		return "CartaDiCredito [IDCliente=" + IDCliente + ", dataScadenza="
				+ dataScadenza + ", IBAN=" + IBAN + ", NumeroCarta="
				+ NumeroCarta + ", Circuito=" + Circuito + ", idCarta="
				+ idCarta + "]";
	}
	
		
}
