<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
	<title>Coordinador</title>
</head>
<body>
	<nav id="navbarMain" class="navbar navbar-expand-lg navbar-ligth">
	    <a id="home" >SCE.mx</a>
	    <span class="mr-auto navbarText">
	    	 Marta Laura Martínez Mendoza
	    </span>
	    <a id="cerrarSesion" class="nav-link ml-auto navbarText" href="<?php echo site_url('/CoordinadorController/cerrarSesion'); ?>">Cerrar sesión</a>
	</nav>
	 <div id="recepContenidorRegistro" class="container m-4 mx-auto">
	<div class="row">
      <div class="col d-inline-flex centralizado">
        <div class="d-inline">
          <div class="px-4 center-cont">
            <a class="d-block" href="#">
              <img src="<?=base_url('estilos/imagenes/lista-de-verificacion.svg');?>" alt="" height="70" width="70">
            </a>
            <span class="btn d-block">Entradas y salidas</span>
          </div>
        </div>
        <div class="d-inline">
          <div class="px-4 center-cont">
            <a class="d-block mx-auto" href="#">
              <img src="<?=base_url('estilos/imagenes/doctor.svg');?>" alt="botón para agendar cita" height="70" width="70">
            </a>
            <span class="btn d-block ">Registrar médico</span>
          </div>
        </div>
        <div class="d-inline">
          <div class="px-4 center-cont">
            <a class="d-block" href="#">
              <img src="<?=base_url('estilos/imagenes/usuario.svg');?>" alt="" height="70" width="70">
            </a>
            <span class="btn d-block">Registrar recepcionista</span>
          </div>
        </div>
        <div class="d-inline">
        <div class="px-4 center-cont">
        <a class="d-block mx-auto" href="#">
          <img src="<?=base_url('estilos/imagenes/pastillas.svg');?>" alt="botón para agendar cita" height="70" width="70">
        </a>
        <span class="btn d-block ">Cosultar medicamentos</span>
      	</div>
    	</div>
      </div>
    </div>
    <div class="separator"></div>
    <div class="row">
      <div class="col-sm">
        <h1>Recepcionistas</h1>
      </div>
      <div class="col-sm">
        <h1 class="mb-1">Registro / Edición</h1>
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
              <input type="text" class="form-control" placeholder="Buscar recepcionista">
            </div>
            <!--end of col-->
            <div class="col-auto">
              <button class="btn btn-primary" type="submit">Buscar</button>
            </div>
            <!--end of col-->
          </div>
        </form>
  </div>
  <div class="col-sm">
        <form class="">
          <div class="card-body row no-gutters align-items-center">
          </div>
        </form>
  </div>
  </div>
    <div class="row">
      <div class="col-sm">
        <div class="scrollable">
            <div id="listaRecepcionistas" class="list-group">
              <a class="list-group-item list-group-item-action" >Recepcionista nombre - hora</a>
              <a class="list-group-item list-group-item-action" >Recepcionista nombre - hora</a>
              <a class="list-group-item list-group-item-action" >Recepcionista nombre - hora</a>
              <a class="list-group-item list-group-item-action" >Recepcionista nombre - hora</a>
              <a class="list-group-item list-group-item-action" >Recepcionista nombre - hora</a>
              <a class="list-group-item list-group-item-action" >Recepcionista nombre - hora</a>
              <a class="list-group-item list-group-item-action" >Recepcionista nombre - hora</a>
              <a class="list-group-item list-group-item-action" >Recepcionista nombre - hora</a>
              <a class="list-group-item list-group-item-action" >Recepcionista nombre - hora</a>
              <a class="list-group-item list-group-item-action" >Recepcionista nombre - hora</a>
              <a class="list-group-item list-group-item-action" >Recepcionista nombre - hora</a>
              <a class="list-group-item list-group-item-action" >Recepcionista nombre - hora</a>
            </div>
      </div>
    </div>
    <div class="col-sm">
        <div class="">
            <form id="registroMedico" action="" method="post">
              <div class="form-group">
                <div class="row">
                  <div class="col">
                    <input class="form-control form-control-borderless" name="pacienteSeguro" type="text" placeholder="Nombre del recepcionista">
                  </div>
                </div>
              </div>

              <div class="form-group">
                <div class="row">
                  <div class="col">
                    <input class="form-control form-control-borderless" name="pacienteSeguro" type="text" placeholder="Apellidos">
                  </div>
                </div>
              </div>

              <div class="form-group">
                <div class="row">
                  <div class="col">
                    <input class="form-control form-control-borderless" name="pacienteSeguro" type="text" placeholder="Telefono">
                  </div>
                </div>
              </div>

              <div class="form-group">
                <div class="row">
                  <div class="col">
                    <input class="form-control form-control-borderless" name="pacienteSeguro" type="text" placeholder="RFC">
                  </div>
                </div>
              </div>

              <div class="form-group">
                <div class="row">
                  <div class="col">
                    <input class="form-control form-control-borderless" name="pacienteSeguro" type="text" placeholder="Número de personal">
                  </div>
                </div>
              </div>

              <div class="form-group">
                <div class="row">
                  <div class="col">
                    <select name="tuno" class="form-control">
                      <option>Matutino</option>
                      <option>Vespertino</option>
                    </select>
                  </div>
                </div>
              </div>

              <div class="form-group">
                <div class="row">
                  <div class="col">
                     <label>Genero</label>
                    <label class="radio-inline"><input type="radio" name="optradio" checked="">Hombre</label>
                    <label class="radio-inline"><input type="radio" name="optradio">Mujer</label>
                  </div>
                </div>
              </div>

              <div class="card-body row no-gutters align-items-center">
                <div class="col-auto">
                  <i class="fas fa-search h4 text-body"></i>
                </div>
                <!--end of col-->
                <div class="col">
                  <input type="text" class="form-control" placeholder="Fecha de nacimiento">
                </div>
                <!--end of col-->
                <div class="col-auto">
                  <button class="btn btn-primary" type="submit">Buscar</button>
                </div>
                <!--end of col-->
              </div>
               <button type="submit" class="btn btn-primary">Guardar</button>

            </form>
      </div>
    </div>
  </div>



      <!--este div permite que todo lo que esté adentro se pueda centrar-->
  </div>
  <!--este div permite que todo lo que esté adentro se pueda centrar-->
  <!--aqui es donde van todas las demás interfaces-->
</body>
</html>
