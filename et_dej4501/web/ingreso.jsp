<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingreso Concursante</title>
    </head>
    <body>
        <%@include file="WEB-INF/menu.jspf" %>
        <h1>Ingreso Participante</h1>
        <form action="<c:url value="/AgregarServlet"/>" method="post">
            <table>
                <tr>
                    <td>Nombre</td>
                    <td>:</td>
                    <td>
                        <input type="text" required="true" maxlength="50" placeholder="Ingrese Nombre"/>
                    </td>
                </tr>
                <tr>
                    <td>Raza</td>
                    <td>:</td>
                    <td>
                        <select name="cboRaza">
                            <option value="">Seleccione</option>
                            <c:forEach items="${lista}" var="lr">
                                <option value="${lr.id_raza}"><c:out value="${lr.nombre_raza}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>ID Reg.</td>
                    <td>:</td>
                    <td>
                        <input type="number" required="true" pattern="[0-9]" 
                               placeholder="Ingrese ID Registro"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <br>
                        <input type="submit" value="Registrar"/>
                    </td>                    
                </tr>
            </table>
        </form>
    </body>
</html>
