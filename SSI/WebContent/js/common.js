function getDatetime(){
	var d = new Date(); 
	
	var hour;
	var second;
	var minute;
	var year;
	var month;
	var date;

	year = d.getFullYear(); 
	month = d.getMonth()+1; 
	date = d.getDate(); 
	hour = d.getHours(); 
	minute =d.getMinutes(); 
	second = d.getSeconds(); 
	
	if(month<10) month="0"+month; 
	if(date<10) date="0"+date; 
	if(hour<10) hour="0"+hour; 
	if(minute<10) minute="0"+minute; 
	if(second<10) second="0"+second; 
	
	//var datetime=year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
	var datetime=month+"/"+date+"/"+year+" " + hour + ":" + minute + ":" + second;
	return datetime;
}