<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin View</title>
<link rel="stylesheet" type="text/css" href="css/table.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript">
	var count = 2;
	
	
	$( document ).ready(function() {
		
		
		
		$( "#saveData_1" ).click(function(){
			updateData(1);
		});
		
		//load all users
		loadUsers();
		
		$( "#newBtton" ).click(function() {
			createNewRow(false);
		});
		
		
		
		
	});
	
	
	var createNewRow = function(updateFlag){
			var td0 = "<tr><td><input type='hidden' id='id_"+ count +"' ></input></td>"
			var td1 = "<td><input id='name_"+ count +"' /></td>";
			var td2 = "<td><input type='text' id='userName_"+ count +"' /> </td>";
			var td3 = "<td><input type='text' id='address_"+ count +"' /></td>";
			var td4 = "<td><input type='text' id='company_"+ count +"' /></td>";
			var td5 = "<td><input type='email' id='email_"+ count +"' /></td>";
			var td6 = "<td><input type='password' id='password_"+ count +"' /></td>";
			var td7 = "<td><select id='userType_"+ count +"'  > "+
						  "<option value=''></option> "+
						  "<option value='Admin'>Admin</option> "+
						  "<option value='Suppliers'>Suppliers</option> "+
						  "<option value='Corporates'>Corporates</option> "+
						  "<option value='Employees'>Employees</option> "+
						"</select></td>";
			var td8 = "";
			if (updateFlag){
				td8 = "<td><button id='updateData_"+ count +"' onclick='updateData("+count+")'  >Save</button></td></tr>";
			}
			else{
				td8 = "<td><button id='saveData_"+ count +"' onclick='saveData("+count+")'  >Save</button> "+
						"<button id='updateData_"+ count +"' style='display:none' onclick='updateData("+count+")'  >Save</button></td></tr>";
			}
			$("#userTable").append(td0+td1+td2+td3+td4+td5+td6+td7+td8);
			count++;
	}
	
	var getJsonData = function(number,accountType){
		console.log("number is "+number);
		
		var name = $("#name_"+number).val();
		var userName = $("#userName_"+number).val();
		var address = $("#address_"+number).val();
		var company = $("#company_"+number).val();
		var email = $("#email_"+number).val();
		var password = $("#password_"+number).val();
		
		
		if( !(name  && userName && address && company && email && password && accountType)){
			alert("Please enter all the fields and try again.");
			return null;
		}
		
		var jsonData = JSON.stringify({ "name" : name, "userName": userName, "address": address, 
						"company" : company, "email": email, "password" : password , "userTypeName":  accountType   } );
		return jsonData;
	}
	
	var saveData = function(number) {
		var accountType = $("#userType_"+number).val();	
		var json = getJsonData(number,accountType);
		if (json){
			postData("save",json,number);
		}
	}
	
	var updateData = function(number) {	
		var accountType = $("#userType_"+number).attr('value');
		if (accountType === undefined)
			accountType =  $("#userType_"+number).val();	
		var json = getJsonData(number,accountType);
		if (json){
			postData("update",json,number);
		}
	}
		
	var postData = function(saveOrUpdate, jsonData,number){
		var postUrl = '';
		if (saveOrUpdate == "save"){
			postUrl = "http://localhost:8080/zsell/account.json";
		}
		else if (saveOrUpdate == "update"){
			postUrl = "http://localhost:8080/zsell/user/"+ ($("#id_"+number).val() ) +".json";
		}
		else{
			alert("Invalid Operation!!");
			
		}
		$.ajax({
			type: "POST",
			url: postUrl,
			data: jsonData,
			contentType: 'application/json'
		})
			.success(function( message ) {
				console.log(message);
				$("#message").text(message.message);
				if (message.success){
					$("#id_"+ (number) ).attr("value", message.user[0].id);
					$("#saveData_"+number).hide();
					$("#updateData_"+number).show();
				}
			});
	}
	
	var loadUsers = function(){
		
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/zsell/users/all.json",
			contentType: 'application/json'
		})
			.done(function( message ) {
				if (message.success){
					$.each( message.user, function( key, value ) {
					
					 createNewRow(true);
					 $("#id_"+ (count-1) ).attr("value", value.id);
					 $("#name_"+ (count-1) ).attr("value", value.name);
					 $("#userName_"+ (count-1) ).attr("value", value.userName);
					 $("#address_"+ (count-1) ).attr("value", value.address);
					 $("#company_"+ (count-1) ).attr("value", value.company);
					 $("#email_"+ (count-1) ).attr("value", value.email);
					 $("#userType_"+ (count-1)+" option[value=" + value.userType.name +"]").attr("selected","selected") ;
					});
				}
			});
	}
	
</script>
</head>
<body>
	
	<jsp:include page="header.jsp"></jsp:include>	
	
	<div>
		<button id="newBtton"  >Create New User</button>
		<br /><br />
	</div>
	<table class="CSSTableGenerator" id="userTable">
		<tr>
			<th colspan='2'>User Name</th>
			<th>Login</th>
			<th>Address</th>
			<th>Company Name</th>
			<th>Email</th>
			<th>New Password</th>
			<th>Account Type</th>
			<th>Save</th>
		</tr>
		<tr>
			<td><input type='hidden' id='id_1' value='${user.id}' ></input></td>
			<td><input id='name_1' value='${user.name}' ></input></td>
			<td><input id='userName_1' value='${user.userName}' ></input>
			<td><input id='address_1' value='${user.address}' ></input>
			<td><input id='company_1' value='${user.company}' ></input>
			<td><input id='email_1' value='${user.email}' ></input>
			<td><input type="password" id="password_1" /></td>
			<td ><input id='userType_1' readonly="readonly" value='<c:out value="${user.userType.name}"  ></c:out>'  title="You cannot modify your own profile" ></td>
			<td><button id="saveData_1"  >Save</button></td>
		</tr>
	</table>
		<br /><div id="message"></div>
</body>
</html>