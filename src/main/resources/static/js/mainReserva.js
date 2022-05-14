/**
 * 
 */

window.onload = function() {

	document.getElementById('minutos').onkeyup = calculaPrecio;
	function calculaPrecio() {

		document.getElementById('precio').value = (document.getElementById('minutos').value * 0.20).toFixed(2);
	}

}