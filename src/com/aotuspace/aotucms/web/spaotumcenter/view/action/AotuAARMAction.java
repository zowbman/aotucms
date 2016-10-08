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
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAnchorBinfo;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAnchorDetail;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpArtistApplication;
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
public class AotuAARMAction extends BaseAction<SpArtistApplication> {

	private Integer[] appIds;

	private int appId;

	//其他代言主播
	public String appliactionList() throws Exception {
		//查询申请代言主播分页信息
		PageBean<SpArtistApplication> spArtistApplicationList = iAotuAARMService
				.findSpArtistApplicationList(rows, page);
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpArtistApplication spArtistApplication : spArtistApplicationList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spArtistApplication.getSpId());
			rowMap.put("sp_AtuId", spArtistApplication.getSpAtuid());
			rowMap.put("sp_ApplyDate", spArtistApplication.getSpApplydate());//申请时间
			rowMap.put("sp_AppResult", spArtistApplication.getSpApplicationResult().getSpStatus());//申请结果
			listMaps.add(rowMap);
		}
		//easyui total 总数  rows列表
		pageListMap.put("total", spArtistApplicationList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//其他代言主播申请详细
	public String applicationArtistDetail() throws Exception {
		SpArtistApplication spArtistApplication = iAotuAARMService.getById(model.getSpId());
		ActionContext.getContext().put("spArtistApplication", spArtistApplication);
		return "applicationArtistDetail";
	}

	//其他代言主播申请审核
	public String appReview() throws Exception {
		if (model.getSpApplicationResult() != null && model.getSpAnchorApplicationDetail().getSpId() == 1) {//如果通过
			//查询原对象
			SpArtistApplication spArtistApplication = iAotuAARMService.getById(appId);
			//1。代言主播申请表通过
			spArtistApplication.setSpApplicationResult(model.getSpApplicationResult());
			//2.插入主播信息
			iAotuAnchorMService.save(new SpAnchorBinfo(null, spArtistApplication.getSpAtuid(), new SpAnchorDetail(
					spArtistApplication.getSpAnchorApplicationDetail().getSpBankRegion(), spArtistApplication
							.getSpAnchorApplicationDetail().getSpBankBranch(), spArtistApplication
							.getSpAnchorApplicationDetail().getSpBanks(), spArtistApplication
							.getSpAnchorApplicationDetail().getSpRealname(), spArtistApplication
							.getSpAnchorApplicationDetail().getSpMobie(), spArtistApplication
							.getSpAnchorApplicationDetail().getSpIdNum(), spArtistApplication
							.getSpAnchorApplicationDetail().getSpIdNumSort(), spArtistApplication
							.getSpAnchorApplicationDetail().getSpBankUserName(), spArtistApplication
							.getSpAnchorApplicationDetail().getSpBaId())));
			//3.修改用户身份
			SpUsersBinfo spUsersBinfo = iAotuUMService.findBySpAtuId(spArtistApplication.getSpAtuid());
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

	//其他代言主播申请表单
	public String addForm() throws Exception {
		return "addForm";
	}

	//添加其他
	public String add() throws Exception {
		iAotuAARMService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//修改

	//删除其他代言主播申请
	public String delete() throws Exception {
		iAotuAARMService.delete(appIds);
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

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}
}
