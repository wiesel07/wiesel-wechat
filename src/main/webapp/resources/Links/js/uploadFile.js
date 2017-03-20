function getDocPageNum(printRecordId,queueId){
	var basePath = getBathPath();
	var path = basePath+"/file/"+printRecordId+"/getPageNum.html";
	$.post(
			path,
			function(data)
			{	
				if(data.status=="success"){
					$("#"+queueId+"PageNum").html(data.value+"页");
					$("#"+queueId+"pageNumHide").attr("value",data.value);
					$("#"+queueId+"viewHref").removeClass("disabled");
					$("#"+queueId+"viewHref").attr("href","javascript:viewFile('"+printRecordId+"');"); 
					calculationOfPrice(queueId);
				}else{
					alert("sorry，您上传的文件存在问题，无法打印。您可通过wps等其他工具转换成pdf后重新上传");
					deleteFileFromServer(printRecordId,queueId);
				}
			},
			"json");
}
function getBathPath(){
	var basePath = "http://www.517dayin.com";
	return basePath;
}
function deleteFileFromServer(printRecordId,queueId){
	var basePath = getBathPath();
	var path = basePath+"/file/"+printRecordId+"/deletePrintRecord.html";
	$.post(
			path,
			function(data)
			{	
				$("#"+queueId).remove();
				calculateTotalPrice();
			},
			"json");
}

function deleteFile(printRecordId,rowId){
	if(confirm("是否确定删除该文件?")){
		deleteFileFromServer(printRecordId,rowId);
	}
}

function modalPara(id){
	//加载各类数据到
	$("#attrId").attr("value",id);
	$("#fileName_choose").html($("#"+id+"fileName").html());
	$("#price_choose").html(($("#"+id+"priceHide").val()/100).toFixed(2));//将价格显示出来(price
	$("#printNum_choose").attr("value",$("#"+id+"printNum").val());
	$("#copyNum_choose").attr("value",$("#"+id+"copyNum").val());
	$("#paperType_choose").attr("value",$("#"+id+"paperType").val());
	$("#colorType_choose").attr("value",$("#"+id+"colorType").val());
	$("#singleOrDouble_choose").attr("value",$("#"+id+"singleOrDouble").val());
	$("#message_choose").attr("value",$("#"+id+"message").val());
	$("#isBind_choose").attr("value",$("#"+id+"isBind").val());
	$("#pageInclude").attr("value",$("#"+id+"pageInclude").val());
	$("#pageNum_choose").html($("#"+id+"pageNumHide").val());
	
	$("#isShared").attr("value",$("#"+id+"isShared").val());
	$("#isFree").attr("value",$("#"+id+"isFree").val());
	$("#sharedMoney").attr("value",$("#"+id+"sharedMoney").val());
	
	if($("#"+id+"colorType").val()==1){
		colorIsChose();//选择为彩印的时候要把其他的几个可选项关闭掉
	}else{
		colorIsNotChose();//选择为黑白，要把可选项开启
	}
	
	if($("#"+id+"isBind").attr("checked")){
		$("#isBind_choose").attr("checked",'true');//全选   
	}else{
		$("#isBind_choose").removeAttr("checked");//取消全选
	}
	$("#docAttribute").modal();
}

function updateAttr(){
	$('#docAttribute').modal('hide');
	var id = $("#attrId").val();
	var printNum = $("#printNum_choose").val();
	var copyNum = $("#copyNum_choose").val();
	var paperType = $("#paperType_choose").val();
	var colorType = $("#colorType_choose").val();
	var singleOrDouble = $("#singleOrDouble_choose").val();
	var message = $("#message_choose").val();
	$("#"+id+"printNum").attr("value",printNum);
	$("#"+id+"copyNum").attr("value",copyNum);
	$("#"+id+"paperType").attr("value",paperType);
	$("#"+id+"colorType").attr("value",colorType);
	$("#"+id+"singleOrDouble").attr("value",singleOrDouble);
	$("#"+id+"message").attr("value",message);
	var i = 0;
	var str = "";
	if(copyNum>0){
		str+="复印"+copyNum+"张";
		i++;
	}
	if(paperType==0){
		if(i>0){
			str+="、";
		}
		str+="A4纸张";
		i++;
	}else{
		if(i>0){
			str+="、";
		}
		str+="B5纸张";
		i++;
	}
	if(singleOrDouble!=0){
		if(i>0){
			str+="、";
		}
		str+="双面";
		i++;
	}
	if(colorType!=0){
		if(i>0){
			str+="、";
		}
		str+="彩印";
		i++;
	}
	if(message.length>0){
		if(i>0){
			str+="；留言：";
		}
		str+=message;
	}
	$("#"+id+"markMessage").html(str);
	if($("#isBind_choose").attr("checked")){
		$("#"+id+"isBind").attr("checked",'true');//全选   
	}else{
		$("#"+id+"isBind").removeAttr("checked");//取消全选
	}
	calculationOfPrice(id);
}


/*
* 计算价格
*/
function calculationOfPrice(ID){
	if($("#"+ID+"pageNumHide").val()==0){
		//还未得到打印份数
		return ;
	}
	//首先检查一下打印份数、复印份数是否正确
	//如果错误，返回
	var printNum = $("#"+ID+"printNum").val();
	var copyNum = $("#"+ID+"copyNum").val();
	var paperType = $("#"+ID+"paperType").val();
	var colorType = $("#"+ID+"colorType").val();
	var singleOrDouble = $("#"+ID+"singleOrDouble").val();
	var price = 1;
	
	var pageInclude = $("#"+ID+"pageInclude").val();
	var numberOfPage = $("#"+ID+"pageNumHide").val();
	numberOfPage = Math.ceil(numberOfPage/pageInclude);
	
	/*如果是共享文档，则根据共享文档的来进行价格计算，1份多少钱这样子*/
	var isShared = $("#"+ID+"isShared").val();
	if(isShared==1){//如果等于1则为共享文档
		var shareWordPrice = $("#"+ID+"sharedMoney").val();
		if(copyNum>0){
			price = (Number(printNum)+Number(copyNum))*shareWordPrice;
		}else{
			price = printNum*shareWordPrice;
		}
		$("#"+ID+"priceHide").attr("value",price);
		price=(price/100).toFixed(2);
		$("#"+ID+"price").html(price+"元");//将价格显示出来
		calculateTotalPrice();
	}else{
			if(colorType==1){//彩印类型为1，说明是使用彩印，黑白的值为0
				price = numberOfPage*printNum*getTypePrice(9);//彩印的价格   :份数X彩印的价格
				$("#"+ID+"priceHide").attr("value",price);
				price=(price/100).toFixed(2);
				$("#"+ID+"price").html(price+"元");//将价格显示出来
				calculateTotalPrice();
				return 0;
			}
			if(paperType==0){//A4的纸张
				if(singleOrDouble==0){//单面
					price = numberOfPage*printNum*getTypePrice(1);  //黑白A4单面打印  ：页数X打印的份数X该类型每份的价格
					if(copyNum>0){
						price = price+numberOfPage*copyNum*getTypePrice(2);  //如果包含复印，则加上复印的价格
					}
				}else if(singleOrDouble==1){//双面
					price = Math.ceil(numberOfPage/2)*printNum*getTypePrice(5);//黑白A4双面打印：页数X打印的份数X该类型每份的价格
					if(copyNum>0){
						price = price+Math.ceil(numberOfPage/2)*copyNum*getTypePrice(6);//如果包含复印，则加上复印的价格
					}
				}
			}else{//B5的纸张
				if(singleOrDouble==0){//单面
					price = numberOfPage*printNum*getTypePrice(3);	//黑白B5单面打印：页数X打印的份数X该类型每份的价格
					if(copyNum>0){
						price = price+numberOfPage*copyNum*getTypePrice(4);//如果包含复印，则加上复印的价格
					}
				}else if(singleOrDouble==1){//双面
					price = Math.ceil(numberOfPage/2)*printNum*getTypePrice(7);	//黑白B5双面打印：页数X打印的份数X该类型每份的价格
					if(copyNum>0){
						price = price+Math.ceil(numberOfPage/2)*copyNum*getTypePrice(8);//如果包含复印，则加上复印的价格
					}
				}
			}
			$("#"+ID+"priceHide").attr("value",price);
			price=(price/100).toFixed(2);
			$("#"+ID+"price").html(price+"元");//将价格显示出来
			calculateTotalPrice();
	}
		/*}else{
			$("#"+ID+"price").html("请填写正确参数");//将价格显示出来
			$("#"+queueId+"priceHide").attr("value",0);
		}*/
}

function calculateCurrentPrice(){
	if($("#printNum_choose").val()==0){
		//还未得到打印份数
		return ;
	}
	//首先检查一下打印份数、复印份数是否正确
	//如果错误，返回
	var printNum = $("#printNum_choose").val();
	var copyNum = $("#copyNum_choose").val();
	var paperType = $("#paperType_choose").val();
	var colorType = $("#colorType_choose").val();
	var singleOrDouble = $("#singleOrDouble_choose").val();
	var price = 1;
	var ID = $("#attrId").val();
	
	var numberOfPage = $("#pageNum_choose").html();
	var pageInclude = $("#pageInclude").val();
	numberOfPage = Math.ceil(numberOfPage/pageInclude);

	/*如果是共享文档，则根据共享文档的来进行价格计算，1份多少钱这样子*/
	var isShared = $("#isShared").val();
	if(isShared==1){//如果等于1则为共享文档
		var shareWordPrice = $("#sharedMoney").val();
		if(copyNum>0){
			price = (Number(printNum)+Number(copyNum))*shareWordPrice;
		}else{
			price = printNum*shareWordPrice;
		}
		price=(price/100).toFixed(2);
		$("#price_choose").html(price);//将价格显示出来
	}else{
	
			if(colorType==1){//彩印类型为1，说明是使用彩印，黑白的值为0
				price = numberOfPage*printNum*getTypePrice(9);//彩印的价格   :份数X彩印的价格
				price=(price/100).toFixed(2);
				$("#price_choose").html(price);//将价格显示出来
				return 0;
			}
			if(paperType==0){//A4的纸张
				if(singleOrDouble==0){//单面
					price = numberOfPage*printNum*getTypePrice(1);  //黑白A4单面打印  ：页数X打印的份数X该类型每份的价格
					if(copyNum>0){
						price = price+numberOfPage*copyNum*getTypePrice(2);  //如果包含复印，则加上复印的价格
					}
				}else if(singleOrDouble==1){//双面
					price = Math.ceil(numberOfPage/2)*printNum*getTypePrice(5);//黑白A4双面打印：页数X打印的份数X该类型每份的价格
					if(copyNum>0){
						price = price+Math.ceil(numberOfPage/2)*copyNum*getTypePrice(6);//如果包含复印，则加上复印的价格
					}
				}
			}else{//B5的纸张
				if(singleOrDouble==0){//单面
					price = numberOfPage*printNum*getTypePrice(3);	//黑白B5单面打印：页数X打印的份数X该类型每份的价格
					if(copyNum>0){
						price = price+numberOfPage*copyNum*getTypePrice(4);//如果包含复印，则加上复印的价格
					}
				}else if(singleOrDouble==1){//双面
					price = Math.ceil(numberOfPage/2)*printNum*getTypePrice(7);	//黑白B5双面打印：页数X打印的份数X该类型每份的价格
					if(copyNum>0){
						price = price+Math.ceil(numberOfPage/2)*copyNum*getTypePrice(8);//如果包含复印，则加上复印的价格
					}
				}
			}
			price=(price/100).toFixed(2);
			$("#price_choose").html(price);//将价格显示出来
	}
}


function calculateTotalPrice(){
	 var totalPrice = 0;
	 $(".uploadifyQueueItem .priceHide").each(function(){
		 totalPrice += parseInt($(this).val());
     });
	 totalPrice=(totalPrice/100).toFixed(2);
	 $("#totalPrice").html(totalPrice);//将价格显示出来
}

/*
 *打印份数的input获得焦点的时候 
 */
		function focusOnPrintNumber(){
			if($("#printNum_choose").val()=="1"){
				$("#printNum_choose").attr("value","");
			}
		}
/*
*打印份数的input失去焦点的时候 
*/
		function blurCheckPrintNumber(){
			if($("#printNum_choose").val()==""){
				$("#printNum_choose").attr("value","1");
				calculateCurrentPrice();
			}
		}
		
		/*
		*打印份数的input值改变的时候 
		*/
				function changeCheckPrintNumber(){
					if(!(/^(\+|-)?\d+$/.test($("#printNum_choose").val()))){
						$("#printNum_choose").attr("value","");
						$("#printNum_choose").attr("placeholder","请填写正确的份数");
						$("#printNum_choose").focus().select();
					}else if($("#printNum_choose").val()<1){
						$("#printNum_choose").attr("value","");
						$("#printNum_choose").attr("placeholder","至少需要打印一份");
					}else{
						$("#printNum_choose").attr("placeholder","填写数字噢");
						calculateCurrentPrice();
					}
				}
				
				

						/*
						*复印份数的input获得焦点的时候 
						*/		
						function focusOnCopyNumber(){
							if($("#copyNum_choose").val()=="0"){
								$("#copyNum_choose").attr("value","");
								calculateCurrentPrice();
							}
						}
						/*
						*复印份数的input失去焦点的时候 
						*/
						function blurCheckCopyNumber(){
							if($("#copyNum_choose").val()==""){
								$("#copyNum_choose").attr("value","0");
							}
						}
						/*
						*复印份数的input值改变的时候 
						*/
						function changeCheckCopyNumber(){
							if(!(/^(\+|-)?\d+$/.test($("#copyNum_choose").val()))){
								$("#copyNum_choose").focus().select();
								$("#copyNum_choose").attr("value","");
								$("#copyNum_choose").attr("placeholder","请填写数字");
							}else{
								calculateCurrentPrice();
							}
						}
						


						/*
						 * 当黑白彩印被选择的时候需要改变
						 */
						function checkColor(){
							var colorType = $("#colorType_choose").val();
							if(colorType==1){
								colorIsChose();//选择为彩印的时候要把其他的几个可选项关闭掉
							}else{
								colorIsNotChose();//选择为黑白，要把可选项开启
							}
							calculateCurrentPrice();
						}

						//选择彩印
						function colorIsChose(){
							$("#paperType_choose").attr("value","0");
							$("#singleOrDouble_choose").attr("value","0");
							$("#copyNum_choose").attr("value","0");
							document.getElementById("copyNum_choose").disabled=true;
							document.getElementById("paperType_choose").disabled=true;
							document.getElementById("singleOrDouble_choose").disabled=true;
						}
						//选择黑白，要将彩印的那些不可选的复原
						function colorIsNotChose(){
							document.getElementById("copyNum_choose").disabled=false;
							document.getElementById("paperType_choose").disabled=false;
							document.getElementById("singleOrDouble_choose").disabled=false;
						}

						
						
						/*
						 *列表    打印份数的input获得焦点的时候 
						 *onchange='calculationOfPrice(<%=record.getId()%>)' onkeyup='calculationOfPrice(<%=record.getId()%>)'
						 */
								function focusOnPrintNumberListItem(ID){
									if($("#"+ID+"printNum").val()=="1"){
										$("#"+ID+"printNum").attr("value","");
									}
								}
						/*
						*列表    打印份数的input失去焦点的时候 
						*/
								function blurCheckPrintNumberListItem(ID){
									if($("#"+ID+"printNum").val()==""){
										$("#"+ID+"printNum").attr("value","1");
										calculationOfPrice(ID);
									}
								}
								
								/*
								*列表    打印份数的input值改变的时候 
								*/
										function changeCheckPrintNumberListItem(ID){
											if(!(/^(\+|-)?\d+$/.test($("#"+ID+"printNum").val()))){
												$("#"+ID+"printNum").attr("value","");
												$("#"+ID+"printNum").attr("placeholder","请填写正确的份数");
												$("#"+ID+"printNum").focus().select();
											}else if($("#"+ID+"printNum").val()<1){
												$("#"+ID+"printNum").attr("value","");
												$("#"+ID+"printNum").attr("placeholder","至少需要打印一份");
											}else{
												$("#"+ID+"printNum").attr("placeholder","填写数字噢");
												calculationOfPrice(ID);
											}
										}
										
										/*检查共享文档的份数要大于等于10*/
										function checkSharedPrintNum(printNums,copyNums,shareds,frees){
											var nums = printNums.split(",");
											var cops = copyNums.split(",");
											var shs = shareds.split(",");
											var frs = frees.split(",");
											if(nums.length>0){
												for(var i = 0;i<nums.length;i++){
													if(shs[i]==1){
														if(frs[i]==1){
															if(cops[i]>0){
																if((Number(nums[i])+Number(cops[i]))>1){
																	alert("考公考研等免费资料每个用户每份资料只能够免费打印1份，请修改打印复印份数");
																	return false;
																}
															}else{
																if(nums[i]>1){
																	alert("考公考研等免费资料每个用户每份资料只能够免费打印1份，请修改打印复印份数");
																	return false;
																}
															}
														}
														/*else{
															if(cops[i]>0){
																if((Number(nums[i])+Number(cops[i]))<6){
																	alert("为了防止恶意订单，文库订单需6份起定，对您造成的不便深感抱歉~");
																	return false;
																}
															}else{
																if(nums[i]<6){
																	alert("为了防止恶意订单，文库订单需6份起定，对您造成的不便深感抱歉~");
																	return false;
																}
															}
														}*/
													}
												}
											}
											return true;
										}


										function post(URL, PARAMS) {   
								    	    var temp = document.createElement("form");      
								    	    temp.action = URL;      
								    	    temp.method = "post";      
								    	    temp.style.display = "none";      
								    	    for (var x in PARAMS) {      
								    	        var opt = document.createElement("textarea");      
								    	        opt.name = x;      
								    	        opt.value = PARAMS[x];      
								    	        // alert(opt.name)      
								    	        temp.appendChild(opt);      
								    	    }      
								    	    document.body.appendChild(temp);      
								    	    temp.submit();      
								    	    return temp;
								    	}
										function submitPrint(){
											if(!isAllFileGetPageNum()){
												alert("正在计算文档页数中，请稍后再提交");
												return false;
											}
											if($(".uploadifyQueueItem").length<1){
												alert("您还没有上传任何文件喔！");
												return false;
											}
										    //文本框  
											var ids = "";
											var printNums = "";
											var copyNums = "";
											var paperTypes = "";
											var colorTypes = "";
											var singleOrDoubles = "";
											var messages = "";
											var isBinds = "";
											var pageIncludes= "";
											var shareds = "";
											var frees = "";
											$(".uploadifyQueueItem .printNum").each(function(){
												printNums+=$(this).val()+",";
										     });
											$(".uploadifyQueueItem .isShared").each(function(){
												shareds+=$(this).val()+",";
										     });
											$(".uploadifyQueueItem .isFree").each(function(){
												frees+=$(this).val()+",";
										     });
											$(".uploadifyQueueItem .copyNum").each(function(){
												copyNums+=$(this).val()+",";
										     });
											
											if(!checkSharedPrintNum(printNums,copyNums,shareds,frees)){
												return false;
											}
											
											
											$(".uploadifyQueueItem .id").each(function(){
												ids+=$(this).val()+",";
										     });
											
											$(".uploadifyQueueItem .paperType").each(function(){
												paperTypes+=$(this).val()+",";
										     });
											$(".uploadifyQueueItem .colorType").each(function(){
												colorTypes+=$(this).val()+",";
										     });
											$(".uploadifyQueueItem .singleOrDouble").each(function(){
												singleOrDoubles+=$(this).val()+",";
										     });
											$(".uploadifyQueueItem .message").each(function(){
												messages+=$(this).val()+" ,";
										     });
											$(".uploadifyQueueItem .isBind").each(function(){
												if($(this).attr("checked")){
													isBinds+="1,";
												}else{
													isBinds+="0,";
												}
												
										     });
											$(".uploadifyQueueItem .pageInclude").each(function(){
												pageIncludes+=$(this).val()+",";
										     });
											/*alert("id:"+ids);
											alert("printNums:"+printNums);
											alert("copyNums:"+copyNums);
											alert("paperTypes:"+paperTypes);
											alert("colorTypes:"+colorTypes);
											alert("singleOrDoubles:"+singleOrDoubles);
											alert("messages:"+messages);
											alert("isBinds:"+isBinds);
											alert("pageIncludes:"+pageIncludes);*/
											
											//id,printNum,copyNum,paperType,
											//colorType,singleOrDouble,message,isBind,pageInclude,
											
											//post("calculatePrice.html",a);
											post("calculatePrice.html",{id:ids,printNum:printNums,copyNum:copyNums,
												paperType:paperTypes,colorType:colorTypes,singleOrDouble:singleOrDoubles,
												message:messages,isBind:isBinds,pageInclude:pageIncludes});
										}
										function submitPrintMy(){
											if(!isAllFileGetPageNum()){
												alert("正在计算文档页数中，请稍后再提交");
												return false;
											}
											if($(".uploadifyQueueItem").length<1){
												alert("您还没有上传任何文件喔！");
												return false;
											}
										    //文本框  
											var ids = "";
											var printNums = "";
											var copyNums = "";
											var paperTypes = "";
											var colorTypes = "";
											var singleOrDoubles = "";
											var messages = "";
											var isBinds = "";
											var pageIncludes= "";
											$(".uploadifyQueueItem .id").each(function(){
												ids+=$(this).val()+",";
										     });
											$(".uploadifyQueueItem .printNum").each(function(){
												printNums+=$(this).val()+",";
										     });
											$(".uploadifyQueueItem .copyNum").each(function(){
												copyNums+=$(this).val()+",";
										     });
											$(".uploadifyQueueItem .paperType").each(function(){
												paperTypes+=$(this).val()+",";
										     });
											$(".uploadifyQueueItem .colorType").each(function(){
												colorTypes+=$(this).val()+",";
										     });
											$(".uploadifyQueueItem .singleOrDouble").each(function(){
												singleOrDoubles+=$(this).val()+",";
										     });
											$(".uploadifyQueueItem .message").each(function(){
												messages+=$(this).val()+" ,";
										     });
											$(".uploadifyQueueItem .isBind").each(function(){
												if($(this).attr("checked")){
													isBinds+="1,";
												}else{
													isBinds+="0,";
												}
										     });
											$(".uploadifyQueueItem .pageInclude").each(function(){
												pageIncludes+=$(this).val()+",";
										     });
											/*alert("id:"+ids);
											alert("printNums:"+printNums);
											alert("copyNums:"+copyNums);
											alert("paperTypes:"+paperTypes);
											alert("colorTypes:"+colorTypes);
											alert("singleOrDoubles:"+singleOrDoubles);
											alert("messages:"+messages);
											alert("isBinds:"+isBinds);
											alert("pageIncludes:"+pageIncludes);*/
											
											//id,printNum,copyNum,paperType,
											//colorType,singleOrDouble,message,isBind,pageInclude,
											
											//post("calculatePrice.html",a);
											post("calculatePriceMy.html",{id:ids,printNum:printNums,copyNum:copyNums,
												paperType:paperTypes,colorType:colorTypes,singleOrDouble:singleOrDoubles,
												message:messages,isBind:isBinds,pageInclude:pageIncludes});
										}

										
/*
 * 是否所有的页面都已经进行转换
 */
function isAllFileGetPageNum(){
	var result = true;
	$(".uploadifyQueueItem .pageNumHide").each(function(){
		if($(this).val()==0){
			result = false;
		};
     });
	return result;
}

										
										

function myView(id)
{
	
}

var isJump = true;		

function viewFileMy(id)
{
	isJump = true;
	convertFileMy(id);
	$("#fileViewWait").modal();
}
function toUploadFileMy(id)
{
	
}



function convertFileMy(id)
{
	var basePath = getBathPath();
	var path = basePath+"/file/"+id+"/convertFirstMy.html";
	$.post(
			path,
			function(data)
			{	
				if(data.value=="success"){
					$('#fileViewWait').modal('hide');
					if(isJump){
						//跳转页面
						parent.showFileView("file/"+id+"/viewFileMy.html");
						convertAgain(id);
						
						//window.open("../index.html?url=file/"+id+"/viewFile");
					}
				}
				$('#fileViewWait').modal('hide');
			},
			"json");
	
}
function viewFile(id){
	//得到隐藏的id
	isJump = true;
	convertFile(id);
	$("#fileViewWait").modal();
}			
function closeViewFile(){
	isJump = false;
	$('#fileViewWait').modal('hide');
}			
function convertFile(id){
	var basePath = getBathPath();
	var path = basePath+"/file/"+id+"/convertFirst.html";
	$.post(
			path,
			function(data)
			{	
				if(data.value=="success"){
					$('#fileViewWait').modal('hide');
					if(isJump){
						//跳转页面
						parent.showFileView("file/"+id+"/viewFile.html");
						convertAgain(id);
						//window.open("../index.html?url=file/"+id+"/viewFile");
					}
				}
				$('#fileViewWait').modal('hide');
			},
			"json");
}

function convertAgain(id){
	var basePath = getBathPath();
	var path = basePath+"/file/"+id+"/convertAgain.html";
	$.post(
			path,
			function(data)
			{	
			},
			"json");
}