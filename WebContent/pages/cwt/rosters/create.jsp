<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>




<jsp:include page="../../../includes/header.jsp" flush="true"/>

<script language="javascript">

function checkAll(bx)
{

 var cbs = document.getElementsByTagName('input');
 for(var i=0; i < cbs.length; i++)
 {
    if(cbs[i].type == 'checkbox')
    {
        cbs[i].checked = bx.checked;
     }
 }
}

</script>

<html:form method="POST" action="/CwtRoster">

    <h2>
        Course Work Therapy - <bean:write name="loginForm" property="systemUser.farmBase" /> Roster
    </h2>
    
    <br/>
     <i>Select a module and class date to generate roster</i></br></br>
     <font style="color:red"><b>WARNING: Make sure all student's CWT Program is properly assigned.  Otherwise rosters will not be accurate and manual entry will be required for each student record.</b></font>
            <font style="color:red"><br /><br /><b><%=session.getAttribute("error") %></b></font>
            <div align="left">
            <table width="90%" cellpadding="0" cellspacing="0" >
            <tr>
            	<td>Module&nbsp;
	             	<html:select property="moduleId"  >
							<html:option value="">select</html:option>
							<html:optionsCollection name="ddl_module" value="value" label="label" />					
				   </html:select>
				 </td>
            <tr>
            	<td>Class Date&nbsp;<html:text property="rosterDate" size="15" maxlength="10" styleClass="tcal" /></td>
            </tr>
            <!-- 
            <tr>
            	<td>
                <table width="100%" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td class="colHeading2" width="100"><input type="checkbox" name="checkall" onclick="checkAll(this);" style="height:10px;" checked>Enroll</td>
                    <td class="colHeading2" width="150">Student</td>
                     <td class="colHeading2" width="100">Job</td>
                    <td class="colHeading2" width="100">Supervisor</td> 
                    <td class="colHeading2" width="100">Class</td>
                    <td class="colHeading2" width="100">Farm</td>
                </tr>
               
                <logic:iterate id="loop" name="cwtRoster" property="masterList" indexId="i">
                <tr>
                   	<td class="searchRowOdd2" width="50" ><html:checkbox name="cwtRoster" property="enrollFlag[${i}]" value="Yes" styleId="chkbx" /></td>
                    <td class="searchRowOdd2" ><bean:write name="loop" property="intake.firstname"/>&nbsp;<bean:write name="loop" property="intake.lastname"/></td>
                     <td class="searchRowOdd2" ><bean:write name="loop" property="cwtJob.title"/></td>
                    <td class="searchRowOdd2" ><bean:write name="loop" property="cwtSupervisor.firstname"/>&nbsp;<bean:write name="loop" property="cwtSupervisor.lastname"/></td>
                    <td class="searchRowOdd2"><bean:write name="loop" property="intake.class_"/></td>
                    <td class="searchRowOdd2"><bean:write name="loop" property="intake.farmBase"/></td>
                </tr> 
                </logic:iterate>
               
                </table>
                </td>
            </tr>
             -->
            </table>
            <br/>
            <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
            	<td height="23" valign="center" align="left">
            		<input type="submit" name="action" value="Generate Roster"/>
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
 <html:hidden name="cwtForm" property="pageSource" value="sections" />
</html:form>
</body>
</html>
