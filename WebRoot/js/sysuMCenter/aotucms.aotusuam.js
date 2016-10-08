$(function() {
	//代言主播管理功能列表tree
	$('#anchorMNav1').tree({
		url : 'aotusuam_anchorM.action',//请求url
		lines : true,
		onClick : function(node){
			if(node.url){
				if($('#anchorMtabs1').tabs('exists',node.text)){
					$('#anchorMtabs1').tabs('select',node.text);
				}else{
					$('#anchorMtabs1').tabs('add',{
						title : node.text,
						closable : true,
						href : node.url+'.action',
						//还可以设置图标
					});
				}
			}
		}
	});
	
	//选项卡
	$('#anchorMtabs1').tabs({
		fit : true,
		border : false,
	});	
});