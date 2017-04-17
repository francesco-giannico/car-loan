package config;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Gestice i file di configurazione.
 * 
 * @author Gianluca
 * 
 */
public class GestioneProperties {

	private Properties properties;
	private InputStream in;
	private FileOutputStream out;
	private String path;

	/**
	 * Istanzia un oggetto GestioneProperies per il file specificato da path.
	 * 
	 * @param path
	 *            il percorso del file.
	 */
	public GestioneProperties(String path) {
		this.path = path;
		in = null;
		out = null;
		try {
			in = getClass().getClassLoader().getResourceAsStream(path);
			properties = new Properties();
			properties.load(in);
		} catch (IOException e) {
		e.printStackTrace();
		}
	}

	/**
	 * Restituisce il valore associato ad una chiave.
	 * 
	 * @param key
	 *            la chiave.
	 * @return il valore associato alla chiave o null se la chiave non è presente.
	 */
	public String getValue(String key) {
		String result = null;
		if (properties.containsKey(key)) {
			result = properties.getProperty(key);
		}
		return result;
	}

	/**
	 * Inserisce un NUOVO attributo (chiave, valore).
	 * Se la chiave è già presente, viene richiamato il metodo {@code setValue}.
	 * 
	 * @param key
	 *            la chiave da inserire.
	 * @param value
	 *            il valore da associare a key.
	 */
	public void addValue(String key, String value) {
		if (properties.containsKey(key)) {
			setValue(key, value);
		}
		properties.put(key, value);
		try {
			out = new FileOutputStream(path);
			properties.store(out, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Imposta l'attributo value per la chiave key.
	 * 
	 * @param key la chiave.
	 * @param value il valore da impostare.
	 */
	public void setValue(String key, String value) {
		properties.setProperty(key, value);
		try {
			out = new FileOutputStream(path);
			properties.store(out, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
