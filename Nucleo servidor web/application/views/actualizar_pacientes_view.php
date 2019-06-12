<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Actualizar información de pacientes</title>
  <link rel="stylesheet" href="<?=base_url('estilos/actualizarPacientes.css');?>">
  <link rel="stylesheet" href="<?=base_url('estilos/bootstrap/bootstrap.min.css');?>">
  <link rel="stylesheet" href="<?=base_url('estilos/bootstrap/bootstrap-grid.min.css');?>">
  <link rel="stylesheet" href="<?=base_url('estilos/bootstrap/bootstrap-reboot.min.css');?>">
  <link rel="stylesheet" href="<?=base_url('estilos/bootstrap-datepicker.min.css');?>">
  <!-- <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css" rel="stylesheet"/> -->
  <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script> -->
  <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script> -->
  <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> -->
  <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script> -->
  <script src="<?=base_url('scripts/jquery.min.js');?>"></script>
  <script src="<?=base_url('scripts/bootstrap-datepicker.min.js');?>"></script>
  <script src="<?=base_url('scripts/popper.min.js');?>"></script>
  <script src="<?=base_url('scripts/bootstrap.min.js');?>"></script>
  <script src="<?=base_url('scripts/actualizacionPacientes.js');?>"></script>
  <script type="text/javascript">
  var base_url = "<?php echo site_url(); ?>";
  </script>
  <script src="<?=base_url('scripts/login.js');?>"></script>
</head>
<body >
  <nav id="navbarMain" class="navbar navbar-expand-lg navbar-ligth">
    <a id="home" >SCE.mx</a>
    <span class="mr-auto navbarText">
      <?= $nombre?>
    </span>
    <a id="cerrarSesion" class="nav-link ml-auto navbarText" href="<?php echo site_url('/RecepcionistaController/cerrarSesion'); ?>">Cerrar sesión</a>
  </nav>
  <div id="recepContenidorRegistro" class="container m-4 mx-auto">
    <div class="row">
      <div class="col-sm d-inline-flex centralizado">
        <div class="d-inline">
          <div class="px-4 center-cont">
            <a class="d-block" href="<?php echo site_url('/RecepcionistaController/index'); ?>">
              <img src="<?=base_url('estilos/imagenes/icons8-home.svg');?>" alt="" height="70" width="70">
            </a>
            <span class="btn d-block">Registro y consultas</span>
          </div>
        </div>
        <div class="d-inline">
          <div class="px-4 center-cont">
            <a class="d-block mx-auto" href="<?php echo site_url('/RecepcionistaController/agendarCita'); ?>">
              <img src="<?=base_url('estilos/imagenes/icons8-calendario.svg');?>" alt="botón para agendar cita" height="70" width="70">
            </a>
            <span class="btn d-block ">Agendar citas</span>
          </div>
        </div>
        <div class="d-inline">
          <div class="px-4 center-cont">
            <a class="d-block" href="#">
              <img src="<?=base_url('estilos/imagenes/icons8-grupos.png');?>" alt="" height="70" width="70">
            </a>
            <span class="btn d-block">Actualización</span>
          </div>
        </div>
      </div>
    </div>
    <div class="separator"></div>
    <div class="row">
      <div class="col-sm">
        <h1>Pacientes</h1>
      </div>
      <div class="col-sm">
        <h1 class="mb-1">Ningún paciente seleccionado</h1>
      </div>
    </div>
    <div class="row">
      <div class="col-sm">
        <form class="">
          <div class="card-body row no-gutters align-items-center">
            <div class="col-auto">
              <i class="fas fa-search h4 text-body"></i>
            </div>
            <!--end of col-->
            <div class="col">
              <input class="form-control form-control-borderless" type="search" placeholder="">
            </div>
            <!--end of col-->
            <div class="col-auto">
              <button class="btn btn-primary" type="submit">Buscar</button>
            </div>
            <!--end of col-->
          </div>
        </form>
        <div class="scrollable">
          <div id="listaConsultas" class="list-group">
            <a class="list-group-item list-group-item-action" > Nombre paciente</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente</a>
          </div>
        </div>
      </div>
      <div class="col-sm">
        <form id="formRegistro" action="" method="post">
          <div class="form-group">
            <label for="nombre">Nombre</label>
            <input type="text" class="form-control" id="txtNombre" placeholder="Ejemplo: 'María Guadalupe'" name="nombre">
          </div>
          <div class="form-group">
            <label for="apellido">Apellido</label>
            <input type="text" class="form-control" id="txtApellido" placeholder="Ejemplo: 'Hernández Sánchez'" name="apellido">
          </div>
          <div class="form-group">
            <label for="seguroSocial">Seguro Social</label>
            <input type="text" class="form-control" id="txtApellido" placeholder="Ejemplo: '3323244'" name="seguroSocial">
          </div>
          <div class="form-group">
            <label for="telefono">Telefono</label>
            <input type="tel" class="form-control" id="txtTelefono" placeholder="2282813731" name="telefono">
          </div>
          <div class="form-group">
            <label for="alergias">Alergias</label>
            <textarea class="form-control" id="txtAlergias" rows="5" name="alergias"></textarea>
          </div>
          <div class="form-group">
            <label for="sexo">Sexo</label>
            <select class="form-control" id="sexo" name="sexo">
              <option value="H">Hombre</option>
              <option value="M">Mujer</option>
            </select>
          </div>
          <div class="form-group">
            <label for="nombre">Fecha de la nacimiento</label>
            <input id="fechaNac" class="form-control" data-date-format="yyyy-mm-dd" name="fecha">
          </div>
          <button type="submit" class="btn btn-primary">Guardar</button>
        </form>
      </div>
    </div>

  </div>
  <div class="container">
    <!-- Button to Open the Modal -->
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
      Open modal
    </button>

    <!-- The Modal -->
    <div class="modal fade" id="myModal">
      <div class="modal-dialog">
        <div class="modal-content">

          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title" id="tituloModalRegistro">Paciente actualizado</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <p id="nombreRegistro">Solo de muestra hay que quitar el botón y vincularlo con el de registrar pero eso se realiza con js</p>
            <p id="numeroRegistro">Laura Martínez</p>
            <p>625327635-1</p>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">Listo</button>

          </div>

        </div>
      </div>
    </div>
  </div>
</body>

</html>
