<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="../includes/header.jsp" flush="true"/>
<%@ page import="org.faithfarm.sms.domain.SystemUser" %>



    <h2>
        Welcome to Faith Farm Student Management System
    </h2>
    
            <p>
                Now you can start using Faith Farm Student Management System.
                <br />
                You can use top menu for accessing all functions.
                <br />
            </p>
            <logic:equal name="loginForm" property="systemUser.userRole" value="CwtInstructor" >
            	<ul style="color: Blue">
	                <li>Manage your CWT roster(s).</li>
	            </ul>
            </logic:equal>
            <logic:equal name="loginForm" property="systemUser.userRole" value="Administrator" >
	            <ul style="color: Blue">
	                <li>Accessing exiting student information please use "Search" link for search. </li>
	                <li>Creating student information please use "New Student" link. </li>
	                <li>Also you can access last search result by using "Search Results" link. </li>
	            </ul>
            </logic:equal>
        

        </div>
        <div class="clear">
        </div>
    </div>
    <div class="footer">
        
    </div>
   
</form>
</body>
</html>
