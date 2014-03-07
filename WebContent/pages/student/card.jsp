<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="org.faithfarm.sms.domain.SystemUser" %>
<%  
    SystemUser user = null;
	
	try {
	  user = (SystemUser)session.getAttribute("system_user"); 
	  if (user==null) user = new SystemUser();
	} catch (Exception e) {
		user=new SystemUser();
	}
%>


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
	font-size:.80em;
}
	
</style>
</head>
<body background="#FFFFFF" onLoad="javascript:print();">

<div align="left">
			<table width="700" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top" class="cardheading">
	                    	<b><bean:write name="intakeForm" property="intake.lastname" />,&nbsp;<bean:write name="intakeForm" property="intake.firstname" />&nbsp;<bean:write name="intakeForm" property="intake.mi" />&nbsp;<bean:write name="intakeForm" property="intake.suffix" /></b><br />
	            	</td>
		            <td valign="top" class="cardheading">
		            <% if ( "rratliff".equals(user.getUsername())||"sjohnson".equals(user.getUsername())||"mmurphy".equals(user.getUsername())||"hlidwell".equals(user.getUsername())||
		            		"vandres".equals(user.getUsername())||"agorrin".equals(user.getUsername())||"pzielinski".equals(user.getUsername())||"robbinj".equals(user.getUsername())) { %>
		               <b><bean:write name="intakeForm" property="intake.ssn" /></b>
		            <% } else { %>
		               <b><bean:write name="intakeForm" property="intake.encryptedSsn" /></b>
		            <% } %>
		            <td valign="top" class="cardheading">
		               <b><bean:write name="intakeForm" property="intake.dob" /></b>
		            </td>
	          </tr>
	          <tr>
				<td valign="top" class="cardheading">
	                    	Name(Last,First,MI)
	            </td>
	            <td valign="top" class="cardheading">
	                      Social Security Number
	            <td valign="top" class="cardheading">
	                      Birthdate
	            </td>
	          </tr>
	       </table>
         
 <table width="700" border="0" cellpadding="0" cellspacing="0">
 <tr>
              <td valign="top" align="left">
                    		<logic:notEmpty name="intakeForm" property="intake.imageHeadshot">
                		   		<img src="<%=request.getContextPath()%>/Image.do" width="200" height="133"/>
                		    </logic:notEmpty>
                	        <logic:empty name="intakeForm" property="intake.imageHeadshot">
                	       		<img src="<%=request.getContextPath()%>/images/local/person.jpg" width="200" height="133"/>
                	       </logic:empty>                    			
              </td>
              <td>
              <table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td colspan="6">
					  <table width="100%" cellpadding="0" cellspacing="0" >
						  <tr>
			              <td valign="top" width="200">
					               <b><bean:write name="intakeForm" property="intake.entryDate" /></b>
					      </td>
			              <td class="cardheading"  width="200">
			          	  	<b>
					              <logic:notEmpty name="intakeForm" property="intake.dlNumber">
			               		   		<bean:write name="intakeForm" property="intake.dlNumber" />	
			               		    </logic:notEmpty>
			               	        <logic:empty name="intakeForm" property="intake.dlNumber">
			               	       		 <b>None</b>
			               	      </logic:empty>      
			          	  	</b>
			          	  </td>
			          	  <td valign="top" class="cardheading"  width="200"> 
						  		<b><bean:write name="intakeForm" property="intake.maritalStatus" /></b>
						  </td>
						  </tr>
						  
						  <tr>
						  		<td>Entry Date</td>
						  		<td>Driver's License</td>
						  		<td>Marital Status</td>					  
						  </tr>
						  
						  <tr>
						  	<td height="10" colspan="3"></td>
						  </tr>
					  </table>
					  </td>
			   </tr>
			   <tr>
					  		<td  valign="top" class="cardheading"><b>Description</b></td>
					  		<td valign="top" width="10"></td>
					  		<td valign="top" class="cardheading"><b>Mailing Address</b></td>
							<td valign="top" width="10"></td>
							<td valign="top" class="cardheading">
								<table cellpadding=0 cellpspacing="10" border="0">
									<tr>
										<td valign="top" class="cardheading"><b>Employer</b></td>
										<td valign="top" class="cardheading"><b>Duties</b></td>
										<td valign="top" class="cardheading"><b>Time</b></td>
									</tr>
								 </table>
							</td>
						</tr>
						<tr>
							<td>
									<table cellpadding=0 cellpspacing="10" border="0">
									<tr>
							   			<td valign="top" class="cardheading"><b><bean:write name="intakeForm" property="intake.age" />&nbsp;&nbsp;<bean:write name="intakeForm" property="intake.weight" />&nbsp;&nbsp;<bean:write name="intakeForm" property="intake.height" /></b></td>
							   		</tr>
							   		<tr>
							   			<td>Age&nbsp;&nbsp;Weight&nbsp;&nbsp;Height<br/></td>
							   		</tr>
							   		<tr>
							   			<td valign="top" class="cardheading"><b><bean:write name="intakeForm" property="intake.eyeColor" />&nbsp;&nbsp;<bean:write name="intakeForm" property="intake.hairColor" /></b></td>
							   		</tr>
							   		<tr>
							   			<td>Eye Color&nbsp;&nbsp;Hair Color&nbsp;&nbsp;Race<br/></td>
							   		</tr>
							   		<tr>
							   			<td valign="top" class="cardheading"><b><bean:write name="intakeForm" property="intake.ethnicity" /></b></td>
							   		</tr>
							   		<tr>
							   			<td>Race</td>
							   		</tr>
							   		</table>
							</td>
							<td valign="top" width="10"></td>
							<td valign="top">
									<table cellpadding=0 cellpspacing="10" border="0">
									<tr>
										<td valign="top" valign="top"><bean:write name="intakeForm" property="intake.address" /></td>
									</tr>
									<tr>
										<td valign="top">
											<bean:write name="intakeForm" property="intake.city" />&nbsp;,
											<bean:write name="intakeForm" property="intake.state" />&nbsp;
											<bean:write name="intakeForm" property="intake.zipcode"/>
										</td>
									</tr>
									</table>
							</td>
							<td valign="top" width="10"></td>
							<td valign="top">
									<table cellpadding=0 cellpspacing="10" border="0">
									<tr>
										<td style="font:.80em;" valign="top"><bean:write name="intakeForm" property="intake.employer1Name" /></td>
										<td style="font:.80em;" valign="top"><bean:write name="intakeForm" property="intake.employer1Job" /></td>
										<td style="font:.80em;" valign="top"><bean:write name="intakeForm" property="intake.employer1Dates" /></td>
									</tr>
									<tr>
										<td style="font:.80em;" valign="top"><bean:write name="intakeForm" property="intake.employer2Name" /></td>
										<td style="font:.80em;" valign="top"><bean:write name="intakeForm" property="intake.employer2Job" /></td>
										<td style="font:.80em;" valign="top"><bean:write name="intakeForm" property="intake.employer2Dates" /></td>
									</tr>
								   </table>
						   </td>
					</tr>
			</table>   
						
						   
		</td>
  	</tr>
  	</table>
			  	
			  	
			  	<table width="700" border="0" cellpadding="0" cellspacing="0">
			  	<tr>
			  		<td valign="top" class="cardheading"><b>Family Info</b></td>
			  		<td valign="top" class="cardheading"><b>Health Info</b></td>
					<td valign="top" class="cardheading"><b>Prob Info</b></td>
					<td valign="top" class="cardheading"><b>Misc.</b></td>
					<td valign="top" class="cardheading"><b>Student History</b></td>
				</tr>
				<tr>
					<td valign="top" class="cardheading">
							<table cellpadding=0 cellpspacing="10" border="0">
							<tr>
								<td valign="top">
									<b><bean:write name="intakeForm" property="intake.emergencyContact" /></b>
								</td>
							</tr>
							<tr>
								<td>Next Of Contact<br/></td>
							</tr>
							<tr>
								<td><b><bean:write name="intakeForm" property="intake.emergencryRelationship" /></b></td>
							</tr>
							<tr>
								<td>How Related<br/></td>
							</tr>
							<tr>
								<td><b><bean:write name="intakeForm" property="intake.emergencyPhone" /></b></td>
							</tr>
							<tr>
								<td>Contact Phone</td>
							</tr>
							</table>
	              </td>
				  <td valign="top" class="cardheading">
							<table cellpadding=0 cellpspacing="10" border="0">
							<tr>
								<td valign="top">
									<b><bean:write name="intakeForm" property="intake.currentHealth" /></b>
								</td>
							</tr>
							<tr>
								<td>Health Condition<br/></td>
							</tr>
							<tr>
								<td><b><bean:write name="intakeForm" property="intake.medicationFlag" /></b></td>
							</tr>
							<tr>
								<td>Medications<br/></td>
							</tr>
							<tr>
								<td><b><bean:write name="intakeForm" property="intake.disabilityFlag" /></b></td>
							</tr>
							<tr>
								<td>Disabled</td>
							</tr>
							</table>
	              </td>
	              
	              <td valign="top" class="cardheading">
							<table cellpadding=0 cellpspacing="10" border="0">
							<tr>
								<td valign="top">
									<b><bean:write name="intakeForm" property="intake.probationOfficer" /></b>
								</td>
							</tr>
							<tr>
								<td>Prob. Officer<br/></td>
							</tr>							
							</table>
	              </td>
	              
	             <td valign="top" class="cardheading">
							<table cellpadding=0 cellpspacing="0" border="0">
							<tr>
								<td valign="top">
									<b><bean:write name="intakeForm" property="intake.religion" /></b>
								</td>
							</tr>
							<tr>
								<td>Religious Background<br/></td>
							</tr>
							<tr>
								<td><b><bean:write name="intakeForm" property="intake.usageLosses" /></b></td>
							</tr>
							<tr>
								<td>Personal Effects<br/></td>
							</tr>
							<tr>
								<td><b>
									<logic:equal name="intakeForm" property="intake.foodStampFlag" value="Yes">
									FOOD STAMPS,
									</logic:equal>
									<logic:equal name="intakeForm" property="intake.ssFlag" value="Yes">
									SOC-SEC,
									</logic:equal>
									<logic:equal name="intakeForm" property="intake.vaFlag" value="Yes">
									VA,
									</logic:equal>
									<logic:equal name="intakeForm" property="intake.wcFlag" value="Yes">
									WORKERS COMP.
									</logic:equal>
									</b></td>
							</tr>
							<tr>
								<td>Government Benefits</td>
							</tr>
							</table>
	              </td>
	              <td width="300" style="font-size:.75em;" valign="top">
	              		<table width="100%" cellpadding=0 cellpspacing="10" border="0">
	              			<tr>
	              				<td><b>Dates</b></td>
	              				<td><b>Program</b></td>
	              				<td><b>Location</b></td>
	              				<td><b>Status</b></td>	              				
	              			</tr>
	              			 <logic:iterate id="loop" name="intakeForm" property="studentHistory" indexId="i">
		                     <tr> 
		                     	<td><bean:write name="loop" property="beginDate"/>-<bean:write name="loop" property="endDate"/></td>
		                     	<td><bean:write name="loop" property="farm"/></td>
		                        <td><bean:write name="loop" property="phase"/></td>
		                        <td><bean:write name="loop" property="programStatus"/></td>
		                     </tr>
		                     </logic:iterate>
	                    </table>
	            </td>
	              
	              
				</tr>
				</table>
			  	
			  	
					      
					              			
          		</td>
          </tr>
         </table>
        
</div>
<body>
</body>
</html>

