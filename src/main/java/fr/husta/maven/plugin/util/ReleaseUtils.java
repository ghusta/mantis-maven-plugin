package fr.husta.maven.plugin.util;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * 
 * @author Guillaume
 * @see org.apache.maven.plugin.changes.ReleaseUtils
 */
public class ReleaseUtils {

	private static final String SNAPSHOT_SUFFIX = "-SNAPSHOT";

	/**
	 * Get the latest release by matching the supplied releases
	 * with the version from the pom.
	 *
	 * @param pomVersion Version of the artifact
	 * @return A <code>Release Version</code> that matches the next release of the current project
	 * @throws org.apache.maven.plugin.MojoExecutionException If a release can't be found
	 */
	public static String getReleaseVersion(String pomVersion)
			throws MojoExecutionException {
		String releaseVersion = null;
		// Remove "-SNAPSHOT" from the end, if it's there
		if (pomVersion != null && pomVersion.endsWith(SNAPSHOT_SUFFIX)) {
			releaseVersion = pomVersion.substring(0, pomVersion.length()
					- SNAPSHOT_SUFFIX.length());
		}

		if (pomVersion != null && pomVersion.endsWith(SNAPSHOT_SUFFIX) == false) {
			releaseVersion = pomVersion;
		}

		if (releaseVersion == null) {
			throw new MojoExecutionException("Couldn't find the release '"
					+ pomVersion + "' among the supplied version.");
		}

		return releaseVersion;
	}

}
