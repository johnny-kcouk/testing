<%@ include file="/WEB-INF/pages/include.jsp"%>


<p class="bs-callout bs-callout-info" role="alert"> 
			<strong>Comunidad:</strong>  ${portal.comunidad.teNombre}
</p>

<div class="page-header">
	<h2>
			<c:if test='${portal.cnPortal==null}'>Nuevo Portal</c:if>
			<c:if test='${portal.cnPortal!=null}'>
			Portal Nº: ${portal.teNombre}
			</c:if></h2>
</div>

<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="portal" method="POST" action="action/portales/guardar">
	
	
		<form:hidden path="cnPortal" />
		<form:hidden path="comunidad.cnComunidad" />
		
		<div class="panel panel-default">
  	<div class="panel-body">
		
<div class="row">
<t:input path="teCalle" label="Calle" required="true" maxlength="100" tabindex="1" gridClass="col-lg-6"/>
<t:input path="teNombre" label="Número" required="true" maxlength="100" tabindex="2" gridClass="col-lg-3"/>
</div>

<div class="row">
<t:area path="teObservaciones" label="Observaciones" required="false" tabindex="3" gridClass="col-lg-12"/>
</div>
		
	</div>

	 <div class="panel-footer">
		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
				<!-- <button type="button" class="btn btn-default" onclick="changeAction('mainForm','action/comunidades/editar?id=${portal.comunidad.cnComunidad}')">Cancelar</button>  -->
				<a href="action/comunidades/editar?id=${portal.comunidad.cnComunidad}" class="btn btn-default">Cancelar</a>
				
				<a href="action/portales/imprimirSobresPortal?codPortal=${portal.cnPortal}" class="btn btn-default">Imprimir Sobres</a> 
				
				<a href="action/portales/imprimirResumenSobres?codPortal=${portal.cnPortal}" class="btn btn-default">Resumen Sobres</a> 
				<!--
					<a href="mailto:angeles@algonz.es?bcc=${portal.emailsPortal}&subject=Acta reunión" class="btn btn-default"> Enviar mails</a> 
					-->
			</div>
		</div>
	</div>

</div>
	

		
	<c:if test="${not empty portal.cnPortal }">	
		
		
		
	
	
	<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Predios</h3>
	</div>

  	<div class="panel-body">
				
<c:if test="${portal.predios != null && empty portal.predios}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty portal.predios}">

	<table id="tablaPaginada" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Tipo</th>
				<th>Predio</th>
				<th>Propietario</th>
				<th>Teléfono</th>
				<th>E-mail</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${portal.predios}" var="predio" varStatus="status">
				<tr>
					<td>${predio.tipoPredio.teTipoPredio}</td>
					<td>${predio.planta.tePlanta} - ${predio.tePredio}</td>
					<td>${predio.terceroByCnPropietario.teNombre} ${predio.terceroByCnPropietario.teApellido1} ${predio.terceroByCnPropietario.teApellido2}</td>
					<td>
						${predio.terceroByCnPropietario.teTlfFijo}
						<c:if test="${not empty predio.terceroByCnPropietario.teTlfFijo and not empty predio.terceroByCnPropietario.teTlfMovil1}">/</c:if>
						${predio.terceroByCnPropietario.teTlfMovil1}
						<c:if test="${not empty predio.terceroByCnPropietario.teTlfMovil1 and not empty predio.terceroByCnPropietario.teTlfMovil2}">/</c:if>						
						${predio.terceroByCnPropietario.teTlfMovil2}
						
					</td>
					<td><a href="mailto:${predio.terceroByCnPropietario.teEmail}">${predio.terceroByCnPropietario.teEmail}</a></td>
					<td>
						<a href="action/predios/editar?id=${predio.cnPredio }">
							<i class="glyphicon glyphicon-edit"  title="Consultar"></i></a> &nbsp;
						<a data-toggle="modal" href="#modalDeleteGET" class="delete_row" data-id="action/predios/eliminar?id=${predio.cnPredio }">
							<i class="glyphicon glyphicon-remove"  title="Eliminar"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
	</div>

	 <div class="panel-footer">
			<a href="action/predios/nuevoPredio?codPortal=${portal.cnPortal}" class="btn btn-primary"> <i class="glyphicon glyphicon-plus glyphicon-white"></i> <span>Nuevo predio</span></a>
	</div>

</div>
			
			
<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Siniestros</h3>
	</div>

  	<div class="panel-body">
		
<c:if test="${portal.siniestros != null && empty portal.siniestros}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty portal.siniestros}">

	<table id="tablaPaginada2" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Tipo</th>
				<th>Empresa</th>
				<th>Descripción</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${portal.siniestros}" var="siniestro" varStatus="status">
				<tr>
					<td>${siniestro.empresaComunidad.empresa.tipoEmpresa.teTipoEmpresa}</td>
					<td>${siniestro.empresaComunidad.empresa.teNombre}</td>
					<td>${siniestro.teNombre}</td>
					<td>
						<a href="action/siniestros/editar?id=${siniestro.cnSiniestro }">
							<i class="glyphicon glyphicon-edit"  title="Consultar"></i></a> &nbsp;
						<a data-toggle="modal" href="#modalDeleteGET" class="delete_row" data-id="action/siniestros/eliminar?id=${siniestro.cnSiniestro }">
							<i class="glyphicon glyphicon-remove"  title="Eliminar"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
	</div>

	 <div class="panel-footer">
			<a href="action/siniestros/nuevoSiniestro?codPortal=${portal.cnPortal }" class="btn btn-primary"><i class="glyphicon glyphicon-plus glyphicon-white"></i> <span>Nuevo siniestro</span></a>
	</div>

</div>
		
		</c:if>
		
		

</form:form>



