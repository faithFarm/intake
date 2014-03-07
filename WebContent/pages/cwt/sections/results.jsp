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
            					<td background="images/local/searchGroupBk.png" class="searchMenuHeader"><b>Farm</b></td>
            					<td background="images/local/searchGroupBk.png" class="searchMenuHeader"><b>CWT</b></td>
            					<td background="images/local/searchGroupBk.png" class="searchMenuHeader"><b>Sequence</b></td>
            					<td background="images/local/searchGroupBk.png" class="searchMenuHeader"></td>
            				</tr>
                            <tr>
                            	<td width="120" height="23" valign="top" background="images/local/searchGroupBk.png" class="searchMenuHeader">
                                  <html:select name="cwtForm" property="farmBase" styleClass="ddlSearch" >
										<html:option value="">select</html:option>
										<html:optionsCollection name="ddl_farm" value="name" label="name" />
								   </html:select>
								 </td>
                             	 <td width="200" height="23" valign="top" background="images/local/searchGroupBk.png" class="searchMenuHeader">
                                  <html:select name="cwtForm" property="programId" styleClass="ddlSearch" >
										<html:option value="">select</html:option>
											<html:optionsCollection name="ddl_program" value="value" label="label" />					
								   </html:select>
								 </td>
 
                             	 <td width="60" height="23" valign="top" background="images/local/searchGroupBk.png" class="searchMenuHeader">
                                  <html:select name="cwtForm" property="moduleSequence" styleClass="ddlSearch" >
										<html:option value="">select</html:option>
											<html:optionsCollection name="ddl_moduleSeq" value="value" label="label" />					
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
                    <td class="colHeading2" width="350">Module Name</td>
                    <td class="colHeading2" width="100">Sequence</td>
                    <td class="colHeading2" width="50">Days</td>
                    <td class="colHeading2" width="50">Times</td>
                    <td class="colHeading2" width="70">Location</td>
                    <td class="colHeading2" width="100">Farm</td>
                    <td class="colHeading2" width="100">Instructor</td>
                </tr>
               
                <logic:iterate id="loop" name="cwtForm" property="cwtSections">
                <tr>
                    <td class="searchRowOdd2">
                    	 <a style="text-decoration:none;" href="<%=request.getContextPath()%>/Cwt.do?action=Edit&type=Section&id=<bean:write name="loop" property="sectionId" />&farm=<bean:write name="loop" property="farmBase" />" alt="Edit Section" title="Edit Section">
                    		 <img src="<%=request.getContextPath()%>/images/local/Edit.gif" width="16" height="14"/>
						 </a>	
						 <a style="text-decoration:none;" href="<%=request.getContextPath()%>/CwtRoster.do?action=Roster&type=Section&id=<bean:write name="loop" property="sectionId" />&farm=<bean:write name="loop" property="farmBase" />" alt="Section Roster" title="Section Roster">
                    		 <img src="<%=request.getContextPath()%>/images/local/roster.png" width="16" height="16"/>
						 </a>						 
					</td>
                   	<td class="searchRowOdd2" ><bean:write name="loop" property="moduleName"/></td>
                    <td class="searchRowOdd2" ><bean:write name="loop" property="moduleSeq"/></td>
                    <td class="searchRowOdd2" ><bean:write name="loop" property="meetingDays"/></td>
                    <td class="searchRowOdd2" ><bean:write name="loop" property="meetingTimes"/></td>
                    <td class="searchRowOdd2" ><bean:write name="loop" property="location"/></td>
                    <td class="searchRowOdd2" ><bean:write name="loop" property="farmBase"/></td>
                    <td class="searchRowOdd2" ><bean:write name="loop" property="instructor"/></td>
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
