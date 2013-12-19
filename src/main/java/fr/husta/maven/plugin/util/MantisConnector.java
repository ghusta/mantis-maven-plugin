package fr.husta.maven.plugin.util;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.Calendar;

import org.apache.maven.plugin.logging.Log;

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
		mantisConnectPortType = portType;
	}

	/**
	 *
	 * @return
	 * @throws RemoteException
	 */
	public String getMantisVersion() throws RemoteException {
		String version = mantisConnectPortType.mc_version();

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
	public BigInteger getProjectIdByName(String username, String password, String projectName) throws RemoteException {
		BigInteger id = mantisConnectPortType.mc_project_get_id_from_name(username, password, projectName);

		// warning returns 0 if not exist !
		if (BigInteger.ZERO.compareTo(id) == 0) {
			throw new RuntimeException("Project '" + projectName + "' unknown");
		}

		return id;
	}

	/**
	 * Simple version of version creation. Fault if project doesn't exist or
	 * version already exists.
	 *
	 * @param aLogin
	 * @param aPassword
	 * @param aProjectId
	 * @param aVersionName
	 * @throws RemoteException
	 */
	public void addProjectVersion(String aLogin, String aPassword, BigInteger aProjectId, String aVersionName,
			boolean aReleasedFlag) throws RemoteException {
		ProjectVersionData[] tempProjectVersions = getProjectVersions(aLogin, aPassword, aProjectId);
		ProjectVersionData tempCurrentVersion = null;
		for (ProjectVersionData tempProjectVersionData : tempProjectVersions) {
			if (tempProjectVersionData.getName().equals(aVersionName)) {
				tempCurrentVersion = tempProjectVersionData;
				break;
			}
		}

		boolean tempAddVersion;
		if (tempCurrentVersion != null) {
			// Version already there. Just update
			tempAddVersion = false;
		} else {
			tempAddVersion = true;
			tempCurrentVersion = new ProjectVersionData();
			tempCurrentVersion.setProject_id(aProjectId);
		}
		tempCurrentVersion.setName(aVersionName);
		tempCurrentVersion.setReleased(aReleasedFlag);
		tempCurrentVersion.setObsolete(Boolean.FALSE);

		// bug Mantis ?
		if (tempCurrentVersion.getDescription() == null) {
			tempCurrentVersion.setDescription("");
		}

		if (tempAddVersion) {
			mantisConnectPortType.mc_project_version_add(aLogin, aPassword, tempCurrentVersion);
		} else {
			mantisConnectPortType.mc_project_version_update(aLogin, aPassword, tempCurrentVersion.getId(),
					tempCurrentVersion);
		}
	}

	/**
	 * Return a list of ProjectVersionData for the project.
	 *
	 * @param username
	 * @param password
	 * @param projectId
	 * @return
	 * @throws RemoteException
	 */
	public ProjectVersionData[] getProjectVersions(String username, String password, BigInteger projectId)
			throws RemoteException {
		ProjectVersionData[] projectVersionDatas = mantisConnectPortType.mc_project_get_versions(username, password,
				projectId);

		return projectVersionDatas;
	}

	/**
	 * renameVersion from aCurrentSnapshot to aReleaseVersion and mark it "released".
	 * @param aLog
	 *
	 * @param aLogin
	 * @param aPassword
	 * @param aProjectId
	 * @param aCurrentSnapshot
	 * @param aReleaseVersion void
	 * @throws RemoteException
	 */
	public void renameVersion(Log aLog, String aLogin, String aPassword, BigInteger aProjectId,
			String aCurrentSnapshot, String aReleaseVersion) throws RemoteException {
		ProjectVersionData[] tempProjectVersions = getProjectVersions(aLogin, aPassword, aProjectId);
		ProjectVersionData tempCurrentVersion = null;
		for (ProjectVersionData tempProjectVersionData : tempProjectVersions) {
			if (tempProjectVersionData.getName().equals(aCurrentSnapshot)) {
				tempCurrentVersion = tempProjectVersionData;
				break;
			}
		}
		if (tempCurrentVersion == null) {
			throw new IllegalArgumentException("Did not find Version " + aCurrentSnapshot + " in ProjectId="
					+ aProjectId);
		}
		BigInteger tempVersionId = tempCurrentVersion.getId();
		aLog.info("Found " + tempCurrentVersion.getName() + " ID=" + tempVersionId);
		tempCurrentVersion.setName(aReleaseVersion);
		tempCurrentVersion.setReleased(true);
		Calendar tempToday = Calendar.getInstance();
		// Go back one minute to have the later added version after this one.
		tempToday.set(Calendar.MILLISECOND, 0);
		tempToday.set(Calendar.SECOND, 0);
		tempToday.add(Calendar.MINUTE, -1);
		tempCurrentVersion.setDate_order(tempToday);
		mantisConnectPortType.mc_project_version_update(aLogin, aPassword, tempVersionId, tempCurrentVersion);
	}

}
