<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<%@ page import="java.util.ArrayList" %>

<jsp:include page="../../includes/header.jsp" flush="true"/>

<script language="javascript">

function maskSsn(e,f){
  	
	var len = f.value.length;
	var key = whichKey(e);
	if(key>47 && key<58)
	{
		if( len==3 )f.value=f.value+'-'
		else if(len==6 )f.value=f.value+'-'
		else f.value=f.value;
	}
	else{
		f.value = f.value.replace(/[^0-9-]/,'')
		f.value = f.value.replace('--','-')
	}


}

function whichKey(e) {
   	
	var code;
	if (!e) var e = window.event;
	if (e.keyCode) code = e.keyCode;
	else if (e.which) code = e.which;
	return code
//	return String.fromCharCode(code);
}

</script>

    <h2>
        Student Search</br></br>
    </h2>
    	<div align="left"> 
        
        <html:form action="/Intake">
        <div onKeyPress="return checkSubmit(event)">
          	<table width="600" cellpadding="0" cellspacing="0" border="0">
            <tr>
            	<td>Last Name</td>
            	<td>First Name</td>
            	<td>Social Security</td>
            </tr>
            <tr>
                <td><html:text property="intake.lastname" size="30" maxlength="13"  /></td>
                <td><html:text property="intake.firstname" size="30" maxlength="13"  /></td>
                <td>
                	<html:text property="intake.ssn" size="11" maxlength="11"  onkeypress="return maskSsn(event,this)" />
                </td>
            </tr>
            </table>
            </br>
            <table width="500" cellpadding="0" cellspacing="0" border="0">
            <tr>
            	<td>From Date</td>
            	<td>To Date</td>
            	<td>Birthdate</td>
            </tr>
             <tr>
            	<td><html:text property="intake.entryDate" size="20" maxlength="13" styleClass="tcal" /></td>
                <td><html:text property="intake.exitDate" size="20" maxlength="13" styleClass="tcal" /></td>
                <td><html:text property="intake.dob" size="20" maxlength="13" styleClass="tcal" /></td>
            
            </tr>
            </table>
            </br>
            <table width="550" cellpadding="0" cellspacing="0" border="0">
            <tr>
            	<td>Program Status</td>
            	<td>Farm Location</td>
            	<td>Class</td>
            </tr>
            <tr>
            	<td>
                    <html:select property="programStatus" styleClass="status" > 
						<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_programStatus" value="value" label="label" />
					</html:select>
				</td>
				<td>
                	<html:select property="intake.farmBase" > 
					<html:option value="ALL">ALL</html:option>
					<html:optionsCollection name="ddl_farm" value="name" label="name" />
					</html:select>
                </td>
                <td>
                    <html:select property="currentClass"  > 
					<html:option value="">ALL</html:option>
					<html:optionsCollection name="ddl_classList" value="value" label="label" />
					</html:select>
                </td>
            
            </tr>
            </table>
                </br>
            <table width="550" cellpadding="0" cellspacing="0" border="0">
            <tr>
            	<td>Job Assignment</td>
            	<td>Supervisor</td>
            	<td>Job Skill</td>
            </tr>
            <tr>
            	<td>    
            		<html:select property="jobId"  > 
					<html:option value="0">ALL</html:option>
					<html:optionsCollection name="ddl_jobList" value="value" label="label" />
					</html:select>                
                </td>
                <td>
                    <html:select property="supervisorId"  > 
					<html:option value="0">ALL</html:option>
					<html:optionsCollection name="ddl_supervisor" value="value" label="label" />
					</html:select>
                </td>
            	<td>
            	    <html:select property="jobSkillId"  > 
					<html:option value="0">Select</html:option>
					<html:optionsCollection name="intakeForm" property="jobSkills" value="jobSkillId" label="description" />
					</html:select>
            	</td>
            </tr>
            </table>
            </br>
            <table width="500" cellpadding="0" cellspacing="0" border="0">
            <tr>
            	<td>Show Archived?&nbsp;<html:checkbox  name="intakeForm" property="archivedFlag" value="Yes" />
                <td>Show Picture?&nbsp;<html:checkbox  name="intakeForm" property="pictureFlag" value="Yes" />
                <td>Need GED?&nbsp;<html:checkbox name="intakeForm" property="gedFlag" value="Yes"/>
                <td>Show Drivers?&nbsp;<html:checkbox name="intakeForm" property="driverFlag" value="Yes"/>
            </tr>
            </table>
            </br></br>
            
            <table width="100%" >
            	<tr>
                	<td width="100%" align="left">
                      <input type="submit" name="action" value="Search Students"/>
                    </td>
                 </tr>
             </table>
                	
           </html:form>  
        
        </div>
    <div class="footer">
        
    </div>
  </div>
</form>

</body>
</html>
