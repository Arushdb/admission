<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	response.setContentType("text/html");
%>

<c:forEach var="list" items="${list}">
<item>

<table border="1" id="tabBOTH" style="display:block;"  cellpadding="5" cellspacing="10">
<tr>
<th> ApplicationNumber</th> <th> Name</th>  <th>Father's Name </th> <th>Gender</th> <th>Category Code </th><th> CCA_Marks</th> <th> INT_Marks</th> 

</tr>
<tr>
<td> ${list.application_number}</td> <td>${list.candidateName}</td>  <td>${list.fatherName}</td> <td>${list.gender}</td> <td>${list.category} </td> <td> Contact to Administrator</td> <td> Contact to Administrator</td> 
</tr>
</table>
 
 </item>
</c:forEach>
