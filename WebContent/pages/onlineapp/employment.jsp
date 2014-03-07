<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="student_header.jsp" flush="true"/>


<html:form action="/OnlineApp">

<table width="675" cellpadding="0" cellspacing="0" border="0">
<tr>
<td align="left">


 <table width="100%">
	<tr>
		<td><b>Work Experience (Check all that apply):</b><br /></td>
	</tr>
</table>

<table width="100%">
	<tr>
	<td>
		<jsp:include page="messages.jsp" flush="true"/>
	</td>
	</tr>
</table>

  
 <table width="100%">
    		
		    	<logic:iterate id="loop" name="onlineAppForm" property="jobSkills" indexId="i">
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
    
    <br/><br/>
    
    <table width="100%">
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
     
   <br/><br/>	


	<table width="100%" style="padding-right:20px;">
    <tr>
    	<td align="left"><input type="submit" name="action" value="Back"  title="Return to previous step" style="padding-left:40px;padding-right:40px;"/></td>
		<td align="right"><input type="submit" name="action" value="Next"  title="Continue to next step" style="padding-left:40px;padding-right:40px;"/></td>
	</tr>
	<tr><td colspan="2" height="30"></td></tr>
	</table>
	
</td>
</tr>
</table>

<html:hidden property="pageSource" value="employment"/>  
<html:hidden property="nextStep" value="process"/>  
<html:hidden property="previousStep" value="legal"/> 


</html:form>

<jsp:include page="footer.jsp" flush="true"/>