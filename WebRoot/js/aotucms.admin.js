$(function(){
	//导航 数据库 id标识 text名称 state状态 iconCls图标 URL地址 nid父节点，数据库读出来，把转为json数据
	$('#nav').tree({
		url : 'aotucms_nav.action',//请求url
		lines : true,
		//一刷新全部展开
		onLoadSuccess : function(node,data){
			if(data){
				$(data).each(function(index, value) {
					$(this.children).each(function(index,value){
						if(this.state=='closed'){
							$('#nav').tree('expandAll');
						}
					});
				});
			}
		},
		onClick : function(node){
			if(node.url){
				if($('#tabs').tabs('exists',node.text)){
					$('#tabs').tabs('select',node.text);
				}else{
					$('#tabs').tabs('add',{
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
	$('#tabs').tabs({
		fit : true,
		border : false,
	});
});