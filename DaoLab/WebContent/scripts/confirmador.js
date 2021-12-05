/**
 * Confirmação de Exclusão
 * @author Matheus	
 */

function confirmar(id){
	let resposta =  confirm("confirma a Exclusão desse departamento ?")
	if(resposta === true){
		//alert(id);
		window.location.href = "delete?id=" + id
	}
}