<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

 
<jsp:include page="../../../includes/header.jsp" flush="true"/>


<html:form method="POST" action="/Cwt.do">

    <h2>
        Course Work Therapy - Create Module 
    </h2>
   
    <br/>
       <br />
 		  <jsp:include page="../messages.jsp" flush="true"/>
	   <br />
 
            <div align="left">
            
            <b>Module Name</b><br /><html:text property="cwtModule.moduleName" size="30" maxlength="50" /><br /><br />
            <b>Module Sequence</b><br />
            		<html:select property="cwtModule.moduleSeq" styleClass="status" > 
						<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_moduleSeq" value="value" label="label" />
					</html:select><br /><br />
            <b>Description</b><br /><html:textarea property="cwtModule.description" cols="93" styleClass="textarea" /><br /><br />
            <b>UBIT/Skill</b><br/>
               <html:select property="cwtModule.programId" styleClass="status" > 
						<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_program" value="value" label="label" />
					</html:select>
               <br /><br />
            
            <b>Status</b><br/>
               <html:select property="cwtModule.status" styleClass="status" > 
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
   
<html:hidden name="cwtForm" property="pageSource" value="module"/>

</html:form>
</body>
</html>
