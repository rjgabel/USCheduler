
$(window).on("load", function() { 
	var mEvent = JSON.parse(sessionStorage.getItem("eventJSON"));
	var start = mEvent.time;
	start = start.split(" ")[1].split(":")[0];
	start = Number(start);
		
	if(start > 12) {
		start = start%12 + "pm"
	}
	else if (start == 12) {
		start += "pm"
	}
	else {
		start += "am"
	}
			
	var end = mEvent.time_end;
	end = end.split(" ")[1].split(":")[0];
	end = Number(end);
	if(end > 12) {
		end = end%12 + "pm"
	}
	else if (end == 12) {
		end += "pm"
	}
	else {
		end += "am"
	}
	
	var date = mEvent.time.split(" ")[0].replaceAll("-","/");
	
	document.getElementById("title").innerHTML = mEvent.name;
	document.getElementById("club").innerHTML = mEvent.organizer;
	document.getElementById("time").innerHTML = date + " | " + start + " - " + end;
	document.getElementById("description").innerHTML = mEvent.description;
	document.getElementById("eventImg").src = mEvent.img_url;
	
});