<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/aotucms.manager.save.css">
<div data-options="fit:true" class="easyui-layout">
	<div data-options="region:'west',split:true,title:'组织机构'," style="width:250px;padding:10px;">
		<ul id="departNav"></ul><!-- departNav结束 -->
	</div>
	<!-- 左结束 -->
	<div data-options="region:'center',title:'员工管理'">
			<input type="hidden" id="ExpandRow"><!-- ExpandRow隐藏域 -->
		<table id="aotumanager">
		</table><!-- aotumanager结束 -->
		<div id="aotumanager_tool" style="padding:5px;">
			<div style="margin-bottom:5px;">
				<s:a href="javascript:void(0);" action="sysmm_add" cssClass="easyui-linkbutton" plain="true" onclick="aotucmsmanager_tool.add()">添加</s:a>
				<s:a href="javascript:void(0);" action="sysmm_edit" cssClass="easyui-linkbutton" plain="true" onclick="aotucmsmanager_tool.edit()">修改</s:a>
				<s:a href="javascript:void(0);" action="sysmm_delete" cssClass="easyui-linkbutton" plain="true" onclick="aotucmsmanager_tool.remove()">删除</s:a>
				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotucmsmanager_tool.reload()">刷新</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotucmsmanager_tool.redo()">取消选择</a>
			</div>
			<div style="padding: 0 0 0 7px;color:#333;">
				<!-- 查询帐号:<input type="text" class="textbox" name="user" style="width:110px"> -->
			</div>
		</div><!-- aotumanager_tool结束 -->
		
		<!-- 添加系统管理员信息的弹出层 -->
		<form id="aotumanager_add" style="margin:0;padding:25px;color:#333;">
			<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
				<tr>
					<th>用户名:</th>
					<td colspan="1"><input type="text" name="spEpaccount" class="textbox"></td>
					<td rowspan="3" colspan="2" >头像</td>
				</tr>
				<tr>
					<th>密码:</th>
					<td colspan="1"><input type="text" name="spEppassword" class="textbox"></td>
				</tr>
				<tr>
					<th>所属组织机构:</th>
					<td colspan="1"><input type="text" id="depart" name="spEmployeeDepart.spId" class="textbox"></td>
				</tr>
		
				<tr>
					<th>姓名:</th>
					<td><input type="text" name="spEmployeeDinfo.spEprealname" class="textbox"></td>
					<th>性别:</th>
					<td><input type="text" name="spEmployeeDinfo.spEpsex" class="textbox"></td>
				</tr>
				<tr>
					<th>年龄:</th>
					<td><input type="text" name="spEmployeeDinfo.spEpage" class="textbox"></td>
					<th>出生日期:</th>
					<td>
						<input type="text" name="spEmployeeDinfo.spEpbirth"  class="textbox">
						<div id="birthC"></div>
					</td>
				</tr>
				<tr>
					<th>身份证:</th>
					<td><input type="text" name="spEmployeeDinfo.spEpidnum" class="textbox"></td>
					<th>手机号码:</th>
					<td><input type="text" name="spEmployeeDinfo.spEpmobie"  class="textbox"></td>
				</tr>
				</tr>
				<tr>
					<th>星座:</th>
					<td><input type="text" id="spEpcon" name="spEmployeeDinfo.spEpcon" class="textbox"></td>
					<th>生肖:</th>
					<td><input type="text" id="spEpanimal" name="spEmployeeDinfo.spEpanimal" class="textbox"></td>
				</tr>
				<tr >
					<th>所在地:</th>
					<td colspan="3"><textarea name="spEmployeeDinfo.spEmpdistrict" class="textbox textarea"></textarea></td>
				</tr>
				<tr>
					<th>详细:</th>
					<td colspan="3"><textarea name="spEmployeeDinfo.spEpaddress" class="textbox textarea"></textarea></td>
				</tr>
			</table>
		</form>
		
		<!-- 修改系统管理员信息弹出层 -->
		<form id="aotumanager_edit" style="margin:0;padding:25px;color:#333;">
			<input type="hidden" name="spEmployeeBinfoKey.spId">
			<input type="hidden" name="spEmployeeBinfoKey.spEpid">
			<!-- <input type="hidden" name="spEmployeeDinfo.spEmployeeBinfoKey.spId"> -->
			<!-- <input type="hidden" name="spEmployeeDinfo.spEmployeeBinfoKey.spEpid"> -->
			<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
				<tr>
					<th>用户名:</th>
					<td colspan="1"><input type="text" disabled="true" name="spEpaccount" class="textbox"></td>
					<td rowspan="3" colspan="2" >头像</td>
				</tr>
				<tr>
					<th>密码:</th>
					<td colspan="1"><input type="text" name="spEppassword" class="textbox"></td>
				</tr>
				<tr>
					<th>所属组织机构:</th>
					<td><input colspan="1" type="text" id="depart_edit" name="spEmployeeDepart.spId" class="textbox"></td>
				</tr>
		
				<tr>
					<th>姓名:</th>
					<td><input type="text" name="spEmployeeDinfo.spEprealname" class="textbox"></td>
					<th>性别:</th>
					<td><input type="text" name="spEmployeeDinfo.spEpsex" class="textbox"></td>
				</tr>
				<tr>
					<th>年龄:</th>
					<td><input type="text" name="spEmployeeDinfo.spEpage" class="textbox"></td>
					<th>出生日期:</th>
					<td>
						<input type="text" name="spEmployeeDinfo.spEpbirth"  class="textbox">
						<div id="birthC"></div>
					</td>
				</tr>
				<tr>
					<th>身份证:</th>
					<td><input type="text" name="spEmployeeDinfo.spEpidnum" class="textbox"></td>
					<th>手机号码:</th>
					<td><input type="text" name="spEmployeeDinfo.spEpmobie"  class="textbox"></td>
				</tr>
				</tr>
				<tr>
					<th>星座:</th>
					<td><input type="text" name="spEmployeeDinfo.spEpcon" class="textbox"></td>
					<th>生肖:</th>
					<td><input type="text" name="spEmployeeDinfo.spEpanimal" class="textbox"></td>
				</tr>
				<tr >
					<th>所在地:</th>
					<td colspan="3"><textarea name="spEmployeeDinfo.spEmpdistrict" class="textbox textarea"></textarea></td>
				</tr>
				<tr>
					<th>详细:</th>
					<td colspan="3"><textarea name="spEmployeeDinfo.spEpaddress" class="textbox textarea"></textarea></td>
				</tr>
			</table>
		</form>
	</div><!-- 中间结束 -->
</div>
<script type="text/javascript" src="easyui/extension/jquery-easyui-datagridview/datagrid-detailview.js"></script>
<script type="text/javascript" src="easyui/extension/validatebox/validatebox.js"></script>
<script type="text/javascript" src="js/sysMCenter/aotucms.sysmm.js"></script>

