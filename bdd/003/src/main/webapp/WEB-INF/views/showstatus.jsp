<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!
</h1>
	<div id="result">
		<h2>
			<c:out value="${status}"></c:out>
		</h2>
	</div>
</body>
</html>
