/**
 * 프로젝트 페이지
 */
	
	$(function() {
		console.log("projectPage.js");

		var admin_sys = ($("input[name='admin_sys']").val());
		var proWrite = $("#proWrite");
		var proUpdate = $("#proUpdate");
		var proDelete = $("#proDelete");
		var tbody = $("#table-body");
		var tpage = $(".table-page");
		
		var proj_no;
		var proj_name;
		var proj_agency;
		var proj_start;
		var proj_end;
		var pageNum = 1;
		
		console.log("현재 로그인 된 계정은 " + admin_sys + "관리자!!!!")
		
		// sys 계정의 모든 프로젝트 리스트 출력
		function projecAlltList(param, callback) {
			var page = param.page;
			$.getJSON("/project/projectAllList/" + page, function(data) {				
				if(callback) {
					callback(data.count, data.list);
				}
			}).error(function() {
				console.log("projectAllList 실패");
			})
		}
		
		// 프로젝트 리스트 받아오기
		function projectList(param, callback) {
			var page = param.page;
			$.getJSON("/project/projectList/" + page, function(data) {				
				if(callback) {
					callback(data.count, data.list);
				}
			}).error(function() {
				console.log("projectList 실패");
			})
		}
		
		// 프로젝트 삭제
		function projectDelete(proj_no, callback) {
			$.ajax({
				type : 'delete',
				url : '/project/projectDelete/' + proj_no,
				success : function(result) {
					console.log("프로젝트 삭제 성공");
					if(callback) {
						callback(result);
					}
				},
				error : function() {
					console.log("프로젝트 삭제 실패");
				}
			});
		}
		
		// 프로젝트 수정
		function projectUpdate(proj_no) {
			$.ajax({
				type : 'get',
				url : '/project/projectUpdate/' + proj_no,
				data : proj_no,
				success : function() {
					console.log("proj_no 전달 성공");
				},
				error : function() {
					console.log("proj_no 전달 실패 : ");
				}
			});
		}
		
		// sys 계정의 모든 프로젝트 리스트 출력
		function showAllList(page) { 
			projecAlltList({
				page : page || 1
			}, function(count, list){
				if(page == -1) {
					pageNum = Math.ceil(count / 10.0);
					showList(pageNum);
					console.log("pageNum : ", pageNum);
					return;
				}
				var str = "";
				if(list == null || list.length == 0) {
					return;
				}
				console.log(list);
				console.log(count);
				for(var i = 0; i < list.length; i++) {
					str += "<tr onmouseover='this.style.backgroundColor=\"#dadada\"' onmouseout='this.style.backgroundColor=\"\"'>";
					str += "<td id='ch-row'><input type='radio' class='radioBtn' name='selected'></td>";
					str += "<td>" + list[i].proj_no + "</td>";
					str += "<td>" + list[i].proj_name + "</td>";
					str += "<td>" + list[i].proj_agency + "</td>";
					str += "<td>" + list[i].proj_start + "</td>";
					str += "<td>" + list[i].proj_end + "</td>";
					str += "</tr>";
				}
				tbody.html(str);
				showListPage(count);
			});
		}
		
		// 프로젝트 리스트 출력
		function showList(page) { 
			projectList({
				page : page || 1
			}, function(count, list){
				if(page == -1) {
					pageNum = Math.ceil(count / 10.0);
					showList(pageNum);
					console.log("pageNum : ", pageNum);
					return;
				}
				var str = "";
				if(list == null || list.length == 0) {
					return;
				}
				console.log(list);
				console.log(count);
				for(var i = 0; i < list.length; i++) {
					str += "<tr onmouseover='this.style.backgroundColor=\"#dadada\"' onmouseout='this.style.backgroundColor=\"\"'>";
					str += "<td id='ch-row'><input type='radio' class='radioBtn' name='selected'></td>";
					str += "<td>" + list[i].proj_no + "</td>";
					str += "<td>" + list[i].proj_name + "</td>";
					str += "<td>" + list[i].proj_agency + "</td>";
					str += "<td>" + list[i].proj_start + "</td>";
					str += "<td>" + list[i].proj_end + "</td>";
					str += "</tr>";
				}
				tbody.html(str);
				showListPage(count);
			});
		}
	    
		
		// 페이징처리
	    function showListPage(count) {
	    	var endNum = Math.ceil(pageNum / 10.0) * 10;
	    	var startNum = endNum-9;
	    	var prev = startNum != 1;
	    	var next = false;
	    	
	    	if(endNum * 10 >= count) {
	    		endNum = Math.ceil(count / 10.0);
	    	}
	    	if(endNum * 10 < count) {
	    		next = true;
	    	}
	    	
	    	var str = "<ul class='pagination justify-content-end'>";
		    if(prev){
		        str += "<li class='page-item'><a href='" + (startNum - 1) + "'class='page-link'>Prev</a></li>";
		    }
		    for(var i = startNum; i <= endNum; i++){
		        var linkStart = pageNum != i ? "'><a href='" + i + "'>" : "active'><a href='" + i + "'>";
		        var linkEnd = pageNum != i ? "</a>" : "</a>" ;
		        str += "<li class='page-item " + linkStart + i + linkEnd + "</a></li>";
		    }
		    if(next){
		        str += "<li class='page-item'><a href='" + (endNum + 1) + "'>Next</a></li>";
		    }
		    str += "</ul>";
		    
		    tpage.html(str);
		}
	    
	    tpage.on("click", "li a", function(e) {
	    	e.preventDefault();
	    	
	    	var targetPageNum = $(this).attr("href");
	    	pageNum = targetPageNum;
	    	
	    	showList(pageNum);
	    });
	    
	    
	    
	    
		// 삭제버튼 클릭시 이벤트 처리
		proDelete.click(function() {
			var chk = $("tr").children().find("input[name=selected]").is(':checked');
			var checked = $("input[name=selected]:checked");
			var proj_no;
			
			checked.each(function(i) {
				var tr = checked.parent().parent().eq(i);
	   			var td = tr.children();				
			
				if(chk) {
					proj_no = Number(td.eq(1).text());
					console.log(proj_no);
				}
			})
			projectDelete(proj_no, function(result){
				console.log('projectDelete() 호출!' + proj_no);
				alert("삭제 되었습니다.")
				location.reload();
			});
		})
		
		
	    // 수정버튼 클릭시 이벤트 처리
		proUpdate.on("click", function() {
			var chk = $("tr").children().find("input[name=selected]").is(':checked');
			var checked = $("input[name=selected]:checked");
			var proj_no;
			
			// 프로젝트 번호 파싱
			checked.each(function(i) {
				var tr = checked.parent().parent().eq(i);
	   			var td = tr.children();				
			
				if(chk) {
					proj_no = Number(td.eq(1).text());
					console.log(proj_no);
				}
			})
			
			if(proj_no == null) {
				alert('프로젝트 번호를 선택하세요!');
				return;
			} else { 
				projectUpdate(proj_no);
				window.open("/project/projectUpdate/", "프로젝트 수정", "width=1200, height=700, left=100, top=50");
			}
	  	});
		

		if(admin_sys === "sys"){
			console.log("login_sys === " + admin_sys);
			showAllList(1)
		}else{
			console.log("로그인 정보 안 넘어옴")
			showList(1);
		}			
	});
	
	// 팝업창에서 호출 할 함수
	function setData() {
		var chk = $("tr").children().find("input[name=selected]").is(':checked');
		var checked = $("input[name=selected]:checked");
		var proj_no;
		
		// 프로젝트 번호 파싱
		checked.each(function(i) {
			var tr = checked.parent().parent().eq(i);
   			var td = tr.children();				
		
			if(chk) {
				proj_no = Number(td.eq(1).text());
				console.log(proj_no);
			}
		})
		return proj_no;
	}

