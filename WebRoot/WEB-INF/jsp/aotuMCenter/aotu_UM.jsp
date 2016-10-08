<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/spaotumcenter/aotucms.aotuuser.save.css">

<input type="hidden" id="UserExpandRow"><!-- ExpandRow隐藏域 -->
<table id="aotuuser">
</table><!-- aotuuser结束 -->
<div id="aotuuser_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<s:a href="javascript:void(0);" action="sysmm_add" cssClass="easyui-linkbutton" plain="true" onclick="aotucmsuser_tool.add()">添加</s:a>
		<s:a href="javascript:void(0);" action="sysmm_edit" cssClass="easyui-linkbutton" plain="true" onclick="aotucmsuser_tool.edit()">修改</s:a>
		<s:a href="javascript:void(0);" action="sysmm_delete" cssClass="easyui-linkbutton" plain="true" onclick="aotucmsuser_tool.remove()">删除</s:a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotucmsuser_tool.reload()">刷新</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotucmsuser_tool.redo()">取消选择</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotucmsuser_tool.userPriv()">（用户-权限）权限设置</a>
	</div>
<div style="padding: 0 0 0 7px;color:#333;">
		<!-- 查询帐号:<input type="text" class="textbox" name="user" style="width:110px"> -->
	</div>
</div><!-- aotuuser_tool结束 -->

<!-- 添加用户信息的弹出层 -->
<form id="aotuuser_add" style="margin:0;padding:25px;color:#333;">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>用户名:</th>
			<td colspan="1"><input type="text" name="spAccount" class="textbox"></td>
			<td rowspan="3" colspan="2" >头像</td>
		</tr>
		<tr>
			<th>密码:</th>
			<td colspan="1"><input type="text" name="spPassword" class="textbox"></td>
		</tr>
		<tr>
			<th>昵称</th>
			<td colspan="1"><input type="text" id="depart" name="spUsersDinfo.spNickname" class="textbox"></td>
		</tr>

		<tr>
			<th>姓名:</th>
			<td><input type="text" name="spUsersDinfo.spRealname" class="textbox"></td>
			<th>性别:</th>
			<td><input type="text" name="spUsersDinfo.spSex" class="textbox"></td>
		</tr>
		<tr>
			<th>年龄:</th>
			<td><input type="text" name="spUsersDinfo.spAge" class="textbox"></td>
			<th>出生日期:</th>
			<td>
				<input type="text" name="spUsersDinfo.spBirth"  class="textbox">
				<div id="birthU"></div>
			</td>
		</tr>
		<tr>
			<th>星座:</th>
			<td><input type="text" id="spEpcon" name="spUsersDinfo.spCon" class="textbox"></td>
			<th>生肖:</th>
			<td><input type="text" id="spEpanimal" name="spUsersDinfo.spAnimal" class="textbox"></td>
		</tr>
		<tr >
			<th>所在地:</th>
			<td colspan="3"><textarea name="spUsersDinfo.spUserdistrict" class="textbox textarea"></textarea></td>
		</tr>
		<tr>
			<th>详细:</th>
			<td colspan="3"><textarea name="spUsersDinfo.spAddress" class="textbox textarea"></textarea></td>
		</tr>
	</table>
</form>
		
<!-- 修改系统管理员信息弹出层 -->
	<form id="aotuuser_edit" style="margin:0;padding:25px;color:#333;">
	<input type="hidden" name="spUsersBinfoKey.spId">
	<input type="hidden" name="spUsersBinfoKey.spAtuid">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>用户名:</th>
			<td colspan="1"><input type="text" disabled="true" name="spAccount" class="textbox"></td>
			<td rowspan="3" colspan="2" >头像</td>
		</tr>
		<tr>
			<th>密码:</th>
			<td colspan="1"><input type="text" name="spPassword" class="textbox"></td>
		</tr>
		<tr>
			<th>昵称</th>
			<td colspan="1"><input type="text" id="depart" name="spUsersDinfo.spNickname" class="textbox"></td>
		</tr>

		<tr>
			<th>姓名:</th>
			<td><input type="text" name="spUsersDinfo.spRealname" class="textbox"></td>
			<th>性别:</th>
			<td><input type="text" name="spUsersDinfo.spSex" class="textbox"></td>
		</tr>
		<tr>
			<th>年龄:</th>
			<td><input type="text" name="spUsersDinfo.spAge" class="textbox"></td>
			<th>出生日期:</th>
			<td>
				<input type="text" name="spUsersDinfo.spBirth"  class="textbox">
				<div id="birthU"></div>
			</td>
		</tr>
		<tr>
			<th>星座:</th>
			<td><input type="text" id="spEpcon" name="spUsersDinfo.spCon" class="textbox"></td>
			<th>生肖:</th>
			<td><input type="text" id="spEpanimal" name="spUsersDinfo.spAnimal" class="textbox"></td>
		</tr>
		<tr >
			<th>所在地:</th>
			<td colspan="3"><textarea name="spUsersDinfo.spUserdistrict" class="textbox textarea"></textarea></td>
		</tr>
		<tr>
			<th>详细:</th>
			<td colspan="3"><textarea name="spUsersDinfo.spAddress" class="textbox textarea"></textarea></td>
		</tr>
	</table>
</form>

<!--（角色-权限）权限设置 -->
<form id="aotuuserpriv_set" style="margin:0;padding:5px;color:#333;">
	<div id="hint">
		<span>温馨提示:请选择相应的操作权限，设置后请点"提交"按钮保存当前的设置。</span>
	</div>
	<div id="userPriv"></div>
</form>

<!-- 代言主播申请记录 -->
<form id="anchorApplicationLog" style="margin:0;padding:25px;color:#333;">
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

<script type="text/javascript" src="easyui/extension/jquery-easyui-datagridview/datagrid-detailview.js"></script>
<script type="text/javascript" src="easyui/extension/validatebox/validatebox.js"></script>
<script type="text/javascript" src="js/aotuMCenter/aotucms.aotuum.js"></script>

