<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/Laboratorios/${laboratorio.id}</title>
</head>
<body>

<b>
<p > <a href="/inicio">  Si quiere volver al inicio pinche aquí </a></p> <br>
  <p > <a href="/laboratorios">  Si quiere ver todos los laboratorios pinche aquí </a></p> <br>
  <p > <a href="/laboratorios/${laboratorio.id}/bancos">  Si quiere ver todos los bancos de este laboratorio pinche aquí </a></p> <br>
  <p id="identificador" >  El ID del laboratorio es ${laboratorio.id} </p> <br>
  <p id="url">La URI del laboratorio es ${laboratorio.url} </p> <br>
  <p id="nombre">EL nombre del laboratorio es ${laboratorio.nombreLab} </p> <br>
  <p id="descripcion">La descripción del laboratorio es ${laboratorio.descripcionLab} </p> <br>
</b>
<div> <b>La disponibilidad del laboratorio es: </b>  <br>
  <#list listaDisponibilidadLaboratorio as horario>
    <p> ${horario}
    <#else> NO tiene disponiblidad asignada
  </#list>
  </div> <br>

  <div> <b> La lista de bancos de trabajo del laboratorio es: </b><br> <br>
    <#list listaBancosDeTrabajo as banco>
       <p >El id del banco es ${banco.id} </p> <br>
         <p> <a href="${banco.url}">La URI del banco es ${banco.url} </a></p> <br>
         <p>La descripción del banco es ${banco.descripcionBanco} </p> <br>
         <p >El ID del laboratorio del banco es ${banco.labID} </p> <br>
         <p >------------------------------------------------- </p> <br>
    <#else> No tiene bancos de trabajo asignados
    </#list>
    </div>

</body>
</html>