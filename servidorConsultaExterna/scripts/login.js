jQuery(document).ready(function($) {
  $('#formulario').submit(function (event) {
    event.preventDefault();
    var usuario = $('#usuario').val();
    var contrasena = $('#contrasena').val();

    $.ajax({
      method: "POST",
      async: true,
      cache: false,
      dataType: 'json',
      timeout: 30000,
      url: base_url+"/pacienteajax/validarLogin",
      data: {usuario: usuario, contrasena: contrasena},
      headers: {
        'Access-Control-Allow-Credentials' : true,
        'Access-Control-Allow-Origin':'*',
        'Access-Control-Allow-Methods':'POST',
        'Access-Control-Allow-Headers':'application/json'
      }
    })
    .done(function (msg)
    {
      if (msg.res == 'no') {
        alert(msg.mensaje);
      } else {
        setTimeout(function () {
          window.location.href = base_url+"/Welcome/index";
        }, 0);
      }
    });
  });
});
