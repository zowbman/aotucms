$(function() {
	//角色管理列表
	$('#aoturole').treegrid({
		url : 'aoturm_listData.action',// url请求地址
		idField : 'sp_id',
		treeField : 'sp_RoleName',
		fit : true,
		fitColums : true,
		striped : true,
		rownumbers : true,
		animate : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		pageNumber : 1,
		toolbar : '#aoturole_tool',
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
			field : 'sp_RoleName',
			title : '角色名称',
			width : 300,
		},{
			field : 'sp_Opt',
			title : '操作',
			width : 300,
			formatter:function(value,row,index){
                var btn = '<a class="roleUserEdit" onclick="roleUserEdit(\''+row.sp_id+'\')" href="javascript:void(0)">编辑</a>';  
                return btn;  
            },
		} ] ],
		onLoadSuccess:function(data){  
	        $('.roleUserEdit').linkbutton({text:'凹凸用户',plain:true,/*iconCls:'icon-edit'*/});  
	        $('#aoturole').datagrid('fixColumnSize')
	    },
	});
	
	//新增角色
	$('#aoturole_add').dialog({
		width:500,
		height:220,
		title:'新增角色',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			iconCls:'',
			handler: function(){
				if($('#aoturole_add').form('validate')){
					$.ajax({
						url : 'aoturm_add.action',//请求路径
						type : 'POST',
						data : $('#aoturole_add').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在新增中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aoturole').treegrid('reload');
								$('#aoturole_add').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '角色新增成功!'
								});
							}else{
								$.messager.alert('新增失败','未知错误，请重试','warning',function(){
									$('input[name="spRolename"]').select();
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
				$('#aoturole_add').dialog('close').form('reset');
			}
		}]
	});
	
	//修改角色信息
	$('#aoturole_edit').dialog({
		width:500,
		height:220,
		title:'修改角色信息',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			iconCls:'',
			handler: function(){
				if($('#aoturole_edit').form('validate')){
					$.ajax({
						url : 'aoturm_editSubmit.action',//请求路径
						type : 'POST',
						data : $('#aoturole_edit').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在修改中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aoturole').treegrid('reload');
								$('#aoturole_edit').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '角色修改成功!'
								});
							}else{
								$.messager.alert('修改失败','未知错误，请重试','warning',function(){
									$('input[name="spRolename"]').select();
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
				$('#aoturole_edit').dialog('close').form('reset');
			}
		}]
	});
	
	
	//角色员工管理
	$('#aoturole_user').dialog({
		width:540,
		height:450,
		title:'角色用户',
		modal:true,
		closed:true,
		buttons:[{
			text:'确定',
			iconCls:'',
			handler: function(){
				$('#aoturole_user').dialog('close');
			}
		}]
	});
	
	//角色员工添加
	$('#aoturole_addUser').dialog({
		width:540,
		height:450,
		title:'选取用户',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			iconCls:'',
			handler: function(){
				var selectids='';
				$('#alluser :checked').each(function(){//匹配所有选中的元素
					if($(this).is(':checked')){
						selectids+=$(this).val()+',';
					}
				});
				if(selectids!=''){
					selectids=selectids.substring(0, selectids.length-1);
					//获取选中条数的id以及epid
		    		var userIds=[];
					var temp=selectids.split(',');
					for(var i=0;i<temp.length;i++){
						var temp1=temp[i].toString().split('-');
						for(var j=0;j<1;j++){
							var map={};
							map['spId']=parseInt(temp1[0]);
							map['spAtuid']=parseInt(temp1[1]);
							userIds.push(map);
						}
					}
					var map={};
					map['userIds']=userIds;
					//ajax
					$.ajax({
						type:'POST',
						url : 'aoturm_addRoleUser.action?spId='+$('#roleId').val(),
						data : JSON.stringify(map),
						contentType:'application/json;charset=utf-8',
						beforeSend : function(){
							$.messager.progress({
								text : '正在添加用户中中...',
							});
						},
						success : function(data){
							$.messager.progress('close');
							if(data[0].code==0){
								var obj=$.parseJSON(data[0].data);
								//添加所选项
								$.each(obj[0].userBinfos, function (i, n) {
									$('#user_groups').append('<option value="' + n.id + '">' + n.account + ' | ' + n.realname + '</option>');;
						        });
								$('#aoturole_addUser').dialog('close');
								$.messager.show({
									title :'提示',
									msg : '用户添加成功!'
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
			iconCls:'',
			handler: function(){
				$('#aoturole_addUser').dialog('close');
			}
		}]
	});
	
	//角色用户管理添加按钮
	$('#user_add').linkbutton({    
	    iconCls: 'icon-search',
	    plain:true,
	    onClick:function(){
	    	//ajax请求员工列表
	    	$.ajax({
				type:'POST',
				url : 'aoturm_userAll.action',
				data : {
					'spId': $('#roleId').val(),
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
						var userList= '<table id="alluser" cellpadding=5 cellspacing=0 width=100% align="center"><tr><td><ul class="ul_users checkbox">hello</ul></td></tr></table><p><input type="checkbox" id="chkall" ><label id="labchkall" for="chkall"><b>全选</b></label></p>';
						$('#aoturole_addUser').html(userList);
						var lis='';
			            $.each(obj[0].userBinfos, function (i, n) {
			            	lis += '<li><input type="checkbox" value="' + n.id + '" /><label>' + n.account + ' | ' + n.realname + '</label></li>';
			            });
			            $('.ul_users').empty().append(lis);
						$('#aoturole_addUser').dialog('open');
						
						//全选
						$('#chkall').click(function () {
				    		var flag = $(this).is(':checked');
				    	    if (flag) {
				    	       $(":checkbox", '#alluser').prop("checked", true);
				    	    }else {
				    	       $(":checkbox", '#alluser').prop("checked", false);
				    	    }
				    	});
				    	$('#labchkall').click(function () {
				    		var flag = $(this).prev().is(':checked');
				    	    var pers = $(this).parent().parent().prev();
				    	    if (!flag) {
				    	       $(":checkbox", '#alluser').prop("checked", true);
				    	    }else {
				    	       $(":checkbox", '#alluser').prop("checked", false);
				    	    }
				    	});
					}
				},
			});
	    	
	    },
	});  
	
	
	//角色员工管理移除按钮
	$('#user_delete').linkbutton({ 
	    iconCls: 'icon-search',
	    plain:true,
	    onClick:function(){
	    	var i=$('#user_groups').get(0).selectedIndex;
	    	if(i>-1){
	    		//获取选中条数的id以及epid
	    		var empIds=[];
				var temp=$('#user_groups').val().toString().split(',');
				for(var i=0;i<temp.length;i++){
					var temp1=temp[i].toString().split('-');
					for(var j=0;j<1;j++){
						var map={};
						map['spId']=parseInt(temp1[0]);
						map['spAtuid']=parseInt(temp1[1]);
						empIds.push(map);
					}
				}
				var map={};
				map['userIds']=empIds;
	    		$.ajax({
					type:'POST',
					url : 'aoturm_deleteRoleUser.action?spId='+$('#roleId').val(),
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
								$('#user_groups option[value="'+n+'"]').remove();
							});
							$.messager.show({
								title :'提示',
								msg : '用户移除成功!'
							});
						}
					},
				});
	    	}else{
	    		$.messager.alert("操作提示", "请选择要移除的用户", "info");
	    	}
	    },
	}); 
	//角色员工管理清空按钮
	$('#user_clear').linkbutton({    
	    iconCls: 'icon-search',
	    plain:true,
	    onClick:function(){
	    	$.ajax({
				type:'POST',
				url : 'aoturm_clearRoleUser.action?spId='+$('#roleId').val(),
				beforeSend : function(){
					$.messager.progress({
						text : '正在清空用户中...',
					});
				},
				success : function(data){
					$.messager.progress('close');
					if(data.code==0){
						//删除所选的项
						$('#user_groups').empty();
						$.messager.show({
							title :'提示',
							msg : '用户清空成功!'
						});
					}
				},
			});
	    },
	}); 
	
	//（身份-权限）权限设置
	$('#aotuuserrolepriv_set').dialog({
		width : 500,
		height: 500,
		title : '（角色-权限）权限设置（注意）',
		modal:true,
		closed:true,
		iconCls : '',
		buttons:[{
			text : '提交',
			iconCls : '',
			handler : function(){
				if($('#aotuuserrolepriv_set').form('validate')){
					var ids=[];
					var privChecked=$('#userrolePriv').tree('getChecked');
					var privIndeterminate=$('#userrolePriv').tree('getChecked','indeterminate');
					$.each(privChecked, function (i, n) {
						ids.push(n.id);
				    });
					$.each(privIndeterminate, function (i, n) {
						ids.push(n.id);
				    });
					var map={};
					map['privIds']=ids;
					map['selectedUserRoleId']=$('#aoturole').treegrid('getSelected').sp_id;
					$.ajax({
						type : 'POST',
						url : 'aoturm_userRolePrivSet.action',
						contentType:'application/json;charset=utf-8',
						data : JSON.stringify(map),
						success : function(data) {
							if (data.code === 0) {
								$('#userrolePriv').tree('reload');
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
				$('#aotuuserrolepriv_set').dialog('close').form('reset');
			},
		}]
	});
	
	
	//验证
	//角色名称
	$('input[name="spRolename"]').validatebox({
		required: true,
		validType : 'length[3,18]',
		missingMessage: '请输入角色名称',
		invalidMessage :'角色名称在3-18位',
	});
	
	
	//功能栏
	aoturole_tool = {
			add : function(){
				$('#aoturole_add').dialog('open');
				$('input[name="spRolename"]').focus();
				//角色父节点
				$('#roleParentId').combotree({
					url : 'aoturm_parentTreeData.action',//请求url
					required : true,
					lines : true,
					onLoadSuccess : function(){
						$('#roleParentId').combotree('setValue',0);
					},
				});
			},
			edit : function(){
				var rows =$('#aoturole').treegrid('getSelections');
				if(rows.length>1){
					$.messager.alert('警告操作','编辑记录只能选定一条数据！','warning');
				}else if(rows.length==1){
					$.ajax({
						type:'POST',
						url : 'aoturm_edit.action',
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
								$('#aoturole_edit').form('load',{
									'spId' : obj[0].spId,
									'spRolename' : obj[0].spRolename,
								}).dialog('open');
								$('#roleParentId_edit').combotree({
									url : 'aoturm_parentTreeData.action',//请求url
									required : true,
									lines : true,
									onLoadSuccess : function(node,data){
										//父节点非空
										if(obj[0].spAotuspaceRoleparent){
											var spAotuspaceRoleparent = obj[0].spAotuspaceRoleparent.spRolename.split(',');
										}else{
											var spAotuspaceRoleparent = "请选择上级节点".split(',');
										}
										var _this=this;
										if(data){	
											$(data).each(function(index,value){
												if($.inArray(value.text,spAotuspaceRoleparent)!=-1){
													var node=$(_this).tree('find',value.id);
													//$(_this).tree('select',node.target);
													$('#roleParentId_edit').combotree('setValue',value.id);
													return false;
												}else{
													$('#roleParentId_edit').combotree('setValue',0);
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
			$('#aoturole').treegrid('unselectAll');
		},
		reload : function() {
			$('#aoturole').treegrid('reload');
		},
		rolePriv:function(){
			var rows =$('#aoturole').treegrid('getSelections');
			if(rows.length>1){
				$.messager.alert('警告操作','编辑记录只能选定一条数据！','warning');
			}else if(rows.length==1){
				$('#aotuuserrolepriv_set').dialog('open');
				//权限tree
				$('#userrolePriv').tree({
					url : 'aoturm_privTree.action',//请求url
					lines : true,
					checkbox:true,
					queryParams:{
						selectedUserRoleId:$('#aoturole').treegrid('getSelected').sp_id,
					},
					onBeforeLoad:function(node,param){
						param.selectedUserRoleId=$('#aoturole').treegrid('getSelected').sp_id;
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

function roleUserEdit(sp_id){
	$.ajax({
		type:'POST',
		url : 'aoturm_roleUserEdit.action',
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
				$('#role_name span').text(obj[0].spRolename);
				$("#user_groups").empty();
				$('#aoturole_user').dialog('open');
				$.each(obj[0].userBinfos, function (i, n) {
		                $('#user_groups').append('<option value="' + n.id + '">' + n.account + ' | ' + n.realname + '</option>');
		        });
				//角色id
				$('#roleId').val(obj[0].spId);
			}
		},
	});
}
