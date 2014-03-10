<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="../../includes/header.jsp" flush="true"/>

<html:form  action="/Report">

<script language="javascript">

function changeFarm(e)
{
	document.getElementById('action').value='FastFind';
	document.forms[0].submit();
}

</script>

<div align="center">
<table width="1100" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td align="left"><h1><bean:write name="reportForm" property="reportTitle"/>
		for <bean:write name="reportForm" property="farmBase"/></td>
		<td valign="bottom" align="right">
			<html:select property="farmBase" styleClass="select" onchange="javascript:changeFarm(this);" >
				<html:optionsCollection name="ddl_farm" value="name" label="name" />
			</html:select>
		</td>
	</tr>
</table>

<!-- 

CLASS 0

 -->
<table width="1100" class="report" cellpadding="0" cellspacing="0">
	<tr>
		<td bgcolor="#8ea3b9" width="50"><font size="1" color="#FFFFFF">Id</font></td>
		<td bgcolor="#8ea3b9" width="100"><font size="1" color="#FFFFFF">FirstName</font></td>
		<td bgcolor="#8ea3b9" width="100"><font size="1" color="#FFFFFF">LastName</font></td>
		<td bgcolor="#8ea3b9" width="100"><font size="1" color="#FFFFFF">Entry Date</font></td>
		<td bgcolor="#8ea3b9" width="100"><font size="1" color="#FFFFFF">DOB</font></td>
		<td bgcolor="#8ea3b9" width="120"><font size="1" color="#FFFFFF">Supervisor</font></td>
		<td bgcolor="#8ea3b9" width="150"><font size="1" color="#FFFFFF">Job</font></td>
		<td bgcolor="#8ea3b9" width="200"><font size="1" color="#FFFFFF">CWT</font></td>
		<td bgcolor="#8ea3b9" width="100"><font size="1" color="#FFFFFF">Class</font></td>
	</tr>
	<% int count=1; String color=""; %>
	<logic:iterate id="loop0" name="reportForm" property="fastFindList" indexId="i">
	<% if (count%2==0) color="#dae2e8"; else color="#ffffff"; %>
	<tr>	
		<td bgcolor="<%=color%>"><a style="text-decoration: none;" href="<%=request.getContextPath()%>/Intake.do?action=Edit&src=FastFind&key=<bean:write name="loop0" property="intakeId"/>"><bean:write name="loop0" property="intakeId"/></a></td>
		<td bgcolor="<%=color%>"><bean:write name="loop0" property="firstname"/></td>
		<td bgcolor="<%=color%>"><bean:write name="loop0" property="lastname"/></td> 
    	<td bgcolor="<%=color%>"><bean:write name="loop0" property="entryDate"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop0" property="dob"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop0" property="supervisor"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop0" property="job"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop0" property="cwt"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop0" property="currentClass"/></td>
    	<td width="30"></td>
	</tr>
	<% count++; %>
    </logic:iterate>

	
    <logic:empty name="reportForm" property="fastFindList">
    	<tr>
    		<td colspan="9">No students</td>
    	</tr>
    </logic:empty>
        
</table>

<br/><br/><br/>

<html:hidden property="action" styleId="action"/>

 </html:form>
 
</div>

</body>
</html>
			