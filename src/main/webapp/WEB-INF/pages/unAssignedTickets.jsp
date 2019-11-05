<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String name = (String) session.getAttribute("userName");
String role=(String)session.getAttribute("userRole");
	if (name == null ||role==null) {
		response.sendRedirect("/RFIDAssetTracking/sessionExpired");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>RFID</title>
	<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
	
	<link rel="icon" href="<c:url value='resources/assets/img/icon.ico' />" type="image/x-icon"/>

	<!-- Fonts and icons -->
	<script src="<c:url value='resources/assets/js/plugin/webfont/webfont.min.js' />"></script>
	
		<script src="<c:url value='resources/js/jquery.min.js' />"></script>
	
	<script src="<c:url value='resources/js/jquery-ui.min.js' />"></script>
	<script src="<c:url value='resources/js/validations.js' />"></script>
	
	<link rel="stylesheet" href="<c:url value='resources/css/jquery-ui.css' />">
		
	<script>
		WebFont.load({
			google: {"families":["Open+Sans:300,400,600,700"]},
			custom: {"families":["Flaticon", "Font Awesome 5 Solid", "Font Awesome 5 Regular", "Font Awesome 5 Brands"], urls: ["<c:url value='resources/assets/css/fonts.css' />"]},
			active: function() {
				sessionStorage.fonts = true;
			}
		});
		var s;
		$(document).ready(function() {
			
			 $("#navbar").load('<c:url value="/resources/common/header.jsp" />'); 
			  $("#sidebar").load('<c:url value="/resources/common/sidebar.jsp" />'); 
			  $("#sysAdminSidebar").load('<c:url value="/resources/common/systemAdminSidebar.jsp" />');

			  tableData();
			  
			  s='<%=role%>';
			  if(s=="SuperAdmin"){
			  	getAdminCount();
			  }
			  else if(s=="Admin"){
				  getCount();
		      }

		});
		var dataSet=[];
		var unassignDataSet=[];
		 var ticketId;
		
		function tableData()
		{			
			$.ajax({
               type:"get",
               url:"getHistoryTickets",
               contentType: 'application/json',
               datatype : "json",
               success:function(data) {
                   historyTicketsList = JSON.parse(data);
					
                   for(var i=0;i<historyTicketsList.length;i++)
        		   {
                   	dataSet.push([historyTicketsList[i].ticketNum,historyTicketsList[i].customer.customerId,historyTicketsList[i].status]);
        		   }
                
                   
			 var table1=$('#historyTickets').DataTable({
					destroy:true,
					language: {
					  emptyTable: "No Data Available"
					},	
										
			        data: dataSet,
			        columns: [
						{title: "Ticket Id" },
						{title: "Customer Id" },
						{title: "Issue" }						
			        ]
			    } );
			 }
			});
			
			
			$.ajax({
	               type:"get",
	               url:"getUnassignedOpenTickets",
	               contentType: 'application/json',
	               datatype : "json",
	               success:function(data) {
	                   unassignTicketsList = JSON.parse(data);
						
	                   for(var i=0;i<unassignTicketsList.length;i++)
	        		   {
	                	   unassignDataSet.push([unassignTicketsList[i].ticketNum,unassignTicketsList[i].customer.customerId,unassignTicketsList[i].status]);
	        		   }
	                
	                   
				 var table1=$('#unassignTicketstbl').DataTable({
						destroy:true,
						language: {
						  emptyTable: "No Data Available"
						},	
											
				        data: unassignDataSet,
				        columns: [
							{title: "Ticket Id" },
							{title: "Customer Id" },
							{title: "Issue" }						
				        ]
				    } );
			}
				});
		}
		
		
function getAdminCount(){
			
			$.ajax({
                type:"get",
                url:"adminTicketsCount",
                contentType: 'application/json',
                datatype : "json",
                success:function(result) {
                	var jsonArr = $.parseJSON(result);
                	$('#openTicketCount')[0].innerHTML=jsonArr[0][0];
                  /* $('#closedTicketCount')[0].innerHTML=jsonArr[1];
                  $('#historyTicketCount')[0].innerHTML=jsonArr[2]; */
                  $('#assignedTicketCount')[0].innerHTML=jsonArr[1][0];
	              $('#unassignedTicketCount')[0].innerHTML=jsonArr[2][0];
                    
                }
			});
		}
		
function getCount(){
	
	$.ajax({
        type:"get",
        url:"ticketsCount",
        contentType: 'application/json',
        datatype : "json",
        success:function(result) {
        	var jsonArr = $.parseJSON(result);
        	$('#openTicketCount')[0].innerHTML=jsonArr[0];
          $('#closedTicketCount')[0].innerHTML=jsonArr[1];
          $('#historyTicketCount')[0].innerHTML=jsonArr[2];
            
        }
	});
}
	</script>
<style>
.fa-bars,
.fa-ellipsis-v
{
color: #fff!important;
}
</style>
	<!-- CSS Files -->

	
	<link rel="stylesheet" href="<c:url value='resources/assets/css/bootstrap.min.css' />">
	<link rel="stylesheet" href="<c:url value='resources/assets/css/azzara.min.css' />">

	<!-- CSS Just for demo purpose, don't include it in your project -->
	<link rel="stylesheet" href="<c:url value='resources/assets/css/demo.css' />">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
</head>
<body>
	<div class="wrapper">
		<!--
			Tip 1: You can change the background color of the main header using: data-background-color="blue | purple | light-blue | green | orange | red"
		-->
		<div class="main-header" style="background-color: #00B1BF;">
			<!-- Logo Header -->
			<div class="logo-header">
				
				<a href="home" class="logo">
				
					<img src="<c:url value='resources/assets/img/logo.png' />" alt="navbar brand" class="navbar-brand">
				</a>
				<button class="navbar-toggler sidenav-toggler ml-auto" type="button" data-toggle="collapse" data-target="collapse" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon">
						<i class="fa fa-bars"></i>
					</span>
				</button>
				<button class="topbar-toggler more"><i class="fa fa-ellipsis-v"></i></button>
				<div class="navbar-minimize">
					<button class="btn btn-minimize btn-rounded">
						<i class="fa fa-bars"></i>
					</button>
				</div>
			</div>
			<!-- End Logo Header -->

<!-- Navbar Header -->
			<div id="navbar">	
			</div>
			<!-- End Navbar -->
		</div>



		 <% 
		 if(role!=null){if (role.equalsIgnoreCase(("SuperAdmin"))) { %> 
<div id="sidebar">
</div>
<%} %>



    <% if (role.equalsIgnoreCase(("Admin"))) { %> 
<div id="sysAdminSidebar">
</div>
<%} }%>


		<div class="main-panel">
			<div class="content">
				<div class="page-inner">
					<div class="page-header">
						<h4 class="page-title">Dashboard</h4>						
					</div>
					<div class="row">
						<div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round">
							 <% if(role!=null){if (role.equalsIgnoreCase(("SuperAdmin"))) { %> 
								<div class="card-body " onclick="location.href='/RFIDAssetTracking/adminOpenTickets'" style="cursor:pointer;">
								<%} %>
								 <% if (role.equalsIgnoreCase(("Admin"))) { %> 
								 <div class="card-body " onclick="location.href='/RFIDAssetTracking/openTickets'" style="cursor:pointer;">
								 <%}} %>
									<div class="row align-items-center">
										<div class="col-icon">
											<div class="icon-big text-center bubble-shadow-small" style="background:#f3545d;border-radius: 5px">
											<img src="<c:url value='resources/assets/img/open.svg' />" >
											</div>
										</div>
										<div class="col col-stats ml-3 ml-sm-0">
											<div class="numbers">
												<p class="card-category">Open</p>
												<h4 class="card-title" id="openTicketCount"></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<% if(role!=null){if (role.equalsIgnoreCase(("Admin"))) { %> 
						
						<div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round">
								<div class="card-body" onclick="location.href='/RFIDAssetTracking/closedTickets'" style="cursor:pointer;">
									<div class="row align-items-center">
										<div class="col-icon">
											<div class="icon-big text-center bubble-shadow-small" style="background:#af91e1;border-radius: 5px">
											<img src="<c:url value='resources/assets/img/closed.svg' />" >
											</div>
										</div>
										<div class="col col-stats ml-3 ml-sm-0">
											<div class="numbers">
												<p class="card-category">Closed</p>
												<h4 class="card-title" id="closedTicketCount"></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round">
								<div class="card-body" onclick="location.href='/RFIDAssetTracking/historyTickets'" style="background-color:#00B1BF;border-radius: 10px;cursor:pointer;">
									<div class="row align-items-center">
										<div class="col-icon">
											<div class="icon-big text-center bubble-shadow-small" style="background:#808080;border-radius: 5px">
												<img src="<c:url value='resources/assets/img/history.svg' />" >
											</div>
										</div>
										<div class="col col-stats ml-3 ml-sm-0">
											<div class="numbers">
												<p class="card-category" style="color:#ffffff;">History</p>
												<h4 class="card-title" id="historyTicketCount" style="color:#ffffff;"></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
					</div>



					<div class="row">

							<div class="col-md-12">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title">Tickets</h4>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table id="historyTickets" class="display table table-striped table-hover" ></table>
									</div>
								</div>
							</div>
						</div>
			</div>
			<%} }%>
			
			 <% if(role!=null){if (role.equalsIgnoreCase(("SuperAdmin"))) { %> 
			<div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round">
								<div class="card-body" onclick="location.href='/RFIDAssetTracking/assignedTickets'" style="cursor:pointer;">
									<div class="row align-items-center">
										<div class="col-icon">
											<div class="icon-big text-center bubble-shadow-small" style="background:#F98B88;border-radius: 5px">
											<img src="<c:url value='resources/assets/img/closed.svg' />" >
											</div>
										</div>
										<div class="col col-stats ml-3 ml-sm-0">
											<div class="numbers">
												<p class="card-category">Assigned</p>
												<h4 class="card-title" id="assignedTicketCount"></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round">
								<div class="card-body" onclick="location.href='/RFIDAssetTracking/unAssignedTickets'" style="background-color:#00B1BF;border-radius: 10px;cursor:pointer;">
									<div class="row align-items-center">
										<div class="col-icon">
											<div class="icon-big text-center bubble-shadow-small" style="background:#808080;border-radius: 5px">
												<img src="<c:url value='resources/assets/img/history.svg' />" >
											</div>
										</div>
										<div class="col col-stats ml-3 ml-sm-0">
											<div class="numbers">
												<p class="card-category" style="color:#ffffff;">UnAssigned</p>
												<h4 class="card-title" id="unassignedTicketCount" style="color:#ffffff;"></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">

							<div class="col-md-12">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title">Tickets</h4>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table id="unassignTicketstbl" style="width:100%" class="display table table-striped table-hover" ></table>
									</div>
								</div>
							</div>
						</div>
			</div>
			<%} }%>
		</div>
			</div>
		</div>
		
	</div>
	</div>
<!--   Core JS Files   -->



<script src="<c:url value='resources/assets/js/core/jquery.3.2.1.min.js' />"></script>
<script src="<c:url value='resources/assets/js/core/popper.min.js' />"></script>
<script src="<c:url value='resources/assets/js/core/bootstrap.min.js' />"></script>

<!-- jQuery UI -->


<script src="<c:url value='resources/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js' />"></script>
<script src="<c:url value='resources/assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js' />"></script>


<!-- jQuery Scrollbar -->
<script src="<c:url value='resources/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js' />"></script>

<!-- Moment JS -->
<script src="<c:url value='resources/assets/js/plugin/moment/moment.min.js' />"></script>

<!-- Chart JS -->
<script src="<c:url value='resources/assets/js/plugin/chart.js/chart.min.js' />"></script>

<!-- jQuery Sparkline -->
<script src="<c:url value='resources/assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js' />"></script>

<!-- Chart Circle -->
<script src="<c:url value='resources/assets/js/plugin/chart-circle/circles.min.js' />"></script>

<!-- Datatables -->
<script src="<c:url value='resources/assets/js/plugin/datatables/datatables.min.js' />"></script>

<!-- Bootstrap Notify -->
<script src="<c:url value='resources/assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js' />"></script>

<!-- Bootstrap Toggle -->
<script src="<c:url value='resources/assets/js/plugin/bootstrap-toggle/bootstrap-toggle.min.js' />"></script>

<!-- jQuery Vector Maps -->
<script src="<c:url value='resources/assets/js/plugin/jqvmap/jquery.vmap.min.js' />"></script>

<script src="<c:url value='resources/assets/js/plugin/jqvmap/maps/jquery.vmap.world.js' />"></script>

<!-- Google Maps Plugin -->
<script src="<c:url value='resources/assets/js/plugin/gmaps/gmaps.js' />"></script>

<!-- Sweet Alert -->

<script src="<c:url value='resources/assets/js/plugin/sweetalert/sweetalert.min.js' />"></script>

<!-- Azzara JS -->

<script src="<c:url value='resources/assets/js/ready.min.js' />"></script>


</body>
</html>