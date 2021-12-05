/**
 * 
 */

function Configurar(){
	
	let id = frmDerp.id.value
	if(id === ""){
		alert("Preencha o campo id")
		frmDerp.fone.focus()
		return false
	}else
		document.forms["frmDerp"].submit()
		
}