$(function() {
	$('#anchorApplicationTypetabs1').tabs({
		fit : true,
		border : false,
		plain:true,
		pill:true,
	});
	
	//供应商列表
	$('#anchorApp')
			.datagrid(
					{
						url : 'aotusuam_applicationList.action',// url请求地址
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
							title : '供应商申请列表',
							width : 100,
							colspan : 4,
						} ], [ {
							field : 'sp_SuSup',
							title : '商家名称',
							width : 200,
						}, {
							field : 'sp_SuCont',
							title : '商家联系人姓名',
							width : 200,
						}, {
							field : 'sp_SuTel',
							title : '商家联系电话',
							width : 200,
						},{
							field : 'sp_Opt',
							title : '操作',
							width : 300,
							formatter:function(value,row,index){
				                var btn = '<a class="anchorReview" onclick="anchorReview(\''+row.sp_id+'\','+row.sp_AtuId+')" href="javascript:void(0)">编辑</a>'
				                			+'<a class="anchorReviewY" onclick="anchorAppReview(\''+row.sp_id+'\','+1+')" href="javascript:void(0)">编辑</a>'
				                			+'<a class="anchorReviewN" onclick="anchorAppReview(\''+row.sp_id+'\','+2+')" href="javascript:void(0)">编辑</a>';  
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
				url : 'aotusuam_appliactionArtistList.action',// url请求地址
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
					title : '代言主播申请列表',
					width : 100,
					colspan : 4,
				} ], [ {
					field : 'sp_UniqueCode',
					title : '唯一标识',
					width : 200,
				}, {
					field : 'sp_AtuId',
					title : '凹凸ID',
					width : 200,
				}, {
					field : 'sp_ApplyDate',
					title : '申请时间',
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
			        $('#anchorApp_other').datagrid('fixColumnSize');
			    },
		});
	
	//代言主播申请详细信息
	$('#anchorApp_detail').dialog({
		width:700,
		height:450,
		title:'代言主播申请详细信息',
		modal:true,
		closed:true,
		buttons:[{
			text:'通过',
			iconCls:'',
			handler: function(){
				anchorAppReview($('#appAnchorId').val(),1);
			}
		},{
			text:'不通过',
			iconCls:'',
			handler: function(){
				anchorAppReview($('#appAnchorId').val(),2);
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
		width:700,
		height:450,
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
	
	//功能栏
	anchorApp_tool = {
			add : function() {
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
								url : 'aotuam_delete.action',
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
	

		artistApp_tool = {
				add : function() {
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
									url : 'aotusuam_deleteOther.action',
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
		href : 'aotusuam_applicationDetail.action',
		queryParams:{
			'appDetailId':sp_id,
		},
		border : false,
	});
	//加载用户信息
	$('#user_detail').panel({
		fit:true,
		href : 'aotusuam_userbDetail.action',
		queryParams:{
			'appAtuId':sp_AtuId,
		},
		border : false,
	});
}
//提交申请审核
function anchorAppReview(sp_id,appStatus){
	$.messager.confirm('提示', '是否提交审核申请', function(flag) {
		if(flag){
			$.ajax({
				type:'POST',
				url : 'aotusuam_appReview.action',
				data : {
					'appId':	sp_id,
					'appStatus' : appStatus,
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

//其他代言主播
function artistReview(sp_id,sp_AtuId){
	//打开申请代言主播详细窗口
	$('#artistApp_detail').dialog('open');
	$('#appArtistId').val(sp_id);
	//加载申请信息
	$('#artist_app_detail').panel({
		fit:true,
		href : 'aotusuam_applicationArtistDetail.action',
		queryParams:{
			'appDetailId':sp_id,
		},
		border : false,
	});
	//加载用户信息
	$('#artist_user_detail').panel({
		fit:true,
		href : 'aotusuam_artistUserDetail.action',
		queryParams:{
			'appAtuId':sp_AtuId,
		},
		border : false,
	});
}

//提交申请审核
function artistAppReview(sp_id,appStatus){
	$.messager.confirm('提示', '是否通过此申请', function(flag) {
		if(flag){
			$.ajax({
				type:'POST',
				url : 'aotusuam_appReviewArtist.action',
				data : {
					'appId':	sp_id,
					'appStatus' : appStatus,
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
