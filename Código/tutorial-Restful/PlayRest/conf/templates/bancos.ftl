<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bancos/</title>
</head>
<body>

<p > <a href="/inicio">  Si quiere volver al inicio pinche aquí </a></p> <br>
<p > <a href="/laboratorios">  Si quiere volver a ver todos los laboratorios pinche aquí </a></p> <br>

<div> <b> La lista de bancos de trabajo es: </b><br> <br>
<#list bancos as banco>
  <p id="identificador">El ID del banco es ${banco.id} </p> <br>
  <p id="url"> <a href="${banco.url}">La URI del banco es ${banco.url} </a></p> <br>
  <p id="descripcion">La descripción del banco es ${banco.descripcionBanco} </p> <br>
  <p id="labID"> <a href="/laboratorios/${banco.labID}">El ID del laboratorio del banco es ${banco.labID} </a></p> <br>
 <p >------------------------------------------------- </p> <br>
 </#list>
</div>

</body>
</html>