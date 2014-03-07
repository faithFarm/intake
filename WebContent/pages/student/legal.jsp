<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="../../includes/header_info.jsp" flush="true"/>
 

<html:form action="/Intake">
<div onKeyPress="return checkSubmit(event)">
   <table width="750">
	
	<tr>
		<td colspan="8"><b>Legal Information:</b></td>
	</tr>
	
	<tr>
		<td colspan="11">
    		<jsp:include page="../../includes/messages.jsp" flush="true"/>
	   </td>
	</tr>
	
		<tr>
		<td colspan="8">Have you ever been sued?&nbsp;
   			<html:select property="intake.lawsuitFlag" styleClass="select" >
				<html:option value="">Select</html:option>
				<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
			</html:select>

            &nbsp;&nbsp;&nbsp;If yes, when&nbsp;
            <html:text property="intake.lawsuitDate"  size="10" maxlength="10" />
        </td>
	</tr>
    <tr>
		<td colspan="8">Details:&nbsp;</td>
    </tr>
    <tr>
    	<td colspan="8">
    		<html:textarea property="intake.lawsuitDetails" cols="93" rows="5" styleClass="textarea" />
    	</td>
    </tr>
    <tr>
		<td colspan="8">Are you involved in a lawsuit?&nbsp;
			   			<html:select property="intake.currentLawsuitFlag" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>
						&nbsp;&nbsp;&nbsp;If yes, when:
						<html:text property="intake.currentLawsuitDate"  size="10" maxlength="10" />
		</td>
	</tr>
    <tr>
		<td colspan="8"></td>
	</tr>
	<tr>
		<td colspan="8">Details:</td>
     </tr>
     <tr>
     	<td colspan="8">
     		<html:textarea property="intake.currentLawsuitDetails" rows="5" cols="93" styleClass="textarea" />
    	</td>
    </tr>
	<tr>
		<td colspan="8">Ever convicted of a felony?&nbsp;
          	<html:select property="intake.felonyFlag" styleClass="select" >
				<html:option value="">Select</html:option>
				<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
			</html:select>
			&nbsp;&nbsp;&nbsp;If yes, how many:
			<html:text property="intake.felonyQty"  size="3" maxlength="2" />
		</td>
	</tr>
    <tr>
		<td colspan="8">Details:</td>
     </tr>
     <tr>
     	<td colspan="8">
     		<html:textarea property="intake.felonyDetails" rows="5" cols="93" styleClass="textarea" />
     	</td>	
     </tr>
    <tr>
		<td colspan="8">Ever convicted of a sexual offense?&nbsp;
    			<html:select property="intake.sexualOffenseFlag" styleClass="select" >
					<html:option value="">Select</html:option>
					<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
				</html:select>
 				&nbsp;&nbsp;&nbsp;If yes, how many:
 				<html:text property="intake.sexualOffenseQty"  size="3" maxlength="2" />
 		</td>
	</tr>
    <tr>
		<td colspan="8">Details:</td>
    </tr>
    <tr>
    	<td colspan="8">
    		<html:textarea property="intake.sexualOffenseDetails" rows="5" cols="93" styleClass="textarea" />
    	</td>
	</tr>
    <tr>
		<td colspan="8" ></td>
	</tr>
	<tr>
		<td colspan="8">Are you on probation? &nbsp;
   			<html:select property="intake.probationFlag" styleClass="select" >
				<html:option value="">Select</html:option>
				<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
			</html:select>

		</td>
	</tr>
	<tr>
		<td colspan="8">What County?&nbsp;
		<html:text property="intake.probationCounty"  size="20" maxlength="20" />
	</tr>
    
    <tr>
		<td colspan="8">What State?&nbsp;
    			<html:select property="intake.probationState" styleClass="select" >
				<html:option value="">Select</html:option>
				<html:optionsCollection name="ddl_state" value="value" label="label" />
			</html:select>
	
        
        
        </td>
	</tr>
    
	<tr>
		<td colspan="8">Probation Officers Name:&nbsp;
		<html:text property="intake.probationOfficer"  size="20" maxlength="20" />
	</tr>
   
	<tr>
		<td colspan="8">Probation Officers Phone #:&nbsp;
		<html:text property="intake.probationOfficerPhone"  size="20" maxlength="20" />
	</tr>
    
	<tr>
		<td colspan="8">Any Court or Probation appointments in the next 30 days?&nbsp;
	   		<html:select property="intake.probationApptFlag" styleClass="select" >
				<html:option value="">Select</html:option>
				<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
			</html:select>
      </td>
      </tr>
      <tr>
      <td colspan="8">If yes, Dates & Times:
          	<html:text property="intake.probationApptDetails"  size="45" maxlength="150" />
      </td>
	</tr>	
    
	 <tr>
	<td colspan="8" valign="bottom" align="center" height="45">   
    	<input type="submit" name="action" value="Save" class="imageButtonSave" title="Save Information" />&nbsp;
    </td>
	</tr>
    
    </table>




    <div class="footer">
        
    </div>
  <html:hidden property="pageSource" value="legal"/> 
 </div> 
</html:form>
</body>
</html>