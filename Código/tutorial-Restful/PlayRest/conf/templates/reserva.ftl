<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/reservas/${reserva.id} </title>
</head>
<script>
function makeDELETERequest(url){


var xhr = new XMLHttpRequest();
xhr.open("DELETE", url);

xhr.setRequestHeader("Accept", "application/json");
xhr.setRequestHeader("Content-Type", "application/json");

xhr.onreadystatechange = function () {
   if (xhr.readyState === 4) {
      console.log(xhr.status);
      console.log(xhr.responseText);
   }};
var form = document.querySelector("#formularioDELETE");
var data = `{

            }`;
console.log(data)
xhr.send(data);
}
</script>

<body>
<p >   Si quiere volver al <b>inicio</b> pinche <a href="/inicio">aquí </a></p> <br>
<p >  Si quiere volver a ver <b>todas las reservas</b> pinche <a href="/reservas"> aquí </a></p> <br>
<p >------------------------------------------------- </p> <br><br>

  <p id="identificador">El ID de la reserva es ${reserva.id} </p> <br>
  <p id="url">La URI de la reserva es ${reserva.url} </p> <br>
  <p id="disponibilidad">La disponibilidad de la reserva es ${reserva.disponibilidadReserva} </p> <br>

  <p id="usuario">El ID del usuario de la reserva es ${usuario.id} </p> <br>
  <p id="usuarioURL">  La URI del usuario de la reserva es <a href="${usuario.url}">${usuario.url} </a></p> <br>
  <p id="laboratorio">El ID del laboratorio de la reserva es ${laboratorio.id} </p> <br>
  <p id="laboratorioURL">La URI del laboratorio de la reserva es <a href="${laboratorio.url}">${laboratorio.url} </a></p> <br>
  <p id="banco">El ID del banco de trabajo de la reserva es ${banco.id} </p> <br>
  <p id="bancoURL">La URI del banco de trabajo de la reserva es <a href="${banco.url}">${banco.url} </a></p> <br> <br>

  <div> <b> La lista de recursos asignados a la reserva es: </b><br> <br>
          <#list listaRecursos as recurso>
             <p >El id del recurso de la reserva es ${recurso.id} </p> <br>
             <p >La URI del recurso de la reserva es <a href="${recurso.url}">${recurso.url} </a></p> <br>
               <p >------------------------------------------------- </p> <br>
          <#else>
          <p>No tiene recursos asignados<p>
          </#list>
          </div>

<form action="#" onSubmit="makeDELETERequest('${reserva.url}'); return false;" id="formularioDELETE" >
 <b> <p>Si quiere borrar esta reserva pulse el botón </p> </b>

  <div>
    <button id="borrarReserva">Borrar reserva</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

</body>
</html>