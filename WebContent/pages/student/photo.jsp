<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<jsp:include page="../../includes/header_info.jsp" flush="true"/>
 


<html:form action="/Intake" method="POST" enctype="multipart/form-data">

<table width="750">
	<tr>
		<td ><b>Upload Photo: </b></td>
	</tr>
	<tr>
    	<td >
            		<logic:notEmpty name="intakeForm" property="intake.imageHeadshot">
           		   		<img src="<%=request.getContextPath()%>/Image.do" width="200" height="133"/>
           		    </logic:notEmpty>
           	        <logic:empty name="intakeForm" property="intake.imageHeadshot">
           	       		<img src="<%=request.getContextPath()%>/images/local/person.jpg" width="200" height="133"/>
           	       </logic:empty>
           	<br/><br/>
        </td>
    </tr>
    <tr>
		<td valign="bottom" align="left" height="45">
        	<html:file property="imageFile" styleClass="imageButtonSave"/>&nbsp;Then&nbsp;
        	<input type="submit" name="action" value="Upload" class="imageButtonSave">
       </td>
	</tr>

	</table>
 <html:hidden property="pageSource" value="personal"/>  
</html:form>
 </div>
</div>
</table>
				