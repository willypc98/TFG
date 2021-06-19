<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/laboratorios/${labID}/bancos/</title>
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
                    "descripcionBanco": "`+form.querySelector('input[name="descripcion"]').value+`",
                    "listaDisponibilidadBanco": ["`+arrayBienDisp+`"]
            }`;
console.log(data)
xhr.send(data);
}
</script>

<body>

<p >   Si quiere volver al <b>inicio</b> pinche <a href="/inicio">aquí </a></p> <br>
<p >   Si quiere volver a ver <b>todos los laboratorios</b> pinche <a href="/laboratorios">aquí </a></p> <br>
 <p >------------------------------------------------- </p><br> <br>
<div> <b> La lista de bancos de trabajo es: </b><br> <br>
<#list bancos as banco>
  <p id="identificador">El ID del banco es ${banco.id} </p> <br>
  <p id="url"> La URI del banco es <a href="${banco.url}">${banco.url} </a></p> <br>
  <p id="descripcion">La descripción del banco es ${banco.descripcionBanco} </p> <br>
  <p id="labID"> El ID del laboratorio del banco es <a href="/laboratorios/${banco.labID}">${banco.labID} </a></p> <br>
 <p >------------------------------------------------- </p> <br>
 </#list>
</div>


<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/laboratorios/${labID}/bancos'); return false;" id="formulario" >

  <div>
    <label for="banco.descripcion">Introduzca la descripción del banco</label>
    <input name="descripcion" id="descripcionBanco" value="">
  </div>
  <div>
      <label for="banco.disponibilidad">Introduzca la disponibilidad del banco separada por comas</label>
      <input name="disponibilidad" id="disponibilidadBanco" value="2021-05-01T09:30:00">
    </div>
  <div>
    <button id="creacionBanco">Crear banco</button>
  </div>
</form>

<p >-------------------------------------------------------------------------------</p> <br>


</body>
</html>