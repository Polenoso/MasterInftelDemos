<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head><title>DevX.com Stock-Trading System Logon</title></head>
<body>

<center>
    <h1>Welcome to the DevX.com Stock-Trading System</h1>
    <br/>

    <form method="post">
        <table width="25%" border="1">
            <tr>
                    <td align="center" bgcolor="lightblue">Log on</td>
            </tr>
            <tr>
                <td>
                    <table border="0" width="100%">
                        <tr>
                            <td width="33%" align="right">Username: </td>
                            <td width="66%" align="left">
                                <spring:bind path="credentials.username">
                                <input type="text"
                                       name="username"
                                       value="<core:out value="${status.value}"/>"/>
                                </spring:bind>
                            </td>

                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <spring:hasBindErrors name="credentials">
                                <font color="red"><core:out value="${status.errorMessage}"/></font>
                                </spring:hasBindErrors>
                            </td>
                        </tr>
                        <tr>
                            <td width="33%" align="right">Password: </td>
                            <td width="66%" align="left">
                                    <spring:bind path="credentials.password">
                                    <input type="password" name="password" />
                                    </spring:bind>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                    <spring:hasBindErrors name="credentials">
                                    <font color="red"><core:out value="${status.errorMessage}"/></font>
                                    </spring:hasBindErrors>
                            </td>
                        </tr>
                        <tr>
                            <td align="center" colspan="2">
                                    <input type="submit" alignment="center" value="Logon">
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

    </form>

</center>

</body>
</html>