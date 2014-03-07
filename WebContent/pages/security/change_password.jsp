<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>  

<jsp:include page="../../includes/header.jsp" flush="true"/>


    <h2>
        Faith Farm Student Management System Version 2.0
    </h2>
    
            <p>
            <b>Change Password</b></br></br>
            Please keep in mind that your password must be between eight (8) and fifteen (15) characters long and contain ALL of the following:
			<ul>
			<li>Uppercase (A-Z)
			<li>Lowercase (a-z)
			<li>Digits (0-9)
			<li>Non-alphanumeric (!, @, #, %, etc.)
			</ul>
            </p>
            
        <html:form action="/Login" focus="password1">
         <span class="failureNotification">
         		<bean:write name="loginForm" property="message"/>
         </span>
        
        	<p>
                 Password:<br />
                 <html:password property="password1" size="20" maxlength="15" styleClass="textbox"></html:password>
              </p>
             <p>
                 Re-enter Password:<br />
                 <html:password property="password2" size="20" maxlength="15" styleClass="textbox"></html:password>
             </p>
             </br>
        	 <input type="submit" name="action" value="Change Password"  />
              
        </html:form>
		</br></br>
        </div>
        <div class="clear">
        </div>
    </div>
    <div class="footer">
        
    </div>
   
</form>
</body>
</html>
