$(function() {
    $('#fechaNac').datepicker({
        weekStart: 1,
        daysOfWeekHighlighted: "6,0",
        autoclose: true,
        todayHighlight: true,
        enableOnReadonly: false,
        language: 'es',
    });
    $('#fechaNac').datepicker("setDate", new Date());
    $('#fechaNac').keypress(function(event) {
        event.preventDefault();
    });
    $("#formRegistro").submit(function(event){
        event.preventDefault();
        var nombre = $("#txtNombre").val();
        var apellido = $("#txtApellido").val();
        var seguro = $("#txtSeguroSocial").val();
        var telefono = $("#txtTelefono").val();
        var alergias = $("#txtAlergias").val();
        var sexo = $("#sexo").val();
        var fechaNacimiento = $("#fechaNac").val();
        var json = JSON.stringify({nombres: nombre, apellidos: apellido, numeroSeguro: seguro, numeroTelefono: telefono, alergias: alergias, sexo: sexo, fechaNacimiento: fechaNacimiento});
        $(this).modificarPaciente(json);
    });
    var numeroSeguro;
});

$.fn.modificarPaciente = function(json) {
    $.ajax({
        url:'http://localhost/ServidorConsultaExterna/index.php/RecepcionistaController/modificarPaciente',
	    method:"PUT",
	    data:json,
	    processData:false,
        contentType:"application/json",
        dataType:"json",
	    cache:false,
	    async:true
    }).done(function (data) {
        if (data.respuesta.resultado) {
            $(this).limpiarFormulario();
            $(this).mostrarModalOk(JSON.parse(json));
        } else {
            $(this).mostrarModalError(data.respuesta.mensaje);
        }
    });
}
$.fn.obtenerPaciente = function(numeroSeguro) {
    $.ajax({
        url:'http://localhost/ServidorConsultaExterna/index.php/RecepcionistaController/obtenerPaciente/' + numeroSeguro,
	    method:"GET",
	    data:json,
	    processData:false,
        contentType:"application/json",
        dataType:"json",
	    cache:false,
	    async:true
    }).done(function (data) {
        if (data.numeroSeguro != 0) {
            $(this).cargarFormulario(data);
        }
    });
}
$.fn.limpiarFormulario = function() {
    $("#txtNombre").val('');
    $("#txtApellido").val('');
    $("#txtSeguroSocial").val('');
    $("#txtTelefono").val('');
    $("#txtAlergias").val('');
    $('#fechaNac').datepicker("setDate", new Date());
}
$.fn.cargarFormulario = function(paciente) {
    $("#txtNombre").val(paciente.nombres);
    $("#txtApellido").val(paciente.apellidos);
    $("#txtSeguroSocial").val(paciente.numeroSeguro);
    $("#txtTelefono").val(paciente.numeroTelefono);
    $("#txtAlergias").val(paciente.alergias);
    $("#sexo").val(paciente.sexo);
    $('#fechaNac').datepicker("setDate", new Date(paciente.fechaNacimiento));
}
$.fn.mostrarModalOk = function(pacienteNuevo) {
    $("#botonAñadirConsulta").show();
    $("#tituloModalRegistro").text("Paciente registrado");
    $("#nombreRegistro").text(pacienteNuevo['nombres'] + " " + pacienteNuevo['apellidos']);
    $("#numeroRegistro").text(pacienteNuevo['numeroSeguro']);
    $("#modalPacienteRegistrado").modal('toggle');
}
$.fn.mostrarModalError = function(mensaje) {
    $("#botonAñadirConsulta").hide();
    $("#tituloModalRegistro").text("Ocurrió un error");
    $("#nombreRegistro").text(mensaje);
    $("#numeroRegistro").text("");
    $("#modalPacienteRegistrado").modal('toggle');
}