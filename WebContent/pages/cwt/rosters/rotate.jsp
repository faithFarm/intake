<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="java.util.ArrayList" %>

<jsp:include page="../../../includes/header.jsp" flush="true"/>

<script language="javascript">

function checkAll(bx)
{

 var cbs = document.getElementsByTagName('input');
 for(var i=0; i < cbs.length; i++)
 {
    if(cbs[i].type == 'checkbox')
    {
        cbs[i].checked = bx.checked;
     }
 }
}

</script>


<h2>
      Course Rotation 
</h2>
     
<div align="center">
        
        
<div align="center">
<table width="900">
	<tr>
		<td><h1><bean:write name="reportForm" property="reportTitle"/>&nbsp;(<bean:write name="reportForm" property="farmBase"/>)</h1></td>
	</tr>
	
	<tr>
		<td><b>Last Rotated on <bean:write name="reportForm" property="lastRotationDate"/> by <bean:write name="reportForm" property="lastRotatedBy"/></b></td>
	</tr>
	
	<tr>
		<td><input type="checkbox" name="checkall" onclick="checkAll(this);" style="height:10px;">Check All</td>
	</tr>
</table>

<html:form action="/Report">

<table width="900" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle0"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class0List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% int count=0; %>
	<logic:iterate id="loop0" name="reportForm" property="class0List" indexId="i">
	<% if (count==0) {  %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow">
		<html:checkbox name="reportForm" property="class0flag[${i}]" value="Yes" />
		<bean:write name="loop0" property="firstname"/>&nbsp;<bean:write name="loop0" property="lastname"/></td>
    	<td class="reportRow"><bean:write name="loop0" property="entryDate"/></td>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
 
</table>


<!-- Class 1 -->
<table width="900" class="report">
	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle1"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class1List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop1" name="reportForm" property="class1List" indexId="i">
	<% if (count==0) { %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow">
			<html:checkbox name="reportForm" property="class1flag[${i}]" value="Yes" />
			<bean:write name="loop1" property="firstname"/>&nbsp;<bean:write name="loop1" property="lastname"/>
			&nbsp;&nbsp;<bean:write name="loop1" property="entryDate"/>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
</table>



<!-- Class 2 -->
<table width="900" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle2"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class2List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop2" name="reportForm" property="class2List" indexId="i">
	<% if (count==0) { %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow">
			<html:checkbox name="reportForm" property="class2flag[${i}]" value="Yes" />
			<bean:write name="loop2" property="firstname"/>&nbsp;<bean:write name="loop2" property="lastname"/>
			&nbsp;&nbsp;<bean:write name="loop2" property="entryDate"/>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
 
</table>



<!-- Class 3 -->
<table width="900" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle3"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class3List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop3" name="reportForm" property="class3List" indexId="i">
	<% if (count==0) { %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow">
			<html:checkbox name="reportForm" property="class3flag[${i}]" value="Yes" />
			<bean:write name="loop3" property="firstname"/>&nbsp;<bean:write name="loop3" property="lastname"/>
			&nbsp;&nbsp;<bean:write name="loop3" property="entryDate"/>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
 
 
 <!-- Class 4 -->
<table width="900" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle4"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class4List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop4" name="reportForm" property="class4List" indexId="i">
	<% if (count==0) { %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow">
		<html:checkbox name="reportForm" property="class4flag[${i}]" value="Yes" />
		<bean:write name="loop4" property="firstname"/>&nbsp;<bean:write name="loop4" property="lastname"/>
		&nbsp;&nbsp;<bean:write name="loop4" property="entryDate"/>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
 
 
  <!-- Class 5 -->
<table width="900" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle5"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class5List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop5" name="reportForm" property="class5List" indexId="i">
	<% if (count==0) { %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow">
			<html:checkbox name="reportForm" property="class5flag[${i}]" value="Yes" />
			<bean:write name="loop5" property="firstname"/>&nbsp;<bean:write name="loop5" property="lastname"/>
			&nbsp;&nbsp;<bean:write name="loop5" property="entryDate"/>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
 
 
  <!-- Class 6 -->
<table width="900" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle6"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class6List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop6" name="reportForm" property="class6List" indexId="i">
	<% if (count%5==0) { %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow">
			<html:checkbox name="reportForm" property="class6flag[${i}]" value="Yes" />
			<bean:write name="loop6" property="firstname"/>&nbsp;<bean:write name="loop6" property="lastname"/>
			&nbsp;&nbsp;<bean:write name="loop6" property="entryDate"/>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
    
    
 
 
</table>
</table>

<input type="submit" name="action" value="Rotate Students"/>

</div>
</html:form>

</body>
</html>
			