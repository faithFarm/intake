<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="../../includes/header.jsp" flush="true"/>


    <h2>
        Course Work Therapy <logic:equal name="loginForm" property="systemUser.userRole" value="CwtInstructor" > - My Rosters</logic:equal>
    </h2>
    		<logic:equal name="loginForm" property="systemUser.userRole" value="Administrator" >
            
            <p>
            Now you can manage programs, metrics, modules, exams, and certifications for Faith Farm students enrolled in the CWT Program.
            <br />
            </p>
            <ul style="color: Blue">
                <li><a href="<%=request.getContextPath()%>/Cwt.do?action=programs">UBIT Skills</a></li>
                <li><a href="<%=request.getContextPath()%>/Cwt.do?action=metrics">Skill Metrics</a></li>
                <li><a href="<%=request.getContextPath()%>/Cwt.do?action=modules">Class Modules</a></li>
                <li><a href="<%=request.getContextPath()%>/Cwt.do?action=sections">Module Sections</a></li>
                <li><a href="<%=request.getContextPath()%>/Cwt.do?action=archives">Roster Search</a></li>
            </ul>
            
            <ul style="color: Blue">
                <li><a href="<%=request.getContextPath()%>/Cwt.do?action=departments">Departments</a></li>
                <li><a href="<%=request.getContextPath()%>/Cwt.do?action=jobs">Jobs</a></li>
                <li><a href="<%=request.getContextPath()%>/Cwt.do?action=supervisors">Supervisors</a></li>
             </ul>
        
			<br/><br/>
			
	        <p>
            Rotation <i>(Applies only to Orientation to Class 5)</i>
            <ul style="color: Blue">
                <li><a href="<%=request.getContextPath()%>/Report.do?action=Rotate&farm=Boynton Beach">Course Rotation for Boynton Beach</a></i>
                <li><a href="<%=request.getContextPath()%>/Report.do?action=Rotate&farm=Fort Lauderdale">Course Rotation for Fort Lauderdale</a></i>
                <li><a href="<%=request.getContextPath()%>/Report.do?action=Rotate&farm=Okeechobee">Course Rotation for Okeechobee</a></i>
                <li><a href="<%=request.getContextPath()%>/Report.do?action=Rotate&farm=Women's Home">Course Rotation for Women's Home</a></i>
            </ul>
            </logic:equal>
            <logic:equal name="loginForm" property="systemUser.userRole" value="CwtInstructor" >
            </br></br> 
            <b>Current Rosters</b></br>
            	<logic:iterate id="loop" name="cwtForm" property="currentRosterList" indexId="i">
            	   <a href="<%=request.getContextPath()%>/CwtRoster.do?action=Roster&type=Section&id=<bean:write name="loop" property="section.moduleOfferingId" />"><img src="<%=request.getContextPath()%>/images/local/Edit.gif" border="0" alt="Manage Roster" title="Manage Roster"/></a>
            	   <a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=Print&type=Section&id=<bean:write name="loop" property="section.moduleOfferingId" />');"><img src="<%=request.getContextPath()%>/images/local/sm_printer.png" border="0" alt="Print Roster" title="Print Roster"/></a>
            	   &nbsp;&nbsp;
            	   <b><bean:write name="loop" property="program.programName" /></b>&nbsp;-&nbsp;<bean:write name="loop" property="module.moduleName" />&nbsp;&nbsp;<i>(&nbsp;<bean:write name="loop" property="section.effectiveDate" /> @ <bean:write name="loop" property="section.meetingTimes" /> - <bean:write name="loop" property="section.meetingDays" /> at <bean:write name="loop" property="section.location" />&nbsp;)</i> - <bean:write name="loop" property="section.farmBase" /></br>
            	</logic:iterate> 
            	<logic:empty  name="cwtForm" property="currentRosterList">
            		<i>No rosters available</i>
            	</logic:empty>
            </br></br> 
            <b>Archived Rosters</b></br>
            	<logic:iterate id="loop1" name="cwtForm" property="archivedRosterList" indexId="i">
            	   <a href="<%=request.getContextPath()%>/CwtRoster.do?action=Roster&type=Section&id=<bean:write name="loop1" property="section.moduleOfferingId" />"><img src="<%=request.getContextPath()%>/images/local/Edit.gif" border="0" alt="Manage Roster" title="Manage Roster"/></a>
            	   <a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=Print&type=Section&id=<bean:write name="loop1" property="section.moduleOfferingId" />');"><img src="<%=request.getContextPath()%>/images/local/sm_printer.png" border="0" alt="Print Roster" title="Print Roster"/></a>
            	   &nbsp;&nbsp;
            	   <b><bean:write name="loop1" property="program.programName" /></b>&nbsp;-&nbsp;<bean:write name="loop1" property="module.moduleName" />&nbsp;&nbsp;<i>(&nbsp;<bean:write name="loop1" property="section.effectiveDate" /> @ <bean:write name="loo1p" property="section.meetingTimes" /> - <bean:write name="loop1" property="section.meetingDays" /> at <bean:write name="loop1" property="section.location" />&nbsp;)</i> - <bean:write name="loop1" property="section.farmBase" /></br>
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
