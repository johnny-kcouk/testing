<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>

<head>
	<tiles:insertAttribute name="head"  />
	
	<title>AlGonz 2.0</title>


<link href="css/application.css" rel="stylesheet" media="screen"/>  
</head>
  
<body>

<tiles:insertAttribute name="header" />

	<div class="container-fluid">
	
		<div id="mainMenu" class="hidden-xs hidden-sm sidebar">
		
			<tiles:insertAttribute name="menu" />
			
		</div>
		<div id="mainContent" class="main">
			<div class="row">
				<tiles:insertAttribute name="messages" />			
			</div>
			<div class="row">
				<tiles:insertAttribute name="content" />
			</div>
		</div>
	
	</div>

</body>

<footer>
	<tiles:insertAttribute name="footer" />
</footer>

<c:import url="/WEB-INF/pages/modal.jsp"/>

</html>