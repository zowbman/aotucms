package com.aotuspace.aotucms.web.spaotumcenter.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotucms.web.base.BaseAction;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAnchorApplication;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAnchorBinfo;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAnchorDetail;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersBinfo;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersIdentity;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:AotuUMAction
 * Description:凹凸空间代言主播
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:17:02
 *
 */

@Controller
@Scope("prototype")
public class AotuAANMAction extends BaseAction<SpAnchorApplication> {

	private Integer[] appIds;

	//代言主播申请列表
	public String applicationList() throws Exception {
		//查询申请代言主播分页信息
		PageBean<SpAnchorApplication> spAnchorApplicationList = iAotuAANMService
				.findSpAnchorApplicationList(rows, page);
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAnchorApplication spAnchorApplication : spAnchorApplicationList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spAnchorApplication.getSpId());
			rowMap.put("sp_AtuId", spAnchorApplication.getSpAtuid());
			rowMap.put("sp_ApplyDate", spAnchorApplication.getSpApplydate());//申请时间
			rowMap.put("sp_AppResult", spAnchorApplication.getSpApplicationResult().getSpStatus());//申请结果
			listMaps.add(rowMap);
		}
		//easyui total 总数  rows列表
		pageListMap.put("total", spAnchorApplicationList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//代言主播申请详细
	public String applicationDetail() throws Exception {
		SpAnchorApplication spAnchorApplication = iAotuAANMService.getById(model.getSpId());
		ActionContext.getContext().put("spAnchorApplication", spAnchorApplication);
		return "applicationDetail";
	}

	/**
	 * 审核申请
	 * 通过：2
	 * 不通过：3
	 * @return
	 * @throws Exception
	 */
	public String appReview() throws Exception {
		if (model.getSpApplicationResult() != null && model.getSpApplicationResult().getSpId() == 2) {//如果通过
			//查询原对象
			//获取申请表原对象
			SpAnchorApplication spAnchorApplication = iAotuAANMService.getById(model.getSpId());
			//1。代言主播申请表通过
			spAnchorApplication.setSpApplicationResult(model.getSpApplicationResult());
			//2.插入主播信息
			iAotuAnchorMService.save(new SpAnchorBinfo(null, spAnchorApplication.getSpAtuid(), new SpAnchorDetail(
					spAnchorApplication.getSpAnchorApplicationDetail().getSpBankRegion(), spAnchorApplication
					.getSpAnchorApplicationDetail().getSpBankBranch(), spAnchorApplication
					.getSpAnchorApplicationDetail().getSpBanks(), spAnchorApplication
					.getSpAnchorApplicationDetail().getSpRealname(), spAnchorApplication
					.getSpAnchorApplicationDetail().getSpMobie(), spAnchorApplication
					.getSpAnchorApplicationDetail().getSpIdNum(), spAnchorApplication
					.getSpAnchorApplicationDetail().getSpIdNumSort(), spAnchorApplication
					.getSpAnchorApplicationDetail().getSpBankUserName(), spAnchorApplication
					.getSpAnchorApplicationDetail().getSpBaId())));
			//3.修改用户身份
			//查询用户
			SpUsersBinfo spUsersBinfo = iAotuUMService.findBySpAtuId(spAnchorApplication.getSpAtuid());
			SpUsersIdentity spUsersIdentity = new SpUsersIdentity();
			spUsersIdentity.setSpId(2);
			spUsersBinfo.setSpUsersIdentity(spUsersIdentity);
			iAotuUMService.update(spUsersBinfo);
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
		} else {
			jsonResult.setCode(100001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//代言主播申请表单
	public String addForm() throws Exception {
		return "addForm";
	}

	//添加
	public String add() throws Exception {
		iAotuAANMService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//修改

	//删除代言主播申请
	public String delete() throws Exception {
		iAotuAANMService.delete(appIds);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//getter、setter
	public Integer[] getAppIds() {
		return appIds;
	}

	@JSON(serialize = true, deserialize = true)
	public void setAppIds(Integer[] appIds) {
		this.appIds = appIds;
	}

}
