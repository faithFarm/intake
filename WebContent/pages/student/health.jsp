<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="org.faithfarm.sms.struts.form.IntakeForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="org.faithfarm.sms.domain.Question" %>
<%@ page import="org.faithfarm.sms.domain.MedicalCondition" %>

<jsp:include page="../../includes/header_info.jsp" flush="true"/>
 
<html:form  action="/Intake">
<div onKeyPress="return checkSubmit(event)">
    <table width="750" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td colspan="8"><b>Health Information: </b></td>
	</tr>

	<tr>
		<td colspan="11">
    		<jsp:include page="../../includes/messages.jsp" flush="true"/>
	   </td>
	</tr>
    
        
		<logic:iterate id="loop" name="intakeForm" property="healthQuestions" indexId="i">
        <tr>
    	<td colspan="7">
        	<bean:write name="loop" property="question"/>
        </td>
        <td>
   			<html:select property="healthAnswer[${i}]" styleClass="select" >
				<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
			</html:select>
			<br />
		</td>
        </tr>
        </logic:iterate>
        
   </table>
   <table width="750" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td colspan="8"><br/><br/><b>Emotional Health:</b><br/><br/></td>
	</tr> 
    	<logic:iterate id="loop" name="intakeForm" property="emotionalQuestions" indexId="i">
        <tr>
    	<td width="300">
        	<bean:write name="loop" property="question"/>
        </td>
        <td>
   			<html:select property="emotionalAnswer[${i}]" styleClass="select" >
				<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
			</html:select>
			<br />
		</td>
		<td width="100" align="left" >
			How Long Ago?
		</td>
		<td colspan="4">
			<html:text property="emotionalAnswerDate[${i}]" size="10" maxlength="10" />
		</td>
        </tr>
        <tr>
        	<td colspan="8">Please describe:&nbsp;
        	<html:text property="emotionalAnswerDetails[${i}]"  size="46" maxlength="45" />
        	</td>
        </tr>
        <tr><td colspan="8" height="15"></td></tr>
        </logic:iterate>
	<tr>
		<td colspan="8"><br/><b>Physical Health:</b><br/><br/></td>
	</tr>
	<tr>
		<td colspan="8">
        
        <table width="100%">
        <tr>
        <td>
		Describe your current health?&nbsp;
	 	   		        <html:select property="intake.currentHealth" styleClass="select" >
	 	   		        	<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_health" value="value" label="label" />
						</html:select>

         </td>
         </tr>
         <tr>
         <td>
		 Are you currently taking medications?&nbsp;
	 	   		        <html:select property="intake.medicationFlag" styleClass="select" >
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>

        </td>
        </tr>
        <tr>
        <td>
          <i>If yes, which meds:</i>&nbsp;&nbsp;
          <html:text property="intake.medicationDetails" size="40" maxlength="40" />
         </td>
         </tr>
        <tr>
         <td>
		Are you in need of medication?&nbsp;
	 	   		            <html:select property="intake.medicationFlag" styleClass="select" >
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>

        </td>
        </tr>
         <tr>
         <td>
        Do you have enough for 30 days?&nbsp;
	 	   		            <html:select property="intake.medicationSupplyFlag" styleClass="select" >
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>

        </td>
        </tr>
        <tr>
        <td>
          If yes, How do you get refill?&nbsp;&nbsp;
          <html:text property="intake.medicationRefillDetails" size="40" maxlength="40" />
         </td>
         </tr>
         <tr>
         <td> 
			Do you have any doctor appointments in the next 30 days?&nbsp;
 	   		            <html:select property="intake.doctorApptFlag" styleClass="select" >
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>

             </td>
         </tr>
         <tr>
         	 <td>If yes, when?&nbsp;
         	 	<html:text property="intake.doctorApptDetails" size="25" maxlength="25" />
         	 </td>
         </tr>
         </table>
         </td>
         </tr>
         
         <tr>
         <td colspan="8">
         
         <table width="100%">
          <logic:iterate id="loop" name="intakeForm" property="physicalQuestions" indexId="i">
         <tr>
         <td>
         <bean:write name="loop" property="question"/>&nbsp;&nbsp;
          	<html:select property="physicalAnswer[${i}]"  styleClass="select" >
				<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
			</html:select>

         </td>
         </tr>
         <tr>
         <td>
              If yes, give diagnosis&nbsp;
              <html:text property="physicalAnswerDetails[${i}]"  size="45" maxlength="45" />
                                   
          </td>
         </tr>
         </logic:iterate>
         </table>
		
        </td>
        </tr>
         <tr>
         <td colspan="8">
         	<table width="100%">
            <tr>
            <td width="300">
                Do you wear glasses or contact lens?&nbsp;
    	   		            <html:select property="intake.eyewearFlag" styleClass="select" >
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>
		&nbsp;&nbsp;&nbsp;
        </td>
        <td>
		How Often?&nbsp;
 	   		            <html:select property="intake.eyewearUsage" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_eyewear" value="value" label="label" />
						</html:select>

       </td>
       </tr>
     
       </table>
       </td>
		
	</td>
	</tr>
    <tr>
         <td colspan="8"></td>
    </tr>
    <tr>
		<td colspan="8"></br><b>Mental Health:</b></td>
	</tr>
	<tr>
		<td colspan="8">
        <logic:iterate id="loop" name="intakeForm" property="mentalQuestions" indexId="i">
   
           <bean:write name="loop" property="question"/>&nbsp;&nbsp;&nbsp;&nbsp;
	            <html:select property="mentalAnswer[${i}]" styleClass="select" >
					<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
				</html:select>
			<br />
            If yes, when&nbsp;
            <html:text property="mentalAnswerDate[${i}]" size="15" maxlength="15" />
            &nbsp;&nbsp;Details:&nbsp;
            <html:text property="mentalAnswerDetails[${i}]" size="40" maxlength="45" />
            <br /><br />
		</logic:iterate>
        </td>
      </tr>
        
    <tr>
		<td colspan="11"><b>Physical Health History:</b></td>
	</tr>    
	<tr>
		<td colspan="8">
        Have you ever had a state claim for an industrial injury?&nbsp;&nbsp;
 	   		            <html:select property="intake.industrialInjuryFlag" styleClass="select" >
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>

            </td>
       </tr>
       <tr>
       	<td colspan="8">If yes, when&nbsp;
       		<html:text property="intake.industrialInjuryDate" size="15" maxlength="15" />
       </td>
       </tr>
       <tr>
       <td colspan="8">Reason:&nbsp;
		       <html:text property="intake.industrialInjuryReason" size="25" maxlength="45" />
	   </td>
	   </tr>
       <tr>
       <td colspan="8">Location&nbsp;
		       <html:text property="intake.industrialInjuryLocation" size="30" maxlength="40" />
	   </td>
	   </tr>
       <tr>
       <td colspan="8">Employer:&nbsp;
		       <html:text property="intake.industrialInjuryEmployer" size="30" maxlength="40" />
	   </td>
	   </tr>
       
    <tr>
		<td colspan="8">&nbsp;</td>
	</tr>
    
    <tr>
		<td colspan="8">
        
        <table width="100%">
        <tr>
        <td>
        Do you have any current physical disability?&nbsp;&nbsp;
   	   		            <html:select property="intake.disabilityFlag" styleClass="select" >
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>
            </td>
       </tr>
       <tr>
       <td>
           If yes, date of last examination&nbsp;
           <html:text property="intake.disabilityExaminationDate" size="15" maxlength="15" />
       </td>
       </tr>
       <tr>
       <td>
           Physician's Name&nbsp;
           <html:text property="intake.disabilityPhysician" size="25" maxlength="45" />
       </td>
       </tr>
       <tr>
       <td>
           Address&nbsp;
           <html:text property="intake.disabilityPhysicianAddress" size="45" maxlength="45" />
        </td>
		</tr>
        </table>
        </td>
        </tr>
    <tr>
		<td colspan="8">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="8"><b>Have you ever had any of the following:</b></td>
	</tr>

	<tr>
		<td colspan="8">
			<table width="750" cellpadding="0" cellspacing="0" border="0">
           
           <logic:iterate id="loop" name="intakeForm" property="medicalConditions" indexId="i">
   			<tr>
					
					<td width="150"><bean:write name="loop" property="description"/></td>	
					<td>
						<html:select property="medicalCondition[${i}]"  styleClass="select" >
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>
					</td>
					</tr>
					<logic:equal name="loop" property="description" value="Hernia/Rupture">
								<tr>
								<td colspan="2" style="padding-left:20px;"><i>Which side?</i>
											
									<html:select property="intake.herniaSide" styleClass="select" >
										<html:option value="">Select</html:option>
										<html:optionsCollection name="ddl_herniaside" value="value" label="label" />
									</html:select>	
								</td>
								
							</tr>
			                <tr>
			                	<td colspan="2" style="padding-left:20px;"><i>Was it operated on?</i>
										
									<html:select property="intake.herniaOperationFlag" styleClass="select" >
										<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
									</html:select>
								</td>
							</tr>
			                
							<tr>
								<td colspan="2" style="padding-left:20px;"><i>Date:</i> 
								<html:text property="intake.herniaDate" size="30" maxlength="30" />
							</tr>
			                <tr>
			                    <td colspan="2" style="padding-left:20px;"><i>Physician:</i> 
			                    <html:text property="intake.herniaDetails" size="30" maxlength="30" />
			                </tr>
  				</logic:equal>
				
				</logic:iterate>
	
	            <tr>
                     <td colspan="8 style="padding-left:50px;"><i>For any "yes" above, explain details: 
                     <html:text property="intake.medicalConditionDetails" size="60" maxlength="30" />
                </tr>
			</table> 			 					
		</td>
		<td></td>
		<td></td>
	</tr>
    
    <tr>
		<td colspan="8" valign="bottom" align="center" height="45">
    	   <input type="submit" name="action" value="Save" class="imageButtonSave" title="Save Information" />&nbsp;
        </td>
	</tr>

    </table>
    
    <html:hidden property="pageSource" value="health"/> 
</div>
</html:form>
    <div class="footer">
        
    </div>
   

</body>
</html>