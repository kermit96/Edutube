<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<script src="/edutube/resources/JS/Link.js"></script>
<div id="leftcontainer">
<!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
           <c:if test="${NOTICE=='1'}">
          <!-- search form -->
          <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
              <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search">검색</i></button>
              </span>
            </div>
          </form>          
          </c:if>
          <!-- /.search form -->
      
         <c:if test="${NOTICE!='1'}">
          <!-- search form -->
          <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
              <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search">검색</i></button>
              </span>
            </div>
          </form>
          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">            
              <li class="treeview">
              <a href="#">
                <i class="fa fa-folder"></i> <span>어학</span>
                
              </a>
              <ul class="treeview">
                <li><a href="JavaScript:goKorIntro()"><i class="fa fa-anchor"></i> 국어</a></li>
                <li><a href="pages/examples/profile.html"><i class="fa fa-anchor"></i> 영어</a></li>
                <li><a href="pages/examples/login.html"><i class="fa fa-anchor"></i> 일본어</a></li>
               
              </ul>
              </li>
             <li class="treeview">
              <a href="#">
                <i class="fa fa-folder"></i> <span>컴퓨터</span>
               
              </a>
              <ul class="treeview">
                <li><a href="pages/examples/invoice.html"><i class="fa fa-circle-o"></i>웹프로그래밍</a></li>
                <li><a href="pages/examples/profile.html"><i class="fa fa-circle-o"></i>안드로이드</a></li>
                <li><a href="pages/examples/login.html"><i class="fa fa-circle-o"></i>아이폰</a></li>
              </ul>
             </li>
             <li class="treeview">
              <a href="#">
                <i class="fa fa-folder"></i> <span>요리</span>
               
              </a>
              <ul class="treeview">
                <li><a href="pages/examples/invoice.html"><i class="fa fa-circle-o"></i>백주부 요리</a></li>
                <li><a href="pages/examples/profile.html"><i class="fa fa-circle-o"></i>엄마 요리</a></li>
                <li><a href="pages/examples/login.html"><i class="fa fa-circle-o"></i>간편 요리</a></li>
              </ul>
             </li>
             <li class="treeview">
              <a href="#">
                <i class="fa fa-folder"></i> <span>Examples</span>
               
              </a>
              <ul class="treeview">
                <li><a href="pages/examples/invoice.html"><i class="fa fa-circle-o"></i> 국어</a></li>
                <li><a href="pages/examples/profile.html"><i class="fa fa-circle-o"></i> 영어</a></li>
                <li><a href="pages/examples/login.html"><i class="fa fa-circle-o"></i>   일본어</a></li>
              </ul>
             </li>
             <li class="treeview">
              <a href="#">
                <i class="fa fa-folder"></i> <span>Examples</span>
               
              </a>
              <ul class="treeview">
                <li><a href="pages/examples/invoice.html"><i class="fa fa-circle-o"></i> aaa</a></li>
                <li><a href="pages/examples/profile.html"><i class="fa fa-circle-o"></i> Profile</a></li>
                <li><a href="pages/examples/login.html"><i class="fa fa-circle-o"></i> Login</a></li>
              </ul>
             </li>
                   
          </ul>
        </c:if> 
        </section>
        <!-- /.sidebar -->
      </aside>  
</div> 
<script src="/edutube/resources/JS/classie.js"></script>
<script src="/edutube/resources/JS/selectFx.js"></script>
<script>
		(function() {
			[].slice.call( document.querySelectorAll( 'select.cs-select' ) ).forEach( function(el) {	
				new SelectFx(el);
			} );
		})();
</script>
