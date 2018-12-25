$(document).ready(function() {
	// 初始化菜单树
	function initTree () {
		// 设置左侧菜单树参数
		$('#woMenuTreeUl').tree({
			url : 'getChildren', // 设置菜单数据URL
			onClick: function(node) {// 菜单树节点点击事件
				if ($('#woContentTabPanel').tabs('exists', node.text)) {// 对应的tab是否已经存在
					$('#woContentTabPanel').tabs('select', node.text); // 选择已有tab
				} else if (node.attributes.url) {
					$('#woContentTabPanel').tabs ('add', {
						href : node.attributes.url,
						// content : '<iframe width=95% height=95% src="' + node.attributes.resource + '" style="margin:10px 10px"/>',
						closable : true,
						title : node.text
					});
				}
			}
		});
		// tabpanel上增加按钮，实现退出系统功能
		$('#woContentTabPanel').panel ({
			tools : [{
				iconCls : 'icon-no',
				handler : function () {
					$.messager.confirm('确认', '您确定要退出吗？', function(r){
						if (r){
							$.post('logout', function(result){
								if (result.success){
									window.location = "";
								} else {
									$.messager.alert({
										title: '错误',
										icon : 'error',
										msg: result.message
									});
								}
								
							},'json');
						}
					});
				}
			}]
		});
	}
	
	$.post('isAuthenticated', function(result) {
		// 如果登录成功：
		if (result.success) {
			initTree ();
		} else {
			// 弹出登录对话框
			$('#woDlgLogin').dialog ('open');
		}
	});
	
	$.extend($.fn.validatebox.defaults.rules, {     
	    phoneNum: { //验证手机号    
	        validator: function(value, param){  
	         return /^1[3-8]+\d{9}$/.test(value);  
	        },     
	        message: '请输入正确的手机号码!'    
	    },
	    email: { //验证邮箱
	        validator: function(value, param){  
	            return /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(value);  
	           },     
	           message: '请输入正确的邮箱!'    
	       }
	}); 
	// 定义登录按钮点击事件
	$('#woButtonsLogin #loginButton').linkbutton({
		onClick : function () {
			$('#woFormLogin').form('submit',{
				url: 'authentication',
				onSubmit: function(){
					return $(this).form('validate');
				},
				success: function(result) {
					var result = eval('(' + result + ')');
					if (result.success == false){
						$.messager.alert({
							title: '错误',
							icon : 'error',
							msg: result.message
						});
					} else {
						$('#woDlgLogin').dialog('close');		// close the dialog
						initTree ();
						// window.location = "";
					}
				}
			});
		}
	});
	
	//定义点击注册按钮弹出注册框事件
	$('#woButtonsLogin #registerButton').linkbutton({
		onClick : function(){
			$('#woDlgRegister').dialog('open');
			$('#woFormRegister').form('clear');
		}
	});
	//定义注册按钮点击事件
	$('#saveRegister').linkbutton({
		onClick : function(){
			$('#woFormRegister').form('submit',{
				url: 'register',
				onSubmit: function(){
					return $(this).form('validate');
				},
				success: function(result) {
					var result = eval('(' + result + ')');
					if (result.success == false){
						$.messager.alert({
							title: '错误',
							icon : 'error',
							msg: result.message
						});
					} else {
						// $('#woDlgLogin').dialog('close');		// close the dialog
						// initTree ();
						$('#woDlgRegister').dialog('close');
					}
				}
			});
		}
	});
	//定义弹出修改密码对话框
	$('#woChangePW').find('a').linkbutton({
		onClick:function(){
			$('#woDlgChange').dialog('open');
			$('#fm-change').form('clear');
		}
	});
	//定义修改密码点击事件
	$('#savePassword').linkbutton({
		onClick:function(){
			$('#fm-change').form('submit',{
				url:'sys/user/change',
				onSubmit:function(){
					return $(this).form('validate');
				},
				success:function(result){
					var result=eval('('+result+')');
					if(result.success==false){
						$.messager.alert({
							title: '错误',
							icon : 'error',
							msg: result.message
						});
					}else{
						window.location = "";
					}
				}
			});
		}
	});
});