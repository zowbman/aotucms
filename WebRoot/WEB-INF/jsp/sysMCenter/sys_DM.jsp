<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link rel="stylesheet" type="text/css" href="css/aotucms.department.save.css">

<table id="aotudepartment">
</table><!-- aotudepartment结束 -->

<div id="aotudepartment_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<s:a href="javascript:void(0);" action="sysdm_add" cssClass="easyui-linkbutton" plain="true" onclick="aotudepartment_tool.add()">添加</s:a>
		<s:a href="javascript:void(0);" action="sysdm_edit" cssClass="easyui-linkbutton" plain="true" onclick="aotudepartment_tool.edit()">修改</s:a>
		<s:a href="javascript:void(0);"  action="sysdm_delete"  cssClass="easyui-linkbutton" plain="true" onclick="aotudepartment_tool.remove()">删除</s:a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotudepartment_tool.reload()">刷新</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotudepartment_tool.redo()">取消选择</a>
	</div>
	<div style="padding: 0 0 0 7px;color:#333;">
		<!-- 查询帐号:<input type="text" class="textbox" name="user" style="width:110px"> -->
	</div>
</div><!-- aotudepartment_tool结束 -->

<!-- 添加系统部门信息的弹出层 -->
<form id="aotudepartment_add" style="margin:0;padding:25px;color:#333;">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>部门名称:</th>
			<td colspan="3"><input type="text" name="spEpdepartn" class="textbox"></td>
		</tr>
		<tr>
			<th>父节点:</th>
			<td colspan="3"><input type="text" id="departmentParentId" name="spEpdeparent.spId" class="textbox"></td>
		</tr>
	</table>
</form>

<!-- 修改系统部门信息的弹出层 -->
<form id="aotudepartment_edit" style="margin:0;padding:25px;color:#333;">
	<input type="hidden" name="spId">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>部门名称:</th>
			<td colspan="3"><input type="text" name="spEpdepartn" class="textbox"></td>
		</tr>
		<tr>
			<th>父节点:</th>
			<td colspan="3"><input type="text" id="departmentParentId_edit" name="spEpdeparent.spId" class="textbox"></td>
		</tr>
	</table>
</form>

<script type="text/javascript" src="js/sysMCenter/aotucms.sysdm.js"></script>
