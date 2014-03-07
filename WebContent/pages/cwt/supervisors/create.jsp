<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

 
<jsp:include page="../../../includes/header.jsp" flush="true"/>


<html:form method="POST" action="/Cwt">

<form method="POST" action="<%=request.getContextPath()%>/cwt">
    <h2>
        Course Work Therapy - Supervisor
    </h2>
    
          <br />
 		  <jsp:include page="../messages.jsp" flush="true"/>
	      <br />
	      
          <div align="left">
            First Name<br/><html:text property="cwtSupervisor.firstname" size="20" maxlength="20" /> 
            <br/> <br/>
            Last Name<br/><html:text property="cwtSupervisor.lastname" size="20" maxlength="20" />
            <br/> <br/>
            Job Title<br />
            <html:select property="cwtSupervisor.jobId" styleClass="status"> 
						<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_job" value="jobId" label="title" />
			</html:select>
            <br />
            <br />Department<br/>
					<html:select property="cwtSupervisor.departmentId" styleClass="status"> 
						<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_department" value="departmentId" label="title" />
					</html:select>
            <br />
            Farm Location<br/>
 					<html:select property="cwtSupervisor.farmBase" styleClass="status"> 
						<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_farm" value="name" label="name" />
					</html:select>
               <br /><br />
            <input type="submit" name="action" value="Save"/>&nbsp;
            </div>
    </div>
    <div class="footer">
        Faith Farm Ministries &copy;2013
    </div>
	<html:hidden name="cwtForm" property="pageSource" value="supervisor"/>
</html:form>
</body>
</html>
