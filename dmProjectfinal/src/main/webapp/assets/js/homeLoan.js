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
	$('#logout').click(function(){
		localStorage.clear();
		window.location.replace("http://localhost:8080/Project/Login.html");
		render_data();});
	function render_data(){
		console.log("dm");
		value = {
				BankName : $('#BankName').val(),
				timePeriod : $('#timePeriod').val(),
				principal : $('#principal').val(),
				
		}
		//console.log("gj"+value);
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
				
			}
			
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
					//alert (data.output);
					
					var res=data.output;
					$("#result").empty();
					document.getElementById('result').innerHTML += res ; 
	
		
				}
				else{
					alert("Invalid ");
				}
			},
			
		});
	}
});