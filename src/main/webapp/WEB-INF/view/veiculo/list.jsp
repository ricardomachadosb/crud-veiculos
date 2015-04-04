<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
	<jsp:body>
        <div class="container" style="margin-top: 20px;">
		<h1>Listagem de Veículos</h1>
		<ul class="breadcrumb">
	    	<li class="active">Listagem <span class="divider">/</span></li>
	    	<li><a href="/veiculo/create">Novo</a></span></li>
	    </ul>
	    <c:choose>
		      <c:when test="${message.length() > 0}">
		      	<div class='alert alert-success' role='alert'>${message}</div>
		      </c:when>
		</c:choose>
	    <table class="table table-bordered">
	      <thead>
	        <tr>
	          <th>Fabricante</th>
	          <th>Modelo</th>
	          <th>Ano</th>
	          <th>#</th>
	        </tr>
	      </thead>
	      <tbody>
		
		<c:forEach items="${veiculos}" var="veiculo">
		    <tr>
		        <td><c:out value="${veiculo.getFabricante()}"/></td>
		        <td><c:out value="${veiculo.getModelo()}"/></td>
		        <td><c:out value="${veiculo.getAno()}"/></td>  
		        
		        <td class='col-md-2'>
		        	<a href='veiculo/show/1'> <button class='btn btn-default' type='button'>
		            <span aria-hidden='true' class='glyphicon glyphicon-eye-open'></span>
		            </button></a>
		            
		        	<a href='veiculo/edit/1'> <button class='btn btn-default' type='button'>
		            <span aria-hidden='true' class='glyphicon glyphicon-edit'></span>
		            </button></a>
		
		            <a href='veiculo/delete/1'> <button class='btn btn-default' type='button'>
		            <span aria-hidden='true' class='glyphicon glyphicon-trash'></span>
		            </button></a>
		        </td>
		    </tr>
		</c:forEach>
      </tbody>
    </table>
  </div>
    </jsp:body>
</t:genericpage>