<%@ page isErrorPage="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="org.slf4j.Logger"%>

<%!
	Logger log = LoggerFactory.getLogger("mov.error");
	String serverName = "[metaweblog-demo]";
%>
<%	
	String requestURL = "";

	if (log.isInfoEnabled()) {
		if (request.getAttribute("javax.servlet.error.message") != null) {
			requestURL = (String)request.getAttribute("javax.servlet.error.message");
			log.info("{} request url '{}' not found", serverName, requestURL);
		}
	}
	
	//response.sendRedirect(ServerName.getFullURL(ServerName.EOC, "/"));
%>
<html>
<body>
<h2>url '<%=requestURL %>' not found</h2>
</body>
</html>