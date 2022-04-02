/**
 * 
 */
window.onload = function(){

	document.getElementById('habilitaCampos').onclick=habilitaCampos;
	function habilitaCampos(){
	document.getElementById('username').disabled=false;
	document.getElementById('password').disabled=false;
	document.getElementById('nombre').disabled=false;
	document.getElementById('apellidos').disabled=false;
	document.getElementById('direccion').disabled=false;
  	document.getElementById('email').disabled=false;
	document.getElementById('botonSubmit').disabled=false;
	document.getElementById('habilitaCampos').disabled=true;
	
	
	}
	
}	


