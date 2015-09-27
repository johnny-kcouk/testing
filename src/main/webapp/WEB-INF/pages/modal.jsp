
  <div class="modal fade NonPrintables" id="modalDeleteGET">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">Confirm Delete</h4>
        </div>
        <div class="modal-body">
        	<p>Está a punto de borrar el registro seleccionado. Este proceso es irreversible</p> 
        	<p>¿Desea continuar?</p>     
                        
	 		<span id="delete-url">
	 		<span class="debug-url">
            
        </div>
        <div class="modal-footer">
          	<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
             <a href="#" class="btn btn-danger danger">Eliminar</a>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->



  <!-- Modal  confirmar eliminar-->
  <div class="modal fade NonPrintables" id="confirmDeletePOST">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">Confirmar</h4>
        </div>
        <div class="modal-body">
        	
        	<p>Está a punto de borrar el registro seleccionado. Este proceso es irreversible</p> 
        	<p>¿Desea continuar?</p>          
            
	 		<span id="delete-url">
	 		<span class="debug-url">
            
        </div>
        <div class="modal-footer">
          	<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
            <button type="button" data-dismiss="modal" class="btn btn-danger danger" id="delete">Eliminar</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->

<script>

 $('#modalDeleteGET').on('show.bs.modal', function(e) {
       $(this).find('.danger').attr('href', $(e.relatedTarget).attr('data-id')); 
       /*  $('#delete-url').html('<a href="' +  $(e.relatedTarget).attr('data-id') + '" class="btn btn-primary danger">Delete</a>');
      $('.debug-url').html('Delete URL: <strong>' + $(this).find('.danger').attr('href') + '</strong>'); */
  });


$('.modalDeletePOST').click('show.bs.modal', function (e) {	

    var $form=$(this).closest('form');
    e.preventDefault();
    $('#confirmDeletePOST').modal({ keyboard: false }).one('click', '.danger', function (e) {
            $form.trigger('submit');
        });
        
});

</script>