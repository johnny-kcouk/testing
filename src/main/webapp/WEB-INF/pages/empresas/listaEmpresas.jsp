<%@ include file="/WEB-INF/pages/include.jsp"%>

<div class="page-header">
	<h2>Empresas</h2>
</div>

		
<div class="panel panel-default">
  	<div class="panel-body">
		
<c:if test="${listaEmpresas != null && empty listaEmpresas}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty listaEmpresas}">

	<table id="tablaPaginada" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Tipo</th>
				<th>CIF</th>
				<th>Nombre</th>
				<th>Teléfono</th>
				<th>E-mail</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listaEmpresas}" var="empresa" varStatus="status">
				<tr>
					<td>${empresa.tipoEmpresa.teTipoEmpresa}</td>
					<td>${empresa.caCif}</td>
					<td>${empresa.teNombre}</td>
					<td>
						${empresa.telefonos}
						
					</td>
					<td><a href="mailto:${empresa.teEmail}">${empresa.teEmail}</a></td>
					<td>
						<a href="action/empresas/editar?id=${empresa.cnEmpresa }">
							<i class="glyphicon glyphicon-edit"  title="Consultar"></i></a> &nbsp;
						<a data-toggle="modal" href="#modalDeleteGET" class="delete_row" data-id="action/empresas/eliminar?id=${empresa.cnEmpresa }">
							<i class="glyphicon glyphicon-remove"  title="Eliminar"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
		
	</div>

	 <div class="panel-footer">
			<a href="action/empresas/nuevo" class="btn btn-primary">Nueva empresa</a>
	</div>

</div>