<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="../../includes/header.jsp" flush="true"/>

<html:form  action="/Report?action=passList">
    <h2>
        Pass Report
    </h2>
    <br/><br/>  
            <div align="left">
            
            
            <table width="100%" border="0">
                <tr>
                <td width="200"><b>Pass Date&nbsp;<i>(mm/dd/yyyy)</i></b></br>
                	From:<html:text property="beginPassDate" size="15" maxlength="10" styleClass="tcal" />
                	&nbsp;&nbsp;To:<html:text property="endPassDate" size="15" maxlength="10" styleClass="tcal" />
                	<input type="submit" name="action" value="Run Report" class="imageButtonSave" title="Run Report" />
                </td>
              </tr>                
             </table>
            	
            
         
    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
    <div class="footer">
        Faith Farm Ministries &copy;2013
    </div>
   
</html:form>
</body>
</html>
