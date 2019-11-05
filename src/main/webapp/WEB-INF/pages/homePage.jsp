<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String name = (String) session.getAttribute("userName");
	String role=(String)session.getAttribute("userRole");
	if (name == null||role==null) {
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" />
<script type="javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>


	<!-- Fonts and icons -->
	<script src="<c:url value='resources/assets/js/plugin/webfont/webfont.min.js' />"></script>
	<script>
		WebFont.load({
			google: {"families":["Open+Sans:300,400,600,700"]},
			custom: {"families":["Flaticon", "Font Awesome 5 Solid", "Font Awesome 5 Regular", "Font Awesome 5 Brands"], urls: ["<c:url value='resources/assets/css/fonts.css' />"]},
			active: function() {
				sessionStorage.fonts = true;
			}
		});
	
	</script>
	<script>
	var name,role;
	$(function(){
		  $("#navbar").load('<c:url value="/resources/common/header.jsp" />'); 
		  $("#adminSidebar").load('<c:url value="/resources/common/sidebar.jsp" />'); 
		  $("#execSidebar").load('<c:url value="/resources/common/executiveSidebar.jsp" />'); 
		  $("#sysAdminSidebar").load('<c:url value="/resources/common/systemAdminSidebar.jsp" />'); 

		   name='<%=name%>';
		   role='<%=role%>';
	  
		  if(role=="SuperAdmin"){
		  	//getAdminCount();
			document.getElementById("open_div").click();	
		  }
		  else if(role=="Admin"){
			//  getCount();
				document.getElementById("open_div_admin").click();	
	      }
		  else if(role=="Manager"){
			 // 	getManagerCount();
				document.getElementById("manager_total_div").click();	
			}
			else if(role=="FeildExecutive"){
				//  getExecutiveTicketsCount();
					document.getElementById("executive_assign_div").click();	
		    }

		});
	function getAdminCount(){
				
				$.ajax({
	                type:"get",
	                url:"adminTicketsCount",
	                contentType: 'application/json',
	                datatype : "json",
	                success:function(result) {
	                	var jsonArr = $.parseJSON(result);
	                	$('#openTicketCount')[0].innerHTML=jsonArr[0][0];
	                 // $('#closedTicketCount')[0].innerHTML=jsonArr[1];
	                 // $('#historyTicketCount')[0].innerHTML=jsonArr[2];
	                 $('#assignedTicketCount')[0].innerHTML=jsonArr[1][0];
	                 $('#unassignedTicketCount')[0].innerHTML=jsonArr[2][0];
	                    
	                }
				});
			} 
			
	function getExecutiveTicketsCount(){
				
		
				$.ajax({
	                type:"get",
	                url:"getExecTicketsCount",
	                contentType: 'application/json',
	                datatype : "json",
	                data:{"username":name},
	                success:function(result) {
	                	var jsonArr = $.parseJSON(result);
	                  $('#assignedExecTickets')[0].innerHTML=jsonArr[0];
	                  $('#closedExecTickets')[0].innerHTML=jsonArr[1];
	                  
	                    
	                }
				});
			
			} 
		
		function getManagerCount(){
			
			$.ajax({
                type:"get",
                url:"managerTicketsCount",
                contentType: 'application/json',
                datatype : "json",
                success:function(result) {
                	var jsonArr = $.parseJSON(result);
                	$('#managerTotalTickets')[0].innerHTML=jsonArr[0];                
                    
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
                 //$('#closedTicketCount')[0].innerHTML=jsonArr[1];
                 $('#historyTicketCount')[0].innerHTML=jsonArr[1];
                 $('#totalTicketCount')[0].innerHTML=jsonArr[2];
	            
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

.isa_success{
    color:#10F564;
}
</style>
	<!-- CSS Files -->

	
	<link rel="stylesheet" href="<c:url value='resources/assets/css/bootstrap.min.css' />">
	<link rel="stylesheet" href="<c:url value='resources/assets/css/azzara.min.css' />">

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

		




<!-- Admin -->



    <% if(role!=null){
    if (role.equalsIgnoreCase(("SuperAdmin"))) { %> 
    
    <!-- Sidebar -->
		<div id="adminSidebar">
		</div>
		<!-- End Sidebar -->
    

		<div class="main-panel">
			<div class="content">
				<div class="page-inner">
					<div class="page-header">
						<h4 class="page-title">Dashboard</h4>						
					</div>
					<div class="row">
						<div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round" >
								<div class="card-body" id="open_div" onclick="location.href='/RFIDAssetTracking/adminOpenTickets'" style="background-color:#00B1BF;">
									<div class="row align-items-center" >
										<div class="col-icon" >
											<div class="icon-big text-center bubble-shadow-small" style="background:#f3545d;border-radius: 5px">
											<img src="<c:url value='resources/assets/img/open.svg' />" >
											</div>
										</div>
										<div class="col col-stats ml-3 ml-sm-0">
											<div class="numbers">
												<p class="card-category" style="color:#ffffff;">Open</p>
												<h4 class="card-title" id="openTicketCount" style="color:#ffffff;"></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round">
								<div class="card-body" onclick="location.href='/RFIDAssetTracking/assignedTickets'" >
									<div class="row align-items-center">
										<div class="col-icon">
											<div class="icon-big text-center bubble-shadow-small" style="background:#F98B88;border-radius: 5px">
											<img src="<c:url value='resources/assets/img/closed.svg' />" >
											</div>
										</div>
										<div class="col col-stats ml-3 ml-sm-0">
											<div class="numbers">
												<p class="card-category" >Assigned</p>
												<h4 class="card-title" id="assignedTicketCount" ></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
<!-- 						<div class="col-sm-6 col-md-3"> -->
<!-- 							<div class="card card-stats card-round"> -->
<!-- 								<div class="card-body" onclick="location.href='/RFIDAssetTracking/unAssignedTickets'" > -->
<!-- 									<div class="row align-items-center"> -->
<!-- 										<div class="col-icon"> -->
<!-- 											<div class="icon-big text-center bubble-shadow-small" style="background:#808080;border-radius: 5px"> -->
<%-- 											<img src="<c:url value='resources/assets/img/history.svg' />" > --%>
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										<div class="col col-stats ml-3 ml-sm-0"> -->
<!-- 											<div class="numbers"> -->
<!-- 												<p class="card-category" >UnAssigned</p> -->
<!-- 												<h4 class="card-title"  id="unassignedTicketCount"></h4> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round">
								<div class="card-body" onclick="location.href='/RFIDAssetTracking/historyTickets'">
									<div class="row align-items-center">
										<div class="col-icon">
											<div class="icon-big text-center bubble-shadow-small" style="background:#808080;border-radius: 5px">
											<img src="<c:url value='resources/assets/img/history.svg' />" >
											</div>
										</div>
										<div class="col col-stats ml-3 ml-sm-0">
											<div class="numbers">
												<p class="card-category">History</p>
												<h4 class="card-title" id="historyTicketCount"></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
					</div>
					
					
				</div>
			</div>
			
		</div>
		
		<%} }%>
		
		
		<% if(role!=null){
		if (role.equalsIgnoreCase(("Admin"))) { %> 
    
    <!-- Sidebar -->
		<div id="sysAdminSidebar">
		</div>
		<!-- End Sidebar -->
    

		<div class="main-panel">
			<div class="content">
				<div class="page-inner">
					<div class="page-header">
						<h4 class="page-title">Dashboard</h4>						
					</div>
					<div class="row">
						<div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round">
								<div class="card-body " onclick="location.href='/RFIDAssetTracking/openTickets'" id="open_div_admin" >
									<div class="row align-items-center">
										<div class="col-icon">
											<div class="icon-big text-center bubble-shadow-small" style="background:#f3545d;border-radius: 5px">
											<img src="<c:url value='resources/assets/img/open.svg' />" >
											</div>
										</div>
										<div class="col col-stats ml-3 ml-sm-0">
											<div class="numbers">
												<p class="card-category" >Open</p>
												<h4 class="card-title" id="openTicketCount"></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- <div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round">
								<div class="card-body" onclick="location.href='/RFIDAssetTracking/assignedTickets'">
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
						</div> -->
						<div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round">
								<div class="card-body" onclick="location.href='/RFIDAssetTracking/historyTickets'">
									<div class="row align-items-center">
										<div class="col-icon">
											<div class="icon-big text-center bubble-shadow-small" style="background:#808080;border-radius: 5px">
											<img src="<c:url value='resources/assets/img/history.svg' />" >
											</div>
										</div>
										<div class="col col-stats ml-3 ml-sm-0">
											<div class="numbers">
												<p class="card-category">History</p>
												<h4 class="card-title" id="historyTicketCount"></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round">
								<div class="card-body" onclick="location.href='/RFIDAssetTracking/totalTickets'">
									<div class="row align-items-center">
										<div class="col-icon">
											<div class="icon-big text-center bubble-shadow-small" style="background:#af91e1;border-radius: 5px">
											<img src="<c:url value='resources/assets/img/closed.svg' />" >
											</div>
										</div>
										<div class="col col-stats ml-3 ml-sm-0">
											<div class="numbers">
												<p class="card-category">Total</p>
												<h4 class="card-title" id="totalTicketCount"></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div> 
						
					</div>

				</div>
			</div>
			
		</div>
		
		<%} }%>
		
		<!-- Manager -->
		<% if(role!=null){
		if (!(role.equalsIgnoreCase(("Admin")))&&!(role.equalsIgnoreCase(("SuperAdmin")))&&!(role.equalsIgnoreCase(("FeildExecutive")))) { %> 
		    
		<div id="managerSidebar">
		</div>
		
		<div class="main-panel">
			<div class="content">
				<div class="page-inner">
					<div class="page-header">
						<h4 class="page-title">Dashboard</h4>						
					</div>
					
					<div class="row">
						<div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round">
								<div class="card-body " onclick="location.href='/RFIDAssetTracking/managerTotalTickets'" id="manager_total_div">
									<div class="row align-items-center">
										<div class="col-icon">
											<div class="icon-big text-center icon-primary bubble-shadow-small">
											<img src="<c:url value='resources/assets/img/open.svg' />" >
											</div>
										</div>
										<div class="col col-stats ml-3 ml-sm-0">
											<div class="numbers">
												<p class="card-category">Total</p>
												<h4 class="card-title" id="managerTotalTickets"></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						
					</div>
		</div>
		
	   </div>
	</div>
	
<%} } %>
		
		<!-- Executive -->
<% if(role!=null){
	if (!(role.equalsIgnoreCase(("Admin")))&&!(role.equalsIgnoreCase(("SuperAdmin")))&&!(role.equalsIgnoreCase(("Manager")))) { %> 

		    <!-- Sidebar -->
		<div id="execSidebar">
		</div>
		<!-- End Sidebar -->
		
		<div class="main-panel">
			<div class="content">
				<div class="page-inner">
					<div class="page-header">
						<h4 class="page-title">Dashboard</h4>		
						<div align="center"><span class="isa_success" id="isa_success">${succMsg}</span></div>				
					</div>
					
					<div class="row">
						<div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round">
								<div class="card-body " onclick="location.href='/RFIDAssetTracking/technicianAssignedTickets'" id="executive_assign_div">
									<div class="row align-items-center">
										<div class="col-icon">
											<div class="icon-big text-center bubble-shadow-small"  style="background:#F98B88;border-radius: 5px;">
											<img src="<c:url value='resources/assets/img/open.svg' />" >
											</div>
										</div>
										<div class="col col-stats ml-3 ml-sm-0">
											<div class="numbers">
												<p class="card-category">Assigned</p>
												<h4 class="card-title" id="assignedExecTickets"></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round">
								<div class="card-body" onclick="location.href='/RFIDAssetTracking/technicianClosedTickets'">
									<div class="row align-items-center">
										<div class="col-icon">
											<div class="icon-big text-center bubble-shadow-small" style="background:#808080;border-radius: 5px">
											<img src="<c:url value='resources/assets/img/closed.svg' />" >
											</div>
										</div>
										<div class="col col-stats ml-3 ml-sm-0">
											<div class="numbers">
												<p class="card-category">Closed</p>
												<h4 class="card-title" id="closedExecTickets"></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
					</div>
		</div>
		
	   </div>
	</div>
	
<%} } %>
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

<script src="<c:url value='resources/assets/js/setting-demo.js' />"></script>
<script src="<c:url value='resources/assets/js/demo.js' />"></script>

</body>
</html>