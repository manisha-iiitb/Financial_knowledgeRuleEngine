/**
 * 
 *//**
 * 
 */
/**
 * 
 */
var BankName;
var timePeriod;
var principal;
//console.log("dm");
jQuery(document).ready(function($){
	getcom();
	$('#Submit').click(function(){
	//alert("sh");
		render_data();
				
	});
	
	function render_data(){
		console.log("dm");
		value = {
				BankName : $('#BankName').val(),
				timePeriod : $('#timePeriod').val(),
				principal : $('#principal').val(),
				
		}
		console.log("gj"+value);
		get_data(value);
	}
	
	function getcom(){
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/getbn",
			data: JSON.stringify(),
			type:"POST",
			dataType : "json",
			async: true,
	 	
			success: function(data) {
				var b=data.cn;
				for(var i=0;i<b.length;i++){
				var newoption=document.createElement("option");
				newoption.value=b[i];
				newoption.innerHTML=b[i];
				document.getElementById('BankName').options.add(newoption);
				}
				
			},
			
		});
	}
	function get_data(value)
	{
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/getEMI",
			data: JSON.stringify(value),
			type:"POST",
			dataType : "json",
			async: true,
	 	
			success: function(data) {
				
				if(data.result=="success"){
					alert (data.output);
					
					var res=data.output;
					System.out.println("kjl"+res);
				//$("body").append(start);
					var start="<div class='container'>"+"<div class='row'>";
					
					$("body").append(start);	
				var as="<div class='brand' align='center'>"+"Brand: "+res+
				"</div>";
				$("body").append(as);
				alert (res);
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