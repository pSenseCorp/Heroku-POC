<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
 
<h1> <c:out value="${heading}"></c:out></h1>
 

	<c:if test="${not empty contacts}">
	<div>
		<h2>Contacts</h2>
		<table>
			<tr>
				<th><span><b>Name:</b></span></th>
				<th><span><b>Phone:</b></span></th>
			</tr>
			<c:forEach items="${contacts}" var="item">
				<tr>
		    		<td>${item.name}</td>
		    		<td>${item.phone}</td>
		    	</tr>
			</c:forEach>
		</table>
	</div>
	</c:if>
	 
	 <c:if test="${not empty message}"><p><b>Error Section</b></p>
	<div> ${message}</div>
	</c:if>

