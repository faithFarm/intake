<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="../../includes/header.jsp" flush="true"/>
 

<html:form  action="/Intake">
<div onKeyPress="return checkSubmit(event)">
    <table width="750">
	<tr>
		<td><b>Help Desk Ticket</b><br /><br /></td>
	</tr>

 	<tr>
		<td>
    		<jsp:include page="../../includes/messages.jsp" flush="true"/>
    		<logic:notEmpty name="intakeForm" property="ticket.ticketId">
				Ticket #<bean:write name="intakeForm" property="ticket.ticketId" /> has been created and submitted.  An email has been sent to the system administrator and the information technology office.
		    </logic:notEmpty>
    		
	   </td>
	</tr>
	<logic:empty name="intakeForm" property="ticket.ticketId">
 	<tr>
		<td>Issue Type</td>
	</tr>
	<tr>
		<td>
        		<html:select property="ticket.issueType" styleClass="select" >
					<html:option value="">Select</html:option>
					<html:optionsCollection name="ddl_issueType" value="value" label="label" />
				</html:select> 
        </td>
	</tr>
	
	<tr>
		<td></br>Priority</td>
	</tr>
	<tr>
		<td>
        		<html:select property="ticket.priority" styleClass="select" >
					<html:option value="">Select</html:option>
					<html:optionsCollection name="ddl_priority" value="value" label="label" />
				</html:select> 
        </td>
	</tr>
    
	
	<tr>
		<td></br>Ticket Description:</td>
	</tr>
    <tr>
		<td>
			<html:textarea property="ticket.description" cols="93" rows="6" styleClass="textarea" />
		</td>
	</tr>
   
    <tr>
		<td valign="bottom" align="left" height="45">    
    	    <input type="submit" name="action" value="Submit Ticket" class="imageButtonSave" title="Submit Ticket" />&nbsp;
  		</td>
	</tr>
	</logic:empty>
    </table>
 
 </div> 
</html:form>

    <div class="footer">
        
    </div>
   
</body>
</html>