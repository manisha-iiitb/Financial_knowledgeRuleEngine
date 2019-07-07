
var bankname;
var years;
var amount;
//console.log("dm");
jQuery(document).ready(function($){

	
	$('#Submit').click(function(){
	
		render_data();
				
	});
	
	function render_data(){
		console.log("dm");
		value = {
				name : $('#name').val(),
				age : $('#age').val(),
				email : $('#email').val(),
				income : $('#income').val(),
				saving : $('#savings').val(),
				seniorcitizen : $('#category').val(),
				Employment : $("input[name='emp']:checked").val(),
				password : $('#password').val(),
				
		}
		console.log("gj"+value);
		get_data(value);
	}
	function get_data(value)
	{
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/register",
			data: JSON.stringify(value),
			type:"POST",
			dataType : "text",
			async: true,
	 	
			success: function(data) {
				
				if(data==="success"){
					window.location.replace("http://localhost:8080/Project/Login.html");

					
					/*var res=data.output;
					$("#result").empty();
					document.getElementById('result').innerHTML += res ;*/ 		
				}
				else{
					alert("Invalid ");
				}
			},
			
		});
	}
});