//初始加载页面时 
$(document).ready(function(){

    //为获取单个值的按钮注册鼠标单击事件 
    $("#getMessage").click(function(){ 
        $.getJSON("jsontest!returnMessage",function(data){ 
            //通过.操作符可以从data.message中获得Action中message的值 
            $("#message").html("<font color='red'>"+data.message+"</font>"); 
        }); 

    }); 

    //为获取UserInfo对象按钮添加鼠标单击事件 
    $("#getUserInfo").click(function(){ 
        $.getJSON("jsontest!returnUserInfo",function(data){ 

            //清空显示层中的数据 
            $("#message").html(""); 
            $("#message").append("<div><font color='red'>User ID: "+data.userInfo.userId+"</font></div>") 
                         .append("<div><font color='red'>User Name: "+data.userInfo.userName+"</font></div>") 
                         .append("<div><font color='red'>Pass Word: "+data.userInfo.passWord+"</font></div>");

        }); 

    }); 

    //为获取List对象按钮添加鼠标单击事件 
    $("#getList").click(function(){ 
        $.getJSON("jsontest!returnList",function(data){ 
            $("#message").html(""); 
            
            var rs = "<table border='1' >" +
            		"<tr><th>index</th>" +
            		"<th>User ID</th>" +
            		"<th>User Name</th>" +
            		"<th>Pass Word</th></tr>";
            //使用jQuery中的each(data,function(){});
            //从data.userInfosList获取UserInfo对象放入value之中 
            $.each(data.userInfosList,function(i,value){ 
            	rs = rs+"<tr><td>"+i+"</td>";
            	rs = rs+"<td>"+value.userId+"</td>";
            	rs = rs+"<td>"+value.userName+"</td>";
            	rs = rs+"<td>"+value.passWord+"</td></tr>";
            });
            rs = rs+"</table>";
            
            $("#message").html(rs);

        }); 

    }); 

    //为获取Map对象按钮添加鼠标单击事件 
    $("#getMap").click(function(){ 

        $.getJSON("jsontest!returnMap",function(data){ 

            $("#message").html(""); 
            //使用jQuery中的each(data,function(){});函数 
            //从data.userInfosList获取UserInfo对象放入value之中 
            //key值为Map的键值 

            var rs = "<table border='1' ><tr><th>Key</th><th colspan='2'>Value</th></tr>";
            $.each(data.userInfosMap,function(key,value){ 
            	rs = rs +"<tr><td>"+key+"</td>";
            	rs = rs +"<td>"+value.userName+"</td>";
            	rs = rs +"<td>"+value.passWord+"</td></tr>";
            }); 
            rs = rs+"</table>";
            
            $("#message").html(rs);

        }); 

    }); 

    //向服务器发送表达数据 
    $("#regRe").click(function(){ 
        //把表单的数据进行序列化 
        var params = $("form").serialize(); 

        //使用jQuery中的$.ajax({});Ajax方法 
        $.ajax({ 
            url:"jsontest!gainUserInfo", 
            type:"POST", 
            data:params, 
            dataType:"json", 
            success:function(data){
	            $("#message").html(""); 
	            $("#message").append("<div><font color='red'>User ID: "+data.userInfo.userId+"</font></div>") 
	                .append("<div><font color='red'>User Name: "+data.userInfo.userName+"</font></div>") 
	                .append("<div><font color='red'>Pass Word: "+data.userInfo.passWord+"</font></div>");
            }
        });
    }); 
}); 