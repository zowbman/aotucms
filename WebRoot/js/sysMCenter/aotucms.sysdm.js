$(function() {
	//组织机构管理列表
	$('#aotudepartment').treegrid({
		url : 'sysdm_listData.action',// url请求地址
		idField : 'sp_id',
		treeField : 'sp_EpDepartN',
		fit : true,
		fitColums : true,
		striped : true,
		rownumbers : true,
		animate : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		pageNumber : 1,
		toolbar : '#aotudepartment_tool',
		columns : [ [ {
			field : 'sp_id',
			title : '自动编号',
			width : 100,
			hidden : true,
			rowspan : 2,
		}, {
			title : '组织机构信息',
			width : 100,
			colspan : 1,
		} ], [ {
			field : 'sp_EpDepartN',
			title : '组织机构名称',
			width : 300,
		} ] ],
	});
	
	//新增部门
	$('#aotudepartment_add').dialog({
		width:500,
		height:220,
		title:'新增组织机构',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			/*iconCls:''*/
			handler: function(){
				if($('#aotudepartment_add').form('validate')){
					$.ajax({
						url : 'sysdm_add.action',//请求路径
						type : 'POST',
						data : $('#aotudepartment_add').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在新增中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotudepartment').treegrid('reload');
								$('#aotudepartment_add').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '组织机构新增成功!'
								});
							}else{
								$.messager.alert('新增失败','未知错误，请重试','warning',function(){
									$('input[name="spEpdepartn"]').select();
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
				$('#aotudepartment_add').dialog('close').form('reset');
			}
		}]
	});
	
	//修改组织机构
	$('#aotudepartment_edit').dialog({
		width:500,
		height:220,
		title:'修改组织机构信息',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			/*iconCls:''*/
			handler: function(){
				if($('#aotudepartment_edit').form('validate')){
					$.ajax({
						url : 'sysdm_editSubmit.action',//请求路径
						type : 'POST',
						data : $('#aotudepartment_edit').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在修改中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotudepartment').treegrid('reload');
								$('#aotudepartment_edit').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '组织机构修改成功!'
								});
							}else{
								$.messager.alert('修改失败','未知错误，请重试','warning',function(){
									$('input[name="spEpdepartn"]').select();
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
				$('#aotudepartment_edit').dialog('close').form('reset');
			}
		}]
	});
	
	//验证
	//组织机构名称
	$('input[name="spEpdepartn"]').validatebox({
		required: true,
		validType : 'length[3,18]',
		missingMessage: '请输入组织机构名称',
		invalidMessage :'组织机构名称在3-18位',
	});
	
	//功能栏
	aotudepartment_tool = {
			add : function(){
				$('#aotudepartment_add').dialog('open');
				$('input[name="spEpdepartn"]').focus();
				//组织机构父节点
				$('#departmentParentId').combotree({
					url : 'sysdm_parentTreeData.action',//请求url
					required : true,
					lines : true,
					onLoadSuccess : function(){
						$('#departmentParentId').combotree('setValue',0);
					},
				});
			},
			edit : function(){
				var rows =$('#aotudepartment').treegrid('getSelections');
				if(rows.length>1){
					$.messager.alert('警告操作','编辑记录只能选定一条数据！','warning');
				}else if(rows.length==1){
					$.ajax({
						type:'POST',
						url : 'sysdm_edit.action',
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
							//console.log(data);
							if(data[0].code==0){
								//解析json
								var obj=$.parseJSON(data[0].data);
								$('#aotudepartment_edit').form('load',{
									'spId' : obj[0].spId,
									'spEpdepartn' : obj[0].spEpdepartn,
								}).dialog('open');
								$('#departmentParentId_edit').combotree({
									url : 'sysdm_parentTreeData.action',//请求url
									required : true,
									lines : true,
									onLoadSuccess : function(node,data){
										//父节点非空
										if(obj[0].spEpdeparent){
											var spEpdeparent = obj[0].spEpdeparent.spEpdepartn.split(',');
										}else{
											var spEpdeparent = "请选择上级节点".split(',');
										}
										var _this=this;
										if(data){
											$(data).each(function(index,value){
												if($.inArray(value.text,spEpdeparent)!=-1){
													var node=$(_this).tree('find',value.id);
													//$(_this).tree('select',node.target);
													$('#departmentParentId_edit').combotree('setValue',value.id);
												}else{
													$('#departmentParentId_edit').combotree('setValue',0);
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
			var rows = $('#aotudepartment').treegrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('确定', '你要删除所选的<strong>' + rows.length
						+ '</strong>条记录吗', function(flag) {
					if (flag) {
						$.ajax({
							type : 'POST',
							url : 'sysdm_delete.action',
							data : {
								spId : rows[0].sp_id,
							},
							beforeSend : function() {
								$('#aotudepartment').treegrid('loading');
							},
							success : function(data) {
								if (data.code === 0) {
									$('#aotudepartment').treegrid('loaded');
									$('#aotudepartment').treegrid('reload');
									$('#aotudepartment')
											.treegrid('unselectAll');
									$.messager.show({
										title : '提示',
										msg : '组织机构已被删除成功!'
									});
								} else {
									$('#aotudepartment').treegrid('loaded');
									$('#aotudepartment').treegrid('reload');
									$('#aotudepartment')
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
			$('#aotudepartment').treegrid('unselectAll');
		},
		reload : function() {
			$('#aotudepartment').treegrid('reload');
		},
	}
});