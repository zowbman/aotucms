<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" type="text/css" href="css/aotucms.manager.css">

<table class="manager_detail" border="0" cellspacing="0" cellpadding="0" style="border-bottom: 1px solid #ccc;">
	<tr>
		<td rowspan="8" style="height:200px;width:60px">
			<div class="face">
				<img src="img/111.jpg"/>
			</div>
		</td>
		<th class="detail-label">员工编号:</th>
		<td class="detail-value"><s:property value="#spEmployeeBinfoDetail.spEmployeeBinfoKey.spEpid"/></td>
		<th class="detail-label">用户名:</th>
		<td class="detail-value"><s:property value="#spEmployeeBinfoDetail.spEpaccount"/></td>
	</tr>
	<tr>
		<th class="detail-label">所属部门:</th>
		<td class="detail-value"><s:property value="#spEmployeeBinfoDetail.spEmployeeDepart.spEpdepartn"/></td>
		<th class="detail-label">拥有权限:</th>
		<td class="detail-value"></td>
	</tr>
	<tr>
		<th class="detail-label">姓名:</th>
		<td class="detail-value"><s:property value="#spEmployeeBinfoDetail.spEmployeeDinfo.spEprealname"/></td>
		<th class="detail-label">年龄:</th>
		<td class="detail-value"><s:property value="#spEmployeeBinfoDetail.spEmployeeDinfo.spEpage"/></td>
	</tr>
	
	<tr style="width:100px;">
		<th class="detail-label">性别:</th>
		<td class="detail-value"><s:property value="#spEmployeeBinfoDetail.spEmployeeDinfo.spEpsex"/></td>
		<th class="detail-label">出生日期:</th>
		<td class="detail-value"><s:property value="#spEmployeeBinfoDetail.spEmployeeDinfo.spEpbirth"/></td>
	</tr>
	<tr>
		<th class="detail-label">身份证:</th>
		<td class="detail-value"><s:property value="#spEmployeeBinfoDetail.spEmployeeDinfo.spEpidnum"/></td>
		<th class="detail-label">手机号码:</th>
		<td class="detail-value"><s:property value="#spEmployeeBinfoDetail.spEmployeeDinfo.spEpmobie"/></td>
	</tr>
	<tr>
		<th class="detail-label">星座:</th>
		<td class="detail-value"><s:property value="#spEmployeeBinfoDetail.spEmployeeDinfo.spEpcon"/></td>
		<th class="detail-label">生肖:</th>
		<td class="detail-value"><s:property value="#spEmployeeBinfoDetail.spEmployeeDinfo.spEpanimal"/></td>
	</tr>
	<tr>
		<th class="detail-label">所在地:</th>
		<td colspan="3" class="detail-value"><s:property value="#spEmployeeBinfoDetail.spEmployeeDinfo.spEmpdistrict"/></td>
	</tr>
	<tr>
		<th class="detail-label">详细:</th>
		<td colspan="3" class="detail-value"><s:property value="#spEmployeeBinfoDetail.spEmployeeDinfo.spEpaddress"/></td>
	</tr>
</table>
