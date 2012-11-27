package fr.husta.maven.plugin;

import java.math.BigInteger;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectVersionData;
import fr.husta.maven.plugin.util.MantisConnector;
import fr.husta.maven.plugin.util.MantisUtils;

/**
 * Displays the versions of a Mantis project.
 */
@Mojo(name = "display-project-versions", requiresProject = true)
public class DisplayProjectVersionsMojo extends AbstractSecureMantisMojo
{

    @Component
    protected MavenProject project;

    @Parameter(property = "mantis.projectName", defaultValue = "${project.artifactId}", required = true)
    protected String projectName;

    public void execute() throws MojoExecutionException, MojoFailureException
    {

        try
        {
            // connection to Mantis SOAP API
            MantisConnectPortType portType = MantisUtils.createConnector(getMantisSoapApiUrl());
            MantisConnector mantisConnector = new MantisConnector(portType);

            getLog().debug("projectName = '" + projectName + "'");

            // find ProjectId from Name
            BigInteger projectId = mantisConnector.getProjectIdByName(login, password, projectName);
            // call to web service method
            ProjectVersionData[] projectVersions = mantisConnector.getProjectVersions(login, password,
                    projectId);
            // TODO: sort versions

            // display to log
            getLog().info("Project : " + projectName + " (id = " + projectId + ")");
            displayVersionList(projectVersions);

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

    /**
     * Display versions to log.
     * 
     * @param projectVersions
     */
    private void displayVersionList(ProjectVersionData[] projectVersions)
    {
        getLog().info("Versions : ");
        // header
        getLog().info(StringUtils.repeat("*", 60));
        getLog().info(
                "*" + StringUtils.center("NAME", 30) + "*" + StringUtils.center("RELEASED", 20) + "*"
                        + StringUtils.center("OBSOLETE", 20) + "*");
        for (int i = 0; i < projectVersions.length; i++)
        {
            displayVersion(projectVersions[i]);
        }
        // footer
        getLog().info(StringUtils.repeat("*", 60));
    }

    private void displayVersion(ProjectVersionData projectVersionData)
    {
        getLog().info(
                "*" + StringUtils.left(projectVersionData.getName(), 30) + "*"
                        + StringUtils.center(projectVersionData.getReleased() ? "X" : " ", 20) + "*"
                        + StringUtils.center(projectVersionData.getObsolete() ? "X" : " ", 20) + "*");
    }

}
