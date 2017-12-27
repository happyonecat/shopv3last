<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>


</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
		d = new dTree('d');
		d.add('01',-1,'系统菜单树');
		d.add('0102','01','分类管理','','','mainFrame');
		d.add('010201','0102','分类管理','${pageContext.request.contextPath}/admincategory','','mainFrame');
		d.add('0104','01','商品管理');
		d.add('010401','0104','商品管理','${pageContext.request.contextPath}/productListServlet','','mainFrame');
		d.add('0105','01','订单管理');
		d.add('010501','0105','未付款的订单','${pageContext.request.contextPath}/listorder?state=0','','mainFrame');
		d.add('010502','0105','已付款的订单','${pageContext.request.contextPath}/listorder?state=1','','mainFrame');
		d.add('010503','0105','未确认收货','${pageContext.request.contextPath}/listorder?state=3','','mainFrame');
		d.add('010504','0105','已完成的订单','${pageContext.request.contextPath}/listorder?state=4','','mainFrame');
		document.write(d);
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
