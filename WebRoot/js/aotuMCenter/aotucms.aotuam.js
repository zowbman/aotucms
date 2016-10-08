$(function() {
	//代言主播管理功能列表tree
	$('#anchorMNav').tree({
		url : 'aotuam_anchorM.action',//请求url
		lines : true,
		onClick : function(node){
			if(node.url){
				if($('#anchorMtabs').tabs('exists',node.text)){
					$('#anchorMtabs').tabs('select',node.text);
				}else{
					$('#anchorMtabs').tabs('add',{
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
	$('#anchorMtabs').tabs({
		fit : true,
		border : false,
	});	
});