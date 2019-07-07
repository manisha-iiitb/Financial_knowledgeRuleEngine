/*var BankName;
var timePeriod;
var principal;*/
// console.log("dm");
jQuery(document)
		.ready(function($) {
					setData(localStorage.getItem("email"));
					$('#Submit').click(function() {
						// alert("sh");
						render_data();

					});
					$('#logout').click(function(){
						localStorage.clear();
						window.location.replace("http://localhost:8080/Project/Login.html");
						render_data();});
					function setData(value)
					{
						$.ajax({url:"http://localhost:8080/Project/webapi/myresource/setHomeLoanDetails",
							data: value,
							type:"POST",
							dataType : "json",
							async: true,
							success: function(data) {
								 var employment =data.emp;
								 var age = data.age;
								 var Monthly = data.income;
									document.getElementById("emp").innerHTML = employment;
									document.getElementById("Age").innerHTML = age;
									document.getElementById("MonthlyIncome").innerHTML = Monthly;
																
							},
							
						});
					}	

					function render_data() {
						console.log("dm");

						value = {
							Age : $('#Age').val(),
							MonthlyIncome : $('#MonthlyIncome').val(),
							//Employment : $("input[name='emp']:checked").val(),
							ITR1 : $('#ITR1').val(),
							//email: localStorage.getItem("email"),
						}
						//alert("values are " + value.Employment);
						// console.log("gj"+value);
						get_HomeLoanEligibility(value);
					}
				
					
					function getcom(){
						$.ajax({url:"http://localhost:8080/Project/webapi/myresource/getAge",
							data: JSON.stringify(),
							type:"POST",
							dataType : "json",
							async: true,
					 	
							success: function(data) {
								var b=data.cn;
								document.getElementById('BankName').add(b);
								}
								
							
							
						});
					}

					function get_HomeLoanEligibility(value) {
						//alert("inside api");
						$.ajax({
									url :"http://localhost:8080/Project/webapi/myresource/getHomeLoanEligibility",
									data : JSON.stringify(value),
									type : "POST",
									dataType : "json",
									async : true,

									success : function(data) {
									
										if (data.result == "success") {
											var res = data.output;
											$("#result").empty();
											document.getElementById('result').innerHTML += res ; 

										} else {
											alert("Invalid ");
										}
									},

								});
					}
				});