$(function() {
    $("#formRegistro").submit(function(event){
        event.preventDefault();
        var nombre = $("#txtNombre").val();
        var apellido = $("#txtApellido").val();
        var seguro = $("#txtSeguroSocial").val();
        var telefono = $("#txtTelefono").val();
        var alergias = $("txtAlergias").val();
        var sexo = $("#sexo").val();
        var fechaNacimiento = $("#fechaNac").datepicker({dateFormat: "yyyy-mm-dd"}).val();
        var json = JSON.stringify({pacNombres: nombre, pacApellidos: apellido, pacNumSeguro: seguro, pacNumTelefono: telefono, pacAlergias: alergias, pacSexo: sexo, pacFechaNac: fechaNacimiento});
        $(this).registrarPaciente(json);
    });
});

$.fn.registrarPaciente = function(json){
	$.ajax({
	    url:'http://localhost/ServidorConsultaExterna/index.php/RecepcionistaController/registrarPaciente',
	    method:"post",
	    data:json,
	    processData:false,
        contentType:"application/json",
        dataType:"json",
	    cache:false,
	    async:true
	}).done(function (data) {
        if (data.respuesta.resultado) {
            alert('registrado');
            //Ventana modal
        } else {
            alert(data.respuesta.mensaje);
            //Ventana modal
        }
    });
}