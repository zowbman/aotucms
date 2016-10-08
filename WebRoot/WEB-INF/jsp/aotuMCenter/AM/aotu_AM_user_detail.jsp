<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" type="text/css"
	href="css/spaotumcenter/aotucms.aotuanchor.css">
<table class="user_bdetail" border="0" cellspacing="0" cellpadding="0"
	style="border-bottom: 1px solid #ccc;width:800">
	<tr>
		<th class="detail-label">头像:</th>
		<td colspan="3" class="detail-value">
			<div class="face">
				<img src="img/111.jpg" />
			</div></td>
	</tr>
	<tr>
		<th class="detail-label">账号:</th>
		<td class="detail-value"><s:property value="#spUser.spAccount"/></td>
		<th class="detail-label">凹凸id:</th>
		<td class="detail-value"><s:property value="#spUser.spAtuid"/></td>
	</tr>
	<tr>
		<th class="detail-label">用户昵称:</th>
		<td class="detail-value"><s:property value="#spUser.spUsersDinfo.spNickname"/></td>
		<th class="detail-label">用户身份:</th>
		<td class="detail-value"><s:property value="#spUser.spUsersIdentity.spIdentityn"/></td>
	</tr>
	<tr>
		<th class="detail-label">真实姓名:</th>
		<td class="detail-value"><s:property value="#spUser.spUsersDinfo.spRealname"/></td>
		<th class="detail-label">性别:</th>
		<td class="detail-value"><s:property value="#spUser.spUsersDinfo.spSex"/></td>
	</tr>
	<tr>
		<th class="detail-label">年龄:</th>
		<td class="detail-value"><s:property value="#spUser.spUsersDinfo.spAge"/></td>
		<th class="detail-label">出生日期:</th>
		<td class="detail-value"><s:property value="#spUser.spUsersDinfo.spBirth"/></td>
	</tr>
	<tr>
		<th class="detail-label">星座:</th>
		<td class="detail-value"><s:property value="#spUser.spUsersDinfo.spCon"/></td>
		<th class="detail-label">生肖:</th>
		<td class="detail-value"><s:property value="#spUser.spUsersDinfo.spAnimal"/></td>
	</tr>
	<tr>
		<th class="detail-label">所在地:</th>
		<td colspan="3" class="detail-value"><s:property value="#spUser.spUsersDinfo.spAddress"/></td>
	</tr>
	<tr>
		<th class="detail-label">详细:</th>
		<td colspan="3" class="detail-value"><s:property value="#spUser.spUsersDinfo.spUserdistrict"/></td>
	</tr>
</table>