<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/Laboratorios/${laboratorio.id}</title>
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
                    "nombreLab": "`+form.querySelector('input[name="nombre"]').value+`",
                    "descripcionLab": "`+form.querySelector('input[name="descripcion"]').value+`"
            }`;
console.log(data)
xhr.send(data);
}
</script>

<body>


<p > <a href="/inicio">  Si quiere volver al inicio pinche aquí </a></p> <br>
  <p > <a href="/laboratorios">  Si quiere ver todos los laboratorios pinche aquí </a></p> <br>
  <p > <a href="/laboratorios/${laboratorio.id}/bancos">  Si quiere ver todos los bancos de este laboratorio pinche aquí </a></p> <br>
  <p id="identificador" >  El ID del laboratorio es ${laboratorio.id} </p> <br>
  <p id="url">La URI del laboratorio es ${laboratorio.url} </p> <br>
  <p id="nombre">EL nombre del laboratorio es ${laboratorio.nombreLab} </p> <br>
  <p id="descripcion">La descripción del laboratorio es ${laboratorio.descripcionLab} </p> <br>

<div> <b>La disponibilidad del laboratorio es: </b>  <br>
  <#list listaDisponibilidadLaboratorio as horario>
    <p> ${horario}
    <#else> NO tiene disponiblidad asignada
  </#list>
  </div> <br>

  <div> <b> La lista de bancos de trabajo del laboratorio es: </b><br> <br>
    <#list listaBancosDeTrabajo as banco>
       <p >El id del banco es ${banco.id} </p> <br>
         <p> La URI del banco es <a href="${banco.url}">${banco.url} </a></p> <br>
         <p>La descripción del banco es ${banco.descripcionBanco} </p> <br>
         <p >El ID del laboratorio del banco es ${banco.labID} </p> <br>
         <p >------------------------------------------------- </p> <br>
    <#else> No tiene bancos de trabajo asignados
    </#list>
    </div>

<form action="#" onSubmit="makePUTRequest('${laboratorio.url}'); return false;" id="formulario" >
 <b> <p>Este formulario es para modificar la información del nombre y la descripción de este laboratorio </p> </b>
  <div>
    <label for="laboratorio.nombre">Introduzca el nombre del laboratorio</label>
    <input name="nombre" id="nombreLab" value="">
  </div>
  <div>
    <label for="laboratorio.descripcion">Introduzca la descripción del laboratorio</label>
    <input name="descripcion" id="descripcionLab" value="">
  </div>

  <div>
    <button id="modificarLab">Modificar laboratorio</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

</body>
</html>