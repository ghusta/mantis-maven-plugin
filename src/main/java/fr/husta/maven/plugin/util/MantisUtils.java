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
public class MantisUtils {

	private MantisUtils() {
	}

	/**
	 * Initialize a web service connection.
	 * 
	 * @param url
	 * @return
	 * @throws ServiceException
	 */
	public static MantisConnectPortType createConnector(String url)
			throws ServiceException {
		MantisConnectLocator locator = new MantisConnectLocator();
		locator.setMantisConnectPortEndpointAddress(url);

		return locator.getMantisConnectPort();
	}

}
