$(function() {
	//组织机构tree
	$('#departNav').tree({
		url : 'sysmm_departTree.action',//请求url
		lines : true,
		//一刷新全部展开
		onLoadSuccess : function(node,data){
			//console.log(data)
			var defaultSelectNode=$('#departNav').tree('find',0);
			$('#departNav').tree('select',defaultSelectNode.target);
		},
		onClick : function(node){
			var selectedId=node.id;
			$('#aotumanager').datagrid('load', { 'spEmployeeDepart.spId': selectedId });
		}
	});
	
	//员工列表
	$('#aotumanager')
			.datagrid(
					{
						url : 'sysmm_listData.action',// url请求地址
						fit : true,
						fitColums : true,
						striped : true,
						rownumbers : true,
						border : false,
						pagination : true,
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50 ],
						pageNumber : 1,
						toolbar : '#aotumanager_tool',
						columns : [ [ {
							field : 'sp_id',
							title : '自动编号',
							width : 100,
							checkbox : true,
							rowspan : 2,
						}, {
							title : '管理员基本信息',
							width : 100,
							colspan : 5,
						} ], [ {
							field : 'sp_EpId',
							title : '员工编号',
							width : 200,
						}, {
							field : 'sp_EpAccount',
							title : '用户名',
							width : 200,
						}, {
							field : 'sp_EpDepart',
							title : '所属组织机构',
							width : 200,
						}, {
							field : 'gender',
							title : '拥有权限',
							width : 200,
						}, {
							field : 'gender',
							title : '最后登录时间',
							width : 200,
						}] ],
						view : detailview,
						detailFormatter : function(rowIndex, rowData) {
							return '<div style="padding:5px"><table class="aotumanagerDetail" style="padding:5px 0 0 10px;"></table></div>';
						},
						onExpandRow : function(index, row) {
							getFaotumanagerDetail(index, row);
							var ExpandRow = $('#ExpandRow').val();
							if (ExpandRow != '') {
								$('#aotumanager').datagrid('collapseRow',
										ExpandRow);
							}
							$('#ExpandRow').val(index);
						},
						onCollapseRow : function(index, row) {
							$('#ExpandRow').val('');
						},
					});
	//员工详细
	function getFaotumanagerDetail(index, row) {
		var aotumanagerDetail = $('#aotumanager').datagrid('getRowDetail',
				index).find('table.aotumanagerDetail');
		aotumanagerDetail.panel({
			href : 'sysmm_detail.action?spEmployeeBinfoKey.spId='+row.sp_id+'&spEmployeeBinfoKey.spEpid='+row.sp_EpId,
			border : false,
			onResize : function() {
				$('#aotumanager').datagrid('fixDetailRowHeight', index);
			},
			onLoad : function() {
				setTimeout(function() {
					$('#aotumanager').datagrid('fixDetailRowHeight', index);
				}, 0);
			}
		});
		$('#aotumanager').datagrid('fixDetailRowHeight', index);
	}
	
	//添加员工
	$('#aotumanager_add').dialog({
		width:800,
		height:570,
		title:'新增系统管理员',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			/*iconCls:''*/
			handler: function(){
				if($('#aotumanager_add').form('validate')){
					//alert('addSuccess');
					//ajax提交
					$.ajax({
						url : 'sysmm_add.action',//请求路径
						type : 'POST',
						data : $('#aotumanager_add').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在新增中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotumanager').datagrid('reload');
								$('#aotumanager_add').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '管理员新增成功!'
								});
							}else{
								$.messager.alert('新增失败','未知错误，请重试','warning',function(){
									$('input[name="spEpaccount"]').select();
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
				$('#aotumanager_add').dialog('close').form('reset');
			}
		}]
	});
	
	//修改员工
	$('#aotumanager_edit').dialog({
		width : 800,
		height: 570,
		title : '修改员工',
		modal:true,
		closed:true,
		/*iconCls : ''*/
		buttons:[{
			text : '提交',
			/*iconCls : ''*/
			handler : function(){
				if($('#aotumanager_edit').form('validate')){
					//ajax提交
					$.ajax({
						url : 'sysmm_editSubmit.action',//请求路径
						type : 'POST',
						data : $('#aotumanager_edit').serialize(),
						beforeSend : function(){
							$.messager.progress({
								text : '正在修改中...',
							});
						},
						success : function(data,response,status){
							$.messager.progress('close');
							if(data.code === 0){//成功
								$('#aotumanager').datagrid('reload');
								$('#aotumanager_edit').dialog('close').form('reset');
								$.messager.show({
									title :'提示',
									msg : '员工修改成功!'
								});
							}else{
								$.messager.alert('修改失败','未知错误，请重试','warning',function(){
									$('input[name="spEpaccount"]').select();
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
				$('#aotumanager_edit').dialog('close').form('reset');
			},
		}]
	});
	
	//星座数据
	$('input[name="spEmployeeDinfo.spEpcon"]').combobox({
		url : 'json/constellation/constellation.json',
		valueField : 'id',
		textField : 'text',
		editable :false,
	});
	
	//生肖数据
	$('input[name="spEmployeeDinfo.spEpanimal"]').combobox({
		url : 'json/chineseZodiac/chineseZodiac.json',
		valueField : 'id',
		textField : 'text',
		editable :false,
	});
	
	//日历
	$('input[name="spEmployeeDinfo.spEpbirth"]').datebox({
		formatter : function(date){
			return date.getFullYear()+"-"+(date.getMonth() + 1)+"-"+date.getDate();
		},
		sharedCalendar : '#birthC',
		editable :false,
	});
	
	$('#birthC').calendar({
		firstDay : 1,
	});
	
	//表单验证
	//管理员帐号
	$('input[name="spEpaccount"]').validatebox({
		required: true,
		validType : 'checkSpEpaccount',
		missingMessage: '请输入系统管理员帐号',
		/*invalidMessage :'系统管理员帐号长度为6-18位',*/
		delay :30000,
	});
	
	//有关表时候把验证关闭
	$('input[name="spEpaccount"]').focus(function() {
		if ($('input[name="spEpaccount"]').val()) {
			$(this).validatebox('disableValidation');
		}
	});
	
	//失去贯标时候把验证打开
	$('input[name="spEpaccount"]').blur(function(){
		$(this).validatebox('enableValidation');
	});
	
	//ajax验证管理员帐号是否重复
	$.extend($.fn.validatebox.defaults.rules,{
		checkSpEpaccount : {
			validator : function(value) {
				var flag=true;
					if (value.length < 6 || value.length > 18) {
						flag=false;
					}else{
						$.ajax({
							url : 'sysmm_checkSpEpAccount.action',//请求路径
							type : 'POST',
							async: false,
							data : {
								spEpaccount : value,
							},
							/*beforeSend : function(){
								$.messager.progress({
									text : '正在验证中...',
								});
							},*/
							success : function(data,response,status){
								/*$.messager.progress('close');*/
								if(data.code != 0){//不成功
									flag=false;
								}
							}
						});
					}
				return flag;
			},
			message:'系统管理员帐号已存在，或长度在6-18位，请重新输入'
		},
		
	});
	
	//验证员工密码
	$('input[name="spEppassword"]').validatebox({
		required: true,
		validType : 'length[6,18]',
		missingMessage: '请输入系统管理员密码',
		invalidMessage :'系统管理员密码在6-18位',
	});
	//验证真实姓名
	$('input[name="spEmployeeDinfo.spEprealname"]').validatebox({
		validType:'length[0,10]',
		invalidMessage:'请输入有效真实姓名,长度小于或等于10位',
	});
	//验证身份证
	$('input[name="spEmployeeDinfo.spEpidnum"]').validatebox({
		validType:'IdCard',
	});
	//验证手机号码
	$('input[name="spEmployeeDinfo.spEpmobie"]').validatebox({
		validType:'Mobile',
	});
	
	//组织机构选择
	$('#depart').combotree({
		url : 'sysmm_departTreeData.action',//请求url
		required : true,
		lines : true,
		//一刷新全部展开
		onLoadSuccess : function(node,data){
			$('#depart').combotree('setValue',0);
		},
	});
	
	//功能栏
	aotucmsmanager_tool = {
			add : function(){
				$('#aotumanager_add').dialog('open');
				$('input[name="spEpaccount"]').focus();
				var checkedNode=$('#departNav').tree('getSelected');
				$('#depart').combotree('setValue',checkedNode.id);
				//星座与生肖
				$('#spEpcon').combobox('setValue',1);
				$('#spEpanimal').combobox('setValue',1);
			},
			edit : function(){
				var rows =$('#aotumanager').datagrid('getSelections');
				if(rows.length>1){
					$.messager.alert('警告操作','编辑记录只能选定一条数据！','warning');
				}else if(rows.length==1){
					$.ajax({
						type:'POST',
						url : 'sysmm_edit.action',
						data : {
							'spEmployeeBinfoKey.spId' : rows[0].sp_id,
							'spEmployeeBinfoKey.spEpid' : rows[0].sp_EpId,
						},
						beforeSend : function(){
							$.messager.progress({
								text : '正在尝试获取数据...',
							});
						},
						success : function(data){
							$.messager.progress('close');
							if(data[0].code==0){
								/*console.log(data[0].data);*/
								var obj=$.parseJSON(data[0].data);
								$('#aotumanager_edit').form('load',{
									'spEmployeeBinfoKey.spId':obj[0].spEmployeeBinfoKey.spId,//id
									'spEmployeeBinfoKey.spEpid':obj[0].spEmployeeBinfoKey.spEpid,//员工Epid
									//'spEmployeeDinfo.spEmployeeBinfoKey.spId' : obj[0].spEmployeeDinfo.spEmployeeBinfoKey.spId,//详细信息id
									//'spEmployeeDinfo.spEmployeeBinfoKey.spEpid' : obj[0].spEmployeeDinfo.spEmployeeBinfoKey.spEpid,//相信信息员工Epid
									'spEpaccount':obj[0].spEpaccount,//用户名
									'spEppassword':obj[0].spEppassword,//密码
									'spEmployeeDinfo.spEprealname':obj[0].spEmployeeDinfo.spEprealname,//真实姓名
									'spEmployeeDinfo.spEpsex':obj[0].spEmployeeDinfo.spEpsex,//性别
									'spEmployeeDinfo.spEpage':obj[0].spEmployeeDinfo.spEpage,//年龄
									'spEmployeeDinfo.spEpbirth':obj[0].spEmployeeDinfo.spEpbirth,//生日
									'spEmployeeDinfo.spEpidnum':obj[0].spEmployeeDinfo.spEpidnum,//身份证
									'spEmployeeDinfo.spEpmobie':obj[0].spEmployeeDinfo.spEpmobie,//手机号码
									'spEmployeeDinfo.spEpcon':obj[0].spEmployeeDinfo.spEpcon,//星座
									'spEmployeeDinfo.spEpanimal':obj[0].spEmployeeDinfo.spEpanimal,//生肖
									'spEmployeeDinfo.spEmpdistrict':obj[0].spEmployeeDinfo.spEmpdistrict,//所在地
									'spEmployeeDinfo.spEpaddress':obj[0].spEmployeeDinfo.spEpaddress,//详细地址
									/*'spEmployeeDepart.spId':obje[0].spEmployeeDepart.spId,*/
								}).dialog('open');
								$('#depart_edit').combotree({
									url : 'sysmm_departTreeData.action',//请求url
									required : true,
									lines : true,
									onLoadSuccess: function(node,data){
										$('#depart_edit').combotree('setValue',obj[0].spEmployeeDepart.spId);
									}
								});
							}
						},
					});
				}else if(rows.length==0){
					$.messager.alert('警告操作','编辑记录至少选定一条数据！','warning');
				}
			},
			remove : function() {
				var rows = $('#aotumanager').datagrid('getSelections');
				if (rows.length > 0) {
					$.messager.confirm('确定', '你要删除所选的<strong>' + rows.length
							+ '</strong>条记录吗', function(flag) {
						if (flag) {
							//获取选中条数的id以及epid
							var empIds=[];
							for(var i=0;i<rows.length;i++){
								var map={};
								map['spId']=rows[i].sp_id;
								map['spEpid']=rows[i].sp_EpId;
								empIds.push(map);
							}
							var map={};
							map['empIds']=empIds;
							$.ajax({
								type : 'POST',
								url : 'sysmm_delete.action',
								contentType:'application/json;charset=utf-8',
								data : JSON.stringify(map),
								beforeSend : function() {
									$('#aotumanager').treegrid('loading');
								},
								success : function(data) {
									if (data.code === 0) {
										$('#aotumanager').datagrid('loaded');
										$('#aotumanager').datagrid('reload');
										$('#aotumanager').datagrid('unselectAll');
										$.messager.show({
											title : '提示',
											msg : '员工已被删除成功'
										});
									} else {
										$('#aotumanager').datagrid('loaded');
										$('#aotumanager').datagrid('reload');
										$('#aotumanager')
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
				$('#aotumanager').datagrid('unselectAll');
			},
			reload : function() {
				$('#aotumanager').datagrid('reload');
				$('#ExpandRow').val('');
			},
	}
});