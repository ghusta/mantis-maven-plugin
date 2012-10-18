package fr.husta.maven.plugin.util;

import java.math.BigInteger;
import java.rmi.RemoteException;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectVersionData;

/**
 * Delegate to MantisConnectPortType.
 * 
 * @author Guillaume
 *
 */
public class MantisConnector {

	private MantisConnectPortType mantisConnectPortType;

	public MantisConnector(MantisConnectPortType portType) {
		this.mantisConnectPortType = portType;
	}

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public String getMantisVersion() throws RemoteException {
		String version = this.mantisConnectPortType.mc_version();

		return version;
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param projectName
	 * @return ProjectId
	 * @throws RemoteException
	 */
	public BigInteger getProjectIdByName(String username, String password,
			String projectName) throws RemoteException {
		BigInteger id = this.mantisConnectPortType.mc_project_get_id_from_name(
				username, password, projectName);

		return id;
	}

	/**
	 * Simple version of version creation.
	 * Fault if project doesn't exist or version already exists.
	 * 
	 * @param username
	 * @param password
	 * @param projectId
	 * @param versionName
	 * @throws RemoteException
	 */
	public void addProjectVersion(String username, String password,
			BigInteger projectId, String versionName) throws RemoteException {
		ProjectVersionData versionData = new ProjectVersionData();
		versionData.setProject_id(projectId);
		versionData.setName(versionName);
		this.mantisConnectPortType.mc_project_version_add(username, password,
				versionData);
	}

}
