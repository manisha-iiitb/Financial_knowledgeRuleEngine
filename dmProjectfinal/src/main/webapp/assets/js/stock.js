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
		$("#result").empty();

		render_data();
				
	});
	$('#drop').change(function () {
		//console.log("-------------------------------------------------------------------");
		value = {
				choice : $('#drop').val(),
					
		}
		display(value);
	}); 
	$('#logout').click(function(){
		localStorage.clear();
		window.location.replace("http://localhost:8080/Project/Login.html");
		render_data();});
	function display(value){
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/show",
			data: JSON.stringify(value),
			type:"POST",
			dataType : "json",
			async: true,
	 	
			success: function(data) {
				var cps=data.current_stock_price;
				var eps=data.earning_per_share;
				console.log(cps);
				
				document.getElementById("eps").innerHTML = eps;
				document.getElementById("cps").innerHTML = cps;	
				
			},
			
		});
	}
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
				document.getElementById('drop').options.add(newoption);
				}
				
			},
			
		});
	}
	
	function render_data(){
		console.log("dm");
		value = {
				companyname : $('#drop').val(),
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
					
					
					var resu1=res.fontsize(3);
					document.getElementById("result").innerHTML+=resu1;
					var as = "<br>";
					document.getElementById("result").innerHTML+= as;
	
				}
				else{
					alert("Invalid ");
				}
			},
			
		});
	}
});