<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="../../../includes/header.jsp" flush="true"/>

<html:form method="POST" action="/Cwt">
    <h2>
        Course Work Therapy - Job
    </h2>
    
        <br />
 		  <jsp:include page="../messages.jsp" flush="true"/>
	    <br />

            <div align="left">
            
            Job Title<br /> <html:text property="cwtJob.title" size="30" maxlength="50" /><br /><br />
            <br />Department<br/>
             		<html:select property="cwtJob.departmentId" styleClass="status"> 
						<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_department" value="departmentId" label="title" />
					</html:select>
               <br />
               <br /><br /><b>Metrics</b><br/>
              		
					<logic:iterate id="loop" name="cwtForm" property="metricList" indexId="i">
			        <tr>
			    	<td width="150">
			        	<bean:write name="loop" property="metricName"/>
			        </td>
			        <td colspan="7">
			   			<html:checkbox property="jobMetric[${i}]" value="YES" />
			   			<br />
					</td>
			        </tr>
			        </logic:iterate>

            <br /><br />
            <input type="submit" name="action" value="Save"/>&nbsp;
            </div>
    </div>
    <div class="footer">
        Faith Farm Ministries &copy;2013
    </div>
   <html:hidden name="cwtForm" property="pageSource" value="job"/>
</html:form>
</body>
</html>
