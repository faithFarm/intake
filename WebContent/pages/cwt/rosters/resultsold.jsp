<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="../../../includes/header.jsp" flush="true"/>


    <h2>
        Course Work Therapy - Roster Results
    </h2>
            </br></br> 
            <logic:equal  name="cwtForm" property="searchArchivedFlag" value="No">
            <b>Current Rosters</b></br>
            	<logic:iterate id="loop" name="cwtForm" property="currentRosterList" indexId="i">
            	   <a href="<%=request.getContextPath()%>/CwtRoster.do?action=Roster&type=Section&id=<bean:write name="loop" property="section.moduleOfferingId" />"><img src="<%=request.getContextPath()%>/images/local/Edit.gif" border="0" alt="Manage Roster" title="Manage Roster"/></a>
            	   <a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=Print&type=Section&id=<bean:write name="loop" property="section.moduleOfferingId" />');"><img src="<%=request.getContextPath()%>/images/local/sm_printer.png" border="0" alt="Print Roster" title="Print Roster"/></a>
            	   &nbsp;&nbsp;
            	   <b><bean:write name="loop" property="program.programName" /></b>&nbsp;-&nbsp;<bean:write name="loop" property="module.moduleName" /><i>&nbsp;&nbsp;(&nbsp;&nbsp;<bean:write name="loop" property="roster.rosterDate" /> - <bean:write name="loop" property="section.farmBase" />&nbsp;&nbsp;)</i></br>
            	</logic:iterate> 
            	<logic:empty  name="cwtForm" property="currentRosterList">
            		<i>No rosters available</i>
            	</logic:empty>
            </br></br> 
            </logic:equal>
            
            <logic:equal  name="cwtForm" property="searchArchivedFlag" value="Yes">
            <b>Archived Rosters</b></br>
            	<logic:iterate id="loop1" name="cwtForm" property="archivedRosterList" indexId="i">
            	   <a href="<%=request.getContextPath()%>/CwtRoster.do?action=View&type=Section&id=<bean:write name="loop1" property="section.moduleOfferingId" />"><img src="<%=request.getContextPath()%>/images/local/Edit.gif" border="0" alt="Manage Roster" title="Manage Roster"/></a>
            	   <a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=Print&type=Section&id=<bean:write name="loop1" property="section.moduleOfferingId" />');"><img src="<%=request.getContextPath()%>/images/local/sm_printer.png" border="0" alt="Print Roster" title="Print Roster"/></a>
            	   &nbsp;&nbsp;
            	   <b><bean:write name="loop1" property="program.programName" /></b>&nbsp;-&nbsp;<bean:write name="loop1" property="module.moduleName" /><i>&nbsp;&nbsp;(&nbsp;&nbsp;<bean:write name="loop1" property="roster.rosterDate" /> - <bean:write name="loop1" property="section.farmBase" />&nbsp;&nbsp;)</i></br>
            	</logic:iterate> 
            	<logic:empty  name="cwtForm" property="archivedRosterList">
            		<i>No rosters available</i>
            	</logic:empty>
  			</logic:equal>
			
        </div>
        <div class="clear">
        </div>
    </div>
    <div class="footer">
        
    </div>
   
</form>
</body>
</html>
