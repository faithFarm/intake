<jsp:include page="student_header.jsp" flush="true"/>

   <table width="950" border="0" >
	<tr>
		<td><b>
		We apologize but an error has occurred in the application!
		In order for us to assist you please contact our intake office:
		</br></br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Fort Lauderdale:&nbsp;&nbsp(954)763-7787</b></br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Boynton Beach:&nbsp;&nbsp(561)737-2222</b></br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Okeechobee:&nbsp;&nbsp(863)763-4224</b></br>
		</br></br></b><%=session.getAttribute("SYSTEM_ERROR") %><br /><br /></td>
	 </tr>
	</table>

<jsp:include page="footer.jsp" flush="true"/>