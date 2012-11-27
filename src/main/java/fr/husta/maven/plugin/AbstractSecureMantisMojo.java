package fr.husta.maven.plugin;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * Abstract class for Mantis Mojos - Secured with login/password.
 * 
 * @author Guillaume
 */
public abstract class AbstractSecureMantisMojo extends AbstractMantisMojo
{

    /**
     * Mantis server login.
     */
    @Parameter(property = "mantis.login", required = true)
    protected String login;

    /**
     * Mantis server password.
     */
    @Parameter(property = "mantis.password", required = true)
    protected String password;

}
