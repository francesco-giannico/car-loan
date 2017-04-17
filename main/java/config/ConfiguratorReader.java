package config;

/**
 * Legge valori dal file di configurazione.
 * 
 * @author Gianluca
 * 
 */
public interface ConfiguratorReader {

	/**
	 * Fornisce il valore associato alla chiave nel file di configurazione.
	 * 
	 * @param key
	 *            la chiave da cui recuperare il valore.
	 * @return il valore associato alla chiave.
	 */
	public String getField(String key);
}
