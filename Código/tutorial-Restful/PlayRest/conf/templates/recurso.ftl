<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/laboratorios/${recurso.labID}/bancos/${recurso.bancoID}/recursos/${recurso.id}</title>
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
                    "nombreRecursoBanco": "`+form.querySelector('input[name="nombre"]').value+`",
                    "descripcionRecursoBanco": "`+form.querySelector('input[name="descripcion"]').value+`"
            }`;
console.log(data)
xhr.send(data);
}
</script>

<script>
function makePATCHRequest(url){


var xhr = new XMLHttpRequest();
xhr.open("PATCH", url);

xhr.setRequestHeader("Accept", "application/json");
xhr.setRequestHeader("Content-Type", "application/json");

xhr.onreadystatechange = function () {
   if (xhr.readyState === 4) {
      console.log(xhr.status);
      console.log(xhr.responseText);
   }};
var form = document.querySelector("#formularioPATCH");
var data = `{
                    "type": "`+form.querySelector('input[name="tipo"]').value+`",
                    "franja": "`+form.querySelector('input[name="franja"]').value+`"
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
<p >   Si quiere volver a ver <b>todos los laboratorios</b> pinche <a href="/laboratorios">aquí </a></p> <br>
<p >   Si quiere volver a ver <b>todos los bancos de trabajo</b> de este laboratorio pinche <a href="/laboratorios/${recurso.labID}/bancos">aquí </a></p> <br>
<p >   Si quiere volver a ver <b>todos los recursos</b> del banco de trabajo de este laboratorio pinche <a href="/laboratorios/${recurso.labID}/bancos/${recurso.bancoID}/recursos">aquí </a></p>
<p >------------------------------------------------- </p> <br>
  <p id="identificador">El ID del recurso es ${recurso.id} </p> <br>
  <p id="url">La URI del recurso es ${recurso.url} </p> <br>
  <p id="nombre">EL nombre del recurso es ${recurso.nombreRecursoBanco} </p> <br>
  <p id="descripcion">La descripción del recurso es ${recurso.descripcionRecursoBanco} </p> <br>
  <p id="labID">El ID del laboratorio del recurso es ${recurso.labID} </p> <br>
  <p id="bancoID">El ID del banco de trabajo del recurso es ${recurso.bancoID} </p> <br>

<div> <b>La disponibilidad del recurso del banco de trabajo es: </b>  <br>
    <#list listaDisponibilidadRecursos as horario>
      <p> ${horario}
      <#else> NO tiene disponiblidad asignada
    </#list>
    </div> <br>


<form action="#" onSubmit="makePUTRequest('${recurso.url}'); return false;" id="formulario" >
 <b> <p>Este formulario es para modificar la información del nombre y de la descripción de este recurso </p> </b>
<div>
    <label for="recurso.nombre">Introduzca el nombre del recurso</label>
    <input name="nombre" id="nombreRecurso" value="">
  </div>
  <div>
    <label for="recurso.descripcion">Introduzca la descripción del recurso</label>
    <input name="descripcion" id="descripcionRecurso" value="">
  </div>

  <div>
    <button id="modificarRecurso">Modificar recurso</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

<form action="#" onSubmit="makePATCHRequest('${recurso.url}'); return false;" id="formularioPATCH" >
 <b> <p>Este formulario es para modificar la disponibilidad de este recurso </p> </b>
  <div>
    <label for="recurso.nombre">Introduzca ADD si quiere añadir o REMOVE si quiere eliminar una franja de disponibilidad</label>
    <input name="tipo" id="Tipo" value="">
  </div>
  <div>
    <label for="recurso.descripcion">Introduzca la hora que quiera modificar</label>
    <input name="franja" id="Franja" value="2021-05-01T09:30:00">
  </div>

  <div>
    <button id="modificarDisponibilidad">Modificar disponiblidad</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

<form action="#" onSubmit="makeDELETERequest('${recurso.url}'); return false;" id="formularioDELETE" >
 <b> <p>Si quiere borrar este recurso pulse el botón </p> </b>

  <div>
    <button id="borrarRecurso">Borrar recurso</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>


</body>
</html>