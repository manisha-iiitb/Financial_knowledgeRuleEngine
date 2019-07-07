/**
 * 
 *//**
 * 
 */
/**
 * 
 */

var duration;
var risk;
//console.log("dm");
jQuery(document).ready(function($){
	setData(localStorage.getItem("email"));

	$('#Submit').click(function(){
	
		render_data();
				
	});
	$('#logout').click(function(){
		localStorage.clear();
		window.location.replace("http://localhost:8080/Project/Login.html");
		render_data();});
	function render_data(){
		console.log("dm");
		value = {
				//age : $('#age').val(),
				//savings : $('#savings').val(),
				duration : $('#dur').val(),
				risk : $('#risk').val()				
		}
		console.log("gj"+value);
		get_data(value);
	}
	function setData(value)
	{
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/setMFDetails",
			data: value,
			type:"POST",
			dataType : "json",
			async: true,
			success: function(data) {
				 var a=data.age;
				 var saving=data.savings;
				//alert(inc);	
				//alert(cat);
					//$("#result").empty();
					document.getElementById("age").value = a;
				
					document.getElementById("save").value = saving;
				alert(a);	
				
			},
			
		});
	}
	function get_data(value)
	{
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/Mutualfund",
			data: JSON.stringify(value),
			type:"POST",
			dataType : "json",
			async: true,
	 	
			success: function(data) {
				
				if(data.result=="success"){
					
					var res=data.output;
					$("#result").empty();
					document.getElementById('result').innerHTML += res ; 
				//$("body").append(start);
				/*	var start="<div class='container'>"+"<div class='row'>";
					
					$("body").append(start);	
				var as="<div class='brand' align='center'>"+"Brand: "+res+
				"</div>";
				$("body").append(as);
				alert (res);*/
			//	var uid=$("#companyname").val();
				
				//alert(sessionStorage.getItem("user_id"));
		
				}
				else{
					alert("Invalid ");
				}
			},
			
		});
	}
});