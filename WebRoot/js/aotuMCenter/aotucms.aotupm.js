$(function() {
	//权限管理列表
	$('#aotuuserpriv').treegrid({
		url : 'aotupm_listData.action',// url请求地址
		idField : 'sp_id',
		treeField : 'sp_Name',
		fit : true,
		fitColums : true,
		striped : true,
		rownumbers : true,
		border:false,
		animate : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		pageNumber : 1,
		toolbar : '#aotuuserpriv_tool',
		columns : [ [ {
			field : 'sp_id',
			title : '自动编号',
			width : 100,
			hidden : true,
			rowspan : 2,
		}, {
			colspan : 2,
			title : '权限信息信息',
			width : 100,
		} ], [ {
			field : 'sp_Name',
			title : '权限名称',
			width : 300,
		},{
			field : 'sp_Url',
			title : '权限地址',
			width : 300,
		}] ],
	});

	//新增操作权限
	$('#aotuuserpriv_add').dialog({
		width:500,
		height:260,
		title:'新增操作权限',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			iconCls:'',
			handler: function(){
				if($('#aotuuserpriv_add').form('validate')){
					$.ajax({
						url : 'aotupm_add.action',//请求路径
						type : 'POST',
						data : $('#aotuuserpriv_add').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在新增中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotuuserpriv').treegrid('reload');
								$('#aotuuserpriv_add').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '操作权限新增成功!'
								});
							}else{
								$.messager.alert('新增失败','未知错误，请重试','warning',function(){
									$('input[name="spName"]').select();
								});
							}
						}
					});
				}
			}
		},{
			text:'取消',
			iconCls:'',
			handler: function(){
				$('#aotuuserpriv_add').dialog('close').form('reset');
			}
		}]
	});
	
	
	//修改操作权限信息
	$('#aotuuserpriv_edit').dialog({
		width:500,
		height:260,
		title:'修改操作权限',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			iconCls:'',
			handler: function(){
				if($('#aotuuserpriv_edit').form('validate')){
					$.ajax({
						url : 'aotupm_editSubmit.action',//请求路径
						type : 'POST',
						data : $('#aotuuserpriv_edit').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在修改中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotuuserpriv').treegrid('reload');
								$('#aotuuserpriv_edit').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '操作权限修改成功!'
								});
							}else{
								$.messager.alert('修改失败','未知错误，请重试','warning',function(){
									$('input[name="spName"]').select();
								});
							}
						}
					});
				}
			}
		},{
			text:'取消',
			iconCls:'',
			handler: function(){
				$('#aotuuserpriv_edit').dialog('close').form('reset');
			}
		}]
	});
	
	
	//验证
	//操作权限名称
	$('input[name="spName"]').validatebox({
		required: true,
		validType : 'length[3,18]',
		missingMessage: '请输入操作权限名称',
		invalidMessage :'操作权限名称在3-18位',
	});
	
	//功能栏
	aotuuserpriv_tool = {
			add : function(){
				$('#aotuuserpriv_add').dialog('open');
				$('input[name="spName"]').focus();
				//角色父节点
				$('#userprivParentId').combotree({
					url : 'aotupm_parentTreeData.action',//请求url
					required : true,
					lines : true,
					onLoadSuccess : function(){
						$('#userprivParentId').combotree('setValue',0);
					},
				});
			},
			edit : function(){
				var rows =$('#aotuuserpriv').treegrid('getSelections');
				if(rows.length>1){
					$.messager.alert('警告操作','编辑记录只能选定一条数据！','warning');
				}else if(rows.length==1){
					$.ajax({
						type:'POST',
						url : 'aotupm_edit.action',
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
								//解析json
								var obj=$.parseJSON(data[0].data);
								$('#aotuuserpriv_edit').form('load',{
									'spId' : obj[0].spId,
									'spName' : obj[0].spName,
									'spUrl' : obj[0].spUrl,
								}).dialog('open');
								$('#userprivParentId_edit').combotree({
									url : 'aotupm_parentTreeData.action',//请求url
									required : true,
									lines : true,
									onLoadSuccess : function(node,data){
										//父节点非空
										if(obj[0].privParent){
											var privParent = obj[0].privParent.spName.split(',');
										}else{
											var privParent = "请选择上级节点".split(',');
										}
										
										var _this=this;
										if(data){	
											$(data).each(function(index,value){
												if($.inArray(value.text,privParent)!=-1){
													var node=$(_this).tree('find',value.id);
													//$(_this).tree('select',node.target);
													$('#userprivParentId_edit').combotree('setValue',value.id);
													return false;
												}else{
													$('#userprivParentId_edit').combotree('setValue',0);
												}
											});
										}
									},
								});
							}
						},
					});
				}else if(rows.length==0){
					$.messager.alert('警告操作','编辑记录至少选定一条数据！','warning');
				}
			},
			remove : function() {
			var rows = $('#aotuuserpriv').treegrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('确定', '你要删除所选的<strong>' + rows.length
						+ '</strong>条记录吗', function(flag) {
					if (flag) {
						$.ajax({
							type : 'POST',
							url : 'aotupm_delete.action',
							data : {
								spId : rows[0].sp_id,
							},
							beforeSend : function() {
								$('#aotuuserpriv').treegrid('loading');
							},
							success : function(data) {
								if (data.code === 0) {
									$('#aotuuserpriv').treegrid('loaded');
									$('#aotuuserpriv').treegrid('reload');
									$('#aotuuserpriv')
											.treegrid('unselectAll');
									$.messager.show({
										title : '提示',
										msg : '操作权限已被删除成功!'
									});
								} else {
									$('#aotuuserpriv').treegrid('loaded');
									$('#aotuuserpriv').treegrid('reload');
									$('#aotuuserpriv')
											.treegrid('unselectAll');
									$.messager.alert('删除失败', '未知错误，请重试',
											'warning');
								}
							},
						});
					}
				});
			}
		},
		// 取消所有选定
		redo : function() {
			$('#aotuuserpriv').treegrid('unselectAll');
		},
		reload : function() {
			$('#aotuuserpriv').treegrid('reload');
		}
	}
});
