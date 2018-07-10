<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>pic</title>
</head>


<body>
	
	<script type="text/javascript">
		/**
		使用图片形式发送http请求，返回的是一张图片，
		向后端发http请求，如果是将此请求集成在https页面会报错，使用图片加载访问的方式可避免此问题。
		*/	
		var img = document.createElement("img");
		img.src = "http://localhost:8080/spring-struts2/hello";
		
		//查看获取的图片，如果获取成功，则弹窗alert(2),如果不成功，则弹窗alert(3)
		if(img.fileSize > 0 || (img.width > 0 && img.height > 0)){
			alert(2);
	    } else {
			alert(3);
		}
		
		//图片格式：不进行显示-none;显示呈现-block
		img.style.display = "block";
			
		
		//在body标签添加图片
		document.body.appendChild(img);
		
		//如果图片加载成功，则报1
		img.onload = function() {
			alert(1);
		}
	</script>


</body>
</html>