<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
	<head>
		<link rel="stylesheet" href=" <c:url value='/resources/css/bootstrap.min.css' />">
		<link rel="stylesheet" href=" <c:url value='/resources/css/bootstrap-theme.min.css' />">
		<link rel="stylesheet" href=" <c:url value='/resources/css/style.css' />">
		<script src=" <c:url value='/resources/js/jquery-2.1.1.min.js' /> "></script>
		<script src=" <c:url value='/resources/js/bootstrap.min.js' /> "></script>
		<script src=" <c:url value='/resources/js/myscripts.js' /> "></script>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
	</head>
  <body>
    <div id="pageheader">
      <jsp:invoke fragment="header"/>
    </div>
    <div id="body">
		
		
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" >
		<div class="container">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="/loja_project/home.php">Veículos</a>
		    </div>

		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav navbar-right">
			<li><a href="/loja_project/logout.php">Exit</a></li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		  </div>
		</nav>
		<div class="container" style="margin-top: 50px !important">
			<jsp:doBody/>
		</div>
    </div>
    <div id="pagefooter">
      <jsp:invoke fragment="footer"/>
    </div>
  </body>
</html>