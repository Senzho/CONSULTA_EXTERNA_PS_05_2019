<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Agendar cita</title>
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
  $(function () {
    $('#datepicker').datepicker({
      weekStart: 1,
      daysOfWeekHighlighted: "6,0",
      autoclose: true,
      todayHighlight: true,
      enableOnReadonly: false,
      language: 'es',
    });
    $('#datepicker').datepicker("setDate", new Date());
    $('#datepicker').keypress(function(event) {
      event.preventDefault();
    });
    $('#datepickerCitaNueva').datepicker({
      weekStart: 1,
      daysOfWeekHighlighted: "6,0",
      autoclose: true,
      todayHighlight: true,
      enableOnReadonly: false,
      language: 'es',
    });
    $('#datepickerCitaNueva').datepicker("setDate", new Date());
    $('#datepickerCitaNueva').keypress(function(event) {
      event.preventDefault();
    });
  });
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
      <div class="col d-inline-flex centralizado">
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
            <a class="d-block mx-auto" href="#">
              <img src="<?=base_url('estilos/imagenes/icons8-calendario.svg');?>" alt="botón para agendar cita" height="70" width="70">
            </a>
            <span class="btn d-block ">Agendar citas</span>
          </div>
        </div>
        <div class="d-inline">
          <div class="px-4 center-cont">
            <a class="d-block" href="<?php echo site_url('/RecepcionistaController/actualizarPacientes'); ?>">
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
        <h1>Citas</h1>
      </div>
      <div class="col-sm">
        <h1 class="mb-1">Agendar cita</h1>
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
              <input class="form-control" data-date-format="dd/mm/yyyy" id="datepicker">
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
        <form id="formRegistro" action="" method="post">
          <div class="form-group">
            <div class=" row">
              <div class="col">
                <label for="pacienteSeguro">Número de seguro social del paciente</label>
              </div>
              <div class="col"></div>
            </div>
            <div class="row">
              <div class="col">
                <input class="form-control form-control-borderless" name="pacienteSeguro" type="search" placeholder="paciente">
              </div>
              <div class="col-auto">
                <button class="btn btn-primary m" type="button">Buscar</button>
              </div>
            </div>
          </div>
        <!-- </div> -->
        <div class="form-group">
          <label for="medicos">Selecciona el médico</label>
          <select class="form-control" id="medicos" name="medicos">
            <option>medico</option>
            <option>medico 2</option>
            <option>medico 3</option>
            <option>medico 4</option>
            <option>medico 5</option>
          </select>
        </div>
        <!-- </div> -->
        <div class="form-group">
          <label for="nombre">Fecha de la cita</label>
          <input id="datepickerCitaNueva" class="form-control" data-date-format="dd/mm/yyyy" id="datepicker">
        </div>
        <div class="form-group">
          <div class=" row">
            <div class="col">
              <label for="horaCita">Horas</label>
            </div>
            <div class="col">
              <label for="minutosCita">Minutos</label>
            </div>
            <div class="col">

            </div>
          </div>
          <div class="row">
            <div class="col">
              <select class="form-control" id="horaCita" name="horaCita">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                <option>6</option>
                <option>7</option>
                <option>8</option>
                <option>9</option>
                <option>10</option>
                <option>11</option>
                <option>12</option>

              </select>
            </div>
            <div class="col">
              <select class="form-control" id="minutosCita" name="minutosCita">
                <<option>00</option>
                <option>10</option>
                <option>20</option>
                <option>30</option>
                <option>40</option>
                <option>50</option>
              </select>
            </div>
            <div class="col">
              <select class="form-control" id="ampm" name="ampm">
                <<option>AM</option>
                <option>PM</option>
              </select>
            </div>
          </div>


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
          <h4 class="modal-title">Cita agendada</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>

        <!-- Modal body -->
        <div class="modal-body">
          <p>Solo de muestra hay que quitar el botón y vincularlo con el de registrar pero eso se realiza con js</p>
          <p>Laura Martínez</p>
          <p>Fecha: 31 de diciembre de 2019</p>
          <p>12:30 PM</p>
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
