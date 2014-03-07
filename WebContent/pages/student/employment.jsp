<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="../../includes/header_info.jsp" flush="true"/>
 

<html:form action="/Intake">
<div onKeyPress="return checkSubmit(event)">
  <table width="750">
	<tr>
		<td colspan="8"><b>Work Experience (Check all that apply):</b><br /></td>
	</tr>
	
	<jsp:include page="../../includes/messages.jsp" flush="true"/>
	
    <tr>
    	<td colspan="8">
    		<table width="100%">
    		
		    	<logic:iterate id="loop" name="intakeForm" property="jobSkills" indexId="i">
		        <tr>
		    	<td width="150">
		        	<bean:write name="loop" property="description"/>
		        </td>
		        <td colspan="7">
		   			<html:checkbox property="workExperience[${i}]" value="Yes" />
		   			<br />
				</td>
		        </tr>
		        </logic:iterate>
			
             <tr>
             	<td width="150">Other Description</td>
             	<td colspan="7"><html:text property="intake.otherJobSkill" size="20" maxlength="30"/></td>
             </tr>
            
             </table>
         </td>
    </tr>

     <tr>
    			<td colspan="8">
                <table width="500">
                <tr>
                	<td>Employer</td>
                	<td>Job Title/Description</td>
                    <td>Contact</td>
                    <td>Phone</td>
                    <td>Dates of Employment</td>
                </tr>
                <tr>
                	<td><html:text property="intake.employer1Name" size="30" maxlength="60"/></td>
                	<td><html:text property="intake.employer1Job" size="30" maxlength="60"/></td>
                	<td><html:text property="intake.employer1Contact" size="25" maxlength="60"/></td>
                    <td><html:text property="intake.employer1Phone" size="15" maxlength="20"/></td>
                    <td><html:text property="intake.employer1Dates" size="20" maxlength="20"/></td>
                </tr>
                <tr>
                	<td><html:text property="intake.employer2Name" size="30" maxlength="60"/></td>
                	<td><html:text property="intake.employer2Job" size="30" maxlength="60"/></td>
                	<td><html:text property="intake.employer2Contact" size="25" maxlength="60"/></td>
                    <td><html:text property="intake.employer2Phone" size="15" maxlength="20"/></td>
                    <td><html:text property="intake.employer2Dates" size="20" maxlength="20"/></td>
                </tr>
                <tr>
                	<td><html:text property="intake.employer3Name" size="30" maxlength="60"/></td>
                	<td><html:text property="intake.employer3Job" size="30" maxlength="60"/></td>
                	<td><html:text property="intake.employer3Contact" size="25" maxlength="60"/></td>
                    <td><html:text property="intake.employer3Phone" size="15" maxlength="20"/></td>
                    <td><html:text property="intake.employer3Dates" size="20" maxlength="20"/></td>
                </tr>
                <tr>
                	<td><html:text property="intake.employer3Name" size="30" maxlength="60"/></td>
                	<td><html:text property="intake.employer3Job" size="30" maxlength="60"/></td>
                	<td><html:text property="intake.employer3Contact" size="25" maxlength="60"/></td>
                    <td><html:text property="intake.employer3Phone" size="15" maxlength="20"/></td>
                    <td><html:text property="intake.employer3Dates" size="20" maxlength="20"/></td>
                </tr>
               
             </tr>
            
             </table>
         </td>
    </tr>




   <tr>
		<td colspan="8" valign="bottom" align="center" height="45">    
    		<input type="submit" name="action" value="Save" class="imageButtonSave" title="Save Information" />&nbsp;
    	</td>
	</tr>

    </table>

 <html:hidden property="pageSource" value="employment"/>  
 
</div>
</html:form>
    <div class="footer">
        
    </div>
   

</body>
</html>