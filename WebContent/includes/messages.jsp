<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<style>

#errors {
	font: 1.15em Arial;
	color: #c30923;
	text-align:left;
	padding-left:0px;
	vertical-align:top;
	padding-top:0px;
}

#info {
	font: 1.15em Arial;
	color: #3c7ac0;
	text-align:left;
	padding-left:0px;
	vertical-align:top;
	padding-top:0px;
}

#success {
	font: 1.15em Arial;
	color: #77c06b;
	text-align:left;
	padding-left:0px;
	vertical-align:top;
	padding-top:0px;
}

#warning {
	font: 1.15em Arial;
	color: #878314;
	text-align:left;
	padding-left:0px;
	vertical-align:top;
	padding-top:0px;
}

</style>



<logic:equal name="intakeForm" property="messageType" value="error">
	<table width="437" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td>
					<div id="errors">
						
						<logic:iterate id="messages" name="intakeForm" property="messages">
							<img src="<%=request.getContextPath() %>/images/local/error.png"/>&nbsp;<bean:write name="messages" property="property"/>&nbsp;<bean:write name="messages" property="message"/></br>
						</logic:iterate>
						
					</div>
				</td>
			</tr>
	 </table>
</logic:equal>

<logic:equal name="intakeForm"  property="messageType" value="info">
		<table width="434" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
					<div id="info">
						<img src="<%=request.getContextPath() %>/images/local/info.png"/>
						<logic:iterate id="messages" name="intakeForm" property="messages">
							<bean:write name="messages" property="property"/>&nbsp;<bean:write name="messages" property="message"/></br>
						</logic:iterate>
					</div>
			</td>
		</tr>
 		</table>
</logic:equal>
<logic:equal name="intakeForm"  property="messageType" value="success">
		<table width="434" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td valign="top">
					<div id="success"> 
						<img src="<%=request.getContextPath() %>/images/local/success.png"/>
						<logic:iterate id="messages" name="intakeForm" property="messages">
							<bean:write name="messages" property="property"/>&nbsp;<bean:write name="messages" property="message"/></br>
						</logic:iterate>
						</br>
					</div>
			</td>
		</tr>
 		</table>
</logic:equal>
<logic:equal name="intakeForm" property="messageType" value="warning">
		<table width="434" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
					<div id="warning">
						<img src="<%=request.getContextPath() %>/images/local/warning.jpg"/>
						<logic:iterate id="messages" name="intakeForm" property="messages">
							<bean:write name="messages" property="property"/>&nbsp;<bean:write name="messages" property="message"/></br>
						</logic:iterate>
						</br>
					</div>
			</td>
		</tr>
 		</table>
</logic:equal>

