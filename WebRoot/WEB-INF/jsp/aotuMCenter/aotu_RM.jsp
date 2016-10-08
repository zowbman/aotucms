<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link rel="stylesheet" type="text/css" href="css/spaotumcenter/aotucms.aoturole.save.css">

<table id="aoturole">
</table><!-- aoturole结束 -->

<div id="aoturole_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<s:a href="javascript:void(0);" action="sysrm_add" cssClass="easyui-linkbutton" plain="true" onclick="aoturole_tool.add()">添加</s:a>
		<s:a href="javascript:void(0);" action="sysrm_edit" cssClass="easyui-linkbutton" plain="true" onclick="aoturole_tool.edit()">修改</s:a>
		<s:a href="javascript:void(0);" action="sysrm_delete" cssClass="easyui-linkbutton" plain="true" onclick="aoturole_tool.remove()">删除</s:a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aoturole_tool.reload()">刷新</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aoturole_tool.redo()">取消选择</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aoturole_tool.rolePriv()">（角色-权限）权限设置</a>
	</div>
	<div style="padding: 0 0 0 7px;color:#333;">
		<!-- 查询帐号:<input type="text" class="textbox" name="user" style="width:110px"> -->
	</div>
</div><!-- aotuuserrole_tool结束 -->

<!-- 添加凹凸空间角色的弹出层 -->
<form id="aoturole_add" style="margin:0;padding:25px;color:#333;">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>角色名称:</th>
			<td colspan="3"><input type="text" name="spRolename" class="textbox"></td>
		</tr>
		<tr>
			<th>父节点:</th>
			<td colspan="3"><input type="text" id="roleParentId" name="spAotuspaceRoleparent.spId" class="textbox"></td>
		</tr>
	</table>
</form>

<!-- 修改系统角色信息的弹出层 -->
<form id="aoturole_edit" style="margin:0;padding:25px;color:#333;">
	<input type="hidden" name="spId">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>角色名称:</th>
			<td colspan="3"><input type="text" name="spRolename" class="textbox"></td>
		</tr>
		<tr>
			<th>父节点:</th>
			<td colspan="3"><input type="text" id="roleParentId_edit" name="spAotuspaceRoleparent.spId" class="textbox"></td>
		</tr>
	</table>
</form>

<!-- 凹凸角色账号管理的弹出层 -->
<form id="aoturole_user" style="margin:0;padding:25px;color:#333;">
	<input id="roleId" type="hidden">
	<div id="role_name">
		<span>角色名称</span>
	</div>
	<div id="role_mark">描述：
		<input type="text" readonly class="textbox">
	</div>
	<div style="margin-bottom:5px;">成员：</div>
	<div id="aoturole_user_content">
		<select id="user_groups" multiple="multiple" size="10"></select>
		<div id="tool">
			<a href="javascript:void(0);" id="user_add">添加</a>
			<a href="javascript:void(0);" id="user_delete">移除</a>
			<a href="javascript:void(0);" id="user_clear">清空</a>
		</div>
	</div>
</form>

<!-- 凹凸添加角色员工的弹出层 -->
<form id="aoturole_addUser" style="margin:0;padding:25px;color:#333;">
</form>

<!--（角色-权限）权限设置 -->
<form id="aotuuserrolepriv_set" style="margin:0;padding:5px;color:#333;">
	<div id="hint">
		<span>温馨提示:请选择相应的操作权限，设置后请点"提交"按钮保存当前的设置。</span>
	</div>
	<div id="userrolePriv"></div>
</form>

<script type="text/javascript" src="js/aotuMCenter/aotucms.aoturm.js"></script>
