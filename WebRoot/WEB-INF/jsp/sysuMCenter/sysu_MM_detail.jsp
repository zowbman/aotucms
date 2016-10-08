<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" type="text/css" href="css/spsumcenter/aotucms.aotuuser.css">
<table class="user_detail" border="0" cellspacing="0" cellpadding="0" style="border-bottom: 1px solid #ccc;">
	<tr>
		<td rowspan="10" style="height:200px;width:60px">
			<div class="face">
				<img src="img/111.jpg"/>
			</div>
		</td>
		<th class="detail-label">供应商ID:</th>
		<td class="detail-value"><s:property value="#spSupplierBinfoDetail.spSupplierBinfoKey.spSuid"/></td>
		<th class="detail-label">供应商帐号:</th>
		<td class="detail-value"><s:property value="#spSupplierBinfoDetail.spSuaccount"/></td>
	</tr>
	<tr>
		<th class="detail-label">商家名称:</th>
		<td class="detail-value"><s:property value="#spSupplierBinfoDetail.spSupplierDinfo.spSusup"/></td>
		<th class="detail-label">商家联系人姓名:</th>
		<td class="detail-value"><s:property value="#spSupplierBinfoDetail.spSupplierDinfo.spSucont"/></td>
	</tr>
	<tr>
		<th class="detail-label">商家联系电话:</th>
		<td class="detail-value"><s:property value="#spSupplierBinfoDetail.spSupplierDinfo.spSutel"/></td>
		<th class="detail-label">商家手机号码:</th>
		<td class="detail-value"><s:property value="#spSupplierBinfoDetail.spSupplierDinfo.spSumobie"/></td>
	</tr>
	<tr>
		<th class="detail-label">商家所在地:</th>
		<td  colspan="3" class="detail-value"><s:property value="#spSupplierBinfoDetail.spSupplierDinfo.spSudistrict"/></td>
	</tr>
	
	<tr style="width:100px;">
		<th class="detail-label">商家详细地址:</th>
		<td class="detail-value"><s:property value="#spSupplierBinfoDetail.spSupplierDinfo.spSuaddress"/></td>
		<th class="detail-label">商家所属行业ID:</th>
		<td class="detail-value"><s:property value="#spSupplierBinfoDetail.spSupplierDinfo.spSutraid"/></td>
	</tr>
	<tr>
		<th class="detail-label">商家简介:</th>
		<td class="detail-value"><s:property value="#spSupplierBinfoDetail.spSupplierDinfo.spSuresume"/></td>
		<th class="detail-label">商家logo:</th>
		<td class="detail-value"><s:property value="#spSupplierBinfoDetail.spSupplierDinfo.spSulogo"/></td>
	</tr>
</table>
