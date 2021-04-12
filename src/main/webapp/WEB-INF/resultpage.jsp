<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Resultatside for beregning af BMI
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${true}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <div>
            <h2>BMI result</h2>

            <p>Din højde er ${requestScope.height} cm<br/>
                Din vægt er ${requestScope.weight} kg</p>
            <p>
                Dit BMI er beregnet til ${requestScope.bmi}
            </p>
            <p>
                Du er ${requestScope.category}
            </p>

        </div>

    </jsp:body>
</t:genericpage>