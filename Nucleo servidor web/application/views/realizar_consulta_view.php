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
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css" rel="stylesheet"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
  <!-- <script src="<?=base_url('scripts/jquery-3.3.1.min.js');?>"></script> -->
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
        <div class="d-inline">
          <div class="px-4 center-cont">
            <a class="d-block" href="#" data-toggle="modal" data-target="#modalBiometricos">
              <img src="<?=base_url('estilos/imagenes/icons8-biometricos.png');?>" alt="" height="70" width="70">
            </a>
            <span class="btn d-block">Biométricos</span>
          </div>
        </div>
        <div class="d-inline">
          <div class="px-4 center-cont">
            <a class="d-block" href="#" data-toggle="modal" data-target="#modalHistorial">
              <img src="<?=base_url('estilos/imagenes/icons8-historial-clinico.png');?>" alt="" height="70" width="70">
            </a>
            <span class="btn d-block">Historial clinico</span>
          </div>
        </div>
      </div>
    </div>
    <div class="separator"></div>
    <div class="row">
      <div class="col-sm">
        <h1></h1>
      </div>
      <div class="col-sm">
        <h1 class="mb-1">Medicamentos recetados</h1>
      </div>
    </div>
    <form class="" method="post">
      <div class="row">
        <div class="col-sm">
          <nav>
            <div class="nav nav-tabs" id="nav-tab" role="tablist">
              <a class="nav-item nav-link active" id="nav-biometricos-tab" data-toggle="tab" href="#nav-biometricos" role="tab" aria-controls="nav-biometricos" aria-selected="true">Tomar biométricos</a>
              <a class="nav-item nav-link" id="nav-consulta-tab" data-toggle="tab" href="#nav-consulta" role="tab" aria-controls="nav-consulta" aria-selected="false">Consulta</a>
            </div>
          </nav>
          <div class="tab-content" id="nav-tabContent">
            <div class="tab-pane fade show active" id="nav-biometricos" role="tabpanel" aria-labelledby="nav-biometricos-tab">
              <div class="biometrics mt-4">
                <div class="form-group">
                  <label for="temperatura">Temperatura</label>
                  <input type="text" class="form-control" id="temperatura" name="temperatura" >
                </div>
                <div class="form-group">
                  <label for="peso">Peso</label>
                  <input type="text" class="form-control" id="peso" name="peso" >
                </div>
                <div class="form-group">
                  <label for="presion">Presion</label>
                  <input type="text" class="form-control" id="presion" name="presion" >
                </div>
                <div class="form-group">
                  <label for="estatura">Estarura</label>
                  <input type="text" class="form-control" id="estatura" name="estatura">
                </div>
              </div>
            </div>
            <div class="tab-pane fade" id="nav-consulta" role="tabpanel" aria-labelledby="nav-consulta-tab">
              <div class="form-group mt-4">
                <label for="diagnostico">Diagnóstico</label>
                <textarea class="form-control" id="diagnostico" name="diagnostico" rows="6"></textarea>
              </div>
              <div class="form-group">
                <label for="instrucciones">Instrucciones</label>
                <textarea class="form-control" id="instrucciones" name="instrucciones" rows="6"></textarea>
              </div>
            </div>
          </div>
        </div>
        <div class="col-sm">
          <div class="scrollable">
            <div id="listaConsultas" class="list-group">
              <a class="list-group-item list-group-item-action" >medicamentos</a>
              <a class="list-group-item list-group-item-action" >medicamentos</a>
              <a class="list-group-item list-group-item-action" >medicamentos</a>
              <a class="list-group-item list-group-item-action" >medicamentos</a>
              <a class="list-group-item list-group-item-action" >medicamentos</a>
              <a class="list-group-item list-group-item-action" >medicamentos</a>
              <a class="list-group-item list-group-item-action" >medicamentos</a>
              <a class="list-group-item list-group-item-action" >medicamentos</a>
              <a class="list-group-item list-group-item-action" >medicamentos</a>
              <a class="list-group-item list-group-item-action" >medicamentos</a>
              <a class="list-group-item list-group-item-action" >medicamentos</a>
              <a class="list-group-item list-group-item-action" >medicamentos</a>
              <a class="list-group-item list-group-item-action" >medicamentos</a>
              <a class="list-group-item list-group-item-action" >medicamentos</a>
              <a class="list-group-item list-group-item-action" >medicamentos</a>
              <a class="list-group-item list-group-item-action" >medicamentos</a>
            </div>
          </div>

          <div class="card-body row no-gutters align-items-center">
            <div class="col-auto">
              <i class="fas fa-search h4 text-body"></i>
            </div>
            <div class="col">
              <input class="form-control form-control-borderless" type="search" placeholder="Search topics or keywords">
            </div>
            <div class="col-auto">
              <button class="btn btn-primary" type="submit">Buscar</button>
              <a class="btn p-0" href="#" role="button">
                <img src="<?=base_url('estilos/imagenes/icons8-más3.svg');?>" alt="btn" height="50" width="50">
              </a>
            </div>
          </div>
        </div>
      </div>
    </form>
  </div>
  <!-- <div class=""> ventanas modales para los Biométricos y el historial clínico  </div> -->

  <div class="modal fade" id="modalBiometricos">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">

        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Historial biométrico</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>

        <!-- Modal body -->
        <div class="modal-body">
          <table class="table">
            <thead class="thead-dark">
              <tr>
                <th scope="col">Día</th>
                <th scope="col">Temperatura</th>
                <th scope="col">Peso</th>
                <th scope="col">Presíon</th>
                <th scope="col">Estatura</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">12/03/2005</th>
                <td>37.2°</td>
                <td>82.3 kg</td>
                <td>120/80 mm Hg</td>
                <td>1.75 m</td>
              </tr>
            </tbody>
          </table>
        </div>

        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" data-dismiss="modal">Listo</button>
        </div>

      </div>
    </div>
  </div>

  <div class="modal fade" id="modalHistorial">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">

        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Historial clínico</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>

        <!-- Modal body -->
        <div class="modal-body">
          <div class="scrollable">
            <div id="listaConsultas" class="list-group">
              <a class="list-group-item list-group-item-action" >
                <p>Fecha</p>
                <p>diagnÓstico</p>
                <p>mecicamentos</p>
              </a>
              <a class="list-group-item list-group-item-action" >
                <p>Fecha</p>
                <p>diagnÓstico</p>
                <p>mecicamentos</p>
              </a>
              <a class="list-group-item list-group-item-action" >
                <p>Fecha</p>
                <p>diagnÓstico</p>
                <p>mecicamentos</p>
              </a>
              <a class="list-group-item list-group-item-action" >
                <p>Fecha</p>
                <p>diagnÓstico</p>
                <p>mecicamentos</p>
              </a>
              <a class="list-group-item list-group-item-action" >
                <p>Fecha</p>
                <p>diagnÓstico</p>
                <p>mecicamentos</p>
              </a>
              <a class="list-group-item list-group-item-action" >
                <p>Fecha</p>
                <p>diagnÓstico</p>
                <p>mecicamentos</p>
              </a>
              </div>
          </div>
        </div>

        <!-- Modal footer -->
        <div class="modal-footer">

          <button type="button" class="btn btn-primary" data-dismiss="modal">Listo</button>
        </div>

      </div>
    </div>
  </div>


</body>

</html>
