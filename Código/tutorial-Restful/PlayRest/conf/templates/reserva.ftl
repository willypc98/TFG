<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/reservas/${reserva.id} </title>
</head>
<body>


  <p id="identificador">El ID de la reserva es ${reserva.id} </p> <br>
  <p id="url">La URI de la reserva es ${reserva.url} </p> <br>
  <p id="disponibilidad">La disponibilidad de la reserva es ${reserva.disponibilidadReserva} </p> <br>

  <p id="usuario">El ID del usuario de la reserva es ${usuario.id} </p> <br>
  <p id="laboratorio">El ID del laboratorio de la reserva es ${laboratorio.id} </p> <br>
  <p id="usuario">El ID del banco de trabajo de la reserva es ${banco.id} </p> <br>

  <div> <b> La lista de recursos asignados a la reserva es: </b><br> <br>
          <#list listaRecursos as recurso>
             <p >El id del recurso de la reserva es ${recurso.id} </p> <br>
               <p >------------------------------------------------- </p> <br>
          <#else>
          <p>No tiene recursos asignados<p>
          </#list>
          </div>


</body>
</html>