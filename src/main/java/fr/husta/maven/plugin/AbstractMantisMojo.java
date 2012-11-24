package fr.husta.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;

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
	 */
	@Parameter(property = "mantis.host.url", required = true)
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
