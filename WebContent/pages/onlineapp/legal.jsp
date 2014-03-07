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
		<td><b>Legal Information:</b></td>
	</tr>
	
	<tr>
		<td>
    		<jsp:include page="messages.jsp" flush="true"/>
	   </td>
	</tr>
	</table>
	
	<table width="100%">
		<tr>
		<td>Have you ever been sued?&nbsp;
   			<html:select property="intake.lawsuitFlag" styleClass="select" >
				<html:option value="">Select</html:option>
				<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
			</html:select>

            &nbsp;&nbsp;&nbsp;If yes, when&nbsp;
            <html:text property="intake.lawsuitDate"  size="10" maxlength="10" />
        </td>
	</tr>
    <tr>
		<td>Details:&nbsp;</td>
    </tr>
    <tr>
    	<td>
    		<html:textarea property="intake.lawsuitDetails" cols="93" rows="5" styleClass="textarea" />
    	</td>
    </tr>
    <tr>
		<td>Are you involved in a lawsuit?&nbsp;
			   			<html:select property="intake.currentLawsuitFlag" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>
						&nbsp;&nbsp;&nbsp;If yes, when:
						<html:text property="intake.currentLawsuitDate"  size="10" maxlength="10" />
		</td>
	</tr>
    <tr>
		<td></td>
	</tr>
	<tr>
		<td>Details:</td>
     </tr>
     <tr>
     	<td>
     		<html:textarea property="intake.currentLawsuitDetails" rows="5" cols="93" styleClass="textarea" />
    	</td>
    </tr>
	<tr>
		<td>Ever convicted of a felony?&nbsp;
          	<html:select property="intake.felonyFlag" styleClass="select" >
				<html:option value="">Select</html:option>
				<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
			</html:select>
			&nbsp;&nbsp;&nbsp;If yes, how many:
			<html:text property="intake.felonyQty"  size="3" maxlength="2" />
		</td>
	</tr>
    <tr>
		<td>Details:</td>
     </tr>
     <tr>
     	<td>
     		<html:textarea property="intake.felonyDetails" rows="5" cols="93" styleClass="textarea" />
     	</td>	
     </tr>
    <tr>
		<td>Ever convicted of a sexual offense?&nbsp;
    			<html:select property="intake.sexualOffenseFlag" styleClass="select" >
					<html:option value="">Select</html:option>
					<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
				</html:select>
 				&nbsp;&nbsp;&nbsp;If yes, how many:
 				<html:text property="intake.sexualOffenseQty"  size="3" maxlength="2" />
 		</td>
	</tr>
    <tr>
		<td>Details:</td>
    </tr>
    <tr>
    	<td>
    		<html:textarea property="intake.sexualOffenseDetails" rows="5" cols="93" styleClass="textarea" />
    	</td>
	</tr>
    <tr>
		<td></td>
	</tr>
	<tr>
		<td>Are you on probation? &nbsp;
   			<html:select property="intake.probationFlag" styleClass="select" >
				<html:option value="">Select</html:option>
				<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
			</html:select>

		</td>
	</tr>
	<tr>
		<td>What County?&nbsp;
		<html:text property="intake.probationCounty"  size="20" maxlength="20" />
	</tr>
    
    <tr>
		<td>What State?&nbsp;
    			<html:select property="intake.probationState" styleClass="select" >
				<html:option value="">Select</html:option>
				<html:optionsCollection name="ddl_state" value="value" label="label" />
			</html:select>
	  </td>
	</tr>
    
	<tr>
		<td>Probation Officers Name:&nbsp;
		<html:text property="intake.probationOfficer"  size="20" maxlength="20" />
	</tr>
   
	<tr>
		<td>Probation Officers Phone #:&nbsp;
		<html:text property="intake.probationOfficerPhone"  size="20" maxlength="20" />
	</tr>
    
	<tr>
		<td>Any Court or Probation appointments in the next 30 days?&nbsp;
	   		<html:select property="intake.probationApptFlag" styleClass="select" >
				<html:option value="">Select</html:option>
				<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
			</html:select>
      </td>
      </tr>
      <tr>
      <td>If yes, Dates & Times:
          	<html:text property="intake.probationApptDetails"  size="45" maxlength="150" />
      </td>
	</tr>	
    
	<br/><br/>
	
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
	

<html:hidden property="pageSource" value="legal"/>  
    <html:hidden property="nextStep" value="employment"/>  
    <html:hidden property="previousStep" value="health"/> 
</html:form>

<jsp:include page="footer.jsp" flush="true"/>