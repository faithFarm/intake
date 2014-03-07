<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

 
<jsp:include page="../../../includes/header.jsp" flush="true"/>


<html:form method="POST" action="/Cwt.do">
    <h2>
        Course Work Therapy - Departments
    </h2>
    
       <br />
 		  <jsp:include page="../messages.jsp" flush="true"/>
	   <br />
            <div align="left">
            
            Department Title<br />
            <html:text property="cwtDepartment.title" size="30" maxlength="60" /><br />
            Farm Location<br/>
 					<html:select property="cwtDepartment.farmBase" styleClass="status"> 
						<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_farm" value="name" label="name" />
					</html:select>
               <br />
   
            <br /><br />
            <input type="submit" name="action" value="Save" />
            	
             </div>
    </div>
    <div class="footer">
        Faith Farm Ministries &copy;2013
    </div>
   <html:hidden name="cwtForm" property="pageSource" value="department"/>
</html:form>
</body>
</html>
