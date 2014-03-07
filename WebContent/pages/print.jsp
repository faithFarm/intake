<%@ page import="org.faithfarm.sms.struts.form.OnlineAppForm" %>
<%@ page import="org.faithfarm.sms.domain.Intake" %>
<%@ page import="java.beans.BeanInfo" %>
<%@ page import="java.beans.Introspector" %>
<%@ page import="java.beans.PropertyDescriptor" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Faith Farm Ministrie</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/dispatch.css"  />
</head>

<script language="javascript">

	
	
	function printPage()
	{
	window.print();
	}
</script>
<%
			Intake intake =  (Intake)session.getAttribute("PRINT_INTAKE");
			String name = (String)session.getAttribute("PRINT_INTAKE_NAME");
			String farm =  (String)session.getAttribute("PRINT_INTAKE_FARM");
			String phone =  (String)session.getAttribute("PRINT_INTAKE_PHONE");
%>

<body onLoad="javascript:printPage();" topmargin="0" >


<font size="6"><%=farm %> Online Intake Application</font></br></br>
<font size="5"><b>Name:</b><i><%=name %>&nbsp;&nbsp;(<%=phone %>)</i></font></br>

<font size="2">
<%
			try {
			BeanInfo info = Introspector.getBeanInfo(intake.getClass());
			PropertyDescriptor[] props = info.getPropertyDescriptors();  
			for (int i=0;i<props.length;i++) {  
				PropertyDescriptor descriptor = props [i]; %>
				<% if ((descriptor.getReadMethod().invoke(intake, null)!=null)&&(descriptor.getReadMethod().invoke(intake, null).toString().length()>0)) { %>
				<%=props[i].getDisplayName()%>:&nbsp;&nbsp;<b><%=descriptor.getReadMethod().invoke(intake, null)%></b></br>
				<% } //end null %>
			<%	}			    
			}
			catch (Exception ex) { ex.getStackTrace(); }
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
%>
</font>
</body>

   	       