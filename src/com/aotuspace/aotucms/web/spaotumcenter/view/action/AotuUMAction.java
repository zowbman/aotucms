package com.aotuspace.aotucms.web.spaotumcenter.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotucms.web.base.BaseAction;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAotuspacePriv;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersBinfo;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersBinfoKey;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersDinfo;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:AotuUMAction
 * Description:��͹�ռ��û�����
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:17:02
 *
 */

@Controller
@Scope("prototype")
public class AotuUMAction extends BaseAction<SpUsersBinfo> {
	
	//Ȩ��ids
	private Integer[] privIds;
	
	//�û�id
	private SpUsersBinfoKey spUsersBinfoKey;
	
	//�û�ids
	public List<SpUsersBinfoKey> userIds;
	
	//�û���Ϣ�б�ҳ��
	public String list() throws Exception {
		return "list";
	}
	
	//�û���Ϣdata
	public String listData() throws Exception {
		//��ѯ�û���ҳ��Ϣ
		PageBean<SpUsersBinfo> spUsersBinfoList = iAotuUMService.findSpUsersBinfoList(rows, page);
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpUsersBinfo spUsersBinfo : spUsersBinfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spUsersBinfo.getSpUsersBinfoKey().getSpId());
			rowMap.put("sp_AtuId", spUsersBinfo.getSpUsersBinfoKey().getSpAtuid());
			rowMap.put("sp_Account", spUsersBinfo.getSpAccount());//�˺�
			rowMap.put("sp_Identity", spUsersBinfo.getSpUsersIdentity().getSpIdentityn());//���
			rowMap.put("sp_ReDate", spUsersBinfo.getSpRedate());//ע��ʱ��
			rowMap.put("sp_Status", spUsersBinfo.getSpUsersStatus().getSpStatusn());//�˺�״̬
			rowMap.put("sp_RePlace", spUsersBinfo.getSpReplace());//ע���
			rowMap.put("sp_ReIp", spUsersBinfo.getSpReip());//ע��Ip
			listMaps.add(rowMap);
		}
		//easyui total ����  rows�б�
		pageListMap.put("total", spUsersBinfoList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	
	//Ա����Ϣ��ϸ
	public String detail() throws Exception {
		//��ѯԱ����Ϣ��ϸ
		SpUsersBinfo spUsersBinfoDetail = iAotuUMService.getByObject(model
				.getSpUsersBinfoKey());
		ActionContext.getContext().put("spUsersBinfoDetail", spUsersBinfoDetail);
		return "detail";
	}

	//����û����Ƿ��ظ�
	public String checkSpAccount() throws Exception{
		//�������˲�ѯ�û����Ƿ����
		SpUsersBinfo spUsersBinfo = iAotuUMService.findBySpAccount(model.getSpAccount());
		if(spUsersBinfo==null){
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//����û�
	public String add() throws Exception {
		//����û���û���ظ����򱣴浽���ݿ�
		SpUsersBinfo spUsersBinfo = iAotuUMService.findBySpAccount(model.getSpAccount());
		if (spUsersBinfo == null) {
			//1:������AutoId���
			Integer maxAutoId = iAotuUMService.findByMaxAutoid();
			SpUsersBinfoKey newKey=new SpUsersBinfoKey();
			if (maxAutoId == null) {
				newKey.setSpAtuid(100000);
			}else{
				newKey.setSpAtuid(maxAutoId + 1);
			}
			model.setSpUsersBinfoKey(newKey);
			//��ϸ��Ϣ
			SpUsersDinfo spUsersDinfo=model.getSpUsersDinfo();
			spUsersDinfo.setSpUsersBinfoKey(newKey);
			model.setSpUsersDinfo(spUsersDinfo);
			//2:���ӵ����ݿ�
			iAotuUMService.save(model);
			//�ɹ�
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");	
		} else {//����û����ظ�������ʾ��������
			//ʧ��
			jsonResult.setCode(100001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//ɾ���û�
	public String delete() throws Exception {
		iAotuUMService.deleteByList(userIds);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//�����û�ҳ��
	public String edit() throws Exception{
		//��ȡԭ�������
		SpUsersBinfo spUsersBinfo=iAotuUMService.getByObject(model.getSpUsersBinfoKey());
		if(spUsersBinfo!=null){
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
			jsonResult.setData("["+objectMapper.writeValueAsString(spUsersBinfo)+"]");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}

	//�����û�
	public String editSubmit() throws Exception {
		//�����ݿ��ȡԴ����
		SpUsersBinfo spUsersBinfo = iAotuUMService.getByObject(model.getSpUsersBinfoKey());
		if(spUsersBinfo!=null){
			//����Ҫ�޸ĵ�����
			spUsersBinfo.setSpPassword(model.getSpPassword());
			//��ϸ��Ϣ
			spUsersBinfo.getSpUsersDinfo().setSpRealname(model.getSpUsersDinfo().getSpRealname());;
			//���µ����ݿ�
			iAotuUMService.update(spUsersBinfo);
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//Ȩ��treegrid
	public String privTree() throws Exception {
		//��ѯȨ������
		List<SpAotuspacePriv> SpAotuspacePrivList = iAotuPMService.findSpAotuspacePrivTreeData();
		//����id��ѯȨ��
		/*SpEmployeePrivilege spEmployeePrivilege =iSysPMService.getById(id)*/
		//��װjson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuspacePriv spAotuspacePriv : SpAotuspacePrivList) {
			List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String, Object>>();
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spAotuspacePriv.getSpId());
			rowMap.put("text", spAotuspacePriv.getSpName());
			if (spAotuspacePriv.getPrivsChildren().size() == 0) {
				if (model.getSpUsersBinfoKey() != null) {
					for (SpUsersBinfo spUsersBinfo : spAotuspacePriv.getSpUsers()) {
						if (model.getSpUsersBinfoKey().equals(spUsersBinfo.getSpUsersBinfoKey())) {
							rowMap.put("checked", "true");
							break;
						}
					}
				}
			}
			for (SpAotuspacePriv childrenPriv : spAotuspacePriv.getPrivsChildren()) {
				Map<String, Object> rowChildrenMap = new HashMap<String, Object>();
				rowChildrenMap.put("id", childrenPriv.getSpId());
				rowChildrenMap.put("text", childrenPriv.getSpName());
				if (childrenPriv.getPrivsChildren().size() == 0) {
					if (model.getSpUsersBinfoKey() != null) {
						for (SpUsersBinfo spUsersBinfo : childrenPriv.getSpUsers()) {
							if (model.getSpUsersBinfoKey().equals(spUsersBinfo.getSpUsersBinfoKey())) {
								rowMap.put("checked", "true");
								break;
							}
						}
					}
				} else if (childrenPriv.getPrivsChildren().size() > 0) {
					List<Map<String, Object>> listChildren2Maps = new ArrayList<Map<String, Object>>();
					for (SpAotuspacePriv chilren2Priv : childrenPriv.getPrivsChildren()) {
						Map<String, Object> rowChilren2Map = new HashMap<String, Object>();
						rowChilren2Map.put("id", chilren2Priv.getSpId());
						rowChilren2Map.put("text", chilren2Priv.getSpName());
						if (model.getSpUsersBinfoKey() != null) {
							for (SpUsersBinfo spUsersBinfo : chilren2Priv.getSpUsers()) {
								if (model.getSpUsersBinfoKey().equals(spUsersBinfo.getSpUsersBinfoKey())) {
									rowChilren2Map.put("checked", "true");
									break;
								}
							}
						}
						listChildren2Maps.add(rowChilren2Map);
					}
					rowChildrenMap.put("children", listChildren2Maps);
				}
				listChildrenMaps.add(rowChildrenMap);

			}
			rowMap.put("children", listChildrenMaps);
			listMaps.add(rowMap);
		}
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}

	//�û���ɫ-����Ȩ�ޣ�Ȩ�����ã�
	public String userPrivSet() throws Exception {
		SpUsersBinfo spUsersBinfo = iAotuUMService.getByObject(spUsersBinfoKey);
		List<SpAotuspacePriv> spAotuspacePrivs = iAotuPMService.getByIds(privIds);
		spUsersBinfo.setSpUsersPrivileges(new HashSet<SpAotuspacePriv>(spAotuspacePrivs));
		iAotuUMService.update(spUsersBinfo);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//getter��setter
	public List<SpUsersBinfoKey> getUserIds() {
		return userIds;
	}
	
	@JSON(serialize = true, deserialize = true)
	public void setUserIds(List<SpUsersBinfoKey> userIds) {
		this.userIds = userIds;
	}

	public Integer[] getPrivIds() {
		return privIds;
	}

	public void setPrivIds(Integer[] privIds) {
		this.privIds = privIds;
	}

	public SpUsersBinfoKey getSpUsersBinfoKey() {
		return spUsersBinfoKey;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpUsersBinfoKey(SpUsersBinfoKey spUsersBinfoKey) {
		this.spUsersBinfoKey = spUsersBinfoKey;
	}
	
	
}
