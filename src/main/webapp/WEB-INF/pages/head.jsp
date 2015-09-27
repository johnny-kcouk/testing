<%@ include file="/WEB-INF/pages/include.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1">
<base href='<c:out value="${pageContext.request.scheme}" />://<c:out value="${pageContext.request.serverName}" />:<c:out value="${pageContext.request.serverPort}" /><c:out value="${pageContext.request.contextPath}" />/'/>


<!-- Bootstrap Latest compiled and minified CSS -->
<link rel="stylesheet" href="bootstrap-3.3.1/css/bootstrap.min.css">

<!-- Bootstrap Optional theme -->
<link rel="stylesheet" href="bootstrap-3.3.1/css/bootstrap-theme.min.css">

<!-- Google Font -->
<link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>

<!-- jQuey 1.11.2 -->
<script src="js/jquery-1.11.2.min.js"></script>

<!-- jQuey UI 1.11.2 -->
<script src="js/jquery-ui.min.js"></script>

<!-- Bootstrap Latest compiled and minified JavaScript -->
<script src="bootstrap-3.3.1/js/bootstrap.min.js"></script>

<!-- Data Tables 1.10.3 -->
<script type="text/javascript" charset="utf8" src="DataTables-1.10.3/media/js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.bootstrap.js"></script>
<script type="text/javascript" charset="utf8" src="DataTables-1.10.3/extensions/TableTools/js/dataTables.tableTools.min.js"></script>
 
<%-- We use the bootstrap data tables css 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/DataTables-1.10.3/media/css/jquery.dataTables.min.css" />  --%>
<link rel="stylesheet" href="https://cdn.datatables.net/plug-ins/380cb78f450/integration/bootstrap/3/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css" href="DataTables-1.10.3/extensions/TableTools/css/dataTables.tableTools.min.css" />


<script type="text/javascript" charset="utf8" src="DataTables-1.10.3/extensions/ColVis/js/dataTables.colVis.min.js"></script>
<link rel="stylesheet" type="text/css" href="DataTables-1.10.3/extensions/ColVis/css/dataTables.colVis.min.css" />


<!-- Data Tables ColumnFilter 1.5.6 -->
<script type="text/javascript" charset="utf8" src="js/jquery.dataTables.columnFilter.js"></script>

<!-- Font Awesome 4.2.0 -->
<link href="font-awesome-4.2.0/css/font-awesome.min.css" rel="stylesheet">


<link href="css/datepicker.css" rel="stylesheet" media="screen"/> 
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/locales/bootstrap-datepicker.es.js"></script>
		
<!-- Select2 3.5.1-->
<script type="text/javascript" charset="utf8" src="select2-3.5.2/select2.js"></script>
<link rel="stylesheet" type="text/css" href="select2-3.5.2/select2.css" />
<link rel="stylesheet" type="text/css" href="select2-3.5.2/select2-bootstrap.css" /> 

<!-- Bootstrap Validator 0.5.3
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"/>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
-->	

<!-- MetisMenu -->	
<link rel="stylesheet" href="css/metisMenu.css">
<script src="js/metisMenu.js"></script>


<!-- Tiny MCE 4.1.6-->
<script type="text/javascript" charset="utf8" src="tinymce/tinymce.min.js"></script>	


<link rel="stylesheet" type="text/css" href="css/DT_bootstrap.css">

<script src="js/DT_bootstrap.js"></script>
<script src="js/application.js"></script>


<!-- file uploader -->
<script src="js/vendor/jquery.ui.widget.js"></script>
<script src="js/jquery.iframe-transport.js"></script>
<script src="js/jquery.fileupload.js"></script>
<link href="css/jquery.fileupload-ui.css" type="text/css" rel="stylesheet" />
<!-- we code these -->
<link href="css/dropzone.css" type="text/css" rel="stylesheet" />
<script src="js/myuploadfunction.js"></script>

<!-- Place inside the <head> of your HTML -->
<script type="text/javascript">
tinymce.init({
    selector: "textarea#teObservaciones",
/* language: "es", */
     theme: "modern",
    menubar: false,
    plugins: [
        "textcolor",
       "paste"
    ],
    toolbar1: "bold forecolor backcolor",
    image_advtab: true,
    oninit : "setPlainText"
 });
</script>
