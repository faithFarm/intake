<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<jsp:include page="../../../includes/header.jsp" flush="true"/>

<html:form method="POST" action="/Cwt.do">
    <h2>
        Course Work Therapy Roster Search
    </h2>
 	</br></br>
 	<font style="color:red"><b><%=session.getAttribute("error") %></b></font></br></br>
 	<table width="40%" cellpadding="0" cellspacing="0">
 		<tr>
 			<td width="150">CWT</td>
 			<td>
 				<html:select name="cwtForm" property="programId"  >
					<html:option value="">select</html:option>
						<html:optionsCollection name="ddl_program" value="value" label="label" />					
			   </html:select>
			</td>
 		</tr><tr>
 			<td width="150">Module Sequence</td>
 			<td>
 				<html:select name="cwtForm" property="moduleSequence"  >
					<html:option value="">select</html:option>
						<html:optionsCollection name="ddl_moduleSeq" value="value" label="label" />					
			   </html:select>
			</td>
 		</tr>
 		<tr>
 			<td width="150">CWT Module</td>
 			<td>
 				<html:select name="cwtForm" property="moduleId"  >
					<html:option value="">select</html:option>
						<html:optionsCollection name="ddl_module" value="value" label="label" />					
			   </html:select>
			</td>
 		</tr>
 		<tr>
 			<td width="80">Class Date</td>
 			<td><html:text property="searchDate" size="15" maxlength="10" styleClass="tcal" /></td>
 		</tr>
 		<!-- 
 		<tr>
 			<td width="80">Archived</td>
 			<td>
 				<html:select name="cwtForm" property="searchArchivedFlag"  >
						<html:option value="No" >No</html:option>
						<html:option value="Yes" >Yes</html:option>
			  	 </html:select>
			</td>
 		</tr>
 		 -->
 		<tr>
 			<td colspan="2" align="left"></br><input type="submit" name="action" value="Search"/><input type="reset" name="action" value="Clear"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="action" value="Create Roster"/></td>
 		</tr>
 	
 	</table>
 
 
 
       </div>
        <div class="clear">
        </div>
    </div>
    <div class="footer">
        
    </div>
   
</html:form>
</body>
</html>
 