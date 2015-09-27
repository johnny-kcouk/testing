$(function () {
    $('#fileupload').fileupload({
        dataType: 'json',
 
        done: function (e, data) {
        	location.reload();
            
        },
 
        progressall: function (e, data) {
        	
        	$('#progress').css('display','');

            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .progress-bar').css(
                'width',
                progress + '%'
            );
        },
 
        dropZone: $('#dropzone')
    });
});