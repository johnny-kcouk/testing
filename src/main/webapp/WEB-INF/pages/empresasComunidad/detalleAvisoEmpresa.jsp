<%@ include file="/WEB-INF/pages/include.jsp"%>


<p class="bs-callout bs-callout-info" role="alert"> 
		<strong>Comunidad:</strong>  ${avisoEmpresa.empresaComunidad.comunidad.teNombre} 
		<br />
		<strong>Empresa:</strong> ${avisoEmpresa.empresaComunidad.empresa.teNombre} (${avisoEmpresa.empresaComunidad.empresa.telefonos})
</p>

<div class="page-header">
	<h2>
			<c:if test='${avisoEmpresa.cnAvisoEmpresa==null}'>Nuevo aviso</c:if>
			<c:if test='${avisoEmpresa.cnAvisoEmpresa!=null}'>Datos del aviso
			</c:if></h2>
</div>

<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="avisoEmpresa" method="POST" action="action/avisosEmpresa/guardar">
	
		
		<form:hidden path="cnAvisoEmpresa" />
		<form:hidden path="empresaComunidad.cnEmpresaComunidad" />
	
<div class="panel panel-default">
  	<div class="panel-body">
		<div class="row">	
			<t:select  id="estado" search="false"  gridClass="col-lg-4" itemLabel="teEstado" itemValue="cnEstado" items="${estadosCombo}" path="estado.cnEstado" required="true" label="Estado" emptyOption="true" tabindex="1"/>
		</div>

		<div class="row">	
			<t:input gridClass="col-lg-4" path="feInicio" label="Fecha inicio" required="true" tabindex="2" maxlength="12" date="true"/>
			<t:input gridClass="col-lg-4" path="feVencimiento" label="Fecha vencimiento" required="true" maxlength="12" tabindex="3" date="true"/>
			<t:input gridClass="col-lg-4" path="feCierre" label="Fecha cierre" required="false" tabindex="4" maxlength="12" date="true"/>			
		</div>		
			
			
			
		<div class="row">	
		
		<!-- 
			<div class="col-lg-4">			
			<div class="form-group ${status.error ? 'error' : '' }">
				<label class="control-label" for="nuDiasAviso">Días para aviso</label>
				<div class="controls">
				  <script>
		       			 $(document).ready(function() { $("#nuDiasAviso").select2({minimumResultsForSearch: -1}); });
		    		</script>    
					<form:select path="nuDiasAviso" cssClass="form-control input-xlarge"
						id="nuDiasAviso" tabindex="5">
						<form:option value="" label="Seleccionar..." />
						<form:option value="2" label="2" />
						<form:option value="6" label="6" />
						<form:option value="11" label="11" />
						<form:option value="15" label="15" />
						<form:option value="30" label="30" />
						<form:option value="60" label="60" />
						<form:option value="75" label="75" />
					</form:select>

					<c:if test="${status.error}">
						<span class="help-inline">${status.errorMessage}</span>
					</c:if>
				</div>
			</div>
			</div 
			-->
			<t:input gridClass="col-lg-4" path="nuDiasAviso" label="Días para aviso" required="false" maxlength="3" tabindex="6"/>
   
			<t:input gridClass="col-lg-8" path="teDescripcion" label="Descripción" required="true" maxlength="100" tabindex="6"/>
		</div>
			
		<div class="row">	
			<t:area gridClass="col-lg-12" path="teObservaciones" label="Observaciones" required="false" tabindex="7"/>
    	</div>
	
	</div>

	 <div class="panel-footer">
		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
					<a href="action/empresasComunidad/editar?id=${avisoEmpresa.empresaComunidad.cnEmpresaComunidad}" class="btn btn-default">Cancelar</a>
					
				
		<c:if test="${not empty avisoEmpresa.cnAvisoEmpresa }">		
			 <a href="action/avisosEmpresa/informeAviso?codAvisoEmpresa=${avisoEmpresa.cnAvisoEmpresa }" class="btn btn-default">Generar informe</a> 
			 </c:if>
			</div>
		</div>
	</div>

</div>
	
	
	

	
	<c:if test="${not empty avisoEmpresa.cnAvisoEmpresa }">		
		
<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Documentos</h3>
	</div>

  	<div class="panel-body">
		<c:if test="${avisoEmpresa.documentos != null && empty avisoEmpresa.documentos}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty avisoEmpresa.documentos}">

	
	<table id="tablaPaginada4" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Nombre</th>
				<th class="dateColumn">Fecha</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${avisoEmpresa.documentos}" var="documento" varStatus="status">
				<tr>
				
					<td>${documento.teNombre}</td>
					<td><fmt:formatDate value="${documento.feGuardado}" pattern="dd/MM/yyyy"/></td>
					<td>
						<a href="action/avisosEmpresa/downloadDocument?id=${documento.cnDocumento }">
							<i class="glyphicon glyphicon-download"  title="Descargar"></i></a> &nbsp;
							<a data-toggle="modal" href="#modalDeleteGET" class="delete_row" data-id="action/avisosEmpresa/deleteDocument?codAvisoEmpresa=${avisoEmpresa.cnAvisoEmpresa }&amp;id=${documento.cnDocumento }">
							<i class="glyphicon glyphicon-remove"  title="Eliminar"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>


    <div id="progress" class="progress"  style="display:none">
        <div class="progress-bar progress-bar-success"></div>
    </div>
	</div>

	 <div class="panel-footer">
		<div>
<span class="btn btn-primary fileinput-button">
                    <i class="glyphicon glyphicon-plus glyphicon-white"></i>
                    <span>Añadir documento</span>
                   <input id="fileupload" type="file" name="files[]" data-url="action/avisosEmpresa/uploadDocument?codAvisoEmpresa=${avisoEmpresa.cnAvisoEmpresa }" multiple>
                </span>
	</div>
	</div>

</div>
		
</c:if>
		
</form:form>







