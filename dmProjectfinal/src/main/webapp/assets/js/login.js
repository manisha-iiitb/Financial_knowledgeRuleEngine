
var bankname;
var years;
var amount;
//console.log("dm");
jQuery(document).ready(function($){

	
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
				email : $('#email').val(),
				password : $('#password').val(),	
		}
		console.log("gj"+value);
		get_data(value);
	}
	function get_data(value)
	{
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/login",
			data: JSON.stringify(value),
			type:"POST",
			dataType : "text",
			async: true,
	 	
			success: function(data) {
				
				if(data=="success"){
					localStorage.setItem("email", $('#email').val());

					window.location.replace("http://localhost:8080/Project/PortfolioAnalysis.html");

					
					/*var res=data.output;
					$("#result").empty();
					document.getElementById('result').innerHTML += res ;*/ 		
				}
				else{
					alert(data);
				}
			},
			
		});
	}
});