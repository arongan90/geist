<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Geist</title>
	<!-- main Css-->
    <link href="${pageContext.request.contextPath}/resources/css/document.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" />
    <!-- Data table-->
    <script src = "http://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" ></script>
    <!-- Bootstrap -->
    <script src="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.js"></script>
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Data button-->
    <script src="https://cdn.datatables.net/buttons/1.6.1/js/dataTables.buttons.min.js"></script>
    
</head>

<body>

	<%
		request.setCharacterEncoding("UTF-8");
	
	    String contentPage=request.getParameter("contentPage");
	    if(contentPage==null)
	        contentPage="main.jsp";
	    
	    String admin_nav = (String)session.getAttribute("sys");
	    
	  	if(admin_nav == "sys") {
	  		admin_nav="admin-nav.jsp";
	  	}else{
	  		admin_nav="nav.jsp";
	  	}
	%>
	
	
	<div id="header">
		<jsp:include page="topnav.jsp" />
	</div>
    <div id="side">
    	<jsp:include page="<%=admin_nav%>" />
    </div>
    
	<div class="app-container fixed-sidebar fixed-header closed-sidebar">
        <!-- Lower -->
        <div class="app-main">
            <!-- Main page -->
            <div class="app-main-outer">
                <!-- Main button page -->
                <div class="app-main_inner">
                    <div class="container-fluid">
                        <div class="container">
                            <!-- Title -->
                            <div class="app-page-title" style="margin: 0px; padding: 50px 0px 30px;">
                                <div class="page-title-heading">
                                    <i class="pe-7s-document-inverse"></i>
                                    <h1><sub>사내공지</sub></h1>
                                    <p>
                                </div>
                                <hr class="Geist-board-hr">
                            </div>
                            <!-- Write -->
                            <article>
                                <div class="container" role="main" style="padding: 0px 100px 0px 100px;">
                                    <form name="notice-w-form" id="notice-w-form" role="form">
                                        <div class="mb-3">
                                            <label for="title">제목</label>
                                            <input type="text" class="form-control" name="title" id="NOTI_TITLE"
                                                placeholder="제목을 입력해 주세요">
                                        </div>
                                        <div class="mb-3">
                                            <label for="content">내용</label>
                                            <textarea class="form-control" rows="15" name="content" id="NOTI_CONTENT"
                                                placeholder="내용을 입력해 주세요"></textarea>
                                        </div>
                                    </form>
                                    <div class="pt-2" style="float: right;">
                                        <button type="button" class="btn btn-sm dt-button" id="btnSave" onclick="javascript:insertBoard();">저장</button>
                                        <button type="button" class="btn btn-sm dt-button" id="btnList" onclick="javascript:goBoardList();">목록</button>
                                    </div>

                                </div>

                            </article>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div id="footer">
    	<jsp:include page="footer.jsp" />
    </div>
    
    <!--js-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/include.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/register.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/My-register.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/document-write.js"></script>
    
</body>
</html>