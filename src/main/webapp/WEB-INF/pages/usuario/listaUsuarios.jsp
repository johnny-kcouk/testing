<%@ include file="/WEB-INF/pages/include.jsp"%>

<div class="page-header">
	<h2>Usuarios</h2>
</div>
		
<div class="panel panel-default">
  	<div class="panel-body">

<c:if test="${listaUsuarios != null && empty listaUsuarios}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty listaUsuarios}">

	<table id="tablaPaginada" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Username</th>
				<th>Nombre</th>
				<th>Email</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listaUsuarios}" var="usuario" varStatus="status">
				<tr>
				
					<td>${usuario.idSistema}</td>
					<td>${usuario.nombreCompleto}</td>
					<td>${usuario.teEmail}</td>
					<td>
						<a href="action/admin/usuario/editar?id=${usuario.idUsuario }">
							<i class="glyphicon glyphicon-edit"  title="Consultar"></i></a> &nbsp;
					<!-- 	
						<a data-toggle="modal" href="#modalDeleteGET" class="delete_row" data-id="action/admin/usuario/eliminar?id=${usuario.idUsuario }">
							<i class="glyphicon glyphicon-remove"  title="Eliminar"></i></a>
							 -->
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
  	
		
	</div>

	 <div class="panel-footer">
			<a href="action/admin/usuario/nuevo" class="btn btn-primary">Nuevo usuario</a>
	</div>

</div>		
		
