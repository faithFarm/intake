<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="../../includes/header.jsp" flush="true"/>


    <h2>
        Manage Users - Create User Account
    </h2>
    
        <br />
 		  <jsp:include page="messages.jsp" flush="true"/> 
	    <br />
<html:form method="POST" action="/Login">
            <div align="left">
            
            First Name<br /> <html:text property="user.firstName" size="30" maxlength="20" /><br /><br />
            Last Name<br /> <html:text property="user.lastName" size="30" maxlength="20" /><br /><br />
            Username<br /> <html:text property="user.username" size="15" maxlength="15" /><br /><br />
            Password<br /> <html:text property="user.password" size="10" maxlength="10" /><br /><br />
            Group<br/>
             		<html:select property="user.group_" styleClass="status"> 
						<html:option value="">Select</html:option>
						<html:option value="Dispatch">Dispatch</html:option>
						<html:option value="Intake">Intake</html:option>
					</html:select><br /><br />
            Role<br/>
             		<html:select property="user.userRole" styleClass="status"> 
						<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_role" value="value" label="label" />
					</html:select>
            
            <br /><br /><b>Farm</b><br/>
             		<html:select property="user.farmBase" styleClass="status"> 
						<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_farm" value="name" label="name" />
					</html:select>

            <br /><br/><br/>
            <input type="submit" name="action" value="Save"/>&nbsp;
            <input type="submit" name="action" value="Delete"/>
            </div>
    </div>
    <div class="footer">
        Faith Farm Ministries &copy;2013
    </div>
    
   <html:hidden name="loginForm" property="pageSource" value="job"/>
</html:form>
</body>
</html>
