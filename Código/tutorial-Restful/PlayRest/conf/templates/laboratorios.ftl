<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/Laboratorios</title>
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
                    "nombreLab": "`+form.querySelector('input[name="nombre"]').value+`",
                    "descripcionLab": "`+form.querySelector('input[name="descripcion"]').value+`",
                    "listaDisponibilidadLaboratorio": ["`+arrayBienDisp+`"]
            }`;
console.log(data)
xhr.send(data);
}
</script>

<body>
<p > <a href="/inicio">  Si quiere volver al inicio pinche aquí </a></p> <br>
<div> <b> La lista de laboratorios es: </b><br> <br>
    <#list laboratorios as laboratorio>
        <p id="identificador" >  El ID del laboratorio es ${laboratorio.id} </p> <br>
         <p id="url"> <a href="${laboratorio.url}">La URI del laboratorio es ${laboratorio.url} </a></p> <br>
         <p id="nombre">EL nombre del laboratorio es ${laboratorio.nombreLab} </p> <br>
         <p id="descripcion">La descripción del laboratorio es ${laboratorio.descripcionLab} </p> <br>
         <p >------------------------------------------------- </p> <br>
    </#list>
    </div>

<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/laboratorios'); return false;" id="formulario" >
  <div>
    <label for="laboratorio.nombre">Introduzca el nombre del laboratorio</label>
    <input name="nombre" id="nombreLab" value="">
  </div>
  <div>
    <label for="laboratorio.descripcion">Introduzca la descripción del laboratorio</label>
    <input name="descripcion" id="descripcionLab" value="">
  </div>
  <div>
      <label for="laboratorio.descripcion">Introduzca la disponibilidad del laboratorio separada por comas</label>
      <input name="disponibilidad" id="disponibilidadLab" value="2021-05-01T09:30:00">
    </div>
  <div>
    <button id="creacionLab">Crear laboratorio</button>
  </div>
</form>

<p >-------------------------------------------------------------------------------</p> <br>




</body>
</html>