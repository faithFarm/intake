<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


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
		<td><b>Spiritual Information: </b><br /><br /></td>
	</tr>

 	<tr>
		<td>
    		<jsp:include page="messages.jsp" flush="true"/>
	   </td>
	</tr>
	</table>
	
	<table width="100%" border="0">
	<tr>
		<td colspan="8">Spiritual Experience</td>
	</tr>
	<tr>
		<td colspan="8">
        		<html:select property="intake.spiritualExperience" styleClass="select" >
					<html:option value="">Select</html:option>
					<html:optionsCollection name="ddl_spiritual" value="value" label="label" />
				</html:select> </br>
        </td>
	</tr>
	<tr>
		<td >Describe your religious/spiritual experiences:</td>
	</tr>
    <tr>
		<td>
			<html:textarea property="intake.religiousExperience" cols="93" rows="15" styleClass="textarea" />
			</br>
		</td>
	</tr>
    
    <tr>
		<td>Religious Background
        		<html:select property="intake.religion" styleClass="select" >
					<html:option value="">Select</html:option>
					<html:optionsCollection name="ddl_religion" value="value" label="label" />
				</html:select> 
        </td>
	</tr>
	</table>
	
	<br/><br/>
	
	
	
</td>
</tr>
</table>

	<table width="100%" style="padding-right:20px;">
    <tr>
    	<td align="left"><input type="submit" name="action" value="Back"  title="Return to previous step" style="padding-left:40px;padding-right:40px;"/></td>
		<td align="right"><input type="submit" name="action" value="Next"  title="Continue to next step" style="padding-left:40px;padding-right:40px;"/></td>
	</tr>
	<tr><td colspan="2" height="30"></td></tr>
	</table>
	
<html:hidden property="pageSource" value="religious"/>  
<html:hidden property="nextStep" value="health"/>  
<html:hidden property="previousStep" value="personal"/> 


</html:form>

<jsp:include page="footer.jsp" flush="true"/>
    