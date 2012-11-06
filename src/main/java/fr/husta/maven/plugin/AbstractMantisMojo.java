package fr.husta.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;

import fr.husta.maven.plugin.util.MantisUtils;

/**
 * Abstract class for Mantis Mojos.
 * 
 * @author Guillaume
 */
public abstract class AbstractMantisMojo extends AbstractMojo {

	/**
	 * Complete Mantis server web URI including protocol and port number. 
	 * Example: http://localhost:80/mantis/
	 * 
	 * @parameter expression="${mantis.host.url}" default-value="http://127.0.0.1:80/mantis/"
	 * @required
	 */
	protected String mantisHostUrl;

	/**
	 * Get the full URL for the SOAP API.
	 * 
	 * @return
	 */
	protected String getMantisSoapApiUrl() {
		String res = null;
		if (mantisHostUrl != null) {
			res = mantisHostUrl + MantisUtils.SOAP_API_URL_SUFFIX;
		}

		return res;
	}

}
