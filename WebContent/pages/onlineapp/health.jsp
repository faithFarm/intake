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
		<td ><b>Health Information: </b></td>
	</tr>

	<tr>
		<td >
    		<jsp:include page="messages.jsp" flush="true"/>
	   </td>
	</tr>
    </table>
    
    
    <table width="100%">
    	<logic:iterate id="loop" name="onlineAppForm" property="healthQuestions" indexId="i">
        <tr>
    	<td>
        	<bean:write name="loop" property="question"/>
        </td>
        <td>
   			<html:select property="healthAnswer[${i}]" styleClass="select" >
				<html:option value="">Select</html:option>
				<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
			</html:select>
			<br />
		</td>
        </tr>
        </logic:iterate>        
   </table>
   
   
   <table width="100%">
	<tr>
		<td ><br/><br/><b>Emotional Health:</b><br/><br/></td>
	</tr> 
    	<logic:iterate id="loop" name="onlineAppForm" property="emotionalQuestions" indexId="i">
        <tr>
    	<td>
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
        	<td >Please describe:&nbsp;
        	<html:text property="emotionalAnswerDetails[${i}]"  size="46" maxlength="45" />
        	</td>
        </tr>
        <tr><td  height="15"></td></tr>
        </logic:iterate>
    </table>
      
    <table width="100%">
	<tr>
		<td ><br/><b>Physical Health:</b><br/><br/></td>
	</tr>
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
    
    
<table width="100%">
          <logic:iterate id="loop" name="onlineAppForm" property="physicalQuestions" indexId="i">
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
    <br/>
    <table width="100%">
    <tr>
		<td ></br><b>Mental Health:</b></td>
	</tr>
	<tr>
		<td >
        <logic:iterate id="loop" name="onlineAppForm" property="mentalQuestions" indexId="i">
   
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
    </table>
    
    <table width="100%">   
    <tr>
		<td ><b>Physical Health History:</b></td>
	</tr>    
	<tr>
		<td >
        Have you ever had a state claim for an industrial injury?&nbsp;&nbsp;
 	   		            <html:select property="intake.industrialInjuryFlag" styleClass="select" >
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>

            </td>
       </tr>
       <tr>
       	<td >If yes, when&nbsp;
       		<html:text property="intake.industrialInjuryDate" size="15" maxlength="15" />
       </td>
       </tr>
       <tr>
       <td >Reason:&nbsp;
		       <html:text property="intake.industrialInjuryReason" size="25" maxlength="45" />
	   </td>
	   </tr>
       <tr>
       <td >Location&nbsp;
		       <html:text property="intake.industrialInjuryLocation" size="30" maxlength="40" />
	   </td>
	   </tr>
       <tr>
       <td >Employer:&nbsp;
		       <html:text property="intake.industrialInjuryEmployer" size="30" maxlength="40" />
	   </td>
	   </tr>
       
    </table>
    
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
    
	
	<table width="100%">
	<tr>
		<td ><b>Have you ever had any of the following:</b></td>
	</tr>

	<tr>
		<td style="padding-left:20px">
			<table cellpadding="0" cellspacing="0" border="0">
           
           <logic:iterate id="loop" name="onlineAppForm" property="medicalConditions" indexId="i">
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
                     <td style="padding-left:50px;"><i>For any "yes" above, explain details: 
                     	<html:text property="intake.medicalConditionDetails" size="60" maxlength="30" />
                     </td>
                </tr>
			</table> 			 					
		</td>
		<td></td>
		<td></td>
	</tr>
    
   <br/><br/>
   
   </td>
</tr>
</table>

</td>
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
	



	
<html:hidden property="pageSource" value="health"/>  
    <html:hidden property="nextStep" value="substance"/>
    <html:hidden property="previousStep" value="religious"/>   
</html:form>

<jsp:include page="footer.jsp" flush="true"/>