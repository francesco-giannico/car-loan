drop database carloan;
create database Carloan;

use Carloan;

CREATE TABLE IF NOT EXISTS Ditta(
	IDDItta integer(11) primary key auto_increment, 
	PartitaIVA char(11) COLLATE utf8mb4_unicode_ci NOT NULL,
	NomeDitta varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
	LogoDitta varbinary(2000) ,
	UNIQUE (PartitaIVA)
); 

CREATE TABLE IF NOT EXISTS Agenzia(
	 IDAgenzia integer(11) primary key auto_increment,
	 NumeroTelefono char(15)  COLLATE utf8mb4_unicode_ci NOT NULL,
	 Nome varchar(20)  COLLATE utf8mb4_unicode_ci NOT NULL,
	 IDDItta integer(11) not null,
	 
	 
	 CONSTRAINT fk_IDDIttaAgenzia
	 FOREIGN KEY(IDDItta)
	 REFERENCES Ditta(IDDItta)
);

CREATE TABLE IF NOT EXISTS Sede(
	 IDSede	 integer(11) primary key auto_increment,
	 Indirizzo varchar(50)  COLLATE utf8mb4_unicode_ci NOT NULL,
	 NumeroTelefono char(10)  COLLATE utf8mb4_unicode_ci NOT NULL,
	 Nome varchar(10)  COLLATE utf8mb4_unicode_ci NOT NULL,
	 IDAgenzia integer(11) NOT null,
	 
	 
	 CONSTRAINT fk_IDAgenzia
	 FOREIGN KEY(IDAgenzia)
	 REFERENCES Agenzia(IDAgenzia)
);

CREATE TABLE IF NOT EXISTS Amministratore(
	IDAmministratore integer(11) primary key auto_increment,
	Nome varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
	Cognome varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
	Sesso char(7) COLLATE utf8mb4_unicode_ci NOT NULL,
	DataNascita date not null,
	Indirizzo varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL ,
	CodiceFiscale varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
	NumCell varchar(20)COLLATE utf8mb4_unicode_ci NOT NULL,
	NumFisso  varchar(20) COLLATE utf8mb4_unicode_ci,
	Assunto char(1) COLLATE utf8mb4_unicode_ci not null, 
	IDDItta integer(11) NOT null,
	
	UNIQUE (CodiceFiscale),
	
	CONSTRAINT fk_IDDittaAmm
	FOREIGN KEY(IDDItta)
	REFERENCES Ditta(IDDitta)
	
);

CREATE TABLE IF NOT EXISTS SupervisoreAgenzia(
	IDSupervisoreAgenzia integer(11) primary key auto_increment,
	Nome varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
	Cognome varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
	Sesso char(7) COLLATE utf8mb4_unicode_ci NOT NULL,
	DataNascita date,
	Indirizzo varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL ,
	CodiceFiscale varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
	NumCell varchar(20)COLLATE utf8mb4_unicode_ci NOT NULL,
	NumFisso  varchar(20) COLLATE utf8mb4_unicode_ci,
	Assunto char(1),
	IDAgenzia integer(11) NOT null,
	
	UNIQUE (CodiceFiscale),
	
	CONSTRAINT fk_IDSupAgenzia
	FOREIGN KEY (IDAgenzia)
	REFERENCES Agenzia(IDAgenzia)
);

CREATE TABLE IF NOT EXISTS  SupervisoreSede (
	IDSupervisoreSede integer(11) primary key auto_increment,
	Nome varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
	Cognome varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
	Sesso char(7) COLLATE utf8mb4_unicode_ci NOT NULL,
	DataNascita date,
	Indirizzo varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL ,
	CodiceFiscale varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
	NumCell varchar(20)COLLATE utf8mb4_unicode_ci NOT NULL,
	NumFisso  varchar(20) COLLATE utf8mb4_unicode_ci,
	Assunto char(1) not null,
	IDSede integer(11) NOT null,
	
	UNIQUE (CodiceFiscale),
	
	CONSTRAINT fk_IDSupSede
	FOREIGN KEY(IDSede)
	REFERENCES Sede(IDSede)
);


CREATE TABLE IF NOT EXISTS Operatore(
	IDOperatore integer(11) primary key auto_increment,
	Nome varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
	Cognome varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
	Sesso char(7) COLLATE utf8mb4_unicode_ci NOT NULL,
	DataNascita date,
	Indirizzo varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL ,
	CodiceFiscale varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
	NumCell varchar(20)COLLATE utf8mb4_unicode_ci NOT NULL,
	NumFisso  varchar(20) COLLATE utf8mb4_unicode_ci,
	Assunto char(1) not null,
	IDSede integer(11) NOT null,
	
	UNIQUE (CodiceFiscale),
	
	CONSTRAINT fk_IDopSede
	FOREIGN KEY(IDSede)
	REFERENCES Sede(IDSede)
);


CREATE TABLE IF NOT EXISTS Credenziali(
	IDCredenziali integer(11) primary key auto_increment,
	Username varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
	Password varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
	
	IDSupervisoreAgenzia integer(11) , 
	IDSupervisoreSede integer(11) ,
	IDAmministratore integer(11) ,
	IDOperatore integer(11) ,
	
	UNIQUE(Username),
	UNIQUE (IDSupervisoreAgenzia),
	UNIQUE (IDSupervisoreSede),
	UNIQUE (IDAmministratore),
	UNIQUE(IDOperatore),
	
	
	CONSTRAINT fk_UserSupA
	FOREIGN KEY(IDSupervisoreAgenzia)
	REFERENCES SupervisoreAgenzia(IDSupervisoreAgenzia),
	
	CONSTRAINT fk_UserSupS
	FOREIGN KEY(IDSupervisoreSede)
	REFERENCES SupervisoreSede(IDSupervisoreSede),
	
	CONSTRAINT fk_UserOperatore
	FOREIGN KEY(IDOperatore)
	REFERENCES Operatore(IDOperatore),
	
	CONSTRAINT fk_UserAmministratore
	FOREIGN KEY(IDAmministratore)
	REFERENCES Amministratore(IDAmministratore)
	
);


CREATE TABLE IF NOT EXISTS Fascia(
	IDFascia integer(11) primary key auto_increment, 
	Prezzo float(10) NOT NULL,
	Descrizione varchar(100) COLLATE utf8mb4_unicode_ci,
	Nome varchar(20) COLLATE utf8mb4_unicode_ci not null,
	Costo_kilometrico float(10) not null
);

CREATE TABLE IF NOT EXISTS Autoveicolo(
	IDAuto integer(11) primary key auto_increment,
	Targa char(7) COLLATE utf8mb4_unicode_ci not null,
	Marca varchar(100) COLLATE utf8mb4_unicode_ci not null,
	Modello varchar(100) COLLATE utf8mb4_unicode_ci not null,
	AlimPrincipale varchar(10) COLLATE utf8mb4_unicode_ci not null,
	AlimSecondaria varchar(10) COLLATE utf8mb4_unicode_ci,
	Colore varchar(10)  COLLATE utf8mb4_unicode_ci,
	Cambio varchar(10) COLLATE utf8mb4_unicode_ci,
	Immatricolazione date NOT NULL,
	Cilindrata integer(10) NOT NULL, 
	Potenza integer(10) NOT Null,
	NroPosti integer(10) NOT null,
	NroTelaio varchar(10) COLLATE utf8mb4_unicode_ci not null,
	Disponibilita varchar(25) COLLATE utf8mb4_unicode_ci not null,
	UltimoKm integer(10) NOT Null,
	CapPortaBagagli integer(10),
	Note varchar(100) COLLATE utf8mb4_unicode_ci,
	Immagine mediumblob,
	DataScadAssic date NOT null,
	OptionalAuto varchar(150) COLLATE utf8mb4_unicode_ci ,
	Prezzo float(10) NOT Null,
	DanniFutili varchar(200) COLLATE utf8mb4_unicode_ci,
	DanniGravi varchar(200) COLLATE utf8mb4_unicode_ci ,
	IDSede integer(11) NOT null,
	IDFascia integer(11) NOT null,

	
	UNIQUE(Targa),
	
	CONSTRAINT fk_AutoSede
	FOREIGN KEY(IDSede)
	REFERENCES Sede(IDSede),	
	
	CONSTRAINT fk_AutoFascia
	FOREIGN KEY(IDFascia)
	REFERENCES Fascia(IDFascia)
);


CREATE TABLE IF NOT EXISTS ManutenzioneOrdinaria(
	IDManutenzione integer(11) primary key auto_increment not null,
	DataInizio date not null,
	DataFine date ,
 	Note varchar(50)  COLLATE utf8mb4_unicode_ci not null,
	IDAuto integer(11) NOT null,
	

	CONSTRAINT fk_AutoManOrdinaria
	FOREIGN KEY(IDAuto)
	REFERENCES Autoveicolo(IDAuto)
);

CREATE TABLE IF NOT EXISTS ManutenzioneStraordinaria(
	IDManutenzione integer(11) primary key auto_increment not null,
	DataInizio date NOT Null,
	DataFine date  ,
 	Note varchar(50)  COLLATE utf8mb4_unicode_ci not null,
	IDAuto integer(11) NOT null,
	
	
	CONSTRAINT fk_AutoManStraordinaria
	FOREIGN KEY(IDAuto)
	REFERENCES Autoveicolo(IDAuto)
);

CREATE TABLE IF NOT EXISTS `Cliente` (
  `IDCliente` integer(11) primary key auto_increment, 
  `Nome` char(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Cognome` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Sesso` varchar(7) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DataEmissPatente` date NOT NULL,
  `DataNascita` date NOT NULL,
  `Indirizzo` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CodFiscale` char(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `NumCell` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `NumTel` char(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PatenteGuida` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DataScadPatente` date NOT NULL,
  `PartitaIva` char(11) COLLATE utf8mb4_unicode_ci ,
  `Email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
   UNIQUE `CodFiscale` (`CodFiscale`),
   UNIQUE  PatenteGuida (PatenteGuida)
);

CREATE TABLE IF NOT EXISTS Contratto(
	IDContratto integer(11) primary key auto_increment,
	Stato varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
	DataCreazione date  NOT NULL,
	Note varchar(200) COLLATE utf8mb4_unicode_ci,
	DataChiusura date ,
	IDCliente  integer(11) NOT null,
	IDOperatore integer(11) ,
	IDSupervisoreAgenzia integer(11) ,
	IDSupervisoreSede integer(11),
	IDAmministratore integer(11),
	
	
	
	CONSTRAINT fk_ClienteContratto
	FOREIGN KEY(IDCliente)
	REFERENCES Cliente(IDCliente),
	
	CONSTRAINT fk_OperatoreContratto
	FOREIGN KEY(IDOperatore)
	REFERENCES Operatore(IDOperatore),
	
	CONSTRAINT fk_SupervisoreAContratto
	FOREIGN KEY(IDSupervisoreAgenzia)
	REFERENCES SupervisoreAgenzia(IDSupervisoreAgenzia),

	
	CONSTRAINT fk_SupervisoreSContratto
	FOREIGN KEY(IDSupervisoreSede)
	REFERENCES SupervisoreSede(IDSupervisoreSede),
	
	CONSTRAINT fk_AmministratoreContratto
	FOREIGN KEY(IDAmministratore)
	REFERENCES Amministratore(IDAmministratore)
	
	
);

 
 

CREATE TABLE IF NOT EXISTS  CartaDiCredito (
	 IDIban integer(11) primary key auto_increment,
	 IBAN char(27) COLLATE utf8mb4_unicode_ci not null,
	 NumeroCarta char(12) COLLATE utf8mb4_unicode_ci NOT NULL,
	 DataScadenza date NOT null,
	 Circuito varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
	 IDCliente integer(11) NOT null, 
	 
	 UNIQUE(IBAN),
	 UNIQUE(NumeroCarta),
	 
	 CONSTRAINT fk_IDClienteCarta
	 FOREIGN KEY(IDCliente)
	 REFERENCES Cliente(IDCliente)
);



CREATE TABLE IF NOT EXISTS  Pagamento (
	IDPagamento integer(11) primary key auto_increment,
	DepCauzionale float(10,1) COLLATE utf8mb4_unicode_ci NOT NULL,
	ImportoFinale  float(10,1) ,
	acconto float(10,1) not null,
	DetrazioneAggiuntiva float(10,1) ,
	IDIban integer(11),
	
	CONSTRAINT fk_IbanID
	FOREIGN KEY (IDIban)
	REFERENCES CartaDiCredito(IDIban)
);



 
 CREATE TABLE IF NOT EXISTS  Optional (
	IDOptional integer(11) primary key auto_increment, 
	Prezzo float(10) NOT NULL,
	Descrizione varchar(200) COLLATE utf8mb4_unicode_ci ,
	LimiteCopertura double(10,1),
	Nome varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
	NumSeggiolini integer(10),
	NumGuidatoriAggiuntivi integer(10) 
 );

 
CREATE TABLE IF NOT EXISTS  Noleggio (
	IDNoleggio integer(11) primary key auto_increment,
	InizioNoleggio date NOT NULL,
	FineNoleggio date ,
	Rientro date not null,
	Ritiro date NOT NULL,
	KmRientro integer(10),
	KmBase integer(10) NOT NULL,
	Stato varchar(20) NOT NULL,
	NumeroSettimane integer(10) ,
	NumeroGiorni integer(10),
	Numero_chilometri integer(10),
	LuogoRestituzione integer(11) ,
	IDContratto integer(11) NOT null ,
	IDPagamento integer(11) NOT Null,
	IDAuto integer(11) NOT Null,
	Note  varchar(200) COLLATE utf8mb4_unicode_ci ,
	
	
	UNIQUE(IDPagamento),
	
	
	CONSTRAINT fk_IDContratto
	FOREIGN KEY(IDContratto)
	REFERENCES Contratto(IDContratto),
	
	CONSTRAINT fk_IDPagamento
	FOREIGN KEY(IDPagamento)
	REFERENCES Pagamento(IDPagamento),
	
	CONSTRAINT fk_IDAutoNoleggio
	FOREIGN KEY(IDAuto)
	REFERENCES Autoveicolo(IDAuto)

);

CREATE TABLE IF NOT EXISTS  Guidatore (
	IDGuidatore integer(11) primary key auto_increment ,
	Nome varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
	Cognome varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
	Indirizzo varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
	CodFiscale char(16)  COLLATE utf8mb4_unicode_ci NOT NULL,
	NumeroPatente char(12) COLLATE utf8mb4_unicode_ci NOT NULL,
	IDNoleggio integer(11) not null,
	
	Unique(CodFiscale),
	UNIQUE(NumeroPatente),
	

	CONSTRAINT fk_NoleggioGuidatore
	FOREIGN KEY (IDNoleggio)
	REFERENCES Noleggio(IDNoleggio)
);
CREATE TABLE IF NOT EXISTS  NoleggioOptional(
	IDNoleggio integer(11),
	IDOptional integer(11),
	primary Key (IDNoleggio,IDOptional),
	
	CONSTRAINT fk_NoleggioOptional
	FOREIGN KEY (IDNoleggio)
	REFERENCES Noleggio(IDNoleggio),
	
	CONSTRAINT fk_OptionalNOleggio
	FOREIGN KEY (IDOptional)
	REFERENCES Optional(IDOptional)
);


CREATE TABLE IF NOT EXISTS  Multa(
	IDMulta integer(11) primary key auto_increment,
	DataMulta date NOT NULL,
	Importo float(10) COLLATE utf8mb4_unicode_ci NOT NULL,
	Note varchar(150) COLLATE utf8mb4_unicode_ci,
	Stato varchar(20) COLLATE utf8mb4_unicode_ci not null,
	DataScadenza date not null,
	DataPagamento date,
	UlterioreAddebitoRitardi float(10),
	IDNoleggio integer(11) NOT null,
	
	
	CONSTRAINT fk_MultaNoleggio
	FOREIGN KEY (IDNoleggio)
	REFERENCES Noleggio(IDNoleggio)
);
