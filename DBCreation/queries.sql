use sql290277;
 
insert into Ditta (PartitaIVA,NomeDitta) values ('49849584939','Carloan');

insert into Agenzia (NumeroTelefono,Nome,IDDitta) values('0883509293','ABari',1);
insert into Agenzia (NumeroTelefono,Nome,IDDitta) values('0883509293','ARoma',1);

insert into Sede (Indirizzo,NumeroTelefono,Nome,IDAgenzia) values('Viale Vespucci,13,70125,Puglia,Ba','0883409584','SedeBari1',1);
insert into Sede (Indirizzo,NumeroTelefono,Nome,IDAgenzia) values('Viale Largomagno,11,70125,Puglia,Ba','0883409584','SedeBari2',1);

insert into Sede (Indirizzo,NumeroTelefono,Nome,IDAgenzia) values('Viale Largomagno,13,7700,Roma,Ro','0883409584','SedeRoma1',2);
insert into Sede (Indirizzo,NumeroTelefono,Nome,IDAgenzia) values('Viale Porcelli,11,7700,Roma,Ro','0883409584','SedeRoma2',2);

insert into Amministratore (Nome,Cognome,Sesso,DataNascita,Indirizzo,CodiceFiscale,NumCell,NumFisso,Assunto,IDDitta) values ('Francesco'
,'Amminga','Maschio','1994-01-19','Viale germania 11,trani,bat,76125','GNNFNCA19XUL328U','393461631','0883509293','s',1);

insert into SupervisoreSede (Nome,Cognome,Sesso,DataNascita,Indirizzo,CodiceFiscale,NumCell,NumFisso,Assunto,IDSede) values ('Francesco'
,'Giannico','Maschio','1994-01-19','Viale germania 11,trani,bat,76125','GNNFNCA19XUL328R','393461631','0883509293','s',1);

insert into SupervisoreAgenzia (Nome,Cognome,Sesso,DataNascita,Indirizzo,CodiceFiscale,NumCell,NumFisso,Assunto,IDAgenzia) values ('Caludio'
,'DelMastri','Maschio','1994-01-19','Viale germania 11,trani,bat,76125','GNNFNCA19XUL328F','393461631','0883509293','s',1);

insert into Operatore (Nome,Cognome,Sesso,DataNascita,Indirizzo,CodiceFiscale,NumCell,NumFisso,Assunto,IDSede) values('Francesco',
'Pappolla','Maschio','1994-01-19','Viale germania 11,trani,bat,76125','GNNFNCA19XUL328X','393461631','0883509293','s',1);

insert into Credenziali(Username,password,IDSupervisoreAgenzia,IDSupervisoreSede,IDAmministratore,IDOperatore) values ('Op','EE774405AFA3E5CBB5279D8E77D3C840DEEE46372CEF95B511CFC8B259858031',null,null,null,1);
insert into Credenziali(Username,password,IDSupervisoreAgenzia,IDSupervisoreSede,IDAmministratore,IDOperatore) values ('Am','EE774405AFA3E5CBB5279D8E77D3C840DEEE46372CEF95B511CFC8B259858031',null,null,1,null);
insert into Credenziali(Username,password,IDSupervisoreAgenzia,IDSupervisoreSede,IDAmministratore,IDOperatore) values ('Ss','EE774405AFA3E5CBB5279D8E77D3C840DEEE46372CEF95B511CFC8B259858031',null,1,null,null);
insert into Credenziali(Username,password,IDSupervisoreAgenzia,IDSupervisoreSede,IDAmministratore,IDOperatore) values ('Sa','EE774405AFA3E5CBB5279D8E77D3C840DEEE46372CEF95B511CFC8B259858031',1,null,null,null);

insert into Fascia (Prezzo,Descrizione,Nome,Costo_kilometrico) 
values  
(280.5,'Sei Stanco di sentirti Piccolo? Affitta un suv','Suv',0.70),
(350,'La potenza e la bellezza in una sola auto!','lusso',0.50), 
(350,'Ti serve un auto comoda per il parcheggio? Affitta un utilitaria!','Utilitaria',0.27);

insert into Autoveicolo (Targa,Marca,Modello,AlimPrincipale,Colore,Cambio,Immatricolazione,Cilindrata,Potenza,NroPosti,
						NroTelaio,Disponibilita,UltimoKm,CapPortaBagagli,Note,DataScadAssic,OptionalAuto,Prezzo,DanniFutili,DanniGravi,IDSede,IDFascia)
                        values('AZ8281','Porsche','911 Carrera GTS','Diesel','Verdone',
						'Automatico','2015-01-16',2330,223,2,'l32200',
						'NonDisponibile',500,1,'','2016-08-16',
						'Finestrini elettrici ed oscuriti, cerchi in lega',150.0,'','',1,2);
						
insert into Autoveicolo (Targa,Marca,Modello,AlimPrincipale,Colore,Cambio,Immatricolazione,Cilindrata,Potenza,NroPosti,
						NroTelaio,Disponibilita,UltimoKm,CapPortaBagagli,Note,DataScadAssic,OptionalAuto,Prezzo,DanniFutili,DanniGravi,IDSede,IDFascia)
                        values('AF8400','Bmw','Serie 3','Diesel','Grigio','Automatico','2015-02-16',2360,222,5,'l32210',
						'Disponibile',700,0,'','2016-08-16','Finestrini elettrici ed oscuriti, cerchi in lega,sensori guida',250.0,'','',2,2);
						
insert into Autoveicolo (Targa,Marca,Modello,AlimPrincipale,Colore,Cambio,Immatricolazione,Cilindrata,Potenza,NroPosti,
						NroTelaio,Disponibilita,UltimoKm,CapPortaBagagli,Note,DataScadAssic,OptionalAuto,Prezzo,DanniFutili,DanniGravi,IDSede,IDFascia)
                        values('AZ8280','Land Rover','Range rover evoque','Diesel','Rosso','Automatico','2015-03-16',2300,213,2,'l321204',
						'NonDisponibile',200,4,'','2016-08-16','Finestrini elettrici ed oscuriti, cerchi in lega',190.0,'','',1,1);
						
insert into Autoveicolo (Targa,Marca,Modello,AlimPrincipale,Colore,Cambio,Immatricolazione,Cilindrata,Potenza,NroPosti,
						NroTelaio,Disponibilita,UltimoKm,CapPortaBagagli,Note,DataScadAssic,OptionalAuto,Prezzo,DanniFutili,DanniGravi,IDSede,IDFascia)
                        values('AF8410','Cadillac','Escalade','Diesel','Grigio','Automatico','2015-02-20',2360,222,5,'l322111',
						'Disponibile',1200,5,'','2016-08-30','Finestrini elettrici ed oscuriti, cerchi in lega,sensori guida',270.0,'','',2,1);		
						
insert into Autoveicolo (Targa,Marca,Modello,AlimPrincipale,Colore,Cambio,Immatricolazione,Cilindrata,Potenza,NroPosti,
						NroTelaio,Disponibilita,UltimoKm,CapPortaBagagli,Note,DataScadAssic,OptionalAuto,Prezzo,DanniFutili,DanniGravi,IDSede,IDFascia)
                        values('LM9999','Fiat','Panda','Benzina','Rosso','Manuale','2015-02-16',234,240,5,'fmlljj22',
						'Disponibile',1000,2,'','2016-08-18','Finestrini elettrici',70,'','',1,3);
						
insert into Autoveicolo (Targa,Marca,Modello,AlimPrincipale,Colore,Cambio,Immatricolazione,Cilindrata,Potenza,NroPosti,
						NroTelaio,Disponibilita,UltimoKm,CapPortaBagagli,Note,DataScadAssic,OptionalAuto,Prezzo,DanniFutili,DanniGravi,IDSede,IDFascia)
                        values('AF8402','Seat','Ibiza','Diesel','Bianco','Manuale','2015-04-16',236,211,5,'l322100',
						'Disponibile',3000,0,'','2016-08-30','Finestrini elettrici ed oscuriti',40,'','',2,3);												
											
insert into Optional(Prezzo,Descrizione,LimiteCopertura,Nome,NumSeggiolini,NumGuidatoriAggiuntivi) 
            values(10,'seggiolino per bambino', null,'seggiolino',1,null);				  
insert into Optional(Prezzo,Descrizione,LimiteCopertura,Nome,NumSeggiolini,NumGuidatoriAggiuntivi) 
            values(20,'seggiolino per bambino', null,'seggiolino',2,null);
insert into Optional(Prezzo,Descrizione,LimiteCopertura,Nome,NumSeggiolini,NumGuidatoriAggiuntivi)  
            values(30,'seggiolino per bambino', null,'seggiolino',3,null);
			
insert into Optional(Prezzo,Descrizione,LimiteCopertura,Nome,NumSeggiolini,NumGuidatoriAggiuntivi)  
            values (200,'assicurazione', 30000,'assicurazionekasko',null,null);
insert into Optional(Prezzo,Descrizione,LimiteCopertura,Nome,NumSeggiolini,NumGuidatoriAggiuntivi) 
            values (20,'gps satellitare', null,'Gps',null,null);
insert into Optional(Prezzo,Descrizione,LimiteCopertura,Nome,NumSeggiolini,NumGuidatoriAggiuntivi) 
            values (20,'Catene da neve', null,'cateneNeve',null,null);	
	
insert into Optional(Prezzo,Descrizione,LimiteCopertura,Nome,NumSeggiolini,NumGuidatoriAggiuntivi) 
            values (25,'guidatore diverso dal principale', null,'guidatoreAggiuntivo',null,1);					
insert into Optional(Prezzo,Descrizione,LimiteCopertura,Nome,NumSeggiolini,NumGuidatoriAggiuntivi)  
            values (35,'guidatore diverso dal principale', null,'guidatoreAggiuntivo',null,2);	
insert into Optional(Prezzo,Descrizione,LimiteCopertura,Nome,NumSeggiolini,NumGuidatoriAggiuntivi) 
            values (40,'guidatore diverso dal principale', null,'guidatoreAggiuntivo',null,3);	

insert into Optional(Prezzo,Descrizione,LimiteCopertura,Nome,NumSeggiolini,NumGuidatoriAggiuntivi) 
            values (0,'30% in piu del kmLimitato massimo', null,'chilometraggioIllimitato',null,null);	
			
insert into Cliente(IDCliente,Nome,Cognome,Sesso,DataEmissPatente,DataNascita,Indirizzo,CodFiscale,NumCell,NumTel,PatenteGuida,DataScadPatente,PartitaIVA,Email)
            values(1,'Claudio','Mastronardo','Maschio','2015-01-01','1994-03-20','Via delle tufare 11 , santeramo in colle ba','CLDMSTRA19L328GI','3934887675','','1232343454','2025-01-01','','ClaudioMastronardo@Gmail.com');
insert into Cliente(IDCliente,Nome,Cognome,Sesso,DataEmissPatente,DataNascita,Indirizzo,CodFiscale,NumCell,NumTel,PatenteGuida,DataScadPatente,PartitaIVA,Email)
            values(2,'Francesco','Giannico','Maschio','2013-03-07','1994-01-19','Viale germania ,11 trani ba','GNNFNCA19L328GLI','3934661631','','3344343545','2023-03-07','','FrancescoGiannico@Gmail.com');

insert into CartaDiCredito(IBAN,Numerocarta,Datascadenza,Circuito,IDCliente) values ('234543456545434565456554545','123456789019','2017-12-10','mastercard',1);		
insert into CartaDiCredito(IBAN,Numerocarta,Datascadenza,Circuito,IDCliente) values ('234543456545434565456553545','123456789049','2017-12-10','Visa',2);		
	
insert into Contratto(Stato,DataCreazione,Note,DataChiusura,IDCliente,IDOperatore,IDSupervisoreAgenzia,IDSupervisoreSede,IDAmministratore)
            values ('Aperto','2015-09-01','',null,1,1,null,null,null);
			
insert into Pagamento(Depcauzionale,IDIban,acconto) values (350,1,230);	
insert into Noleggio (InizioNoleggio,Rientro,Ritiro,Kmbase,Stato,IDContratto,IDAuto,IDPagamento,LuogoRestituzione,Numero_chilometri,NumeroSettimane,NumeroGiorni) 
					values ('2015-08-30','2015-09-5','2015-09-02',700,'aperto',1,1,1,2,500,0,4);

insert into NoleggioOptional(IDNoleggio,IDOptional) values(1,1);

insert into Contratto(Stato,DataCreazione,Note,DataChiusura,IDCliente,IDOperatore,IDSupervisoreAgenzia,IDSupervisoreSede,IDAmministratore)
            values ('Aperto','2015-08-28','',null,2,1,null,null,null);
insert into Pagamento(Depcauzionale,idiban,acconto) values (350,2,230);	

insert into Noleggio (InizioNoleggio,Rientro,Ritiro,Kmbase,Stato,IDContratto,IDAuto,IDPagamento,LuogoRestituzione,Numero_chilometri,NumeroSettimane,NumeroGiorni) 
					values ('2015-08-31','2015-09-7','2015-09-03',200,'aperto',2,3,2,1,1000,0,4);

insert into NoleggioOptional(IDNoleggio,IDOptional) values(2,2);
 