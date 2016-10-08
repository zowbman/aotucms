<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/spaotumcenter/aotucms.aotuuserpriv.save.css">
<table id="aotuuserpriv">
</table><!-- aotuuserpriv结束 -->
<div id="aotuuserpriv_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<s:a href="javascript:void(0);" action="syspm_add" cssClass="easyui-linkbutton" plain="true" onclick="aotuuserpriv_tool.add()">添加</s:a>
		<s:a href="javascript:void(0);" action="syspm_edit" cssClass="easyui-linkbutton" plain="true" onclick="aotuuserpriv_tool.edit()">修改</s:a>
		<s:a href="javascript:void(0);" action="syspm_delete" cssClass="easyui-linkbutton" plain="true" onclick="aotuuserpriv_tool.remove()">删除</s:a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotuuserpriv_tool.reload()">刷新</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotuuserpriv_tool.redo()">取消选择</a>
	</div>
	<div style="padding: 0 0 0 7px;color:#333;">
	
	<!-- 查询帐号:<input type="text" class="textbox" name="user" style="width:110px"> -->
	</div>
</div><!-- aotuuserpriv_tool结束 -->

<!-- 添加系统操作权限的弹出层 -->
<form id="aotuuserpriv_add" style="margin:0;padding:25px;color:#333;">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>操作权限名称:</th>
			<td colspan="3"><input type="text" name="spName" class="textbox"></td>
		</tr>
		<tr>
			<th>权限地址:</th>
			<td colspan="3"><input type="text" name="spUrl" class="textbox"></td>
		</tr>
		<tr>
			<th>父操作权限:</th>
			<td colspan="3"><input type="text" id="userprivParentId" name="privParent.spId" class="textbox"></td>
		</tr>
	</table>
</form>

<!-- 修改系统操作权限的弹出层-->
<form id="aotuuserpriv_edit" style="margin:0;padding:25px;color:#333;">
	<input type="hidden" name="spId">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>操作权限名称:</th>
			<td colspan="3"><input type="text" name="spName" class="textbox"></td>
		</tr>
		<tr>
			<th>权限地址:</th>
			<td colspan="3"><input type="text" name="spUrl" class="textbox"></td>
		</tr>
		<tr>
			<th>父操作权限:</th>
			<td colspan="3"><input type="text" id="userprivParentId_edit" name="privParent.spId" class="textbox"></td>
		</tr>
	</table>
</form>

<script type="text/javascript" src="js/aotuMCenter/aotucms.aotupm.js"></script>