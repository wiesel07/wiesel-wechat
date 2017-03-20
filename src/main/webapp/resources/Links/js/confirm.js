$(document).ready(function(){
			 $("#modal").modal();  
		});
			function test(){
		var charge;
		$.post('/PrintService/pingPlusPlus_createCharge2.action', {
			printorderID : GetQueryString("orderID")
		}, function(response) {
			console.log(response);
			//var html= $.ajax({url:"/PrintService/createCharge",async:false,type:"POST"});
			charge = eval("("+response+")");
			//return;
			pingppPc.createPayment(charge, function(result, error){
			    if (result == "success") {
			        // 只有微信公众账号 wx_pub 支付成功的结果会在这里返回，其他的 wap 支付结果都是在 extra 中对应的 URL 跳转。
			    } else if (result == "fail") {
			        // charge 不正确或者微信公众账号支付失败时会在此处返回
			    } else if (result == "cancel") {
			        // 微信公众账号支付取消支付
			    }
			});
			
		});
		
	}