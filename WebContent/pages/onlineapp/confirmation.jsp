<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="student_header.jsp" flush="true"/>


<html:form action="/OnlineApp">

<table width="675" cellpadding="0" cellspacing="0" border="0">
<tr>
<td>
<b>Thank You!</b>&nbsp;Your application has been successfully submitted.
<br/><br/>
<a href="http://faithfarm.org">Click here</a> to return to the website.

<br/><br/>

</td>
</tr>
</table>
	
<html:hidden property="pageSource" value="process"/>  
</html:form>

<jsp:include page="footer.jsp" flush="true"/>
    