package fr.husta.maven.plugin;

/**
 * Abstract class for Mantis Mojos - Secured with login/password.
 * 
 * @author Guillaume
 */
public abstract class AbstractSecureMantisMojo extends AbstractMantisMojo
{

    /**
     * @parameter expression="${login}"
     * @required
     */
    protected String login;

    /**
     * @parameter expression="${password}"
     * @required
     */
    protected String password;

}
