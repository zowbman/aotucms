<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/aotucms.emplpriv.save.css">
<input type="hidden" id="ExpandRow"><!-- ExpandRow隐藏域 -->
<table id="aotuemplpriv">
</table><!-- aotumanager结束 -->
<div id="aotuemplpriv_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<s:a href="javascript:void(0);" action="syspm_add" cssClass="easyui-linkbutton" plain="true" onclick="aotuemplpriv_tool.add()">添加</s:a>
		<s:a href="javascript:void(0);" action="syspm_edit" cssClass="easyui-linkbutton" plain="true" onclick="aotuemplpriv_tool.edit()">修改</s:a>
		<s:a href="javascript:void(0);" action="syspm_delete" cssClass="easyui-linkbutton" plain="true" onclick="aotuemplpriv_tool.remove()">删除</s:a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotuemplpriv_tool.reload()">刷新</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotuemplpriv_tool.redo()">取消选择</a>
	</div>
	<div style="padding: 0 0 0 7px;color:#333;">
	
	<!-- 查询帐号:<input type="text" class="textbox" name="user" style="width:110px"> -->
	</div>
</div><!-- aotumanager_tool结束 -->

<!-- 添加系统操作权限的弹出层 -->
<form id="aotuemplpriv_add" style="margin:0;padding:25px;color:#333;">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>操作权限名称:</th>
			<td colspan="3"><input type="text" name="spEpname" class="textbox"></td>
		</tr>
		<tr>
			<th>权限地址:</th>
			<td colspan="3"><input type="text" name="spEpurl" class="textbox"></td>
		</tr>
		<tr>
			<th>父操作权限:</th>
			<td colspan="3"><input type="text" id="emplprivParentId" name="spEpparent.spId" class="textbox"></td>
		</tr>
	</table>
</form>

<!-- 修改系统操作权限的弹出层-->
<form id="aotuemplpriv_edit" style="margin:0;padding:25px;color:#333;">
	<input type="hidden" name="spId">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>操作权限名称:</th>
			<td colspan="3"><input type="text" name="spEpname" class="textbox"></td>
		</tr>
		<tr>
			<th>权限地址:</th>
			<td colspan="3"><input type="text" name="spEpurl" class="textbox"></td>
		</tr>
		<tr>
			<th>父操作权限:</th>
			<td colspan="3"><input type="text" id="emplprivParentId_edit" name="spEpparent.spId" class="textbox"></td>
		</tr>
	</table>
</form>

<script type="text/javascript" src="js/sysMCenter/aotucms.syspm.js"></script>