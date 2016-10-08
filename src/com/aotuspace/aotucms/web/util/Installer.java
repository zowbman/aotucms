package com.aotuspace.aotucms.web.util;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeePrivilege;
import com.aotuspace.aotucms.web.spsysmcenter.service.ISysMMService;

@Component
public class Installer {

	@Resource
	SessionFactory sessionFactory;
	@Resource
	ISysMMService iSysMMService;
	
	/**
	 * ִ�а�װ
	 * @throws Exception 
	 */
	
	@Transactional
	public void install() throws Exception{
		System.out.println("ִ�а�װ����...");
		Session session  = sessionFactory.getCurrentSession();
		
		/*//���泬������Ա�û�
		SpEmployeeBinfo defaultEmpl = new SpEmployeeBinfo();
		defaultEmpl.setSpEpaccount("admin");//�˺�
		defaultEmpl.setSpEppassword("admin");//����
		//Ա��id
		Integer maxEpId = iSysMMService.findByMaxEpId();
		SpEmployeeBinfoKey newKey=new SpEmployeeBinfoKey();
		if (maxEpId == null) {
			newKey.setSpEpid(100000);
			defaultEmpl.setSpEmployeeBinfoKey(newKey);
		}
		newKey.setSpEpid(maxEpId + 1);
		defaultEmpl.setSpEmployeeBinfoKey(newKey);
		//����
		
		session.save(defaultEmpl); //����
*/
		//����Ȩ������
		SpEmployeePrivilege menu, menu1,menu2,menu3,menu4,menu5,menu6;
		
		//ϵͳ��������ģ��(Ȩ�����ƣ���ַ��ͼ�꣬չ��״̬�����ڵ�)
		menu =new SpEmployeePrivilege("ϵͳ��������",null,null,"open",null);
		menu1 =new SpEmployeePrivilege("ϵͳԱ������","sysmm_list",null,"open",menu);
		menu2 =new SpEmployeePrivilege("ϵͳ��֯��������","sysdm_list",null,"open",menu);
		menu3 =new SpEmployeePrivilege("ϵͳ��ɫ����","sysrm_list",null,"open",menu);
		menu4 =new SpEmployeePrivilege("ϵͳ����Ȩ�޹���","syspm_list",null,"open",menu);
		menu5 =new SpEmployeePrivilege("ϵͳ��ɫȨ�޹���","sysrpm_list",null,"open",menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		
		session.save(new SpEmployeePrivilege("��֯�����б�","sysmm_departTree",null,null,menu1));
		session.save(new SpEmployeePrivilege("ϵͳԱ���б�","sysmm_listData",null,null,menu1));
		session.save(new SpEmployeePrivilege("ϵͳԱ�����","sysmm_add",null,null,menu1));
		session.save(new SpEmployeePrivilege("ϵͳԱ��ɾ��","sysmm_delete",null,null,menu1));
		session.save(new SpEmployeePrivilege("ϵͳԱ���޸�","sysmm_edit",null,null,menu1));
		
		session.save(new SpEmployeePrivilege("ϵͳ��֯�����б�","sysmd_listData",null,null,menu2));
		session.save(new SpEmployeePrivilege("ϵͳ��֯�������","sysmd_add",null,null,menu2));
		session.save(new SpEmployeePrivilege("ϵͳ��֯����ɾ��","sysmd_delete",null,null,menu2));
		session.save(new SpEmployeePrivilege("ϵͳ��֯�����޸�","sysmd_edit",null,null,menu2));
	
		session.save(new SpEmployeePrivilege("ϵͳ��ɫ�б�","sysrm_listData",null,null,menu3));
		session.save(new SpEmployeePrivilege("ϵͳ��ɫ���","sysrm_add",null,null,menu3));
		session.save(new SpEmployeePrivilege("ϵͳ��ɫɾ��","sysrm_delete",null,null,menu3));
		session.save(new SpEmployeePrivilege("ϵͳ��ɫ�޸�","sysrm_edit",null,null,menu3));	
		
		session.save(new SpEmployeePrivilege("ϵͳ����Ȩ���б�","syspm_listData",null,null,menu4));
		session.save(new SpEmployeePrivilege("ϵͳ����Ȩ�����","syspm_add",null,null,menu4));
		session.save(new SpEmployeePrivilege("ϵͳ����Ȩ��ɾ��","syspm_delete",null,null,menu4));
		session.save(new SpEmployeePrivilege("ϵͳ����Ȩ���޸�","syspm_edit",null,null,menu4));	
		
		session.save(new SpEmployeePrivilege("ϵͳ��ɫ�б�","sysrm_listData",null,null,menu5));
		session.save(new SpEmployeePrivilege("ϵͳ������ɫȨ���б�","sysrpm_privTree",null,null,menu5));
		session.save(new SpEmployeePrivilege("ϵͳ��ɫȨ������","sysrpm_edit",null,null,menu5));	
		
		//��͹�ռ��������ģ��
		//��͹�ռ��������(Ȩ�����ƣ���ַ��ͼ�꣬չ��״̬�����ڵ�)
		menu =new SpEmployeePrivilege("��͹�ռ��������",null,null,"open",null);
		menu1 =new SpEmployeePrivilege("��͹�ռ��û�����","aotuum_list",null,null,menu);
		menu2 =new SpEmployeePrivilege("��͹�ռ��û���ݹ���","aotuuim_list",null,null,menu);
		menu3 =new SpEmployeePrivilege("��͹�ռ��ɫ����","aoturm_list",null,null,menu);
		menu4 =new SpEmployeePrivilege("��͹�ռ�Ȩ�޹���","aotupm_list",null,null,menu);
		menu5 =new SpEmployeePrivilege("��͹�ռ������������","aotuam_index",null,null,menu);
		
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		
		session.save(new SpEmployeePrivilege("������������","aotuam_application",null,null,menu5));
		session.save(new SpEmployeePrivilege("����������Լ����","",null,null,menu5));
		
		//��͹�ռ�������������ģ��
		menu =new SpEmployeePrivilege("��͹�ռ�������������",null,null,"open",null);
		menu1 =new SpEmployeePrivilege("��Ʒ����",null,null,null,menu);
		menu2 =new SpEmployeePrivilege("�ɹ�����",null,null,null,menu);
		menu3 =new SpEmployeePrivilege("�ֿ����",null,null,null,menu);
		menu4 =new SpEmployeePrivilege("���۹���",null,null,null,menu);
		menu5 =new SpEmployeePrivilege("��������",null,null,null,menu);
		
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		
		//��Ʒ����
		session.save(new SpEmployeePrivilege("��Ʒ��Ϣ","commodity_list",null,null,menu1));
		//�ɹ�����
		session.save(new SpEmployeePrivilege("��������","purchase_purchaseOrdersAdd",null,null,menu2));
		session.save(new SpEmployeePrivilege("�������","purchase_purchaseStorageOrdersAdd",null,null,menu2));
		session.save(new SpEmployeePrivilege("������ѯ","purchase_purchaseStorageInquire",null,null,menu2));
		//�ֿ����
		session.save(new SpEmployeePrivilege("�ֿ���Ϣ","warehouse_list",null,null,menu3));
		session.save(new SpEmployeePrivilege("�ֿ���Ʒ��Ϣ","warehouse_pdList",null,null,menu3));
		//���۹���
		//��������
		session.save(new SpEmployeePrivilege("�����ϼܶ���","treasure_treasureShelvesOrder",null,null,menu5));
		session.save(new SpEmployeePrivilege("�ϼܶ�����ѯ","treasure_treasureShelvesOrderList",null,null,menu5));
		menu6=new SpEmployeePrivilege("�������","treasuretask_index",null,null,menu5);
		session.save(menu6);
		
		session.save(new SpEmployeePrivilege("��������","treasuretask_release",null,null,menu6));
		
		
		System.out.println("��װ����...");
	}
	
	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();

	}
}
