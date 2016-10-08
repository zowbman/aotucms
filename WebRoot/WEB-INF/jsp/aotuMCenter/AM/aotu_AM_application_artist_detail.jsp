<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" type="text/css" href="css/spaotumcenter/aotucms.aotuanchor.css">
<table class="app_detail"  border="0" cellspacing="0" cellpadding="0" style="border-bottom: 1px solid #ccc;width:800px;">
	<tr>
		<th class="detail-label">凹凸ID:</th>
		<td class="detail-value"><s:property value="#spArtistApplication.spAtuid"/></td>
		<th class="detail-label">真实姓名:</th>
		<td class="detail-value"><s:property value="#spArtistApplication.spAnchorApplicationDetail.spRealname"/></td>
	</tr>
	<tr>
		<th class="detail-label">身份证:</th>
		<td colspan="3" class="detail-value"><s:property value="#spArtistApplication.spAnchorApplicationDetail.spIdNum"/></td>
	</tr>
	<tr>
		<th class="detail-label">身份证复印件:</th>
		<td colspan="3" class="detail-value">
			<div class="face">
				<img src="img/111.jpg" />
			</div>
		</td>
	</tr>
	<tr>
		<th class="detail-label">开户银行:</th>
		<td class="detail-value"><s:property value="#spArtistApplication.spAnchorApplicationDetail.spBanks.name"/></td>
		<th class="detail-label">开户地:</th>
		<td class="detail-value"><s:property value="#spArtistApplication.spAnchorApplicationDetail.spBankRegion.name"/></td>
	</tr>
	<tr>
		<th class="detail-label">支行:</th>
		<td class="detail-value"><s:property value="#spArtistApplication.spAnchorApplicationDetail.spBankBranch.name"/></td>
		<th class="detail-label">银行卡号:</th>
		<td class="detail-value"><s:property value="#spArtistApplication.spAnchorApplicationDetail.spBaId"/></td>
	</tr>
	<tr>
		<th class="detail-label">开户人:</th>
		<td colspan="3" class="detail-value"><s:property value="#spArtistApplication.spAnchorApplicationDetail.spBankUserName"/></td>
	</tr>
	
	<tr>
		<th class="detail-label">手机号码:</th>
		<td class="detail-value"><s:property value="#spArtistApplication.spAnchorApplicationDetail.spMobie"/></td>
		<th class="detail-label">艺名:</th>
		<td class="detail-value"><s:property value="#spArtistApplication.spArname"/></td>
	</tr>
	<tr>
		<th class="detail-label">内容概述:</th>
		<td colspan="3" class="detail-value"><s:property value="#spArtistApplication.spArcontent"/></td>
	</tr>
	<tr>
		<th class="detail-label">直播截图:</th>
		<td colspan="3" class="detail-value">
			<div class="face">
				<img src="img/111.jpg" />
			</div>
			<div class="face">
				<img src="img/111.jpg" />
			</div>
			<div class="face">
				<img src="img/111.jpg" />
			</div>
		</td>
	</tr>
</table>