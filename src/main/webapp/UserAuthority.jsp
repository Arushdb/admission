<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	response.setContentType("text/html");
%>

<link rel="stylesheet" type="text/css" href="../css/mystyleMenu.css">

<div id="menu" style="display:none;">
  <ul>
          <li id="MA"  style="display:none;"><a href "#">Reports</a>
               <ul>
         
                   <li id="MAA" style="display:none;" ><a href "#">Generate Interview List</a> </li>
                   <li id ="MAB" style="display:none;" ><a href "#">Marks Reports</a> 
                        <ul>
                                <li id="MABA" style="display:none;" ><a href "#">CCA Marks List</a> </li>
                                <li id="MABB" style="display:none;" ><a href "#">Interview Marks List</a> </li>
                                <li id="MABC" style="display:none;"  onclick="location.href='checkonClick.htm?AppNumber=aap&FLAG=flag&USER=user';"><a>Both Marks List</a> </li>
                        </ul>
                   
                   </li>
                  
               </ul>
         
          </li>
          
          <li id ="MB" style="display:none;" ><a href "#">Result Related</a></li>
          
          <li id ="MC" style="display:none;"><a>Marks Entry Panels</a>
          
                <ul>
                <li id="MCA" style="display:none;" onclick="location.href='checkonClick.htm?FLAG=cca&AUTHO=MCA';" ><a>Enter CCA Marks</a></li>
                <li id="MCB" style="display:none;" onclick="location.href='checkonClick.htm?FLAG=int&AUTHO=MCB';" ><a>Enter Interview Marks</a></li>
                <li id="MCC" style="display:none;" onclick="location.href='checkonClick.htm?FLAG=both&AUTHO=MCC';" ><a>Enter Both Marks</a></li>
                <li id="MCD" style="display:none;" onclick="location.href='checkonClick.htm?FLAG=adm';" ><a>Administrative Panel</a></li>
                </ul>
          </li>
          <li onclick="location.href='logout.htm;'"><a href="#">logout</a></li>
     </ul>
     
</div>
<c:forEach var="authority" items="${sessionScope.authority}">


<c:choose>
<c:when test="${authority.authority=='MA' && authority.modifier!='InValid' }">
<script>
 document.getElementById("menu").style.display="block";
 document.getElementById("MA").style.display="block";
 </script>
</c:when>
<c:when test="${authority.authority=='MAA' && authority.modifier!='InValid' }">
<script>
 document.getElementById("menu").style.display="block";
 document.getElementById("MA").style.display="block";
 document.getElementById("MAA").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MAB' && authority.modifier!='InValid' }">
<script>
 document.getElementById("menu").style.display="block";
 document.getElementById("MA").style.display="block";
 document.getElementById("MAB").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MAC' && authority.modifier!='InValid' }">
<script>
 document.getElementById("menu").style.display="block";
 document.getElementById("MA").style.display="block";
 document.getElementById("MAC").style.display="block";
 </script>
</c:when>


<c:when test="${authority.authority=='MAC' && authority.modifier!='InValid' }">
<script>
 document.getElementById("menu").style.display="block";
 document.getElementById("MA").style.display="block";
 document.getElementById("MAC").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MABA' && authority.modifier!='InValid' }">
<script>
 document.getElementById("menu").style.display="block";
 document.getElementById("MA").style.display="block";
 document.getElementById("MAB").style.display="block"; 
 document.getElementById("MABA").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MABB' && authority.modifier!='InValid' }">
<script>
 document.getElementById("menu").style.display="block";
 document.getElementById("MA").style.display="block";
 document.getElementById("MAB").style.display="block"; 
 document.getElementById("MABB").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MABC' && authority.modifier!='InValid' }">
<script>
 document.getElementById("menu").style.display="block";
 document.getElementById("MA").style.display="block";
 document.getElementById("MAB").style.display="block"; 
 document.getElementById("MABC").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MB' && authority.modifier!='InValid' }">
<script>
 document.getElementById("menu").style.display="block";
 document.getElementById("MB").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MB' && authority.modifier!='InValid' }">
<script>
 document.getElementById("menu").style.display="block";
 document.getElementById("MB").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MBA' && authority.modifier!='InValid' }">
<script>
  document.getElementById("menu").style.display="block";
  document.getElementById("MB").style.display="block";
  document.getElementById("MBA").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MBB' && authority.modifier!='InValid' }">
<script>
  document.getElementById("menu").style.display="block";
  document.getElementById("MB").style.display="block";
  document.getElementById("MBB").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MBC' && authority.modifier!='InValid' }">
<script>
  document.getElementById("menu").style.display="block";
  document.getElementById("MB").style.display="block";
  document.getElementById("MBC").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MBCA' && authority.modifier!='InValid' }">
<script>
  document.getElementById("menu").style.display="block";
  document.getElementById("MB").style.display="block";
  document.getElementById("MBC").style.display="block";
  document.getElementById("MBCA").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MBCB' && authority.modifier!='InValid' }">
<script>
  document.getElementById("menu").style.display="block";
  document.getElementById("MB").style.display="block";
  document.getElementById("MBC").style.display="block";
  document.getElementById("MBCB").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MBCC' && authority.modifier!='InValid' }">
<script>
  document.getElementById("menu").style.display="block";
  document.getElementById("MB").style.display="block";
  document.getElementById("MBC").style.display="block";
  document.getElementById("MBCC").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MC' && authority.modifier!='InValid' }">
<script>
  document.getElementById("menu").style.display="block";
  document.getElementById("MC").style.display="block";
 </script>
</c:when>


<c:when test="${authority.authority=='MCA' && authority.modifier!='InValid' }">
<script>
  document.getElementById("menu").style.display="block";
  document.getElementById("MC").style.display="block";
  document.getElementById("MCA").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MCB' && authority.modifier!='InValid' }">
<script>
  document.getElementById("menu").style.display="block";
  document.getElementById("MC").style.display="block";
  document.getElementById("MCB").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MCC' && authority.modifier!='InValid' }">
<script>
  document.getElementById("menu").style.display="block";
  document.getElementById("MC").style.display="block";
  document.getElementById("MCC").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MCD' && authority.modifier!='InValid' }">
<script>
  document.getElementById("menu").style.display="block";
  document.getElementById("MC").style.display="block";
  document.getElementById("MCD").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MCCA' && authority.modifier!='InValid' }">
<script>
  document.getElementById("menu").style.display="block";
  document.getElementById("MC").style.display="block";
  document.getElementById("MCC").style.display="block";
  document.getElementById("MCCA").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MCCB' && authority.modifier!='InValid' }">
<script>
  document.getElementById("menu").style.display="block";
  document.getElementById("MC").style.display="block";
  document.getElementById("MCC").style.display="block";
  document.getElementById("MCCB").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MCCC' && authority.modifier!='InValid' }">
<script>
  document.getElementById("menu").style.display="block";
  document.getElementById("MC").style.display="block";
  document.getElementById("MCC").style.display="block";
  document.getElementById("MCCC").style.display="block";
 </script>
</c:when>

<c:when test="${authority.authority=='MCCD' && authority.modifier!='InValid' }">
<script>
  document.getElementById("menu").style.display="block";
  document.getElementById("MC").style.display="block";
  document.getElementById("MCC").style.display="block";
  document.getElementById("MCCD").style.display="block";
 </script>
</c:when>

<c:otherwise></c:otherwise>
</c:choose>
</c:forEach>


