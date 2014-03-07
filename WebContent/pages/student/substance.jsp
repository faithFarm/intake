<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="../../includes/header_info.jsp" flush="true"/>

<html:form method="POST" action="/Intake">

    <table width="750">
	<tr>
		<td colspan="8"><b>Substance Abuse Information: </b><br /><br /></td>
	</tr>

	<tr>
		<td colspan="11">
    		<jsp:include page="../../includes/messages.jsp" flush="true"/>
	   </td>
	</tr>
	
	<tr>
		<td colspan="11"><b>Chemical Dependency (Answer all that apply):</b></td>
	</tr>
	<tr>
		<td  valign="top" colspan="4">
			<table border="0" cellpadding="5" cellspacing="5" width="352" height="243" align="left">
				<tr>
					<td width="124">

					Substance</td>
					<td width="102">

					Years Used</td>
					<td>

					Last Used</td>
				</tr>
				<tr>
					<td width="124">

					Alcohol</td>
					<td width="102">
						<html:text property="intake.alcoholYearsUsed" size="13" maxlength="2" />
					</td>
					<td>
						<html:text property="intake.alcoholLastUsed" size="20" maxlength="15" />
					</td>
				</tr>
				<tr>
					<td width="124">

					Cocaine</td>
						<td width="102">
						<html:text property="intake.cocaineYearsUsed" size="13" maxlength="2" />
					</td>
					<td>
						<html:text property="intake.cocaineLastUsed" size="20" maxlength="15" />
					</td>
				</tr>
				<tr>
					<td width="124">

					Marijuana</td>
					<td width="102">
						<html:text property="intake.marijuanaYearsUsed" size="13" maxlength="2" />
					</td>
					<td>
						<html:text property="intake.marijuanaLastUsed" size="20" maxlength="15" />
					</td>				</tr>
				<tr>
					<td width="124">

					Oxycodone</td>
					<td width="102">
						<html:text property="intake.oxycodoneYearsUsed" size="13" maxlength="2" />
					</td>
					<td>
						<html:text property="intake.oxycodoneLastUsed" size="20" maxlength="15" />
					</td>				</tr>
				<tr>
					<td width="124">

					Speed</td>
					<td width="102">
						<html:text property="intake.speedYearsUsed" size="13" maxlength="2" />
					</td>
					<td>
						<html:text property="intake.speedLastUsed" size="20" maxlength="15" />
					</td>				</tr>
				<tr>
					<td width="124">

					Heroin</td>
					<td width="102">
						<html:text property="intake.heroinYearsUsed" size="13" maxlength="2" />
					</td>
					<td>
						<html:text property="intake.heroinLastUsed" size="20" maxlength="15" />
					</td>				</tr>
                <tr>
					<td width="124">

					Xanax</td>
					<td width="102">
						<html:text property="intake.xanaxYearsUsed" size="13" maxlength="2" />
					</td>
					<td>
						<html:text property="intake.xanaxLastUsed" size="20" maxlength="15" />
					</td>				</tr>
				<tr>
					<td width="124">

					Other</td>
					<td width="102">
						<html:text property="intake.otherYearsUsed" size="13" maxlength="2" />
					</td>
					<td>
						<html:text property="intake.otherLastUsed" size="20" maxlength="15" />
					</td>				
				</tr>
			</table>
		</td>
		<td>&nbsp;</td>
        </tr>
    
        <tr>
			<td valign="top">Longest time sober in past 3 years?&nbsp;
				<html:text property="intake.sober3Years" size="9" maxlength="15" />
			</td>
			<td valign="top">in the last year? 
				<html:text property="intake.sober1Year" size="9" maxlength="15" />
			</td>
        </tr>
    
        <tr>
			<td colspan="8">&nbsp;</td>
		</tr>
        <tr>
    	<td colspan="8">
        Physical effects of abuse<br>
        	<html:checkbox property="motivationalLossFlag" value="Yes" />Motivational Loss&nbsp;&nbsp;
        	<html:checkbox property="shakesConvulsionsFlag" value="Yes" />Shakes-Convulsions&nbsp;&nbsp;
        	<html:checkbox property="memoryLossFlag" value="Yes" />Memory Loss&nbsp;&nbsp;
        	<html:checkbox property="incoherentThinkingFlag" value="Yes" />Incoherent Thinking&nbsp;&nbsp;
        	<html:checkbox property="organProblemsFlag" value="Yes" />Organ Problems&nbsp;&nbsp;
        <br/><br/>	
		<b>Any Previous Rehabs?</b>&nbsp;
			<logic:equal name="intakeForm" property="intake.aaFlag" value="Yes">
				<html:checkbox property="intake.aaFlag" value="Yes" />AA or NA&nbsp;&nbsp;
			</logic:equal>
			<logic:notEqual name="intakeForm" property="intake.aaFlag" value="Yes">
				<html:checkbox property="intake.aaFlag" value="Yes" />AA or NA&nbsp;&nbsp;
			</logic:notEqual>
            Number of Years&nbsp;
            <html:text property="intake.aaFlag" size="10" maxlength="10" />
        </td>
        </tr>
         <tr>
         <td colspan="8">
         	<table width="100%">
            <tr>
            	<td>
                    Ever been to a Faith Farm Before?&nbsp;
      		            <html:select property="intake.prevFfFlag" styleClass="select" >
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
						</html:select>
                </td>
                <td>
					Year:&nbsp;
					<html:text property="intake.prevFfYear" size="12" maxlength="12" />
				</td>
                <td>
					Where:&nbsp;
                     <html:select property="intake.prevFfOther" > 
                     	<html:option value=""></html:option>
						<html:optionsCollection name="ddl_farm" value="name" label="name" />
					 </html:select>

            	</td>
             </tr>
             <tr>
             	<td colspan="3">
           		  Other Rehabs
           		  <html:text property="intake.prevFfYear" size="45" maxlength="45" />
           		</td>
             </tr>
            
             </table>
		</td>
	</tr>
  
        <tr>
        	<td colspan="8" valign="top">
		<b>Pattern of usage:</b><br>
        	<html:checkbox property="usagePattern1" value="Constantly"  />Constantly&nbsp;&nbsp;
            <html:checkbox property="usagePattern2" value="Weekends"/>Weekends&nbsp;&nbsp;
            <html:checkbox property="usagePattern3" value="Special Occasions" />Special Occasions&nbsp;&nbsp;
            <html:checkbox property="usagePattern4" value="Whenever available" />Whenever available&nbsp;&nbsp;
            <html:checkbox property="usagePattern5" value="When Things Get Tough" />When Things Get Tough&nbsp;&nbsp;
            <html:checkbox property="usagePattern6" value="A Week/Off A Week" />On A Week/Off A Week
        </br></br> 
		<b>Losses due to usage:</b><br>
        	<html:checkbox property="usageLosses1" value="Job"/>Job&nbsp;&nbsp;
            <html:checkbox property="usageLosses2" value="Marriage" />Marriage&nbsp;&nbsp;
            <html:checkbox property="usageLosses3" value="Friends" />Friends&nbsp;&nbsp;
            <html:checkbox property="usageLosses4" value="Possessions" />Possessions&nbsp;&nbsp;
            <html:checkbox property="usageLosses5" value="Arrests" />Arrests&nbsp;&nbsp;
            <html:checkbox property="usageLosses6" value="DUIs" />DUI's&nbsp;&nbsp;
            <html:checkbox property="usageLosses7" value="Heavy Debt" />Heavy Debt&nbsp;&nbsp;
            <html:checkbox property="usageLosses8" value="Health" />Health&nbsp;&nbsp;
            <html:checkbox property="usageLosses9" value="Incarceration" />Incarceration
        	</td>
         </tr>
   
          <tr>
        	<td colspan="8" valign="top">
			<b>Quantity Consumed:</b><br>
        		Per week:&nbsp;&nbsp;
        		<html:text property="intake.quantityPerWeek" size="25" maxlength="25" />
        		Per week 2 years ago:&nbsp;&nbsp;
            	<html:text property="intake.quantity2Years" size="25" maxlength="25" />
            </td>
         </tr>
    




     <tr>
		<td colspan="8" valign="bottom" align="center" height="45">   
   		 	<input type="submit" name="action" value="Save" class="imageButtonSave" title="Save Information" />&nbsp;
   		</td>
	</tr>
    </table>
  <html:hidden property="pageSource" value="substance"/>     
</html:form>

<br /><br />
   
</body>
</html>