<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
	<head>
		<link rel="stylesheet" href="resources/css/bootstrap.min.css">
		<link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="resources/css/style.css">
		<script src="resources/js/jquery-2.1.1.min.js"></script>
		<script src="resources/js/bootstrap.min.js"></script>
		<script src="resources/js/myscripts.js"></script>
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
		      <a class="navbar-brand" href="/loja_project/home.php">Loja axé melodico</a>
		    </div>

		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
			<li class="active"><a href="/loja_project/home.php">Home</a></li>
			<li class="dropdown">
			  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cadastro<span class="caret"></span></a>
			  <ul class="dropdown-menu" role="menu">
			  	<li><a href="/loja_project/grupo/list.php">Grupo</a></li>
			    <li><a href="/loja_project/album/list.php">Albuns</a></li>
			    <li><a href="/loja_project/faixa/list.php">Faixas</a></li>
			  </ul>
			</li>
		      </ul>
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