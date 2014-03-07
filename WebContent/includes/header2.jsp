


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head><title>
	Faith Farm Student Information System
</title><link href="<%=request.getContextPath()%>/styles/site.css" rel="stylesheet" type="text/css" />
    <script  type="text/javascript" src="<%=request.getContextPath()%><%=request.getContextPath()%>/scripts/jquery-1.4.1.min.js">
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.format.1.02.js">
    </script>    
    <script  type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dateentry.min.js">
    </script>    
    <style type="text/css">
        @import "<%=request.getContextPath()%>/styles/jquery.datepick.css";
    </style>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.datepick.min.js"></script>    
    <script type="text/javascript">
        $(document).ready(
        function () {
            settings = { yearRange: '-50:+0',
                showOn: 'button',
                buttonImageOnly: true, buttonImage: '/img/calendar.gif'
            };
            //$.datepick.setDefaults(settings);
            
//            $('.DateEnty').dateEntry({ spinnerImage: ''});            
            //$('.DateEnty').datepick();
            $('.Substance_yearusedEntry').format({ precision: 1, allow_negative: false, autofix: true });
            $('.Substance_monthEntry').format({ precision: 0, allow_negative: false, autofix: true });
            $('.positivenumeric').format({ precision: 0, allow_negative: false, autofix: true });
        });
        
    </script>
    
<link href="/WebResource.axd?d=KGuiKqK2W2wKA2IYJD46Z22nJFZTljzkRCAad-GntX1wvhtyO8u6GZFzBmtHSZBLYR_3V8g91GKT6qCabTHhbIhZ0ngzOI6PkCJsRZji32PDiUK4pZdIz96fFtwH6C2dDJWSHrPLkL-5vW3cc2L4EA2&amp;t=634121238346104669" type="text/css" rel="stylesheet" class="Telerik_stylesheet" /><link href="/WebResource.axd?d=lSCFq7H2p5OPLsnrEDiKSmEhOVHP4slc9NAhBHxio5UdPlV15tis7VXAbfUm9pFRvqmbzAAoOBk10-zZGNC_v5tLgeUbRK4MEgICQHzmrE1MYPejSZpkIJtLjfhB2yaAH20TaeFjFUuDcsCU9ntHTEcrs68d76vQgR3w753D-Ow1&amp;t=634121238346104669" type="text/css" rel="stylesheet" class="Telerik_stylesheet" /></head>
<body>
    <form method="post" action="default.aspx" id="ctl01">
<div class="aspNetHidden">
<input type="hidden" name="RadScriptManager1_TSM" id="RadScriptManager1_TSM" value="" />
<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="" />
<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="" />
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwUKMTg4NzU5OTAzNQ9kFgJmD2QWAgIDD2QWAgIBDw8WAh4XRW5hYmxlQWpheFNraW5SZW5kZXJpbmdoFgIeBXN0eWxlBQ1kaXNwbGF5Om5vbmU7ZBgEBR5fX0NvbnRyb2xzUmVxdWlyZVBvc3RCYWNrS2V5X18WAwUXY3RsMDAkUmFkRm9ybURlY29yYXRvcjEFKWN0bDAwJEhlYWRMb2dpblZpZXckSGVhZExvZ2luU3RhdHVzJGN0bDAxBSljdGwwMCRIZWFkTG9naW5WaWV3JEhlYWRMb2dpblN0YXR1cyRjdGwwMwUfY3RsMDAkTWFpbkNvbnRlbnQkSGVhZExvZ2luVmlldw8PZAIBZAUVY3RsMDAkTWVudSRMb2dpblZpZXcxDw9kAgFkBRNjdGwwMCRIZWFkTG9naW5WaWV3Dw9kAgFk/B30Bh1MOlzCa8l+wiOO5WGy7oubg/A4xnqDBrMRAlw=" />
</div>

<script type="text/javascript">
//<![CDATA[
var theForm = document.forms['ctl01'];
if (!theForm) {
    theForm = document.ctl01;
}
function __doPostBack(eventTarget, eventArgument) {
    if (!theForm.onsubmit || (theForm.onsubmit() != false)) {
        theForm.__EVENTTARGET.value = eventTarget;
        theForm.__EVENTARGUMENT.value = eventArgument;
        theForm.submit();
    }
}
//]]>
</script>


<script src="/WebResource.axd?d=cYJiSq5z9nleAInA_Bfrx52i0zQIOP73w7DhItyNbWzzPgZ9qAb-EEiHr_6ZwhSajNhwiSFgk1t8_vFtNYCYcvthUmwMrT4p5oyqIEiLkdI1&amp;t=634932975836264443" type="text/javascript"></script>


<script src="/ScriptResource.axd?d=UvhGNumjY175XzCV1lXSekvyntimbALrn4dw7d_p3hK8VHq1qIiOG8NEUnPCi5VCPQzzjy9bUFiR19-astcoByz4O8jqyam8mRRpQiqFLG5XdLx9MMImiHaXqVr5uHsMK27jRRNyhsi2gOw17IYR9w2&amp;t=150492e7" type="text/javascript"></script>
<script src="/Telerik.Web.UI.WebResource.axd?_TSM_HiddenField_=RadScriptManager1_TSM&amp;compress=1&amp;_TSM_CombinedScripts_=%3b%3bSystem.Web.Extensions%2c+Version%3d4.0.0.0%2c+Culture%3dneutral%2c+PublicKeyToken%3d31bf3856ad364e35%3aen-US%3a89093640-ae6b-44c3-b8ea-010c934f8924%3aea597d4b%3ab25378d2%3bTelerik.Web.UI%3aen-US%3a9ed39ec4-e2df-4c82-8301-a59ed459cbb1%3a16e4e7cd%3a86526ba7" type="text/javascript"></script>
<script type="text/javascript">
//<![CDATA[
if (typeof(Sys) === 'undefined') throw new Error('ASP.NET Ajax client-side framework failed to load.');
//]]>
</script>

<div class="aspNetHidden">

	<input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION" value="/wEWAgLZ6I+CAgLC0ZzyCigokc+qUE8RGIBiqfrjlpFgztVkZOpMv9VIXCv2IfAt" />
</div>
    <div id="RadFormDecorator1" style="display:none;">
	<!-- 2010.1.530.40 --><script type="text/javascript">
//<![CDATA[
if (typeof(WebForm_AutoFocus) != 'undefined')
{
	var old_WebForm_AutoFocus = WebForm_AutoFocus;
	WebForm_AutoFocus = function(arg)
	{
		Sys.Application.add_load(function()
		{
			old_WebForm_AutoFocus(arg);
			WebForm_AutoFocus = old_WebForm_AutoFocus;
		});
	}
}
if (typeof(Telerik) != 'undefined')
{
	if (Telerik.Web.UI.RadFormDecorator)
	{
		Telerik.Web.UI.RadFormDecorator.initializePage("RadFormDecorator1", "", "WebBlue", 4);
	}
}
//]]></script><input id="RadFormDecorator1_ClientState" name="RadFormDecorator1_ClientState" type="hidden" />
</div>
        <script type="text/javascript">
//<![CDATA[
Sys.WebForms.PageRequestManager._initialize('ctl00$RadScriptManager1', 'ctl01', [], [], [], 90, 'ctl00');
//]]>
</script>


 <div class="page">
        <div class="header">
            <div class="title">
                <a href="/Default.aspx"> 
                <h1>
                   Faith Farm Student Management
                </h1>
                </a>
            </div>
            <div class="loginDisplay">
                
                        Welcome <span class="bold"><span id="HeadLoginView_HeadLoginName">intake</span></span>!
                        [ <a id="HeadLoginView_HeadLoginStatus" href="javascript:__doPostBack(&#39;ctl00$HeadLoginView$HeadLoginStatus$ctl00&#39;,&#39;&#39;)">Log Out</a> ]
                    
            </div>
             <div class="clear hideSkiplink">
                          
               
                       <a href="#Menu_LoginView1_NavigationMenu_SkipLink"><img alt="Skip Navigation Links" src="/WebResource.axd?d=zk1zGvduTV0eoSSb5DCWURftaYV24wAQqLpXwDcGntw2arIzPp1ASqL39z87Tvthc3VOuCvtlvKYhLAiXQmxPvojXu0E0HB4EhcdKY_dUTU1&amp;t=634932975836264443" width="0" height="0" style="border-width:0px;" /></a><div class="menu" id="Menu_LoginView1_NavigationMenu">
	<ul class="level1">
		<li><a class="level1" href="Default.aspx">
                             Home
                            </a></li><li><a class="level1" href="About.aspx">
                             About
                            </a></li><li><a class="level1" href="StudentPages/StudentSearch.aspx">
                             Search
                            </a></li><li><a class="level1" href="StudentPages/AddNewStudent.aspx">
                             New Student
                            </a></li><li><a class="level1" href="StudentPages/SearchResult.aspx">
                             Search Result
                            </a></li><li><a class="level1" href="StudentPages/Reports/ReportList.aspx">
                             Reports for Printing
                            </a></li><li><a>
                             Fast Find
                            </a><ul class="level2">
			<li><a class="level2" href="StudentPages/Reports/FastFind.aspx?LocID=2">                            
                            Class List for Boyton Beach                            
                            </a></li><li><a class="level2" href="StudentPages/Reports/FastFind.aspx?LocID=3">                            
                            Class List for Ft.Lauderdale                            
                            </a></li><li><a class="level2" href="StudentPages/Reports/FastFind.aspx?LocID=4">                            
                            Class List for Okeechobee                            
                            </a></li><li><a class="level2" href="StudentPages/Reports/FastFind.aspx?LocID=5">                            
                            Class List for Women's Home                            
                            </a></li>
		</ul></li>
	</ul>
</div><a id="Menu_LoginView1_NavigationMenu_SkipLink"></a>
                    
          
            
            </div>
        </div>
