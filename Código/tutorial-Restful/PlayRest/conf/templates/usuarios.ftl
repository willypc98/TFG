<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/usuarios </title>
</head>
<body>

<b>
<p > <a href="/inicio">  Si quiere volver al inicio pinche aquí </a></p> <br>
<p > <a href="/reservas">  Si quiere ver todas las reservas pinche aquí </a></p> <br>
<p > <a href="/laboratorios">  Si quiere ver todos los laboratorios pinche aquí </a></p> <br>
  </b>

  <div> <b> La lista de usuarios es: </b><br> <br>
  <#list usuarios as usuario>
  <p id="identificador">El ID del usuario es ${usuario.id} </p> <br>
  <p id="url"> <a href="${usuario.url}">La URI del usuario es ${usuario.url} </a> </p> <br>
  <p id="nombre">EL nombre del usuario es ${usuario.nombre} </p> <br>
  <p id="grado">El grado al que pertenece el usuario  es ${usuario.grado} </p> <br>
  <p >------------------------------------------------- </p> <br>
   </#list>
  </div>

</body>
</html>