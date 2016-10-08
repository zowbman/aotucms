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
 * Description:��͹�ռ��������
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:17:02
 *
 */

@Controller
@Scope("prototype")
public class AotuAANMAction extends BaseAction<SpAnchorApplication> {

	private Integer[] appIds;

	//�������������б�
	public String applicationList() throws Exception {
		//��ѯ�������������ҳ��Ϣ
		PageBean<SpAnchorApplication> spAnchorApplicationList = iAotuAANMService
				.findSpAnchorApplicationList(rows, page);
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAnchorApplication spAnchorApplication : spAnchorApplicationList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spAnchorApplication.getSpId());
			rowMap.put("sp_AtuId", spAnchorApplication.getSpAtuid());
			rowMap.put("sp_ApplyDate", spAnchorApplication.getSpApplydate());//����ʱ��
			rowMap.put("sp_AppResult", spAnchorApplication.getSpApplicationResult().getSpStatus());//������
			listMaps.add(rowMap);
		}
		//easyui total ����  rows�б�
		pageListMap.put("total", spAnchorApplicationList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//��������������ϸ
	public String applicationDetail() throws Exception {
		SpAnchorApplication spAnchorApplication = iAotuAANMService.getById(model.getSpId());
		ActionContext.getContext().put("spAnchorApplication", spAnchorApplication);
		return "applicationDetail";
	}

	/**
	 * �������
	 * ͨ����2
	 * ��ͨ����3
	 * @return
	 * @throws Exception
	 */
	public String appReview() throws Exception {
		if (model.getSpApplicationResult() != null && model.getSpApplicationResult().getSpId() == 2) {//���ͨ��
			//��ѯԭ����
			//��ȡ�����ԭ����
			SpAnchorApplication spAnchorApplication = iAotuAANMService.getById(model.getSpId());
			//1���������������ͨ��
			spAnchorApplication.setSpApplicationResult(model.getSpApplicationResult());
			//2.����������Ϣ
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
			//3.�޸��û����
			//��ѯ�û�
			SpUsersBinfo spUsersBinfo = iAotuUMService.findBySpAtuId(spAnchorApplication.getSpAtuid());
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

	//�������������
	public String addForm() throws Exception {
		return "addForm";
	}

	//���
	public String add() throws Exception {
		iAotuAANMService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//�޸�

	//ɾ��������������
	public String delete() throws Exception {
		iAotuAANMService.delete(appIds);
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

}
