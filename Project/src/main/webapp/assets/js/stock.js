/**
 * 
 *//**
 * 
 */
/**
 * 
 */
var companyname;
var earningpershare;
//console.log("dm");
jQuery(document).ready(function($){
	
	getcom();
	$('#Submit').click(function(){
	
		render_data();
				
	});
	
	function getcom(){
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/getcn",
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
				document.getElementById('myList').options.add(newoption);
				}
				
			},
			
		});
	}
	
	function render_data(){
		console.log("dm");
		value = {
				companyname : $('#myList').val(),
				cps : $('#cps').val(),
				
		}
		//alert(value.companyname);
		get_data(value);
	}
	function get_data(value)
	{
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/getEPS",
			data: JSON.stringify(value),
			type:"POST",
			dataType : "json",
			async: true,
	 	
			success: function(data) {
				
				if(data.result=="success"){
					//alert (data.output);
					
					var res=data.output;
									//$("body").append(start);
					var start="<div class='container'>"+"<div class='row'>";
					
					$("body").append(start);	
				var as="<br><div class='brand' align='center'>"+"RESULT: "+res+
				"</div>";
				$("body").append(as);
				//alert (res);
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