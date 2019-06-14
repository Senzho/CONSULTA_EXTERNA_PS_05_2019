var esNuevoRegistro = true;
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
        var nombreUsuario = $(this).limpiarEntradas($("#txtUsuario").val());
        var contraseña = $(this).limpiarEntradas($("#txtContrasena").val());
        var nombre = $(this).limpiarEntradas($("#txtNombre").val());
        var apellido = $(this).limpiarEntradas($("#txtApellido").val());
        var telefono = $(this).limpiarEntradas($("#txtTelefono").val());
        var rfc = $(this).limpiarEntradas($("#txtRfc").val());
        var numeroPersonal = $(this).limpiarEntradas($("#txtNumeroPersonal").val());
        var turno = $(this).limpiarEntradas($("#turno").val());
        var sexo = $(this).limpiarEntradas($("#sexo").val());
        var fechaNacimiento = $(this).limpiarEntradas($("#fechaNac").val());
        if (esNuevoRegistro) {
            var json = JSON.stringify({usuario: {nombre: nombreUsuario, contraseña: contraseña}, medico: {nombres: nombre, apellidos: apellido, telefono: telefono, rfc: rfc, numeroPersonal: numeroPersonal, turno: turno, sexo: sexo, fechaNacimiento: fechaNacimiento}});
            $(this).registrarMedico(json);
        } else {
            var json = JSON.stringify({nombres: nombre, apellidos: apellido, telefono: telefono, rfc: rfc, numeroPersonal: numeroPersonal, turno: turno, sexo: sexo, fechaNacimiento: fechaNacimiento});
            $(this).registrarMedico(json);
        }
    });
    $("#botonBuscar").click(function(event) {
        event.preventDefault();
        var rfc = $("#campoBusqueda").val();
        $(this).obtenerMedico(rfc);
    });
    $("a.panelMedico").click($(this).agregarEvento);
});

$.fn.agregarEvento = function() {
    var rfc = $(this).attr("id");
    $(this).obtenerMedico(rfc);
}
$.fn.registrarMedico = function(json) {
    var metodo;
    var type;
    if (esNuevoRegistro) {
        metodo = 'guardarMedico';
        type = 'POST';
    } else {
        metodo = 'modificarMedico';
        type = 'PUT';
    }
    $.ajax({
        url: base_url+'/CoordinadorController/' + metodo,
	    method:type,
	    data:json,
	    processData:false,
        contentType:"application/json",
        dataType:"json",
	    cache:false,
	    async:true
    }).done(function (data) {
        if (data.respuesta.resultado) {
            if (esNuevoRegistro) {
                $(this).mostrarModalOk(JSON.parse(json)['medico']);
                $(this).agregarMedico(JSON.parse(json)['medico']);
            } else {
                $(this).mostrarModalOk(JSON.parse(json));
                $(this).actualizarLista(JSON.parse(json));
            }
            $(this).limpiarFormulario();
        } else {
            $(this).mostrarModalError(data.respuesta.mensaje);
        }
    });
}
$.fn.obtenerMedico = function(rfc) {
    $.ajax({
        url:base_url+'/CoordinadorController/obtenerMedico/' + rfc,
	    method:"GET",
	    processData:false,
        contentType:"application/json",
        dataType:"json",
	    cache:false,
	    async:true
    }).done(function (data) {
        if (data.numeroPersonal != 0) {
            $(this).cargarFormulario(data);
        }
    });
}
$.fn.limpiarFormulario = function() {
    $("#tituloFormulario").text('Registrar médico');
    $("#txtUsuario").val('');
    $("#txtContrasena").val('');
    $("#txtNombre").val('');
    $("#txtApellido").val('');
    $("#txtTelefono").val('');
    $("#txtRfc").val('');
    $("#txtNumeroPersonal").val('');
    $('#fechaNac').datepicker("setDate", new Date());
    $("#txtUsuario").show();
    $("#txtContrasena").show();
    esNuevoRegistro = true;
}
$.fn.cargarFormulario = function(medico) {
    $("#tituloFormulario").text(medico.rfc);
    $("#txtNombre").val(medico.nombres);
    $("#txtApellido").val(medico.apellidos);
    $("#txtTelefono").val(medico.telefono);
    $("#txtRfc").val(medico.rfc);
    $("#txtNumeroPersonal").val(medico.numeroPersonal);
    $("#turno").val(medico.turno);
    $("#sexo").val(medico.sexo);
    $('#fechaNac').datepicker("setDate", new Date(medico.fechaNacimiento));
    $("#txtUsuario").hide();
    $("#txtContrasena").hide();
    esNuevoRegistro = false;
}
$.fn.mostrarModalOk = function(medico) {
    $("#tituloModalRegistro").text("Médico guardado");
    $("#nombreRegistro").text(medico['nombres'] + " " + medico['apellidos']);
    $("#rfcRegistro").text(medico['rfc']);
    $("#modalMedico").modal('toggle');
}
$.fn.mostrarModalError = function(mensaje) {
    $("#tituloModalRegistro").text("Ocurrió un error");
    $("#nombreRegistro").text(mensaje);
    $("#rfcRegistro").text("");
    $("#modalMedico").modal('toggle');
}
$.fn.actualizarLista = function(medico) {
    $("#" + medico['rfc']).text(medico['nombres'] + " " + medico['apellidos']);
}
$.fn.agregarMedico = function(medico) {
    $("#listaMedicos").append("<a class='list-group-item list-group-item-action panelMedico' data-toggle='list' id='" + medico['rfc'] + "'>" + medico['nombres'] + " " + medico['apellidos'] + "</a>");
    $("#" + medico['rfc']).click($(this).agregarEvento);
}
$.fn.limpiarEntradas = function (cadena) {
    return String(cadena).replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;');
}