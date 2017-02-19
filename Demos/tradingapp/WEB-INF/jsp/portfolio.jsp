<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head><title>Portfolio</title></head>
<body>
<h1>Portfolio</h1>
<b>Cash:</b> <fmt:formatNumber value="${model.cash}" type="currency" />
<br/>
<br/>
<table border="1">
	<tr>
		<td><b>Symbol</b></td>
		<td><b>Company</b></td>
		<td><b>Price</b></td>
		<td><b>Change</b></td>
		<td><b>% Change</b></td>
		<td><b>Shares</b></td>
		<td><b>Open</b></td>
		<td><b>Volume</b></td>
		<td><b>Current Value</b></td>
		<td><b>Gain/Loss</b></td>
	</tr>
	<core:forEach items="${model.portfolioItems}" var="stock">
	<tr>
		<td><str:upperCase><core:out value="${stock.symbol}"/></str:upperCase></td>
		<td><core:out value="${stock.quote.company}"/></td>
		<td><fmt:formatNumber value="${stock.quote.value}" type="currency" /></td>
		<td>
			<core:choose>
				<core:when test="${stock.quote.change >= 0}">
					<fmt:formatNumber value="${stock.quote.change}" type="currency" />
				</core:when>
				<core:otherwise>
					<font color="red">
						<fmt:formatNumber value="${stock.quote.change}" type="currency" />
					</font>
				</core:otherwise>
			</core:choose>	
		</td>
		<td>
			<core:choose>
				<core:when test="${stock.quote.pctChange >= 0}">
					<fmt:formatNumber value="${stock.quote.pctChange}" type="percent" />
				</core:when>
				<core:otherwise>
					<font color="red">
						<fmt:formatNumber value="${stock.quote.pctChange}" type="percent" />
					</font>
				</core:otherwise>
			</core:choose>	
		</td>
		<td><fmt:formatNumber value="${stock.shares}"/></td>
		<td><fmt:formatNumber value="${stock.quote.openPrice}" type="currency" /></td>
		<td><fmt:formatNumber value="${stock.quote.volume}"/></td>
		<td><fmt:formatNumber value="${stock.currentValue}" type="currency" /></td>
		<td>
			<core:choose>
				<core:when test="${stock.gainLoss >= 0}">
					<fmt:formatNumber value="${stock.gainLoss}" type="currency" />
				</core:when>
				<core:otherwise>
					<font color="red">
						<fmt:formatNumber value="${stock.gainLoss}" type="currency" />
					</font>
				</core:otherwise>
			</core:choose>	
		</td>
	</tr>
	</core:forEach>
</table>
<br>
<a href="<core:url value="trade.htm"/>">Make a trade</a><br/>
<a href="<core:url value="logon.htm"/>">Log out</a>
<br>
</body>
</html>