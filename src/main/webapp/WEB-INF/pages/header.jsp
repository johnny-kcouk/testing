<%@ include file="/WEB-INF/pages/include.jsp" %>

<script type="text/javascript">
$(document).ready(function(){

	$("#toggle-sidebar").click(function(){
	    $("#mainMenu").toggleClass("hide");
	     $("#mainContent").toggleClass("full-page");
	      return false;
	});
	
	$("#toggle-sidebar-xs").click(function(){
		$("#mainMenu").toggleClass("hidden-sm");
	     return false;
	});
});
</script>


<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header visible-lg-block  visible-md-block hidden-xs hidden-sm">			
			<a class="navbar-brand " href="#"> 
						ALGONZ
			</a>			
		</div>
		<div class="navbar-header-small visible-xs-block  visible-sm-block hidden-mg hidden-lg">			
			<a class="navbar-brand " href="#"> 
				ALGONZ
			</a>
		</div>
		
			<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li id="toggle-sidebar-li" class="hidden-xs hidden-sm">
                <a href="#" id="toggle-sidebar"><em class="fa fa-step-backward fa-18px"></em></a>
            </li>
            <li id="toggle-sidebar-li-xs" class="hidden-md hidden-lg">
            	<a href="#" id="toggle-sidebar-xs"><em class="fa fa-step-backward fa-18px"></em></a>
            </li>
                
			
		</ul>
		
		<ul class="nav navbar-nav navbar-right">
		

		<li class="dropdown dropdown-info">
				 <a id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" role="button" aria-expanded="false">
			<em class="fa fa-user fa-18px"></em> </b>
			</a>
			<div class="dropdown-menu navbar-message orange" aria-labelledby="drop3" role="menu">
					
							<%-- <div class="dropdown-header">
									<span class="orange-title"><h4> <em class=" icon-info3"></em>  <fmt:message key="header.information" /></h4></span>
							</div> --%>
						<div class="dropdown-body">
							<div class="niceScroll" style="overflow: hidden;" tabindex="5000">
								<ul>								
									<li  class="media"><a style="padding-left: 20px;" href="<c:url value="action/usuario/editar?id=${usuario_ses.idUsuario }" />">Preferencias</a></li>
								</ul>
							</div>					
						</div>
						 <div class="dropdown-footer"><p class="pull-right"><em class="fa fa-sign-out"></em> 	<a  href="<c:url value="/j_spring_security_logout" />">Salir</a></p> <p> ${usuario_ses.idSistema }</p></div>
				
					</div>
		</li>
		
			
		<%-- 	
			<li class="logout">
				<a  href="<c:url value="/j_spring_security_logout" />">
					<em class="fa fa-sign-out"></em>
				</a>
			</li> --%>
		</ul>
		

	</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>

