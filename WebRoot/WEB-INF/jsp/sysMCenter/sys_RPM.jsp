<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/aotucms.emplrolepriv.save.css">

<div data-options="fit:true" class="easyui-layout">
	<div data-options="region:'west',split:true,title:'角色信息',border:false," style="width:300px;">
		<input type="hidden" id="SelectedRow"><!-- SelectedRow隐藏域 -->
		<table id="aotuemplrole">
		</table><!-- aotumanager结束 -->
		<div id="aotuemplrole_tool" style="padding:5px;">
			<div style="margin-bottom:5px;">
				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotuemplrolepriv_tool.reload()">刷新</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotuemplrolepriv_tool.redo()">取消选择</a>
			</div>
			<!-- <div style="padding: 0 0 0 7px;color:#333;">
				查询帐号:<input type="text" class="textbox" name="user" style="width:110px">
			</div> -->
		</div><!-- aotumanager_tool结束 -->
	</div><!-- 左结束 -->
	<div data-options="region:'center',title:'(角色-操作权限)权限设置',border:false,">
	<input type="hidden" id="isChange" value="0">
		<div id="hint">
			<span>温馨提示:请选择相应的角色进行操作权限设置，设置后请点"保存"按钮保存当前的设置。<s:a action="sysrpm_edit" id="privsave" onclick="savepriv()" href="javascript:void(0);">保存</s:a></span>
		</div>
		<ul id="privTree" style="padding:45px 10px 10px 10px;">
		</ul><!-- privTree结束 -->
	</div><!-- 中间结束 -->
</div>
<!-- <script type="text/javascript" src="easyui/extension/treegrid/treegrid.js"></script> -->
<script type="text/javascript" src="js/sysMCenter/aotucms.sysrpm.js"></script>
