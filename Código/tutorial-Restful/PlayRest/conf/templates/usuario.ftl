<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/usuarios/${usuario.id} </title>
</head>
<body>

<b>
<p > <a href="/inicio">  Si quiere volver al inicio pinche aquí </a></p> <br>
<p > <a href="/usuarios">  Si quiere volver a ver todos los usuarios pinche aquí </a></p> <br>
  <p id="identificador">El ID del usuario es ${usuario.id} </p> <br>
  <p id="url">La URI del usuario es ${usuario.url} </p> <br>
  <p id="nombre">EL nombre del usuario es ${usuario.nombre} </p> <br>
  <p id="grado">El grado al que pertenece el usuario  es ${usuario.grado} </p> <br> <br>
</b>
<div> <b> La lista de reservas asignadas al usuario ${usuario.nombre} es: </b><br> <br>
        <#list listaReservas as reserva>
           <p >El id de la reserva es ${reserva.id} </p> <br>
             <p> <a href="${reserva.url}">La URI de la reserva es ${reserva.url} </a></p> <br>
             <p>La disponibilidad de la reserva del banco es ${reserva.disponibilidadReserva} </p> <br>
             <p >------------------------------------------------- </p> <br>
        <#else>
        <p>No tiene reservas asignadas<p>
        <p >------------------------------------------------- </p> <br>
        </#list>
        </div>

         <form action="http://localhost:9000/usuarios/${usuario.id}" method="PUT">
                <div>
                  <label for="usuario.nombre">Introduzca el nombre que quiere modificar del usuario</label>
                  <input name="nombre" id="nombreUsuario" value="">
                </div>
                <div>
                  <label for="usuario.grado">Introduzca el grado que quiere modificar del usuario</label>
                  <input name="grado" id="gradoUsuario" value="">
                </div>
                <div>
                  <button>Modificar usuario</button>
                </div>
              </form>
         <p >------------------------------------------------- </p> <br>
 <form action="http://localhost:9000/usuarios/${usuario.id}" method="DELETE">

                 <div>
                   <button>Borrar este usuario</button>
                 </div>
               </form>
          <p >------------------------------------------------- </p> <br>

</body>
</html>