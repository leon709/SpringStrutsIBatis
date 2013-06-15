function expend(select){
    if($(select).html()=="+"){
        $(select).html('-');
        //$(select).parents('tr').css('background-color','#FFBB66');
        $(select).parents('tr').next(".blind").css('background-color','#c6daf5');
        $(select).parents('tr').next(".blind").show();
         
        //ajaxLoadingDetail(select);
        jsonLoadingDetail(select);
    }else{
        $(select).html('+');
        $(select).parents('tr').next(".blind").hide();
    }
    
}
 
function ajaxLoadingDetail(select){
	//generally we use ajax to load data from backend dao here
    var studentId = $(select).next("td").html();
     
    $.ajax({    
        type:'post', 
        url:'ajaxGetStudentCourseDetail',
        data: {"studentId":studentId},
        dataType:"text",
        success: function(data) {                       
            $(select).parents('tr').next(".blind").find("td").html(data);
        },
        error: function(data) {
            alert('request fail!');
        }    
    });
}
function jsonLoadingDetail(select){
	//generally we use ajax to load data from backend dao here
	var studentId = $(select).next("td").html();
	
	$.ajax({    
	    type:'post', 
	    url:'jsonGetStudentCourseDetail',
	    data: {"studentId":studentId},
	    dataType:"json",
	    success: function(data){
	    	var rs = "<table width='100%' >" +
	    	"<tr bgcolor='#00AA88' >" +
	    	"<th width='100' >Name</th>" +
	    	"<th>Course Name</th>" +
	    	"<th width='100' >Score</th>" +
	    	"<th width='100' >Teacher Name</th></tr>";
	        //使用jQuery中的each(data,function(){});
	        //迭代data.studentCourseList对象放入value之中 
	        $.each(data.studentCourseList,function(i,value){ 
	        	rs = rs+"<tr><td>"+value.studentName+"</td>";
	        	rs = rs+"<td>"+value.courseName+"</td>";
	        	rs = rs+"<td>"+value.score+"</td>";
	        	rs = rs+"<td>"+value.teacherName+"</td></tr>";
	        });
	        rs = rs+"</table>";
	        
	        $(select).parents('tr').next(".blind").find("td").html(rs);
	    },
	    error: function(data) {
	        alert('request fail!');
	    } 
	});
}
