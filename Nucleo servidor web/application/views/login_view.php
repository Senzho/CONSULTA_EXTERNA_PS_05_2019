<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Inicio de sesión</title>
  <link rel="stylesheet" href="<?=base_url('estilos/login.css');?>">
  <link rel="stylesheet" href="<?=base_url('estilos/bootstrap/bootstrap.min.css');?>">
  <link rel="stylesheet" href="<?=base_url('estilos/bootstrap/bootstrap-grid.min.css');?>">
  <link rel="stylesheet" href="<?=base_url('estilos/bootstrap/bootstrap-reboot.min.css');?>">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <!-- <script src="<?=base_url('scripts/jquery-3.3.1.min.js');?>"></script> -->
  <script type="text/javascript">
  var base_url = "<?php echo site_url(); ?>";
  </script>
  <script src="<?=base_url('scripts/login.js');?>"></script>
</head>
<body class="text-center align-content-center" data-gr-c-s-loaded="true">
  <div class="container">
    <div class="row">
      <div class="col">
        <p id="mensaje"><?php if (isset($info)) {
          echo $info;
        }?></p>
      </div>
      <div class="col">
        <form id="formulario" class="form-signin">
          <img class="mb-4x" src="<?=base_url('estilos/imagenes/login.png');?>" alt="imagen login"/>
          <h1 class="h3 mb-3 font-weight-normal">Iniciar sesión</h1>
          <label for="inputEmail" class="sr-only">Usuario</label>
          <input type="text" id="usuario" class="form-control" placeholder="Usuario" required="" autofocus="">
          <label for="contrasena" class="sr-only">Contraseña</label>
          <input type="password" id="contrasena" class="form-control" placeholder="Contraseña" required="">
          <button class="btn btn-lg btn-primary btn-block" type="submit" id="btnEnviar">Ingresar</button>
          <p class="mt-5 mb-3 text-muted">© 2019-2020</p>
        </form>
      </div>
      <div class="col"></div>
    </div>
  </div>
</body>

</html>
