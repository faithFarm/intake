<%@ page import="org.faithfarm.sms.domain.Intake" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/profile.css"  />
<title>Faith Farm Ministries Online Student Application</title>

  	<script language="javascript">
	function printPage()
	{
	window.print();
	}
	
</script>

<style>
body {
	font-size:.80em;
	font-family: Arial;
	word-wrap:break-word;
}

p {}
.break-word {
  word-wrap: break-word;
}
	
</style>
</head>
<body background="#FFFFFF" onLoad="javascript:print();">

<%
			Intake intake =  (Intake)session.getAttribute("PRINT_INTAKE");
			String name = (String)session.getAttribute("PRINT_INTAKE_NAME");
			String farm =  (String)session.getAttribute("PRINT_INTAKE_FARM");
			String phone =  (String)session.getAttribute("PRINT_INTAKE_PHONE");
%>

<body onLoad="javascript:printPage();" topmargin="0" >


<font size="6"><%=farm %> Online Intake Application</font></br>
<font size="5"><b>Name:</b><i><%=name %>&nbsp;&nbsp;(<%=phone %>)</i></font></br>

<p>
Last Name :&nbsp;<b><bean:write name="intakeForm" property="intake.lastname" /></b></br>      			
First Name :&nbsp;<b><bean:write name="intakeForm" property="intake.firstname" /></b></br>
MI :&nbsp;<b><bean:write name="intakeForm" property="intake.mi" /></b></br>
SSN :&nbsp;<b><bean:write name="intakeForm" property="intake.ssn" /></b></br>
Referred By:&nbsp;<b><bean:write name="intakeForm" property="intake.referredBy" /></b></br>    			
Current Address:&nbsp;<b><bean:write name="intakeForm" property="intake.address" /></b></br>    			
City:&nbsp;<b><bean:write name="intakeForm" property="intake.city" /></b></br>    			
State:&nbsp;<b><bean:write name="intakeForm" property="intake.state" /></b></br>
Zip:&nbsp;<b><bean:write name="intakeForm" property="intake.zipcode" /></b></br>
Phone Number:&nbsp;<b><bean:write name="intakeForm" property="intake.emergencyPhone" /></b></br>    			
Contact Person:&nbsp;<b><bean:write name="intakeForm" property="intake.emergencyContact" /></b></br>
Relationship:&nbsp;<b><bean:write name="intakeForm" property="intake.emergencryRelationship" /></b></br>
Date of Birth:&nbsp;<b><bean:write name="intakeForm" property="intake.dob" /></b></br>    			
Age:&nbsp;<b><bean:write name="intakeForm" property="intake.age" /></b></br>    			
Height:&nbsp;<b><bean:write name="intakeForm" property="intake.height" /></b></br>
Weight:&nbsp;<b><bean:write name="intakeForm" property="intake.weight" /></b></br>
Eyes:&nbsp;<b><bean:write name="intakeForm" property="intake.eyeColor" /></b></br>
Hairy:&nbsp;<b><bean:write name="intakeForm" property="intake.hairColor" /></b></br>
Ethnic Background:&nbsp;<b><bean:write name="intakeForm" property="intake.ethnicity" /></b></br>    			
Marital Status:&nbsp;<b><bean:write name="intakeForm" property="intake.maritalStatus" /></b></br>
Presently Living:&nbsp;<b><bean:write name="intakeForm" property="intake.homeLocation" /></b></br>
Schooling Completed:&nbsp;<b><bean:write name="intakeForm" property="intake.educationLevel" /></b></br>    			
Valid Driver License:&nbsp;<b><bean:write name="intakeForm" property="intake.dlFlag" /></b></br>    			
State:&nbsp;<b><bean:write name="intakeForm" property="intake.dlState" /></b></br>    			
DL Number:&nbsp;<b><bean:write name="intakeForm" property="intake.dlNumber" /></b></br>    			
Father Still Living:&nbsp;<b><bean:write name="intakeForm" property="intake.fatherLivingFlag" /></b></br>    			
Relationship with father:&nbsp;<b><bean:write name="intakeForm" property="intake.relationshipWithFather" /></b></br>    			
Mother Still Living:&nbsp;<b><bean:write name="intakeForm" property="intake.motherLivingFlag" /></b></br>    			
Relationship with mother:&nbsp;<b><bean:write name="intakeForm" property="intake.relationshipWithMother" /></b></br>    			
Number of Brothers:&nbsp;<b><bean:write name="intakeForm" property="intake.brothers" /></b></br>    			
Number of Sisters:&nbsp;<b><bean:write name="intakeForm" property="intake.sisters" /></b></br>    			
Number of Children:&nbsp;<b><bean:write name="intakeForm" property="intake.children" /></b></br>   
Homeless How Often:&nbsp;<b><bean:write name="intakeForm" property="intake.homelessHowOften" /></b></br>   
Homeless Reason:&nbsp;<b><bean:write name="intakeForm" property="intake.homelessReason" /></b></br>   
Homeless Time:&nbsp;<b><bean:write name="intakeForm" property="intake.homelessTime" /></b></br>   
Spiritual Background:&nbsp;<b><bean:write name="intakeForm" property="intake.religion" /></b></br>    			
Spiritual Experience:&nbsp;<b><bean:write name="intakeForm" property="intake.religiousExperience" /></b></br>    			
General health:&nbsp;<b><bean:write name="intakeForm" property="intake.currentHealth" /></b></br>
alcoholLastUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.alcoholLastUsed" /></b></br>
alcoholYearsUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.alcoholYearsUsed" /></b></br>
cocaineLastUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.cocaineLastUsed" /></b></br>
cocaineYearsUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.cocaineYearsUsed" /></b></br>
heroinLastUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.heroinLastUsed" /></b></br>
heroinYearsUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.heroinYearsUsed" /></b></br>
marijuanaLastUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.marijuanaLastUsed" /></b></br>
marijuanaYearsUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.marijuanaYearsUsed" /></b></br>
oxycodoneLastUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.oxycodoneLastUsed" /></b></br>
oxycodoneYearsUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.oxycodoneYearsUsed" /></b></br>
speedYearsUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.speedYearsUsed" /></b></br>
speedLastUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.speedLastUsed" /></b></br>
xanaxLastUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.xanaxLastUsed" /></b></br>
xanaxYearsUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.xanaxYearsUsed" /></b></br>
otherYearsUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.otherYearsUsed" /></b>
otherLastUsed:&nbsp;<b><bean:write name="intakeForm" property="intake.otherLastUsed" /></b></br>
Medical Condition:&nbsp;<b></b></br>    			
If so, what:&nbsp;<b></b></br>    			
Felony:&nbsp;<b><bean:write name="intakeForm" property="intake.felonyFlag" /></b></br>    			
How Many:&nbsp;<b><bean:write name="intakeForm" property="intake.felonyQty" /></b></br>    			
Name them:&nbsp;<b><bean:write name="intakeForm" property="intake.felonyDetails" /></b></br>    			
Probation:&nbsp;<b><bean:write name="intakeForm" property="intake.probationFlag" /></b></br>    			
Probation Officer:&nbsp;<b><bean:write name="intakeForm" property="intake.probationOfficer" /></b></br>    			
Probation Phone:&nbsp;<b><bean:write name="intakeForm" property="intake.probationOfficerPhone" /></b></br> 
Probation County:&nbsp;<b><bean:write name="intakeForm" property="intake.probationCounty" /></b></br> 
Probation State:&nbsp;<b><bean:write name="intakeForm" property="intake.probationState" /></b></br>    			
Industrial Accident:&nbsp;<b><bean:write name="intakeForm" property="intake.industrialInjuryFlag" /></b></br>    			
Employer:&nbsp;<b><bean:write name="intakeForm" property="intake.industrialInjuryEmployer" /></b></br>    			
When:&nbsp;<b><bean:write name="intakeForm" property="intake.industrialInjuryDate" /></b></br>    			
What Happened:&nbsp;<b><bean:write name="intakeForm" property="intake.industrialInjuryReason" /></b></br>    			
Claim Open:&nbsp;<b><bean:write name="intakeForm" property="intake.industrialInjuryClaimStatus" /></b></br>    			
Employer #1<b><bean:write name="intakeForm" property="intake.employer1Name" /></b> </br>     			
Duties #1<b><bean:write name="intakeForm" property="intake.employer1Job" /></b></br>	
Dates #1<b><bean:write name="intakeForm" property="intake.employer1Dates" /></b></br> 			
Employer #2<b><bean:write name="intakeForm" property="intake.employer2Name" /></b></br>    			
Duties #2<b><bean:write name="intakeForm" property="intake.employer2Job" /></b></br>		
Dates #2<b><bean:write name="intakeForm" property="intake.employer2Dates" /></b></br>			
Employer #3<b><bean:write name="intakeForm" property="intake.employer3Name" /></b></br>     			
Duties #3<b><bean:write name="intakeForm" property="intake.employer3Job" /></b></br>		
Dates #3<b><bean:write name="intakeForm" property="intake.employer3Dates" /></b> </br>			
Employer #4<b><bean:write name="intakeForm" property="intake.employer4Name" /></b></br>    			
Duties #4<b><bean:write name="intakeForm" property="intake.employer4Job" /></b></br>		
Dates #4<b><bean:write name="intakeForm" property="intake.employer4Dates" /></b></br>			
</p>

<body>
</body>
</html>