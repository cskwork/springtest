<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert title here</title>
</head>
<body>
<div>
	<span id="currTime"></span>
</div>

<ul>
	<li>
		<a href="/board/list">글 목록</a>
	</li>
	<li>
		<a href="/board/write">글 작성</a>
	</li>
</ul>

<script src="https://cdnjs.cloudflare.com/ajax/libs/dayjs/1.10.7/dayjs.min.js" integrity="sha512-bwD3VD/j6ypSSnyjuaURidZksoVx3L1RPvTkleC48SbHCZsemT3VKMD39KknPnH728LLXVMTisESIBOAb5/W0Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script>
var now = dayjs()
now = dayjs().format("YYYY-MM-DD hh:mm:s")

window.addEventListener("load", function(event) {
	var currTime = document.getElementById("currTime");
	currTime.textContent += now;
});

</script>

</body>

<!-- 
https://en.wikipedia.org/wiki/ISO_8601
https://john015.netlify.app/moment-js%EB%A5%BC-day-js%EB%A1%9C-%EB%8C%80%EC%B2%B4%ED%95%98%EA%B8%B0

YYYY-MM-DD
YYYY-Www
hh:mm:ss.sss

 -->
</html>