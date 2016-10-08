<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/spaotumcenter/aotucms.aotuanchor.save.css">

<div id="anchorApplicationTypetabs">
	<div title="代言主播申请" id="anchorApp-tab" style="display:block;overflow:hidden;">
		<!-- 代言主播申请 -->
		<table id="anchorApp">
		</table><!-- anchorApp结束 -->
	</div>
	<div title="其他代言主播申请" id="artistApp-tab" style="display:block;overflow:hidden;">
		<!-- 其他代言主播申请 -->
		<table id="artistApp">
		</table><!-- artistApp结束 -->
	</div>
</div><!-- anchorApplicationTypetabs结束 -->

<!-- 申请代言主播工具栏 -->
<div id="anchorApp_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<!-- 
		<s:a href="javascript:void(0);" action="syspm_add" cssClass="easyui-linkbutton" plain="true" onclick="anchorApp_tool.add()">添加</s:a>
		<s:a href="javascript:void(0);" action="syspm_edit" cssClass="easyui-linkbutton" plain="true" onclick="anchorApp_tool.edit()">修改</s:a> 
		-->
		<s:a href="javascript:void(0);" action="syspm_add" cssClass="easyui-linkbutton" plain="true" onclick="anchorApp_tool.add()">添加</s:a>
		<s:a href="javascript:void(0);" action="syspm_delete" cssClass="easyui-linkbutton" plain="true" onclick="anchorApp_tool.remove()">删除</s:a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="anchorApp_tool.reload()">刷新</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="anchorApp_tool.redo()">取消选择</a>
	</div>
	<div style="padding: 0 0 0 7px;color:#333;">
	<!-- 查询帐号:<input type="text" class="textbox" name="user" style="width:110px"> -->
	</div>
</div><!-- anchorApp_tool结束 -->

<!-- 申请其他代言主播工具栏 -->
<div id="artistApp_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<!--
		<s:a href="javascript:void(0);" action="syspm_add" cssClass="easyui-linkbutton" plain="true" onclick="anchorApp_other_tool.add()">添加</s:a>
		<s:a href="javascript:void(0);" action="syspm_edit" cssClass="easyui-linkbutton" plain="true" onclick="anchorApp_other_tool.edit()">修改</s:a>
		-->
		<s:a href="javascript:void(0);" action="syspm_add" cssClass="easyui-linkbutton" plain="true" onclick="artistApp_tool.add()">添加</s:a>
		<s:a href="javascript:void(0);" action="syspm_delete" cssClass="easyui-linkbutton" plain="true" onclick="artistApp_tool.remove()">删除</s:a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="artistApp_tool.reload()">刷新</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="artistApp_tool.redo()">取消选择</a>
	</div>
	<div style="padding: 0 0 0 7px;color:#333;">
	<!-- 查询帐号:<input type="text" class="textbox" name="user" style="width:110px"> -->
	</div>
</div><!-- anchorApp_tool结束 -->

<!-- 代言主播申请详细 -->
<form id="anchorApp_detail" style="margin:0;padding:5px;color:#333;">
	<input type="hidden" id="appAnchorId">
	<div id="anchorApp_detail_tabs">
		<div title="申请信息" style="display:block;overflow:hidden;">
			<!-- 申请信息 -->
			<div id="app_detail">
			</div><!-- app_detail结束 -->
		</div>
		<div title="用户信息" style="display:block;overflow:hidden;">
			<!-- 用户信息 -->
			<div id="user_detail">
			</div><!-- user_detail结束 -->
		</div>
		<!-- 安全信息 -->
		<!-- 实名信息 -->
	</div><!-- anchorApp_detail_tabs结束 -->
</form><!-- anchorApp_detail结束 -->

<!-- 其他代言主播申请详细 -->
<form id="artistApp_detail" style="margin:0;padding:5px;color:#333;">
<input type="hidden" id="appArtistId">
	<div id="artistApp_detail_tabs">
		<div title="申请信息" style="display:block;overflow:hidden;">
			<!-- 申请信息 -->
			<div id="artist_app_detail">
			</div><!-- artist_app_detail结束 -->
		</div>
		<div title="用户信息" style="display:block;overflow:hidden;">
			<!-- 用户信息 -->
			<div id="artist_user_detail">
			</div><!-- artist_user_detail结束 -->
		</div>
		<!-- 安全信息 -->
		<!-- 实名信息 -->
	</div><!-- artistApp_detail_tabs结束 -->
</form><!-- artistApp_detail结束 -->

<!-- 添加代言主播申请 -->
<form id="anchorApp_add"  style="margin:0;padding:25px;color:#333;">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>用户:</th>
			<td>
				<input type="text" id="spUser" name="spAtuid" class="textbox">
				
			</td>
		</tr>
		<tr>
			<th>真实姓名:</th>
			<td><input type="text" name="spAnchorApplicationDetail.spRealname" class="textbox"></td>
		</tr>
		<tr>
			<th>身份证:</th>
			<td><input type="text" name="spAnchorApplicationDetail.spIdNum" class="textbox"></td>
		</tr>
		<tr>
			<th>身份证复印件:</th>
			<td><input type="text"  name="spAnchorApplicationDetail.spIdNumSort" class="textbox"></td>
		</tr>
		<tr>
			<th>开户银行:</th>
			<td><input type="text" id="spBank" name="spAnchorApplicationDetail.spBanks.id" class="textbox"></td>
		</tr>
		<tr>
			<th>开户地:</th>
			<td>
				<input type="text" id="spPro" class="textbox">
				<input type="text" id="spCity"  name="spAnchorApplicationDetail.spBankRegion.id" class="textbox">
			</td>
		</tr>
		<tr>
			<th>支行名称:</th>
			<td><input type="text" id="spBankBranch" style="width:348px;"  name="spAnchorApplicationDetail.spBankBranch.id" class="textbox"></td>
		</tr>
		<tr>
			<th>银行卡号:</th>
			<td><input type="text"  name="spAnchorApplicationDetail.spBaId" class="textbox"></td>
		</tr>
		<tr>
			<th>开户人姓名:</th>
			<td><input type="text"  name="spAnchorApplicationDetail.spBankUserName" class="textbox"></td>
		</tr>
		<tr>
			<th>联系电话:</th>
			<td><input type="text"  name="spAnchorApplicationDetail.spMobie" class="textbox"></td>
		</tr>
		
	</table>
</form>



<!-- 选择用户 -->
<div id="spUser-dialog">
	<div id="spUser-datagrid"></div>
</div>

<script type="text/javascript" src="easyui/extension/validatebox/validatebox.js"></script>
<script type="text/javascript" src="js/aotuMCenter/AM/aotucms.aotuam.application.js"></script>

