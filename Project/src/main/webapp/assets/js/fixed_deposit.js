/**
 * 
 //*
 * 
 */
/**
 * 
 */
var bankname;
var years;
var amount;
//console.log("dm");
jQuery(document).ready(function($){

      getbankNames();
	
	$('#Submit').click(function(){
	
		render_data();
				
	});
	
	function getbankNames(){
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/getbank",
			data: JSON.stringify(),
			type:"POST",
			dataType : "json",
			async: true,
	 	
			success: function(data) {
				var b=data.bn;
				for(var i=0;i<b.length;i++){
				var newoption=document.createElement("option");
				newoption.value=b[i];
				newoption.innerHTML=b[i];
				document.getElementById('mybank').options.add(newoption);
				}
				
			},
			
		});
	}
	
	
	
	function render_data(){
		console.log("dm");
		value = {
				bankname : $('#mybank').val(),
				years : $('#years').val(),
				amount : $('#amount').val(),
				
		}
		console.log("gj"+value);
		get_data(value);
	}
	function get_data(value)
	{
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/MaturityAmount",
			data: JSON.stringify(value),
			type:"POST",
			dataType : "json",
			async: true,
	 	
			success: function(data) {
				
				if(data.result=="success"){
					
					
					var res=data.output;
					
				//$("body").append(start);
					var start="<div class='container'>"+"<div class='row'>";
					
					$("body").append(start);	
				var as="<br><div class='brand' align='center'>"+"Result: "+res+
				"</div>";
				$("body").append(as);
				
			//	var uid=$("#companyname").val();
				
			
		
				}
				else{
					alert("Invalid ");
				}
			},
			
		});
	}
});