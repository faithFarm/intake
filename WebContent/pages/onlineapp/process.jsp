<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="student_header.jsp" flush="true"/>



<html:form action="/OnlineApp?action=Submit" styleId="form1" onsubmit="saveButton.disabled = true; return true;">

<table width="675" cellpadding="0" cellspacing="0" border="0">
<tr>
<td>
Thank you for completing your application to Faith Farm Ministries <b><bean:write name="onlineAppForm" property="intake.farmBase"/>.</b>
<br/><br/>
To submit your application click 'Submit'</br></br>
<!-- To review your application click 'Review'<br/><br/>-->
Once your application has been processed a representative will contact you.
</td>
</tr>
 <tr>
    	<td align="left">
    		<br/><br/>
    		<!-- <input type="submit" name="action" value="Review"  title="Review your application" style="padding-left:40px;padding-right:40px;"/>&nbsp; -->
			<input type="submit" id="saveButton" name="action" value="Submit"  title="Submit your application" style="padding-left:40px;padding-right:40px;" />
			<br/><br/><br/>
		</td>
		</tr>
	</table>
	
	
</td>
</tr>
</table>
	
<html:hidden property="pageSource" value="process"/>  
</html:form>
			
<jsp:include page="footer.jsp" flush="true"/>