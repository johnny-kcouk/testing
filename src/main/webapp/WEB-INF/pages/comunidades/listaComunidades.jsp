<%@ include file="/WEB-INF/pages/include.jsp"%>

<div class="page-header">
	<h2>Comunidades</h2>
</div>
		
		<div role="tabpanel">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#misComunidades" aria-controls="misComunidades" role="tab" data-toggle="tab">Mis Comunidades</a></li>
    <li role="presentation"><a href="#todasComunidades" aria-controls="todasComunidades" role="tab" data-toggle="tab">Todas las Comunidades</a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane fade in active" id="misComunidades">
    

		

<c:if test="${listaComunidadesUsuario != null && empty listaComunidadesUsuario}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty listaComunidadesUsuario}">

	<table id="tablaPaginada" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>CIF</th>
				<th>Nombre</th>
				<th>Código postal</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listaComunidadesUsuario}" var="comunidad" varStatus="status">
				<tr>
				
					<td>${comunidad.caCif}</td>
					<td>${comunidad.teNombre}</td>
					<td>${comunidad.teCp}</td>
					<td>
						<a href="action/comunidades/editar?id=${comunidad.cnComunidad }">
							<i class="glyphicon glyphicon-edit"  title="Consultar"></i></a> &nbsp;
						<a data-toggle="modal" href="#modalDeleteGET" class="delete_row" data-id="action/comunidades/eliminar?id=${comunidad.cnComunidad }">
							<i class="glyphicon glyphicon-remove"  title="Eliminar"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
    
    </div>
    <div role="tabpanel" class="tab-pane fade in" id="todasComunidades">
    <c:if test="${listaComunidades != null && empty listaComunidades}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty listaComunidades}">

	<table id="tablaPaginada2" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>CIF</th>
				<th>Nombre</th>
				<th>Código postal</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listaComunidades}" var="comunidad" varStatus="status">
				<tr>
				
					<td>${comunidad.caCif}</td>
					<td>${comunidad.teNombre}</td>
					<td>${comunidad.teCp}</td>
					<td>
						<a href="action/comunidades/editar?id=${comunidad.cnComunidad }">
							<i class="glyphicon glyphicon-edit"  title="Consultar"></i></a> &nbsp;
						<a data-toggle="modal" href="#modalDeleteGET" class="delete_row" data-id="action/comunidades/eliminar?id=${comunidad.cnComunidad }">
							<i class="glyphicon glyphicon-remove"  title="Eliminar"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
    </div>
  </div>

</div>
		
<br/>


<div>
	<a href="action/comunidades/nuevo" class="btn btn-primary">Nueva comunidad</a>
</div>




<script>
	$(function() {
		/* $('#tabMenuComunidades a:first').tab('show'); */
	})
</script>