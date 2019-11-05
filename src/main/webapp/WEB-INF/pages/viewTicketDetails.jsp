<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String name = (String) session.getAttribute("userName");
String role = (String)session.getAttribute("userRole");
	if (name == null||role==null) {
		response.sendRedirect("/RFIDAssetTracking/sessionExpired");
	}
 String details=(String)request.getParameter("listDetails"); %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>RFID</title>
	<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
	
	<link rel="icon" href="<c:url value='resources/assets/img/icon.ico' />" type="image/x-icon"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script type="javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.5.6/css/buttons.dataTables.min.css">

<style>


.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}
.close {
  color: #aaaaaa;

  font-size: 28px;
  font-weight: bold;
}
.modal-content {
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
}


.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

.backBtn
{
	position:fixed;
    right: 48px;
    top: 84px;
}
 .table.invTable thead tr {
  background-color: #87ceeb;
}

 .table.designTable thead tr {
  background-color: #FFFACD;
}
.table.custTable thead tr {
  background-color: #ddffdd;
}
.table.tagTable thead tr {
  background-color: #FFCCCB;
}
</style>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

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
	var details=<%=details%>;
	console.log(details);
	var designDataset=[],invDataset=[],custDataset=[],tagDataset=[];
	 var ticketId;
	 var designDetails,invDetails,custDetails,tagDetails;
	$(function(){
		  $("#navbar").load('<c:url value="/resources/common/header.jsp" />'); 
		 $("#sysAdminSidebar").load('<c:url value="/resources/common/systemAdminSidebar.jsp" />'); 
		  $("#sidebar").load('<c:url value="/resources/common/sidebar.jsp" />'); 
		  $("#execSidebar").load('<c:url value="/resources/common/executiveSidebar.jsp" />'); 
			
			 designDetails=details[0];
			 invDetails=details[1];
			 custDetails=details[2];
			 tagDetails=details[3];
			 tagImage=details[4];
			 
			 
			 $("#imageId").attr("src", 'data:image/jpg;base64,'+tagImage);
			 
			 console.log("designDetails"+designDetails);
			 console.log("invDetails"+invDetails);
			 console.log("custDetails"+custDetails);
			 
		  tableData();
		});
	

	
	function tableData()
	{			
		
               for(var i=0;i<designDetails.length;i++)
    		   {
            	   designDataset.push([designDetails[i].tagId,designDetails[i].exchangeName,designDetails[i].shelfName,designDetails[i].startPoint,designDetails[i].endPoint,designDetails[i].length,designDetails[i].address,designDetails[i].floor,designDetails[i].suite,designDetails[i].vertIn]);
    			   
    		   }
               
               for(var i=0;i<invDetails.length;i++)
    		   {
            	   invDataset.push([invDetails[i].equipmentId,invDetails[i].exchangeName,invDetails[i].shelfName,invDetails[i].length,invDetails[i].inventoryStatusCode,invDetails[i].ownershipTypeCode,invDetails[i].siteName,invDetails[i].installedDate,invDetails[i].operationalState]);
    			   
    		   }
               
               for(var i=0;i<custDetails.length;i++)
    		   {
            	   custDataset.push([custDetails[i].customerId,custDetails[i].customerName,custDetails[i].address,custDetails[i].city,custDetails[i].customerStatus,custDetails[i].fax,custDetails[i].partial,custDetails[i].phoneNum,custDetails[i].postalCode,custDetails[i].surname,custDetails[i].userId]);
    			   
    		   }
               

               for(var i=0;i<tagDetails.length;i++)
    		   {
            	   tagDataset.push([tagDetails[i].customerid,tagDetails[i].ticketid,tagDetails[i].taguniqueid,tagDetails[i].pre_uniq,tagDetails[i].fileName]);
    			   
    		   }
              
               
		 var table1=$('#designTable').DataTable({
				destroy:true,
				paging:false,
		 		info:false,
				language: {
				  emptyTable: "No Data Available"
				},	
				dom: 'Bfrtip',
		        buttons: [
		            'copy', 'csv', 'excel', 'pdf', 'print'
		        ],
		        data: designDataset,
		        columns: [
					{title: "Tag Id" },
					{title: "Exchange Name" },
					{title: "Shelf Name" },	
					{title: "Start Point" },
					{title: "End Point" },
					{title: "Length" },	
					
					{title: "Address" },
					{title: "Floor" },
					{title: "Suite" },
					{title: "Vert In" }
		        ]
		    } );
		 
		 var table2=$('#invTable').DataTable({
				destroy:true,
				paging:false,
		 		info:false,
				language: {
				  emptyTable: "No Data Available"
				},								
		        data: invDataset,
		        columns: [
		        	{title: "Equipment Id",  },
		        	
					{title: "Exchange Name" },
					{title: "Shelf Name" },	
					{title: "Length" },
					{title: "Inventory Status Code" },
					{title: "Ownership Type Code" },	
					
					{title: "Site Name" },
					{title: "Installed Date" },
					{title: "Operational State" }	
		        ]
		    } );
		 
		 var table3=$('#custTable').DataTable({
				destroy:true,
				paging:false,
		 		info:false,
				language: {
				  emptyTable: "No Data Available"
				},								
		        data: custDataset,
		        columns: [
		        	{title: "Customer Id" },
		        	{title: "Customer Name" },
					{title: "Address" },
					{title: "City" },	
					{title: "Customer Status" },
					{title: "Fax" },
					{title: "Partial" },	
					
					{title: "Phone Number" },
					{title: "Postal Code" },
					{title: "Surname" }	,
					{title: "User Id" }	
		        ]
		    } );
		 
		 
		 
		 
		 var table4=$('#tagTable').DataTable({
				destroy:true,
				paging:false,
		 		info:false,
				language: {
				  emptyTable: "No Data Available"
				},				
		        data: tagDataset,
		        columnDefs: [{ "targets": -1, "data": null, "defaultContent": "<button style=' background-color: #4CAF50;border: none;  color: white;  padding: 5px 25px;  text-align: center;  text-decoration: none;  display: inline-block;  font-size: 16px;  margin: 4px 2px;  cursor: pointer;' id='viewBtn'>View</button>"}],
		        columns: [
		        	{title: "Customer Id" },
		        	{title: "Ticket Id" },
		        	{title:"Tag Unique Id"},
		        	{title:"Previous Tag Unique Id"},
		        	{title:" Tag Image "}
		        ]
		    } );
		 
		 $('#tagTable tbody').on('click', '[id*=viewBtn]', function () {
			// alert("hi")
		        var data = table4.row( this ).data();
				//alert(data);
		        //alert( 'You clicked on '+data[0]+'\'s row' );
		       // modal.style.display = "block";
		        $(".modal").css("display","block");		        
		        $(".close").css("display","block");		        
				$("#imageId").css("display","block");
		        
		        
		        
		    } );
		 
		
			 $(".close").on('click',function(){
		        $(".modal").css("display","");		        

			 
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

.card-header1 {
    
    background-color: #ddffdd;
    border-bottom: 1px solid rgba(0,0,0,0.125);
    border-radius: 5px;
}

.card-header3 {
   
    background-color: #87ceeb;
    border-bottom: 1px solid rgba(0,0,0,0.125);
    border-radius: 5px;
}

.card-header2 {
   
    background-color: #FFFACD;
    border-bottom: 1px solid rgba(0,0,0,0.125);
    border-radius: 5px;
}

.card-header4 {
   
    background-color: #FFCCCB;
    border-bottom: 1px solid rgba(0,0,0,0.125);
    border-radius: 5px;
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


    <% if(role!=null){if (role.equalsIgnoreCase(("SuperAdmin"))) { %> 
<div id="sidebar">
</div>
<%} %>



    <% if (role.equalsIgnoreCase(("Admin"))) { %> 
<div id="sysAdminSidebar">
</div>
<%} %>


 <% if (role.equalsIgnoreCase(("FeildExecutive"))) { %> 
<div id="execSidebar">
</div>
<%} }%>


		<div class="main-panel">
			<div class="content">
				<div class="page-inner">
					<div class="page-header">
						<h4 class="page-title">Details</h4>	
						<span style="float:right;font-size:24px" onclick="javascript:history.back()" class="hover6"><i class="fas fa-arrow-circle-left backBtn"></i></span>					
					</div>
					
					
					<div class="col-md-12">
							<div class="card">
								
								
								<div class="card-header">
									<h4 style="width:100px" class="card-title card-header3">&nbsp;&nbsp;Inventory</h4>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table style="width:100%" id="invTable" class="invTable table table-striped table-hover" >
											
										</table>
									</div>
								</div>
								
								<div class="card-header">
									<h4 style="width:100px"  class="card-title card-header2">&nbsp;&nbsp;Design</h4>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table style="width:100%" id="designTable" class="designTable table table-striped table-hover" >
											
										</table>
									</div>
								</div>
								
								<div class="card-header">
									<h4 style="width:100px"  class="card-title card-header1">&nbsp;&nbsp;Customer</h4>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table style="width:100%" id="custTable" class="custTable table table-striped table-hover" >
											
										</table>
									</div>
								</div>
								
								
								
								<div class="card-header">
									<h4 style="width:150px"  class="card-title card-header4">&nbsp;&nbsp;Tag Information</h4>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table style="width:100%" id="tagTable" class="tagTable table table-striped table-hover" >
											
										</table>
										
										
									</div>
								</div>
								<div id="myModal" class="modal">

										 <!-- Modal content -->
		 						 	<div class="modal-content">
		 						 	<div style="float:right">
			 				   			<span class="close" >&times;</span>
			 				   			</div>
			  						  	<img id="imageId" src="" style="display:none" />
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