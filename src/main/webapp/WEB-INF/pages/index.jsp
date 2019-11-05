<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>RFID</title>
	<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
	<script src="<c:url value='resources/js/jquery.min.js' />"></script>
	
	<script src="<c:url value='resources/js/jquery-ui.min.js' />"></script>
	<script src="<c:url value='resources/js/validations.js' />"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="<c:url value='resources/css/jquery-ui.css' />">
	<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
	<link rel="icon" href="<c:url value='resources/assets/img/icon.ico' />" type="image/x-icon"/>

	<!-- Fonts and icons -->
	<script src="<c:url value='resources/assets/js/plugin/webfont/webfont.min.js' />"></script>
	<script>
		WebFont.load({
			google: {"families":["Open+Sans:300,400,600,700"]},
			custom: {"families":["Flaticon", "Font Awesome 5 Solid", "Font Awesome 5 Regular", "Font Awesome 5 Brands"], urls: ['resources/assets/css/fonts.css']},
			active: function() {
				sessionStorage.fonts = true;
			}
		});
	</script>
	
	<script>
 	$(document).ready(function(){	
		 $("select option[value='Select']").attr('disabled','disabled');
 		// 		dateFun();
// // 		$("#userName,#emailId,#mobileNum,#pwd,#cpwd").attr('required', '');  
 	});

// 	function dateFun()
// 	{
// 	var today = new Date();
// 		document.getElementById('createdDate').value=today;
// 	}


	
	</script>
	<style>
.fa-bars,
.fa-ellipsis-v
{
color: #fff!important;
}
.textcenter
{
text-align:center
}
.bg
{

background-image:url("<c:url value='resources/assets/img/rfid3.jpg' />");
background-repeat: no-repeat;
background-size: 100% 450px;

  

}
div.absolute {
  position: absolute;
  top: 25px;
  right: 50px;
  border: 3px solid #73AD21;
  
}
.fa-bars,
.fa-ellipsis-v
{
color: #fff!important;
}
.login .wrapper1.wrapper-login
{
display: flex;
    justify-content: center;
    align-items: center;
    height: unset;
    padding: 15px;
}
.wrapper1
{
	
    min-height: 450px;
    position: relative;
    top: 0;
}
.login .wrapper1.wrapper-login .container-login, .login .wrapper1.wrapper-login .container-signup {
    width: 350px;
    height: 400px;
    background: #fff;
    padding: 60px 25px;
    border-radius: 5px;
    -webkit-box-shadow: 0 0.75rem 1.5rem rgba(18,38,63,.03);
    -moz-box-shadow: 0 .75rem 1.5rem rgba(18,38,63,.03);
    box-shadow: 0 0.75rem 1.5rem rgba(18,38,63,.03);
    border: 1px solid #ebecec;
}
 .login .wrapper1.wrapper-login .container-login .form-action, .login .wrapper1.wrapper-login .container-signup .form-action {
    text-align: center;
    padding: 25px 10px 0;
}
.login .wrapper1.wrapper-login .container-login .btn-login, .login .wrapper1.wrapper-login .container-signup .btn-login {
    padding: 10px 0;
    width: 100px;
    font-size: 17px;
    
}
.btn-primary1
{
background:#0645ad!important;
border-color:#0645ad!important;
color:#ffffff;
}
 .loginNmsDet {
/*     position: absolute; */
/*     left: 2%; */
    margin-top: 15px;
    margin-left: 15px;
    font-size: 13px;
    color: #555;
}
.text-center
{
font-size: 25px;
}
.card-footer1 {
/*      padding: .10rem .25rem;  */
position: fixed;
   left: 0;
   bottom: 0;
   width:100%;
     
    background-color: rgba(0,0,0,0.03);
    border-top: 1px solid rgba(0,0,0,0.125);
}
</style>
	<!-- CSS Files -->	
	<link rel="stylesheet" href="<c:url value='resources/assets/css/bootstrap.min.css' />">
	<link rel="stylesheet" href="<c:url value='resources/assets/css/azzara.min.css' />">

</head>
<body class="login " >
<div class="logo-header"  >
				&nbsp;&nbsp;
				<a  class="logo" >
				
					<img src="<c:url value='resources/assets/img/logo.png' />" alt="navbar brand" class="navbar-brand">
				</a>
				
				
				
			</div>
<!-- <div style="width:100%;height:35%"> -->
<%-- <img style="width:100%;height:100%" src="<c:url value='resources/assets/img/rfid3.jpg' />" /> --%>
<!-- </div> -->

	<div class=" bg wrapper1 wrapper-login  " >
		<div  class="absolute container container-login animated fadeIn ">
			<h3 class="text-center">Login</h3>
			        <form:form action="validateUser" method="post" modelAttribute="User">
			<div class="login-form">
				<div class="form-group form-floating-label input-group-prepend">
				
						<span class="input-group-text" id="basic-addon1"><i style='font-size:15px;width:12px' class='far'>&#xf007;</i>	</span>							
				<form:input  path="username" id="username" name="username" placeholder="Username" class="form-control input-full" />
				
				<!-- 	<input id="username" name="username" type="text" class="form-control input-border-bottom" required> -->
				
				</div>
				<div class="form-group form-floating-label input-group-prepend">
				
						<span class="input-group-text" id="basic-addon1"><i style='font-size:15px;width:12px' class='fa'>&#xf023;</i>	</span>
				<form:password  path="password" id="password" name="password" placeholder="Password" class="form-control  input-full" />
					<!--  <input id="password" name="password" type="password" class="form-control input-border-bottom" required>-->
				
<!-- 					<div class="show-password"> -->
<!-- 						<i class="flaticon-interface"></i> -->
<!-- 					</div>					 -->
					
					</div>
				
				
				<div class="form-group form-floating-label input-group-prepend">
				
						<span class="input-group-text" id="basic-addon1"><i style='font-size:15px;width:12px' class='fas'>&#xf4fe;</i>	</span>
                <form:select style='font-size:12.5px;' id="type" path="type" name="type" class="form-control input-full"  >
                <form:option  value="Select">Select Role</form:option>
                	<form:option value="SuperAdmin">Super Admin</form:option>
                	<form:option value="Admin">Admin</form:option>
                	<form:option  value="Manager">Manager</form:option>
                	<form:option  value="FeildExecutive">Field Technician</form:option>                	
                </form:select>
               
                </div>
                

				<div class="form-action mb-3">
				<input  type="submit" class="btn btn-primary1 btn-rounded btn-login" value="Sign In">
				
				</div>
				
			</div>
						
			        </form:form>
			        
			 
		</div>
		  
		
	 
		
		</div>
		<div class=" loginNmsDet" >
			<b>Cyient ROFC-RFID application</b> is with open APIs that facilitates the telecom organizations to manage their OFCs/cables using RFID tags and readers/writers. It is an unique solution build over the strong Java framework. The powerful blend of an advanced rules engine, SDK, and web service APIs facilitates a seemless integration and implementation.
		</div>
		<div class="card-footer1">
									
									© 2019 Cyient 
								</div>
	
<!-- 	<div class="footer-new ms-dialogHidden" style="background-color:#666; "> -->
<!--             <div class="footer-custom"> -->
<!--                 <span style="width:100%; display:inline-block; background-color:#666; color:#fff; padding:2px 0px 2px 8px "> © 2019 Cyient                     -->
<!--                 </span> -->
<!--             </div> -->
<!--         </div> -->
	
	<script src="<c:url value='resources/assets/js/core/jquery.3.2.1.min.js' />"></script>
	<script src="<c:url value='resources//assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js' />"></script>
	<script src="<c:url value='resources/assets/js/core/popper.min.js' />"></script>
	<script src="<c:url value='resources/assets/css/bootstrap.min.css' />"></script>
	<script src="<c:url value='resources/assets/js/ready.js' />"></script>
</body>

</html>