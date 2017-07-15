function getFormParamStr(formId){
	var searchFormE = document.getElementById(formId);
	var paramStr = '';
	for(var i=0;i < searchFormE.elements.length;i++){
		var fieldE = searchFormE.elements[i];
		if(fieldE.name && ''!=fieldE.name){
			if(fieldE.type=='select-multiple'){
				for(var j=0;j<fieldE.options.length;j++){
					var optionE = fieldE.options[j];
					if(optionE.selected){
						paramStr = paramStr + '&' + fieldE.name + '=' + encodeURIComponent(optionE.value);
					}
				}
			}else if(fieldE.type=='checkbox' || fieldE.type=='radio'){
				if(fieldE.checked){
					paramStr = paramStr + '&' + fieldE.name + '=' + encodeURIComponent(fieldE.value);
				}
			}else{
				paramStr = paramStr + '&' + fieldE.name + '=' + encodeURIComponent(fieldE.value);
			}
		}
	}
	return paramStr;
}
function getSearchParmFromDataTableOAData(oaData){
	var sEcho = 1;
	var iDisplayStart = 0;
	var iDisplayLength = 10;
	if(oaData == null){
		oaData = [];
	}
	for(var i=0;i<oaData.length;i++){
		var oaOneData = oaData[i];
		if(oaOneData.name=='sEcho'){
			sEcho = oaOneData.value;
		}else if(oaOneData.name=='iDisplayStart'){
			iDisplayStart = oaOneData.value;
		}else if(oaOneData.name=='iDisplayLength'){
			iDisplayLength = oaOneData.value;
		}
	}
	return ('sEcho='+sEcho + '&iDisplayStart='+ iDisplayStart + '&iDisplayLength='+ iDisplayLength);
}
Date.prototype.format = function (fmt) { 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};
function dialogAlert(opt){
	var tempDiv = $('<div title="'+(opt.title?opt.title:'')+'"></div>');
	$(document.body).append(tempDiv);
	tempDiv.html((opt.msg?opt.msg:''));
	tempDiv.dialog({
	      autoOpen: true,
	      modal: true,
	      buttons: {
	    	  	确定: function() {
	    	  		tempDiv.dialog( "close" );
				}
	      },
	      close: function() {
	    	  if(opt.onClose){
	    		  opt.onClose();
	    	  }
	    	  tempDiv.remove();
	      }
	});
}
function dialogConfirm(opt){
	var tempDiv = $('<div title="'+(opt.title?opt.title:'')+'"></div>');
	$(document.body).append(tempDiv);
	tempDiv.html((opt.msg?opt.msg:''));
	tempDiv.dialog({
	      autoOpen: true,
	      modal: true,
	      buttons: {
	    	  	确定: function() {
	    	  		if(opt.onOk){
	    	  			opt.onOk();
	    	  		}
	    	  		
	    	  		tempDiv.dialog( "close" );
				},
				取消: function(){
					
					if(opt.onCancel){
	    	  			opt.onCancel();
	    	  		}
					tempDiv.dialog( "close" );
				}
	      },
	      close: function() {
	    	  if(opt.onClose){
	    		  opt.onClose();
	    	  }
	    	  tempDiv.remove();
	      }
	});
}

Date.prototype.format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
};

function accAdd(arg1,arg2){  
    var r1,r2,m;  
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
    m=Math.pow(10,Math.max(r1,r2))  
    return (accMul(arg1,m)+accMul(arg2,m))/m
}

function accMul(arg1, arg2) {
	var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
	try {
		m += s1.split(".")[1].length
	} catch (e) {
	}
	try {
		m += s2.split(".")[1].length
	} catch (e) {
	}
	return Number(s1.replace(".", "")) * Number(s2.replace(".", ""))
			/ Math.pow(10, m)
}

function accDiv(arg1,arg2){  
    var t1=0,t2=0,r1,r2;  
    try{t1=arg1.toString().split(".")[1].length}catch(e){}  
    try{t2=arg2.toString().split(".")[1].length}catch(e){}  
    with(Math){  
        r1=Number(arg1.toString().replace(".",""))  
        r2=Number(arg2.toString().replace(".",""))  
        return (r1/r2)*pow(10,t2-t1);  
    }  
}