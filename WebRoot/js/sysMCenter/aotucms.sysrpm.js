$(function() {
	//角色管理列表
	$('#aotuemplrole').treegrid({
		url : 'sysrm_listData.action',// url请求地址
		idField : 'sp_id',
		treeField : 'sp_EpStN',
		fit : true,
		fitColums : true,
		striped : true,
		rownumbers : true,
		border:false,
		animate : true,
		toolbar : '#aotuemplrole_tool',
		columns : [ [ {
			field : 'sp_id',
			title : '自动编号',
			width : 100,
			hidden : true,
		},{
			field : 'sp_EpStN',
			title : '角色名称',
			width : 264,
		}] ],
		onLoadSuccess:function(row,data){
			
		},
		onClickRow:function(row){
			$('#SelectedRow').val(row.sp_id);
			$('#isChange').val(0);
			$('#privTree').tree('reload');
		},
	});
	
	//权限tree
	$('#privTree').tree({
		url : 'sysrpm_privTree.action',//请求url
		lines : true,
		checkbox:true,
		queryParams:{
			selectedRoleId:$('#SelectedRow').val(),
		},
		onCheck:function(node, checked){
			//获取角色是否有被选中
			var obj =$('#aotuemplrole').treegrid('getSelected');
			if(obj!=null){
				$('#isChange').val(1);
				$('#privsave').attr('style','display:inline;');
			}else{
				if(checked){
					$.messager.alert('操作提示','请选择一个角色后，才进行操作权限设置','warning');
					var cknodes = $(this).tree("getChecked");
					for(var i = 0 ; i < cknodes.length ; i++){
						$(this).tree("uncheck", cknodes[i].target);
					}
				//$("#roleNav").tree('uncheck',node.target);
				}
			}
		},
		onBeforeLoad:function(node,param){
			param.selectedRoleId=$('#SelectedRow').val();
			$.messager.progress({
				text : '正在尝试获取数据...',
			});
		},
		onLoadSuccess:function(){
			if($('#isChange').val()!=0){
				$('#privsave').attr('style','display:inline;')
			}else{
				$('#privsave').attr('style','display:none;')
			}
			$.messager.progress('close');
		}
	});

	//功能栏
	aotuemplrolepriv_tool = {
		// 取消所有选定
		redo : function() {
			$('#SelectedRow').val('');
			$('#aotuemplrole').treegrid('unselectAll');
			//取消roleNav选中
			var roots = $('#privTree').tree('getRoots'); 
			for(var i = 0 ; i < roots.length ; i++){
				$('#privTree').tree("uncheck", roots[i].target);
			}
			//保存标志
			$('#isChange').val(0);
			$('#privsave').attr('style','display:none;')
		},
		reload : function() {
			$('#aotuemplrole').treegrid('reload');
		},
	}
});

//保存权限
function savepriv(){
	var ids=[];
	var privChecked=$('#privTree').tree('getChecked');
	var privIndeterminate=$('#privTree').tree('getChecked','indeterminate');
	$.each(privChecked, function (i, n) {
		ids.push(n.id);
    });
	$.each(privIndeterminate, function (i, n) {
		ids.push(n.id);
    });
	var map={};
	map['privIds']=ids;
	map['selectedRoleId']=$('#SelectedRow').val();
	$.ajax({
		type : 'POST',
		url : 'sysrpm_editSubmit.action',
		contentType:'application/json;charset=utf-8',
		data : JSON.stringify(map),
		success : function(data) {
			if (data.code === 0) {
				$('#isChange').val(0);
				$('#privsave').attr('style','display:none;')
				$('#privTree').tree('reload');
				$.messager.show({
					title : '提示',
					msg : '角色-操作权限设置成功'
				});
			} else {
				$.messager.alert('设置失败', '未知错误，请重试','warning');
			}
		},
	});
}