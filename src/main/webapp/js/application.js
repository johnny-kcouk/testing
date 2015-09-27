function changeAction(formName, accion){
	document.getElementById(formName).action=accion;
	document.getElementById(formName).submit();
}
$(document).ready(function() {
	
// Menu
$('#menu').metisMenu();
$('.nave-menu').metisMenu();

// Show number of sub-elements in the menu
$('#menu > li').hover(function () {
    $(this).find('.onhover').text($(this).find('li').size());
});


});