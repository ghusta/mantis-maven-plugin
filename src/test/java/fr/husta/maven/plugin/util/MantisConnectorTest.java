package fr.husta.maven.plugin.util;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectVersionData;

public class MantisConnectorTest
{

    private static MantisConnectPortType mantisConnectPortType;

    @BeforeClass
    public static void setUpGlobal() throws Exception
    {
        String endpointUrl = "http://intercom_1/mantis" + MantisUtils.SOAP_API_URL_SUFFIX;
        mantisConnectPortType = MantisUtils.createConnector(endpointUrl);
    }

    @Test
    @Ignore
    public void testGetMantisVersion() throws RemoteException
    {
        String mantisVersion = mantisConnectPortType.mc_version();
    }

    @Test
    @Ignore
    public void testAddProjectVersion() throws RemoteException
    {
        ProjectVersionData data = new ProjectVersionData();
        data.setId(BigInteger.ZERO);
        data.setProject_id(new BigInteger("13"));
        data.setName("99");
        data.setDescription("");
        data.setReleased(Boolean.TRUE);
        data.setObsolete(Boolean.FALSE);
        data.setDate_order(new GregorianCalendar());

        // First invocation
        mantisConnectPortType.mc_project_version_add("ghusta", "0guillaume0", data);

        // Second invocation => must generate Fault
        mantisConnectPortType.mc_project_version_add("ghusta", "0guillaume0", data);

    }

}
