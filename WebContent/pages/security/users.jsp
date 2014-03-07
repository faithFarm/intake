<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

 
<jsp:include page="../../includes/header.jsp" flush="true"/>


<html:form method="POST" action="/Login">
    <h2>
        Course Work Therapy
    </h2>
    
            <br />
            <jsp:include page="messages.jsp" flush="true"/>
            <br/>
          
            <div align="left">
            <table width="80%" cellpadding="0" cellspacing="0" >
            <tr>
            	<td>
                <table width="100%" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td class="colHeading2" width="100">Actions</td>
                    <td class="colHeading2" width="300">Name</td>
                    <td class="colHeading2" width="300">UserId</td>
                    <td class="colHeading2" width="150">Role</td>
                    <td class="colHeading2" width="150">Farm</td>
                </tr>
               <logic:iterate id="loop1" name="loginForm" property="userList">              
                <tr>
                    <td class="searchRowOdd2">
                    	 <a href="<%=request.getContextPath()%>/Login.do?action=Edit&id=<bean:write name="loop1" property="userId"/>" alt="Edit user" title="Edit user" >
                    	 	<img src="<%=request.getContextPath()%>/images/local/Edit.gif" width="16" height="14"/>
                    	 </a>
                    </td>
                   	<td class="searchRowOdd2"><bean:write name="loop1" property="firstName"/>&nbsp;<bean:write name="loop1" property="lastName"/></td>
                    <td class="searchRowOdd2"><bean:write name="loop1" property="username"/></td>
                    <td class="searchRowOdd2"><bean:write name="loop1" property="userRole"/></td>
                    <td class="searchRowOdd2"><bean:write name="loop1" property="farmBase"/></td>
                </tr> 
                </logic:iterate>
                <logic:empty name="loginForm" property="userList">
					<td colspan="8">No users defined</td>
				</logic:empty>
                </table>
                </td>
            </tr>
            </table>
            </br>
            <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
            	<td height="23" valign="center" align="left">
            		<input type="submit" name="action" value="Create"/>
            	</td>
            </tr>
           	</table>
        	</div>

        </div>
        <div class="clear">
        </div>
    </div>
    <div class="footer">
        
    </div>

</html:form>
</body>
</html>
