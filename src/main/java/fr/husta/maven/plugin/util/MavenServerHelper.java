package fr.husta.maven.plugin.util;

import java.util.List;

import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Server;
import org.apache.maven.settings.Settings;

/**
 * Use Maven settings.xml server section.
 * <br/>
 * <b>settings.xml</b> 
 * <pre>
 *&lt;servers&gt;
 *  &lt;server&gt;
 *    &lt;id&gt;github&lt;/id&gt;
 *    &lt;username&gt;GitHubLogin&lt;/username&gt;
 *    &lt;password&gt;GitHubPassw0rd&lt;password&gt;
 *  &lt;/server&gt;
 *&lt;/servers&gt;
 * </pre>
 *
 * <b>pom.xml</b>
 * <pre>
 *&lt;properties&gt;
 *  &lt;xxx.server&gt;github&lt;/xxx.server&gt;
 *&lt;/properties&gt;
 * </pre>
 */
public class MavenServerHelper {

	/**
	* Get server with given id
	*
	* @param settings Maven user settings
	* @param serverId must be non-null and non-empty
	* @return server or null if none matching
	*/
	public static Server getServer(final Settings settings,
			final String serverId) {
		if (settings == null) {
			return null;
		}
		List<Server> servers = settings.getServers();
		if (servers == null || servers.isEmpty()) {
			return null;
		}

		for (Server server : servers) {
			if (serverId.equals(server.getId())) {
				return server;
			}
		}
		return null;
	}

}
