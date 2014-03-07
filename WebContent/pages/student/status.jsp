<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="../../includes/header_info.jsp" flush="true"/>
 
<html:form method="POST" action="Intake">
<div onKeyPress="return checkSubmit(event)">
    <table width="950" border="0" >
	<tr>
		<td width="100%" colspan="2"><b>Student Status: </b><br /><br /></td>
	</tr>
	
	<tr>
		<td colspan="11">
    		<jsp:include page="../../includes/messages.jsp" flush="true"/>
	   </td>
	</tr>
	
    <tr>
    	<td valign="top">
        	<table width="150" cellpadding="0" cellspacing="0" border="0">
            <tr>
            	<td >Supervisor</td>
            </tr>
            <tr>
            	<td>
 					  <html:select property="intake.supervisorId" styleClass="status"> 
                      	<html:option value="0">Select</html:option>
						<html:optionsCollection name="ddl_supervisor" value="value" label="label" />
					  </html:select>
            	</td>
            </tr>
            <tr>
            	<td>Job</td>
            </tr>
            <tr>
            	<td>
                    <html:select property="intake.jobId" styleClass="status"> 
                    	<html:option value="0">Select</html:option>
						<html:optionsCollection name="ddl_job" value="jobId" label="title" />
					</html:select>

            	</td>
            </tr>
           <tr>
            	<td>CWT</td>
            </tr>
            <tr>
            	<td>
                    <html:select property="intake.cwtProgramId" styleClass="status"> 
                    	<html:option value="0">Select</html:option>
                    	<html:option value="1">Not Applicable</html:option>
                    	<html:option value="2">Instructor</html:option>
						<html:optionsCollection name="ddl_program" value="value" label="label" />
					</html:select>
            	</td>
            </tr>
            <tr>
            	<td>Class</td>
            </tr>
            <tr>
            	<td>
  					  <html:select property="intake.class_" styleClass="status"> 
                      	<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_classList" value="value" label="label" />
					  </html:select>
 
            	</td>
            </tr>
            <tr>
            	<td>Area</td>
            </tr>
            <tr>
            	<td>
            		<html:text property="intake.area" size="20" maxlength="45" styleClass="status" />             		
            	</td>
            </tr>
            <tr>
            	<td>Room</td>
            </tr>
            <tr>
            	<td>
            		<html:text property="intake.room" size="20" maxlength="20"/>
            	</td>
            </tr>
            <tr>
            	<td>Bed</td>
            </tr>
            <tr>
            	<td>
            		<html:text property="intake.bed" size="20" maxlength="20"/>
            	</td>
            </tr>
            <tr>
            	<td>Date Last Entered</td>
            </tr>
            <tr>
            	<td>
            		<html:text property="intake.entryDate" size="20" maxlength="12" styleClass="tcal"/>
            	</td>
            </tr>
            <tr>
            	<td>(This is the date they LAST ENTERED the program.  Please check the Student Status History for original entry date.)<br/><br/></td>
            </tr>
            <tr>
            	<td>Archived
            	   <html:select property="intake.archivedFlag" styleClass="select" >
							<html:optionsCollection name="ddl_yesNo" value="value" label="label" />
				   </html:select>
            	   
            	</td>
            </tr>
            <tr>
            	<td>(Archived students will not show up in searches or reports.)</td>
            </tr>
            </table>        
        </td>
       
        <td valign="top">
        	<table width="500" cellpadding="0" cellspacing="0" border="0">
            <tr style="background: silver; text-align: left;">
           		<td width="100"><b>Location</b></td>
                <td width="100"><b>Program</b></td>
                <td width="100"><b>Status</b></td>
                <td width="100"><b>Reason</b></td>
                <td width="100"><b>Date Began</b></td>
                <td width=""><b>Date Ended</b></td>
            </tr>
           <tr>
            	<td><bean:write name="intakeForm" property="currentStatus.farm"/></td>
            	<td><bean:write name="intakeForm" property="currentStatus.phase"/></td>
            	<td><bean:write name="intakeForm" property="currentStatus.programStatus"/></td>
            	<td><bean:write name="intakeForm" property="currentStatus.reason"/></td>
            	<td><bean:write name="intakeForm" property="currentStatus.beginDate" /></td>
            	<td><bean:write name="intakeForm" property="currentStatus.endDate" /></td>
            </tr>
        	<logic:empty name="intakeForm" property="currentStatus">
                     <tr>
                     	<td colspan="6">No Status</td> 
                     </tr>
            </logic:empty>        	
        	
        	<tr>
            	<td colspan="6">
               		 <hr style="border-bottom: 1px dotted #000000;"/>
                </td>
            </tr>
            <tr style="background: silver; text-align: left;">
            	<td colspan="6">Reason for Change of Status:<br /><br /></td>
            </tr>
            <tr style="background: silver; text-align: left;">
            	<td valign="top">                     
                	<html:select property="history.farm" styleClass="status"> 
						<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_farm" value="name" label="name" />
					</html:select>
					&nbsp;&nbsp;
                </td>
                <td valign="top">                     
                	<html:select property="history.phase" styleClass="status"> 
						<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_phase" value="value" label="label" />
					</html:select>
                &nbsp;&nbsp;
                </td>
                <td valign="top" colspan="2"> 
                    <html:select property="history.programStatus" styleClass="status" > 
						<html:option value="">Select</html:option>
						<html:optionsCollection name="ddl_programStatus" value="value" label="label" />
					</html:select>
                
                &nbsp;&nbsp;
                </td>
                <td valign="top"> <html:text property="history.beginDate" size="12" maxlength="12" styleClass="tcal" /></td>
                <td valign="top"> <html:text property="history.endDate" size="12" maxlength="12" styleClass="tcal" /></td>
                </tr>
                <tr style="background: silver; text-align: left;">
                	<td colspan="6">
                    	<html:textarea property="history.reason" cols="93" styleClass="textarea" />
                    </td>
                </tr>
            </tr>            
            </table>
            
            <br /><br />
            <table width="800" cellpadding="0" cellspacing="0" border="0">
            <tr>
            	<td  style="background: silver; text-align: center;color:#000000;font-weight:bold;height=18px;">Student History</td>
            </tr>
            <tr>
            	<table width="800" cellpadding="0" cellspacing="0" >
            		<tr>
                    	<td style="height:20px;border: 1px solid #666;color:#000000;font-weight:bold;padding-left:5px;">From</td>
                        <td style="height:20px;border: 1px solid #666;color:#000000;font-weight:bold;padding-left:5px;">To</td>
                        <td style="height:20px;border: 1px solid #666;color:#000000;font-weight:bold;padding-left:5px;">Location</td>
                        <td style="height:20px;border: 1px solid #666;color:#000000;font-weight:bold;padding-left:5px;">Program</td>
                        <td style="height:20px;border: 1px solid #666;color:#000000;font-weight:bold;padding-left:5px;">Ending Status</td>
                        <td style="height:20px;border: 1px solid #666;color:#000000;font-weight:bold;padding-left:5px;">Reason</td>
                        <td style="height:20px;border: 1px solid #666;color:#000000;font-weight:bold;padding-left:5px;">Action</td>
                     </tr>
                    <logic:iterate id="loop" name="intakeForm" property="studentHistory" indexId="i">
                    
                    
                    <logic:notEqual name="intakeForm" property="editIndex" value="${i }">
                     <tr> 
                     	<td><bean:write name="loop" property="beginDate"/></td>
                        <td><bean:write name="loop" property="endDate"/></td>
                        <td><bean:write name="loop" property="farm"/></td> 
                        <td><bean:write name="loop" property="phase"/></td>
                        <td><bean:write name="loop" property="programStatus"/></td>
                        <td><bean:write name="loop" property="reason"/></td>
                        <td>
                        	<input type="submit" name="action" value="Edit History" onClick="javascript:document.getElementById('editIndex').value='<%=i %>'; document.getElementById('editId').value='<bean:write name="loop" property="studentHistoryId"/>';" class="imageButtonEdit" title="Edit" />
                        	<input type="submit" name="action" value="Delete History" onClick="javascript:document.getElementById('deleteId').value='<bean:write name="loop" property="studentHistoryId"/>'" class="imageButtonDelete" title="Delete" />
                        </td>
                     </tr>
                     </logic:notEqual>
                     
		             <logic:equal name="intakeForm" property="editIndex" value="${i }">
		             <tr> 
	                     	<td><html:text property="editStatus.beginDate" size="12" maxlength="12" styleClass="tcal" /></td>
	                        <td><html:text property="editStatus.endDate" size="12" maxlength="12" styleClass="tcal" /></td>
	                        <td><html:select property="editStatus.farm" styleClass="status"> 
									<html:option value="">Select</html:option>
									<html:optionsCollection name="ddl_farm" value="name" label="name" />
								</html:select>
							</td> 
	                        <td><html:select property="editStatus.phase" styleClass="status"> 
									<html:option value="">Select</html:option>
									<html:optionsCollection name="ddl_phase" value="value" label="label" />
								</html:select>
							</td>
	                        <td><html:select property="editStatus.programStatus" styleClass="status" > 
									<html:option value="">Select</html:option>
									<html:optionsCollection name="ddl_programStatus" value="value" label="label" />
								</html:select>
							</td>
	                        <td><html:text property="editStatus.reason" size="10" maxlength="500" styleClass="textbox" /></td>
	                        <td>
	                        	<input type="submit" name="action" value="Save History Change" onClick="javascript:document.getElementById('editId').value='<bean:write name="loop" property="studentHistoryId"/>'" class="imageButtonSaveEdit" title="Save Edits"/>
	                        </td>
		               </tr>
		              </logic:equal>
                     
                     
                     </logic:iterate>
                     <logic:empty name="intakeForm" property="studentHistory">
                     <tr>
                     	<td colspan="7">No history</td> 
                     </tr>
                     </logic:empty>

               </table>
             <!--   
            <br /><br />
            <table width="700" cellpadding="0" cellspacing="0" border="0">
            <tr>
            	<td  style="background: silver; text-align: center;color:#000000;font-weight:bold;height=18px;">Pass History</td>
            </tr>
            <tr>
            	<table width="700" cellpadding="0" cellspacing="0" >
            		<tr>
                    	<td style="height:20px;border: 1px solid #666;color:#000000;font-weight:bold;padding-left:5px;">Pass Date</td>
                        <td style="height:20px;border: 1px solid #666;color:#000000;font-weight:bold;padding-left:5px;">Hours</td>
                        <td style="height:20px;border: 1px solid #666;color:#000000;font-weight:bold;padding-left:5px;">Type</td>
                        <td style="height:20px;border: 1px solid #666;color:#000000;font-weight:bold;padding-left:5px;">Del</td>
                     </tr>
                     <tr>
                    	<td style="background: silver;height:20px;border: 1px solid #666;color:#000000;font-weight:bold;padding-left:5px;">
                    		<html:text property="passHistory.passDate" size="12" maxlength="12" styleClass="tcal"/>
                    	</td>
                        <td style="background: silver;height:20px;border: 1px solid #666;color:#000000;font-weight:bold;padding-left:5px;">
                                <html:text property="passHistory.hours" size="3" maxlength="3"/>
                		</td>
                        <td colspan="2" style="background: silver;height:20px;border: 1px solid #666;color:#000000;font-weight:bold;padding-left:5px;">
                               <html:select property="passHistory.passType" >
                               		<html:option value="">Select</html:option> 
									<html:optionsCollection name="ddl_passType" value="value"  label="label" />
								</html:select>  
						</td>
                        
                     </tr>
                     <logic:notEmpty name="intakeForm" property="studentPassHistory">
	                     <logic:iterate id="loop" name="intakeForm" property="studentPassHistory" indexId="i">
	                     <tr> 
	                     	<td><bean:write name="loop" property="passDate"/></td>
	                        <td><bean:write name="loop" property="hours"/></td>
	                        <td><bean:write name="loop" property="passType"/></td> 
	                        <td><input type="submit" name="action" value="Delete Pass History" onClick="javascript:document.getElementById('deleteId').value='<bean:write name="loop" property="studentPassHistoryId"/>'" class="imageButtonDelete"/></td>
	                     </tr>
	                     </logic:iterate>
	                  </logic:notEmpty>    
	                  <logic:empty name="intakeForm" property="studentPassHistory">
	                     <tr>
	                     	<td colspan="7">No history</td> 
	                     </tr>
	                  </logic:empty>
                    
                </table>
                
                -->
                
                
 
                
            </tr>
            </table>
        </td>
    </tr>
    </table>
	<br /><br />
    <div align="center">
   		<input type="submit" name="action" value="Save" class="imageButtonSave" title="Save Information" />&nbsp;
   </div>





    <div class="footer">
        
    </div>
     <html:hidden property="pageSource" value="status"/>  
     <html:hidden property="deleteId" styleId="deleteId" />
     <html:hidden property="editId" styleId="editId" />
     <html:hidden property="editIndex" styleId="editIndex" />
    </div>
</html:form>
</body>
</html>