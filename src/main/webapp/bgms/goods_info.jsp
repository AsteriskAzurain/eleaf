<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>商品管理</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="robots" content="all,follow">
<!-- Bootstrap CSS-->
<link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome CSS-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<!-- Google fonts - Popppins for copy-->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,800">
<!-- orion icons-->
<link rel="stylesheet" href="css/orionicons.css">
<!-- theme stylesheet-->
<link rel="stylesheet" href="css/style.default.css"
	id="theme-stylesheet">
<!-- Custom stylesheet - for your changes-->
<link rel="stylesheet" href="css/custom.css">
<!-- Favicon-->
<link rel="shortcut icon" href="img/favicon.png?3">
<!-- Tweaks for older IEs-->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->

<link rel="stylesheet" href="css/btn_puton.css">

<script type="text/javascript" src="../resources/js/rendering.js"></script>
<script type="text/javascript" src="../resources/js/ajax-jquery.js"></script>
<script type="text/javascript" src="../router/view-mapping.js"></script>
<script type="text/javascript" src="../server/api.js"></script>
<link rel="stylesheet" type="text/css" href="../resources/css/global.css" />
</head>
<body>
	<!-- Modal-->
	<script type="text/javascript" src="js/modal.js"></script>

	<header class="header">
		<script type="text/javascript" src="js/navigation.js"></script>
	</header>
	<div class="d-flex align-items-stretch">
		<div id="sidebar" class="sidebar py-3">
			<script type="text/javascript" src="js/sidebar/head_1.js"></script>

			<li class='sidebar-list-item'>
				<a href='#' class='sidebar-link text-muted active' data-toggle='collapse'
					data-target='#goods' aria-expanded="true" aria-controls='goods'>
				<i class='o-sales-up-1 mr-3 text-gray'></i> 
				<span>商品管理</span>
				</a>
				<div id='goods' class='collapse show'>
					<ul
						class='sidebar-menu list-unstyled border-left border-primary border-thick'>
						<li class='sidebar-list-item'><a href='goods_type.jsp'
							class='sidebar-link text-muted pl-lg-5'>商品类别</a></li>
						<li class='sidebar-list-item'><a href='goods_info.jsp'
							class='sidebar-link text-muted pl-lg-5 active'>商品详情</a></li>
					</ul>
				</div>
			</li>

			<script type="text/javascript" src="js/sidebar/orders_3.js"></script>
			<script type="text/javascript" src="js/sidebar/user_4.js"></script>
			<script type="text/javascript" src="js/sidebar/admin_5.js"></script>
		</div>
		<div class="page-holder w-100 d-flex flex-wrap">
			<div class="container-fluid px-xl-5">
				<section class="py-5">
					<div class="col">
						<form class="form-inline">
							<button type="button" class="btn btn-primary mb-2"
								style="margin-right: 20px" onclick="window.location.href='insert/goods_info_add.jsp'">增加</button>
							<div class="input-group mb-2">
								<input type="text" placeholder="请输入搜索内容"
									aria-describedby="button-addon2" class="form-control">
								<div class="input-group-append">
									<button id="button-addon2" type="button"
										class="btn btn-primary">搜索</button>
								</div>
							</div>
						</form>

						<div class="row-lg-6 mb-4">
							<div class="card">
								<div class="card-header">
									<h6 class="text-uppercase mb-0">商品管理</h6>
									<div id="btn_p"></div>
								</div>
								<div class="card-body" style="overflow: scroll;">
									<table class="table table-striped table-hover card-text"
										style="min-width: 1500px;">
										<thead>
											<tr>
												<th>#</th>
												<th>类别</th>
												<th>商品名</th>
												<th>是否上架</th>
												<th>单价</th>
												<th>成本</th>
												<th>浏览量</th>
												<th>购买量</th>
												<th>operation</th>
											</tr>
										</thead>
										<tbody class="dataview" dataview="ginfo">
											<tr  class="dv-unit">
												<th class="dv-elem-index" scope="row">1</th>
												<td class="dv-elem-expose" dv-elem="type_id"></td>
												<td class="dv-elem-expose" dv-elem="goods_name"></td>
												<td class="dv-elem-map putonlab" dv-elem="hiddenFlag" >
													<div class="btn-group-toggle" data-toggle="buttons">
														<label class="btn btn-sm btn-puton active"> 
														<input type="checkbox" checked> 上架
														</label>
													</div>
												</td>
												<td class="dv-elem-expose" dv-elem="goods_price"></td>
												<td class="dv-elem-expose" dv-elem="goods_cost"></td>
												<td class="dv-elem-expose" dv-elem="goods_view_count"></td>
												<td class="dv-elem-expose" dv-elem="goods_buy_count"></td>
												<td class="dv-elem-map" dv-elem="data_mod">
													<div class="inline">
														<button type="button" class="btn btn-sm btn-secondary btn_mod" dv-elem="update">编辑</button>
														<button type="button" class="btn btn-sm btn-secondary btn_mod" dv-elem="delete">删除</button>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>

					</div>
				</section>
			</div>
			<!-- footer begin -->
			<script type="text/javascript" src="js/footer.js"></script>
			<!-- footer end -->
		</div>
	</div>
	<!-- JavaScript files-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/popper.js/umd/popper.min.js">
		
	</script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="vendor/jquery.cookie/jquery.cookie.js">
		
	</script>
	<script src="vendor/chart.js/Chart.min.js"></script>
	<script src="js/js.cookie.min.js"></script>
	<script src="js/front.js"></script>
		<script type="text/javascript">
		$(document).ready(function() {
			//alert("ready")
			var resolve = function(dataset) {
				let map_resolve = function(data) {
					var mappar=$(document).find('.dv-elem-map')
					if(mappar.attr('dv-elem')=="hiddenFlag"){
						var puton=mappar.find('label')
						console.info(data.id+data.hiddenFlag)
						$(puton).attr("title",data.id);
						/* 未选中：flag=0，active */
						if(data.hiddenFlag==0){
							$(puton).addClass("active");
						}else{
							$(puton).removeClass("active");
						}
					} 
				};
				dv_rendering(dataset, $('.dataview[dataview="ginfo"]'), map_resolve, $.noop);
			};
			getGInfoList(null, $.noop, resolve);
		});
		$("button[dv-elem='update']").click(function() {
			id=$(this).attr("title");
			console.info(id)
			window.location.href="/eleaf/goods/info/loadRecord?id="+id
		})
		$("button[dv-elem='delete']").click(function() {
			id=$(this).attr("title");
			if (confirm("确定要删除吗？")) {
				alert("已删除。")
				//do delete /goods/info/deletehide?id=
				window.location.href="/eleaf/goods/info/deletehide?id="+id
				//window.location.href="goods_info.jsp"
			} else {
				alert("已取消。")
			}
		})
		$(".putonlab").click(function(){
			id=$(this).find('label').attr("id");
			//alert(id)
			//flag=$(this).attr("title");
			window.location.href="/eleaf/goods/info/puton?id="+id

		})
	</script>

</body>
</html>
ml>
