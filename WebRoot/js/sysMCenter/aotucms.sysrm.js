$(function() {
	//角色管理列表
	$('#aotustation').treegrid({
		url : 'sysrm_listData.action',// url请求地址
		idField : 'sp_id',
		treeField : 'sp_EpStN',
		fit : true,
		fitColums : true,
		striped : true,
		rownumbers : true,
		animate : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		pageNumber : 1,
		toolbar : '#aotustation_tool',
		columns : [ [ {
			field : 'sp_id',
			title : '自动编号',
			width : 100,
			hidden : true,
			rowspan : 2,
		}, {
			title : '角色信息',
			width : 100,
			colspan : 2,
		} ], [ {
			field : 'sp_EpStN',
			title : '角色名称',
			width : 300,
		},{
			field : 'sp_Opt',
			title : '操作',
			width : 300,
			formatter:function(value,row,index){
                var btn = '<a class="roleEmplEdit" onclick="roleEmplEdit(\''+row.sp_id+'\')" href="javascript:void(0)">编辑</a>';  
                return btn;  
            },
		} ] ],
		onLoadSuccess:function(data){  
	        $('.roleEmplEdit').linkbutton({text:'员工',plain:true,/*iconCls:'icon-edit'*/});  
	        $('#aotustation').datagrid('fixColumnSize')
	    },
	});
	
	//新增角色
	$('#aotustation_add').dialog({
		width:500,
		height:220,
		title:'新增角色',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			/*iconCls:''*/
			handler: function(){
				if($('#aotustation_add').form('validate')){
					$.ajax({
						url : 'sysrm_add.action',//请求路径
						type : 'POST',
						data : $('#aotustation_add').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在新增中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotustation').treegrid('reload');
								$('#aotustation_add').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '角色新增成功!'
								});
							}else{
								$.messager.alert('新增失败','未知错误，请重试','warning',function(){
									$('input[name="spEpstn"]').select();
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
				$('#aotustation_add').dialog('close').form('reset');
			}
		}]
	});
	
	//修改角色信息
	$('#aotustation_edit').dialog({
		width:500,
		height:220,
		title:'修改角色信息',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			/*iconCls:''*/
			handler: function(){
				if($('#aotustation_edit').form('validate')){
					$.ajax({
						url : 'sysrm_editSubmit.action',//请求路径
						type : 'POST',
						data : $('#aotustation_edit').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在修改中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotustation').treegrid('reload');
								$('#aotustation_edit').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '角色修改成功!'
								});
							}else{
								$.messager.alert('修改失败','未知错误，请重试','warning',function(){
									$('input[name="spEpstn"]').select();
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
				$('#aotustation_edit').dialog('close').form('reset');
			}
		}]
	});
	
	//角色员工管理
	$('#aotustation_empl').dialog({
		width:540,
		height:450,
		title:'角色员工',
		modal:true,
		closed:true,
		buttons:[{
			text:'确定',
			/*iconCls:''*/
			handler: function(){
				$('#aotustation_empl').dialog('close');
			}
		}]
	});
	
	//角色员工添加
	$('#aotustation_addEmpl').dialog({
		width:540,
		height:450,
		title:'选取员工',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			/*iconCls:''*/
			handler: function(){
				var selectids='';
				$('#allempl :checked').each(function(){//匹配所有选中的元素
					if($(this).is(':checked')){
						selectids+=$(this).val()+',';
					}
				});
				if(selectids!=''){
					selectids=selectids.substring(0, selectids.length-1);
					//获取选中条数的id以及epid
		    		var empIds=[];
					var temp=selectids.split(',');
					for(var i=0;i<temp.length;i++){
						var temp1=temp[i].toString().split('-');
						for(var j=0;j<1;j++){
							var map={};
							map['spId']=parseInt(temp1[0]);
							map['spEpid']=parseInt(temp1[1]);
							empIds.push(map);
						}
					}
					var map={};
					map['empIds']=empIds;
					
					//ajax
					$.ajax({
						type:'POST',
						url : 'sysrm_addRoleEmpl.action?spId='+$('#stationId').val(),
						data : JSON.stringify(map),
						contentType:'application/json;charset=utf-8',
						beforeSend : function(){
							$.messager.progress({
								text : '正在添加员工中...',
							});
						},
						success : function(data){
							$.messager.progress('close');
							if(data[0].code==0){
								var obj=$.parseJSON(data[0].data);
								//添加所选项
								$.each(obj[0].employeeBinfos, function (i, n) {
									$('#empl_groups').append('<option value="' + n.id + '">' + n.account + ' | ' + n.realname + '</option>');;
						        });
								$('#aotustation_addEmpl').dialog('close');
								$.messager.show({
									title :'提示',
									msg : '员工添加成功!'
								});
							}
						},
					});
					
				}else if(selectids.length==0){
					$.messager.alert('操作提示','请至少选择一个用户','warning');
				}else{
					$.messager.alert('添加失败', '未知错误，请重试','warning');
				}
			}
		},{
			text:'取消',
			/*iconCls:''*/
			handler: function(){
				$('#aotustation_addEmpl').dialog('close');
			}
		}]
	});
	
	//角色员工管理添加按钮
	$('#empl_add').linkbutton({    
	    iconCls: 'icon-search',
	    plain:true,
	    onClick:function(){
	    	//ajax请求员工列表
	    	$.ajax({
				type:'POST',
				url : 'sysrm_emplAll.action',
				data : {
					'spId': $('#stationId').val(),
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
						//绑定员工checkbox
						var emplList= '<table id="allempl" cellpadding=5 cellspacing=0 width=100% align="center"><tr><td><ul class="ul_empls checkbox">hello</ul></td></tr></table><p><input type="checkbox" id="chkall" ><label id="labchkall" for="chkall"><b>全选</b></label></p>';
						$('#aotustation_addEmpl').html(emplList);
						var lis='';
			            $.each(obj[0].employeeBinfos, function (i, n) {
			            	lis += '<li><input type="checkbox" value="' + n.id + '" /><label>' + n.account + ' | ' + n.realname + '</label></li>';
			            });
			            $('.ul_empls').empty().append(lis);
						$('#aotustation_addEmpl').dialog('open');
						
						//全选
						$('#chkall').click(function () {
				    		var flag = $(this).is(':checked');
				    	    if (flag) {
				    	       $(":checkbox", '#allempl').prop("checked", true);
				    	    }else {
				    	       $(":checkbox", '#allempl').prop("checked", false);
				    	    }
				    	});
				    	$('#labchkall').click(function () {
				    		var flag = $(this).prev().is(':checked');
				    	    var pers = $(this).parent().parent().prev();
				    	    if (!flag) {
				    	       $(":checkbox", '#allempl').prop("checked", true);
				    	    }else {
				    	       $(":checkbox", '#allempl').prop("checked", false);
				    	    }
				    	});
					}
				},
			});
	    	
	    },
	});  
	
	
	//角色员工管理移除按钮
	$('#empl_delete').linkbutton({ 
	    iconCls: 'icon-search',
	    plain:true,
	    onClick:function(){
	    	var i=$('#empl_groups').get(0).selectedIndex;
	    	if(i>-1){
	    		//获取选中条数的id以及epid
	    		var empIds=[];
				var temp=$('#empl_groups').val().toString().split(',');
				for(var i=0;i<temp.length;i++){
					var temp1=temp[i].toString().split('-');
					for(var j=0;j<1;j++){
						var map={};
						map['spId']=parseInt(temp1[0]);
						map['spEpid']=parseInt(temp1[1]);
						empIds.push(map);
					}
				}
				var map={};
				map['empIds']=empIds;
	    		$.ajax({
					type:'POST',
					url : 'sysrm_deleteRoleEmpl.action?spId='+$('#stationId').val(),
					data : JSON.stringify(map),
					contentType:'application/json;charset=utf-8',
					beforeSend : function(){
						$.messager.progress({
							text : '正在移除员工中...',
						});
					},
					success : function(data){
						$.messager.progress('close');
						if(data.code==0){
							//删除所选的项
							$.each(temp, function (i, n) {
								$('#empl_groups option[value="'+n+'"]').remove();
							});
							$.messager.show({
								title :'提示',
								msg : '员工移除成功!'
							});
						}
					},
				});
	    	}else{
	    		$.messager.alert("操作提示", "请选择要移除的员工", "info");
	    	}
	    },
	}); 
	//角色员工管理清空按钮
	$('#empl_clear').linkbutton({    
	    iconCls: 'icon-search',
	    plain:true,
	    onClick:function(){
	    	$.ajax({
				type:'POST',
				url : 'sysrm_clearRoleEmpl.action?spId='+$('#stationId').val(),
				beforeSend : function(){
					$.messager.progress({
						text : '正在晴空员工中...',
					});
				},
				success : function(data){
					$.messager.progress('close');
					if(data.code==0){
						//删除所选的项
						$('#empl_groups').empty();
						$.messager.show({
							title :'提示',
							msg : '员工清空成功!'
						});
					}
				},
			});
	    },
	}); 
	
	//验证
	//角色名称
	$('input[name="spEpstn"]').validatebox({
		required: true,
		validType : 'length[3,18]',
		missingMessage: '请输入角色名称',
		invalidMessage :'角色名称在3-18位',
	});
	
	//功能栏
	aotustation_tool = {
			add : function(){
				$('#aotustation_add').dialog('open');
				$('input[name="spEpstn"]').focus();
				//角色父节点
				$('#stationParentId').combotree({
					url : 'sysrm_parentTreeData.action',//请求url
					required : true,
					lines : true,
					onLoadSuccess : function(){
						$('#stationParentId').combotree('setValue',0);
					},
				});
			},
			edit : function(){
				var rows =$('#aotustation').treegrid('getSelections');
				if(rows.length>1){
					$.messager.alert('警告操作','编辑记录只能选定一条数据！','warning');
				}else if(rows.length==1){
					$.ajax({
						type:'POST',
						url : 'sysrm_edit.action',
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
								$('#aotustation_edit').form('load',{
									'spId' : obj[0].spId,
									'spEpstn' : obj[0].spEpstn,
								}).dialog('open');
								$('#stationParentId_edit').combotree({
									url : 'sysrm_parentTreeData.action',//请求url
									required : true,
									lines : true,
									onLoadSuccess : function(node,data){
										//父节点非空
										if(obj[0].spEpstparent){
											var spEpstparent = obj[0].spEpstparent.spEpstn.split(',');
										}else{
											var spEpstparent = "请选择上级节点".split(',');
										}
										var _this=this;
										if(data){	
											$(data).each(function(index,value){
												if($.inArray(value.text,spEpstparent)!=-1){
													var node=$(_this).tree('find',value.id);
													//$(_this).tree('select',node.target);
													$('#stationParentId_edit').combotree('setValue',value.id);
													return false;
												}else{
													$('#stationParentId_edit').combotree('setValue',0);
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
			var rows = $('#aotustation').treegrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('确定', '你要删除所选的<strong>' + rows.length
						+ '</strong>条记录吗', function(flag) {
					if (flag) {
						$.ajax({
							type : 'POST',
							url : 'sysrm_delete.action',
							data : {
								spId : rows[0].sp_id,
							},
							beforeSend : function() {
								$('#aotustation').treegrid('loading');
							},
							success : function(data) {
								if (data.code === 0) {
									$('#aotustation').treegrid('loaded');
									$('#aotustation').treegrid('reload');
									$('#aotustation')
											.treegrid('unselectAll');
									$.messager.show({
										title : '提示',
										msg : '组织机构已被删除成功!'
									});
								} else {
									$('#aotustation').treegrid('loaded');
									$('#aotustation').treegrid('reload');
									$('#aotustation')
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
			$('#aotustation').treegrid('unselectAll');
		},
		reload : function() {
			$('#aotustation').treegrid('reload');
		}
	}
});

function roleEmplEdit(sp_id){
	$.ajax({
		type:'POST',
		url : 'sysrm_roleEmplEdit.action',
		data : {
			'spId' : sp_id,
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
				$('#role_name span').text(obj[0].spEpstn);
				$("#empl_groups").empty();
				$('#aotustation_empl').dialog('open');
				$.each(obj[0].employeeBinfos, function (i, n) {
		                $('#empl_groups').append('<option value="' + n.id + '">' + n.account + ' | ' + n.realname + '</option>');
		        });
				//角色id
				$('#stationId').val(obj[0].spId);
			}
		},
	});
}
