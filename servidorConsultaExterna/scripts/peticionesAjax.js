$.fn.buscarPaciente = function(numeroSeguro, token){
	$.ajax({
	    url:'http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Paciente/obtener/' + numeroSeguro + "/" + token,
	    type:"get",
	    processData:false,
	    contentType:false,
	    cache:false,
	    async:true,
	    success: function(data){
            var json = JSON.parse(data);
            if (json['token']) {
                var paciente = json['paciente'];
                if (paciente['pacNumSeguro'] == 0) {
                    alert("No encontrado");
                } else {
                    alert("Obtenido");
                    //Mostrar en combo de selección
                }
            } else {
                alert("Lo sentimos, tu sesión a expirado");
            }
	    },
	    error: function(data){
	    	alert("Lo sentimos, ocurrió un error al obtener el paciente");
	    }
	});
}

$.fn.obtenerCitasFecha = function(fecha, token){
	$.ajax({
	    url:'http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Cita/obtener/' + fecha + "/" + token,
	    type:"get",
	    processData:false,
	    contentType:false,
	    cache:false,
	    async:true,
	    success: function(data){
            var json = JSON.parse(data);
            if (json['token']) {
                var citas = json['citas'];
                $.each(citas, function (i, item) {
                    //Añadir cita a la interfaz
                });
            } else {
                alert("Lo sentimos, tu sesión a expirado");
            }
	    },
	    error: function(data){
	    	alert("Lo sentimos, ocurrió un error al obtener las citas");
	    }
	});
}

$.fn.buscarPersonal = function(rfc, token){
	$.ajax({
	    url:'http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Personal/obtener/' + rfc + "/" + token,
	    type:"get",
	    processData:false,
	    contentType:false,
	    cache:false,
	    async:true,
	    success: function(data){
            var json = JSON.parse(data);
            if (json['token']) {
                var personal = json['personal'];
                if (personal['prRfc'] == '') {
                    alert("No encontrado");
                } else {
                    alert("Obtenido");
                    //Mostrar en combo de selección
                }
            } else {
                alert("Lo sentimos, tu sesión a expirado");
            }
	    },
	    error: function(data){
	    	alert("Lo sentimos, ocurrió un error al obtener el personal");
	    }
	});
}

$.fn.buscarMedicamentos = function(token){
	$.ajax({
	    url:'http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Medicamento/obtener/' + token,
	    type:"get",
	    processData:false,
	    contentType:false,
	    cache:false,
	    async:true,
	    success: function(data){
            var json = JSON.parse(data);
            if (json['token']) {
                var medicamentos = json['medicamentos'];
                $.each(medicamentos, function (i, item) {
                    //Añadir medicamento al combo de la interfaz
                });
            } else {
                alert("Lo sentimos, tu sesión a expirado");
            }
	    },
	    error: function(data){
	    	alert("Lo sentimos, ocurrió un error al obtener los medicamentos");
	    }
	});
}

$.fn.obtenerHistorialBiometrico = function(numeroSeguro, token){
	$.ajax({
	    url:'http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Consulta/historicobiometrico/' + numeroSeguro + '/' + token,
	    type:"get",
	    processData:false,
	    contentType:false,
	    cache:false,
	    async:true,
	    success: function(data){
            var json = JSON.parse(data);
            if (json['token']) {
                var consultas = json['consultas'];
                $.each(consultas, function (i, item) {
                    //Añadir datos biométricos a la interfaz
                });
            } else {
                alert("Lo sentimos, tu sesión a expirado");
            }
	    },
	    error: function(data){
	    	alert("Lo sentimos, ocurrió un error al obtener el historial");
	    }
	});
}

$.fn.obtenerHistorialClinico = function(numeroSeguro, token){
	$.ajax({
	    url:'http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Consulta/historicoclinico/' + numeroSeguro + '/' + token,
	    type:"get",
	    processData:false,
	    contentType:false,
	    cache:false,
	    async:true,
	    success: function(data){
            var json = JSON.parse(data);
            if (json['token']) {
                var consultas = json['consultas'];
                $.each(consultas, function (i, item) {
                    //Añadir datos clínicos a la interfaz
                });
            } else {
                alert("Lo sentimos, tu sesión a expirado");
            }
	    },
	    error: function(data){
	    	alert("Lo sentimos, ocurrió un error al obtener el historial");
	    }
	});
}