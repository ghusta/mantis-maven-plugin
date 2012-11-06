package fr.husta.maven.plugin;

/**
 * Abstract class for Mantis Mojos - Secured with login/password.
 * 
 * @author Guillaume
 */
public abstract class AbstractSecureMantisMojo extends AbstractMantisMojo
{

    /**
     * Mantis server login.
     * 
     * @parameter expression="${mantis.login}"
     * @required
     */
    protected String login;

    /**
     * Mantis server password.
     * 
     * @parameter expression="${mantis.password}"
     * @required
     */
    protected String password;

}
