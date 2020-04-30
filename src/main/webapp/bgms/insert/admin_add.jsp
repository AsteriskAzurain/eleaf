<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>权限管理 - eLeaf后台管理系统</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="robots" content="all,follow">
<script type="text/javascript" src="../vendor/jquery/jquery.min.js"></script>
<script type="text/javascript" src="../vendor/popper.js/umd/popper.min.js"></script>
<script type="text/javascript" src="../vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../vendor/jquery.cookie/jquery.cookie.js"></script>
<script type="text/javascript" src="../vendor/chart.js/Chart.min.js"></script>
<script type="text/javascript" src="../js/js.cookie.min.js"></script>
<script type="text/javascript" src="../js/front.js"></script>
<script type="text/javascript" src="../../resources/js/rendering.js"></script>
<script type="text/javascript" src="../../resources/js/ajax-jquery.js"></script>
<script type="text/javascript" src="../../router/view-mapping.js"></script>
<script type="text/javascript" src="../../server/api.js"></script>
<script type="text/javascript" src="../../resources/js/encryption-md5.js"></script>
<link rel="stylesheet" type="text/css" href="../../resources/css/global.css" />
<!-- Bootstrap CSS-->
<link rel="stylesheet" href="../vendor/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome CSS-->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<!-- Google fonts - Popppins for copy-->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,800">
<!-- orion icons-->
<link rel="stylesheet" href="../css/orionicons.css">
<!-- theme stylesheet-->
<link rel="stylesheet" href="../css/style.default.css" id="theme-stylesheet">
<!-- Custom stylesheet - for your changes-->
<link rel="stylesheet" href="../css/custom.css">
<!-- Favicon-->
<link rel="shortcut icon" href="../img/favicon.png?3">
<!-- Tweaks for older IEs-->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
</head>

<body>
	<!-- 模态框： 修改密码 -->
	<script type="text/javascript" src="../js/modal.js"></script>
	
	<!-- 页眉： 顶部导航栏 -->
	<header class="header">
		<script type="text/javascript" src="../js/navigation_child.js"></script>
	</header>
	
	<!-- 正文 -->
	<div class="d-flex align-items-stretch">
		<!-- 左侧导航栏 -->
		<div id="sidebar" class="sidebar py-3">
			<script type="text/javascript" src="../js/sidebar/head_1.js"></script>
			<script type="text/javascript" src="../js/sidebar/goods_2.js"></script>
			<script type="text/javascript" src="../js/sidebar/orders_3.js"></script>
			<script type="text/javascript" src="../js/sidebar/user_4.js"></script>
			
			<!-- 权限管理（展开） -->
				<li class='sidebar-list-item'>
					<a href='admin.jsp' class='sidebar-link text-muted active'> 
						<i class='o-wireframe-1 mr-3 text-gray'></i> 
						<span>权限管理</span>
					</a>
				</li>
			</ul>

		</div>
		
		<!-- 右侧正文 -->
		<div class="page-holder w-100 d-flex flex-wrap">
			<div class="container-fluid px-xl-5">
				<section class="py-5">
					<div class="col">
						<div class="row-lg-6 mb-4">
							<div class="card">
								<div class="card-header">
									<h6 class="text-uppercase mb-0">权限管理</h6>
								</div>
								<div class="card-body">
									<fieldset>
										<legend>新建管理员账号</legend>
										<div class="form-group mt-4">
											<label for="InputUsername">用户名</label>
											<input id="InputUsername" type="text" placeholder="输入用户名" class="form-control">
										</div>
										<div class="form-group mt-4">
											<label for="InputPassword">密码</label>
											<input id="InputPassword" type="text" placeholder="输入密码" class="form-control">
										</div>
										<div class="form-group mt-4">
											<label for="customRadio">权限</label>
											<div class="custom-control custom-radio">
												<input id="customRadio1" type="radio" name="customRadio" class="custom-control-input" value="1" checked="checked">
												<label for="customRadio1" class="custom-control-label">普通管理员</label>
											</div>
											<div class="custom-control custom-radio">
												<input id="customRadio0" type="radio" name="customRadio" class="custom-control-input" value="0">
												<label for="customRadio0" class="custom-control-label">超级管理员</label>
											</div>
										</div>
										<button type="submit" class="btn btn-primary mt-4 add">提交</button>
									</fieldset>
								</div>
							</div>
						</div>

					</div>
				</section>
			</div>
			<!-- 页脚 -->
			<script type="text/javascript" src="../js/footer.js"></script>
		</div>
	</div>
	
	<!-- JavaScript files-->

	<script type="text/javascript">
		$('button.add').click(function() {
			var resolve = function(dataset) {
				if (dataset == null || dataset == "") {
					tip('用户名已被占用！', 'error');
				} else {
					tip('注册成功，3秒后<a href="../root.jsp">返回</a>...', 'success', 3000);
					window.setTimeout(function() {
						window.location.href = '../admin.jsp';
					}, 3000);
				}
			};
			var params = {username: $('input#InputUsername').val(), password: b64_md5($('input#InputPassword').val())}
			adminAdd(params, $.noop, resolve);
		});
	</script>
</body>
</html>