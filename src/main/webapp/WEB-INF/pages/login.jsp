<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ taglib uri="http://efsavage.com/twitter-bootstrap" prefix="bs"%>


<body onload='document.signinForm.j_username.focus();'>

	<div class="container">
		<div class="row">
			<div class="col-xs-6  col-sm-6 col-md-5 col-xs-offset-3 col-sm-offset-3 col-md-offset-4 ">
				<div class="panel panel-dark">
					<!-- <div class="panel-heading page-icon">
						<div class="row">
							<div class="text-center ">
								<a href="#">
								<img src="" alt="">
								</a>
							</div>	
						</div>	
					</div> -->
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12 col-md-12  col-sm-12 col-xs-12 text-center">					
								<h2 class="blue-dark">  <strong>Plataforma AlGonz 2.0</strong></h2>
								
								<h3><em><i class="fa fa-lock"></i> Log in </em></h3>
							
								<form  id="signinForm" class="form-horizontal col-sm-12 " action="<c:url value='j_spring_security_check'/>" method="POST" role="form">
				
							<c:if test="${not empty error }">
								<p class="alert alert-danger  well-sm" role="alert"> ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
							</c:if>
								
	                        
	                        <div class="form-group">
	                        
	                            <label for="j_username" class="control-label"></label>
	                            <div class="col-sm-12 ">
		                            <div class="input-group">
										<span class="input-group-addon">
											<i class="fa fa-user"></i>
										</span>
			                               	 <input id="j_username" type='text'  class="form-control" required autofocus  name='j_username' 
			                                value='<c:if test="${not empty messError}" ><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' 
			                                placeholder="Usuario"/>
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
									<input id="password" class="form-control" type='password' name='j_password' placeholder="Password" required />
				                </div>
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

	

