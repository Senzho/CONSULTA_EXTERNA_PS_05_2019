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
        var nombre = $(this).limpiarEntradas($("#txtNombre").val());
        var apellido = $(this).limpiarEntradas($("#txtApellido").val());
        var seguro = $(this).limpiarEntradas($("#txtSeguroSocial").val());
        var telefono = $(this).limpiarEntradas($("#txtTelefono").val());
        var alergias = $(this).limpiarEntradas($("#txtAlergias").val());
        var sexo = $(this).limpiarEntradas($("#sexo").val());
        var fechaNacimiento = $(this).limpiarEntradas($("#fechaNac").val());
        var json = JSON.stringify({nombres: nombre, apellidos: apellido, numeroSeguro: seguro, numeroTelefono: telefono, alergias: alergias, sexo: sexo, fechaNacimiento: fechaNacimiento});
        $(this).modificarPaciente(json);
    });
    $("#botonBuscar").click(function(event) {
        event.preventDefault();
        var numeroSeguro = $("#campoBusqueda").val();
        $(this).obtenerPaciente(numeroSeguro);
    });
    $("a.panelPaciente").click(function() {
        var numeroSeguro = $(this).attr("id");
        //$('a.panelPaciente[id='+numeroSeguro+']').attr('class', 'list-group-item list-group-item-action panelPaciente active');
        //$('a.panelPaciente[class=list-group-item list-group-item-action panelPaciente]').attr('class', 'list-group-item list-group-item-action panelPaciente ');
        $(this).obtenerPaciente(numeroSeguro);
    });
    var numeroSeguro;
});

$.fn.modificarPaciente = function(json) {
    $.ajax({
        url: base_url+'/RecepcionistaController/modificarPaciente',
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
            $(this).actualizarLista(JSON.parse(json));
        } else {
            $(this).mostrarModalError(data.respuesta.mensaje);
        }
    });
}
$.fn.obtenerPaciente = function(numeroSeguro) {
    $.ajax({
        url:base_url+'/RecepcionistaController/obtenerPaciente/' + numeroSeguro,
	    method:"GET",
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
    $("#tituloFormulario").text('Ningún paciente seleccionado');
    $("#txtNombre").val('');
    $("#txtApellido").val('');
    $("#txtSeguroSocial").val('');
    $("#txtTelefono").val('');
    $("#txtAlergias").val('');
    $('#fechaNac').datepicker("setDate", new Date());
}
$.fn.cargarFormulario = function(paciente) {
    $("#tituloFormulario").text(paciente.numeroSeguro);
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
    $("#tituloModalRegistro").text("Paciente actualizado");
    $("#nombreRegistro").text(pacienteNuevo['nombres'] + " " + pacienteNuevo['apellidos']);
    $("#numeroRegistro").text(pacienteNuevo['numeroSeguro']);
    $("#modalPacienteActualizado").modal('toggle');
}
$.fn.mostrarModalError = function(mensaje) {
    $("#botonAñadirConsulta").hide();
    $("#tituloModalRegistro").text("Ocurrió un error");
    $("#nombreRegistro").text(mensaje);
    $("#numeroRegistro").text("");
    $("#modalPacienteActualizado").modal('toggle');
}
$.fn.actualizarLista = function(paciente) {
    $("#" + paciente['numeroSeguro']).text(paciente['nombres'] + " " + paciente['apellidos']);
}
$.fn.limpiarEntradas = function (cadena) {
    return String(cadena).replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;');
}
