<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<t:genericpage>
	<jsp:body>

<div class="container" style="margin-top:20px;">
<h1>Cadastro de veículos</h1>
	<ul class="breadcrumb">
    	<li> <a href="${contextPath}/veiculo/list" >Listagem </a><span class="divider">/</span></li>
    	<li class="active">Edição</a></span></li>
    </ul>
   <div class="row">
          <div class="col-xs-6" style="margin-left: 25%;">
              <c:choose>
			      <c:when test="${message.length() > 0}">
			      	<div class='alert alert-success' role='alert'>${message}</div>
			      </c:when>
			</c:choose>
             <form autocomplete="off" id="login-form"  method="post" action="${contextPath}/veiculo/update" role="form" enctype="multipart/form-data">
             		<input type="hidden" value="${veiculo.getId()}">
                  <div class="form-group">
                      <label for="nome">Fabricante</label>
                      <input placeholder="Fabricante"  class="form-control" id="fabrincante" name="fabricante" value="${veiculo.getFabricante()}">
                  </div>
                  <div class="form-group">
                      <label for="modelo">Modelo</label>
                      <input placeholder="Modelo"  class="form-control" id="modelo" name="modelo" value="${veiculo.getModelo()}">
                  </div>
                  <div class="form-group">
                      <label for="ano">Ano</label>
                      <input type="number" placeholder="ano"  class="form-control" id="ano" name="ano" value="${veiculo.getAno()}">
                  </div>
                   <div class="form-group">
                      <label for="ano">Foto</label>
                        <input id="arquivos" type="file" class="form-control" id="foto" name="foto" value="http://www.mktesportivo.com/novo/wp-content/uploads/2013/12/img_logo_blue.jpg">
                  </div>
                  <input type="submit" value="Alterar" class="btn btn-custom btn-lg btn-block" id="btn-login">
                  <img src="${pageContext.request.contextPath}/images/${veiculo.getFoto()}"/>
              </form>
          </div>
    </div>
  </div>
  
   </jsp:body>
</t:genericpage>