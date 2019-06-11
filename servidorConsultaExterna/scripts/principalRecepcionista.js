$(function() {
    $("#formRegistro").submit(function(event){
        event.preventDefault();
        var nombre = $("#txtNombre").val();
        var apellido = $("#txtApellido").val();
        var seguro = $("#txtSeguroSocial").val();
        var telefono = $("#txtTelefono").val();
        var alergias = $("txtAlergias").val();
        var sexo = $("#sexo").val();
        var json = JSON.stringify({pacNombres: nombre, pacApellidos: apellido, pacNumSeguro: seguro, pacNumTelefono: telefono, pacAlergias: alergias, pacSexo: sexo});
        $(this).registrarPaciente(json, $("#tHidden").val());
    });
});

$.fn.registrarPaciente = function(json, token){
	$.ajax({
	    url:'http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Paciente/registrar/' + token,
	    type:"post",
	    data:json,
	    processData:false,
	    contentType:false,
	    cache:false,
	    async:true,
	    success: function(data){
            var json = JSON.parse(data);
            if (json['token']) {
                if (json['registrado']) {
                    alert('registrado');
                    //actualizar la interfaz
                } else {
                    var mensaje;
                    if (json['error'] == 'registro') {
                        mensaje = "Ocurrió un error al registrar el paciente, intente de nuevo más tarde"
                    } else {
                        mensaje = "Ya existe un paciente con el número de seguro ingresado";
                    }
                    alert("Error: " + mensaje);
                }
            } else {
                alert("Lo sentimos, tu sesión a expirado");
            }
	    },
	    error: function(data){
	    	alert("Lo sentimos, ocurrió un error al subir tu documento");
	    }
	});
}