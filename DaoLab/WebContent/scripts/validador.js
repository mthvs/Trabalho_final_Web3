/**
 *Validação de formulário
 *@author Matheus Henrique
 */

function Validar(){
	let nome = frmDerp.nome.value
	if(nome === ""){
		alert("Preencha o campo Nome")
		frmDerp.fone.focus()
		return false
	}else
		document.forms["frmDerp"].submit()
}