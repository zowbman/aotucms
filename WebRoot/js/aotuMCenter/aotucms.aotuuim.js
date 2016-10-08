$(function() {
	//凹凸空间账号列表
	$('#aotuuserident')
			.datagrid(
					{
						url : 'aotuuim_listData.action',// url请求地址
						fit : true,
						fitColums : true,
						striped : true,
						rownumbers : true,
						border : false,
						pagination : true,
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50 ],
						pageNumber : 1,
						toolbar : '#aotucmsuserident_tool',
						columns : [ [ {
							field : 'sp_id',
							title : '自动编号',
							width : 100,
							checkbox : true,
							rowspan : 2,
						}, {
							title : '凹凸用户身份信息',
							width : 200,
						} ], [ {
							field : 'sp_IdentityN',
							title : '身份名称',
							width : 200,
						}] ],
					});
	
	//添加用户
	$('#aotuuserident_add').dialog({
		width:400,
		height:170,
		title:'新增用户身份（注意）',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			iconCls:'',
			handler: function(){
				if($('#aotuuserident_add').form('validate')){
					//ajax提交
					$.ajax({
						url : 'aotuuim_add.action',//请求路径
						type : 'POST',
						data : $('#aotuuserident_add').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在新增中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotuuserident').datagrid('reload');
								$('#aotuuserident_add').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '用户身份新增成功!'
								});
							}else{
								$.messager.alert('新增失败','未知错误，请重试','warning',function(){
									$('input[name="spIdentityn"]').select();
								});
							}
						}
					});
				}
			},
		},{
			text:'取消',
			iconCls:'',
			handler: function(){
				$('#aotuuserident_add').dialog('close').form('reset');
			}
		}],
	});
	
	//修改用户
	$('#aotuuserident_edit').dialog({
		width : 400,
		height: 170,
		title : '修改用户身份（注意）',
		modal:true,
		closed:true,
		iconCls : '',
		buttons:[{
			text : '提交',
			iconCls : '',
			handler : function(){
				if($('#aotuuserident_edit').form('validate')){
					//ajax提交
					$.ajax({
						url : 'aotuuim_editSubmit.action',//请求路径
						type : 'POST',
						data : $('#aotuuserident_edit').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在修改中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotuuserident').datagrid('reload');
								$('#aotuuserident_edit').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '用户身份修改成功!'
								});
							}else{
								$.messager.alert('修改失败','未知错误，请重试','warning',function(){
									$('input[name="spIdentityn"]').select();
								});
							}
						}
					});
				}
			}
		},{
			text : '取消',
			iconCls : '',
			handler : function(){
				$('#aotuuserident_edit').dialog('close').form('reset');
			},
		}]
	});
	
	//（身份-权限）权限设置
	$('#aotuuseridentpriv_set').dialog({
		width : 500,
		height: 500,
		title : '（身份-权限）权限设置（注意）',
		modal:true,
		closed:true,
		iconCls : '',
		buttons:[{
			text : '提交',
			iconCls : '',
			handler : function(){
				if($('#aotuuseridentpriv_set').form('validate')){
					var ids=[];
					var privChecked=$('#useridentPriv').tree('getChecked');
					var privIndeterminate=$('#useridentPriv').tree('getChecked','indeterminate');
					$.each(privChecked, function (i, n) {
						ids.push(n.id);
				    });
					$.each(privIndeterminate, function (i, n) {
						ids.push(n.id);
				    });
					var map={};
					map['privIds']=ids;
					map['selectedUserIdentId']=$('#aotuuserident').datagrid('getSelected').sp_id;
					$.ajax({
						type : 'POST',
						url : 'aotuuim_userIdentPrivSet.action',
						contentType:'application/json;charset=utf-8',
						data : JSON.stringify(map),
						success : function(data) {
							if (data.code === 0) {
								$('#useridentPriv').tree('reload');
								$.messager.show({
									title : '提示',
									msg : '身份-操作权限设置成功'
								});
							} else {
								$.messager.alert('设置失败', '未知错误，请重试','warning');
							}
						},
					});
				}
			}
		},{
			text : '取消',
			iconCls : '',
			handler : function(){
				$('#aotuuseridentpriv_set').dialog('close').form('reset');
			},
		}]
	});

	
	
	
	//验证员工密码
	$('input[name="spIdentityn"]').validatebox({
		required: true,
		validType : 'length[0,2]',
		missingMessage: '请输入用户身份名称',
		invalidMessage :'用户身份名称在2位以内',
	});
	
	//功能栏
	aotucmsuserident_tool = {
			add : function(){
				$('#aotuuserident_add').dialog('open');
				$('input[name="spIdentityn"]').focus();
			},
			edit : function(){
				var rows =$('#aotuuserident').datagrid('getSelections');
				if(rows.length>1){
					$.messager.alert('警告操作','编辑记录只能选定一条数据！','warning');
				}else if(rows.length==1){
					$.ajax({
						type:'POST',
						url : 'aotuuim_edit.action',
						data : {
							'spId' : rows[0].sp_id,
						},
						beforeSend : function(){
							$.messager.progress({
								text : '正在尝试获取数据...',
							});
						},
						success : function(data){
							$.messager.progress('close');
							if(data[0].code==0){
								var obj=$.parseJSON(data[0].data);
								$('#aotuuserident_edit').form('load',{
									'spId':obj[0].spId,//id
									'spIdentityn':obj[0].spIdentityn,//员工Epid
								}).dialog('open');
							}
						},
					});
				}else if(rows.length==0){
					$.messager.alert('警告操作','编辑记录至少选定一条数据！','warning');
				}
			},
			remove : function() {
				var rows = $('#aotuuserident').datagrid('getSelections');
				if (rows.length > 0) {
					$.messager.confirm('确定', '你要删除所选的<strong>' + rows.length
							+ '</strong>条记录吗', function(flag) {
						if (flag) {
							//获取选中条数的id
							var useridentIds={};
							for(var i=0;i<rows.length;i++){
								useridentIds['useridentIds['+i+']']=rows[i].sp_id;
							}
							$.ajax({
								type : 'POST',
								url : 'aotuuim_delete.action',
								data :useridentIds,
								beforeSend : function() {
									$('#aotuuserident').datagrid('loading');
								},
								success : function(data) {
									if (data.code === 0) {
										$('#aotuuserident').datagrid('loaded');
										$('#aotuuserident').datagrid('reload');
										$('#aotuuserident').datagrid('unselectAll');
										$.messager.show({
											title : '提示',
											msg : '用户身份已被删除成功'
										});
									} else {
										$('#aotuuserident').datagrid('loaded');
										$('#aotuuserident').datagrid('reload');
										$('#aotuuserident')
												.treegrid('unselectAll');
										$.messager.alert('删除失败', '未知错误，请重试',
												'warning');
									}
								},
							});
						}
					});
				}else if(rows.length==0){
					$.messager.alert('警告操作','删除记录至少选定一条数据！','warning');
				}
			},
			// 取消所有选定
			redo : function() {
				$('#aotuuserident').datagrid('unselectAll');
			},
			reload : function() {
				$('#aotuuserident').datagrid('reload');
			},
			identPriv:function(){
				var rows =$('#aotuuserident').datagrid('getSelections');
				if(rows.length>1){
					$.messager.alert('警告操作','编辑记录只能选定一条数据！','warning');
				}else if(rows.length==1){
					$('#aotuuseridentpriv_set').dialog('open');
					//权限tree
					$('#useridentPriv').tree({
						url : 'aotuuim_privTree.action',//请求url
						lines : true,
						checkbox:true,
						queryParams:{
							selectedUserIdentId:$('#aotuuserident').datagrid('getSelected').sp_id,
						},
						onBeforeLoad:function(node,param){
							param.selectedUserIdentId=$('#aotuuserident').datagrid('getSelected').sp_id;
							$.messager.progress({
								text : '正在尝试获取数据...',
							});
						},
						onLoadSuccess:function(){
							$.messager.progress('close');
						}
					});
				}else if(rows.length==0){
					$.messager.alert('警告操作','记录至少选定一条数据！','warning');
				}
				
			}
	}
});