package fr.husta.maven.plugin.util;

import javax.xml.rpc.ServiceException;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;

/**
 * Utility class.
 * 
 * @author Guillaume
 *
 */
public class MantisUtils
{

    public static final String SOAP_API_URL_SUFFIX = "/api/soap/mantisconnect.php";

    private MantisUtils()
    {
    }

    /**
     * Initialize a web service connection.
     * 
     * @param url Endpoint URL
     * @return
     * @throws ServiceException
     */
    public static MantisConnectPortType createConnector(String url) throws ServiceException
    {
        MantisConnectLocator locator = new MantisConnectLocator();
        locator.setMantisConnectPortEndpointAddress(url);

        return locator.getMantisConnectPort();
    }

}
