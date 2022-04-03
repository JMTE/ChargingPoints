/**
 * 
 */

window.onload = function() {

	document.getElementById('botonCancelar').onclick = cancelarReserva;

}

function cancelarReserva() {
	event.preventDefault();

	Swal.fire({
		title: 'Eliminar reserva',
		
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Si',
		cancelButtonText: 'No'
	}).then((result) => {
		if (result.isConfirmed) {

			document.getElementById('formularioCancelar').submit()

		}
	})
}


