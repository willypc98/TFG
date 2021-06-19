<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/laboratorios/${labID}/bancos/${bancoID}/recursos</title>
</head>
<script>
function makePOSTRequest(url){


var xhr = new XMLHttpRequest();
xhr.open("POST", url);

xhr.setRequestHeader("Accept", "application/json");
xhr.setRequestHeader("Content-Type", "application/json");

xhr.onreadystatechange = function () {
   if (xhr.readyState === 4) {
      console.log(xhr.status);
      console.log(xhr.responseText);
   }};
var form = document.querySelector("#formulario");
var disponibilidad= form.querySelector('input[name="disponibilidad"]').value
console.log(disponibilidad)
var arrayDisp = disponibilidad.split(',');
var arrayBienDisp= arrayDisp.join('","');
console.log(arrayDisp);

var data = `{
                    "nombreRecursoBanco": "`+form.querySelector('input[name="nombre"]').value+`",
                    "descripcionRecursoBanco": "`+form.querySelector('input[name="descripcion"]').value+`",
                    "listaDisponibilidadRecursos": ["`+arrayBienDisp+`"]
            }`;
console.log(data)
xhr.send(data);
}
</script>

<body>

<p > <a href="/inicio">  Si quiere volver al inicio pinche aquí </a></p> <br>
<p > <a href="/laboratorios">  Si quiere volver a ver todos los laboratorios pinche aquí </a></p> <br>

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


<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/laboratorios/${labID}/bancos/${bancoID}/recursos'); return false;" id="formulario" >

<div>
    <label for="recurso.nombre">Introduzca el nombre del recurso</label>
    <input name="nombre" id="nombreRecurso" value="">
  </div>
  <div>
    <label for="recurso.descripcion">Introduzca la descripción del recurso</label>
    <input name="descripcion" id="descripcionRecurso" value="">
  </div>
  <div>
      <label for="recurso.disponibilidad">Introduzca la disponibilidad del recurso separada por comas</label>
      <input name="disponibilidad" id="disponibilidadRecurso" value="2021-05-01T09:30:00">
    </div>
  <div>
    <button id="creacionRecurso">Crear recurso</button>
  </div>
</form>

<p >-------------------------------------------------------------------------------</p> <br>



</body>
</html>