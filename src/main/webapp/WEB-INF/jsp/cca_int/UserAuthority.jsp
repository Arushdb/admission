<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	response.setContentType("text/xml");
%>
<CodeList>
     <autho><c:out value="${authority}"/></autho>
     <modi><c:out value="${modifier}"/></modi>
</CodeList>
