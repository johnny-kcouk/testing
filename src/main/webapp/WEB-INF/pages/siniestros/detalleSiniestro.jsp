<%@ include file="/WEB-INF/pages/include.jsp"%>

<p class="bs-callout bs-callout-info" role="alert"> 
			<strong>Comunidad:</strong> ${siniestro.portal.comunidad.teNombre} 
			<br />
			<strong>Portal nº:</strong>	<c:if test='${siniestro.portal.teNombre!=null}'> ${siniestro.portal.teNombre}</c:if>
		
</p>

<div class="page-header">
	<h2>	
			<c:if test='${siniestro.cnSiniestro==null}'>Nuevo Siniestro</c:if>
			<c:if test='${siniestro.cnSiniestro!=null}'>Datos Siniestro: ${siniestro.teNombre}
			</c:if>	</h2>
</div>

 


<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="siniestro" method="POST" action="action/siniestros/guardar">
	
		<form:hidden path="cnSiniestro" />
		<form:hidden path="portal.cnPortal" />
		
		<div class="panel panel-default">
  	<div class="panel-body">
		
		<div class="row">

    
    	<t:select  id="tipoEmpresa" search="true"  gridClass="col-lg-4" itemLabel="teTipoEmpresa" itemValue="cnTipoEmpresa" items="${tiposEmpresaCombo}" path="cnTipoSiniestro" required="true" label="Tipo siniestro" emptyOption="true" tabindex="1" onchange="cargarEmpresasComunidad(${siniestro.portal.comunidad.cnComunidad },this.value)"/>
			
		<t:select  id="empresaComunidad" search="false" gridClass="col-lg-6" itemLabel="value" itemValue="id" items="${empresasComunidadCombo}" path="empresaComunidad.cnEmpresaComunidad" required="true" label="Empresa" emptyOption="true" tabindex="2"/>

		</div>

		<div class="row">
	
		<t:input gridClass="col-lg-12" path="teNombre" label="Descripción" required="true" maxlength="100" tabindex="3"/>
		</div>
		

<div class="row">
			<t:area gridClass="col-lg-12" path="teObservaciones" label="Observaciones" cols="500" tabindex="9"/>
		</div>
	</div>

	 <div class="panel-footer">
		
	
		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
				
				<c:if test="${empty siniestro.cnSiniestro }">	
				<button type="button" class="btn btn-default"
					onclick="changeAction('mainForm','action/siniestros/guardarToActuacion')">Nueva actuación</button>
				</c:if>	
				<a href="action/portales/editar?id=${siniestro.portal.cnPortal}" class="btn btn-default">Cancelar</a>
			
			</div>
		</div>
	</div>

</div>
	

	


	


	<c:if test="${not empty siniestro.cnSiniestro }">	
	
	<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Listado de actuaciones</h3>
	</div>

  	<div class="panel-body">
				
<c:if test="${siniestro.actuaciones != null && empty siniestro.actuaciones}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty siniestro.actuaciones}">

	<table id="tablaPaginada" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th class="dateColumn">Fecha Inicio</th>
				<th class="dateColumn">Fecha Vencimiento</th>
				<th class="dateColumn">Fecha Cierre</th>
				<th>Estado </th>
				<th>Descripción</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${siniestro.actuaciones}" var="actuacion" varStatus="status">
				<tr>
				
					<td><fmt:formatDate value="${actuacion.feInicio}" pattern="dd/MM/yyyy"/></td>			
					<td><fmt:formatDate value="${actuacion.feVencimiento}" pattern="dd/MM/yyyy"/></td>
					<td><fmt:formatDate value="${actuacion.feCierre}" pattern="dd/MM/yyyy"/></td>
					<td>
						${actuacion.estado.teEstado}				
					</td>
					<td>${actuacion.teDescripcion}</td>
					<td>
						<a href="action/actuaciones/editar?id=${actuacion.cnActuacion }">
							<i class="glyphicon glyphicon-edit"  title="Consultar"></i></a> &nbsp;
						<a data-toggle="modal" href="#modalDeleteGET" class="delete_row" data-id="action/actuaciones/eliminar?id=${actuacion.cnActuacion }">
							<i class="glyphicon glyphicon-remove"  title="Eliminar"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
	</div>

	 <div class="panel-footer">
			<a href="action/actuaciones/nuevo?codSiniestro=${siniestro.cnSiniestro}" class="btn btn-primary"> <i class="glyphicon glyphicon-plus glyphicon-white"></i> <span>Nueva actuación</span></a>
	</div>

</div>
	
	</c:if>
	

</form:form>



<script type="text/javascript">

function cargarEmpresasComunidad(codComunidad, idTipoEmpresa) {
	jQuery.ajax({
		type : "POST",
		url : "/algonz/action/siniestros/cargarEmpresasComunidad",
		data : {"codComunidad":codComunidad, "idTipoEmpresa":idTipoEmpresa},
		success : function(response) {
			while (document.getElementById("empresaComunidad").options.length > 0) {
				document.getElementById("empresaComunidad").options.remove(0);
			}


			document.getElementById("empresaComunidad").options.add(new Option("Seleccionar...",
					""))

			for ( var i = 0; i < response.length; i++) {
				var d = response[i];
				document.getElementById("empresaComunidad").options.add(new Option(d.value, d.id))
			}


			$("#empresaComunidad").select2("val", ""); 

		}
	});
}

</script>




