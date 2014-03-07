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
<table width="900" cellpadding="0" cellspacing="0" border="0">
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
<table width="900" class="report" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30" colspan="8" bgcolor="#8ea3b9"><font size="1" color="#FFFFFF"><b>Orientation</b></font></td>
	</tr>
	<tr>
		<td bgcolor="#8ea3b9" width="50"><font size="1" color="#FFFFFF">Id</font></td>
		<td bgcolor="#8ea3b9" width="100"><font size="1" color="#FFFFFF">FirstName</font></td>
		<td bgcolor="#8ea3b9" width="100"><font size="1" color="#FFFFFF">LastName</font></td>
		<td bgcolor="#8ea3b9" width="100"><font size="1" color="#FFFFFF">Entry Date</font></td>
		<td bgcolor="#8ea3b9" width="200"><font size="1" color="#FFFFFF">Supervisor</font></td>
		<td bgcolor="#8ea3b9" width="200"><font size="1" color="#FFFFFF">Job</font></td>
		<td bgcolor="#8ea3b9" width="200"><font size="1" color="#FFFFFF">CWT</font></td>
		<td bgcolor="#8ea3b9" width="150"><font size="1" color="#FFFFFF">Current Program</font></td>
	</tr>
	<% int count=1; String color=""; %>
	<logic:iterate id="loop0" name="reportForm" property="class0CwtMasterList" indexId="i">
	<% if (count%2==0) color="#dae2e8"; else color="#ffffff"; %>
	<tr>	
		<td bgcolor="<%=color%>"><a style="text-decoration: none;" href="<%=request.getContextPath()%>/Intake.do?action=Edit&src=FastFind&key=<bean:write name="loop0" property="intake.intakeId"/>"><bean:write name="loop0" property="intake.intakeId"/></a></td>
		<td bgcolor="<%=color%>"><bean:write name="loop0" property="intake.firstname"/></td>
		<td bgcolor="<%=color%>"><bean:write name="loop0" property="intake.lastname"/></td> 
    	<td bgcolor="<%=color%>"><bean:write name="loop0" property="intake.entryDate"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop0" property="cwtSupervisor.firstname"/>&nbsp;<bean:write name="loop0" property="cwtSupervisor.lastname"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop0" property="cwtJob.title"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop0" property="program.programName"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="reportForm" property="program0[${i}]"/></td>
    	<td width="30"></td>
	</tr>
	<% count++; %>
    </logic:iterate>

	

<!-- 

CLASS 1

 -->

	<tr>
		<td  height="30" colspan="8" bgcolor="#8ea3b9"><font size="1" color="#FFFFFF"><b>Class 1</b></font></td>
	</tr>

	<% count=1; %>
	<logic:iterate id="loop1" name="reportForm" property="class1CwtMasterList" indexId="i">
	<% if (count%2==0) color="#dae2e8"; else color="#ffffff"; %>
	<tr>	
		<td bgcolor="<%=color%>"><a style="text-decoration: none;" href="<%=request.getContextPath()%>/Intake.do?action=Edit&key=<bean:write name="loop1" property="intake.intakeId"/>"><bean:write name="loop1" property="intake.intakeId"/></a></td>
		<td bgcolor="<%=color%>"><bean:write name="loop1" property="intake.firstname"/></td>
		<td bgcolor="<%=color%>"><bean:write name="loop1" property="intake.lastname"/></td> 
    	<td bgcolor="<%=color%>"><bean:write name="loop1" property="intake.entryDate"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop1" property="cwtSupervisor.firstname"/>&nbsp;<bean:write name="loop1" property="cwtSupervisor.lastname"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop1" property="cwtJob.title"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop1" property="program.programName"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="reportForm" property="program1[${i}]"/></td>
    	<td width="30"></td>
	</tr>
	<% count++; %>
    </logic:iterate>
    <logic:empty name="reportForm" property="class1CwtMasterList">
    	<tr>
    		<td colspan="8">No students</td>
    	</tr>
    </logic:empty>
    
    

<!-- 

CLASS 2

 -->

	<tr>
		<td  height="30" colspan="8" bgcolor="#8ea3b9"><font size="1" color="#FFFFFF"><b>Class 2</b></font></td>
	</tr>

	<% count=1; %>
	<logic:iterate id="loop2" name="reportForm" property="class2CwtMasterList" indexId="i">
	<% if (count%2==0) color="#dae2e8"; else color="#ffffff"; %>
	<tr>	
		<td bgcolor="<%=color%>"><a style="text-decoration: none;" href="<%=request.getContextPath()%>/Intake.do?action=Edit&key=<bean:write name="loop2" property="intake.intakeId"/>"><bean:write name="loop2" property="intake.intakeId"/></a></td>
		<td bgcolor="<%=color%>"><bean:write name="loop2" property="intake.firstname"/></td>
		<td bgcolor="<%=color%>"><bean:write name="loop2" property="intake.lastname"/></td> 
    	<td bgcolor="<%=color%>"><bean:write name="loop2" property="intake.entryDate"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop2" property="cwtSupervisor.firstname"/>&nbsp;<bean:write name="loop2" property="cwtSupervisor.lastname"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop2" property="cwtJob.title"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop2" property="program.programName"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="reportForm" property="program2[${i}]"/></td>
    	<td width="30"></td>
	</tr>
	<% count++; %>
    </logic:iterate>
     <logic:empty name="reportForm" property="class2CwtMasterList">
    	<tr>
    		<td colspan="8">No students</td>
    	</tr>
    </logic:empty>
    
    
    

<!-- 

CLASS 3

 -->

	<tr>
		<td  height="30" colspan="8" bgcolor="#8ea3b9"><font size="1" color="#FFFFFF"><b>Class 3</b></font></td>
	</tr>

	<% count=1; %>
	<logic:iterate id="loop3" name="reportForm" property="class3CwtMasterList" indexId="i">
	<% if (count%2==0) color="#dae2e8"; else color="#ffffff"; %>
	<tr>	
		<td bgcolor="<%=color%>"><a style="text-decoration: none;" href="<%=request.getContextPath()%>/Intake.do?action=Edit&key=<bean:write name="loop3" property="intake.intakeId"/>"><bean:write name="loop3" property="intake.intakeId"/></a></td>
		<td bgcolor="<%=color%>"><bean:write name="loop3" property="intake.firstname"/></td>
		<td bgcolor="<%=color%>"><bean:write name="loop3" property="intake.lastname"/></td> 
    	<td bgcolor="<%=color%>"><bean:write name="loop3" property="intake.entryDate"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop3" property="cwtSupervisor.firstname"/>&nbsp;<bean:write name="loop3" property="cwtSupervisor.lastname"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop3" property="cwtJob.title"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop3" property="program.programName"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="reportForm" property="program3[${i}]"/></td>
    	<td width="30"></td>
	</tr>
	<% count++; %>
    </logic:iterate>
     <logic:empty name="reportForm" property="class3CwtMasterList">
    	<tr>
    		<td colspan="8">No students</td>
    	</tr>
    </logic:empty>
    
   
<!-- 

CLASS 4

 -->

	<tr>
		<td  height="30" colspan="8" bgcolor="#8ea3b9"><font size="1" color="#FFFFFF"><b>Class 4</b></font></td>
	</tr>

	<% count=1; %>
	<logic:iterate id="loop4" name="reportForm" property="class4CwtMasterList" indexId="i">
	<% if (count%2==0) color="#dae2e8"; else color="#ffffff"; %>
	<tr>	
		<td bgcolor="<%=color%>"><a style="text-decoration: none;" href="<%=request.getContextPath()%>/Intake.do?action=Edit&key=<bean:write name="loop4" property="intake.intakeId"/>"><bean:write name="loop4" property="intake.intakeId"/></a></td>
		<td bgcolor="<%=color%>"><bean:write name="loop4" property="intake.firstname"/></td>
		<td bgcolor="<%=color%>"><bean:write name="loop4" property="intake.lastname"/></td> 
    	<td bgcolor="<%=color%>"><bean:write name="loop4" property="intake.entryDate"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop4" property="cwtSupervisor.firstname"/>&nbsp;<bean:write name="loop4" property="cwtSupervisor.lastname"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop4" property="cwtJob.title"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop4" property="program.programName"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="reportForm" property="program4[${i}]"/></td>
    	<td width="30"></td>
	</tr>
	<% count++; %>
    </logic:iterate>
      <logic:empty name="reportForm" property="class4CwtMasterList">
    	<tr>
    		<td colspan="8">No students</td>
    	</tr>
    </logic:empty>
    
   

<!-- 

CLASS 5

 -->
<tr>
		<td  height="30" colspan="8" bgcolor="#8ea3b9"><font size="1" color="#FFFFFF"><b>Class 5</b></font></td>
	</tr>

	<% count=1; %>
	<logic:iterate id="loop5" name="reportForm" property="class5CwtMasterList" indexId="i">
	<% if (count%2==0) color="#dae2e8"; else color="#ffffff"; %>
	<tr>	
		<td bgcolor="<%=color%>"><a style="text-decoration: none;" href="<%=request.getContextPath()%>/Intake.do?action=Edit&key=<bean:write name="loop5" property="intake.intakeId"/>"><bean:write name="loop5" property="intake.intakeId"/></a></td>
		<td bgcolor="<%=color%>"><bean:write name="loop5" property="intake.firstname"/></td>
		<td bgcolor="<%=color%>"><bean:write name="loop5" property="intake.lastname"/></td> 
    	<td bgcolor="<%=color%>"><bean:write name="loop5" property="intake.entryDate"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop5" property="cwtSupervisor.firstname"/>&nbsp;<bean:write name="loop5" property="cwtSupervisor.lastname"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop5" property="cwtJob.title"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop5" property="program.programName"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="reportForm" property="program5[${i}]"/></td>
    	<td width="30"></td>
	</tr>
	<% count++; %>
    </logic:iterate>
      <logic:empty name="reportForm" property="class5CwtMasterList">
    	<tr>
    		<td colspan="8">No students</td>
    	</tr>
    </logic:empty>
    
    
 
<!-- 

CLASS 6

 -->

	<tr>
		<td  height="30" colspan="8" bgcolor="#8ea3b9"><font size="1" color="#FFFFFF"><b>Class 6</b></font></td>
	</tr>

	<% count=1; %>
	<logic:iterate id="loop6" name="reportForm" property="class6CwtMasterList" indexId="i">
	<% if (count%2==0) color="#dae2e8"; else color="#ffffff"; %>
	<tr>	
		<td bgcolor="<%=color%>"><a style="text-decoration: none;" href="<%=request.getContextPath()%>/Intake.do?action=Edit&key=<bean:write name="loop6" property="intake.intakeId"/>"><bean:write name="loop6" property="intake.intakeId"/></a></td>
		<td bgcolor="<%=color%>"><bean:write name="loop6" property="intake.firstname"/></td>
		<td bgcolor="<%=color%>"><bean:write name="loop6" property="intake.lastname"/></td> 
    	<td bgcolor="<%=color%>"><bean:write name="loop6" property="intake.entryDate"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop6" property="cwtSupervisor.firstname"/>&nbsp;<bean:write name="loop6" property="cwtSupervisor.lastname"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop6" property="cwtJob.title"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop6" property="program.programName"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="reportForm" property="program6[${i}]"/></td>
    	<td width="30"></td>
	</tr>
	<% count++; %>
    </logic:iterate>
        <logic:empty name="reportForm" property="class6CwtMasterList">
    	<tr>
    		<td colspan="8">No students</td>
    	</tr>
    </logic:empty>
    
    
    
    <!-- 

CLASS 7

 -->

	<tr>
		<td  height="30" colspan="8" bgcolor="#8ea3b9"><font size="1" color="#FFFFFF"><b>Fresh Start</b></font></td>
	</tr>

	<% count=1; %>
	<logic:iterate id="loop7" name="reportForm" property="class7CwtMasterList" indexId="i">
	<% if (count%2==0) color="#dae2e8"; else color="#ffffff"; %>
	<tr>	
		<td bgcolor="<%=color%>"><a style="text-decoration: none;" href="<%=request.getContextPath()%>/Intake.do?action=Edit&key=<bean:write name="loop7" property="intake.intakeId"/>"><bean:write name="loop7" property="intake.intakeId"/></a></td>
		<td bgcolor="<%=color%>"><bean:write name="loop7" property="intake.firstname"/></td>
		<td bgcolor="<%=color%>"><bean:write name="loop7" property="intake.lastname"/></td> 
    	<td bgcolor="<%=color%>"><bean:write name="loop7" property="intake.entryDate"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop7" property="cwtSupervisor.firstname"/>&nbsp;<bean:write name="loop7" property="cwtSupervisor.lastname"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop7" property="cwtJob.title"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop7" property="program.programName"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="reportForm" property="program7[${i}]"/></td>
    	<td width="30"></td>
	</tr>
	<% count++; %>
    </logic:iterate>
     <logic:empty name="reportForm" property="class7CwtMasterList">
    	<tr>
    		<td colspan="8">No students</td>
    	</tr>
    </logic:empty>
    
    
    <!-- 

CLASS 8

 -->

	<tr>
		<td  height="30" colspan="8" bgcolor="#8ea3b9"><font size="1" color="#FFFFFF"><b>Interns</b></font></td>
	</tr>

	<% count=1; %>
	<logic:iterate id="loop8" name="reportForm" property="class8CwtMasterList" indexId="i">
	<% if (count%2==0) color="#dae2e8"; else color="#ffffff"; %>
	<tr>	
		<td bgcolor="<%=color%>"><a style="text-decoration: none;" href="<%=request.getContextPath()%>/Intake.do?action=Edit&key=<bean:write name="loop8" property="intake.intakeId"/>"><bean:write name="loop8" property="intake.intakeId"/></a></td>
		<td bgcolor="<%=color%>"><bean:write name="loop8" property="intake.firstname"/></td>
		<td bgcolor="<%=color%>"><bean:write name="loop8" property="intake.lastname"/></td> 
    	<td bgcolor="<%=color%>"><bean:write name="loop8" property="intake.entryDate"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop8" property="cwtSupervisor.firstname"/>&nbsp;<bean:write name="loop8" property="cwtSupervisor.lastname"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop8" property="cwtJob.title"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop8" property="program.programName"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="reportForm" property="program8[${i}]"/></td>
    	<td width="30"></td>
	</tr>
	<% count++; %>
    </logic:iterate>
      <logic:empty name="reportForm" property="class8CwtMasterList">
    	<tr>
    		<td colspan="8">No students</td>
    	</tr>
    </logic:empty>
    
    
    
    <!-- 

CLASS 9

 -->

	<tr>
		<td  height="30" colspan="8" bgcolor="#8ea3b9"><font size="1" color="#FFFFFF"><b>SLS</b></font></td>
	</tr>

	<% count=1; %>
	<logic:iterate id="loop9" name="reportForm" property="class9CwtMasterList" indexId="i">
	<% if (count%2==0) color="#dae2e8"; else color="#ffffff"; %>
	<tr>	
		<td bgcolor="<%=color%>"><a style="text-decoration: none;" href="<%=request.getContextPath()%>/Intake.do?action=Edit&key=<bean:write name="loop9" property="intake.intakeId"/>"><bean:write name="loop9" property="intake.intakeId"/></a></td>
		<td bgcolor="<%=color%>"><bean:write name="loop9" property="intake.firstname"/></td>
		<td bgcolor="<%=color%>"><bean:write name="loop9" property="intake.lastname"/></td> 
    	<td bgcolor="<%=color%>"><bean:write name="loop9" property="intake.entryDate"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop9" property="cwtSupervisor.firstname"/>&nbsp;<bean:write name="loop9" property="cwtSupervisor.lastname"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop9" property="cwtJob.title"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop9" property="program.programName"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="reportForm" property="program9[${i}]"/></td>
    	<td width="30"></td>
	</tr>
	<% count++; %>
    </logic:iterate>
      <logic:empty name="reportForm" property="class9CwtMasterList">
    	<tr>
    		<td colspan="8">No students</td>
    	</tr>
    </logic:empty>
    
    
    <!-- 

CLASS 10

 -->

	<tr>
		<td  height="30" colspan="8" bgcolor="#8ea3b9"><font size="1" color="#FFFFFF"><b>Student Teacher</b></font></td>
	</tr>

	<% count=1; %>
	<logic:iterate id="loop10" name="reportForm" property="class10CwtMasterList" indexId="i">
	<% if (count%2==0) color="#dae2e8"; else color="#ffffff"; %>
	<tr>	
		<td bgcolor="<%=color%>"><a style="text-decoration: none;" href="<%=request.getContextPath()%>/Intake.do?action=Edit&key=<bean:write name="loop10" property="intake.intakeId"/>"><bean:write name="loop10" property="intake.intakeId"/></a></td>
		<td bgcolor="<%=color%>"><bean:write name="loop10" property="intake.firstname"/></td>
		<td bgcolor="<%=color%>"><bean:write name="loop10" property="intake.lastname"/></td> 
    	<td bgcolor="<%=color%>"><bean:write name="loop10" property="intake.entryDate"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop10" property="cwtSupervisor.firstname"/>&nbsp;<bean:write name="loop10" property="cwtSupervisor.lastname"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop10" property="cwtJob.title"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop10" property="program.programName"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="reportForm" property="program10[${i}]"/></td>
    	<td width="30"></td>
	</tr>
	<% count++; %>
    </logic:iterate>
       <logic:empty name="reportForm" property="class10CwtMasterList">
    	<tr>
    		<td colspan="8">No students</td>
    	</tr>
    </logic:empty>
    

   <!-- 

CLASS 11

 -->

	<tr>
		<td  height="30" colspan="8" bgcolor="#8ea3b9"><font size="1" color="#FFFFFF"><b>Omega</b></font></td>
	</tr>

	<% count=1; %>
	<logic:iterate id="loop11" name="reportForm" property="class11CwtMasterList" indexId="i">
	<% if (count%2==0) color="#dae2e8"; else color="#ffffff"; %>
	<tr>	
		<td bgcolor="<%=color%>"><a style="text-decoration: none;" href="<%=request.getContextPath()%>/Intake.do?action=Edit&key=<bean:write name="loop11" property="intake.intakeId"/>"><bean:write name="loop11" property="intake.intakeId"/></a></td>
		<td bgcolor="<%=color%>"><bean:write name="loop11" property="intake.firstname"/></td>
		<td bgcolor="<%=color%>"><bean:write name="loop11" property="intake.lastname"/></td> 
    	<td bgcolor="<%=color%>"><bean:write name="loop11" property="intake.entryDate"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop11" property="cwtSupervisor.firstname"/>&nbsp;<bean:write name="loop11" property="cwtSupervisor.lastname"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop11" property="cwtJob.title"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="loop11" property="program.programName"/></td>
    	<td bgcolor="<%=color%>"><bean:write name="reportForm" property="program11[${i}]"/></td>
    	<td width="30"></td>
	</tr>
	<% count++; %>
    </logic:iterate>
       <logic:empty name="reportForm" property="class11CwtMasterList">
    	<tr>
    		<td colspan="8">No students</td>
    	</tr>
    </logic:empty>
        
</table>

<br/><br/><br/>

<html:hidden property="action" styleId="action"/>

 </html:form>
 
</div>

</body>
</html>
			