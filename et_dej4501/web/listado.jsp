<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado Participantes</title>
    </head>
    <body>
        <%@include file="WEB-INF/menu.jspf" %>
        <h1>Listado Participantes</h1>
        <form method="get" action="<c:url value="/ListarServlet"/>">
            <table>
                <tr>
                    <td>Seleccione Raza</td>
                    <td>:</td>
                    <td>
                        <select name="cboRaza">
                            <option value="0">Todos</option>
                            <c:forEach items="${lista}" var="lr">                                
                                <option value="${lr.id_raza}" ${param.cboRaza == lr.id_raza ? "SELECTED" : ""}>
                                    <c:out value="${lr.nombre_raza}"/>
                                </option>
                            </c:forEach>                            
                        </select>
                    </td>
                    <td>
                        <input type="submit" value="Buscar">
                    </td>
                </tr>
            </table>        
            <br><br>
        </form>
        <c:choose>
            <c:when test="${listap==null}">
                <%-- Salta el seleccione --%>
            </c:when>
            <c:when test="${empty listap}">
                <h3>No hay concursantes para mostrar</h3>
            </c:when>
            <c:otherwise>
                <c:if test="${listap!=null}">
                    <table border="1px" width="50%">
                        <tr>
                            <td>Nombre</td>
                            <td>Raza</td>
                            <td>ID Reg.</td>
                            <td>Fecha Reg.</td>
                            <td>Accion</td>               
                        </tr>
                        <c:forEach items="${listap}" var="lp">
                            <tr>
                                <td><c:out value="${lp.nombre_participante}"/></td>
                                <td><c:out value="${lp.raza.nombre_raza}"/></td>
                                <td><c:out value="${lp.id_resgistro}"/></td>
                                <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${lp.fecha_registro}"/></td>
                                <td>
                                    <c:url var="borrarParticipante" value="/BorrarServlet">
                                        <c:param name="codp" value="${lp.id_participante}"/> 
                                        <c:param name="razaid" value="${param.cboRaza}"/>
                                    </c:url>
                                    <a href="${borrarParticipante}">
                                        <button type="button">
                                            Eliminar
                                        </button>
                                    </a>
                                </td>
                            </tr>   
                        </c:forEach>
                    </table>
                </c:if>
            </c:otherwise>
        </c:choose><br><br>
        <c:out value="${mensaje}"/>
    </body>
</html>
