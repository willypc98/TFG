<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/laboratorios/${banco.labID}/bancos/${banco.id}</title>
</head>
<body>


  <p id="identificador">El ID del banco es ${banco.id} </p> <br>
  <p id="url">La URI del banco es ${banco.url} </p> <br>
  <p id="descripcion">La descripción del banco es ${banco.descripcionBanco} </p> <br>
  <p id="labID"> <a href="/laboratorios/${banco.labID}">El ID del laboratorio del banco es ${banco.labID} </a></p> <br>

  <div> <b>La disponibilidad del banco de trabajo es: </b>  <br>
    <#list disponibilidadBanco as horario>
      <p> ${horario}
      <#else> NO tiene disponiblidad asignada
    </#list>
    </div> <br>

    <div> <b> La lista de recursos del banco de trabajo es: </b><br> <br>
        <#list listaRecursos as recurso>
           <p >El id del recurso del banco es ${recurso.id} </p> <br>
             <p> <a href="${recurso.url}">La URI del recurso del banco es ${recurso.url} </a></p> <br>
             <p>El nombre del recurso del  banco es ${recurso.nombreRecursoBanco} </p> <br>
             <p>La descripción del recurso del banco es ${recurso.descripcionRecursoBanco} </p> <br>
             <p ><a href="/laboratorios/${recurso.labID}">El ID del laboratorio del recurso del banco es ${recurso.labID} </a> </p> <br>
             <p ><a href="${recurso.bancoID}">El ID del banco del recurso es ${recurso.bancoID} </a></p> <br>
             <p >------------------------------------------------- </p> <br>
        <#else> No tiene recursos asignados
        </#list>
        </div>

</body>
</html>