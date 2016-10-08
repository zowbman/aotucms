<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/spaotumcenter/aotucms.aotuuserident.save.css">

<table id="aotuuserident">
</table><!-- aotuuser结束 -->
<div id="aotucmsuserident_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<s:a href="javascript:void(0);" action="sysmm_add" cssClass="easyui-linkbutton" plain="true" onclick="aotucmsuserident_tool.add()">添加</s:a>
		<s:a href="javascript:void(0);" action="sysmm_edit" cssClass="easyui-linkbutton" plain="true" onclick="aotucmsuserident_tool.edit()">修改</s:a>
		<s:a href="javascript:void(0);" action="sysmm_delete" cssClass="easyui-linkbutton" plain="true" onclick="aotucmsuserident_tool.remove()">删除</s:a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotucmsuserident_tool.reload()">刷新</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotucmsuserident_tool.redo()">取消选择</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotucmsuserident_tool.identPriv()">（身份-权限）权限设置</a>
	</div>
<div style="padding: 0 0 0 7px;color:#333;">
		<!-- 查询帐号:<input type="text" class="textbox" name="user" style="width:110px"> -->
	</div>
</div><!-- aotuuser_tool结束 -->

<!-- 添加用户身份的弹出层 -->
<form id="aotuuserident_add" style="margin:0;padding:25px;color:#333;">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>身份名称:</th>
			<td colspan="1"><input type="text" name="spIdentityn" class="textbox"></td>
			<input type="text" style="display: none;" >
		</tr>
	</table>
</form>
		
<!-- 修改用户身份弹出层 -->
<form id="aotuuserident_edit" style="margin:0;padding:25px;color:#333;">
	<input type="hidden" name="spId">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>身份名称:</th>
			<td colspan="1"><input type="text" name="spIdentityn" class="textbox"></td>
			<input type="text" style="display: none;" >
		</tr>
	</table>
</form>

<!--（身份-权限）权限设置 -->
<form id="aotuuseridentpriv_set" style="margin:0;padding:5px;color:#333;">
	<div id="hint">
		<span>温馨提示:请选择相应的操作权限，设置后请点"提交"按钮保存当前的设置。</span>
	</div>
	<div id="useridentPriv"></div>
</form>


<script type="text/javascript" src="js/aotuMCenter/aotucms.aotuuim.js"></script>

