$(function(){
	//���� ���ݿ� id��ʶ text���� state״̬ iconClsͼ�� URL��ַ nid���ڵ㣬���ݿ����������תΪjson����
	$('#nav').tree({
		url : 'aotucms_nav.action',//����url
		lines : true,
		//һˢ��ȫ��չ��
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
						//����������ͼ��
					});
				}
			}
		}
	});

	//ѡ�
	$('#tabs').tabs({
		fit : true,
		border : false,
	});
});