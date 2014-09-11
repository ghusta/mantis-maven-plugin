package fr.husta.maven.plugin;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.math.BigInteger;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.maven.model.IssueManagement;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import fr.husta.maven.plugin.util.MantisConnector;
import fr.husta.maven.plugin.util.MantisUtils;

/**
 * Adds a version to a project.
 */
@Mojo(name = "release-project-version", requiresProject = true)
public class ReleaseProjectVersionMojo extends AbstractSecureMantisMojo {

	@Component
	protected MavenProject project;

	@Parameter(property = "mantis.projectName", defaultValue = "${project.artifactId}", required = true)
	protected String projectName;

	@Parameter(property = "mantis.currentSnapshot", defaultValue = "${project.version}", required = true)
	protected String currentSnapshot;

	@Parameter(property = "mantis.releaseVersion", required = true)
	protected String releaseVersion;

	@Parameter(property = "mantis.developmentVersion", required = true)
	protected String developmentVersion;

	@Parameter(property = "mantis.versionPrefix", required = false)
	protected String versionPrefix;

	protected IssueManagement issueManagement;

	public void execute() throws MojoExecutionException {
		issueManagement = project.getIssueManagement();
		Log tempLog = getLog();
		if (issueManagement != null) {
			tempLog.debug("IssueManagement -> system = " + issueManagement.getSystem());
			tempLog.debug("IssueManagement -> url = " + issueManagement.getUrl());

			final String ISSUE_MNGT_MANTIS = "Mantis";
			if (issueManagement.getSystem() != null && ISSUE_MNGT_MANTIS.equals(issueManagement.getSystem()) == false) {
				tempLog.warn("IssueManagement -> system should be set to '" + ISSUE_MNGT_MANTIS + "'.");
			}
		}

		try {
			// connection to Mantis SOAP API
			MantisConnectPortType portType = MantisUtils.createConnector(getMantisSoapApiUrl());
			MantisConnector mantisConnector = new MantisConnector(portType);

			tempLog.debug("projectName = '" + projectName + "'");

			// find ProjectId from Name
			BigInteger projectId = mantisConnector.getProjectIdByName(login, password, projectName);

			tempLog.info("Project " + projectName + " has ID=" + projectId);

			String tempCurrentSnapshot = currentSnapshot;
			if (versionPrefix != null) {
				tempCurrentSnapshot = versionPrefix + "-" + tempCurrentSnapshot;
			}
			String tempReleaseVersion = releaseVersion;
			if (versionPrefix != null) {
				tempReleaseVersion = versionPrefix + "-" + tempReleaseVersion;
			}
			tempLog.info("Rename Version '" + tempCurrentSnapshot + "' to " + tempReleaseVersion);
			mantisConnector.renameVersion(tempLog, login, password, projectId, tempCurrentSnapshot, tempReleaseVersion);
			tempLog.info("Renamed Version '" + tempCurrentSnapshot + "' to " + tempReleaseVersion);
			// call to web service method
			String tempDevelopmentVersion = developmentVersion;
			if (versionPrefix != null) {
				tempDevelopmentVersion = versionPrefix + "-" + tempDevelopmentVersion;
			}
			tempLog.info("Create Version '" + tempDevelopmentVersion + "' in Mantis, releaseFlag=" + false);
			mantisConnector.addProjectVersion(login, password, projectId, tempDevelopmentVersion, false);
			tempLog.info("Version '" + tempDevelopmentVersion + "' created in Mantis, releaseFlag=" + false);

		} catch (ServiceException e) {
			tempLog.error(e.getMessage());
			throw new MojoExecutionException(e.getMessage(), e);
		} catch (RuntimeException e) {
			tempLog.error(e.getMessage());
			throw new MojoExecutionException(e.getMessage(), e);
		} catch (RemoteException e) {
			tempLog.error(e.getMessage());
			throw new MojoExecutionException(e.getMessage(), e);
		}

	}
}
