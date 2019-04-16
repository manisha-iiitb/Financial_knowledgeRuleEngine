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
	
	$('#PE').click(function(){
		window.location.replace("http://localhost:8080/Project/Stock.html");
		render_data();
				
	});
	$('#GI').click(function(){
		window.location.replace("http://localhost:8080/Project/Gold.html");
		render_data();
				
	});
	$('#FD').click(function(){
		window.location.replace("http://localhost:8080/Project/Fixed_Deposit.html");
		render_data();
				
	});
	$('#HL').click(function(){
		window.location.replace("http://localhost:8080/Project/HomeLoan.html");
		render_data();
				
	});
	
});