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
	
	$('#Submit').click(function(){
	
		render_data();
				
	});
	
	function render_data(){
		console.log("dm");
		value = {
				carat : $('#carat').val(),
				grams : $('#grams').val(),
				
		}
		console.log("gj"+value);
		get_data(value);
	}
	function get_data(value)
	{
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/getGoldInvest",
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