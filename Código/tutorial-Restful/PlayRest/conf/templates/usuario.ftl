<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/usuarios/${usuario.id} </title>
</head>

<script>
function makePUTRequest(url){


var xhr = new XMLHttpRequest();
xhr.open("PUT", url);

xhr.setRequestHeader("Accept", "application/json");
xhr.setRequestHeader("Content-Type", "application/json");

xhr.onreadystatechange = function () {
   if (xhr.readyState === 4) {
      console.log(xhr.status);
      console.log(xhr.responseText);
   }};
var form = document.querySelector("#formulario");
var data = `{
                    "nombre": "`+form.querySelector('input[name="nombre"]').value+`",
                    "grado": "`+form.querySelector('input[name="grado"]').value+`"
            }`;
console.log(data)
xhr.send(data);
}
</script>

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
<p >   Si quiere volver a ver <b>todos los usuarios</b> pinche <a href="/usuarios">aquí </a></p> <br>
<p >------------------------------------------------- </p> <br><br>
  <p id="identificador">El ID del usuario es ${usuario.id} </p> <br>
  <p id="url">La URI del usuario es ${usuario.url} </p> <br>
  <p id="nombre">EL nombre del usuario es ${usuario.nombre} </p> <br>
  <p id="grado">El grado al que pertenece el usuario  es ${usuario.grado} </p> <br> <br>

<div> <b> La lista de reservas asignadas al usuario ${usuario.nombre} es: </b><br> <br>
        <#list listaReservas as reserva>
           <p >El id de la reserva es ${reserva.id} </p> <br>
             <p> La URI de la reserva es <a href="${reserva.url}">${reserva.url} </a></p> <br>
             <p>La disponibilidad de la reserva del banco es ${reserva.disponibilidadReserva} </p> <br>
             <p >------------------------------------------------- </p> <br>
        <#else>
        <p>No tiene reservas asignadas<p>
        <p >------------------------------------------------- </p> <br>
        </#list>
        </div>

     <form action="#" onSubmit="makePUTRequest('${usuario.url}'); return false;" id="formulario" >
      <b> <p>Este formulario es para modificar la información del nombre y el grado de este usuario </p> </b>
       <div>
         <label for="laboratorio.nombre">Introduzca el nombre del usuario</label>
         <input name="nombre" id="nombreUsu" value="">
       </div>
       <div>
         <label for="laboratorio.descripcion">Introduzca el grado del usuario</label>
         <input name="grado" id="gradoUsu" value="">
       </div>

       <div>
         <button id="modificarLab">Modificar usuario</button>
       </div>
     </form>
     <p >------------------------------------------------- </p> <br>



     <form action="#" onSubmit="makeDELETERequest('${usuario.url}'); return false;" id="formularioDELETE" >
      <b> <p>Si quiere este usuario pulse el botón </p> </b>

       <div>
         <button id="borrarLab">Borrar usuario</button>
       </div>
     </form>
     <p >------------------------------------------------- </p> <br>

</body>
</html>