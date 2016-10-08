<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" type="text/css" href="css/spaotumcenter/aotucms.aotuuser.css">
<table class="user_detail" border="0" cellspacing="0" cellpadding="0" style="border-bottom: 1px solid #ccc;">
	<tr>
		<td rowspan="10" style="height:200px;width:60px">
			<div class="face">
				<img src="img/111.jpg"/>
			</div>
		</td>
		<th class="detail-label">凹凸ID:</th>
		<td class="detail-value"><s:property value="#spUsersBinfoDetail.spUsersBinfoKey.spAtuid"/></td>
		<th class="detail-label">账号状态:</th>
		<td class="detail-value"><s:property value="#spUsersBinfoDetail.spAccount"/></td>
	</tr>
	<tr>
		<th class="detail-label">用户名:</th>
		<td class="detail-value"><s:property value="#spUsersBinfoDetail.spAccount"/></td>
		<th class="detail-label">用户身份:</th>
		<td class="detail-value"><s:property value="#spUsersBinfoDetail.spUsersIdentity.spIdentityn"/></td>
	</tr>
	<tr>
		<th class="detail-label">注册时间:</th>
		<td class="detail-value"><s:property value="#spUsersBinfoDetail.spRedate"/></td>
		<th class="detail-label">注册地点:</th>
		<td class="detail-value"><s:property value="#spUsersBinfoDetail.spReplace"/></td>
	</tr>
	<tr>
		<th class="detail-label">注册IP:</th>
		<td  colspan="3" class="detail-value"><s:property value="#spUsersBinfoDetail.spReip"/></td>
	</tr>
	
	<tr style="width:100px;">
		<th class="detail-label">昵称:</th>
		<td class="detail-value"><s:property value="#spUsersBinfoDetail.spUsersDinfo.spNickname"/></td>
		<th class="detail-label">姓名:</th>
		<td class="detail-value"><s:property value="#spUsersBinfoDetail.spUsersDinfo.spRealname"/></td>
	</tr>
	<tr>
		<th class="detail-label">性别:</th>
		<td class="detail-value"><s:property value="#spUsersBinfoDetail.spUsersDinfo.spSex"/></td>
		<th class="detail-label">年龄:</th>
		<td class="detail-value"><s:property value="#spUsersBinfoDetail.spUsersDinfo.spAge"/></td>
	</tr>
	<tr>
		<th class="detail-label">出生日期:</th>
		<td colspan="3" class="detail-value"><s:property value="#spUsersBinfoDetail.spUsersDinfo.spBirth"/></td>
	</tr>
	<tr>
		<th class="detail-label">星座:</th>
		<td class="detail-value"><s:property value="#spUsersBinfoDetail.spUsersDinfo.spCon"/></td>
		<th class="detail-label">生肖:</th>
		<td class="detail-value"><s:property value="#spUsersBinfoDetail.spUsersDinfo.spAnimal"/></td>
	</tr>
	<tr>
		<th class="detail-label">所在地:</th>
		<td colspan="3" class="detail-value"><s:property value="#spUsersBinfoDetail.spUsersDinfo.spUserdistrict"/></td>
	</tr>
	<tr>
		<th class="detail-label">详细:</th>
		<td colspan="3" class="detail-value"><s:property value="#spUsersBinfoDetail.spUsersDinfo.spAddress"/></td>
	</tr>
</table>
