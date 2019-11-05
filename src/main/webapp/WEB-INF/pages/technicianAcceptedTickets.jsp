<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String name = (String) session.getAttribute("userName");
	if (name == null) {
		response.sendRedirect("/RFIDAssetTracking/sessionExpired");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>RFID</title>
	<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
	
		<script src="<c:url value='resources/js/jquery.min.js' />"></script>
	
	<script src="<c:url value='resources/js/jquery-ui.min.js' />"></script>
	<script src="<c:url value='resources/js/validations.js' />"></script>
	
	<link rel="stylesheet" href="<c:url value='resources/css/jquery-ui.css' />">
	
	<link rel="icon" href="<c:url value='resources/assets/img/icon.ico' />" type="image/x-icon"/>

	<!-- Fonts and icons -->
	<script src="<c:url value='resources/assets/js/plugin/webfont/webfont.min.js' />"></script>
		
		<style type="text/css">
#openModal {
	text-align:center;
	margin:auto;
	width:50%;
	height:20%;
	opacity:.95;
	top:0;
	bottom:0;
	right:0;
	left:0;	
	position:absolute;
	background-color:#ffffff;
	overflow:auto
}

/* The Close Button */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

.btn-border.btn-assign {
    color: #6610f2!important;
    border: 1px solid #6610f2!important;
    }
    
.fa-bars,
.fa-ellipsis-v
{
color: #fff!important;
}

</style>

    
	<script>
		WebFont.load({
			google: {"families":["Open+Sans:300,400,600,700"]},
			custom: {"families":["Flaticon", "Font Awesome 5 Solid", "Font Awesome 5 Regular", "Font Awesome 5 Brands"], urls: ["<c:url value='resources/assets/css/fonts.css' />"]},
			active: function() {
				sessionStorage.fonts = true;
			}
		});
	</script>
	
	<script >
		$(document).ready(function() {

			  $("#navbar").load('<c:url value="/resources/common/header.jsp" />'); 
			  $("#execSidebar").load('<c:url value="/resources/common/executiveSidebar.jsp" />'); 
			  getExecCount();
			  tableData();
			  
			 
		});	
	
		var dataSet=[];
		 var custId;
		 var ticketType;
		 var s;
		 function getExecCount(){
			 s='<%=name%>';
				$.ajax({
	                type:"get",
	                url:"getExecTicketsCount",
	                contentType: 'application/json',
	                datatype : "json",
	                data:{"username":s},
	                success:function(result) {
	                	var jsonArr = $.parseJSON(result);
	                  $('#assignedExecTickets')[0].innerHTML=jsonArr[0];
	                  $('#acceptedExecTickets')[0].innerHTML=jsonArr[1];
	                  $('#closedExecTickets')[0].innerHTML=jsonArr[2];
	                  
	                    
	                }
				});
			}
		
		function tableData()
		{	
			
			
			$.ajax({
                type:"get",
                async: false,
                url:"getExecutiveAcceptedTickets",
                contentType: 'application/json',
                datatype : "json",
                 data:{"username":s},
                success:function(data1) {
                    openTicketsList = JSON.parse(data1);                    
                    console.log(openTicketsList[0]);					
//                     for(var i=0;i<openTicketsList.length;i++)
//          		   {
//                     	dataSet.push([openTicketsList[i][0].ticketNum,openTicketsList[i][0].customer.customerId,openTicketsList[i][0].ticketDescription,openTicketsList[i][0].ticketType,openTicketsList[i][0].uniqueId,openTicketsList[i][0].severity,openTicketsList[i][0].status]);
// 		 		   }
					

 					for(var i=0;i<openTicketsList.length;i++)
         		   {
						uid=openTicketsList[i].uniqueId	
					 	var Unique= hex2a(uid)
                    	dataSet.push([openTicketsList[i].ticketNum,openTicketsList[i].customer.customerId,openTicketsList[i].ticketDescription,openTicketsList[i].ticketType,Unique,openTicketsList[i].severity,openTicketsList[i].status]);
		 		   }
                                       
			 var table1=$('#executiveAcceptedTickets').DataTable({
					destroy:true,
					language: {
					  emptyTable: "No Data Available"
					},	
					columnDefs: [{ "targets": -1, "data": null, render: function (a,b,data,d) {
						if (data[3] =='New') {
			                return "<input type='button' style=' background-color: #4CAF50;border: none;  color: white;  padding: 5px 25px;  text-align: center;  text-decoration: none;  display: inline-block;  font-size: 16px;  margin: 4px 2px;  cursor: pointer;' id='viewBtn' value='Write to tag' />";
			            }
			            else if (data[3] =='Existing') {
				                return "<input type='button' style=' background-color: #FF6347;border: none;  color: white;  padding: 5px 25px;  text-align: center;  text-decoration: none;  display: inline-block;  font-size: 16px;  margin: 4px 2px;  cursor: pointer;' id='viewBtn' value='Fix the cable' />";
				            }
			            }			            
			        }],
					//columnDefs: [{ "targets": -1, "data": null, "defaultContent": "<input type='button' style=' background-color: #4CAF50;border: none;  color: white;  padding: 5px 25px;  text-align: center;  text-decoration: none;  display: inline-block;  font-size: 16px;  margin: 4px 2px;  cursor: pointer;' id='viewBtn' value='View' />"}],	
			        data: dataSet,
			        columns: [
						{title: "Ticket Id" },
						{title: "Customer Id" },
						{title: "Ticket Description" },
						{title: "Ticket Type" },
						{title: "Unique ID" },
						{title: "Severity" },		
						{title: "Action" }
			        ]
			    } );
			 
			 
			 $('#executiveAcceptedTickets tbody').on('click', '[id*=viewBtn]', function () {
		            data1 =  table1.row($(this).parents('tr')).data();
		            rowIndex = $(this).parent().index();		
		            ticketId=data1[0];
		            custId=data1[1];
		            ticketType=data1[3];
		            if(ticketType=='New')
	            	{
		            	redirectToOther();
	            	}		            
		            else if(ticketType=='Existing')
	            	{
		            	myFunction();
	            	}
		          
             });
		
			 
		}
			});
		}
		

		
		
		
		function hex2a(uid) {
		    var str_uniq = '';
		    for (var i = 0; i < uid.length; i += 2) str_uniq += String.fromCharCode(parseInt(uid.substr(i, 2), 16));
		    return str_uniq;
		}

function myFunction() {
	
			swal({
				//title: 'Are you sure?',
				text: "Are there any changes?",
				type: 'warning',
				buttons:{
					confirm: {
						text : 'Yes',
						className : 'btn btn-success'
					},
					cancel: {
						visible: true,
						className: 'btn btn-danger'
					}
				}
			}).then((Delete) => {
				if (Delete) {
					redirectToOther();
				} else {
					swal.close();
					
				}
			});
		
		}
		

		
		function redirectToOther()
		{
			console.log("Cust"+custId);
			
			$.ajax({
                type: "get",
                url: "getinventorydetails",
                contentType: 'application/json',
                data :{
                	custId,ticketId
                  },
                datatype: "json",
                success: function(result) {
                    inventoryList = JSON.parse(result);
                   window.location.href = '/RFIDAssetTracking/fetchinventoryDetails?ticketid='+window.encodeURIComponent(ticketId)+'&ticketType='+ticketType+'&inventoryDetails='+ window.encodeURIComponent(JSON.stringify(inventoryList)); 
                  

                }
			
       		 }); 
			
		}
		
		
		
		
	</script>


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

		<!-- Sidebar -->
<div id="execSidebar">
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
								<div class="card-body " onclick="location.href='/RFIDAssetTracking/technicianAssignedTickets'" style="cursor:pointer;" >
									<div class="row align-items-center">
										<div class="col-icon">
											<div class="icon-big text-center bubble-shadow-small" style="background:#F98B88;border-radius: 5px;" >
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
								<div class="card-body " onclick="location.href='/RFIDAssetTracking/technicianAcceptedTickets'" style="background-color:#00B1BF;border-radius: 10px;cursor:pointer;">
									<div class="row align-items-center">
										<div class="col-icon">
											<div class="icon-big text-center bubble-shadow-small"  style="background:#af91e1;border-radius: 5px">
											<img src="<c:url value='resources/assets/img/open.svg' />" >
											</div>
										</div>
										<div class="col col-stats ml-3 ml-sm-0">
											<div class="numbers">
												<p class="card-category"  style="color:#ffffff;">Accepted</p>
												<h4 class="card-title" id="acceptedExecTickets"  style="color:#ffffff;"></h4>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-md-3">
							<div class="card card-stats card-round">
								<div class="card-body" onclick="location.href='/RFIDAssetTracking/technicianClosedTickets'" style="cursor:pointer;">
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



					<div class="row">

							<div class="col-md-12">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title">Tickets</h4>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table id="executiveAcceptedTickets" style="width:100%" class="display table table-striped table-hover" >
											
										</table>
									</div>
								</div>
							</div>
						</div>
	
	

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