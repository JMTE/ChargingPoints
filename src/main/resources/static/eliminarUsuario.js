/**
 * 
 */

function eliminarUsuario() {
	event.preventDefault();

	Swal.fire({
		title: 'Eliminar usuario',
		
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Si',
		cancelButtonText: 'No'
	}).then((result) => {
		if (result.isConfirmed) {

			document.getElementById('formularioEliminar').submit();

		}
	})
}