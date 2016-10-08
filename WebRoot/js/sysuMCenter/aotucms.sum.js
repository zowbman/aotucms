$(function() {
	//供应商账号列表
	$('#aotusupplier')
			.datagrid(
					{
						url : 'aotusum_listData.action',// url请求地址
						fit : true,
						fitColums : true,
						striped : true,
						rownumbers : true,
						border : false,
						pagination : true,
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50 ],
						pageNumber : 1,
						toolbar : '#aotusum_tool',
						columns : [ [ {
							field : 'sp_id',
							title : '自动编号',
							width : 100,
							checkbox : true,
							rowspan : 2,
						}, {
							title : '供应商基本信息',
							width : 100,
							colspan : 7,
						} ], [ {
							field : 'sp_SuId',
							title : '供应商ID',
							width : 200,
						}, {
							field : 'sp_SuAccount',
							title : '供应商账号',
							width : 200,
						}, {
							field : 'sp_SuSup',
							title : '商家名称',
							width : 200,
						}, {
							field : 'sp_SuCont',
							title : '商家联系人姓名',
							width : 200,
						}, {
							field : 'sp_SuMobie',
							title : '商家手机号码',
							width : 200,
						},{
							field : 'sp_SuTel',
							title : '商家联系电话',
							width : 200,
						},{
							field : 'sp_SuDistrict',
							title : '商家所在地',
							width : 200,
						}] ],
						view : detailview,
						detailFormatter : function(rowIndex, rowData) {
							return '<div style="padding:5px"><table class="aotuuserDetail" style="padding:5px 0 0 10px;"></table></div>';
						},
						onExpandRow : function(index, row) {
							getFaotuuserDetail(index, row);
							var SupplierExpandRow = $('#SupplierExpandRow').val();
							if (SupplierExpandRow != '') {
								$('#aotusupplier').datagrid('collapseRow',
										SupplierExpandRow);
							}
							$('#SupplierExpandRow').val(index);
						},
						onCollapseRow : function(index, row) {
							$('#SupplierExpandRow').val('');
						},
					});
	//供应商详细
	function getFaotuuserDetail(index, row) {
		//console.log(row);
		var aotumanagerDetail = $('#aotusupplier').datagrid('getRowDetail',
				index).find('table.aotuuserDetail');
		aotumanagerDetail.panel({
			href : 'aotusum_detail.action?spSupplierBinfoKey.spId='+row.sp_id+'&spSupplierBinfoKey.spSuid='+row.sp_SuId,
			border : false,
			onResize : function() {
				$('#aotusupplier').datagrid('fixDetailRowHeight', index);
			},
			onLoad : function() {
				setTimeout(function() {
					$('#aotusupplier').datagrid('fixDetailRowHeight', index);
				}, 0);
			}
		});
		$('#aotusupplier').datagrid('fixDetailRowHeight', index);
	}
	
	//添加用户
	$('#aotusum_add').dialog({
		width:800,
		height:530,
		title:'新增供应商（注意）',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			iconCls:'',
			handler: function(){
				if($('#aotusum_add').form('validate')){
					//alert('addSuccess');
					//ajax提交
					$.ajax({
						url : 'aotusum_add.action',//请求路径
						type : 'POST',
						data : $('#aotusum_add').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在新增中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotusupplier').datagrid('reload');
								$('#aotusum_add').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '供应商帐号新增成功!'
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
				$('#aotusum_add').dialog('close').form('reset');
			}
		}],
	});
	
	//修改用户
	$('#aotusum_edit').dialog({
		width : 800,
		height: 530,
		title : '修改供应商（注意）',
		modal:true,
		closed:true,
		iconCls : '',
		buttons:[{
			text : '提交',
			iconCls : '',
			handler : function(){
				if($('#aotusum_edit').form('validate')){
					//ajax提交
					$.ajax({
						url : 'aotusum_editSubmit.action',//请求路径
						type : 'POST',
						data : $('#aotusum_edit').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在修改中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotusupplier').datagrid('reload');
								$('#aotusum_edit').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '供应商修改成功!'
								});
							}else{
								$.messager.alert('修改失败','未知错误，请重试','warning',function(){
									$('input[name="spSupassword"]').select();
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
				$('#aotusum_edit').dialog('close').form('reset');
			},
		}]
	});
	
	/*//星座数据
	$('input[name="spSupplierDinfo.spCon"]').combobox({
		url : 'json/constellation/constellation.json',
		valueField : 'id',
		textField : 'text',
		editable :false,
	});
	
	//生肖数据
	$('input[name="spSupplierDinfo.spAnimal"]').combobox({
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
	});*/
	
	//表单验证
	//供应商帐号
	$('input[name="spSuaccount"]').validatebox({
		required: true,
		validType : 'checkSpEpaccount',
		missingMessage: '请输入供应商帐号',
		invalidMessage :'您的帐号名称已重复或供应商帐号长度为6-18位,',
		delay :30000,
	});
	
	//有关表时候把验证关闭
	$('input[name="spSuaccount"]').focus(function() {
		if ($('input[name="spSuaccount"]').val()) {
			$(this).validatebox('disableValidation');
		}
	});
	
	//失去贯标时候把验证打开
	$('input[name="spSuaccount"]').blur(function(){
		$(this).validatebox('enableValidation');
	});
	
	//ajax验证供应商帐号是否重复
	$.extend($.fn.validatebox.defaults.rules,{
		checkSpEpaccount : {
			validator : function(value) {
				var flag=true;
					if (value.length < 6 || value.length > 18) {
						flag=false;
					}else{
						$.ajax({
							url : 'aotusum_checkSuAccount.action',//请求路径
							type : 'POST',
							async: false,
							data : {
								spSuaccount : value,
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
			message:'供应商已存在，或长度在6-18位，请重新输入'
		},
		
	});
	
	//验证供应商密码
	$('input[name="spSupassword"]').validatebox({
		required: true,
		validType : 'length[6,18]',
		missingMessage: '请输入供应商密码',
		invalidMessage :'供应商密码在6-18位',
	});
	//验证真实姓名
	$('input[name="spSupplierDinfo.spSucont"]').validatebox({
		validType:'length[0,10]',
		invalidMessage:'请输入有效真实姓名,长度小于或等于10位',
	});
	
	//功能栏
	aotusum_tool = {
			add : function(){
				$('#aotusum_add').dialog('open');
				$('input[name="spSuaccount"]').focus();
				/*//星座与生肖
				$('#spEpcon').combobox('setValue',1);
				$('#spEpanimal').combobox('setValue',1);*/
			},
			edit : function(){
				var rows =$('#aotusupplier').datagrid('getSelections');
				if(rows.length>1){
					$.messager.alert('警告操作','编辑记录只能选定一条数据！','warning');
				}else if(rows.length==1){
					$.ajax({
						type:'POST',
						url : 'aotusum_edit.action',
						data : {
							'spSupplierBinfoKey.spId' : rows[0].sp_id,
							'spSupplierBinfoKey.spSuid' : rows[0].sp_SuId,
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
								$('#aotusum_edit').form('load',{
									'spSupplierBinfoKey.spId':obj[0].spSupplierBinfoKey.spId,//id
									'spSupplierBinfoKey.spSuid':obj[0].spSupplierBinfoKey.spSuid,//员工Epid
									'spSuaccount':obj[0].spSuaccount,//供应商名
									'spSupassword':obj[0].spSupassword,//密码
									'spSupplierDinfo.spSusup':obj[0].spSupplierDinfo.spSusup,//商家名称
									'spSupplierDinfo.spSucont':obj[0].spSupplierDinfo.spSucont,//商家联系人姓名
									'spSupplierDinfo.spSutel':obj[0].spSupplierDinfo.spSutel,//商家联系电话
									'spSupplierDinfo.spSumobie':obj[0].spSupplierDinfo.spSumobie,//商家手机号码
									'spSupplierDinfo.spSudistrict':obj[0].spSupplierDinfo.spSudistrict,//商家所在地
									'spSupplierDinfo.spSuaddress':obj[0].spSupplierDinfo.spSuaddress,//商家详细地址
									'spSupplierDinfo.spSutraid':obj[0].spSupplierDinfo.spSutraid,//商家所属行业ID
									'spSupplierDinfo.spSuresume':obj[0].spSupplierDinfo.spSuresume,//商家简介
									'spSupplierDinfo.spSulogo':obj[0].spSupplierDinfo.spSulogo,//商家logo
								}).dialog('open');
							}
						},
					});
				}else if(rows.length==0){
					$.messager.alert('警告操作','编辑记录至少选定一条数据！','warning');
				}
			},
			remove : function() {
				var rows = $('#aotusupplier').datagrid('getSelections');
				if (rows.length > 0) {
					$.messager.confirm('确定', '你要删除所选的<strong>' + rows.length
							+ '</strong>条记录吗', function(flag) {
						if (flag) {
							//获取选中条数的id以及supid
							var supIds=[];
							for(var i=0;i<rows.length;i++){
								var map={};
								map['spId']=rows[i].sp_id;
								map['spSuid']=rows[i].sp_SuId;
								supIds.push(map);
							}
							var map={};
							map['supIds']=supIds;
							$.ajax({
								type : 'POST',
								url : 'aotusum_delete.action',
								contentType:'application/json;charset=utf-8',
								data : JSON.stringify(map),
								beforeSend : function() {
									$('#aotusupplier').treegrid('loading');
								},
								success : function(data) {
									if (data.code === 0) {
										$('#aotusupplier').datagrid('loaded');
										$('#aotusupplier').datagrid('reload');
										$('#aotusupplier').datagrid('unselectAll');
										$.messager.show({
											title : '提示',
											msg : '供应商已被删除成功'
										});
									} else {
										$('#aotusupplier').datagrid('loaded');
										$('#aotusupplier').datagrid('reload');
										$('#aotusupplier')
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
				$('#aotusupplier').datagrid('unselectAll');
			},
			reload : function() {
				$('#aotusupplier').datagrid('reload');
				$('#SupplierExpandRow').val('');
			},
	}
});