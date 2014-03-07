<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

 
<jsp:include page="../../../includes/header.jsp" flush="true"/>



<html:form method="POST" action="/Cwt">

    <h2>
        Course Work Therapy - <bean:write name="cwtForm" property="cwtModule.moduleName" /> Sections
    </h2>
    
            <br />
            <jsp:include page="../messages.jsp" flush="true"/>
            <br/>
            
            <div align="left">
            
            <table width="90%" cellpadding="0" cellspacing="0" border="0">
            				<tr>
            					<td background="images/local/searchGroupBk.png" class="searchMenuHeader"><b>Farm</b>
            					<td background="images/local/searchGroupBk.png" class="searchMenuHeader"><b>Module</b></td>
            					<td background="images/local/searchGroupBk.png" class="searchMenuHeader"></td>
            				</tr>
                            <tr>
                            	<td width="150" height="23" valign="top" background="images/local/searchGroupBk.png" class="searchMenuHeader">
                                  <html:select name="cwtForm" property="farmBase" styleClass="ddlSearch" >
										<html:option value="">select</html:option>
										<html:optionsCollection name="ddl_farm" value="name" label="name" />
								   </html:select>
								 </td>
                             	 <td width="150" height="23" valign="top" background="images/local/searchGroupBk.png" class="searchMenuHeader">
                                  <html:select name="cwtForm" property="moduleId" styleClass="ddlSearch" >
										<html:option value="">select</html:option>
											<html:optionsCollection name="ddl_module" value="value" label="label" />					
								   </html:select>
								 </td>
                                
                                <td align="left" valign="top" background="images/local/searchGroupBk.png" class="searchMenuHeader">
                                	<html:submit property="action" value="Filter" styleClass="imageButtonSearch" title="Filter" />
                                </td>
                            </tr>
           </table>
           
           <table width="90%" cellpadding="0" cellspacing="0" >
            <tr>
            	<td>
                <table width="100%" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td class="colHeading2" width="100">Actions</td>
                    <td class="colHeading2" width="150">Module Name</td>
                    <td class="colHeading2" width="100">Meeting Days</td>
                    <td class="colHeading2" width="100">Meeting Times</td>
                    <td class="colHeading2" width="100">Location</td>
                    <td class="colHeading2" width="100">Farm</td>
                    <td class="colHeading2" width="100">Instructor</td>
                    <td class="colHeading2" width="100">Status</td>
                </tr>
               
                <logic:iterate id="loop" name="cwtForm" property="masterList">
                <tr>
                    <td class="searchRowOdd2">
                    	 <a style="text-decoration:none;" href="<%=request.getContextPath()%>/Cwt.do?action=Edit&type=Section&id=<bean:write name="loop" property="section.moduleOfferingId" />&farm=<bean:write name="loop" property="section.farmBase" />" alt="Edit Section" title="Edit Section">
                    		 <img src="<%=request.getContextPath()%>/images/local/Edit.gif" width="16" height="14"/>
						 </a>	
						 <a style="text-decoration:none;" href="<%=request.getContextPath()%>/CwtRoster.do?action=Roster&type=Section&id=<bean:write name="loop" property="section.moduleOfferingId" />&farm=<bean:write name="loop" property="section.farmBase" />" alt="Section Roster" title="Section Roster">
                    		 <img src="<%=request.getContextPath()%>/images/local/roster.png" width="16" height="16"/>
						 </a>						 
					</td>
                   	<td class="searchRowOdd2" ><bean:write name="loop" property="module.moduleName"/></td>
                    <td class="searchRowOdd2" ><bean:write name="loop" property="section.meetingDays"/></td>
                    <td class="searchRowOdd2" ><bean:write name="loop" property="section.meetingTimes"/></td>
                    <td class="searchRowOdd2" ><bean:write name="loop" property="section.location"/></td>
                    <td class="searchRowOdd2" ><bean:write name="loop" property="section.farmBase"/></td>
                    <td class="searchRowOdd2" ><bean:write name="loop" property="supervisor.firstname"/>&nbsp;<bean:write name="loop" property="supervisor.lastname"/></td>
                    <td class="searchRowOdd2"><bean:write name="loop" property="section.status"/></td>
                </tr> 
                </logic:iterate>
               

                </table>
                </td>
            </tr>
            </table>
            <br/>
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
 <html:hidden name="cwtForm" property="pageSource" value="sections" />
 </html:form>
</body>
</html>
