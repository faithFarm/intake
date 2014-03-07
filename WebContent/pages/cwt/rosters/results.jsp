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
            	   <a href="<%=request.getContextPath()%>/CwtRoster.do?action=Roster&archivedFlag=No&type=Section&id=<bean:write name="loop" property="sectionId" />">
            	       <b><bean:write name="loop" property="moduleName" /> - Module <bean:write name="loop" property="sequence" /></b><i>&nbsp;&nbsp;(&nbsp;&nbsp;<bean:write name="loop" property="rosterDate" /> - <bean:write name="loop" property="farmBase" />&nbsp;&nbsp;)</i>
            	   </a>
            	    </br>
            	</logic:iterate> 
            	<logic:empty  name="cwtForm" property="currentRosterList">
            		<i>No rosters available</i>
            	</logic:empty>
            </br></br> 
            </logic:equal>
            
            <logic:equal  name="cwtForm" property="searchArchivedFlag" value="Yes">
            <b>Rosters</b></br>
            	<logic:iterate id="loop1" name="cwtForm" property="archivedRosterList" indexId="i">
            	   <a href="<%=request.getContextPath()%>/CwtRoster.do?action=Roster&archivedFlag=Yes&type=Section&id=<bean:write name="loop1" property="sectionId" />&date=<bean:write name="loop1" property="rosterDate" />">
            	   	  <b><bean:write name="loop1" property="moduleName" /> - Module <bean:write name="loop1" property="sequence" /></b><i>&nbsp;&nbsp;(&nbsp;&nbsp;<bean:write name="loop1" property="rosterDate" /> - <bean:write name="loop1" property="farmBase" />&nbsp;&nbsp;)</i></br>
            	   </a>
            	</logic:iterate> 
            	<logic:empty  name="cwtForm" property="archivedRosterList">
            		<i>No rosters available</i>
            	</logic:empty>
  			</logic:equal>
		</br>
	           <div align="center">
	            	<a href="<%=request.getContextPath()%>/Cwt.do?action=archives">Search</a>
    	        </div>
 
		</br></br></br>	
        </div>
        <div class="clear">
        </div>
    </div>
    
    <div class="footer">
        
    </div>
   
</form>
</body>
</html>
