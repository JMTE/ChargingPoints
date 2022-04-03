/**
 * 
 */

window.onload = function() {

	document.getElementById('botonEliminar').onclick = eliminarVehiculo;

}

function eliminarVehiculo() {
	event.preventDefault();

	Swal.fire({
		title: 'Eliminar vehiculo',
		
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Si',
		cancelButtonText: 'No'
	}).then((result) => {
		if (result.isConfirmed) {

			document.getElementById('formularioEliminar').submit()

		}
	})
}


