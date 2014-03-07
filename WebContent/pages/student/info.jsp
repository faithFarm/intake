<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/profile.css"  />
<title>Print Student/Intake Profile</title>

  	<script language="javascript">
	function printPage()
	{
	window.print();
	}
	
</script>

<style>
body {
	font-size:.90em;
	font-family: Arial;
}
	
</style>
</head>
<body background="#FFFFFF" onLoad="javascript:print();">


<table width="900" border="0" cellpadding="0" cellspacing="0">
 <tr>
              <td valign="top" align="left">
                    		<logic:notEmpty name="intakeForm" property="intake.imageHeadshot">
                		   		<img src="<%=request.getContextPath()%>/Image.do" width="200" height="133"/>
                		    </logic:notEmpty>
                	        <logic:empty name="intakeForm" property="intake.imageHeadshot">
                	       		<img src="<%=request.getContextPath()%>/images/local/person.jpg" width="200" height="133"/>
                	       </logic:empty>                    			
              </td>
              <td align="right" valign="top">
              	Date Entered: <b><bean:write name="intakeForm" property="intake.entryDate" /></b>
              </td>
 </tr>
 <tr><td colspan="3" height="10"></td></tr>
 </table>
 
 <table width="900" border="0" cellpadding="0" cellspacing="0">
 <tr>
              <td valign="top" align="left">
              Last Name :&nbsp;<b><bean:write name="intakeForm" property="intake.lastname" /></b>      			
              </td>
              <td align="left" valign="top">
              First Name :&nbsp;<b><bean:write name="intakeForm" property="intake.firstname" /></b>  
              </td>
              <td align="left" valign="top">
              MI :&nbsp;<b><bean:write name="intakeForm" property="intake.mi" /></b>  
              &nbsp;&nbsp;&nbsp;
              SSN :&nbsp;<b><bean:write name="intakeForm" property="intake.ssn" /></b>  
              </td>
 </tr>
 <tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left" colspan="3">
               Referred By:&nbsp;<b><bean:write name="intakeForm" property="intake.referredBy" /></b>      			
              </td>
             
 </tr>
 <tr>
              <td valign="top" align="left" colspan="3">
               Current Address:&nbsp;<b><bean:write name="intakeForm" property="intake.address" /></b>      			
              </td>
             
 </tr>
 <tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left">
               City:&nbsp;<b><bean:write name="intakeForm" property="intake.city" /></b>      			
              </td>
              <td align="left" valign="top">
               State:&nbsp;<b><bean:write name="intakeForm" property="intake.state" /></b>  
              </td>
              <td align="left" valign="top">
               Zip:&nbsp;<b><bean:write name="intakeForm" property="intake.zipcode" /></b>  
              </td>
 </tr>
 <tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left">
               Phone Number:&nbsp;<b><bean:write name="intakeForm" property="intake.emergencyPhone" /></b>      			
              </td>
              <td align="left" valign="top">
               Contact Person:&nbsp;<b><bean:write name="intakeForm" property="intake.emergencyContact" /></b>  
              </td>
              <td align="left" valign="top">
               Relationship:&nbsp;<b><bean:write name="intakeForm" property="intake.emergencryRelationship" /></b>  
              </td>
 </tr>
 <tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left">
               Date of Birth:&nbsp;<b><bean:write name="intakeForm" property="intake.dob" /></b>      			
              </td>
              <td align="left" valign="top">
               Height:&nbsp;<b><bean:write name="intakeForm" property="intake.height" /></b>  
              </td>
              <td align="left" valign="top">
               Weight:&nbsp;<b><bean:write name="intakeForm" property="intake.weight" /></b>  
               &nbsp;&nbsp;Eyes:&nbsp;<b><bean:write name="intakeForm" property="intake.eyeColor" /></b>  
               &nbsp;&nbsp;Hairy:&nbsp;<b><bean:write name="intakeForm" property="intake.hairColor" /></b>  
              </td>
 </tr>
 <tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left">
               Ethnic Background:&nbsp;<b><bean:write name="intakeForm" property="intake.ethnicity" /></b>      			
              </td>
              <td align="left" valign="top">
               Marital Status:&nbsp;<b><bean:write name="intakeForm" property="intake.maritalStatus" /></b>  
              </td>
              <td align="left" valign="top">
               Presently Living:&nbsp;<b><bean:write name="intakeForm" property="intake.homeLocation" /></b>  
              </td>
 </tr>

 <tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left" colspan="3">
               Schooling Completed:&nbsp;<b><bean:write name="intakeForm" property="intake.educationLevel" /></b>      			
              </td>
              
 </tr>

 <tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left">
               Valid Driver License:&nbsp;<b><bean:write name="intakeForm" property="intake.dlFlag" /></b>      			
              </td>
              <td valign="top" align="left">
               State:&nbsp;<b><bean:write name="intakeForm" property="intake.dlState" /></b>      			
              </td>
              <td valign="top" align="left">
               DL Number:&nbsp;<b><bean:write name="intakeForm" property="intake.dlNumber" /></b>      			
              </td>
 </tr>

<tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left">
               Father Still Living:&nbsp;<b><bean:write name="intakeForm" property="intake.fatherLivingFlag" /></b>      			
              </td>
              <td valign="top" align="left" colspan="2">
               Relationship with father:&nbsp;<b><bean:write name="intakeForm" property="intake.relationshipWithFather" /></b>      			
              </td>
              
 </tr>
 
 <tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left">
               Mother Still Living:&nbsp;<b><bean:write name="intakeForm" property="intake.motherLivingFlag" /></b>      			
              </td>
              <td valign="top" align="left" colspan="2">
               Relationship with mother:&nbsp;<b><bean:write name="intakeForm" property="intake.relationshipWithMother" /></b>      			
              </td>
              
 </tr>

<tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left">
               Number of Brothers:&nbsp;<b><bean:write name="intakeForm" property="intake.brothers" /></b>      			
              </td>
              <td valign="top" align="left" >
               Number of Sisters:&nbsp;<b><bean:write name="intakeForm" property="intake.sisters" /></b>      			
              </td>
              <td valign="top" align="left" >
               Number of Children:&nbsp;<b><bean:write name="intakeForm" property="intake.children" /></b>      			
              </td>
              
 </tr>
 
 <tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left">
               Spiritual Background:&nbsp;<b><bean:write name="intakeForm" property="intake.religion" /></b>      			
              </td>
              <td valign="top" align="left" colspan="2" >
               Spiritual Experience:&nbsp;<b><bean:write name="intakeForm" property="intake.religiousExperience" /></b>      			
              </td>
             
              
 </tr>
 
 <tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left">
               General health:&nbsp;<b><bean:write name="intakeForm" property="intake.currentHealth" /></b>      			
              </td>
              <td valign="top" align="left" >
               Medical Condition:&nbsp;<b></b>      			
              </td>
              <td valign="top" align="left" >
               If so, what:&nbsp;<b></b>      			
              </td>
              
 </tr>
 
 <tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left">
               Felon:&nbsp;<b><bean:write name="intakeForm" property="intake.felonyFlag" /></b>      			
              </td>
              <td valign="top" align="left" >
               How Many:&nbsp;<b><bean:write name="intakeForm" property="intake.felonyQty" /></b>      			
              </td>
              <td valign="top" align="left" >
               Name them:&nbsp;<b><bean:write name="intakeForm" property="intake.felonyDetails" /></b>      			
              </td>
              
 </tr>
 
 <tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left">
               Probation:&nbsp;<b><bean:write name="intakeForm" property="intake.probationFlag" /></b>      			
              </td>
              <td valign="top" align="left" >
               Probation Officer:&nbsp;<b><bean:write name="intakeForm" property="intake.probationOfficer" /></b>      			
              </td>
              <td valign="top" align="left" >
               Probation Phone:&nbsp;<b><bean:write name="intakeForm" property="intake.probationOfficerPhone" /></b>      			
              </td>
 </tr>
 
 <tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left">
               Industrial Accident:&nbsp;<b><bean:write name="intakeForm" property="intake.industrialInjuryFlag" /></b>      			
              </td>
              <td valign="top" align="left" >
               Employer:&nbsp;<b><bean:write name="intakeForm" property="intake.industrialInjuryEmployer" /></b>      			
              </td>
              <td valign="top" align="left" >
               When:&nbsp;<b><bean:write name="intakeForm" property="intake.industrialInjuryDate" /></b>      			
              </td>
 </tr>
 
 <tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left">
               What Happened:&nbsp;<b><bean:write name="intakeForm" property="intake.industrialInjuryReason" /></b>      			
              </td>
              <td valign="top" align="left" colspan="2" >
               Claim Open:&nbsp;<b><bean:write name="intakeForm" property="intake.industrialInjuryClaimStatus" /></b>      			
              </td>
              
 </tr>
 
 
 <tr><td colspan="3" height="10"></td></tr>
 <tr>
              <td valign="top" align="left">
               <b>Employer</b>      			
              </td>
              <td valign="top" align="left" >
              <b>Duties</b>      			
              </td>
              <td valign="top" align="left" >
              <b>Dates</b>      			
              </td>
   </tr>
 
 <tr>
              <td valign="top" align="left">
               <bean:write name="intakeForm" property="intake.employer1Name" />      			
              </td>
              <td valign="top" align="left" >
               <bean:write name="intakeForm" property="intake.employer1Job" />    			
              </td>
              <td valign="top" align="left" >
               <bean:write name="intakeForm" property="intake.employer1Dates" />    			
              </td>              
 </tr>
 <tr>
              <td valign="top" align="left">
               <bean:write name="intakeForm" property="intake.employer2Name" />      			
              </td>
              <td valign="top" align="left" >
               <bean:write name="intakeForm" property="intake.employer2Job" />    			
              </td>
              <td valign="top" align="left" >
               <bean:write name="intakeForm" property="intake.employer2Dates" />    			
              </td>              
 </tr>
 <tr>
              <td valign="top" align="left">
               <bean:write name="intakeForm" property="intake.employer3Name" />      			
              </td>
              <td valign="top" align="left" >
               <bean:write name="intakeForm" property="intake.employer3Job" />    			
              </td>
              <td valign="top" align="left" >
               <bean:write name="intakeForm" property="intake.employer3Dates" />    			
              </td>              
 </tr>
 <tr>
              <td valign="top" align="left">
               <bean:write name="intakeForm" property="intake.employer4Name" />      			
              </td>
              <td valign="top" align="left" >
               <bean:write name="intakeForm" property="intake.employer4Job" />    			
              </td>
              <td valign="top" align="left" >
               <bean:write name="intakeForm" property="intake.employer4Dates" />    			
              </td>              
 </tr>
 
 </table>
 
 
<body>
</body>
</html>