package fr.husta.maven.plugin;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.Arrays;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectVersionData;
import fr.husta.maven.plugin.util.MantisConnector;
import fr.husta.maven.plugin.util.MantisUtils;
import fr.husta.maven.plugin.util.ProjectVersionDataComparator;

/**
 * Displays the versions of a Mantis project.
 * 
 * @goal display-project-versions
 * @requiresProject true
 * 
 */
public class DisplayProjectVersionsMojo extends AbstractSecureMantisMojo
{

    /**
     * @parameter default-value="${project}"
     * @required
     * @readonly
     */
    protected MavenProject project;

    /**
     * @parameter expression="${mantis.projectName}"
     *            default-value="${project.artifactId}"
     * @required
     */
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
            // sort versions
            Arrays.sort(projectVersions, new ProjectVersionDataComparator());

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
        String nbVersions = "";
        if (projectVersions != null && projectVersions.length > 0)
        {
            nbVersions = "(" + projectVersions.length + ")";
        }

        getLog().info("Versions " + nbVersions + " : ");
        final int totalWidth = 54;
        // header
        getLog().info(StringUtils.repeat("*", totalWidth));
        getLog().info(
                "*" + StringUtils.center("NAME", 30) + "*" + StringUtils.center("RELEASED", 10) + "*"
                        + StringUtils.center("OBSOLETE", 10) + "*");
        getLog().info(StringUtils.repeat("*", totalWidth));

        // list of versions
        for (int i = 0; i < projectVersions.length; i++)
        {
            displayVersion(projectVersions[i]);
        }

        // footer
        getLog().info(StringUtils.repeat("*", totalWidth));
    }

    private void displayVersion(ProjectVersionData projectVersionData)
    {
        getLog().info(
                "*"
                        + StringUtils.rightPad(" " + StringUtils.abbreviate(projectVersionData.getName(), 28)
                                + " ", 30) + "*"
                        + StringUtils.center(projectVersionData.getReleased() ? "X" : " ", 10) + "*"
                        + StringUtils.center(projectVersionData.getObsolete() ? "X" : " ", 10) + "*");
    }

}
