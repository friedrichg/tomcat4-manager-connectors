set PATH=c:\jdk1.3.1_28\bin
set JAVA_HOME=C:\jdk1.3.1_28
cd C:\jakarta-tomcat-4.0.6\server\classes
set CP=..\..\common\lib\servlet.jar;..\lib\catalina.jar;..\lib\tomcat-ajp.jar;..\lib\tomcat-coyote.jar
del org\apache\catalina\servlets\*.class
javac -classpath %CP% org\apache\catalina\servlets\*.java > log.txt 2>&1 && echo Cool >> log.txt
	
