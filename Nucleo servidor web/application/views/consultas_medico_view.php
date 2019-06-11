<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Consultas médicas</title>
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
  <script type="text/javascript">
  var base_url = "<?php echo site_url(); ?>";
  </script>
  <script src="<?=base_url('scripts/login.js');?>"></script>
</head>
<body >
  <nav id="navbarMain" class="navbar navbar-expand-lg navbar-ligth">
    <a id="home" >SCE.mx</a>
    <span class="mr-auto navbarText">
      Marta Laura Martínez Mendoza
    </span>
    <a id="cerrarSesion" class="nav-link ml-auto navbarText" href="<?php echo site_url('/MedicoController/cerrarSesion'); ?>">Cerrar sesión</a>
  </nav>
  <div id="recepContenidorRegistro" class="container m-4 mx-auto">
    <div class="row">
      <div class="col d-inline-flex centralizado">
        <div class="d-inline">
          <div class="px-4 center-cont">
            <a class="d-block" href="#">
              <img src="<?=base_url('estilos/imagenes/icons8-citas-medico.png');?>" alt="" height="70" width="70">
            </a>
            <span class="btn d-block">Ver mis consultas</span>
          </div>
        </div>
      </div>
    </div>
    <div class="separator"></div>
    <div class="row">
      <div class="col-sm">
        <h1>Mis consultas</h1>
      </div>
      <div class="col-sm">
        <h1 class="mb-1"></h1>
      </div>
    </div>
    <div class="row">
      <div class="col-sm">
        <div class="scrollable">
          <div id="listaConsultas" class="list-group">
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
            <a class="list-group-item list-group-item-action" >Nombre paciente - hora</a>
          </div>
        </div>
      </div>
      <div class="col-sm">
      </div>
    </div>

  </div>
</body>

</html>
