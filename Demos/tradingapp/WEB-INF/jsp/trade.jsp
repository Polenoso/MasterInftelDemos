<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head><title>Trade</title></head>
<body>
<h1>Trade</h1>
<form method="post">

<!-- first bind on the object itself to display global errors - if available -->
<spring:bind path="trade.*">
	<font color="red">
	    <core:forEach items="${status.errorMessages}" var="error">
	    Error: <core:out value="${error}"/><br/>
	    </core:forEach>
    </font>
    <br/>
</spring:bind>

<table border="1">
	<tr>
		<td></td>
		<td><b>Symbol</b></td>
		<td><b>Shares</b></td>
	</tr>
	<tr>
		<td>
			<spring:bind path="trade.buySell">
			<input type="radio" 
			       name="buySell" 
			       value="true" 
			       <core:if test="${status.value}">checked</core:if> >
				Buy
			</input>
			<input type="radio" 
			       name="buySell" 
			       value="false" 
			       <core:if test="${! status.value}">checked</core:if> >
				Sell
			</input>
			</spring:bind>			
		</td>
		<td>
			<spring:bind path="trade.symbol">
			<input type="text" name="symbol" value="<core:out value="${status.value}"/>"/>
			</spring:bind>
		</td>
		<td>
			<spring:bind path="trade.shares">
			<input type="text" name="shares" value="<core:out value="${status.value}"/>"/>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td colspan="3" align="center"><input type="submit" alignment="center" name="_target1" value="Execute Order"></td>
	</tr>
</table>

    
</form>
<br>
<a href="<core:url value="portfolio.htm"/>">View Portfolio</a><br/>
<a href="<core:url value="logon.htm"/>">Log out</a>
<br>
</body>
</html>