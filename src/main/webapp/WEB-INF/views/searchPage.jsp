<%@page import="com.te.springmvc.beans.ItemBean"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
ItemBean infoBean = (ItemBean) request.getAttribute("data");
String msg = (String) request.getAttribute("msg");
List<ItemBean> infoBeans = (List) request.getAttribute("infobeans");
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
		<legend>Search</legend>
		<form action="./search" method="get">
			<table>
				<tr>
					<td>Item ID</td>
					<td>:</td>
					<td><input type="number" name="aid" placeholder="Enter the ID"></td>
				</tr>

				<tr>
					<td><input type="submit" value="Search"></td>
				</tr>
			</table>
		</form>
	</fieldset>

	<%
	if (infoBean != null) {
	%>
	<h1>
		ID:
		<%=infoBean.getId()%></h1>
	<h1>
		Name :
		<%=infoBean.getName()%></h1>
	<h1>
		qty :
		<%=infoBean.getQty()%></h1>
		<h1>
		Price :
		<%=infoBean.getPrice()%></h1>
	<%
	}
	%>

	<%
	if (infoBeans != null) {
	%>
	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>qty</th>
			<th>Price</th>
		</tr>
		<%
		for (ItemBean bean : infoBeans) {
		%>
		<tr>
			<td><%=bean.getId()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getQty()%></td>
			<td><%=bean.getPrice()%></td>
		</tr>

	</table>

	<%
	}
	}
	%>
</body>
</html>