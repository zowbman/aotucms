<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/spsumcenter/aotucms.sum.save.css">

<input type="hidden" id="SupplierExpandRow"><!-- ExpandRow隐藏域 -->
<table id="aotusupplier">
</table><!-- aotusupplier结束 -->

<div id="aotusum_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<s:a href="javascript:void(0);" action="aotusum_add" cssClass="easyui-linkbutton" plain="true" onclick="aotusum_tool.add()">添加</s:a>
		<s:a href="javascript:void(0);" action="aotusum_edit" cssClass="easyui-linkbutton" plain="true" onclick="aotusum_tool.edit()">修改</s:a>
		<s:a href="javascript:void(0);" action="aotusum_delete" cssClass="easyui-linkbutton" plain="true" onclick="aotusum_tool.remove()">删除</s:a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotusum_tool.reload()">刷新</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotusum_tool.redo()">取消选择</a>
	</div>
<div style="padding: 0 0 0 7px;color:#333;">
		<!-- 查询帐号:<input type="text" class="textbox" name="user" style="width:110px"> -->
	</div>
</div><!-- aotusum_tool结束 -->

<!-- 添加供应商信息的弹出层 -->
<form id="aotusum_add" style="margin:0;padding:25px;color:#333;">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>供应商名称:</th>
			<td colspan="1"><input type="text" name="spSuaccount" class="textbox"></td>
			<td rowspan="3" colspan="2" >头像</td>
		</tr>
		<tr>
			<th>密码:</th>
			<td colspan="1"><input type="text" name="spSupassword" class="textbox"></td>
		</tr>
		<tr>
			<th>商家名称</th>
			<td colspan="1"><input type="text"  name="spSupplierDinfo.spSusup" class="textbox"></td>
		</tr>

		<tr>
			<th>商家联系人姓名:</th>
			<td><input type="text" name="spSupplierDinfo.spSucont" class="textbox"></td>
			<th>商家联系电话:</th>
			<td><input type="text" name="spSupplierDinfo.spSutel" class="textbox"></td>
		</tr>
		<tr>
			<th>商家手机号码:</th>
			<td><input type="text" name="spSupplierDinfo.spSumobie" class="textbox"></td>
			<th>商家所在地:</th>
			<td><input type="text" name="spSupplierDinfo.spSudistrict"  class="textbox"></td>
		</tr>
		<tr>
			<th>商家详细地址:</th>
			<td><input type="text"  name="spSupplierDinfo.spSuaddress" class="textbox"></td>
			<th>商家所属行业ID:</th>
			<td><input type="text"  name="spSupplierDinfo.spSutraid" class="textbox"></td>
		</tr>
		<tr >
			<th>商家简介:</th>
			<td colspan="3"><textarea name="spSupplierDinfo.spSuresume" class="textbox textarea"></textarea></td>
		</tr>
		<tr>
			<th>商家logo:</th>
			<td colspan="3"><textarea name="spSupplierDinfo.spSulogo" class="textbox textarea"></textarea></td>
		</tr>
	</table>
</form>
		
<!-- 修改供应商信息弹出层 -->
	<form id="aotusum_edit" style="margin:0;padding:25px;color:#333;">
	<input type="hidden" name="spSupplierBinfoKey.spId">
	<input type="hidden" name="spSupplierBinfoKey.spSuid">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>供应商名称:</th>
			<td colspan="1"><input type="text" disabled="true" name="spSuaccount" class="textbox"></td>
			<td rowspan="3" colspan="2" >头像</td>
		</tr>
		<tr>
			<th>密码:</th>
			<td colspan="1"><input type="text" name="spSupassword" class="textbox"></td>
		</tr>
		<tr>
			<th>商家名称</th>
			<td colspan="1"><input type="text" id="depart" name="spSupplierDinfo.spSusup" class="textbox"></td>
		</tr>

		<tr>
			<th>商家联系人姓名:</th>
			<td><input type="text" name="spSupplierDinfo.spSucont" class="textbox"></td>
			<th>商家联系电话:</th>
			<td><input type="text" name="spSupplierDinfo.spSutel" class="textbox"></td>
		</tr>
		<tr>
			<th>商家手机号码:</th>
			<td><input type="text" name="spSupplierDinfo.spSumobie" class="textbox"></td>
			<th>商家所在地:</th>
			<td><input type="text" name="spSupplierDinfo.spSudistrict"  class="textbox"></td>
		</tr>
		<tr>
			<th>商家详细地址:</th>
			<td><input type="text"  name="spSupplierDinfo.spSuaddress" class="textbox"></td>
			<th>商家所属行业ID:</th>
			<td><input type="text"  name="spSupplierDinfo.spSutraid" class="textbox"></td>
		</tr>
		<tr >
			<th>商家简介:</th>
			<td colspan="3"><textarea name="spSupplierDinfo.spSuresume" class="textbox textarea"></textarea></td>
		</tr>
		<tr>
			<th>商家logo:</th>
			<td colspan="3"><textarea name="spSupplierDinfo.spSulogo" class="textbox textarea"></textarea></td>
		</tr>
	</table>
	</form>

<script type="text/javascript" src="easyui/extension/jquery-easyui-datagridview/datagrid-detailview.js"></script>
<script type="text/javascript" src="easyui/extension/validatebox/validatebox.js"></script>
<script type="text/javascript" src="js/sysuMCenter/aotucms.sum.js"></script>

