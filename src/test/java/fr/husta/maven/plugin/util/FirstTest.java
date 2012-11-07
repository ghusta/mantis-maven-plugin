package fr.husta.maven.plugin.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;

public class FirstTest
{

    @Test
    @Ignore
    public void testCnx() throws ServiceException, RemoteException
    {
        String hostTest = "http://www.mantisbt.org/bugs";
        String urlTest = hostTest + "/api/soap/mantisconnect.php";

        // check open connection
        try
        {
            URL urlTestCnx = new URL(urlTest);
            HttpURLConnection urlConnection = (HttpURLConnection) urlTestCnx.openConnection();
            urlConnection.setConnectTimeout(100); // timeout ms
            urlConnection.setReadTimeout(100); // timeout ms
            urlConnection.connect();
            System.out.println(urlConnection);
        }
        catch (MalformedURLException e)
        {
            Assert.fail(e.getMessage());
        }
        catch (java.net.UnknownHostException e)
        {
            // the test can't continue => fails silently (ex : java.net.UnknownHostException: www.mantisbt.org)
            // e.printStackTrace();
            System.err.println(e.toString());
            System.err.println("Maybe the proxy / firewall should be configured...");
            return;
        }
        catch (IOException e)
        {
            Assert.fail(e.getMessage());
        }

        MantisConnectPortType portType = MantisUtils.createConnector(urlTest);
        String version = portType.mc_version();
        Assert.assertNotNull(version);
        System.out.println("Mantis Demo Version : " + version);
    }

}
