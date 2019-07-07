/**
 * 
 //*
 * 
 */
/**
 * 
 */
//var income;
//var category;
var years;
var amount;
//console.log("dm");
jQuery(document).ready(function($){
	
	setData(localStorage.getItem("email"));
     // getbankNames();
	$('#logout').click(function(){
		localStorage.clear();
		window.location.replace("http://localhost:8080/Project/Login.html");
		render_data();});
	$('#Submit').click(function(){
	
		render_data();
				
	});
	$('#mybank').change(function () {
		console.log("-------------------------------------------------------------------");
	});

	
	function setData(value)
	{
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/setFDDetails",
			data: value,
			type:"POST",
			dataType : "json",
			async: true,
			success: function(data) {
				 var inc=data.income;
				 var cat=data.category;
				//alert(inc);	
				//alert(cat);
					//$("#result").empty();
					document.getElementById("income").innerHTML = inc;
				
					document.getElementById("category").innerHTML = cat;
					
				
			},
			
		});
	}	
	function render_data(){
		console.log("dm");
		value = {
				//income : $('#income').val(),
				//category : $('#category').val(),
				years : $('#years').val(),
				amount : $('#amount').val(),
				
		}
		console.log("gj"+value);
		get_data(value);
	}
	function get_data(value)
	{
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/FDAnalysis",
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
//					var start="<div class='container'>"+"<div class='row'>";
//					
//					$("body").append(start);	
//				var as="<br><div class='brand' align='center'><b>"+"Result: "+res+
//				"</b></div>";
//				$("body").append(as);
				
			//	var uid=$("#companyname").val();
				
			
		
				}
				else{
					alert("Invalid ");
				}
			},
			
		});
	}
});