<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/laboratorios/${banco.labID}/bancos/${banco.id}</title>
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
                    "descripcionBanco": "`+form.querySelector('input[name="descripcion"]').value+`"
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


<p > <a href="/inicio">  Si quiere volver al inicio pinche aquí </a></p> <br>
<p > <a href="/laboratorios/${banco.labID}/bancos">  Si quiere volver a ver todos los bancos de trabajo  pinche aquí </a></p> <br>
  <p > <a href="/laboratorios/${banco.labID}/bancos/${banco.id}/recursos">  Si quiere ver todos los recursos de este banco de trabajo pinche aquí </a></p> <br>
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

<form action="#" onSubmit="makePUTRequest('${banco.url}'); return false;" id="formulario" >
 <b> <p>Este formulario es para modificar la información de la descripción de este banco de trabajo </p> </b>

  <div>
    <label for="laboratorio.descripcion">Introduzca la descripción del banco</label>
    <input name="descripcion" id="descripcionBanco" value="">
  </div>

  <div>
    <button id="modificarBanco">Modificar banco</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

<form action="#" onSubmit="makePATCHRequest('${banco.url}'); return false;" id="formularioPATCH" >
 <b> <p>Este formulario es para modificar la disponibilidad de este banco de trabajo </p> </b>
  <div>
    <label for="banco.nombre">Introduzca ADD si quiere añadir o REMOVE si quiere eliminar una franja de disponibilidad</label>
    <input name="tipo" id="Tipo" value="">
  </div>
  <div>
    <label for="banco.descripcion">Introduzca la hora que quiera modificar</label>
    <input name="franja" id="Franja" value="2021-05-01T09:30:00">
  </div>

  <div>
    <button id="modificarDisponibilidad">Modificar disponiblidad</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

<form action="#" onSubmit="makeDELETERequest('${banco.url}'); return false;" id="formularioDELETE" >
 <b> <p>Si quiere borrar este banco pulse el botón </p> </b>

  <div>
    <button id="borrarBanco">Borrar banco</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

</body>
</html>