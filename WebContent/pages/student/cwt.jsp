<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.faithfarm.sms.domain.CwtMaster" %>
<%@ page import="org.faithfarm.sms.domain.CwtModules" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.faithfarm.sms.util.Validator" %>

<jsp:include page="../../includes/header_info.jsp" flush="true"/>


<html:form  action="/Intake">
   
   
    <table width="900" border="0">
    <tr>
		<td >
    		<jsp:include page="../../includes/messages.jsp" flush="true"/>
	   </td>
	</tr>
	<tr>
		<td><b>Comprehensive Work Therapy</b></br></br></td>
	</tr>
	<tr>
            	<td>Current CWT:&nbsp;
            		<html:select property="intake.cwtProgramId" styleClass="status">  
                    	<html:option value="1">Not Applicable</html:option>
                    	<html:option value="2">Instructor</html:option>
						<html:optionsCollection name="ddl_program" value="value" label="label" />
					</html:select></td>
            </tr>
	<tr>
		<td>
			<table width="900" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="colHeading">CWT</td>
					<td class="colSpacer" width="1"></td>
					<td class="colHeading">Module</td>
					<td class="colSpacer" width="1"></td>
					<td class="colHeading">Completion Date</td>
					<td class="colSpacer" width="1"></td>
					<td class="colHeading" colspan="2">Status</b></td>
				</tr>
				 <%
				 List<CwtMaster> list = (List<CwtMaster>)session.getAttribute("cwtmasters");
				 List<CwtModules> modules = new ArrayList<CwtModules>();
				 
				 Validator v = new Validator();
				 
				 for (Iterator iterator =
						 list.iterator(); iterator.hasNext();){
			    		 CwtMaster obj = (CwtMaster) iterator.next();
			    		 modules = obj.getModules();
			    		 %>
			    		 <tr>
			    		 <td class="searchRowOdd"><%=obj.getProgram().getProgramName() %></td>
			    		 <td class="searchRowSpcrOdd"></td>
			    		 <td class="searchRowOdd"><%=obj.getModule().getModuleName() %></td>
			    		 <td class="searchRowSpcrOdd"></td>
			    		 <td class="searchRowOdd"><%=obj.getRoster().getAttendDate() %></td>
						 <td class="searchRowSpcrOdd"></td>
			    		 <td class="searchRowOdd"><%=obj.getRoster().getStatus() %></td>
			    		 <td class="searchRowOdd"><% if (!"Not Completed".equals(obj.getRoster().getStatus())) { %>
			    		 <input type="submit" name="action" value="Reset Cwt"  onClick="javascript:document.getElementById('deleteId').value='<%=obj.getRoster().getRosterId() %>'" class="imageButtonCwtReset">
			    		 <% } %></td>
			    		 </tr>
			    <%
				 }				 
				 %>
				 </table>

		 </td>     
	</tr>
    </table>
    </br></br>
     <table width="900" border="0">
	<tr>
		<td colspan="10"><b>Add Module To Student Record</b></br></br></td>
	</tr>
	<tr>
            	
            	<td>Module:&nbsp;</td>
            	<td>
            		<html:select property="cwtModuleId" styleClass="status"> 
            			<html:option value="0">Select</html:option> 
                    	<html:optionsCollection name="ddl_module" value="value" label="label" />
					</html:select>
				</td>
				<td>Class Date:&nbsp;</td>
				<td><html:text property="classDate" size="20" maxlength="12" styleClass="tcal"/></td>
				<td>Attended:&nbsp;</td>
            	<td>
            		<html:select property="attended" styleClass="status"> 
            		    <html:option value="">Select</html:option> 
                    	<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
					</html:select>
				</td>
				<td>Status:&nbsp;</td>
            	<td>
            		<html:select property="cwtStatus" styleClass="status">
            		<html:option value="">Select</html:option>  
                    	<html:optionsCollection name="ddl_rosterStatus" value="value" label="label" />
					</html:select>
				</td>
				<td>Exam Score:&nbsp;</td>
            	<td>
            		<html:text property="examScore" size="10" maxlength="10" styleClass="status" />  
                </td>
    </tr>
    </table>
    </br></br></br>
    <div align="center">
   		<input type="submit" name="action" value="Save" class="imageButtonSave" title="Save Information" />&nbsp;
   </div>
    
 <html:hidden property="pageSource" value="cwt"/> 
 <html:hidden property="deleteId" styleId="deleteId" />
 </html:form>

    <div class="footer">
        
    </div>
   
</body>
</html>