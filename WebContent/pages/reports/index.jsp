<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="../../includes/header.jsp" flush="true"/>

<html:form  action="/Report">
    <h2>
        Report List
    </h2>
    <br/><br/>  
            <div align="left">
            
            
            <b>Pass Report</b>
            	<ul>
            		<li><a href="javascript:window.open('<%=request.getContextPath()%>/pages/reports/passdate.jsp');">Pass Report By Date</a></li>
            	</ul>
            	<br/><br/>
            
           	<b>Class List</b>
            	<ul>
            		<li><a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=classlist&farm=Boynton Beach');">Boynton Beach</a></li>
            		<li><a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=classlist&farm=Fort Lauderdale');">Fort Lauderdale</a></li>
            		<li><a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=classlist&farm=Okeechobee');">Okeechobee</a></li>
            		<li><a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=classlist&farm=Women\'s Home');">Women's Home</a></li>
            		<!-- 
            		<li><a href="<%=request.getContextPath()%>/Report.do?rpt=ClassList&farm=Boynton Beach">Boynton Beach</a></li>
            		<li><a href="<%=request.getContextPath()%>/Report.do?rpt=ClassList&farm=Fort Lauderdale">Fort Lauderdale</a></li>
            		<li><a href="<%=request.getContextPath()%>/Report.do?rpt=ClassList&farm=Okeechobee">Okeechobee</a></li>
            		<li><a href="<%=request.getContextPath()%>/Report.do?rpt=ClassList&farm=Women's Home">Women's Home</a></li>
            		 -->
            	</ul>
            	<br/><br/>
            	
            	<b>CWT Graduation Certificate List <i>(Current Class 6 Only)</i></b>
            	<ul>
            		<li><a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=cwtgrad&farm=Boynton Beach');">Boynton Beach</a></li>
            		<li><a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=cwtgrad&farm=Fort Lauderdale');">Fort Lauderdale</a></li>
            		<li><a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=cwtgrad&farm=Okeechobee');">Okeechobee</a></li>
            		<li><a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=cwtgrad&farm=Women\'s Home');">Women's Home</a></li>
            	</ul>
            	<br/><br/>
   
            	<!-- 
            	<b>Bed List <i>(Under Construction)</i></b>
            	<ul>
            		<li><a href="<%=request.getContextPath()%>/Report.do?rpt=BedList&farm=Boynton Beach">Boynton Beach</a></li>
            		<li><a href="<%=request.getContextPath()%>/Report.do?rpt=BedList&farm=Fort Lauderdale">Fort Lauderdale</a></li>
            		<li><a href="<%=request.getContextPath()%>/Report.do?rpt=BedList&farm=Okeechobee">Okeechobee</a></li>
            	</ul>
            	<br/><br/>
            	
            	<b>Job List</b>
            	<ul>
            		<li><a href="<%=request.getContextPath()%>/Report.do?rpt=JobList&farm=Boynton Beach">Boynton Beach</a></li>
            		<li><a href="<%=request.getContextPath()%>/Report.do?rpt=JobList&farm=Fort Lauderdale">Fort Lauderdale</a></li>
            		<li><a href="<%=request.getContextPath()%>/Report.do?rpt=JobList&farm=Okeechobee">Okeechobee</a></li>
            	</ul>
            	</br></br>
            	-->
            	<!-- 
            	<b>Pass List</b>
            	<ul>
            		<li><a href="<%=request.getContextPath()%>/Report.do?rpt=PassList&farm=Boynton Beach">Boynton Beach</a></li>
            		<li><a href="<%=request.getContextPath()%>/Report.do?rpt=PassList&farm=Fort Lauderdale">Fort Lauderdale</a></li>
            		<li><a href="<%=request.getContextPath()%>/Report.do?rpt=PassList&farm=Okeechobee">Okeechobee</a></li>
            		<li><a href="<%=request.getContextPath()%>/Report.do?rpt=PassList&farm=Women's Home">Women's Home</a></li>
            	</ul>
            	<br/><br/>
            	 -->
            	<b>Wait List</b>
            	<ul>
            		<li><a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=waitlist&farm=Boynton Beach');">Boynton Beach</a></li>
            		<li><a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=waitlist&farm=Fort Lauderdale');">Fort Lauderdale</a></li>
            		<li><a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=waitlist&farm=Okeechobee');">Okeechobee</a></li>
            		<li><a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=waitlist&farm=Women's Home');">Women's Home</a></li>
            	</ul>
            	</br></br>
            	
            	
            	<b>Other Reports</b>
            	<ul>
            		<li><a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=occupancy');">Occupancy Report</a></li>
            		<li><a href="javascript:window.open('<%=request.getContextPath()%>/pdfapp.do?action=completion');">Resident Status Report</a></li>
            	</ul>
            	</br></br>
            	
 
            </div>
            
            
    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
    <div class="footer">
        Faith Farm Ministries &copy;2013
    </div>
   
</html:form>
</body>
</html>
