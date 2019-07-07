/**
 * 
 *//**
 * 
 */
/**
 * 
 */
var inv_type;

//console.log("dm");
jQuery(document).ready(function($){
	$('#logout').click(function(){
		localStorage.clear();
		window.location.replace("http://localhost:8080/Project/Login.html");
		render_data();});
	
	$('#Submit').click(function(){
		$("#result").empty();
		render_data();
				
	});
	
	function render_data(){
		console.log("dm");
		if (document.getElementById('r1').checked) {
			  inv_type = document.getElementById('r1').value;
			}
		if (document.getElementById('r2').checked) {
			  inv_type = document.getElementById('r2').value;
			}
		value = {
				amount : $('#amt').val(),
				invest_type: inv_type,
				
		}
		console.log("gj"+value);
		get_data(value);
	}
	function get_data(value)
	{
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/buystock",
			data: JSON.stringify(value),
			type:"POST",
			dataType : "json",
			async: true,
	 	
			success: function(data) {
				
				if(data.result=="success"){
					
					var res=data.output;
					var res1="<b>Analysis:<b>";
			    	var resu=res1.fontsize(5);
				document.getElementById("result").innerHTML+=resu;
				for(var i=0;i<res.length;i++)
					{
					var resu1=res[i].fontsize(3);
					document.getElementById("result").innerHTML+=resu1;
					var as = "<br>";
					document.getElementById("result").innerHTML+= as;
					}
					
				}
				else{
					alert("Invalid ");
				}
			},
			
		});
	}
});