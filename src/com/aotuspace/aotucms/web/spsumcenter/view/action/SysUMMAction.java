package com.aotuspace.aotucms.web.spsumcenter.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotucms.web.base.BaseAction;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsumcenter.hbm.SpSupplierBinfo;
import com.aotuspace.aotucms.web.spsumcenter.hbm.SpSupplierBinfoKey;
import com.aotuspace.aotucms.web.spsumcenter.hbm.SpSupplierDinfo;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:SysSMMAction
 * Description:ϵͳ��Ӧ�̹���
 * Company:aotuspace
 * @author    sida
 * @date      2015-10-12 ����5:29:45
 *
 */
@Controller
@Scope("prototype")
public class SysUMMAction extends BaseAction<SpSupplierBinfo> {

	//��Ӧ��ids
	public List<SpSupplierBinfoKey> supIds;
	
	//��Ӧ��id
	private SpSupplierBinfoKey spSupplierBinfoKey;
	
	//��Ӧ����Ϣ�б�ҳ��
	public String list() throws Exception{
		return "list";
	}
	
	//��Ӧ����Ϣdata
	public String listData() throws Exception{
		PageBean<SpSupplierBinfo> spSupplierBinfoList =iSysUMMService.findSpSupplierBinfoList(rows,page);
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
		for (SpSupplierBinfo spSupplierBinfo : spSupplierBinfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spSupplierBinfo.getSpSupplierBinfoKey().getSpId());//���id
			rowMap.put("sp_SuId", spSupplierBinfo.getSpSupplierBinfoKey().getSpSuid());//��Ӧ��id
			rowMap.put("sp_SuAccount", spSupplierBinfo.getSpSuaccount());//��Ӧ���ʺ�
			rowMap.put("sp_SuPassword", spSupplierBinfo.getSpSupassword());//��Ӧ������
			rowMap.put("sp_SuSup", spSupplierBinfo.getSpSupplierDinfo().getSpSusup());//�̼�����
			rowMap.put("sp_SuCont", spSupplierBinfo.getSpSupplierDinfo().getSpSucont());//�̼���ϵ������
			rowMap.put("sp_SuMobie", spSupplierBinfo.getSpSupplierDinfo().getSpSumobie());//�̼��ֻ�����
			rowMap.put("sp_SuTel", spSupplierBinfo.getSpSupplierDinfo().getSpSutel());//�̼���ϵ�绰
			rowMap.put("sp_SuDistrict", spSupplierBinfo.getSpSupplierDinfo().getSpSudistrict());//�̼����ڵ�
			listMaps.add(rowMap);
		}
		//easyui total ����rows�б�
		pageListMap.put("total", spSupplierBinfoList.getRecordList());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	
	//��Ӧ����Ϣ��ϸ
	public String detail() throws Exception{
		//��ѯ��Ӧ����Ϣ��ϸ
		SpSupplierBinfo spSupplierBinfoDetail = iSysUMMService.getByObject(model.getSpSupplierBinfoKey());
		ActionContext.getContext().put("spSupplierBinfoDetail", spSupplierBinfoDetail);
		return "detail";
	}
	
	//��鹩Ӧ���Ƿ��ظ�
	public String checkSuAccount() throws Exception{
		//�������˲�ѯ��Ӧ���Ƿ����
		SpSupplierBinfo spSupplierBinfo = iSysUMMService.findBySpSuAccount(model.getSpSuaccount());
		if(spSupplierBinfo==null){
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
		}else{
			jsonResult.setCode(100001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//��ӹ�Ӧ��
	public String add() throws Exception{
		//����û���û���ظ����򱣴浽���ݿ�
		SpSupplierBinfo spSupplierBinfo = iSysUMMService.findBySpSuAccount(model.getSpSuaccount());
		if(spSupplierBinfo == null){
			//1:������Suid���(service�ӿ�ʵ���࣬���ݹ�Ӧ����Ϣ��spid���н��򣬲��һ�ȡһ��select top 1 from �� order by desc <-- ��һ�����󣬶�����һ��list)
			Integer maxSuId = iSysUMMService.findByMaxSuId();
			SpSupplierBinfoKey newKey = new SpSupplierBinfoKey();
			if(maxSuId == null){
				newKey.setSpSuid(100000);
			}else{
				newKey.setSpSuid(maxSuId+1);
			}
			model.setSpSupplierBinfoKey(newKey);
		//��ϸ��Ϣ---------
			SpSupplierDinfo spSupplierDinfo = model.getSpSupplierDinfo();
			spSupplierDinfo.setSpSupplierBinfoKey(newKey);
			spSupplierDinfo.setSpSutraid(0);
			model.setSpSupplierDinfo(spSupplierDinfo);
		//2:���ӵ����ݿ�
		iSysUMMService.save(model);
		//�ɹ�
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		}else{//�����Ӧ���ظ�������ʾ��������
			//ʧ��
			jsonResult.setCode(100001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//ɾ����Ӧ��
	public String delete() throws Exception{
		//��ȡ��Ӧ�̵�Դ����
		iSysUMMService.deleteByList(supIds);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//���Ĺ�Ӧ��ҳ��
	public String edit() throws Exception{
		//��ȡԴ�������
		SpSupplierBinfo spSupplierBinfo = iSysUMMService.getByObject(model.getSpSupplierBinfoKey());
		if(spSupplierBinfo!=null){
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
			jsonResult.setData("["+objectMapper.writeValueAsString(spSupplierBinfo)+"]");
		}else{
			jsonResult.setCode(100001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}
	
	//���Ĺ�Ӧ��
	public String editSubmit() throws Exception{
		//�����ݿ��ȡԴ����
		SpSupplierBinfo spSupplierBinfo = iSysUMMService.getByObject(model.getSpSupplierBinfoKey());
		if(spSupplierBinfo!=null){
			//����Ҫ�޸ĵ�����
			spSupplierBinfo.setSpSupassword(model.getSpSupassword());
			//��ϸ��Ϣ
			spSupplierBinfo.getSpSupplierDinfo().setSpSusup(model.getSpSupplierDinfo().getSpSusup());//�̼�����
			spSupplierBinfo.getSpSupplierDinfo().setSpSucont(model.getSpSupplierDinfo().getSpSucont());//�̼���ϵ������
			spSupplierBinfo.getSpSupplierDinfo().setSpSutel(model.getSpSupplierDinfo().getSpSutel());//�̼���ϵ�绰
			spSupplierBinfo.getSpSupplierDinfo().setSpSumobie(model.getSpSupplierDinfo().getSpSumobie());//�̼��ֻ�����
			spSupplierBinfo.getSpSupplierDinfo().setSpSudistrict(model.getSpSupplierDinfo().getSpSudistrict());//�̼����ڵ�
			spSupplierBinfo.getSpSupplierDinfo().setSpSuaddress(model.getSpSupplierDinfo().getSpSuaddress());//�̼���ϸ��ַ
			spSupplierBinfo.getSpSupplierDinfo().setSpSutraid(model.getSpSupplierDinfo().getSpSutraid());//�̼�������ҵID
			spSupplierBinfo.getSpSupplierDinfo().setSpSuresume(model.getSpSupplierDinfo().getSpSuresume());//�̼Ҽ��
			spSupplierBinfo.getSpSupplierDinfo().setSpSulogo(model.getSpSupplierDinfo().getSpSulogo());//�̼�logo
			//���µ����ݿ�
			iSysUMMService.update(spSupplierBinfo);
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
		}else{
			jsonResult.setCode(0);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//-------------------------------------------
	public List<SpSupplierBinfoKey> getSupIds() {
		return supIds;
	}

	@JSON(serialize = true,deserialize = true)
	public void setSupIds(List<SpSupplierBinfoKey> supIds) {
		this.supIds = supIds;
	}

	public SpSupplierBinfoKey getSpSupplierBinfoKey() {
		return spSupplierBinfoKey;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpSupplierBinfoKey(SpSupplierBinfoKey spSupplierBinfoKey) {
		this.spSupplierBinfoKey = spSupplierBinfoKey;
	}
	
	
}
