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
		<td class="detail-value"><s:property value="#spAnchorApplication.spAtuid"/></td>
		<th class="detail-label">真实姓名:</th>
		<td class="detail-value"><s:property value="#spAnchorApplication.spAnchorApplicationDetail.spRealname"/></td>
	</tr>
	<tr>
		<th class="detail-label">身份证:</th>
		<td colspan="3" class="detail-value"><s:property value="#spAnchorApplication.spAnchorApplicationDetail.spIdNum"/></td>
	</tr>
	<tr>
		<th class="detail-label">身份证复印件:</th>
		<td colspan="3" class="detail-value">
			<div class="face">
				<s:if test="#spAnchorApplication.spAnchorApplicationDetail.spIdNumSort!=null">
					<img src="/pic/${spAnchorApplication.spAnchorApplicationDetail.spIdNumSort }" />
				</s:if>
			</div>
		</td>
	</tr>
	<tr>
		<th class="detail-label">开户银行:</th>
		<td class="detail-value"><s:property value="#spAnchorApplication.spAnchorApplicationDetail.spBanks.name"/></td>
		<th class="detail-label">开户地:</th>
		<td class="detail-value"><s:property value="#spAnchorApplication.spAnchorApplicationDetail.spBankRegion.name"/></td>
	</tr>
	<tr>
		<th class="detail-label">支行:</th>
		<td class="detail-value"><s:property value="#spAnchorApplication.spAnchorApplicationDetail.spBankBranch.name"/></td>
		<th class="detail-label">银行卡号:</th>
		<td class="detail-value"><s:property value="#spAnchorApplication.spAnchorApplicationDetail.spBaId"/></td>
	</tr>
	<tr>
		<th class="detail-label">开户人:</th>
		<td colspan="3" class="detail-value"><s:property value="#spAnchorApplication.spAnchorApplicationDetail.spBankUserName"/></td>
	</tr>
	
	<tr>
		<th class="detail-label">手机号码:</th>
		<td class="detail-value"><s:property value="#spAnchorApplication.spAnchorApplicationDetail.spMobie"/></td>
		<th class="detail-label">直播平台:</th>
		<td class="detail-value"><s:property value="#spAnchorApplication.spLivestationInfo.spLiStationN"/></td>
	</tr>
	<tr>
		<th class="detail-label">直播昵称:</th>
		<td class="detail-value"><s:property value="#spAnchorApplication.spLinickname"/></td>
		<th class="detail-label">直播链接:</th>
		<td class="detail-value"><s:property value="#spAnchorApplication.spLiSrc"/></td>
	</tr>
	<tr>
		<th class="detail-label">申请时间:</th>
		<td colspan="3" class="detail-value"><s:property value="#spAnchorApplication.spApplydate"/></td>
	</tr>
	<tr>
		<th class="detail-label">直播截图:</th>
		<td colspan="3" class="detail-value">
		<s:if test="#spAnchorApplication.spAnchorApplicationDetail.spIdNumSort!=null">
			<div class="face">
				<img src="/pic/${spAnchorApplication.spLiscreensort }" />
			</div>
		</s:if>
		</td>
	</tr>
</table>