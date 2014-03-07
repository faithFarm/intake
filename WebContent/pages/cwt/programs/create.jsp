<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

 
<jsp:include page="../../../includes/header.jsp" flush="true"/>


<html:form method="POST" action="/Cwt.do">
    <h2>
        Course Work Therapy - Create UBIT Skill
    </h2>
    
       <br />
 		  <jsp:include page="../messages.jsp" flush="true"/>
	   <br />
  
            <div align="left">
            
            UBIT Name<br /><html:text property="cwtProgram.programName" size="30" maxlength="50" /><br /><br />
            Description<br /><html:textarea property="cwtProgram.description" cols="93" styleClass="textarea" /><br />
        	 Status<br/>
                    <html:select property="cwtProgram.status" styleClass="status" > 
						<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_cwtStatus" value="value" label="label" />
					</html:select>
               <br /><br />
            <br />
            <input type="submit" name="action" value="Save" />&nbsp;
            <input type="submit" name="action" value="Delete"/>
            	
             </div>
    </div>
    <div class="footer">
        Faith Farm Ministries &copy;2013
    </div>
    <html:hidden name="cwtForm" property="pageSource" value="program"/>
</html:form>
</body>
</html>
