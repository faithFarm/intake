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
        Course Work Therapy - <bean:write name="cwtRoster" property="cwtModule.moduleName" /> Roster&nbsp;(<bean:write name="cwtRoster" property="rosterDate"/>&nbsp;-&nbsp;<bean:write name="cwtRoster" property="cwtModuleSection.farmBase" />)
    </h2>
    
            <br />
            <jsp:include page="../messages.jsp" flush="true"/>
            <br/>
            
            <div align="left">
            <table width="90%" cellpadding="0" cellspacing="0" >
            <tr>
            	<td>
                <table width="100%" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td class="colHeading2" width="150">Student</td>
                    <td class="colHeading2" width="100"><input type="checkbox" name="checkall" onclick="checkAll(this);" style="height:10px;" >Attended</td>
                    <td class="colHeading2" width="100">Exam Score</td>
                    <td class="colHeading2" width="100">Status</td>
                </tr>
               
                <logic:iterate id="loop" name="cwtRoster" property="masterList" indexId="i">
                <tr>
                   	<td class="searchRowOdd2" ><bean:write name="loop" property="cwtIntake.name"/></td>
                    <td class="searchRowOdd2" ><html:checkbox name="cwtRoster" property="attendFlag[${i}]" value="Yes" /></td>
                    <td class="searchRowOdd2" ><html:text name="cwtRoster" property="examScore[${i}]" size="5" maxlength="5" /></td>
                    <td class="searchRowOdd2" >
                    	<html:select name="cwtRoster" property="status[${i}]" styleClass="status" > 
							<html:option value="">Select</html:option>
							<html:optionsCollection name="ddl_rosterStatus" value="value" label="label" />
						</html:select>
                    </td>
                </tr> 
                </logic:iterate>
               </table>
               </br></br>
                <table width="98%" cellpadding="0" cellspacing="0" border="0">
               <tr>
                	<td>
                	   <html:textarea name="cwtRoster" property="cwtRosterNotes.notes" rows="10" cols="120"/>
                	</td>
                </tr>
                <!-- <tr>
                	<td align="left"><html:checkbox name="cwtRoster" property="archiveFlag" value="Yes"/>Archive Roster</td>
                </tr> -->
                </table>
                <div align="center">
	            	<a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=Print&archivedFlag=No&type=Section&id=<bean:write name="cwtRoster" property="cwtModuleSection.moduleOfferingId" />');">Print</a>
	            	&nbsp;&nbsp;
	            	<a href="<%=request.getContextPath()%>/Cwt.do?action=archives">Search</a>
            	</div>
 
                </td>
            </tr>
            </table>
            <br/>
            <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
            	<td height="23" valign="center" align="left">
            		<input type="submit" name="action" value="Save"/>
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
