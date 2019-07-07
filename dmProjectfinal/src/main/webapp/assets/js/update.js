
var bankname;
var years;
var amount;
//console.log("dm");
jQuery(document).ready(function($){

	get_old_data(localStorage.getItem("email"));
	 $('#logout').click(function(){
	  		localStorage.clear();
	  		window.location.replace("http://localhost:8080/Project/Login.html");
	  	});
	
	$('#Submit').click(function(){
	
		render_data();
				
	});
	
	
	
	
	
	function render_data(){
		console.log("dm");
		value = {
				age : $('#age').val(),
				email : $('#email').val(),
				income : $('#income').val(),
				saving : $('#savings').val(),
				seniorcitizen : $('#category').val(),
				Employment : $("input[name='emp']:checked").val(),
			
		}
		console.log("gj"+value);
		get_data(value);
	}
	
	function get_old_data(value)
	{
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/olddata",
			data: value,
			type:"POST",
			dataType : "json",
			async: true,
	 	
			success: function(data) {
		          document.getElementById("name").value = data.name;
		          document.getElementById("age").value = data.age;
		          document.getElementById("income").value = data.income;
		          document.getElementById("email").value = data.email;
		          var type=data.e_type;
		          if(type==="Government Employee"){
		        	  
radiobtn= document.getElementById("ge");
radiobtn.checked=true;}
		else  if(type==="Self Employed"){
		          radiobtn= document.getElementById("se");
		          radiobtn.checked=true;}

		          document.getElementById("savings").value = data.saving;
		          document.getElementById("se").value = data.e_type;
		          document.getElementById("category").value = data.citizen;

			
			},
			
		});
	}
	function get_data(value)
	{
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/updateprofile",
			data: JSON.stringify(value),
			type:"POST",
			dataType : "text",
			async: true,
	 	
			success: function(data) {
				
				if(data==="success"){
					
					
					alert("Your data updates successfully.")
							
		
				}
				else{
					alert("Not updated");
				}
			},
			
		});
	}
});