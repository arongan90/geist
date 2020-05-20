

var MypageService = (function(){
		function detailView(param, callback, error){
			var emp_no = param.emp_no;
			$.getJSON("/mypage/" + emp_no + ".json", function(data){
				if(callback){
					callback(data);
				}
			}).fail(function(xhr, status, err){
				if(error){
					error();
				}
			});
		}
		
		function modifyEmp(param, callback, error){
			$.ajax({
				type : 'POST',
				url : '/mypage/detail'+emp_no,
				data : JSON.stringify(param),
				contentType : "application/json; charset=utf-8",
				success : function(result, status, xhr){
					if(callback){
						callback(result);
					}
				},
				error : function(xhr, status, err){
					if(error){
						error(err);
					}
				}
			});
		}
		
		return{
			detailView : detailView,
			modifyEmp : modifyEmp,
		};
	})();
	
$(document).ready(function(){
	var mypageForm = $("#mypage-form");
	var mypageBtn = $("#My-btnSave");
	detailView($("input[name='login_no']").val());
	
	function detailView(emp_no){
		MypageService.detailView({
			emp_no : emp_no
		}, function(data){
			$("div[name='emp_name']").append("<h1 class='m-0'>"+data.emp_name+" "+data.emp_position+'</h1>');
			$("div[name='emp_name']").append("<h6 class='m-0 pt-3 pb-4' name='dept_no'>"+"Geist회사 >"+" "+data.dept_no+'</h1>');
			
			$("thead").append("<th scope='row'>"+"아이디"+"</th>");
			$("thead").append("<td>"+data.emp_id+"</td>");
			
			$("tbody").append("<tr><th scope='row'>"+"사원번호"+"</th>"+"<td>"+data.emp_no+"</td></tr>");
			$("tbody").append("<tr><th scope='row'>"+"직책"+"</th>"+"<td>"+data.emp_position+"</td></tr>");
			$("tbody").append("<tr><th scope='row'>"+"급여"+"</th>"+"<td>"+data.emp_sal+"</td></tr>");
			$("tbody").append("<tr><th scope='row'>"+"입사일"+"</th>"+"<td>"+data.emp_date+"</td></tr>");
			$("tbody").append("<tr><th scope='row'>"+"생년월일"+"</th>"+"<td>"+data.emp_birth+"</td></tr>");
			
		});
		
		mypageBtn.on("click", function(e){
			var emp_pw = $("#My-pwd").val();
			var emp_tel = $("#My-company-phone").val();
			var emp_phone = $("#My-phone").val();
			var emp_address = $("#My-addr1").val();
			emp_address += $("#My-addr2").val();
			emp_address += $("#My-addr-etc").val();
						
			MypageService.modifyEmp({
				emp_pw : emp_pw,
				emp_tel : emp_tel,
				emp_phone : emp_phone,
				emp_address : emp_address
			}, function(result){
				alert(result);
				location.href = "/mypage";
			});
		});
		
	}
});