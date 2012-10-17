mantis-maven-plugin
===================

General information
-------------------

A Maven plugin for Mantis integration.  
Based on the [Mantis SOAP API](http://www.mantisbt.org/wiki/doku.php/mantisbt:faq#does_mantisbt_provide_a_webservice_interface) (Web Service).  
Live test of the web service on the MantisBT site : [Test it](http://www.mantisbt.org/bugs/api/soap/mantisconnect.php) ([WSDL](http://www.mantisbt.org/bugs/api/soap/mantisconnect.php?wsdl)).

Goals
-----
1. **pluginPrefix:addProjectVersion** - Create a project's version - Method _mc_project_version_add_
2. **pluginPrefix:getVersion** - Displays version of Mantis server.


Help
----

* Documentation :
    * Maven official site : [Maven - Guide to Developing Java Plugins](http://maven.apache.org/guides/plugin/guide-java-plugin-development.html)
    * Maven Plugin API : [The Descriptor and Annotations](http://maven.apache.org/developers/mojo-api-specification.html#The_Descriptor_and_Annotations)
    * Maven Plugin Plugin : [Configuring Generation of Help Mojo](http://maven.apache.org/plugin-tools/maven-plugin-plugin/examples/generate-help.html)
    * Book "_Better builds with Maven_" : [Developing Custom Maven Plugins](http://www.maestrodev.com/better-builds-with-maven/developing-custom-maven-plugins/)

