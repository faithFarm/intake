<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

 
<jsp:include page="../../../includes/header.jsp" flush="true"/>


<html:form method="POST" action="/Cwt.do">

    <h2>
        Course Work Therapy - Create Module Section 
    </h2>
    
       <br />
 		  <jsp:include page="../messages.jsp" flush="true"/>
	   <br />
 
            <div align="left">            
            <b>Module Name</b><br />
                        <html:select property="cwtModuleSection.moduleId" styleClass="status" > 
						  <html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_module" value="value" label="label" />
					</html:select>
            <br /><br />            
            
            <b>Meeting Days</b><br />
            	<html:checkbox property="monday" value="M" />Monday
            	<html:checkbox property="tuesday" value="TU" />Tuesday
            	<html:checkbox property="wednesday" value="W" />Wednesday
            	<html:checkbox property="thursday" value="TH" />Thursday
            	<html:checkbox property="friday" value="F" />Friday
            	<html:checkbox property="saturday" value="SA" />Saturday
            	<html:checkbox property="sunday" value="SU" />Sunday  
                <br /><br />
            <b>Meeting Times</b><br />
            	<html:text property="cwtModuleSection.meetingTimes" size="10" maxlength="10" />
            <br /><br />
            <b>Begin Date&nbsp;&nbsp;</b><html:text property="cwtModuleSection.effectiveDate" size="20" maxlength="12" styleClass="tcal" />
            &nbsp;&nbsp;&nbsp;<b>End Date&nbsp;&nbsp;</b><html:text property="cwtModuleSection.expirationDate" size="20" maxlength="12" styleClass="tcal" />
            <br/><br>
            <b>Meeting Location</b><br />
            	<html:text property="cwtModuleSection.location" size="30" maxlength="30" />
            <br/><br/>
            <b>Farm</b><br />
                       <html:select property="cwtModuleSection.farmBase" styleClass="status"> 
                      	<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_farm" value="name" label="name" />
					  </html:select>
            <br/><br/>
            <b>Instructor</b><br/>
            
                      <html:select property="cwtModuleSection.instructorId" styleClass="status"> 
                      	<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_supervisor" value="value" label="label" />
					  </html:select>
            
            <br/><br/>
        	<b>Metrics</b><br/>
  					<logic:iterate id="loop" name="cwtForm" property="metricList" indexId="i">
			        <tr>
			    	<td >
			   			<html:checkbox property="moduleMetric[${i}]" value="YES" />
			   		</td>
					<td colspan="7">
			        	<bean:write name="loop" property="metricName"/><br />
			        </td>
			        
			        </tr>
			        </logic:iterate>
            <br /><br/>
            <b>Status</b><br/>
               <html:select property="cwtModuleSection.status" styleClass="status" > 
						<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_cwtStatus" value="value" label="label" />
					</html:select>
               <br /><br />
           
            <input type="submit" name="action" value="Save" />&nbsp;
            <input type="submit" name="action" value="Delete"/>
            	
             </div>
    </div>
    <div class="footer">
        Faith Farm Ministries &copy;2013
    </div>
   
<html:hidden name="cwtForm" property="pageSource" value="section"/>

</html:form>
</body>
</html>
