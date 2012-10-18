mantis-maven-plugin
===================

General information
-------------------

A Maven plugin for Mantis integration.  
Based on the [Mantis SOAP API](http://www.mantisbt.org/wiki/doku.php/mantisbt:faq#does_mantisbt_provide_a_webservice_interface) (Web Service).  
Live test of the web service on the MantisBT site : [Test it](http://www.mantisbt.org/bugs/api/soap/mantisconnect.php) ([WSDL](http://www.mantisbt.org/bugs/api/soap/mantisconnect.php?wsdl)).

Goals
-----
1. **mantis:addProjectVersion** - Create a project's version - Method _mc_project_version_add_
2. **mantis:getVersion** - Displays version of Mantis server - Method _mc_version_


Help
----

1. Maven Plugin Development :
    * Maven official site : [Maven - Guide to Developing Java Plugins](http://maven.apache.org/guides/plugin/guide-java-plugin-development.html)
    * Maven Plugin API : [The Descriptor and Annotations](http://maven.apache.org/developers/mojo-api-specification.html#The_Descriptor_and_Annotations)
    * Maven Plugin Plugin : [Configuring Generation of Help Mojo](http://maven.apache.org/plugin-tools/maven-plugin-plugin/examples/generate-help.html)
    * Maven Plugin Prefix : [Introduction to Plugin Prefix Resolution](http://maven.apache.org/guides/introduction/introduction-to-plugin-prefix-mapping.html)
    * Book "_Better Builds with Maven_" : [Developing Custom Maven Plugins](http://www.maestrodev.com/better-builds-with-maven/developing-custom-maven-plugins/)
    * Book "_Maven: The Complete Reference_" : [Writing Plugins](http://www.sonatype.com/books/mvnref-book/reference/writing-plugins.html)

2. Maven & GitHub :
    * Maven + GitHub : [Maven Tips and Tricks: Using GitHub](http://www.sonatype.com/people/2009/09/maven-tips-and-tricks-using-github/)

3. Eclipse & GitHub :
    * Eclipse EGit + GitHub : [EGit/GitHub/User Guide](http://wiki.eclipse.org/EGit/GitHub/UserGuide)