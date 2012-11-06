package fr.husta.maven.plugin.util;

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

}
