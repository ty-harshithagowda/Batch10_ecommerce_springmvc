<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%

	String msg = (String) request.getAttribute("msg");
%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
		if (msg != null && !msg.isEmpty()) {
	%>
	<h1>
		<%=msg%></h1>
	<%
		}
	%>
	<fieldset>
		<legend>Update Item</legend>
		<form action="./update" method="post">
			<table>
				<tr>
					<td>Item ID</td>
					<td>:</td>
					<td><input type="number" name="id"
						placeholder="Enter the ID" required="required" ></td>
				</tr>
				<tr>
					<td>Item Name</td>
					<td>:</td>
					<td><input type="text" name="name"
						placeholder="Enter the Name" ></td>
				</tr>
				<tr>
					<td>Item qty</td>
					<td>:</td>
					<td><input type="text" name="qty"
						placeholder="Enter the qty" ></td>
				</tr>
				<tr>
					<td>Item Price</td>
					<td>:</td>
					<td><input type="text" name="price"
						placeholder="Enter the price" ></td>
				</tr>
				<tr>
					<td><input type="submit" value="update"></td>
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>