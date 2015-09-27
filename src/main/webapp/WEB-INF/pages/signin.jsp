<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ taglib uri="http://efsavage.com/twitter-bootstrap" prefix="bs"%>


<body onload='document.f.j_username.focus();'>

	<div class="container">
		<div class="row">
			<div class="col-xs-6  col-sm-6 col-md-5 col-xs-offset-3 col-sm-offset-3 col-md-offset-4 ">
				<div class="panel panel-dark">
					<div class="panel-heading page-icon">
						<div class="row">
							<div class="text-center ">
								<a href="signin.htm">
								<img src="resources/imagesBootstrap/systemHeader/logo-signin.png" alt="">
								</a>
							</div>	
						</div>	
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12 col-md-12  col-sm-12 col-xs-12 text-center">					
								<h2 class="blue-dark"> <i class="fa fa-lock"></i> <strong>Log in </strong></h2>
								
								<p class="">Plataforma AlGonz 2.0</em></p>
							
								<form  id="signinForm" class="form-horizontal col-sm-12 " action="<c:url value='j_spring_security_check'/>" method="POST" role="form">
				
							<c:if test="${not empty error }">
								<p class="alert alert-danger  well-sm" role="alert"> ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
							</c:if>
								
	                        
	                        <div class="form-group">
	                        
	                            <label for="username" class="control-label"></label>
	                            <div class="col-sm-12 ">
		                            <div class="input-group">
										<span class="input-group-addon">
											<i class="fa fa-user"></i>
										</span>
			                               	 <input id="username" type='email'  class="form-control" required autofocus  name='j_username' 
			                                value='<c:if test="${not empty messError}" ><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' 
			                                placeholder="<fmt:message key='signin.username' />"/>
	            	            	 </div>
	            	              </div>
	                                                                                   
	                        </div>
	                        <div class="form-group">
	                            <label for="password" class="control-label"></label>
	                            <div class="col-sm-12 ">
	                              <div class="input-group ">
										<span class="input-group-addon">
											<i class="fa fa-lock"></i>
										</span>
									<input id="password" class="form-control" type='password' name='j_password' placeholder="<fmt:message key='signin.password' />" required />
				                </div>
				             <h6 class="text-left"><a href='<c:out value="restorePassword.htm"/>'><fmt:message key="signin.forgotPassword"/></a></h6>
				                </div>
				                
	                        </div>
	                          
	                       
	                       
	                        
	      <div class="form-group">
			<label class="control-label" for="j_username">Usuario</label>
			<div class="controls">
				<input type='text' name='j_username' value='' id="j_username"
					placeholder="Usuario" class="form-control input-block-level">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label" for="j_password">Password</label>
			<div class="controls">
				<input type="password" name="j_password" id="j_password"
					placeholder="Password" class="form-control input-block-level">
			</div>
		</div>
		<div class="form-group">
			<div class="controls">

				<bs:button type="submit" value="Entrar" text="Entrar"
					cssClass="btn btn-primary" />
				<bs:button type="reset" value="Limpiar" text="Limpiar" />
			</div>
		</div>
	                                           
	                    </form>
	                    
			       
							</div>	
							</div>	
						</div>	
					</div>
			</div>
	</div>
</div>

	

