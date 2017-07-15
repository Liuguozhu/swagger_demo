function loadright(url){
	$.ajax({
        type: "post",
        url :url,
        success: function(r){
        	
        	if(r){
        		$('#loading').hide();
        	}
        	
            $('#content').html(r);
        }
    });
}
function deletes(url,data){
	dialogConfirm({
		title:'确认',
		msg:'删除数据可能会影响其他设置',
		onOk:function(){
			if(data){
				loadrightbydata(url,data);
	        }else{
	        	loadright(url);
	        }
		}
	
	});
}
function edits(url){
	loadright(url);
}
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


function loadrightbydata(url,data){
	$.ajax({
        type: "post",
        url :url,
        data:data,
        success: function(responsetext){
        	if(responsetext){
        		$('#loading').hide();
        	}
            $('#content').html(responsetext);
        }
    });
}



function loaddata(url) {
	/*fromStart=$('#start').val();
	toEnd= $('#end').val();*/
	$.ajax({
		type : "post",
		url : url,
		data : {},
		success : function(responsetext) {
			 $('#content').html(responsetext);
		}
	});  	
}

function isEmpty(value) {
    if (value == null){
    	return true;
    }	
    if ((value + "").trim().length == 0){
    	return true;
    }
    return false;
}