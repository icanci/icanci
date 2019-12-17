<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="ic"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>小璨小璨,请管理员不要删库!!!</title>
		<link rel="shortcut icon" href="/viewOfAdmins/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="/viewOfAdmins/css/base.css" />
		<link rel="stylesheet" type="text/css" href="/viewOfAdmins/css/show.css" />
	</head>
	<body>
	
		<!-- 右中 -->
		<div class="pos-right-center">
			<p></p>
			<form action="/admin/seachPaging" method="post">
				<input type="text" name="seach" id="" value="" placeholder="搜一搜" />
				<input type="submit" value="搜索意见反馈" />
			</form>
		</div>
		
		<table border="1" cellspacing="0" cellpadding="0">
			<caption><img src="/viewOfAdmins/images/logo1.png" >
			<p>小璨<span>温馨(/微笑)</span>提醒,请管理员( <span class="namespan">${sessionScope.ADMIN_IN_SESSION.username}</span> )不要 <span>删库</span> !!! 否则 <span>锤爆</span> 你的狗头 此界面 <span>不接受</span> 吐槽 </p>
			</caption>
			<tr>
				<th>意见主题</th>
				<th>意见内容</th>
				<th>发表时间</th>
				<th><span>请谨慎删除</span></th>
			</tr>
			
			<!-- 开始循环 -->
			<ic:forEach items="${pageResult.listData}" var="fb">
				<tr>
					<td>${fb.feedbackTheme}</td>
					<td>${fb.feedbackContent}</td>
					<td>${fb.outPrint}</td>
					<td><a href="/admin/deleteFeedback?deleteId=${fb.id}">删了删了我怕谁</a></td>
				</tr>
			</ic:forEach>
			<tr>
				<td colspan="4">
					<div class="jump">
						<p>
							<a href="/admin/list?currentPage=${pageResult.beginPage}&pageSize=${PageSize}">首页</a>
							<a href="/admin/list?currentPage=${pageResult.prevPage}&pageSize=${PageSize}">上一页</a>
							<a href="/admin/list?currentPage=${pageResult.nextPage}&pageSize=${PageSize}">下一页</a>
							<a href="/admin/list?currentPage=${pageResult.totalPage}&pageSize=${PageSize}">尾页</a>
							当前是 ${pageResult.currentPage}/${pageResult.totalPage} 页 一共 ${pageResult.totalCount} 条数据   
						</p>
						<form action="/admin/list" method="post">
							跳转到第 &nbsp; <input class="numget" type="number" value="${pageResult.currentPage}" min="1" max="${pageResult.totalPage}"
							 name="currentPage" id="" /> &nbsp;页
							&nbsp;<input type="submit" class="numsub" value="跳转" /> <a href="/admin/Administration" style="color: red ">点此进入用户管理界面</a>
						</form>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
