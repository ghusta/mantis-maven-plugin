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

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.maven.plugin.MojoExecutionException;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;

import fr.husta.maven.plugin.util.MantisUtils;

/**
 * Adds a version to a project.
 *
 * @goal addProjectVersion
 * @requiresProject false
 * 
 */
public class AddProjectVersionMojo extends AbstractSecureMantisMojo
{

    public void execute() throws MojoExecutionException
    {
        throw new MojoExecutionException("Error", new UnsupportedOperationException("TODO"));
    }
}
