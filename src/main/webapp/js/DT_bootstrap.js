/* Default class modification */
$.extend($.fn.dataTableExt.oStdClasses, {
	"sSortAsc" : "header headerSortDown",
	"sSortDesc" : "header headerSortUp",
	"sSortable" : "header"
});

/* API method to get paging information */
$.fn.dataTableExt.oApi.fnPagingInfo = function(oSettings) {
	return {
		"iStart" : oSettings._iDisplayStart,
		"iEnd" : oSettings.fnDisplayEnd(),
		"iLength" : oSettings._iDisplayLength,
		"iTotal" : oSettings.fnRecordsTotal(),
		"iFilteredTotal" : oSettings.fnRecordsDisplay(),
		"iPage" : Math.ceil(oSettings._iDisplayStart
				/ oSettings._iDisplayLength),
		"iTotalPages" : Math.ceil(oSettings.fnRecordsDisplay()
				/ oSettings._iDisplayLength)
	};
}

/* Bootstrap style pagination control */
$
		.extend(
				$.fn.dataTableExt.oPagination,
				{
					"bootstrap" : {
						"fnInit" : function(oSettings, nPaging, fnDraw) {
							var oLang = oSettings.oLanguage.oPaginate;
							var fnClickHandler = function(e) {
								e.preventDefault();
								if (oSettings.oApi._fnPageChange(oSettings,
										e.data.action)) {
									fnDraw(oSettings);
								}
							};

							$(nPaging)
									.addClass('pagination')
									.append(
											'<ul class="pagination">'
													+ '<li class="prev disabled"><a href="#">&larr; '
													+ oLang.sPrevious
													+ '</a></li>'
													+ '<li class="next disabled"><a href="#">'
													+ oLang.sNext
													+ ' &rarr; </a></li>'
													+ '</ul>');
							var els = $('a', nPaging);
							$(els[0]).bind('click.DT', {
								action : "previous"
							}, fnClickHandler);
							$(els[1]).bind('click.DT', {
								action : "next"
							}, fnClickHandler);
						},

						"fnUpdate" : function(oSettings, fnDraw) {
							var iListLength = 5;
							var oPaging = oSettings.oInstance.fnPagingInfo();
							var an = oSettings.aanFeatures.p;
							var i, j, sClass, iStart, iEnd, iHalf = Math
									.floor(iListLength / 2);

							if (oPaging.iTotalPages < iListLength) {
								iStart = 1;
								iEnd = oPaging.iTotalPages;
							} else if (oPaging.iPage <= iHalf) {
								iStart = 1;
								iEnd = iListLength;
							} else if (oPaging.iPage >= (oPaging.iTotalPages - iHalf)) {
								iStart = oPaging.iTotalPages - iListLength + 1;
								iEnd = oPaging.iTotalPages;
							} else {
								iStart = oPaging.iPage - iHalf + 1;
								iEnd = iStart + iListLength - 1;
							}

							for (i = 0, iLen = an.length; i < iLen; i++) {
								// Remove the middle elements
								$('li:gt(0)', an[i]).filter(':not(:last)')
										.remove();

								// Add the new list items and their event
								// handlers
								for (j = iStart; j <= iEnd; j++) {
									sClass = (j == oPaging.iPage + 1) ? 'class="active"'
											: '';
									$(
											'<li ' + sClass + '><a href="#">'
													+ j + '</a></li>')
											.insertBefore(
													$('li:last', an[i])[0])
											.bind(
													'click',
													function(e) {
														e.preventDefault();
														oSettings._iDisplayStart = (parseInt(
																$('a', this)
																		.text(),
																10) - 1)
																* oPaging.iLength;
														fnDraw(oSettings);
													});
								}

								// Add / remove disabled classes from the static
								// elements
								if (oPaging.iPage === 0) {
									$('li:first', an[i]).addClass('disabled');
								} else {
									$('li:first', an[i])
											.removeClass('disabled');
								}

								if (oPaging.iPage === oPaging.iTotalPages - 1
										|| oPaging.iTotalPages === 0) {
									$('li:last', an[i]).addClass('disabled');
								} else {
									$('li:last', an[i]).removeClass('disabled');
								}
							}
						}
					}
				});

$.extend($.fn.dataTableExt.oStdClasses, {
	"sWrapper" : "dataTables_wrapper form-inline"
});

/* Table initialisation */
$(document)
.ready(
		function() {
			
			$('#tablaAlarmas')
			.dataTable(
					{
						"sDom": "<'row'<'col-xs-2'T><'col-xs-10'f>r>t<'row'<'col-xs-6'i><'col-xs-6'p>>",
						"sPaginationType" : "bootstrap",
						"oLanguage" : {											
							"sLengthMenu" : "Mostrando _MENU_ registros por página",
							"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
							"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
							"sZeroRecords": "No hay registros coincidentes",
							"sSearch" : "Filtrar resultados:",
							"sInfoEmpty": "0 registros encontrados",
							"oPaginate" : {
								"sNext" : " Siguiente ",
								"sPrevious" : " Anterior "
							}
						},
						"bAutoWidth" : false,
						"bLengthChange" : false,
						"bInfo" : true,
						"bPaginate" : true,
						"bFilter" : true,
						"bSort" : true,
						"iDisplayLength" : 10,
						"aaSorting": [[ 4, "asc" ] ],
						"aoColumnDefs": [{ "aTargets": [ 'dateColumn' ], "bSortable": true, "sType": "uk_date2"},
						                 { "aTargets": [ '_all' ], "bSortable": true , "sType": "natural"}],
								        "oTableTools": {
								            "aButtons": [{
								                    "sExtends":    "print",
								                    "sButtonText": "Imprimir"
								                }]
								        }
					});
			
			
			$('#tablaMisAlarmas')
			.dataTable(
					{
						"sDom": "<'row'<'col-xs-2'T><'col-xs-10'f>r>t<'row'<'col-xs-6'i><'col-xs-6'p>>",
						"sPaginationType" : "bootstrap",
						"oLanguage" : {											
							"sLengthMenu" : "Mostrando _MENU_ registros por página",
							"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
							"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
							"sZeroRecords": "No hay registros coincidentes",
							"sSearch" : "Filtrar resultados:",
							"sInfoEmpty": "0 registros encontrados",
							"oPaginate" : {
								"sNext" : " Siguiente ",
								"sPrevious" : " Anterior "
							}
						},
						"bAutoWidth" : false,
						"bLengthChange" : false,
						"bInfo" : true,
						"bPaginate" : true,
						"bFilter" : true,
						"bSort" : true,
						"iDisplayLength" : 10,
						"aaSorting": [[ 4, "asc" ] ],
						"aoColumnDefs": [{ "aTargets": [ 'dateColumn' ], "bSortable": true, "sType": "uk_date2"},
						                 { "aTargets": [ '_all' ], "bSortable": true , "sType": "natural"}],
								        "oTableTools": {
								            "aButtons": [{
								                    "sExtends":    "print",
								                    "sButtonText": "Imprimir"
								                }]
								        }
					});
	
		$('#tablaAlarmas2')
				.dataTable(
						{
							"sDom": "<'row'<'col-xs-6'l><'col-xs-6'f>r>t<'row'<'col-xs-6'i><'col-xs-6'p>>",
							"sPaginationType" : "bootstrap",
							"oLanguage" : {											
								"sLengthMenu" : "Mostrando _MENU_ registros por página",
								"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
								"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
								"sZeroRecords": "No hay registros coincidentes",
								"sSearch" : "Filtrar resultados.:",
								"sInfoEmpty": "0 registros encontrados",
								"oPaginate" : {
									"sNext" : " Siguiente ",
									"sPrevious" : " Anterior "
								}
							},
							"bAutoWidth" : false,
							"bLengthChange" : false,
							"bInfo" : true,
							"bPaginate" : true,
							"bFilter" : true,
							"bSort" : true,
							"iDisplayLength" : 10,
							"aaSorting": [[ 3, "asc" ] ],
							"aoColumnDefs": [{ "aTargets": [ 'dateColumn' ], "bSortable": true, "sType": "uk_date2"},
							                 { "aTargets": [ '_all' ], "bSortable": true , "sType": "natural"}]
						});
		
		
			
	
		$('#tablaMisAlarmas2')
				.dataTable(
						{
							"sDom": "<'row'<'col-xs-6'l><'col-xs-6'f>r>t<'row'<'col-xs-6'i><'col-xs-6'p>>",
							"sPaginationType" : "bootstrap",
							"oLanguage" : {											
								"sLengthMenu" : "Mostrando _MENU_ registros por página",
								"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
								"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
								"sZeroRecords": "No hay registros coincidentes",
								"sSearch" : "Filtrar resultados.:",
								"sInfoEmpty": "0 registros encontrados",
								"oPaginate" : {
									"sNext" : " Siguiente ",
									"sPrevious" : " Anterior "
								}
							},
							"bAutoWidth" : false,
							"bLengthChange" : false,
							"bInfo" : true,
							"bPaginate" : true,
							"bFilter" : true,
							"bSort" : true,
							"iDisplayLength" : 10,
							"aaSorting": [[ 3, "asc" ] ],
							"aoColumnDefs": [{ "aTargets": [ 'dateColumn' ], "bSortable": true, "sType": "uk_date2"},
							                 { "aTargets": [ '_all' ], "bSortable": true , "sType": "natural"}]
						});
		
		
		$('#tablaPaginada')
		.dataTable(
				{
					"sDom": "<'row'<'col-xs-6'l><'col-xs-6'f>r>t<'row'<'col-xs-6'i><'col-xs-6'p>>",
					"sPaginationType" : "bootstrap",
					"oLanguage" : {											
						"sLengthMenu" : "Mostrando _MENU_ registros por página",
						"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
						"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
						"sZeroRecords": "No hay registros coincidentes",
						"sSearch" : "Filtrar resultados:",
						"sInfoEmpty": "0 registros encontrados",
						"oPaginate" : {
							"sNext" : " Siguiente ",
							"sPrevious" : " Anterior "
						}
					},
					"bAutoWidth" : false,
					"bLengthChange" : false,
					"bInfo" : true,
					"bPaginate" : true,
					"bFilter" : true,
					"bSort" : true,
					"iDisplayLength" : 10,
					"aaSorting": [ ],
					"aoColumnDefs": [{ "aTargets": [ 'dateColumn' ], "bSortable": true, "sType": "uk_date2"},
					                 { "aTargets": [ '_all' ], "bSortable": true , "sType": "natural"}]
				});

	$('#tablaPaginada2')
			.dataTable(
					{
						"sDom": "<'row'<'col-xs-6'l><'col-xs-6'f>r>t<'row'<'col-xs-6'i><'col-xs-6'p>>",
						"sPaginationType" : "bootstrap",
						"oLanguage" : {											
							"sLengthMenu" : "Mostrando _MENU_ registros por página",
							"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
							"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
							"sZeroRecords": "No hay registros coincidentes",
							"sSearch" : "Filtrar resultados:",
							"sInfoEmpty": "0 registros encontrados",
							"oPaginate" : {
								"sNext" : " Siguiente ",
								"sPrevious" : " Anterior "
							}
						},
						"bAutoWidth" : false,
						"bLengthChange" : false,
						"bInfo" : true,
						"bPaginate" : true,
						"bFilter" : true,
						"bSort" : true,
						"iDisplayLength" : 10,
						"aaSorting": [ ],
						"aoColumnDefs": [{ "aTargets": [ 'dateColumn' ], "bSortable": true, "sType": "uk_date2"},
						                 { "aTargets": [ '_all' ], "bSortable": true , "sType": "natural"}]
					});
	

					$('#tablaPaginada3')
							.dataTable(
									{
	    								"sDom": "<'row'<'col-xs-6'l><'col-xs-6'f>r>t<'row'<'col-xs-6'i><'col-xs-6'p>>",
										"sPaginationType" : "bootstrap",
										"oLanguage" : {											
											"sLengthMenu" : "Mostrando _MENU_ registros por página",
											"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
											"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
											"sZeroRecords": "No hay registros coincidentes",
											"sSearch" : "Filtrar resultados:",
											"sInfoEmpty": "0 registros encontrados",
											"oPaginate" : {
												"sNext" : " Siguiente ",
												"sPrevious" : " Anterior "
											}
										},
										"bAutoWidth" : false,
										"bLengthChange" : false,
										"bInfo" : true,
										"bPaginate" : true,
										"bFilter" : true,
										"bSort" : true,
										"iDisplayLength" : 10,
										"aaSorting": [ ],
										"aoColumnDefs": [{ "aTargets": [ 'dateColumn' ], "bSortable": true, "sType": "uk_date2"},
										                 { "aTargets": [ '_all' ], "bSortable": true , "sType": "natural"}]
									});


						$('#tablaPaginada4')
								.dataTable(
										{
		    								"sDom": "<'row'<'col-xs-6'l><'col-xs-6'f>r>t<'row'<'col-xs-6'i><'col-xs-6'p>>",
											"sPaginationType" : "bootstrap",
											"oLanguage" : {											
												"sLengthMenu" : "Mostrando _MENU_ registros por página",
												"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
												"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
												"sZeroRecords": "No hay registros coincidentes",
												"sSearch" : "Filtrar resultados:",
												"sInfoEmpty": "0 registros encontrados",
												"oPaginate" : {
													"sNext" : " Siguiente ",
													"sPrevious" : " Anterior "
												}
											},
											"bAutoWidth" : false,
											"bLengthChange" : false,
											"bInfo" : true,
											"bPaginate" : true,
											"bFilter" : true,
											"bSort" : true,
											"iDisplayLength" : 10,
											"aaSorting": [ ],
											"aoColumnDefs": [{ "aTargets": [ 'dateColumn' ], "bSortable": true, "sType": "uk_date2"},
											                 { "aTargets": [ '_all' ], "bSortable": true , "sType": "natural"}]
										});
						

						$('#tablaPaginada_print')
								.dataTable(
										{
											"sDom": "<'row'<'col-xs-2'T><'col-xs-10'f>r>t<'row'<'col-xs-6'i><'col-xs-6'p>>",
											"sPaginationType" : "bootstrap",
											"oLanguage" : {											
												"sLengthMenu" : "Mostrando _MENU_ registros por página",
												"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
												"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
												"sZeroRecords": "No hay registros coincidentes",
												"sSearch" : "Filtrar resultados:",
												"sInfoEmpty": "0 registros encontrados",
												"oPaginate" : {
													"sNext" : " Siguiente ",
													"sPrevious" : " Anterior "
												}
											},
											"bAutoWidth" : false,
											"bLengthChange" : false,
											"bInfo" : true,
											"bPaginate" : true,
											"bFilter" : true,
											"bSort" : true,
											"iDisplayLength" : 10,
											"aaSorting": [ ],
											"aoColumnDefs": [{ "aTargets": [ 'dateColumn' ], "bSortable": true, "sType": "uk_date2"},
											                 { "aTargets": [ '_all' ], "bSortable": true , "sType": "natural"}],
									        "oTableTools": {
									            "aButtons": [{
									                    "sExtends":    "print",
									                    "sButtonText": "Imprimir"
									                }]
									        }
										});
						
						$('#tablaPaginada2_print')
						.dataTable(
								{
									"sDom": "<'row'<'col-xs-2'T><'col-xs-10'f>r>t<'row'<'col-xs-6'i><'col-xs-6'p>>",
									"sPaginationType" : "bootstrap",
									"oLanguage" : {											
										"sLengthMenu" : "Mostrando _MENU_ registros por página",
										"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
										"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
										"sZeroRecords": "No hay registros coincidentes",
										"sSearch" : "Filtrar resultados:",
										"sInfoEmpty": "0 registros encontrados",
										"oPaginate" : {
											"sNext" : " Siguiente ",
											"sPrevious" : " Anterior "
										}
									},
									"bAutoWidth" : false,
									"bLengthChange" : false,
									"bInfo" : true,
									"bPaginate" : true,
									"bFilter" : true,
									"bSort" : true,
									"iDisplayLength" : 10,
									"aaSorting": [ ],
									"aoColumnDefs": [
									                 { "aTargets": [ 'dateColumn' ], "bSortable": true, "sType": "uk_date2"},
									                 { "aTargets": [ '_all' ], "bSortable": true , "sType": "natural"}
									                 ],
							        "oTableTools": {
							            "aButtons": [{
							                    "sExtends":    "print",
							                    "sButtonText": "Imprimir"
							                }]
							        }
								});
		});



/* Ordenar fechas*/

jQuery.fn.dataTableExt.aTypes.push(
    function ( sData )
    {
        if (sData.match(/^(0[1-9]|[12][0-9]|3[01])\-(0[1-9]|1[012])\-(19|20|21)\d\d$/))
        {
            return 'uk_date2';
        }
        else
        {
            return null;
        }
    }
);
 
jQuery.fn.dataTableExt.oSort['uk_date2-asc']  = function(a,b) {
    var ukDatea = a.split('/');
    var ukDateb = b.split('/');
     
    var x = (ukDatea[2] + ukDatea[1] + ukDatea[0]) * 1;
    var y = (ukDateb[2] + ukDateb[1] + ukDateb[0]) * 1;
     
    return ((x < y) ? -1 : ((x > y) ?  1 : 0));
     
     
};
 
jQuery.fn.dataTableExt.oSort['uk_date2-desc'] = function(a,b) {
    var ukDatea = a.split('/');
    var ukDateb = b.split('/');
     
    var x = (ukDatea[2] + ukDatea[1] + ukDatea[0]) * 1;
    var y = (ukDateb[2] + ukDateb[1] + ukDateb[0]) * 1;
     
    return ((x < y) ? 1 : ((x > y) ?  -1 : 0));
};



(function() {
	 
	/*
	 * Natural Sort algorithm for Javascript - Version 0.7 - Released under MIT license
	 * Author: Jim Palmer (based on chunking idea from Dave Koelle)
	 * Contributors: Mike Grier (mgrier.com), Clint Priest, Kyle Adams, guillermo
	 * See: http://js-naturalsort.googlecode.com/svn/trunk/naturalSort.js
	 */
	function naturalSort (a, b) {
	    var re = /(^-?[0-9]+(\.?[0-9]*)[df]?e?[0-9]?$|^0x[0-9a-f]+$|[0-9]+)/gi,
	        sre = /(^[ ]*|[ ]*$)/g,
	        dre = /(^([\w ]+,?[\w ]+)?[\w ]+,?[\w ]+\d+:\d+(:\d+)?[\w ]?|^\d{1,4}[\/\-]\d{1,4}[\/\-]\d{1,4}|^\w+, \w+ \d+, \d{4})/,
	        hre = /^0x[0-9a-f]+$/i,
	        ore = /^0/,
	        // convert all to strings and trim()
	        x = a.toString().replace(sre, '') || '',
	        y = b.toString().replace(sre, '') || '',
	        // chunk/tokenize
	        xN = x.replace(re, '\0$1\0').replace(/\0$/,'').replace(/^\0/,'').split('\0'),
	        yN = y.replace(re, '\0$1\0').replace(/\0$/,'').replace(/^\0/,'').split('\0'),
	        // numeric, hex or date detection
	        xD = parseInt(x.match(hre)) || (xN.length != 1 && x.match(dre) && Date.parse(x)),
	        yD = parseInt(y.match(hre)) || xD && y.match(dre) && Date.parse(y) || null;
	    // first try and sort Hex codes or Dates
	    if (yD)
	        if ( xD < yD ) return -1;
	        else if ( xD > yD )  return 1;
	    // natural sorting through split numeric strings and default strings
	    for(var cLoc=0, numS=Math.max(xN.length, yN.length); cLoc < numS; cLoc++) {
	        // find floats not starting with '0', string or 0 if not defined (Clint Priest)
	        var oFxNcL = !(xN[cLoc] || '').match(ore) && parseFloat(xN[cLoc]) || xN[cLoc] || 0;
	        var oFyNcL = !(yN[cLoc] || '').match(ore) && parseFloat(yN[cLoc]) || yN[cLoc] || 0;
	        // handle numeric vs string comparison - number < string - (Kyle Adams)
	        if (isNaN(oFxNcL) !== isNaN(oFyNcL)) return (isNaN(oFxNcL)) ? 1 : -1;
	        // rely on string comparison if different types - i.e. '02' < 2 != '02' < '2'
	        else if (typeof oFxNcL !== typeof oFyNcL) {
	            oFxNcL += '';
	            oFyNcL += '';
	        }
	        if (oFxNcL < oFyNcL) return -1;
	        if (oFxNcL > oFyNcL) return 1;
	    }
	    return 0;
	}
	 
	jQuery.extend( jQuery.fn.dataTableExt.oSort, {
	    "natural-asc": function ( a, b ) {
	        return naturalSort(a,b);
	    },
	 
	    "natural-desc": function ( a, b ) {
	        return naturalSort(a,b) * -1;
	    }
	} );
	 
	}());



/* Corrige el formato del search con Bootstrap 3 */
$(function(){
    $('.table').each(function(){
        var datatable = $(this);
        // SEARCH - Add the placeholder for Search and Turn this into in-line formcontrol
        var search_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] input');
        search_input.attr('placeholder', 'Filtrar')
        search_input.addClass('form-control input-small')
        search_input.css('width', '250px')
 
        // SEARCH CLEAR - Use an Icon
        var clear_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] a');
        clear_input.html('<i class="icon-remove-circle icon-large"></i>')
        clear_input.css('margin-left', '5px')
 
        // LENGTH - Inline-Form control
        var length_sel = datatable.closest('.dataTables_wrapper').find('div[id$=_length] select');
        length_sel.addClass('form-control input-small')
        length_sel.css('width', '75px')
 
        // LENGTH - Info adjust location
        var length_sel = datatable.closest('.dataTables_wrapper').find('div[id$=_info]');
        length_sel.css('margin-top', '18px')
    });
});