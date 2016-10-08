$(function() {
	//主播种类tabs
	$('#anchorApplicationTypetabs').tabs({
		fit : true,
		border : false,
		plain:true,
		pill:true,
	});
	
	//代言主播申请列表
	$('#anchorApp')
			.datagrid(
					{
						url : 'aotuaanm_applicationList.action',// url请求地址
						fit : true,
						fitColums : true,
						striped : true,
						rownumbers : true,
						border : false,
						pagination : true,
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50 ],
						pageNumber : 1,
						toolbar : '#anchorApp_tool',
						columns : [ [ {
							field : 'sp_id',
							title : '自动编号',
							width : 100,
							checkbox : true,
							rowspan : 2,
						}, {
							title : '代言主播申请列表',
							width : 100,
							colspan : 4,
						} ], [ {
							field : 'sp_AtuId',
							title : '凹凸ID',
							width : 200,
						}, {
							field : 'sp_ApplyDate',
							title : '申请时间',
							width : 200,
						}, {
							field : 'sp_AppResult',
							title : ' 申请状态',
							width : 200,
						},{
							field : 'sp_Opt',
							title : '操作',
							width : 300,
							formatter:function(value,row,index){
								console.log(row);
				                var btn = '<a class="anchorReview" onclick="anchorReview(\''+row.sp_id+'\','+row.sp_AtuId+')" href="javascript:void(0)">编辑</a>'
				                			+'<a class="anchorReviewY" onclick="anchorAppReview(\''+row.sp_id+'\','+2+')" href="javascript:void(0)">编辑</a>'
				                			+'<a class="anchorReviewN" onclick="anchorAppReview(\''+row.sp_id+'\','+3+')" href="javascript:void(0)">编辑</a>';  
				                return btn;  
				            },
						} ] ],
						onLoadSuccess:function(data){  
					        $('.anchorReview').linkbutton({text:'审核',plain:true,/*iconCls:'icon-edit'*/});  
					        $('.anchorReviewY').linkbutton({text:'通过',plain:true,/*iconCls:'icon-edit'*/});  
					        $('.anchorReviewN').linkbutton({text:'不通过',plain:true,/*iconCls:'icon-edit'*/});  
					        $('#anchorApp').datagrid('fixColumnSize');
					    },
				});
	
	//其他代言主播申请列表
	$('#artistApp')
	.datagrid(
			{
				url : 'aotuaarm_appliactionList.action',// url请求地址
				fit : true,
				fitColums : true,
				striped : true,
				rownumbers : true,
				border : false,
				pagination : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50 ],
				pageNumber : 1,
				toolbar : '#artistApp_tool',
				columns : [ [ {
					field : 'sp_id',
					title : '自动编号',
					width : 100,
					checkbox : true,
					rowspan : 2,
				}, {
					title : '其他代言主播申请列表',
					width : 100,
					colspan :4,
				} ], [ {
					field : 'sp_AtuId',
					title : '凹凸ID',
					width : 200,
				}, {
					field : 'sp_ApplyDate',
					title : '申请时间',
					width : 200,
				},{
					field : 'sp_AppResult',
					title : ' 申请状态',
					width : 200,
				},{
					field : 'sp_Opt',
					title : '操作',
					width : 300,
					formatter:function(value,row,index){
						//0:申请中
						//1:通过
						//2:不通过
		                var btn = '<a class="artistReview" onclick="artistReview(\''+row.sp_id+'\','+row.sp_AtuId+')" href="javascript:void(0)">编辑</a>'
		                			+'<a class="artistReviewY" onclick="artistAppReview(\''+row.sp_id+'\','+1+')" href="javascript:void(0)">编辑</a>'
		                			+'<a class="artistReviewN" onclick="artistAppReview(\''+row.sp_id+'\','+2+')" href="javascript:void(0)">编辑</a>';  
		                return btn;  
		            },
				}] ] ,
				onLoadSuccess:function(data){  
			        $('.artistReview').linkbutton({text:'审核',plain:true,/*iconCls:'icon-edit'*/});  
			        $('.artistReviewY').linkbutton({text:'通过',plain:true,/*iconCls:'icon-edit'*/});  
			        $('.artistReviewN').linkbutton({text:'不通过',plain:true,/*iconCls:'icon-edit'*/});  
			        $('#artistApp').datagrid('fixColumnSize');
			    },
		});
	
	//修正行高
	$('#anchorApplicationTypetabs').tabs({
		onSelect:function(){
			$('#anchorApp').datagrid('fixColumnSize');
			$('#artistApp').datagrid('fixColumnSize');
		}
	});
	
	//代言主播申请详细信息
	$('#anchorApp_detail').dialog({
		width:860,
		height:600,
		title:'代言主播申请详细信息',
		modal:true,
		closed:true,
		buttons:[{
			text:'通过',
			iconCls:'',
			handler: function(){
				anchorAppReview($('#appAnchorId').val(),2);
			}
		},{
			text:'不通过',
			iconCls:'',
			handler: function(){
				anchorAppReview($('#appAnchorId').val(),3);
			}
		},{
			text:'取消',
			iconCls:'',
			handler: function(){
				$('#anchorApp_detail').dialog('close');
			}
		}]
	});
	
	$('#anchorApp_detail_tabs').tabs({
		fit : true,
		plain:true,
	});
	
	//其他代言主播申请详细信息
	$('#artistApp_detail').dialog({
		width:860,
		height:600,
		title:'其他代言主播申请详细信息',
		modal:true,
		closed:true,
		buttons:[{
			text:'通过',
			iconCls:'',
			handler: function(){
				artistAppReview($('#appArtistId').val(),1)
			}
		},{
			text:'不通过',
			iconCls:'',
			handler: function(){
				artistAppReview($('#appArtistId').val(),2)
			}
		},{
			text:'取消',
			iconCls:'',
			handler: function(){
				$('#artistApp_detail').dialog('close');
			}
		}]
	});
	$('#artistApp_detail_tabs').tabs({
		fit : true,
		plain:true,
	});
	
	//添加代言主播申请
	$('#anchorApp_add').dialog({
		width:700,
		height:700,
		title:'添加代言主播申请',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			iconCls:'',
			handler: function(){				
				if($('#anchorApp_add').form('validate')){
					var tab =$('#anchorApplicationTypetabs').tabs('getSelected');
					var url;
					switch(tab.panel('options').id){
						case 'anchorApp-tab':url='aotuaanm_add.action';
						break;
						case 'artistApp-tab':url='aotuaarm_add.action';
						break;
					}
					if(url){
						//ajax
						$.ajax({
							type : 'POST',
							url : url,
							data :$('#anchorApp_add').serialize(),
							success : function(data) {
								if (data.code === 0) {
									$('#anchorApp').datagrid('reload');
									$('#artistApp').datagrid('reload');
									$('#anchorApp_add').dialog('close');
									$.messager.show({
										title : '提示',
										msg : '代言主播申请添加成功'
									});
								} else {
									$.messager.alert('添加失败', '未知错误，请重试',
											'warning');
								}
							},
						});
					}else{
						$.messager.alert('添加失败', '未知错误，请重试',
						'warning');
					}
					
				}
			}
		},{
			text:'取消',
			iconCls:'',
			handler: function(){
				$('#anchorApp_add').dialog('close');
			}
		}]
	});
		
	
	//添加代言主播申请表单验证
	//用户（弹出框选择）
	//1.dialog
	//2.datagrid
	$('#spUser').textbox({
		missingMessage: '请选择用户',
		required: true,
		editable:false,
		buttonText:'选择用户',
		onClickButton:function(){
			$('#spUser-dialog').dialog('open');
		},
	});
	
	//选择用户dialog
	$('#spUser-dialog').dialog({
		width:800,
		height:500,
		title:'选择用户',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			iconCls:'',
			handler: function(){
				var row=$('#spUser-datagrid').datagrid('getSelected');
				if(row){
					$('#spUser').textbox('setValue',row.sp_AtuId)
				}else{
					$('#spUser').textbox('setValue',row);
				}
				$('#spUser-dialog').dialog('close');
			}
		},{
			text:'取消',
			iconCls:'',
			handler: function(){
				$('#spUser-dialog').dialog('close');
			}
		}]
	});
	
	//凹凸空间账号列表
	$('#spUser-datagrid')
			.datagrid(
					{
						url : 'aotuum_listData.action',// url请求地址
						singleSelect:true,
						fit : true,
						fitColums : true,
						striped : true,
						rownumbers : true,
						border : false,
						pagination : true,
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50 ],
						pageNumber : 1,
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
					
					});
	//真实姓名
	$('input[name="spAnchorApplicationDetail.spRealname"]').validatebox({
		missingMessage: '请输入真实姓名',
		required: true,
		validType : 'CHS',
	});
	//身份证
	$('input[name="spAnchorApplicationDetail.spIdNum"]').validatebox({
		missingMessage: '请输入身份证号码',
		required: true,
		validType : 'IdCard',
	});
	
	$.extend($.fn.validatebox.defaults.rules, {
	    comboVry: {
	        validator: function (value,param) {//param为默认值
	            return value != param;
	        },
	        message: '请选择'
	    }
	});
	
	//开户银行
	$('#spBank').combobox({
		required: true,
		valueField : 'id',
		textField : 'name',
		editable :false,
		data:[{'id':-1,name:'请选择开户银行'}],//2:北京银行
		onLoadSuccess:function(data){
			$('#spBank').combobox('setValue',data[0].id);
		}
		
	});
	//支行
	$('#spBankBranch').combobox({
		required: true,
		valueField : 'id',
		textField : 'name',
		validType:['comboVry["请选择支行"]'],
		data:[{'id':null,name:'请选择支行'}],
		onLoadSuccess:function(data){
			$('#spBankBranch').combobox('setValue',data[0].id);
		}
	});
	//开户地
	//省
	$('#spPro').combobox({
		required: true,
		valueField : 'id',
		textField : 'name',
		editable :false,
		validType:['comboVry["选择省"]'],
		data:[{'id':-1,name:'选择省'}],//2:北京市
		onLoadSuccess:function(data){
			$('#spPro').combobox('setValue',data[0].id);
		},
		onSelect : function(record) {
			if (record.id != -1) {
				$('#spCity').combobox('loadData',record.spBankRegionsChildren);
			} else {
				$('#spCity').combobox('loadData', [ {'id' : -1,name : '选择市'} ]);
			}
			var brankId = $('#spBank').combobox('getValue');
			var cityId = $('#spCity').combobox('getValue');
			bankbranch((brankId == -1 ? 1 : brankId),
					record.id, (cityId == -1 ? 585 : cityId));
		}
	});
	//市
	$('#spCity').combobox({
		required: true,
		valueField : 'id',
		textField : 'name',
		editable :false,
		validType:['comboVry["选择市"]'],
		data:[{'id':-1,name:'选择市'}],//585:北京市
		onLoadSuccess:function(data){
			$('#spCity').combobox('setValue',data[0].id);
		},
		onSelect : function(record) {
			var bankId = $('#spBank').combobox('getValue');
			var proId = $('#spPro').combobox('getValue');
			bankbranch((bankId==-1?1:bankId), (proId == -1 ? 2 : proId),record.id);
		}
	});

	// 开户银行
	$.post('aotudic_bankList.action',// url
	function(bank) {
		bank.unshift({
			'id' : -1,
			'name' : '请选择开户银行'
		});// 1:北京银行
		$('#spBank').combobox(
				{
					data : bank,
					validType : [ 'comboVry["请选择开户银行"]' ],
					onLoadSuccess : function(data) {
						if (data.length) {
							$('#spBank').combobox('setValue', data[0].id);
						}
					},
					onSelect : function(record) {
						var proId = $('#spPro').combobox('getValue');
						var cityId = $('#spCity').combobox('getValue');
						bankbranch(record.id, (proId == -1 ? 2 : proId),
								(cityId == -1 ? 585 : cityId));
					}
				});
	});
	
	// 省
	$.post('aotudic_bankRegionList.action',// url
	function(Pro) {
		Pro.unshift({
			'id' : -1,
			'name' : '选择省'
		});// 2:北京市
		$('#spPro').combobox('loadData',Pro );		
		var bankId = $('#spBank').combobox('getValue');// 2 :北京银行
		var proId = $('#spPro').combobox('getValue');// 2:北京市
		var cityId = $('#spCity').combobox('getValue');// 585:北京市
		bankbranch((bankId == -1 ? 1 : bankId), (proId == -1 ? 2 : proId),
				(cityId == -1 ? 585 : cityId));
	});
	
	
	// 银行卡号
	$('input[name="spAnchorApplicationDetail.spBaId"]').validatebox({
		missingMessage: '请输入银行卡号码',
		required: true,
		validType : 'BankId',
	});
	//开户人姓名
	$('input[name="spAnchorApplicationDetail.spBankUserName"]').validatebox({
		missingMessage: '请输入开户人姓名',
		required: true,
		validType : 'CHS',
	});
	//开户人姓名
	$('input[name="spAnchorApplicationDetail.spMobie"]').validatebox({
		missingMessage: '请输入联系电话',
		required: true,
		validType : 'Mobile',
	});
	
	//代言主播功能栏
	anchorApp_tool = {
			add : function() {
				$.ajax({
					type : 'POST',
					url : 'aotuaanm_addForm.action',
					success : function(data) {
						$('.addform').remove();
						$('#anchorApp_add .save_table').append(data);
						
						//直播平台
						$('#spListation').combobox({
							url:'aotudic_liveStationList.action',
							required: true,
							valueField : 'spId',
							textField : 'spLiStationN',
							editable :false,
							onLoadSuccess:function(data){
								$('#spListation').combobox('setValue',data[0].spId);
							}
						});
						$('#anchorApp_add').dialog('open');
					},
				});
			},
			edit : function() {
			},
			remove : function() {
				var rows = $('#anchorApp').datagrid('getSelections');
				if (rows.length > 0) {
					$.messager.confirm('确定', '你要删除所选的<strong>' + rows.length
							+ '</strong>条记录吗', function(flag) {
						if (flag) {
							var ids=[];
							var map={};
							$.each(rows, function (i, row) {
								ids.push(row.sp_id);
						    });
							map['appIds']=ids;
							// 获取选中条数的id
							$.ajax({
								type : 'POST',
								url : 'aotuaanm_delete.action',
								contentType:'application/json;charset=utf-8',
								data :JSON.stringify(map),
								success : function(data) {
									if (data.code === 0) {
										$('#anchorApp').datagrid('loaded');
										$('#anchorApp').datagrid('reload');
										$('#anchorApp').datagrid('unselectAll');
										$.messager.show({
											title : '提示',
											msg : '代言主播申请已被删除成功'
										});
									} else {
										$('#anchorApp').datagrid('loaded');
										$('#anchorApp').datagrid('reload');
										$('#anchorApp').treegrid('unselectAll');
										$.messager.alert('删除失败', '未知错误，请重试',
												'warning');
									}
								},
							});
						}
					});
				} else if (rows.length == 0) {
					$.messager.alert('警告操作', '删除记录至少选定一条数据！', 'warning');
				}
			},
			// 取消所有选定
			redo : function() {
				$('#anchorApp').datagrid('unselectAll');
			},
			reload : function() {
				$('#anchorApp').datagrid('reload');
			},
	}
		//其他代言主播功能栏
		artistApp_tool = {
				add : function() {
					$.ajax({
						type : 'POST',
						url : 'aotuaarm_addForm.action',
						success : function(data) {
							$('.addform').remove();
							$('#anchorApp_add .save_table').append(data);
							$('#anchorApp_add').dialog('open');
						},
					});
				},
				edit : function() {
				},
				remove : function() {
					var rows = $('#artistApp').datagrid('getSelections');
					if (rows.length > 0) {
						$.messager.confirm('确定', '你要删除所选的<strong>' + rows.length
								+ '</strong>条记录吗', function(flag) {
							if (flag) {
								var ids=[];
								var map={};
								$.each(rows, function (i, row) {
									ids.push(row.sp_id);
							    });
								map['appIds']=ids;
								$.ajax({
									type : 'POST',
									url : 'aotuaarm_delete.action',
									contentType:'application/json;charset=utf-8',
									data :JSON.stringify(map),
									success : function(data) {
										if (data.code === 0) {
											$('#artistApp').datagrid('loaded');
											$('#artistApp').datagrid('reload');
											$('#artistApp').datagrid('unselectAll');
											$.messager.show({
												title : '提示',
												msg : '其他代言主播申请已被删除成功'
											});
										} else {
											$('#artistApp').datagrid('loaded');
											$('#artistApp').datagrid('reload');
											$('#artistApp').datagrid('unselectAll');
											$.messager.alert('删除失败', '未知错误，请重试',
													'warning');
										}
									},
								});
							}
						});
					} else if (rows.length == 0) {
						$.messager.alert('警告操作', '删除记录至少选定一条数据！', 'warning');
					}
				},
				// 取消所有选定
				redo : function() {
					$('#artistApp').datagrid('unselectAll');
				},
				reload : function() {
					$('#artistApp').datagrid('reload');
				},
			}
});

//代言主播申请
function anchorReview(sp_id,sp_AtuId){
	//打开申请代言主播详细窗口
	$('#anchorApp_detail').dialog('open');
	$('#appAnchorId').val(sp_id);
	//加载申请信息
	$('#app_detail').panel({
		fit:true,
		href : 'aotuaanm_applicationDetail.action',
		queryParams:{
			spId:sp_id,
		},
		border : false,
	});
	//加载用户信息
	$('#user_detail').panel({
		fit:true,
		href : 'aotuam_userbDetail.action',
		queryParams:{
			'appAtuId':sp_AtuId,
		},
		border : false,
	});
}

//提交申请审核
function anchorAppReview(sp_id,appStatus){
	$.messager.confirm('提示', '是否提交审核', function(flag) {
		if(flag){
			$.ajax({
				type:'POST',
				url : 'aotuaanm_appReview.action',
				data : {
					'spId':	sp_id,
					'spApplicationResult.spId' : appStatus,
				},
				beforeSend : function(){
					$.messager.progress({
						text : '正在提交审核结果...',
					});
				},
				success : function(data){
					$.messager.progress('close');
					$('#anchorApp_detail').dialog('close');
					$('#anchorApp').datagrid('unselectAll');
					$('#anchorApp').datagrid('reload');
					if(data.code==0){
						$.messager.show({
							title : '提示',
							msg : '审核结果已提交成功'
						});
					}else{
						$.messager.alert('审核结果提交失败', '未知错误，请重试',
						'warning');
					}
				},
			});
		}
	});
}

//其他代言主播
function artistReview(sp_id,sp_AtuId){
	//打开申请代言主播详细窗口
	$('#artistApp_detail').dialog('open');
	$('#appArtistId').val(sp_id);
	//加载申请信息
	$('#artist_app_detail').panel({
		fit:true,
		href : 'aotuaarm_applicationArtistDetail.action',
		queryParams:{
			spId:sp_id,
		},
		border : false,
	});
	//加载用户信息
	$('#artist_user_detail').panel({
		fit:true,
		href : 'aotuam_userbDetail.action',
		queryParams:{
			'appAtuId':sp_AtuId,
		},
		border : false,
	});
}

//提交申请审核
function artistAppReview(sp_id,appStatus){
	$.messager.confirm('提示', '是否提交审核', function(flag) {
		if(flag){
			$.ajax({
				type:'POST',
				url : 'aotuaarm_appReview.action',
				data : {
					'appId':	sp_id,
					'spApplicationResult.spId' : appStatus,
				},
				beforeSend : function(){
					$.messager.progress({
						text : '正在提交审核结果...',
					});
				},
				success : function(data){
					$.messager.progress('close');
					$('#artistApp_detail').dialog('close')
					$('#artistApp').datagrid('unselectAll');
					$('#artistApp').datagrid('reload');
					if(data.code==0){
						$.messager.show({
							title : '提示',
							msg : '审核结果已提交成功'
						});
					}else{
						$.messager.show({
							title : '提示',
							msg : '审核结果提交失败，请重试'
						});
					}
				},
			});
		}
	});
}
//支行列表
function bankbranch(bankId,proId,cityId){
	if(bankId){
		//支行
		$.post(
				'aotudic_bankbanchList.action',//url
				{
					'bankId':bankId,
					'proId':proId,
					'cityId':cityId,
				},//参数
				function(data){
					data.unshift({'id':null,'name':'请选择支行'});
					$('#spBankBranch').combobox('loadData',data);
				});
	}else{
		$('#spBankBranch').combobox('loadData',[{'id':null,'name':'请选择支行'}]);
	}
	
}
