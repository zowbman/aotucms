$(function(){
	/** 
     * ��չ�������ѡ�񣨵��checkbox����Ч���� 
     *      �Զ����������ԣ� 
     *      cascadeCheck ����ͨ������������δ���ص��ӽڵ㣩 
     *      deepCascadeCheck ����ȼ���������δ���ص��ӽڵ㣩 
     */  
    $.extend($.fn.treegrid.defaults,{  
        onLoadSuccess : function() {
            var target = $(this);  
            var opts = $.data(this, "treegrid").options;  
            var panel = $(this).datagrid("getPanel");  
            var gridBody = panel.find("div.datagrid-body");  
            var idField = opts.idField;//�����idField��ʵ����API�﷽����id����  
            gridBody.find("div.datagrid-cell-check input[type=checkbox]").unbind(".treegrid").click(function(e){
                if(opts.singleSelect) return;//��ѡ����
                if(opts.cascadeCheck ||opts.deepCascadeCheck){

                    var id=$(this).parent().parent().parent().attr("node-id");  
                    var status = false;  
                    if($(this).is(':checked')) status = true;  
                    //����ѡ�񸸽ڵ�  
                    selectParent(target,id,idField,status);
                    
                    selectChildren(target,id,idField,opts.deepCascadeCheck,status);  
                    
                    /** 
                     * ����ѡ�񸸽ڵ� 
                     * @param {Object} target 
                     * @param {Object} id �ڵ�ID 
                     * @param {Object} status �ڵ�״̬��true:��ѡ��false:δ��ѡ 
                     * @return {TypeName}  
                     */  
                    function selectParent(target,id,idField,status){
                        var parent = target.treegrid('getParent',id);  
                        if(parent){  
                            var parentId = parent[idField];  
                            console.log();
                            if(status){
                                target.treegrid('select',parentId);  
                            }else{
                            	if(target.treegrid('getChecked').length==2){
                            		target.treegrid('unselect',parentId); 
                            	}
                            }
                            selectParent(target,parentId,idField,status);
                        }  
                    }  
                    /** 
                     * ����ѡ���ӽڵ� 
                     * @param {Object} target 
                     * @param {Object} id �ڵ�ID 
                     * @param {Object} deepCascade �Ƿ���ȼ��� 
                     * @param {Object} status �ڵ�״̬��true:��ѡ��false:δ��ѡ 
                     * @return {TypeName}  
                     */  
                    function selectChildren(target,id,idField,deepCascade,status){  
                        //��ȼ���ʱ��չ���ڵ�  
                        if(status&&deepCascade)  
                            target.treegrid('expand',id);  
                        if(status){
                        	 target.treegrid('select',id); 
                        }
                        else{
                        	target.treegrid('unselect',id); 
                        }
                        //����ID��ȡ�²㺢�ӽڵ�  
                        var children = target.treegrid('getChildren',id); 
                        for(var i=0;i<children.length;i++){ 
                            var childId = children[i][idField];
                            if(status){
                            	target.treegrid('select',childId); 
                            }
                            else{
                            	target.treegrid('unselect',childId); 
                            }   
                            selectChildren(target,childId,idField,deepCascade,status);//�ݹ�ѡ���ӽڵ�  
                        }  
                    }  
                }  
                e.stopPropagation();//ֹͣ�¼�����  
            });  
        }  
    });  
    /** 
     * ��չ���������ѡ������ 
     * @param {Object} container 
     * @param {Object} options 
     * @return {TypeName}  
     */  
    $.extend($.fn.treegrid.methods,{  
        /** 
         * ����ѡ�� 
         * @param {Object} target 
         * @param {Object} param  
         *      param������������: 
         *          id:��ѡ�Ľڵ�ID 
         *          deepCascade:�Ƿ���ȼ��� 
         * @return {TypeName}  
         */  
        cascadeCheck : function(target,param){  
            var opts = $.data(target[0], "treegrid").options;  
            if(opts.singleSelect)  
                return;  
            var idField = opts.idField;//�����idField��ʵ����API�﷽����id����  
            var status = false;//������ǵ�ǰ�ڵ��״̬��true:��ѡ��false:δ��ѡ  
            var selectNodes = $(target).treegrid('getSelections');//��ȡ��ǰѡ����  
            for(var i=0;i<selectNodes.length;i++){  
                if(selectNodes[i][idField]==param.id)  
                    status = true;  
            }  
            //����ѡ�񸸽ڵ�  
            selectParent(target[0],param.id,idField,status);  
            selectChildren(target[0],param.id,idField,param.deepCascade,status);  
            /** 
             * ����ѡ�񸸽ڵ� 
             * @param {Object} target 
             * @param {Object} id �ڵ�ID 
             * @param {Object} status �ڵ�״̬��true:��ѡ��false:δ��ѡ 
             * @return {TypeName}  
             */  
            function selectParent(target,id,idField,status){  
                var parent = $(target).treegrid('getParent',id);  
                if(parent){  
                    var parentId = parent[idField];  
                    if(status)  
                        $(target).treegrid('select',parentId);  
                    else  
                        $(target).treegrid('unselect',parentId);  
                    selectParent(target,parentId,idField,status);  
                }  
            }  
            /** 
             * ����ѡ���ӽڵ� 
             * @param {Object} target 
             * @param {Object} id �ڵ�ID 
             * @param {Object} deepCascade �Ƿ���ȼ��� 
             * @param {Object} status �ڵ�״̬��true:��ѡ��false:δ��ѡ 
             * @return {TypeName}  
             */  
            function selectChildren(target,id,idField,deepCascade,status){  
                //��ȼ���ʱ��չ���ڵ�  
                if(!status&&deepCascade)  
                    $(target).treegrid('expand',id);  
                //����ID��ȡ�²㺢�ӽڵ�  
                var children = $(target).treegrid('getChildren',id);  
                for(var i=0;i<children.length;i++){  
                    var childId = children[i][idField];  
                    if(status)  
                        $(target).treegrid('select',childId);  
                    else  
                        $(target).treegrid('unselect',childId);  
                    selectChildren(target,childId,idField,deepCascade,status);//�ݹ�ѡ���ӽڵ�  
                }  
            }  
        }  
    });  
});