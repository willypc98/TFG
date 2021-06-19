<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/usuarios </title>
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
var data = `{
                    "nombre": "`+form.querySelector('input[name="nombre"]').value+`",
                    "grado": "`+form.querySelector('input[name="grado"]').value+`"
            }`;
console.log(data)
xhr.send(data);
}
</script>
<body>


<p >  Si quiere volver al inicio pinche <a href="/inicio"> aquí </a></p> <br>
<p >   Si quiere ver todas las reservas pinche <a href="/reservas">aquí </a></p> <br>
<p >  Si quiere ver todos los laboratorios pinche <a href="/laboratorios">aquí </a></p> <br>
 <p >------------------------------------------------- </p> <br>

  <div> <b> La lista de usuarios es: </b><br> <br>
  <#list usuarios as usuario>
  <p id="identificador">El ID del usuario es ${usuario.id} </p> <br>
  <p id="url"> La URI del usuario es <a href="${usuario.url}">${usuario.url} </a> </p> <br>
  <p id="nombre">EL nombre del usuario es ${usuario.nombre} </p> <br>
  <p id="grado">El grado al que pertenece el usuario  es ${usuario.grado} </p> <br>
  <p >------------------------------------------------- </p> <br>
   </#list>
  </div>

<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/usuarios'); return false;" id="formulario" >
  <div>
    <label for="usuario.nombre">Introduzca el nombre del usuario</label>
    <input name="nombre" id="nombreUsuario" value="">
  </div>
  <div>
    <label for="usuario.grado">Introduzca el grado del usuario</label>
    <input name="grado" id="gradoUsuario" value="">
  </div>
  <div>
    <button id="creacion">Crear usuario</button>
  </div>
</form>



</body>

</html>