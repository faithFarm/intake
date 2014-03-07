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



<jsp:include page="../../includes/header_info.jsp" flush="true"/>
 
<tr>
 			<td bgcolor="#ffffff" align="center">

<div class="interior-mid">
  <div class="interior-text-mid">
 
<script language="javascript" type="text/javascript">


function ucase(obj) {
  obj.value=obj.value.toUpperCase();
}

function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }

function mask(val){  
	tel='';  
	for(var i=0;i<val.length;i++){  
	if(i<3){  
	    val[i]='*'  
	}  
	if(i==2 || i==5){val[i]='*-'}  
	if(i<5 && i>=3 ){  
	    val[i]='*'}  
	    teltel=tel+val[i]  
	}  
	f.value=tel;  
	alert('>'+val);
	
	}  
</script>

<html:form action="/Intake" enctype="multipart/form-data" styleId="intake">
<div onKeyPress="return checkSubmit(event);">
	<% String sTitle="Student Electronic File"; %>
	<table width="100%">
	<tr>
		<logic:equal name="intakeForm" property="intake.applicationStatus" value="Pending">
			<% sTitle="Online Pending Application"; %>
		</logic:equal>
		<logic:equal name="intakeForm" property="intake.applicationStatus" value="Accepted">
			<% sTitle="Online Accepted Application"; %>
		</logic:equal>
		<logic:equal name="intakeForm" property="intake.applicationStatus" value="Withdrawn">
			<% sTitle="Online Withdrawn Application"; %>
		</logic:equal>
		<logic:equal name="intakeForm" property="intake.applicationStatus" value="Denied">
			<% sTitle="Online Denied Application"; %>
		</logic:equal>
		<logic:equal name="intakeForm" property="intake.applicationStatus" value="Waitlist">
			<% sTitle="Online Waitlisted Application"; %>
		</logic:equal>
	
		<td valign="top">
				<font size="5"><b><bean:write name="intakeForm" property="intake.farmBase" /> <%=sTitle %></b></font>
				</br>
				
				<logic:equal name="intakeForm" property="intake.applicationStatus" value="Pending">
				 <table width="100%" border="0">
	             <tr>
	                 <td width="250">Application Response Status&nbsp;
	                 	<html:select property="intake.responseStatus" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_responseStatus" value="value" label="label" />
						</html:select>
	                 </td>
	               </tr>
	             </table>
	             </logic:equal>
	             
	             <logic:equal name="intakeForm" property="intake.applicationStatus" value="Denied">
				 <table width="100%" border="0">
	             <tr>
	                 <td width="250">Application Response Status&nbsp;
	                 	<html:select property="intake.responseStatus" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_responseStatus" value="value" label="label" />
						</html:select>
	                 </td>
	               </tr>
	             </table>
	             </logic:equal>
	             
	             <logic:equal name="intakeForm" property="intake.applicationStatus" value="Waitlist">
				 <table width="100%" border="0">
	             <tr>
	                 <td width="250">Application Response Status&nbsp;
	                 	<html:select property="intake.responseStatus" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_responseStatus" value="value" label="label" />
						</html:select>
	                 </td>
	               </tr>
	             </table>
	             </logic:equal>
	             
	             <logic:equal name="intakeForm" property="intake.applicationStatus" value="Accepted">
				 <table width="100%" border="0">
	             <tr>
	                 <td width="250">Application Response Status&nbsp;
	                 	<html:select property="intake.responseStatus" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_responseStatus" value="value" label="label" />
						</html:select>
						&nbsp;&nbsp;&nbsp;
						Expected Arrival Date&nbsp;	
					 	<html:text property="intake.arrivalDate" size="15" maxlength="10" styleClass="tcal" />
               
	                 </td>
	               </tr>
	             </table>
	             </logic:equal>
	             
	             <logic:equal name="intakeForm" property="intake.applicationStatus" value="Withdrawn">
				 <table width="100%" border="0">
	             <tr>
	                 <td width="250">Application Response Status&nbsp;
	                 	<html:select property="intake.responseStatus" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_responseStatus" value="value" label="label" />
						</html:select>
	                 </td>
	               </tr>
	             </table>
	             </logic:equal>
	            
		</td>
		<td align="right">
	        		<a href="<%=request.getContextPath()%>/Intake.do?action=PrintFull" style="text-decoration:none;"><img src="<%=request.getContextPath()%>/images/local/print.jpg" border="0"/></a>
	    </td>       
	</tr>
	</table>
	
	
	<logic:equal name="intakeForm" property="intake.reapplyFlag" value="Yes">
	<table width="750">
	<tr>
		<td >
		
			<table width="98%" style="background:#f8b81f;border-color:#986f0b;border: 1px solid black;">
			<tr>
				<td width="35"><img src="<%=request.getContextPath() %>/images/local/icon_warning.jpg"/></td>
				<td>
					<p>
						&nbsp;&nbsp;<font style="color:#000000;size=4;"><b>This application is for a returning student or previous applicant.</b></font>
					</p>
				</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	</logic:equal>
	
    <table width="750">
	<tr>
		<td colspan="8"><b>Personal Information: </b></td>
	</tr>

    <tr>
		<td colspan="11">
    		<jsp:include page="../../includes/messages.jsp" flush="true"/>
	   </td>
	</tr>
	</table>
	             <table width="250" border="0">
                <tr>
                	<td>
                			<logic:notEmpty name="intakeForm" property="intake.imageHeadshot">
                		   		<img src="<%=request.getContextPath()%>/Image.do" style="height:auto; width:250px;/>
                		    </logic:notEmpty>
                	        <logic:empty name="intakeForm" property="intake.imageHeadshot">
                	       		<img src="<%=request.getContextPath()%>/images/local/person.jpg" width="200" height="133"/>
                	       </logic:empty>
                	</td>
                </tr>
                </table>
               
                <table>
                <tr>	
                	<td><a href=""></a>				
                		<logic:equal name="loginForm" property="systemUser.farmBase" value="Boynton Beach" >
							<logic:equal name="intakeForm" property="intake.farmBase" value="Boynton Beach">
							 	<a href="#" onclick="javascript:document.getElementById('uploadButton').click();"><img src="<%=request.getContextPath() %>/images/local/uploadIcon.png" border="0"/></a>
                	    		<html:file property="imageFile" styleClass="imageButtonUpload" styleId="uploadButton" onchange="javascript:document.getElementById('uploadFileFlag').value='Yes';document.getElementById('intake').submit();" />
                       		</logic:equal>
						</logic:equal>

                	    <logic:equal name="loginForm" property="systemUser.farmBase" value="Fort Lauderdale" >
							<logic:equal name="intakeForm" property="intake.farmBase" value="Fort Lauderdale">
							 	<a href="#" onclick="javascript:document.getElementById('uploadButton').click();"><img src="<%=request.getContextPath() %>/images/local/uploadIcon.png" border="0"/></a>
                	    		<html:file property="imageFile" styleClass="imageButtonUpload" styleId="uploadButton" onchange="javascript:document.getElementById('uploadFileFlag').value='Yes';document.getElementById('intake').submit();" />
                       		</logic:equal>
						</logic:equal>

                	    <logic:equal name="loginForm" property="systemUser.farmBase" value="Okeechobee" >
							<logic:equal name="intakeForm" property="intake.farmBase" value="Okeechobee">
							 	<a href="#" onclick="javascript:document.getElementById('uploadButton').click();"><img src="<%=request.getContextPath() %>/images/local/uploadIcon.png" border="0"/></a>
                	    		<html:file property="imageFile" styleClass="imageButtonUpload" styleId="uploadButton" onchange="javascript:document.getElementById('uploadFileFlag').value='Yes';document.getElementById('intake').submit();" />
                       		</logic:equal>
						</logic:equal>

                	    <logic:equal name="loginForm" property="systemUser.farmBase" value="Women's Home" >
							<logic:equal name="intakeForm" property="intake.farmBase" value="Women's Home">
							 	<a href="#" onclick="javascript:document.getElementById('uploadButton').click();"><img src="<%=request.getContextPath() %>/images/local/uploadIcon.png" border="0"/></a>
                	    		<html:file property="imageFile" styleClass="imageButtonUpload" styleId="uploadButton"  onchange="javascript:document.getElementById('uploadFileFlag').value='Yes';document.getElementById('intake').submit();" />
                       		</logic:equal>
						</logic:equal>
                	</td>
                </tr>
                </table>
                
                <table>
                <tr>
                <td>Last Name</td>
                <td><html:text property="intake.lastname" size="30" maxlength="30" /></td>
                <td>&nbsp;&nbsp;</td>
                <td>First Name</td>
                <td><html:text property="intake.firstname" size="30" maxlength="30" /></td>
                <td>&nbsp;&nbsp;</td>
                <td>MI</td>
                <td colspan="2" align="left"><html:text property="intake.mi" size="3" maxlength="1" /></td>
                </td>
            </tr>
            </table>
      	<table width="100%">
		<tr>
	
	</tr>
	</table>
                <table width="100%" border="0">
                <tr>
                <td>SSN</td>
                <td>
                		<% if (  "hlidwell".equals(user.getUsername())||"rratliff".equals(user.getUsername())||"sjohnson".equals(user.getUsername())||"mmurphy".equals(user.getUsername())||
		            		"vandres".equals(user.getUsername())||"agorrin".equals(user.getUsername())||"pzielinski".equals(user.getUsername())||"robbinj".equals(user.getUsername())) { %>
			               <b><html:text styleId="ssn" property="intake.ssn" size="30" maxlength="11" onkeypress="return maskSsn(event,this)" /></b>
			            <% } else { %>
			               <b><bean:write name="intakeForm" property="intake.encryptedSsn" /></b>
			            <% } %>
               
                </td>
                <td>&nbsp;&nbsp;</td>
                <td >Referred to Faith Farm By</td>
                <td>
                    <html:text property="intake.referredBy" size="30" maxlength="40" />
                </td>
           		</tr>
                </table>
    
                <table width="100%" border="0">
                <tr>
                <td>Current Address&nbsp;<html:text property="intake.address" size="30" maxlength="45"  /></td>
                <td>City&nbsp;<html:text property="intake.city" size="20" maxlength="25"  />
               <td>State
                <html:select property="intake.state" styleClass="select" >
					<html:option value="">Select</html:option>
					<html:optionsCollection name="ddl_state" value="value" label="label" />
				</html:select>
                </td>
                <td>Zipcode<html:text property="intake.zipcode" size="20" maxlength="11" />
                </td>
                </tr>
            </table>
   
                <table width="100%" border="0">
                <tr>
                <td width="150">Phone Number&nbsp;&nbsp;
                	<html:text property="intake.referredByPhone" size="20" maxlength="12" onkeypress="return maskPhone(event,this)"  />
                </td>
                <td width="200">Email Address&nbsp;&nbsp;
                	<html:text property="intake.emailAddress" size="40" maxlength="80"  />
                </td>
                </tr>
                </table>
                
                <table width="60%" border="0">
                <tr>
                <td width="200">Date of Birth&nbsp;<i>(mm/dd/yyyy)</i>&nbsp;
                	<html:text property="intake.dob" size="15" maxlength="10" styleClass="tcal" />
                </td>
                <td>Age&nbsp;<html:text property="intake.age" size="2"  maxlength="2" onkeypress="return isNumberKey(event)"/></td>
                <td>
					Gender:
					<html:radio property="intake.gender" styleClass="select" value="male" onclick="toggle_visibility('block');" />Male&nbsp;&nbsp;&nbsp;
					<html:radio property="intake.gender" styleClass="select" value="female" onclick="toggle_visibility('none');"/>Female
					<br/><br/>
				</td>
                </tr>                
                </table>
   
                <table width="100%" border="0">
               
                <tr>
                <td>Emergency Contact&nbsp;<html:text property="intake.emergencyContact"   /> </td>
                <td>Relationship&nbsp;<html:text property="intake.emergencryRelationship"  /></td>
                 <td>Emergency Phone&nbsp;&nbsp;<html:text property="intake.emergencyPhone" size="20" maxlength="12" onkeypress="return maskPhone(event,this)" /></td>
               </tr>               
                </table>
       
	        <table width="100%" border="0">
                <tr>
                    <td width="250">Marial Status&nbsp;
                    	<html:select property="intake.maritalStatus" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_maritalstatus" value="value" label="label" />
						</html:select>
                    </td>
                    <td>Ethnicity
		                 <html:select property="intake.ethnicity" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_ethnicity" value="value" label="label" />
						</html:select>
                    </td>
                </tr>
             </table>
           
        <table width="100%">
        <tr>
        <td>Height&nbsp;<html:text property="intake.height" size="5" /></td>
		<td>Weight&nbsp;<html:text property="intake.weight" size="6" /></td>
		<td>Eyes Color&nbsp;
        		        <html:select property="intake.eyeColor" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_eyecolor" value="value" label="label" />
						</html:select>
        </td>
		<td>Hair Color&nbsp;
    		            <html:select property="intake.hairColor" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_haircolor" value="value" label="label" />
						</html:select>
        </td>
        </tr>
        
        </table>
      
        <table width="100%">
        <tr>
        <td>Where do you live?
				&nbsp;&nbsp;
    		            <html:select property="intake.homeLocation" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_living" value="value" label="label" />
						</html:select>
		</td>
		<td>Schooling Completed?
        		&nbsp;
    		            <html:select property="intake.educationLevel" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_education" value="value" label="label" />
						</html:select>

		</td>
		<td>Need GED?
        		&nbsp;
    		            <html:select property="intake.needGed" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>

		</td>		
        </tr>
       
         </table>
      
        <table width="100%">
        <tr>
        <td>Did you graduate?&nbsp;
     		            <html:select property="intake.graduateFlag" styleClass="select" >
							
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select></td>
		<td>Read English? &nbsp;
	   		            <html:select property="intake.englishReadingFlag" styleClass="select" >
							
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>
		</td>
		<td>Speak English?&nbsp;
	   		            <html:select property="intake.englishSpeakingFlag" styleClass="select" >
							
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>
		</td>
        </tr>
        
        </table>
   
   <table>
     <tr>
		<td colspan="8"><b>Current Income?</b><html:text property="intake.incomeAmount" size="10" maxlength="10"/>&nbsp;&nbsp;<b>Check One:</b>&nbsp;
			<input type="checkbox" property="intake.incomeWeeklyFlag" value="Yes" />Weekly&nbsp;&nbsp;
            <input type="checkbox" property="intake.incomeBiWeeklyFlag" value="Yes"  />Bi-Weekly&nbsp;&nbsp;
            <input type="checkbox" property="intake.incomeMonthlyFlag" value="Yes" />Monthly&nbsp;&nbsp;
            <input type="checkbox" property="intake.incomeYearlyFlag" value="Yes"  />Yearly
         </td>
     </tr>
    
	<tr> 
		<td colspan="8">Source(s)?&nbsp;&nbsp;
			<html:text property="intake.incomeSource"   />
        </td>	
    </tr>

    <tr>
		<td colspan="8"><b>Do you receive government benefits?</b>&nbsp;&nbsp;
			<html:checkbox property="intake.ssFlag" value="Yes"   />Social Security&nbsp;&nbsp;
            <html:checkbox property="intake.vaFlag" value="Yes"  />VA&nbsp;&nbsp;
            <html:checkbox property="intake.wcFlag" value="Yes" />Workman's Comp
            <html:checkbox property="intake.foodStampFlag" value="Yes" />SNAP Nutrition Assistance (food Stamps)
         </td>
     </tr>
    
	<tr>
		<td colspan="8">Other benefits?&nbsp;&nbsp;
		<html:text property="intake.otherBenefits" size="20" maxlength="20" />
        </td>	
    </tr>
    </table>
    
    
     	<table width="100%">
        <tr>
        <td width="220">
        <b>Are you a US Veteran?</b> &nbsp;
	   		            <html:select property="intake.veteranStatus" styleClass="select" >
							
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>
		</td>
        <td>Branch of Service? &nbsp;
	   		            <html:select property="intake.branchOfService" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_military" value="value" label="label" />
						</html:select>
		</td>
         </tr>
      
        </table>
     
            <table width="100%">
            <tr>
            <td width="220">Highest Rank&nbsp;&nbsp;
                <html:text property="intake.rank" size="20" maxlength="20"  />
            </td>	
            <td >Length Of Service&nbsp;&nbsp;
                <html:text property="intake.lengthOfService" size="20" maxlength="20"  />
            </td>	
        </tr>
        
         </table>
    
     	<table width="100%">
        <tr>
        <td width="260">Have a Valid Driver's License?&nbsp;&nbsp;
        		   		<html:select property="intake.dlFlag" styleClass="select" >
							
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>
        </td>
        <td>
        	License/Id State
			   		   <html:select property="intake.dlState" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_state" value="value" label="label" />
						</html:select>
        </td>	
        <td>License/Id#:&nbsp;&nbsp;
			<html:text property="intake.dlNumber" size="22" maxlength="22"  />
        </td>	
       
    </tr>
    </table>
    
     	<table width="100%">
        <tr>
        <td width="260">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i>If no driver's license, do you have a valid government id?</i>&nbsp;&nbsp;
        		   		<html:select property="intake.stateIdFlag" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>
        	&nbsp;&nbsp;&nbsp;
        	<i>Id Type</i>
			   		   <html:select property="intake.stateIdType" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_stateIdType" value="value" label="label" />
						</html:select>
        </td>
	    </tr>
	    </table>
   
     	<table width="100%">
        <tr>
        <td width="90">
        	<b>Your Family:</b>&nbsp;&nbsp;</td>
            <td width="200">Is your Mother living?
          		            <html:select property="intake.motherLivingFlag" styleClass="select" >
							
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>
        </td>
           	<td>Is your Father living?
           		        <html:select property="intake.fatherLivingFlag" styleClass="select" >
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>
        	</td>
         </tr>
   </table>
     	<table width="100%">
        <tr>
        <td width="280">Relationship with Mother?&nbsp;&nbsp;
        			    <html:select property="intake.relationshipWithMother" styleClass="select" >
        			    <html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_relationship" value="value" label="label" />
						</html:select>
        &nbsp;&nbsp;&nbsp;&nbsp;</td>
    	<td>Relationship with Father?&nbsp;&nbsp;
    	           		<html:select property="intake.relationshipWithFather" styleClass="select" >
    	           		<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_relationship" value="value" label="label" />
						</html:select>
    	</td>
        <tr>
        
        </table>
   </table>
   <table>
    <tr>
    	<td >Number of Brothers&nbsp;&nbsp;<html:text property="intake.brothers" size="2"  onkeypress="return isNumberKey(event)" />&nbsp;&nbsp;&nbsp;&nbsp;
        				Number of Sisters&nbsp;&nbsp;<html:text property="intake.sisters" size="2"  onkeypress="return isNumberKey(event)" />&nbsp;&nbsp;&nbsp;&nbsp;
        				Number of Children&nbsp;&nbsp;<html:text property="intake.children" size="2" onkeypress="return isNumberKey(event)" />
    <tr>
		<td >&nbsp;</td>
	</tr>

    <tr>
		<td ><b>Homelessness Documentation:</b><br /></td>
	</tr>
    
     <tr>
		<td><br />How long have you been homeless?<br /></td>
      </tr>
      <tr>
      	<td>
      		<html:radio property="intake.homelessTime" value="Never" />&nbsp;Never&nbsp;&nbsp;
			<html:radio property="intake.homelessTime" value="Less than 2 weeks"  />&nbsp;Less than 2 weeks&nbsp;&nbsp;
            <html:radio property="intake.homelessTime" value="2 weeks to 1 month" />&nbsp;2 weeks to 1 month&nbsp;&nbsp;
            <html:radio property="intake.homelessTime" value="1 to 3 months" />&nbsp;1 to 3 months&nbsp;&nbsp;
            <html:radio property="intake.homelessTime" value="3 months to 1 year" />&nbsp;3 months to 1 year&nbsp;&nbsp;
            <html:radio property="intake.homelessTime" value="more than 1 year" />&nbsp;more than 1 year
         </td>
     </tr>
     <tr>
     	<td></td>
     </tr>
     <tr>
		<td><br />How often have you been homeless?<br /></td>
      </tr>
      <tr>
      	<td >
			<html:radio property="intake.homelessHowOften" value="Never" />&nbsp;Never&nbsp;&nbsp;
            <html:radio property="intake.homelessHowOften" value="1 to 2 times" />&nbsp;1 to 2 times&nbsp;&nbsp;
            <html:radio property="intake.homelessHowOften" value="more than 2 times in 2 years" />&nbsp;more than 2 times in 2 years&nbsp;&nbsp;
            <html:radio property="intake.homelessHowOften" value="Long Term" />&nbsp;Long term
        </td>
     </tr>
     <tr>
		<td ><br />Reason for homelessness:<br /></td>
      </tr>
      <tr>
      	<td >
			<html:radio property="intake.homelessReason" value="Lack of a fixed, regular and adequate night time residence." />&nbsp;Lack of a fixed, regular and adequate night time residence.<br />
            <html:radio property="intake.homelessReason" value="Primary night time residence is a shelter designed to provide temporary living accomodations (including welfar hotels, congregate shelters, and transitional housing for the mentally ill)." />&nbsp;Primary night time residence is a shelter designed to provide temporary living accomodations (including welfar hotels, congregate shelters, and transitional housing for the mentally ill).<br />
            <html:radio property="intake.homelessReason" value="Primary night time residence is an institution that provides a temporary residence for individuals intended to be institutionalized." />&nbsp;Primary night time residence is an institution that provides a temporary residence for individuals intended to be institutionalized.<br />
            <html:radio property="intake.homelessReason" value="Primary night time residence is a public or private place not designated for, or ordinarily used as a regular sleeping accomodation for human beings."/>&nbsp;Primary night time residence is a public or private place not designated for, or ordinarily used as a regular sleeping accomodation for human beings.
        </td>
     </tr>
     </table>
     
    </br>
    <table width="600">
    <tr>
    	<td><b>Case Notes:</b></td>
	</tr>
	<tr>
		<td>
			<html:textarea property="intake.caseNotes" cols="120" rows="10" styleClass="textarea" />
		</td>
	</tr>
	</table>
	
	<br/><br/>
	<div align="center">
		<logic:equal name="loginForm" property="systemUser.farmBase" value="Boynton Beach" >
			<logic:equal name="intakeForm" property="intake.farmBase" value="Boynton Beach">
				<input type="submit" name="action" value="Save" class="imageButtonSave" title="Save" id="action" />
			</logic:equal>
			<logic:notEqual name="intakeForm" property="intake.farmBase" value="Boynton Beach">
				<input type="submit" name="action" value="Save" class="imageButtonSave" title="Save" disabled/>
			</logic:notEqual>
		</logic:equal>
		
		<logic:equal name="loginForm" property="systemUser.farmBase" value="Okeechobee" >
			<logic:equal name="intakeForm" property="intake.farmBase" value="Okeechobee">
				<input type="submit" name="action" value="Save" class="imageButtonSave" title="Save" id="action" />
			</logic:equal>
			<logic:notEqual name="intakeForm" property="intake.farmBase" value="Okeechobee">
				<input type="submit" name="action" value="Save" class="imageButtonSave" title="Save" disabled/>
			</logic:notEqual>
		</logic:equal>
		
		<logic:equal name="loginForm" property="systemUser.farmBase" value="Fort Lauderdale" >
			<logic:equal name="intakeForm" property="intake.farmBase" value="Fort Lauderdale">
				<input type="submit" name="action" value="Save" class="imageButtonSave" title="Save" id="action" />
			</logic:equal>
			<logic:notEqual name="intakeForm" property="intake.farmBase" value="Fort Lauderdale">
				<input type="submit" name="action" value="Save" class="imageButtonSave" title="Save" disabled/>
			</logic:notEqual>
		</logic:equal>
		
		<logic:equal name="loginForm" property="systemUser.farmBase" value="Women's Home" >
			<logic:equal name="intakeForm" property="intake.farmBase" value="Women's Home">
				<input type="submit" name="action" value="Save" class="imageButtonSave" title="Save" id="action" />
			</logic:equal>
			<logic:notEqual name="intakeForm" property="intake.farmBase" value="Women's Home">
				<input type="submit" name="action" value="Save" class="imageButtonSave" title="Save" disabled/>
			</logic:notEqual>
		</logic:equal>
	</div>   			
       
    <html:hidden property="pageSource" value="personal"/>  
    <html:hidden property="uploadFileFlag" styleId="uploadFileFlag" />
    </div>  
</html:form>

<script language="javascript">
	mask(document.getElementById('ssn').value);
</script>

</div>
    