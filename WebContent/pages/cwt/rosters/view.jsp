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
    
             </br>
            <div align="left">
            <table width="90%" cellpadding="0" cellspacing="0" >
            <tr>
            	<td>
                <table width="100%" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td class="colHeading2" width="150">Student</td>
                   <td class="colHeading2" width="100"><input type="checkbox" name="checkall" onclick="checkAll(this);" style="height:10px;" disabled>Attended</td>
                    <td class="colHeading2" width="100">Exam Score</td>
                    <td class="colHeading2" width="100">Status</td>
                </tr>
               
                <logic:iterate id="loop" name="cwtRoster" property="masterList" indexId="i">
                <tr>
                   	<td class="searchRowOdd2" ><bean:write name="loop" property="intake.firstname"/>&nbsp;<bean:write name="loop" property="intake.lastname"/></td>
                    <td class="searchRowOdd2" ><html:checkbox name="cwtRoster" property="attendFlag[${i}]" value="Yes" disabled="true"/></td>
                    <td class="searchRowOdd2" ><bean:write name="cwtRoster" property="examScore[${i}]"  /></td>
                    <td class="searchRowOdd2" ><bean:write name="cwtRoster" property="status[${i}]"  /></td>
                </tr> 
                </logic:iterate>
               </table>
               </br></br>
                <table width="100%" cellpadding="0" cellspacing="0" border="0">
               <tr>
                	<td>
                	   <html:textarea name="cwtRoster" property="cwtModuleSection.instructorNotes" rows="10" cols="120" disabled="true"/>
                	</td>
                </tr>
                </table>
                </td>
            </tr>
            </table>
            <br/>
            <div align="center">
            	<a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=Print&archivedFlag=Yes&type=Section&id=<bean:write name="cwtRoster" property="cwtModuleSection.moduleOfferingId" />');">Print</a>
            	&nbsp;&nbsp;
            	<a href="<%=request.getContextPath()%>/Cwt.do?action=archives">Search</a>
            </div>
            
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
