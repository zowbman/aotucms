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
 * Description:��͹�ռ��������
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:17:02
 *
 */

@Controller
@Scope("prototype")
public class AotuAARMAction extends BaseAction<SpArtistApplication> {

	private Integer[] appIds;

	private int appId;

	//������������
	public String appliactionList() throws Exception {
		//��ѯ�������������ҳ��Ϣ
		PageBean<SpArtistApplication> spArtistApplicationList = iAotuAARMService
				.findSpArtistApplicationList(rows, page);
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpArtistApplication spArtistApplication : spArtistApplicationList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spArtistApplication.getSpId());
			rowMap.put("sp_AtuId", spArtistApplication.getSpAtuid());
			rowMap.put("sp_ApplyDate", spArtistApplication.getSpApplydate());//����ʱ��
			rowMap.put("sp_AppResult", spArtistApplication.getSpApplicationResult().getSpStatus());//������
			listMaps.add(rowMap);
		}
		//easyui total ����  rows�б�
		pageListMap.put("total", spArtistApplicationList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//������������������ϸ
	public String applicationArtistDetail() throws Exception {
		SpArtistApplication spArtistApplication = iAotuAARMService.getById(model.getSpId());
		ActionContext.getContext().put("spArtistApplication", spArtistApplication);
		return "applicationArtistDetail";
	}

	//�������������������
	public String appReview() throws Exception {
		if (model.getSpApplicationResult() != null && model.getSpAnchorApplicationDetail().getSpId() == 1) {//���ͨ��
			//��ѯԭ����
			SpArtistApplication spArtistApplication = iAotuAARMService.getById(appId);
			//1���������������ͨ��
			spArtistApplication.setSpApplicationResult(model.getSpApplicationResult());
			//2.����������Ϣ
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
			//3.�޸��û����
			SpUsersBinfo spUsersBinfo = iAotuUMService.findBySpAtuId(spArtistApplication.getSpAtuid());
			SpUsersIdentity spUsersIdentity = new SpUsersIdentity();
			spUsersIdentity.setSpId(2);
			spUsersBinfo.setSpUsersIdentity(spUsersIdentity);
			iAotuUMService.update(spUsersBinfo);
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
		} else {
			jsonResult.setCode(100001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//�����������������
	public String addForm() throws Exception {
		return "addForm";
	}

	//�������
	public String add() throws Exception {
		iAotuAARMService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//�޸�

	//ɾ������������������
	public String delete() throws Exception {
		iAotuAARMService.delete(appIds);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//getter��setter
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
