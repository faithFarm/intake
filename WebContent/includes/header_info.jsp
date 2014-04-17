<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="org.faithfarm.sms.domain.SystemUser" %>
<jsp:useBean id="loginForm" class="org.faithfarm.sms.struts.form.LoginForm" scope="session" />
<%  
    SystemUser user = null;
	try {
	  user = (SystemUser)session.getAttribute("system_user"); 
	  if (user==null) user = new SystemUser();
	} catch (Exception e) {
		user=new SystemUser();
	}

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head><title>
	Faith Farm Student Information System
</title>
<meta http-equiv="refresh" content="14400;url=/intake/pages/security/index.jsp" />

	<link href="<%=request.getContextPath()%>/styles/site.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/styles/tcal.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/analytics.js"></script>
   
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/tcal.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/chart.js"></script>

    <script  type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.4.1.min.js">
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.format.1.02.js">
    </script>    
    <script  type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dateentry.min.js">
    </script>    
    <style type="text/css">
        @import "/Styles/jquery.datepick.css";
    </style>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.datepick.min.js"></script>    
 
 
 <script language="javascript" type="text/javascript">
  
   function moveOnMax(field,nextFieldID){
	  if(field.value.length >= field.maxLength){
	    document.getElementById(nextFieldID).focus();
	  }
	}

function maskPhone(e,f){

  	
	var len = f.value.length;
	var key = whichKey(e);
	if(key>47 && key<58)
	{
		if( len==3 )f.value=f.value+'-'
		else if(len==7 )f.value=f.value+'-'
		else f.value=f.value;
	}
	else{
		f.value = f.value.replace(/[^0-9-]/,'')
		f.value = f.value.replace('--','-')
	}
}

function maskSsn(e,f){

  	
	var len = f.value.length;
	var key = whichKey(e);
	if(key>47 && key<58)
	{
		if( len==3 )f.value=f.value+'-'
		else if(len==6 )f.value=f.value+'-'
		else f.value=f.value;
	}
	else{
		f.value = f.value.replace(/[^0-9-]/,'')
		f.value = f.value.replace('--','-')
	}
}
 
function whichKey(e) {
   	
	var code;
	if (!e) var e = window.event;
	if (e.keyCode) code = e.keyCode;
	else if (e.which) code = e.which;
	return code
//	return String.fromCharCode(code);
}


</script>


  		<logic:equal name="loginForm" property="systemUser.farmBase" value="Boynton Beach" >
			<logic:equal name="intakeForm" property="intake.farmBase" value="Boynton Beach">
				 <script language="javascript" type="text/javascript"> 
					function checkSubmit(e)
						{
						   if(e && e.keyCode == 13)
						   {
							  document.getElementById('action').value='Save';
							  alert('action='+document.getElementById('action').value);
							  document.forms[0].submit();
						   }
						}
				   </script>
			</logic:equal>
			<logic:notEqual name="intakeForm" property="intake.farmBase" value="Boynton Beach">
				 <script language="javascript" type="text/javascript"> 
					function checkSubmit(e)
						{
						   if(e && e.keyCode == 13)
						   {
						      //document.forms[0].submit();
						   }
						}
				   </script>
			</logic:notEqual>
		</logic:equal>
		
		<logic:equal name="loginForm" property="systemUser.farmBase" value="Okeechobee" >
			<logic:equal name="intakeForm" property="intake.farmBase" value="Okeechobee">
				 <script language="javascript" type="text/javascript"> 
					function checkSubmit(e)
						{
						   if(e && e.keyCode == 13)
						   {
						      document.forms[0].submit();
						   }
						}
				   </script>
			</logic:equal>
			<logic:notEqual name="intakeForm" property="intake.farmBase" value="Okeechobee">
				 <script language="javascript" type="text/javascript"> 
					function checkSubmit(e)
						{
						   if(e && e.keyCode == 13)
						   {
						      //document.forms[0].submit();
						   }
						}
				   </script>
			</logic:notEqual>
		</logic:equal>
		
		<logic:equal name="loginForm" property="systemUser.farmBase" value="Fort Lauderdale" >
			<logic:equal name="intakeForm" property="intake.farmBase" value="Fort Lauderdale">
				 <script language="javascript" type="text/javascript"> 
					function checkSubmit(e)
						{
						   if(e && e.keyCode == 13)
						   {
						      document.forms[0].submit();
						   }
						}
				   </script>
			</logic:equal>
			<logic:notEqual name="intakeForm" property="intake.farmBase" value="Fort Lauderdale">
				 <script language="javascript" type="text/javascript"> 
					function checkSubmit(e)
						{
						   if(e && e.keyCode == 13)
						   {
						      //document.forms[0].submit();
						   }
						}
				   </script>
			</logic:notEqual>
		</logic:equal>
		
		<logic:equal name="loginForm" property="systemUser.farmBase" value="Women's Home" >
			<logic:equal name="intakeForm" property="intake.farmBase" value="Women's Home">
				 <script language="javascript" type="text/javascript"> 
					function checkSubmit(e)
						{
						   if(e && e.keyCode == 13)
						   {
						      document.forms[0].submit();
						   }
						}
				   </script>
			</logic:equal>
			<logic:notEqual name="intakeForm" property="intake.farmBase" value="Women's Home">
					 <script language="javascript" type="text/javascript"> 
					function checkSubmit(e)
						{
						   if(e && e.keyCode == 13)
						   {
						      //document.forms[0].submit();
						   }
						}
			   </script>
			</logic:notEqual>
		</logic:equal>

 <div class="page">
        <div class="header">
            <div class="title">
               <h1>
                   Faith Farm Student Management 2.0
                </h1> 
            </div>
            <div class="loginDisplay">
                	<% if (user.getUsername()!=null) { %>
                     <table width="680" cellpadding="0" cellspacing="0" border="0">
                	<tr>
                		<td align="right" valign="center">
	                	    <bean:write name="loginForm" property="systemUser.username" />@<bean:write name="loginForm" property="systemUser.farmBase" /> </b>
                	    </td>
                        <td valign="center" width="25">
	                    	<a href="<%=request.getContextPath()%>/Login.do?action=logout" ><img src="<%=request.getContextPath() %>/images/local/logout.png" style="width:15px;height:auto;" border="0" alt="Logout" title="Logout"></img></a>
	                    </td>
	                    <td valign="center" width="25"> 	
                   			<a href="<%=request.getContextPath()%>/Login.do?action=PasswordReset" ><img src="<%=request.getContextPath() %>/images/local/icon_changePassword.png" style="width:20px;height:auto;" border="0" alt="Change Password" title="Change Password"></img></a>
	                    </td>
                    </tr>
                    </table>
                    <% } else { %>
                        [ <a href="<%=request.getContextPath()%>/login" >Log In</a> ]
                    <% } %>
            </div>
   <div class="clear hideSkiplink"> 
    
		<div class="menu" >	
		<ul>
		<logic:equal name="loginForm" property="systemUser.userRole" value="Administrator" >
    		<li><a href="<%=request.getContextPath()%>/Intake.do?action=personal" >Personal</a></li>
      		<li><a href="<%=request.getContextPath()%>/Intake.do?action=religious" >Spiritual</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=substance" >Substance</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=health" >Health</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=legal" >Legal</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=employment" >Employment</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=status" >Status</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=pass" >Pass</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=discipline" >Discipline</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=cwt" >CWT</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=Search" >Search</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=SearchApps">Applications</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=HelpDesk">Help Desk</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=Home" >Home</a></li>
        </logic:equal>
        <logic:equal name="loginForm" property="systemUser.userRole" value="StudentAssistant" >
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=pass" >Pass</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=discipline" >Discipline</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=Search" >Search</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=HelpDesk">Help Desk</a></li>
            <li><a href="<%=request.getContextPath()%>/Intake.do?action=Home" >Home</a></li>
    	</logic:equal>
        </ul>
		<table>
    	<tr>
    		 <td width="33"></td>
		     <td><font style="color:#FFFFFF">
		     <b>Student Id:</b>&nbsp;<font style="color:#aeadad"><bean:write name="intakeForm" property="intake.intakeId" /></font>
		     &nbsp;&nbsp;&nbsp;
		     <b>Student Name:</b>&nbsp;<font style="color:#aeadad"><bean:write name="intakeForm" property="intake.firstname" />&nbsp;<bean:write name="intakeForm" property="intake.lastname" /></font>
		     &nbsp;&nbsp;&nbsp;
		     <font style="color:#FFFFFF"><b>SSN:</b>&nbsp;<font style="color:#aeadad"><bean:write name="intakeForm" property="intake.encryptedSsn" /></font>
		     &nbsp;&nbsp;&nbsp;
		     <%
		     /*
		     String sDate = IntakeForm.getIntake().getCreationDate();
		    
		     try {
		    	Long lDate=new Long("0");
		    	 
		    	if (sDate.length()>0) {
		    		lDate=new Long(sDate);
		    		sDate = v8r.convertEpoch(lDate);
		    	}
		     } catch (Exception e) { 
		    	 System.out.println(e.getMessage());
		     }*/
		     %>
		     <font style="color:#FFFFFF"><b>Date Entered:</b>&nbsp;<font style="color:#aeadad"><bean:write name="intakeForm" property="intake.entryDate" /></font>
		     </td>
		     </tr>
		     <tr>
		      <td width="33"></td>
		     <td>
		     <a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=application');" style="color:#19fd01"><b>Print Application</b></a>&nbsp;&nbsp;&nbsp;
		     <a href="javascript:window.open('<%=request.getContextPath()%>/pages/student/card.jsp');" style="color:#19fd01"><b>Print Card</b></a>
		     <!-- <a href="<%=request.getContextPath()%>/pages/student/info.jsp" style="color:#19fd01""><b>Print Student Information</b></a> -->
		     
		     <!-- Permission for Boynton Beach -->
		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Accepted">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Boynton Beach">
		     			<% if ("Boynton Beach".equals(user.getFarmBase())) { %>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Admit" style="color:#19fd01"><b>Admit To Program</b></a>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Unaccept" style="color:#19fd01"><b>Unaccept</b></a>
		        		<% } %>
		     	</logic:equal>
		     </logic:equal>
		     
		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Pending">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Boynton Beach">
		     			<% if ("Boynton Beach".equals(user.getFarmBase())) { %>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Accept" style="color:#19fd01"><b>Accept Application</b></a>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Deny" style="color:#19fd01"><b>Deny Application</b></a>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Waitlist" style="color:#19fd01"><b>Waitlist</b></a>
 							&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Withdraw" style="color:#19fd01"><b>Withdraw</b></a>
		        			&nbsp;&nbsp;&nbsp;<font style="color:#FFFFFF">Transfer&nbsp;[&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Transfer&tfarm=OKE" style="color:#19fd01"><b>OKE</b></a>
		        									<a href="<%=request.getContextPath()%>/Intake.do?action=Transfer&tfarm=FTL" style="color:#19fd01"><b>FTL</b></a>
		        									<a href="<%=request.getContextPath()%>/Intake.do?action=Transfer&tfarm=EHW" style="color:#19fd01"><b>EHW</b></a>
		        									]</font>
		        		<% } %>
		     	</logic:equal>
		     </logic:equal>
		     
		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Denied">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Boynton Beach">
		     			<% if ("Boynton Beach".equals(user.getFarmBase())) { %>
		    				&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Reinstate" style="color:#19fd01"><b>Reinstate Application</b></a>
		     			<% } %>
		     	</logic:equal>
		     </logic:equal>

		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Withdrawn">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Boynton Beach">
		     			<% if ("Boynton Beach".equals(user.getFarmBase())) { %>
		    				&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Reinstate" style="color:#19fd01"><b>Reinstate Application</b></a>
		     			<% } %>
		     	</logic:equal>
		     </logic:equal>

		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Waitlist">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Boynton Beach">
		     			<% if ("Boynton Beach".equals(user.getFarmBase())) { %>
		    				&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Accept" style="color:#19fd01"><b>Accept Application</b></a>
 							&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Withdraw" style="color:#19fd01"><b>Withdraw</b></a>
		     			<% } %>
		     	</logic:equal>
		     </logic:equal>
   			  
		     
		      <!-- Permission for Fort Lauderdale -->
		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Accepted">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Fort Lauderdale">
		     			<% if ("Fort Lauderdale".equals(user.getFarmBase())) { %>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Admit" style="color:#19fd01"><b>Admit To Program</b></a>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Unaccept" style="color:#19fd01"><b>Unaccept</b></a>
		        		<% } %>
		     	</logic:equal>
		     </logic:equal>
		     
		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Pending">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Fort Lauderdale">
		     			<% if ("Fort Lauderdale".equals(user.getFarmBase())) { %>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Accept" style="color:#19fd01"><b>Accept Application</b></a>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Deny" style="color:#19fd01"><b>Deny Application</b></a>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Waitlist" style="color:#19fd01"><b>Waitlist</b></a>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Withdraw" style="color:#19fd01"><b>Withdraw</b></a>
		        			&nbsp;&nbsp;&nbsp;<font style="color:#FFFFFF">Transfer&nbsp;[&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Transfer&tfarm=OKE" style="color:#19fd01"><b>OKE</b></a>
		        									<a href="<%=request.getContextPath()%>/Intake.do?action=Transfer&tfarm=BYN" style="color:#19fd01"><b>BYN</b></a>
		        									<a href="<%=request.getContextPath()%>/Intake.do?action=Transfer&tfarm=EHW" style="color:#19fd01"><b>EHW</b></a>
		        									]</font>
		        		<% } %>
		     	</logic:equal>
		     </logic:equal>
		     
		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Denied">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Fort Lauderdale">
		     			<% if ("Fort Lauderdale".equals(user.getFarmBase())) { %>
		    				&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Reinstate" style="color:#19fd01"><b>Reinstate Application</b></a>
		     			<% } %>
		     	</logic:equal>
		     </logic:equal>

		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Withdrawn">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Fort Lauderdale">
		     			<% if ("Fort Lauderdale".equals(user.getFarmBase())) { %>
		    				&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Reinstate" style="color:#19fd01"><b>Reinstate Application</b></a>
		     			<% } %>
		     	</logic:equal>
		     </logic:equal>
		     
		    <logic:equal name="intakeForm" property="intake.applicationStatus" value="Waitlist">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Fort Lauderdale">
		     			<% if ("Fort Lauderdale".equals(user.getFarmBase())) { %>
		    				&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Accept" style="color:#19fd01"><b>Accept Application</b></a>
		     				&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Withdraw" style="color:#19fd01"><b>Withdraw</b></a>
		     			<% } %>
		     	</logic:equal>
		     </logic:equal>
   			 
		     <!-- Permission for Okeechobee -->
		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Accepted">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Okeechobee">
		     			<% if ("Okeechobee".equals(user.getFarmBase())) { %>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Admit" style="color:#19fd01"><b>Admit To Program</b></a>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Unaccept" style="color:#19fd01"><b>Unaccept</b></a>
		        		<% } %>
		     	</logic:equal>
		     </logic:equal>
		     
		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Pending">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Okeechobee">
		     			<% if ("Okeechobee".equals(user.getFarmBase())) { %>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Accept" style="color:#19fd01"><b>Accept Application</b></a>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Deny" style="color:#19fd01"><b>Deny Application</b></a>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Waitlist" style="color:#19fd01"><b>Waitlist</b></a>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Withdraw" style="color:#19fd01"><b>Withdraw</b></a>
		        			&nbsp;&nbsp;&nbsp;<font style="color:#FFFFFF">Transfer&nbsp;[&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Transfer&tfarm=FTL" style="color:#19fd01"><b>FTL</b></a>
		        									<a href="<%=request.getContextPath()%>/Intake.do?action=Transfer&tfarm=BYN" style="color:#19fd01"><b>BYN</b>
		        									<a href="<%=request.getContextPath()%>/Intake.do?action=Transfer&tfarm=EHW" style="color:#19fd01"><b>EHW</b></a></a>
		        									]</font>
		        		<% } %>
		     	</logic:equal>
		     </logic:equal>
		     
		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Denied">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Okeechobee">
		     			<% if ("Okeechobee".equals(user.getFarmBase())) { %>
		    				&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Reinstate" style="color:#19fd01"><b>Reinstate Application</b></a>
		     			<% } %>
		     	</logic:equal>
		     </logic:equal>

		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Withdrawn">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Okeechobee">
		     			<% if ("Okeechobee".equals(user.getFarmBase())) { %>
		    				&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Reinstate" style="color:#19fd01"><b>Reinstate Application</b></a>
		     			<% } %>
		     	</logic:equal>
		     </logic:equal>
		     		     
		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Waitlist">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Okeechobee">
		     			<% if ("Okeechobee".equals(user.getFarmBase())) { %>
		    				&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Accept" style="color:#19fd01"><b>Accept Application</b></a>
		     				&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Withdraw" style="color:#19fd01"><b>Withdraw</b></a>
		     			<% } %>
		     	</logic:equal>
		     </logic:equal>
		     
   			  
		     <!-- Permission for Women's Home -->
		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Accepted">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Women's Home">
		     			<% if ("Women's Home".equals(user.getFarmBase())) { %>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Admit" style="color:#19fd01"><b>Admit To Program</b></a>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Unaccept" style="color:#19fd01"><b>Unaccept</b></a>
		        		<% } %>
		     	</logic:equal>
		     </logic:equal>
		     
		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Pending">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Women's Home">
		     			<% if ("Women's Home".equals(user.getFarmBase())) { %>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Accept" style="color:#19fd01"><b>Accept Application</b></a>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Deny" style="color:#19fd01"><b>Deny Application</b></a>
		        			&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Waitlist" style="color:#19fd01"><b>Waitlist</b></a>
		        			&nbsp;&nbsp;&nbsp;<font style="color:#FFFFFF">Transfer&nbsp;[&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Transfer&tfarm=FTL" style="color:#19fd01"><b>FTL</b></a>
		        									<a href="<%=request.getContextPath()%>/Intake.do?action=Transfer&tfarm=BYN" style="color:#19fd01"><b>BYN</b>
		        									<a href="<%=request.getContextPath()%>/Intake.do?action=Transfer&tfarm=OKE" style="color:#19fd01"><b>OKE</b></a></a>
		        									]&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Withdraw" style="color:#19fd01"><b>Withdraw</b></a>
		        		<% } %>
		     	</logic:equal>
		     </logic:equal>
		     
		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Denied">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Women's Home">
		     			<% if ("Women's Home".equals(user.getFarmBase())) { %>
		    				&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Reinstate" style="color:#19fd01"><b>Reinstate Application</b></a>
		     			<% } %>
		     	</logic:equal>
		     </logic:equal>

		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Withdrawn">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Women's Home">
		     			<% if ("Women's Home".equals(user.getFarmBase())) { %>
		    				&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Reinstate" style="color:#19fd01"><b>Reinstate Application</b></a>
		     			<% } %>
		     	</logic:equal>
		     </logic:equal>
		     		     
		     <logic:equal name="intakeForm" property="intake.applicationStatus" value="Waitlist">
		     	<logic:equal name="intakeForm" property="intake.farmBase" value="Women's Home">
		     			<% if ("Women's Home".equals(user.getFarmBase())) { %>
		    				&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Accept" style="color:#19fd01"><b>Accept Application</b></a>
		     				&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Intake.do?action=Withdraw" style="color:#19fd01"><b>Withdraw</b></a>
		     			<% } %>
		     	</logic:equal>
		     </logic:equal>
   		   </td>
		     
    	</tr>
    </table> 
    </div>

                  
          
            
            </div>
        </div>
        <div class="main">
        
        