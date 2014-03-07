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
        Application Search</br></br>
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
                <td><html:text property="searchParameter.lastname" size="30" maxlength="13"  /></td>
                <td><html:text property="searchParameter.firstname" size="30" maxlength="13"  /></td>
                <td>
                	<html:text property="searchParameter.ssn" size="11" maxlength="11"  onkeypress="return maskSsn(event,this)" />
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
            	<td><html:text property="searchParameter.beginDate" size="20" maxlength="13" styleClass="tcal" /></td>
                <td><html:text property="searchParameter.endDate" size="20" maxlength="13" styleClass="tcal" /></td>
                <td><html:text property="searchParameter.dob" size="20" maxlength="13" styleClass="tcal" /></td>
            
            </tr>
            </table>
            </br>
            <table width="550" cellpadding="0" cellspacing="0" border="0">
            <tr>
            	<td>Application Status:</td>
                <td>Farm Location</td>
            	<td>Job Skill</td>
            </tr>
            <tr>
            	<td>
                	<html:select property="searchParameter.applicationStatus"  >
                		<html:option value="ALL">ALL</html:option> 
						<html:optionsCollection name="ddl_applicationStatus" value="value" label="label" />
					</html:select>
                </td>
                <td>
                	<html:select property="searchParameter.farmBase" > 
					<html:option value="ALL">ALL</html:option>
					<html:optionsCollection name="ddl_farm" value="name" label="name" />
					</html:select>
                </td>
                <td>
            	    <html:select property="searchParameter.jobSkillId"  > 
					<html:option value="0">Select</html:option>
					<html:optionsCollection name="intakeForm" property="jobSkills" value="jobSkillId" label="description" />
					</html:select>
            	</td>
              
            </tr>
            </table>
        
            </br>
            <table width="300" cellpadding="0" cellspacing="0" border="0">
            <tr>
            	 <td>Need GED?&nbsp;<html:checkbox name="intakeForm" property="searchParameter.gedFlag" value="Yes"/>
                <td>Has Valid Driver's License?&nbsp;<html:checkbox name="intakeForm" property="searchParameter.driverFlag" value="Yes"/>
            </tr>
            </table>
            </br></br>
            
            <table width="100%" >
            	<tr>
                	<td width="100%" align="left">
                      <input type="submit" name="action" value="Search Applications"/>
                    </td>
                 </tr>
             </table>
                	
           </html:form>  
        
        </div>




<!-- 



    <h2>
        Application Search
    </h2>
    	<div align="center">
        <div class="applicationSearch">
        
        <html:form action="/Intake">
        <div onKeyPress="return checkSubmit(event)">
          	<table width="380" cellpadding="0" cellspacing="0" border="0">
            <tr>
            	<td></td>
                <td>From:</td>
                <td>To:</td>
                <td></td>
            </tr>
            <tr>
            	<td>Application Date:</td>
                <td><html:text property="searchParameter.beginDate" size="20" maxlength="13" styleClass="tcal" />
                </td>
                
                <td><html:text property="searchParameter.endDate" size="20" maxlength="13" styleClass="tcal" /></td>
                <td></td>
            </tr>
             <tr><td colspan="4"><br /></td></tr>
            <tr>
            	<td>Last Name:</td>
                <td colspan="3"><html:text property="searchParameter.lastname" size="30" maxlength="13"  /></td>
            </tr>
             <tr><td colspan="4"><br /></td></tr>
             <tr>
            	<td>First Name:</td>
                <td colspan="3"><html:text property="searchParameter.firstname" size="30" maxlength="13"  /></td>
            </tr>
             <tr><td colspan="4"><br /></td></tr>
             <tr>
            	<td>SSN:</td>
                <td colspan="3">
                	<html:text property="searchParameter.ssn" size="11" maxlength="11"  onkeypress="return maskSsn(event,this)" />
                </td>
            </tr>
             <tr><td colspan="4"><br /></td></tr>
               <tr>
            	<td>Birthdate:</td>
                <td colspan="3"><html:text property="searchParameter.dob" size="20" maxlength="13" styleClass="tcal" /></td>
            </tr>
            
            <tr><td colspan="4"><br /></td></tr>
             <tr>
            	<td>Location:</td>
                <td>
                	<html:select property="searchParameter.farmBase" multiple="true" style="height:90px;" > 
						<html:optionsCollection name="ddl_farm" value="name" label="name" />
						<html:option value="ALL">ALL</html:option>
					</html:select>
                </td>
                
             <tr><td colspan="4"><br /></td></tr>
             
            <tr><td colspan="4"><br /></td></tr>
             <tr>
            	<td>Application Status:</td>
                <td>
                	<html:select property="searchParameter.applicationStatus" multiple="true" style="height:90px;" > 
						<html:optionsCollection name="ddl_applicationStatus" value="value" label="label" />
					</html:select>
                </td>
                
             <tr><td colspan="4"><br /></td></tr>
                      
         </table>
                        
                  
                    
                 
                 </td>
            </tr>
            </table>
            <table width="100%" >
            	<tr>
                	<td width="100%" align="center">
                      <input type="submit" name="action" value="Search Applications"/>
                    </td>
                 </tr>
             </table>
                	
           </html:form>  
        </div>
        </div>
        
 -- -->
    <div class="footer">
        
    </div>
   </div>
</body>
</html>
