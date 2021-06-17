<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/Laboratorios</title>
</head>
<body>

<div> <b> La lista de laboratorios es: </b><br> <br>
    <#list laboratorios as laboratorio>
        <p id="identificador" >  El ID del laboratorio es ${laboratorio.id} </p> <br>
         <p id="url"> <a href="${laboratorio.url}">La URI del laboratorio es ${laboratorio.url} </a></p> <br>
         <p id="nombre">EL nombre del laboratorio es ${laboratorio.nombreLab} </p> <br>
         <p id="descripcion">La descripción del laboratorio es ${laboratorio.descripcionLab} </p> <br>
         <p >------------------------------------------------- </p> <br>
    </#list>
    </div>



</body>
</html>