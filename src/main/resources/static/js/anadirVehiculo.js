/**
 * 
 */

window.onload = function() {

	document.getElementById('anadirVehiculo').onclick = anadirVehiculo;

}

function anadirVehiculo() {
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

			document.getElementById('formularioAnadir').submit()

		}
	})
}
/**
 * 
 */