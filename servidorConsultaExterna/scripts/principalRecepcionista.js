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
        $(this).registrarPaciente(json);
    });
    var numeroSeguro;
});

$.fn.registrarPaciente = function(json){
	$.ajax({
	    url: base_url+'/RecepcionistaController/registrarPaciente',
	    method:"POST",
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
$.fn.limpiarFormulario = function() {
    $("#txtNombre").val('');
    $("#txtApellido").val('');
    $("#txtSeguroSocial").val('');
    $("#txtTelefono").val('');
    $("#txtAlergias").val('');
    $('#fechaNac').datepicker("setDate", new Date());
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
$.fn.limpiarEntradas = function (cadena) {
    return String(cadena).replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;');
}
