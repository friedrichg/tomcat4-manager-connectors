tomcat4-manager-connectors
==========================

Shows processors stats in connectors in tomcat 4.0.6 manager using java 1.3

To use the class:

1. Replace the web.xml in your managers folder (manager/WEB-INF/web.xml)
2. Copy the class inside server class folder.
3. Restart Tomcat
4. Access via http://localhost:8080/manager/connectors

PD: Only Tested with java 1.3 and tomcat 4.0.6

In newer versions, perhaps you should use JMX instead.

