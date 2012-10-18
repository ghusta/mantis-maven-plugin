package fr.husta.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;

/**
 * Abstract class for Mantis Mojos.
 * 
 * @author Guillaume
 */
public abstract class AbstractMantisMojo extends AbstractMojo
{

    protected static final String SOAP_API_URL_SUFFIX = "/api/soap/mantisconnect.php";

    /**
     * Complete Mantis server web URI including protocol and port number. 
     * Example: http://localhost:8008/
     * 
     * @parameter expression="${mantisHost}" default-value="http://127.0.0.1:80"
     * @required
     */
    protected String mantisHost;

    /**
     * 
     * @return
     */
    protected String getMantisSoapApiUrl()
    {
        String res = null;
        if (mantisHost != null)
        {
            res = mantisHost + SOAP_API_URL_SUFFIX;
        }

        return res;
    }

}
