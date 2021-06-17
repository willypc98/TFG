<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/laboratorios/${recurso.labID}/bancos/${recurso.bancoID}/recursos/${recurso.id}</title>
</head>
<body>

<p > <a href="/inicio">  Si quiere volver al inicio pinche aquí </a></p> <br>
<p > <a href="/laboratorios">  Si quiere volver a ver todos los laboratorios pinche aquí </a></p> <br>
<p > <a href="/laboratorios/${recurso.labID}/bancos">  Si quiere volver a ver todos los bancos de trabajo de este laboratorio pinche aquí </a></p> <br>
<p > <a href="/laboratorios/${recurso.labID}/bancos/${recurso.bancoID}/recursos">  Si quiere volver a ver todos los recursos del banco de trabajo de este laboratorio pinche aquí </a></p> <br>
<b>
  <p id="identificador">El ID del recurso es ${recurso.id} </p> <br>
  <p id="url">La URI del recurso es ${recurso.url} </p> <br>
  <p id="nombre">EL nombre del recurso es ${recurso.nombreRecursoBanco} </p> <br>
  <p id="descripcion">La descripción del recurso es ${recurso.descripcionRecursoBanco} </p> <br>
  <p id="labID">El ID del laboratorio del recurso es ${recurso.labID} </p> <br>
  <p id="bancoID">El ID del banco de trabajo del recurso es ${recurso.bancoID} </p> <br>
</b>
<div> <b>La disponibilidad del recurso del banco de trabajo es: </b>  <br>
    <#list listaDisponibilidadRecursos as horario>
      <p> ${horario}
      <#else> NO tiene disponiblidad asignada
    </#list>
    </div> <br>
</body>
</html>