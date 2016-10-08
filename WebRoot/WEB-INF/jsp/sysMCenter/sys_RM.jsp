<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link rel="stylesheet" type="text/css" href="css/aotucms.station.save.css">

<table id="aotustation">
</table><!-- aotustation结束 -->

<div id="aotustation_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<s:a href="javascript:void(0);" action="sysrm_add" cssClass="easyui-linkbutton" plain="true" onclick="aotustation_tool.add()">添加</s:a>
		<s:a href="javascript:void(0);" action="sysrm_edit" cssClass="easyui-linkbutton" plain="true" onclick="aotustation_tool.edit()">修改</s:a>
		<s:a href="javascript:void(0);" action="sysrm_delete" cssClass="easyui-linkbutton" plain="true" onclick="aotustation_tool.remove()">删除</s:a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotustation_tool.reload()">刷新</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotustation_tool.redo()">取消选择</a>
	</div>
	<div style="padding: 0 0 0 7px;color:#333;">
		<!-- 查询帐号:<input type="text" class="textbox" name="user" style="width:110px"> -->
	</div>
</div><!-- aotustation_tool结束 -->

<!-- 添加系统部门信息的弹出层 -->
<form id="aotustation_add" style="margin:0;padding:25px;color:#333;">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>角色名称:</th>
			<td colspan="3"><input type="text" name="spEpstn" class="textbox"></td>
		</tr>
		<tr>
			<th>父节点:</th>
			<td colspan="3"><input type="text" id="stationParentId" name="spEpstparent.spId" class="textbox"></td>
		</tr>
	</table>
</form>

<!-- 修改系统角色信息的弹出层 -->
<form id="aotustation_edit" style="margin:0;padding:25px;color:#333;">
	<input type="hidden" name="spId">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>角色名称:</th>
			<td colspan="3"><input type="text" name="spEpstn" class="textbox"></td>
		</tr>
		<tr>
			<th>父节点:</th>
			<td colspan="3"><input type="text" id="stationParentId_edit" name="spEpstparent.spId" class="textbox"></td>
		</tr>
	</table>
</form>

<!-- 系统角色员工管理的弹出层 -->
<form id="aotustation_empl" style="margin:0;padding:25px;color:#333;">
	<input id="stationId" type="hidden">
	<div id="role_name">
		<span>角色名称</span>
	</div>
	<div id="role_mark">描述：
		<input type="text" readonly class="textbox">
	</div>
	<div style="margin-bottom:5px;">成员：</div>
	<div id="aotustaion_empl_content">
		<select id="empl_groups" multiple="multiple" size="10"></select>
		<div id="tool">
			<a href="javascript:void(0);" id="empl_add">添加</a>
			<a href="javascript:void(0);" id="empl_delete">移除</a>
			<a href="javascript:void(0);" id="empl_clear">清空</a>
		</div>
	</div>
</form>

<!-- 系统添加角色员工的弹出层 -->
<form id="aotustation_addEmpl" style="margin:0;padding:25px;color:#333;">
</form>

<script type="text/javascript" src="js/sysMCenter/aotucms.sysrm.js"></script>
