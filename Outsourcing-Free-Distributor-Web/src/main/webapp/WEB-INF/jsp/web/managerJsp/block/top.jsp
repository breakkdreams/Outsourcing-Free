<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="row border-bottom">
    <nav class="navbar navbar-static-top  " role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary "><i class="fa fa-bars"></i> </a>
            <%--<form role="search" class="navbar-form-custom" action="search_results.html">--%>
                <%--<div class="form-group">--%>
                    <%--<input type="text" placeholder="Search for something..." class="form-control" name="top-search" id="top-search">--%>
                <%--</div>--%>
            <%--</form>--%>
        </div>
        <ul class="nav navbar-top-links navbar-right">
            <li>
                <%-- <span class="m-r-sm text-muted welcome-message">Welcome.${account.username }</span> --%>
            </li>
            <li>
                <a onclick="logOut()">
                    <i class="fa fa-sign-out"></i>退出
                </a>
            </li>
        </ul>

    </nav>
</div>



	<script type="text/javascript" src="base/baseUrl.js"></script>
	
	<script type="text/javascript">
		function logOut() {
			$.ajax({
				url : logoffUrl,
				type : "post",
				dataType : "json",
				async : false,
				success : function(data) {
					if (data.Code == 200) {
						window.location.href = userLoginPage;
					}
				}
			})
		}
	</script>
</body>
</html>