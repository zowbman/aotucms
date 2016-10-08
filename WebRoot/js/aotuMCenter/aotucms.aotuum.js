$(function() {
	//凹凸空间账号列表
	$('#aotuuser')
			.datagrid(
					{
						url : 'aotuum_listData.action',// url请求地址
						fit : true,
						fitColums : true,
						striped : true,
						rownumbers : true,
						border : false,
						pagination : true,
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50 ],
						pageNumber : 1,
						toolbar : '#aotuuser_tool',
						columns : [ [ {
							field : 'sp_id',
							title : '自动编号',
							width : 100,
							checkbox : true,
							rowspan : 2,
						}, {
							title : '凹凸用户基本信息',
							width : 100,
							colspan : 7,
						} ], [ {
							field : 'sp_AtuId',
							title : '凹凸ID',
							width : 200,
						}, {
							field : 'sp_Account',
							title : '用户名',
							width : 200,
						}, {
							field : 'sp_Identity',
							title : '用户身份',
							width : 200,
							formatter:function(value,row,index){
				                var btn = row.sp_Identity+'<a class="anchorApplicationLog" onclick="anchorApplicationLog(\''+row.sp_id+'\')" href="javascript:void(0)">编辑</a>';  
				                return btn;  
				            },
						}, {
							field : 'sp_ReDate',
							title : '注册时间',
							width : 200,
						}, {
							field : 'sp_RePlace',
							title : '注册地',
							width : 200,
						},{
							field : 'sp_ReIp',
							title : '注册Ip',
							width : 200,
						},{
							field : 'sp_Status',
							title : '账号状态',
							width : 200,
						}] ],
						view : detailview,
						detailFormatter : function(rowIndex, rowData) {
							return '<div style="padding:5px"><table class="aotuuserDetail" style="padding:5px 0 0 10px;"></table></div>';
						},
						onExpandRow : function(index, row) {
							getFaotuuserDetail(index, row);
							var UserExpandRow = $('#UserExpandRow').val();
							if (UserExpandRow != '') {
								$('#aotuuser').datagrid('collapseRow',
										UserExpandRow);
							}
							$('#UserExpandRow').val(index);
						},
						onCollapseRow : function(index, row) {
							$('#UserExpandRow').val('');
						},
						onLoadSuccess:function(data){  
					        $('.anchorApplicationLog').linkbutton({text:'代言主播申请记录',plain:true,/*iconCls:'icon-edit'*/});  
					        $('#aotuuser').datagrid('fixColumnSize')
					    },
					});
	//员工详细
	function getFaotuuserDetail(index, row) {
		//console.log(row);
		var aotumanagerDetail = $('#aotuuser').datagrid('getRowDetail',
				index).find('table.aotuuserDetail');
		aotumanagerDetail.panel({
			href : 'aotuum_detail.action?spUsersBinfoKey.spId='+row.sp_id+'&spUsersBinfoKey.spAtuid='+row.sp_AtuId,
			border : false,
			onResize : function() {
				$('#aotuuser').datagrid('fixDetailRowHeight', index);
			},
			onLoad : function() {
				setTimeout(function() {
					$('#aotuuser').datagrid('fixDetailRowHeight', index);
				}, 0);
			}
		});
		$('#aotuuser').datagrid('fixDetailRowHeight', index);
	}
	
	//添加用户
	$('#aotuuser_add').dialog({
		width:800,
		height:530,
		title:'新增凹凸用户（注意）',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			iconCls:'',
			handler: function(){
				if($('#aotuuser_add').form('validate')){
					//alert('addSuccess');
					//ajax提交
					$.ajax({
						url : 'aotuum_add.action',//请求路径
						type : 'POST',
						data : $('#aotuuser_add').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在新增中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotuuser').datagrid('reload');
								$('#aotuuser_add').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '凹凸账号新增成功!'
								});
							}else{
								$.messager.alert('新增失败','未知错误，请重试','warning',function(){
									$('input[name="spEpaccount"]').select();
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
				$('#aotuuser_add').dialog('close').form('reset');
			}
		}],
	});
	
	//修改用户
	$('#aotuuser_edit').dialog({
		width : 800,
		height: 530,
		title : '修改凹凸用户（注意）',
		modal:true,
		closed:true,
		iconCls : '',
		buttons:[{
			text : '提交',
			iconCls : '',
			handler : function(){
				if($('#aotuuser_edit').form('validate')){
					//ajax提交
					$.ajax({
						url : 'aotuum_editSubmit.action',//请求路径
						type : 'POST',
						data : $('#aotuuser_edit').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在修改中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotuuser').datagrid('reload');
								$('#aotuuser_edit').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '凹凸用户修改成功!'
								});
							}else{
								$.messager.alert('修改失败','未知错误，请重试','warning',function(){
									$('input[name="spPassword"]').select();
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
				$('#aotuuser_edit').dialog('close').form('reset');
			},
		}]
	});
	
	//（用户-权限）权限设置
	$('#aotuuserpriv_set').dialog({
		width : 500,
		height: 500,
		title : '（用户-权限）权限设置（注意）',
		modal:true,
		closed:true,
		iconCls : '',
		buttons:[{
			text : '提交',
			iconCls : '',
			handler : function(){
				if($('#aotuuserpriv_set').form('validate')){
					var ids=[];
					var privChecked=$('#userPriv').tree('getChecked');
					var privIndeterminate=$('#userPriv').tree('getChecked','indeterminate');
					$.each(privChecked, function (i, n) {
						ids.push(n.id);
				    });
					$.each(privIndeterminate, function (i, n) {
						ids.push(n.id);
				    });
					var map={};
					map['privIds']=ids;
					var map1={};
					map1['spId']=$('#aotuuser').datagrid('getSelected').sp_id;
					map1['spAtuid']=$('#aotuuser').datagrid('getSelected').sp_AtuId;
					map['spUsersBinfoKey']=map1;
					$.ajax({
						type : 'POST',
						url : 'aotuum_userPrivSet.action',
						contentType:'application/json;charset=utf-8',
						data : JSON.stringify(map),
						success : function(data) {
							if (data.code === 0) {
								$('#userPriv').tree('reload');
								$.messager.show({
									title : '提示',
									msg : '用户-操作权限设置成功'
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
				$('#aotuuserpriv_set').dialog('close').form('reset');
			},
		}]
	});
	
	//用户代言主播申请记录
	$('#anchorApplicationLog').dialog({
		width:540,
		height:450,
		title:'代言主播申请记录',
		/*modal:true,
		closed:true,*/
		buttons:[{
			text:'确定',
			iconCls:'',
			handler: function(){
				$('#aoturole_user').dialog('close');
			}
		}]
	});
	
	
	//星座数据
	$('input[name="spUsersDinfo.spCon"]').combobox({
		url : 'json/constellation/constellation.json',
		valueField : 'id',
		textField : 'text',
		editable :false,
	});
	
	//生肖数据
	$('input[name="spUsersDinfo.spAnimal"]').combobox({
		url : 'json/chineseZodiac/chineseZodiac.json',
		valueField : 'id',
		textField : 'text',
		editable :false,
	});
	
	//日历
	$('input[name="spUsersDinfo.spBirth"]').datebox({
		formatter : function(date){
			return date.getFullYear()+"-"+(date.getMonth() + 1)+"-"+date.getDate();
		},
		sharedCalendar : '#birthU',
		editable :false,
	});
	
	$('#birthU').calendar({
		firstDay : 1,
	});
	
	//表单验证
	//凹凸帐号
	$('input[name="spAccount"]').validatebox({
		required: true,
		validType : 'checkSpEpaccount',
		missingMessage: '请输入凹凸帐号',
		invalidMessage :'凹凸帐号长度为6-18位',
		delay :30000,
	});
	
	//有关表时候把验证关闭
	$('input[name="spAccount"]').focus(function() {
		if ($('input[name="spAccount"]').val()) {
			$(this).validatebox('disableValidation');
		}
	});
	
	//失去贯标时候把验证打开
	$('input[name="spAccount"]').blur(function(){
		$(this).validatebox('enableValidation');
	});
	
	//ajax验证凹凸帐号是否重复
	$.extend($.fn.validatebox.defaults.rules,{
		checkSpEpaccount : {
			validator : function(value) {
				var flag=true;
					if (value.length < 6 || value.length > 18) {
						flag=false;
					}else{
						$.ajax({
							url : 'aotuum_checkSpAccount.action',//请求路径
							type : 'POST',
							async: false,
							data : {
								spEpaccount : value,
							},
							beforeSend : function(){
								$.messager.progress({
									text : '正在验证中...',
								});
							},
							success : function(data,response,status){
								$.messager.progress('close');
								if(data.code != 0){//不成功
									flag=false;
								}
							}
						});
					}
				return flag;
			},
			message:'凹凸帐号已存在，或长度在6-18位，请重新输入'
		},
		
	});
	
	//验证员工密码
	$('input[name="spPassword"]').validatebox({
		required: true,
		validType : 'length[6,18]',
		missingMessage: '请输入凹凸密码',
		invalidMessage :'凹凸密码密码在6-18位',
	});
	//验证真实姓名
	$('input[name="spUsersDinfo.spRealname"]').validatebox({
		validType:'length[0,10]',
		invalidMessage:'请输入有效真实姓名,长度小于或等于10位',
	});
	
	//功能栏
	aotucmsuser_tool = {
			add : function(){
				$('#aotuuser_add').dialog('open');
				$('input[name="spAccount"]').focus();
				//星座与生肖
				$('#spEpcon').combobox('setValue',1);
				$('#spEpanimal').combobox('setValue',1);
			},
			edit : function(){
				var rows =$('#aotuuser').datagrid('getSelections');
				if(rows.length>1){
					$.messager.alert('警告操作','编辑记录只能选定一条数据！','warning');
				}else if(rows.length==1){
					$.ajax({
						type:'POST',
						url : 'aotuum_edit.action',
						data : {
							'spUsersBinfoKey.spId' : rows[0].sp_id,
							'spUsersBinfoKey.spAtuid' : rows[0].sp_AtuId,
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
								$('#aotuuser_edit').form('load',{
									'spUsersBinfoKey.spId':obj[0].spUsersBinfoKey.spId,//id
									'spUsersBinfoKey.spAtuid':obj[0].spUsersBinfoKey.spAtuid,//员工Epid
									'spAccount':obj[0].spAccount,//用户名
									'spPassword':obj[0].spPassword,//密码
									'spUsersDinfo.spNickname':obj[0].spUsersDinfo.spNickname,//昵称
									'spUsersDinfo.spRealname':obj[0].spUsersDinfo.spRealname,//真实姓名
									'spUsersDinfo.spSex':obj[0].spUsersDinfo.spSex,//性别
									'spUsersDinfo.spAge':obj[0].spUsersDinfo.spAge,//年龄
									'spUsersDinfo.spBirth':obj[0].spUsersDinfo.spBirth,//出生日期
									'spUsersDinfo.spCon':obj[0].spUsersDinfo.spCon,//星座
									'spUsersDinfo.spAnimal':obj[0].spUsersDinfo.spAnimal,//生肖
									'spUsersDinfo.spUserdistrict':obj[0].spUsersDinfo.spUserdistrict,//所在地
									'spUsersDinfo.spAddress':obj[0].spUsersDinfo.spAddress,//详细地址
								}).dialog('open');
							}
						},
					});
				}else if(rows.length==0){
					$.messager.alert('警告操作','编辑记录至少选定一条数据！','warning');
				}
			},
			remove : function() {
				var rows = $('#aotuuser').datagrid('getSelections');
				if (rows.length > 0) {
					$.messager.confirm('确定', '你要删除所选的<strong>' + rows.length
							+ '</strong>条记录吗', function(flag) {
						if (flag) {
							//获取选中条数的id以及epid
							var userIds=[];
							for(var i=0;i<rows.length;i++){
								var map={};
								map['spId']=rows[i].sp_id;
								map['spAtuid']=rows[i].sp_AutId;
								userIds.push(map);
							}
							var map={};
							map['userIds']=userIds;
							$.ajax({
								type : 'POST',
								url : 'aotuum_delete.action',
								contentType:'application/json;charset=utf-8',
								data : JSON.stringify(map),
								beforeSend : function() {
									$('#aotuuser').treegrid('loading');
								},
								success : function(data) {
									if (data.code === 0) {
										$('#aotuuser').datagrid('loaded');
										$('#aotuuser').datagrid('reload');
										$('#aotuuser').datagrid('unselectAll');
										$.messager.show({
											title : '提示',
											msg : '凹凸用户已被删除成功'
										});
									} else {
										$('#aotuuser').datagrid('loaded');
										$('#aotuuser').datagrid('reload');
										$('#aotuuser')
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
				$('#aotuuser').datagrid('unselectAll');
			},
			reload : function() {
				$('#aotuuser').datagrid('reload');
				$('#UserExpandRow').val('');
			},
			userPriv:function(){
				var rows =$('#aotuuser').datagrid('getSelections');
				if(rows.length>1){
					$.messager.alert('警告操作','编辑记录只能选定一条数据！','warning');
				}else if(rows.length==1){
					$('#aotuuserpriv_set').dialog('open');
					//权限tree
					$('#userPriv').tree({
						url : 'aotuum_privTree.action',//请求url
						lines : true,
						checkbox:true,
						queryParams:{
							'spUsersBinfoKey.spId' : rows[0].sp_id,
							'spUsersBinfoKey.spAtuid' : rows[0].sp_AtuId,
						},
						onBeforeLoad:function(node,param){
							param['spUsersBinfoKey.spId']=rows[0].sp_id;
							param['spUsersBinfoKey.spAtuid']=rows[0].sp_AtuId;
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

function anchorApplicationLog(sp_id){
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
