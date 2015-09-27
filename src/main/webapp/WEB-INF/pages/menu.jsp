<%@ include file="/WEB-INF/pages/include.jsp" %>

 
	<ul id="menu" class="nav nav-sidebar">
      													
<sec:authorize access="hasRole('ROLE_ADMIN')">
				
								<li><a href=""> <em class="fa fa-pencil-square"></em> Administración <span class="fa fa-chevron-down pull-right vert-offset-top-1-5"></span></a>
									<ul>
										<li><a href="action/admin/usuario/listado">Usuarios</a></li>		
						<!-- <li><a href="action/siniestros/listado">Siniestros</a></li>		
						<li><a href="action/portales/listado">Portales</a></li>	-->	
									</ul>
								</li>
								</sec:authorize>
								
								<li class="active"><a href=""> <em class="fa fa-pencil-square"></em> Gestión <span class="fa fa-chevron-down pull-right vert-offset-top-1-5"></span></a>
									<ul>
										<li><a href="action/alarmas/listado">Alarmas</a></li>	
										<li><a href="action/comunidades/listado">Comunidades</a></li>	
										<li><a href="action/empresas/listado">Empresas</a></li>	
									</ul>
								</li>							
</ul>
					
					
					