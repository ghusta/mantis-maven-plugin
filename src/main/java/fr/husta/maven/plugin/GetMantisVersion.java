package fr.husta.maven.plugin;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import fr.husta.maven.plugin.util.MantisUtils;

/**
 * Displays version of Mantis server.
 * 
 * @goal display-version
 * @requiresProject false
 *
 */
public class GetMantisVersion extends AbstractMantisMojo
{

    public void execute() throws MojoExecutionException, MojoFailureException
    {
        try
        {
            MantisConnectPortType portType = MantisUtils.createConnector(getMantisSoapApiUrl());

            String version = portType.mc_version();
            getLog().info("Mantis Version : " + version);

        }
        catch (ServiceException e)
        {
            // getLog().error(e.getMessage());
            throw new MojoExecutionException(e.getMessage(), e);
        }
        catch (RemoteException e)
        {
            // getLog().error(e.getMessage());
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

}
