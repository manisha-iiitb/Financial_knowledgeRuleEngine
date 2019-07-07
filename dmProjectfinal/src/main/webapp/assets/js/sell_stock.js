/**
 * 
 */
$(document).ready(function () {
	getcom(0);
    var next = 0;


    function Company(name, units, price) {
        this.name = name;
        this.units = units;
        this.price = price;
    }
    $('#logout').click(function(){
		localStorage.clear();
		window.location.replace("http://localhost:8080/Project/Login.html");
		render_data();});
    $("#add-more").click(function(e){
        e.preventDefault();
        var addto = "#field" + next;
        next = next + 1;
        var newIn = ' <div id="field'+ next +'" name="field'+ next +'"><div class="form-group"> <label class="col-md-4 control-label" for="action_id" style="color:#00CED1;"><b>Stock Name</b></label> <div class="col-md-5"> <!-- <input id="action_id" name="action_id" type="text" placeholder="" class="form-control input-md"> --> <form> <fieldset> <p> <select id = "drop'+ next +'"class="form-control"> </select> </p> </fieldset> </form> </div> </div><br><br> <!-- Text input--><div class="form-group"> <label class="col-md-4 control-label" for="action_name" style="color:#00CED1";><b>Number of shares owned</b></label> <div class="col-md-5"> <input id="share'+ next +'" name="action_name" type="text" placeholder="" class="form-control input-md"> </div></div><br><br><!-- Text input--> <div class="form-group"> <label class="col-md-4 control-label" for="action_name" style="color:#00CED1";><b>Buying Price</b></label> <div class="col-md-5"> <input id="price'+ next +'" name="action_name" type="text" placeholder="" class="form-control input-md"> </div> </div> <br><br></div></div>';
        var newInput = $(newIn);
        $(addto).after(newInput);
        $("#field" + next).attr('data-source',$(addto).attr('data-source'));
        $("#count").val(next);  
        var a='<br>';
        var inp=$(a);
        $(addto).after(inp);
        getcom(next);
    });

    $( "#evaluate" ).on( "click", function() {
    	$("#result").empty();
    	var i;
        var arrayList=[];
        var total=0;
    	for (i = 0; i < next+1; i++) {
    		var cn=$('#drop'+i).val();
    			
    		var sh=$('#share'+i).val();
    		var pr=$('#price'+i).val();
    		total=total+sh*pr;
    		
    		var employeeObject1 = new Company(cn,sh,pr);
            arrayList.push(employeeObject1);
     }
    	var res="<b>Analysis:<b>";
    	var resu=res.fontsize(5);
    	//<b><b>
    	document.getElementById("result").innerHTML+=resu;
		var as = "<br>";
		document.getElementById("result").innerHTML+= as;
		res="Your total investment price: Rs ";
		resu=res.fontsize(3);
    	document.getElementById("result").innerHTML+=resu;
    	document.getElementById("result").innerHTML+=total;
		var as = "<br>";
		document.getElementById("result").innerHTML+= as;
    	console.log(JSON.stringify(arrayList));
    	setsell2(arrayList);
    	setsell(arrayList);
    	
  	});
	function getcom(val){
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/loaddata",
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
				document.getElementById('drop'+val).options.add(newoption);
				
				}
				
			},
			
		});
	} 
	function setsell(val){
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/sellsent",
			data: JSON.stringify(val),
			type:"POST",
			dataType : "json",
			async: true,
	 	
			success: function(data) {
				var res=data.output;
				for(var i=0;i<res.length;i++)
					{
					document.getElementById("result").innerHTML+=res[i];
					var as = "<br>";
					document.getElementById("result").innerHTML+= as;
					}
			},
			
		});
	} 
    
	function setsell2(val){
		$.ajax({url:"http://localhost:8080/Project/webapi/myresource/sellsent2",
			data: JSON.stringify(val),
			type:"POST",
			dataType : "text",
			async: true,
	 	
			success: function(data) {
					document.getElementById("result").innerHTML+=data;
					var as = "<br>";
					document.getElementById("result").innerHTML+= as;
					
			},
			error: function(data) {
				alert("failed");
			}	
		});
	} 
});