package org.apache.catalina.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import org.apache.catalina.Server;
import org.apache.catalina.ServerFactory;
import org.apache.catalina.Service;
import org.apache.catalina.Connector;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.ajp.tomcat4.Ajp13Connector;
import org.apache.coyote.tomcat4.CoyoteConnector;

public class ManagerConnectors
    extends HttpServlet {
	
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		if (session != null) session.invalidate();
		response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();	
		writer.println("OK - Connectors for current server");
		Server server =	ServerFactory.getServer();
		if (server == null) return;
		writer.println("Server="+server);
		
		Service services[] = server.findServices();
		if (services == null) return;
		writer.println("NumberOfServices="+services.length);
		for (int i=0;i<services.length;i++){
			if (services[i] == null) continue;
			writer.println("Service"+i+"="+services[i]);
			Connector connectors[]=services[i].findConnectors();
			if (connectors == null) continue;
			writer.println("NumberOfConnectors"+i+"="+connectors.length);
			for (int j=0;j<connectors.length;j++){
					if (connectors[j] == null) continue;
					writer.println("Connector"+i+"_"+j+"="+connectors[j]);
					if (connectors[j] instanceof HttpConnector) {
							HttpConnector h = (HttpConnector) connectors[j];
							writer.println("ConnectorMaxProcessors"+i+"_"+j+"="+h.getMaxProcessors());
							writer.println("ConnectorMinProcessors"+i+"_"+j+"="+h.getMinProcessors());
							writer.println("ConnectorCurProcessors"+i+"_"+j+"="+h.getCurProcessors());
					} else if (connectors[j] instanceof Ajp13Connector){
							Ajp13Connector a = (Ajp13Connector) connectors[j];
							writer.println("ConnectorMaxProcessors"+i+"_"+j+"="+a.getMaxProcessors());
							writer.println("ConnectorMinProcessors"+i+"_"+j+"="+a.getMinProcessors());
							writer.println("ConnectorCurProcessors"+i+"_"+j+"="+a.getCurProcessors());
					} else if (connectors[j] instanceof CoyoteConnector){
							CoyoteConnector c = (CoyoteConnector) connectors[j];
							writer.println("ConnectorMaxProcessors"+i+"_"+j+"="+c.getMaxProcessors());
							writer.println("ConnectorMinProcessors"+i+"_"+j+"="+c.getMinProcessors());
							writer.println("ConnectorCurProcessors"+i+"_"+j+"="+c.getCurProcessors());
					} else if (connectors[j] instanceof org.apache.catalina.connector.http10.HttpConnector){
							org.apache.catalina.connector.http10.HttpConnector h10 = (org.apache.catalina.connector.http10.HttpConnector) connectors[j];
							writer.println("ConnectorMaxProcessors"+i+"_"+j+"="+h10.getMaxProcessors());
							writer.println("ConnectorMinProcessors"+i+"_"+j+"="+h10.getMinProcessors());
							writer.println("ConnectorCurProcessors"+i+"_"+j+"="+h10.getCurProcessors());
					} else {
							writer.println("ConnectorMaxProcessors"+i+"_"+j+"=NA");
							writer.println("ConnectorMinProcessors"+i+"_"+j+"=NA");
							writer.println("ConnectorCurProcessors"+i+"_"+j+"=NA");
					}
			}
		}
		
	}
}