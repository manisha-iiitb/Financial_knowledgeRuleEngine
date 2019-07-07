var companyname;
var earningpershare;
//console.log("dm");
jQuery(document).ready(function($){
	
	$('#SB').click(function(){
		window.location.replace("http://localhost:8080/Project/buy.html");
		//render_data();
		//setData(localStorage.getItem("email"));
				
	});
	
	$('#SS').click(function(){
		window.location.replace("http://localhost:8080/Project/sell_stock.html");
		//render_data();
				
	});
	
	$('#GI').click(function(){
		window.location.replace("http://localhost:8080/Project/Mutual_Fund.html");
		//render_data();
				
	});
	
	
	$('#FD').click(function(){
		window.location.replace("http://localhost:8080/Project/FDAnalysis.html");
		//render_data();

				
	});
	
	
	$('#HL').click(function(){
		window.location.replace("http://localhost:8080/Project/HomeLoanEligibility.html");
		//render_data();
				
	});
	$('#logout').click(function(){
		localStorage.clear();
		window.location.replace("http://localhost:8080/Project/Login.html");
		render_data();});
});