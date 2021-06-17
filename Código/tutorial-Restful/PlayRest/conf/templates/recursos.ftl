<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/recursos</title>
</head>
<body>

<p > <a href="/inicio">  Si quiere volver al inicio pinche aquí </a></p> <br>
<p > <a href="/laboratorios">  Si quiere volver a ver todos los laboratorios pinche aquí </a></p> <br>

<b>
<div> <b> La lista de bancos de trabajo es: </b><br> <br>
<#list recursos as recurso>

  <p id="identificador">El ID del recurso es ${recurso.id} </p> <br>
  <p id="url"><a href="${recurso.url}">La URI del recurso es ${recurso.url} </a></p> <br>
  <p id="nombre">EL nombre del recurso es ${recurso.nombreRecursoBanco} </p> <br>
  <p id="descripcion">La descripción del recurso es ${recurso.descripcionRecursoBanco} </p> <br>
  <p id="labID">El ID del laboratorio del recurso es ${recurso.labID} </p> <br>
  <p id="bancoID">El ID del banco de trabajo del recurso es ${recurso.bancoID} </p> <br>
<p >------------------------------------------------- </p> <br>
 </#list>
</div>

</body>
</html>