<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/reservas </title>
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
var recurso= form.querySelector('input[name="recurso"]').value
console.log(recurso)
var arrayRecurso = recurso.split(',');
console.log(arrayRecurso);
var arrayBienRecurso= arrayRecurso.join('},{"id":');
console.log(arrayBienRecurso);

var data = `{
                        "usu": {"id": `+form.querySelector('input[name="usuario"]').value+`},
                    	"lab": {"id": `+form.querySelector('input[name="laboratorio"]').value+`},
                    	"ban": {"id": `+form.querySelector('input[name="banco"]').value+`},
                    	"disponibilidadReserva": "`+form.querySelector('input[name="disponibilidad"]').value+`",
                    	"listaRecursos": [
                    	{"id": `+arrayBienRecurso+`} ]
            }`;
console.log(data)
xhr.send(data);
}
</script>

<body>
<p > <a href="/inicio">  Si quiere volver al inicio pinche aquí </a></p> <br>
<p > <a href="/usuarios">  Si quiere ver todos los usuarios pinche aquí </a></p> <br>
<p > <a href="/laboratorios">  Si quiere ver todos los laboratorios pinche aquí </a></p> <br>
<div> <b> La lista de reservas es: </b><br> <br>
  <#list reservas as reserva>
 <p id="identificador">El ID de la reserva es ${reserva.id} </p> <br>
  <p id="url"><a href="${reserva.url}">La URI de la reserva es ${reserva.url} </a></p> <br>
  <p id="disponibilidad">La disponibilidad de la reserva es ${reserva.disponibilidadReserva} </p> <br>
<p >------------------------------------------------- </p> <br>
   </#list>
  </div>

<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/reservas'); return false;" id="formulario" >
  <b> <p>Este formulario es para crear una reserva </p> </b>
  <div>
    <label for="reserva.usu">Introduzca el ID del usuario</label>
    <input name="usuario" id="usuarioID" value="">
  </div>
  <div>
      <label for="reserva.lab">Introduzca el ID del laboratorio</label>
      <input name="laboratorio" id="laboratorioID" value="">
    </div>
   <div>
       <label for="reserva.ban">Introduzca el ID del banco</label>
       <input name="banco" id="bancoID" value="">
     </div>
   <div>
       <label for="reserva.recurso">Introduzca el ID de los recursos separados por comas</label>
       <input name="recurso" id="recursoID" value="">
     </div>
  <div>
      <label for="laboratorio.descripcion">Introduzca el horario de la reserva  </label>
      <input name="disponibilidad" id="disponibilidadReserva" value="2021-05-01T09:30:00">
    </div>
  <div>
    <button id="creacionReserva">Crear reserva</button>
  </div>
</form>

<p >-------------------------------------------------------------------------------</p> <br>

</body>
</html>