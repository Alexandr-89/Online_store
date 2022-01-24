<%@ taglib uri="httr:/java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>

All users

<c:forEach items="${users}" var="user">
<tr>
<td> ${user.id} </td>
<td> ${user.login} </td>
<td> ${user.email} </td>
<td> ${user.role} </td>
<td> ${user.status} </td>
</tr>
</c:forEach>

</body>
</html>