$(function() {
	//角色管理列表
	$('#aotuemplpriv').treegrid({
		url : 'syspm_listData.action',// url请求地址
		idField : 'sp_id',
		treeField : 'sp_EpName',
		fit : true,
		fitColums : true,
		striped : true,
		rownumbers : true,
		border:false,
		animate : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		pageNumber : 1,
		toolbar : '#aotuemplpriv_tool',
		columns : [ [ {
			field : 'sp_id',
			title : '自动编号',
			width : 100,
			hidden : true,
			rowspan : 2,
		}, {
			colspan : 3,
			title : '权限信息信息',
			width : 100,
		} ], [ {
			field : 'sp_EpName',
			title : '权限名称',
			width : 300,
		},{
			field : 'sp_EpUrl',
			title : '权限地址',
			width : 300,
		},{
			field : 'sp_IconCls',
			title : '图标',
			width : 100,
		}] ],
	});
	
	
	
	//新增操作权限
	$('#aotuemplpriv_add').dialog({
		width:500,
		height:260,
		title:'新增操作权限',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			/*iconCls:''*/
			handler: function(){
				if($('#aotuemplpriv_add').form('validate')){
					$.ajax({
						url : 'syspm_add.action',//请求路径
						type : 'POST',
						data : $('#aotuemplpriv_add').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在新增中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotuemplpriv').treegrid('reload');
								$('#aotuemplpriv_add').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '操作权限新增成功!'
								});
							}else{
								$.messager.alert('新增失败','未知错误，请重试','warning',function(){
									$('input[name="spEpname"]').select();
								});
							}
						}
					});
				}
			}
		},{
			text:'取消',
			/*iconCls:''*/
			handler: function(){
				$('#aotuemplpriv_add').dialog('close').form('reset');
			}
		}]
	});
	
	//修改操作权限信息
	$('#aotuemplpriv_edit').dialog({
		width:500,
		height:260,
		title:'修改操作权限',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			/*iconCls:''*/
			handler: function(){
				if($('#aotuemplpriv_edit').form('validate')){
					$.ajax({
						url : 'syspm_editSubmit.action',//请求路径
						type : 'POST',
						data : $('#aotuemplpriv_edit').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在修改中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotuemplpriv').treegrid('reload');
								$('#aotuemplpriv_edit').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '操作权限修改成功!'
								});
							}else{
								$.messager.alert('修改失败','未知错误，请重试','warning',function(){
									$('input[name="spEpname"]').select();
								});
							}
						}
					});
				}
			}
		},{
			text:'取消',
			/*iconCls:''*/
			handler: function(){
				$('#aotuemplpriv_edit').dialog('close').form('reset');
			}
		}]
	});
	
	//验证
	//操作权限名称
	$('input[name="spEpname"]').validatebox({
		required: true,
		validType : 'length[3,18]',
		missingMessage: '请输入操作权限名称',
		invalidMessage :'操作权限名称在3-18位',
	});
	
	//功能栏
	aotuemplpriv_tool = {
			add : function(){
				$('#aotuemplpriv_add').dialog('open');
				$('input[name="spEpname"]').focus();
				//角色父节点
				$('#emplprivParentId').combotree({
					url : 'syspm_parentTreeData.action',//请求url
					required : true,
					lines : true,
					onLoadSuccess : function(){
						$('#emplprivParentId').combotree('setValue',0);
					},
				});
			},
			edit : function(){
				var rows =$('#aotuemplpriv').treegrid('getSelections');
				if(rows.length>1){
					$.messager.alert('警告操作','编辑记录只能选定一条数据！','warning');
				}else if(rows.length==1){
					$.ajax({
						type:'POST',
						url : 'syspm_edit.action',
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
								$('#aotuemplpriv_edit').form('load',{
									'spId' : obj[0].spId,
									'spEpname' : obj[0].spEpname,
									'spEpurl' : obj[0].spEpurl,
								}).dialog('open');
								$('#emplprivParentId_edit').combotree({
									url : 'syspm_parentTreeData.action',//请求url
									required : true,
									lines : true,
									onLoadSuccess : function(node,data){
										//父节点非空
										if(obj[0].spEpparent){
											var spEpparent = obj[0].spEpparent.spEpname.split(',');
										}else{
											var spEpparent = "请选择上级节点".split(',');
										}
										var _this=this;
										if(data){	
											$(data).each(function(index,value){
												if($.inArray(value.text,spEpparent)!=-1){
													var node=$(_this).tree('find',value.id);
													//$(_this).tree('select',node.target);
													$('#emplprivParentId_edit').combotree('setValue',value.id);
													return false;
												}else{
													$('#emplprivParentId_edit').combotree('setValue',0);
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
			var rows = $('#aotuemplpriv').treegrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('确定', '你要删除所选的<strong>' + rows.length
						+ '</strong>条记录吗', function(flag) {
					if (flag) {
						$.ajax({
							type : 'POST',
							url : 'syspm_delete.action',
							data : {
								spId : rows[0].sp_id,
							},
							beforeSend : function() {
								$('#aotuemplpriv').treegrid('loading');
							},
							success : function(data) {
								if (data.code === 0) {
									$('#aotuemplpriv').treegrid('loaded');
									$('#aotuemplpriv').treegrid('reload');
									$('#aotuemplpriv')
											.treegrid('unselectAll');
									$.messager.show({
										title : '提示',
										msg : '操作权限已被删除成功!'
									});
								} else {
									$('#aotuemplpriv').treegrid('loaded');
									$('#aotuemplpriv').treegrid('reload');
									$('#aotuemplpriv')
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
			$('#aotuemplpriv').treegrid('unselectAll');
		},
		reload : function() {
			$('#aotuemplpriv').treegrid('reload');
		}
	}
});
