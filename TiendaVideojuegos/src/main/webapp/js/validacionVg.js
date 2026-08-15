/**
 * 
 */
function validacion()
{
	var n1, n2, n3;
	n1 = document.getElementById("CVE").value;
	n2 = document.getElementById("TIT").value;
	n3 = document.getElementById("PRE").value;
	n4 = document.getElementById("CVEPROV").value;
	n5 = document.getElementById("INV").value;
	if(n1=="" || n2=="" || n3=="" || n4=="" || n5=="")
	{
		alert("Los Campos no pueden quedar vacios");
	}
	else
	{
		document.forms[0].action = "InsertarVideojuego.do?CVE="+n1+"&TIT="+n2+"&PRE="+n3+"&CVEPROV="+n4+"&INV="+n5;
		document.forms[0].method = "post";
		document.forms[0].submit();
		alert("VideoJuego insertado!");
	}
}