<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>

</head>
<body>
	<form method="Get" action="<%=request.getContextPath()%>/EmpServlet">
		<input type="submit" value="서블릿이동">
	</form>
</body>
</html>