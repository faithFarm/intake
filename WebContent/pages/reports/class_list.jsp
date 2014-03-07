<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head><title>
	Class List Report
</title>

	<link href="<%=request.getContextPath()%>/styles/report.css" rel="stylesheet" type="text/css" />
	
		
  	<script language="javascript">
	function printPage()
	{
	window.print();
	}
	</script>
	
</head>


<body onload="javascript:printPage();">


<div align="center">
<table width="1000">
	<tr>
		<td><h1><bean:write name="reportForm" property="reportTitle"/></h1></td>
	</tr>
	<tr>
		<td><b>for <bean:write name="reportForm" property="farmBase"/> on <bean:write name="reportForm" property="runDate"/></b></td>
	</tr>
</table>
<table width="1000" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle0"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class0List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% int count=0; %>
	<logic:iterate id="loop0" name="reportForm" property="class0List">
	<% if (count==0) {  %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow"><bean:write name="loop0" property="firstname" />&nbsp;<bean:write name="loop0" property="lastname"/></td>
    	<td class="reportRow"><bean:write name="loop0" property="entryDate"/></td>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>

 
</table>


<!-- Class 1 -->
<table width="1000" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle1"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class1List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop1" name="reportForm" property="class1List">
	<% if (count==0) { %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow"><bean:write name="loop1" property="firstname"/>&nbsp;<bean:write name="loop1" property="lastname"/></td>
    	<td class="reportRow"><bean:write name="loop1" property="entryDate"/></td>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
 
</table>


<!-- Class 2 -->
<table width="1000" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle2"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class2List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop2" name="reportForm" property="class2List">
	<% if (count==0) { %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow"><bean:write name="loop2" property="firstname"/>&nbsp;<bean:write name="loop2" property="lastname"/></td>
    	<td class="reportRow"><bean:write name="loop2" property="entryDate"/></td>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
 
</table>



<!-- Class 3 -->
<table width="1000" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle3"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class3List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop3" name="reportForm" property="class3List">
	<% if (count==0) { %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow"><bean:write name="loop3" property="firstname"/>&nbsp;<bean:write name="loop3" property="lastname"/></td>
    	<td class="reportRow"><bean:write name="loop3" property="entryDate"/></td>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
 
 
 <!-- Class 4 -->
<table width="1000" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle4"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class4List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop4" name="reportForm" property="class4List">
	<% if (count==0) { %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow"><bean:write name="loop4" property="firstname"/>&nbsp;<bean:write name="loop4" property="lastname"/></td>
    	<td class="reportRow"><bean:write name="loop4" property="entryDate"/></td>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
 
 
  <!-- Class 5 -->
<table width="1000" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle5"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class5List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop5" name="reportForm" property="class5List">
	<% if (count==0) {  %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow"><bean:write name="loop5" property="firstname"/>&nbsp;<bean:write name="loop5" property="lastname"/></td>
    	<td class="reportRow"><bean:write name="loop5" property="entryDate"/></td>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
 
 
  <!-- Class 6 -->
<table width="1000" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle6"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class6List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop6" name="reportForm" property="class6List">
	<% if (count==0) { %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow"><bean:write name="loop6" property="firstname" />&nbsp;<bean:write name="loop6" property="lastname"/></td>
    	<td class="reportRow"><bean:write name="loop6" property="entryDate" /></td>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
    
    
     <!-- Fresh Start -->
<table width="1000" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle7"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class7List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop7" name="reportForm" property="class7List">
	<% if (count==0) { %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow"><bean:write name="loop7" property="firstname"/>&nbsp;<bean:write name="loop7" property="lastname"/></td>
    	<td class="reportRow"><bean:write name="loop7" property="entryDate"/></td>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
 
 
 
  <!-- SLS -->
<table width="1000" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle8"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class8List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop8" name="reportForm" property="class8List">
	<% if (count==0) { %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow"><bean:write name="loop8" property="firstname"/>&nbsp;<bean:write name="loop8" property="lastname"/></td>
    	<td class="reportRow"><bean:write name="loop8" property="entryDate"/></td>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
    
    
     <!-- Intern -->
<table width="1000" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle9"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class9List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop9" name="reportForm" property="class9List">
	<% if (count==0) { %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow"><bean:write name="loop9" property="firstname"/>&nbsp;<bean:write name="loop9" property="lastname"/></td>
    	<td class="reportRow"><bean:write name="loop9" property="entryDate"/></td>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
 
 
 <!-- Student Teacher -->
<table width="1000" class="report">

	<tr>
		<td colspan="12" class="reportHeader"><b>Class:&nbsp;<bean:write name="reportForm" property="classTitle10"/></b></td>
	</tr>
	<logic:empty name="reportForm" property="class4List">
		<tr>
			<td class="reportRow">No students enrolled.</td>
	    </tr>
	</logic:empty>
	
	<% count=0; %>
	<logic:iterate id="loop10" name="reportForm" property="class10List">
	<% if (count==0) { %>
	<tr>
	<% } count++; %>
		<td class="reportStudentNameRow"><bean:write name="loop10" property="firstname"/>&nbsp;<bean:write name="loop10" property="lastname"/></td>
    	<td class="reportRow"><bean:write name="loop10" property="entryDate"/></td>
    	<td width="30" class="reportRow"></td>
	<% if (count==4) { count=0; %>
	</tr>
	<% } %>
    </logic:iterate>
 
 
</table>
</table>
</div>

</body>
</html>
			