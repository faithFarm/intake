<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Job List</title>

<style>

body {
font: 11pt Georgia, "Times New Roman", Times, serif;
line-height: 1.00;
}

@media print
{
 .page-break  { page-break-before:always; 
 }

@page{
argin-left: 0px;
margin-right: 0px;
margin-top: 0px;
margin-bottom: 0px;
}
h1 {
	font:"Arial";
	font-size:.80em;
	color:#000000;
	text-align:center;
	text-decoration:none;
}
h2 {
	font:"Arial";
	font-size:1.00em;
	color:#000000;
	text-align:center;
}
h3 {
	font:"Arial";
	font-size:.60em;
	color:#000000;
	text-align:center;
}
td {}
 
.department {
	font:"Arial";
	font-size:.70em;
	color:#f90606;
	text-align:center;
	text-decoration:underline;	
}
.student {
	font:"Arial";
	font-size:.70em;
	color:#000000;
	text-align:left;	
}
.header {
	font:"Arial";
	font-size:.70em;
	color:#000000;
	text-align:center;	
}

</style>

 	<script language="javascript">
	function printPage()
	{
	window.print();
	}
	</script>

</head>
<body onload="javascript:printPage();">

<%
		Date ddate = new java.util.Date(new java.util.Date().toString());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
%>
<table width="100%" cellpadding="0" cellspacing="0" border="0" >
<tr>
	<td align="center" colspan="13">
		<h2>Fort Lauderdale Job List&nbsp;<i><%=df.format(ddate) %></i></h3>
	</td>
</tr>

<tr>
	<th width="14%" class="header" >RICK ANDRES<br/>JEFF SCHLEBAUM</th>
	<th width="5" bgcolor="#000000"></th>
    <th width="14%" class="header">RICHARD ROSENDO</th> 
    <th width="5" bgcolor="#000000"></th>
    <th width="14%" class="header">RICK ANDRES</th>
    <th width="5" bgcolor="#000000"></th>
    <th width="14%" class="header">RICK ANDRES</th>
    <th width="5" bgcolor="#000000"></th>
    <th width="14%" class="header">RICK ANDRES</th>
    <th width="5" bgcolor="#000000"></th>
    <th width="14%" class="header">RICHARD ROSENDO</br>RICK CORRING</th>
    <th width="5" bgcolor="#000000"></th>
    <th width="14%" class="header">RICHARD ROSENDO</br>JOE ROSSOCK</br>MIKE AUERBACH</th>
</tr>
<tr>
	<td valign="top">
		<table width="100%" border="0">
        	<tr>
            	<td class="department">
                	<b>Canteen(<bean:write name="reportForm" property="canteenListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="canteenList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
                  	<tr>
            	<td class="department">
                	<b>Kitchen(<bean:write name="reportForm" property="kitchenListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="kitchenList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
           <tr>
            	<td class="department">
                	<b>Laundry(<bean:write name="reportForm" property="laundryListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="laundryList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
			<tr><td height="10"></td></tr>
			
           <tr>
            	<td class="department">
                	<h1>RICK ANDRES</h1>
                	<b>House Crew(<bean:write name="reportForm" property="houseCrewListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="houseCrewList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            

           <tr>
            	<td class="department">
            		<h1>RICHARD ROSENDO</h1>
                	<b>Garage(<bean:write name="reportForm" property="garageListSize" />)</b> 
                </td> 
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="garageList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
<tr><td height="10"></td></tr>
			
        </table>        
    </td>
    
    <td width="5" bgcolor="#000000"></td>
     
    <td valign="top">
		<table width="100%" border="0">
        	<tr>
            	<td class="department">
                	<b>Grounds Crew(<bean:write name="reportForm" property="groundsCrewListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="groundsCrewList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
                  	<tr>
            	<td class="department">
                	<b>Mower Repair(<bean:write name="reportForm" property="mowerRepairListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="mowerRepairList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
           <tr>
            	<td class="department">
            		<h1>RICHARD ROSENDO</h1>
                	<b>Computer(<bean:write name="reportForm" property="computerListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="computerList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            

           <tr>
            	<td class="department">
                	<b>Fix-It(<bean:write name="reportForm" property="fixItListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="fixItList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            

           <tr>
            	<td class="department">
            		<h1>RICK ANDRES</h1>
            		<b>Gate Guards(<bean:write name="reportForm" property="gateGuardsListSize" />)</b> 
                </td> 
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="gateGuardsList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
            
            
           <tr>
            	<td class="department">
            		<b>Front Office(<bean:write name="reportForm" property="frontOfficeListSize" />)</b> 
                </td> 
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="frontOfficeList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            

         <tr>
            	<td class="department">
            		<b>Courier(<bean:write name="reportForm" property="courierListSize" />)</b> 
                </td> 
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="courierList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
            
                     <tr>
            	<td class="department">
            		<b>Houseman(<bean:write name="reportForm" property="dtcHousemanListSize" />)</b> 
                </td> 
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="dtcHousemanList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
<tr><td height="10"></td></tr>
			            
        </table>        
    </td>
    
    <td width="5" bgcolor="#000000"></td>
    
    <td valign="top">
		<table width="100%" border="0">
        	<tr>
            	<td class="department">
            		<b>Appliance(<bean:write name="reportForm" property="applianceListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="applianceList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
            <tr>
            	<td class="department">
            		<b>Books(<bean:write name="reportForm" property="booksListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="booksList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
           <tr>
            	<td class="department">
            		<b>Bric-Brac Sorting(<bean:write name="reportForm" property="bricBracSortingListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="bricBracSortingList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            

           <tr>
            	<td class="department">
                	<b>Clothing Sort(<bean:write name="reportForm" property="clothingSortingListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="clothingSortingList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            

           <tr>
            	<td class="department">
            		<b>Salvage(<bean:write name="reportForm" property="salvageListSize" />)</b> 
                </td> 
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="salvageList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
  <tr><td height="10"></td></tr>
			          
          </table>
    </td>
    
    <td width="5" bgcolor="#000000"></td>
    
    <td valign="top">
		<table width="100%" border="0">
        	<tr>
            	<td class="department">
            		<b>Dock Lead(<bean:write name="reportForm" property="dockLeadListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="dockLeadList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
            <tr>
            	<td class="department">
            		<b>Dock Worker(<bean:write name="reportForm" property="dockWorkerListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="dockWorkerList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
           <tr>
            	<td class="department">
            		<b>Drivers(<bean:write name="reportForm" property="truckDriverListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="truckDriverList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
   <tr><td height="10"></td></tr>
			         
         </table>
    </td>
    
    <td width="5" bgcolor="#000000"></td>
    
   <td valign="top">
		<table width="100%" border="0">
        	<tr>
            	<td class="department">
            		<b>Dispatch(<bean:write name="reportForm" property="dispatchListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            	<b>Anthony Ellis</b>
            	</td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="dispatchList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
            <tr>
            	<td class="department">
            		<b>Radio Man(<bean:write name="reportForm" property="radioManListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="radioManList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
           <tr>
            	<td class="department">
            		<b>Scheduling</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		Women's Ministry
                </td>
            </tr>
            
                     <tr>
            	<td class="department">
            		<b>Delivery Coord.(<bean:write name="reportForm" property="delCoordinatorListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="delCoordinatorList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
                     <tr>
            	<td class="department">
            		<b>Helpers(<bean:write name="reportForm" property="helperListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="helperList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
  <tr><td height="10"></td></tr>
			          
         </table>
    </td>
    
    <td width="5" bgcolor="#000000"></td>
    
     <td valign="top">
		<table width="100%" border="0">
        	<tr>
            	<td class="department">
            		<b>Outside Sales(<bean:write name="reportForm" property="outsideSalesListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="outsideSalesList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
            <tr>
            	<td class="department">
            		<b>Customer P/U(<bean:write name="reportForm" property="customerPickUpListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="customerPickUpList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
           <tr>
            	<td class="department">
            		<b>NF Sales(<bean:write name="reportForm" property="newFurnitureListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="newFurnitureList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
                     <tr>
            	<td class="department">
            		<b>As Is Sales(<bean:write name="reportForm" property="asIsSalesListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="asIsSalesList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
                     <tr>
            	<td class="department">
            		<b>Bric-Brac Room(<bean:write name="reportForm" property="bricBracRoomListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="bricBracRoomList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
            
                      
                     <tr>
            	<td class="department">
            		<b>Bric-Brac/Corral(<bean:write name="reportForm" property="bricBracCorralListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="bricBracCorralList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
            
                      
                     <tr>
            	<td class="department">
            		<b>Clothing(<bean:write name="reportForm" property="clothingWomensListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="clothingWomensList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
            
                      
                     <tr>
            	<td class="department">
            		<b>NF Warehouse(<bean:write name="reportForm" property="newFurnitureWarehouseListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="newFurnitureWarehouseList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
 <tr><td height="10"></td></tr>
			           
         </table>
    </td>
  
  <td width="5" bgcolor="#000000"></td>
    
  <td valign="top">
		<table width="100%" border="0">
        	<tr>
            	<td class="department">
            		<b>Maintenance(<bean:write name="reportForm" property="maintenanceListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="maintenanceList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
            <tr>
            	<td class="department">
            		<b>Bicycle Repair(<bean:write name="reportForm" property="bicycleRepairListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="bicycleRepairList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
           <tr>
            	<td class="department">
            		<b>Donation Pick Up(<bean:write name="reportForm" property="donationPickUpListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="donationPickUpList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
                     <tr>
            	<td class="department">
            		<b>Career Center(<bean:write name="reportForm" property="careerCenterListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="careerCenterList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
            
                     <tr>
            	<td class="department">
            		<b>Helpers(<bean:write name="reportForm" property="atCorpListSize" />)</b>
                </td>
            </tr>
            <tr>
            	<td class="student">
            		<logic:iterate id="loop" name="reportForm" property="atCorpList">
                		<bean:write name="loop" property="firstname" />&nbsp;<bean:write name="loop" property="lastname" /><br/>
                   	</logic:iterate>
                </td>
            </tr>
   <tr><td height="10"></td></tr>
			         
         </table>
    </td>
    
</tr>

</table>

</body>
</html>
