$(function () {
  $('#formRegistro').submit(function(event) {
    event.preventDefault();
    var nombre = $('#txtNombre').val();
    var apellidos = $('#txtApellidos').val();
    var seguroSocial = $('#txtSeguroSocial').val();
    var telefono = $('#txtTelefono').val();
    var alergias = $('#txtAlergias').val();
    if ($.trim(nombre).length != 0 && $.trim(apellidos).length != 0 && $.trim(seguroSocial).length != 0
    && $.trim(telefono).length != 0 && $.trim(alergias).length != 0) {
      var objeto= JSON.stringify( {"nombre": busqueda, "apellidos": apellidos, "seguro": seguroSocial, "telefono": telefono, "alergias":alergias});
      $.ajax({
        method: "POST",
        async: true,
        cache: false,
        contentType: "application/json",
        dataType: 'json',
        data: objeto,
        url: base_url +'index.php/RecepcionistaController/registrarPaciente'
      })
      .done(function (msg)
      {
        console.log(msg);
        if (msg.codigo == 15) {
          $('#myModel').modal('show');
        } else {
          // $("#mensajeHijo" ).remove();
          // $('#nombreBusqueda').val(msg.mensaje.nombre);
          // $('#apellidosBusqueda').val(msg.mensaje.apellidos);
          // $('#telefonoBusqueda').val(msg.mensaje.telefono);
          // $('#telefonoBusqueda').val(msg.mensaje.telefono);
          // $('#telefonoBusqueda').val(msg.mensaje.telefono);
        }
      });

    }else {
      $( "#mensajeHijo" ).remove();
      $('#mensajes').append("<p id='mensajeHijo'>El campo de búsqueda está vacío</p>").css('color', 'red');
    }
  });
  function validarCampos() {

  }
});
