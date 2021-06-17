<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/reservas </title>
</head>
<body>
<p > <a href="/inicio">  Si quiere volver al inicio pinche aquí </a></p> <br>
<p > <a href="/usuarios">  Si quiere ver todos los usuarios pinche aquí </a></p> <br>
<p > <a href="/laboratorios">  Si quiere ver todos los laboratorios pinche aquí </a></p> <br>
<div> <b> La lista de reservas es: </b><br> <br>
  <#list reservas as reserva>
 <p id="identificador">El ID de la reserva es ${reserva.id} </p> <br>
  <p id="url"><a href="${reserva.url}">La URI de la reserva es ${reserva.url} </a></p> <br>
  <p id="disponibilidad">La disponibilidad de la reserva es ${reserva.disponibilidadReserva} </p> <br>
<p >------------------------------------------------- </p> <br>
   </#list>
  </div>


</body>
</html>