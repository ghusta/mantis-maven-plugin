package fr.husta.maven.plugin.util;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import junit.framework.Assert;

import org.junit.Test;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;

public class FirstTest {

	@Test
	public void testCnx() throws ServiceException, RemoteException {
		String hostTest = "http://www.mantisbt.org/bugs";
		String urlTest = hostTest + "/api/soap/mantisconnect.php";

		MantisConnectPortType portType = MantisUtils.createConnector(urlTest);
		String version = portType.mc_version();
		Assert.assertNotNull(version);
		System.out.println("Mantis Demo Version : " + version);
	}

}
