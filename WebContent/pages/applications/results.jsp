<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%@ page import="java.util.ArrayList" %>

<jsp:include page="../../includes/header.jsp" flush="true"/>


    <h2>
        Application Search Results
    </h2>
    <%
    int byn=0, ftl=0, oke=0, ewh=0;
    %>
     <logic:notEmpty name="intakeForm" property="message">
  		    <table width="800" cellpadding="0" cellspacing="0" border="0">
                  <tr>
                  	<td width="18">
                  		<img src="<%=request.getContextPath()%>/images/local/message.png" />
                  	</td>
                  	<td class="instructions" align="left">
                  		<i><bean:write name="intakeForm" property="message" /></i>
                  	</td>
                  </tr>
              </table>
              </br>
     </logic:notEmpty>
    
    <% String rowClass="searchRowOdd2"; %>
     <table width="100%" cellpadding="0" cellspacing="0" border="0">
     <tr>
         <td class="colHeading2" width="30"></td>
         <td class="colHeading2" width="150">Submission Date</td>
         <td class="colHeading2" width="270">Name</td>
         <td class="colHeading2" width="100">SSN</td>
         <td class="colHeading2" width="100">DOB</td>
         <td class="colHeading2" width="100">Valid DL</td>
         <td class="colHeading2" width="200">Farm</td>
         <td class="colHeading2" width="140">Status</td>
         <td class="colHeading2" width="140">Arrival Date</td>
         <td class="colHeading2" width="200">Response</td>
    </tr>
     <logic:notEmpty name="intakeForm" property="applicantList" >
	
       <logic:iterate id="loop" name="intakeForm" property="applicantList">
       <% rowClass="searchRowOdd2"; %>
       <logic:equal name="loop" property="reapplyFlag" value="Yes">
	       		<% rowClass="searchRowHighlight"; %>
	   </logic:equal>
	    <tr>
	       <td class="<%=rowClass%>">
	                    	 <a href="<%=request.getContextPath()%>/Intake.do?action=Edit&key=<bean:write name="loop" property="intakeId"/>" style="text-decoration:none">
	                    	 <img src="<%=request.getContextPath()%>/images/local/Edit.gif" width="16" height="14" border="0"/>&nbsp;</a>	                   		 
	      </td>
	       <td class="<%=rowClass%>"><bean:write name="loop" property="applicationSubmissionDate"/></td>
	       
	       <td class="<%=rowClass%>">
	       <bean:write name="loop" property="firstname"/>&nbsp;<bean:write name="loop" property="mi"/>&nbsp;<bean:write name="loop" property="lastname"/></td>
	       <td class="<%=rowClass%>"><bean:write name="loop" property="encryptedSsn"/></td>
	       <td class="<%=rowClass%>"><bean:write name="loop" property="dob"/></td>
	       <td class="<%=rowClass%>"><bean:write name="loop" property="dlFlag"/></td>
	       <td class="<%=rowClass%>"><bean:write name="loop" property="farmBase"/></td>
	       <td class="<%=rowClass%>"><bean:write name="loop" property="applicationStatus"/></td>
	       <td class="<%=rowClass%>"><bean:write name="loop" property="arrivalDate"/></td>
	       <td class="<%=rowClass%>"><bean:write name="loop" property="responseStatus"/></td>
	   </tr>
	
   		<logic:equal name="loop" property="farmBase" value="Boynton Beach">
   			<% byn++;  %>
   		</logic:equal>
   		<logic:equal name="loop" property="farmBase" value="Fort Lauderdale">
   			<% ftl++;  %>
   		</logic:equal>
   		<logic:equal name="loop" property="farmBase" value="Okeechobee">
   			<% oke++;  %>
   		</logic:equal>
   		<logic:equal name="loop" property="farmBase" value="Women's Home">
   			<% ewh++;  %>
   		</logic:equal>
   </logic:iterate>
   <tr>
   		<td colspan="9"></br><i>Applications:</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Boynton Beach=<b><%=byn %></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Fort Lauderdale=<b><%=ftl %></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Okeechobee=<b><%=oke %></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Women's Home=<b><%=ewh %></b>
   			</br></br>* highlighted rows indicate a returning student or previous applicant</i>
   		</td>
   </tr>
   </logic:notEmpty>
   <logic:empty name="intakeForm" property="applicantList">
       <tr>
           <td colspan="9">No Applications</td> 
       </tr>
   </logic:empty>
    </table>
    <div class="footer">
    </div>
   
</form>
</body>
</html>