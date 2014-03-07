<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<style>

#errors {
	font: 1.15em Arial;
	color: #c30923;
	text-align:left;
	padding-left:40px;
	vertical-align:top;
	padding-top:0px;
}

#info {
	font: 1.15em Arial;
	color: #3c7ac0;
	text-align:left;
	padding-left:40px;
	vertical-align:top;
	padding-top:0px;
}

#success {
	font: 1.15em Arial;
	color: #77c06b;
	text-align:left;
	padding-left:40px;
	vertical-align:top;
	padding-top:0px;
}

#warning {
	font: 1.15em Arial;
	color: #878314;
	text-align:left;
	padding-left:40px;
	vertical-align:top;
	padding-top:0px;
}

</style>



<logic:equal name="cwtForm" property="messageType" value="error">
	<table width="437" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td width="437" height="66" background="<%=request.getContextPath()%>/images/standard/error_header.jpg"></td>
			</tr>
			<tr>
				<td background="<%=request.getContextPath()%>/images/standard/error_body.jpg" valign="top">
					<div id="errors">
						<ul>
						<logic:iterate id="messages" name="cwtForm" property="messages">
							<li><bean:write name="messages" property="property"/>&nbsp;<bean:write name="messages" property="message"/></li>
						</logic:iterate>
						</ul>
					</div>
				</td>
			</tr>
	 		<tr>
				<td width="437" height="12" background="<%=request.getContextPath()%>/images/standard/error_footer.jpg"></td>
			</tr>
	</table>
</logic:equal>

<logic:equal name="cwtForm"  property="messageType" value="info">
		<table width="434" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="434" height="65" background="<%=request.getContextPath()%>/images/standard/info_header.jpg"></td>
		</tr>
		<tr>
			<td background="<%=request.getContextPath()%>/images/standard/info_body.jpg" valign="top">
					<div id="info">
						<ul>
						<logic:iterate id="messages" name="cwtForm" property="messages">
							<li><bean:write name="messages" property="property"/>&nbsp;<bean:write name="messages" property="message"/></li>
						</logic:iterate>
						</ul>
					</div>
			</td>
		</tr>
 		<tr>
			<td width="434" height="10" background="<%=request.getContextPath()%>/images/standard/info_footer.jpg"></td>
		</tr>
		</table>
</logic:equal>
<logic:equal name="cwtForm"  property="messageType" value="success">
		<table width="434" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="434" height="65" background="<%=request.getContextPath()%>/images/standard/success_header.jpg"></td>
		</tr>
		<tr>
			<td background="<%=request.getContextPath()%>/images/standard/success_body.jpg" valign="top">
					<div id="success">
						<ul>
						<logic:iterate id="messages" name="cwtForm" property="messages">
							<li><bean:write name="messages" property="property"/>&nbsp;<bean:write name="messages" property="message"/></li>
						</logic:iterate>
						</ul>
					</div>
			</td>
		</tr>
 		<tr>
			<td width="434" height="10" background="<%=request.getContextPath()%>/images/standard/success_footer.jpg"></td>
		</tr>
		</table>
</logic:equal>
<logic:equal name="cwtForm" property="messageType" value="warning">
		<table width="434" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="434" height="65" background="<%=request.getContextPath()%>/images/standard/warning_header.jpg"></td>
		</tr>
		<tr>
			<td background="<%=request.getContextPath()%>/images/standard/warning_body.jpg" valign="top">
					<div id="warning">
						<ul>
						<logic:iterate id="messages" name="cwtForm" property="messages">
							<li><bean:write name="messages" property="property"/>&nbsp;<bean:write name="messages" property="message"/></li>
						</logic:iterate>
						</ul>
					</div>
			</td>
		</tr>
 		<tr>
			<td width="434" height="10" background="<%=request.getContextPath()%>/images/standard/warning_footer.jpg"></td>
		</tr>
		</table>
</logic:equal>

