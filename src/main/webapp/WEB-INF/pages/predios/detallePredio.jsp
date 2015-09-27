<%@ include file="/WEB-INF/pages/include.jsp"%>


<p class="bs-callout bs-callout-info" role="alert"> 
			<strong>Comunidad:</strong> ${predio.portal.comunidad.teNombre} 
			<br />
			<strong>Portal nº:</strong>	<c:if test='${predio.portal.teNombre!=null}'> ${predio.portal.teNombre}</c:if>
</p>			

<div class="page-header">
	<h2>
			<c:if test='${predio.cnPredio==null}'>Nuevo Predio</c:if>
			<c:if test='${predio.cnPredio!=null}'>Predio: ${predio.planta.tePlanta } - ${predio.tePredio}
			</c:if></h2>
</div>

<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="predio" method="POST" action="action/predios/guardar">
	
		<form:hidden path="cnPredio" />
		<form:hidden path="portal.cnPortal" />
		<form:hidden path="terceroByCnPropietario.cnTercero" />
		<form:hidden path="terceroByCnConyuge.cnTercero" />
		<form:hidden path="terceroByCnInquilino.cnTercero" />
	
	
	<div class="panel panel-default">
  	<div class="panel-body">
		
<div class="row">

<div class="col-lg-2">
<div class="form-group ${status.error ? 'error' : '' }">
				<label class="control-label" for="tipoPredio.cnTipoPredio">Tipo
					predio *</label>
				<div class="controls">
				 <script>
		       			 $(document).ready(function() { $("#tipoPredio").select2({minimumResultsForSearch: -1}); });
		    		</script>    
					<form:select path="tipoPredio.cnTipoPredio" cssClass="form-control input-xlarge"
						id="tipoPredio" tabindex="1">
						<form:option value="1" label="Piso" />
						<form:option value="2" label="Garaje" />
					</form:select>

					<c:if test="${status.error}">
						<span class="help-inline">${status.errorMessage}</span>
					</c:if>
				</div>
			</div>
</div>
<t:select itemLabel="tePlanta" itemValue="cnPlanta" items="${plantasCombo}" path="planta.cnPlanta" required="true" label="Planta" emptyOption="true" tabindex="1" gridClass="col-lg-2"/>
<t:input path="tePredio" label="Predio" required="false" maxlength="50" tabindex="3" gridClass="col-lg-2" />
<t:input path="teTrastero" label="Trastero" required="false" maxlength="50" tabindex="4" gridClass="col-lg-2" />
<t:input path="tePlaza" label="Plaza asociada" required="false" maxlength="50" tabindex="5" gridClass="col-lg-2" />
</div>

<div class="row">
<t:select itemLabel="teTipoRepresentante" itemValue="cnTipoRepresentante" items="${tiposRepresentanteCombo}" path="tipoRepresentante.cnTipoRepresentante" required="false" label="Tipo representante" emptyOption="true" tabindex="7" gridClass="col-lg-4"/>
</div>
		
<div class="row">
<t:area path="teObservaciones" label="Observaciones" cols="500"	tabindex="9" gridClass="col-lg-12" />
</div>
	</div>
</div>




<ul id="tabMenu" class="nav nav-tabs">
  <li id="propietario-tab" class="active"><a href="#propietario" data-toggle="tab">Datos del propietario</a></li>
  <li id="conyuge-tab"><a href="#conyuge" data-toggle="tab">Datos del cónyuge</a></li>
  <li id="inquilino-tab"><a href="#inquilino" data-toggle="tab">Datos del inquilino</a></li></ul>



 <div id="tabContent" class="tab-content">
    <div id="propietario" class="tab-pane fade in active">
    
    


<div class="row">
<t:input path="terceroByCnPropietario.caNif" label="NIF" required="false" maxlength="10" tabindex="8" gridClass="col-lg-4" />
</div>

<div class="row">
<t:input path="terceroByCnPropietario.teNombre" label="Nombre" required="true" maxlength="100" tabindex="9" gridClass="col-lg-4" />
<t:input path="terceroByCnPropietario.teApellido1" label="Primer apellido" required="true" maxlength="50" tabindex="10" gridClass="col-lg-4" />
<t:input path="terceroByCnPropietario.teApellido2" label="Segundo apellido" required="false" maxlength="50" tabindex="11" gridClass="col-lg-4" />
</div>

<div class="row">
<t:input path="terceroByCnPropietario.teTlfFijo" label="Teléfono" required="false" maxlength="50" tabindex="12" gridClass="col-lg-4" />
<t:input path="terceroByCnPropietario.teTlfMovil1" label="Móvil" required="false" maxlength="50" tabindex="13" gridClass="col-lg-4" />
<t:input path="terceroByCnPropietario.teTlfMovil2" label="Otro" required="false" maxlength="50" tabindex="14" gridClass="col-lg-4" />
</div>


<div class="row">
<t:input path="terceroByCnPropietario.teEmail" label="E-mail" required="false" maxlength="50" tabindex="15" gridClass="col-lg-6" />
</div>

<div class="row">
<t:input path="terceroByCnPropietario.nuCuentaCorriente" label="Cuenta corriente" maxlength="23" required="false" tabindex="16" gridClass="col-lg-6" />
</div>

<div class="row">
<t:area path="terceroByCnPropietario.teDireccionSecundaria" label="Dirección secundaria" required="false" tabindex="18" gridClass="col-lg-6"/>
</div>

    
	<div class="control-group" style="clear: both">
		<div class="controls">
			<button type="submit" class="btn btn-primary">Guardar</button>
			<a href="action/portales/editar?id=${predio.portal.cnPortal}" class="btn btn-default">Cancelar</a>
			
				<a href="action/predios/imprimirPropietario?codPredio=${predio.cnPredio}" class="btn btn-default">Imprimir</a> 
				
			<!--  <button type="button" class="btn btn-default"
				onclick="changeAction('mainForm','action/predios/listado')">Cancelar</button>
				<a href="action/predios/imprimirPropietario?codPredio=${predio.cnPredio}" class="btn btn-default">Imprimir</a> 
				<a href="" style="text-decoration:none"
                 onclick='javascript:imprimirPDF("/algonz/action/comunidades/listado");return false;'
                 tabindex="1" id="impresoraPortadaSalida">
                 pdf
              </a>
              <a href="" onclick='javascript:imprimirEtiquetaSobre("/algonz/action/comunidades/listado");return false;' tabindex="1" id="enlaceEtiquetaSobre">Sobre
              </a>
			 -->
		</div>
	</div> 
    </div>
    <div id="conyuge" class="tab-pane fade">


<div class="row">
<t:input path="terceroByCnConyuge.caNif" label="NIF" required="false" maxlength="10" tabindex="19" gridClass="col-lg-4" />
</div>

<div class="row">
<t:input path="terceroByCnConyuge.teNombre" label="Nombre" required="false" maxlength="100" tabindex="20" gridClass="col-lg-4" />
<t:input path="terceroByCnConyuge.teApellido1" label="Primer apellido" required="false" maxlength="50" tabindex="21" gridClass="col-lg-4" />
<t:input path="terceroByCnConyuge.teApellido2" label="Segundo apellido" required="false" maxlength="50" tabindex="22" gridClass="col-lg-4" />
</div>

<div class="row">
<t:input path="terceroByCnConyuge.teTlfFijo" label="Teléfono" required="false" maxlength="50" tabindex="23" gridClass="col-lg-4" />
<t:input path="terceroByCnConyuge.teTlfMovil1" label="Móvil" required="false" maxlength="50" tabindex="24" gridClass="col-lg-4" />
<t:input path="terceroByCnConyuge.teTlfMovil2" label="Otro" required="false" maxlength="50" tabindex="25" gridClass="col-lg-4" />
</div>


<div class="row">
<t:input path="terceroByCnConyuge.teEmail" label="E-mail" required="false" maxlength="50" tabindex="26" gridClass="col-lg-6" />
</div>

<div class="row">
<t:input path="terceroByCnConyuge.nuCuentaCorriente" label="Cuenta corriente" required="false" maxlength="23" tabindex="27" gridClass="col-lg-6" />
</div>

<div class="row">
<t:area path="terceroByCnConyuge.teDireccionSecundaria" label="Dirección secundaria" required="false" tabindex="29" gridClass="col-lg-6"/>
</div>

	<div class="control-group" style="clear: both">
		<div class="controls">
			<button type="submit" class="btn btn-primary">Guardar</button>
			<a href="action/portales/editar?id=${predio.portal.cnPortal}" class="btn btn-default">Cancelar</a>	
				<a href="action/predios/imprimirConyuge?codPredio=${predio.cnPredio}" class="btn btn-default">Imprimir</a> 
		</div>
	</div>

    </div>
    <div id="inquilino" class="tab-pane fade">


<div class="row">
<t:input path="terceroByCnInquilino.caNif" label="NIF" required="false" maxlength="10" tabindex="30" gridClass="col-lg-4" />
</div>

<div class="row">
<t:input path="terceroByCnInquilino.teNombre" label="Nombre" required="false" maxlength="100" tabindex="31" gridClass="col-lg-4" />
<t:input path="terceroByCnInquilino.teApellido1" label="Primer apellido" required="false" maxlength="50" tabindex="32" gridClass="col-lg-4" />
<t:input path="terceroByCnInquilino.teApellido2" label="Segundo apellido" required="false" maxlength="50" tabindex="33" gridClass="col-lg-4" />
</div>

<div class="row">
<t:input path="terceroByCnInquilino.teTlfFijo" label="Teléfono" required="false" maxlength="50" tabindex="34" gridClass="col-lg-4" />
<t:input path="terceroByCnInquilino.teTlfMovil1" label="Móvil" required="false" maxlength="50" tabindex="35" gridClass="col-lg-4" />
<t:input path="terceroByCnInquilino.teTlfMovil2" label="Otro" required="false" maxlength="50" tabindex="36" gridClass="col-lg-4" />
</div>


<div class="row">
<t:input path="terceroByCnInquilino.teEmail" label="E-mail" required="false" maxlength="50" tabindex="37" gridClass="col-lg-6" />
</div>

<div class="row">
<t:input path="terceroByCnInquilino.nuCuentaCorriente" label="Cuenta corriente" maxlength="23" required="false" tabindex="38" gridClass="col-lg-6" />
</div>


	<div class="control-group" style="clear: both">
		<div class="controls">
			<button type="submit" class="btn btn-primary">Guardar</button>
			<a href="action/portales/editar?id=${predio.portal.cnPortal}" class="btn btn-default">Cancelar</a>
		</div>
	</div>

    </div>
   
  </div> 


</form:form>




<script>
function callback(direccion)
	{
		window.location = direccion;
	}


	/**
	 * Lanza el popup de imprimir PDF
	 *
	 * @param ruta
	 * @return
	 */
	function imprimirPDF(ruta){
		// desactivarPantalla('capa_transparente');
		ventana_hija =  window.open(ruta, '_blank', 'height=120,width=250,left=450,top=300,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=no,resizable=no,modal=yes')
	}
	
	
	function imprimirEtiquetaSobre(ruta){
		callback(ruta);
	}
	</script>
