<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="includes/error_header.jsp" flush="true"/>
 

    <table width="950" border="0" >
	<tr>
		<td width="321"><img src="<%=request.getContextPath() %>/images/local/errorMan.jpg"></td>
		<td><b>An error has occurred in the application!</br></br></b><%=session.getAttribute("SYSTEM_ERROR") %><br /><br /></td>
	</tr>
	</table>
	
	 <br /><br />
   
	</body>
	</html>
	