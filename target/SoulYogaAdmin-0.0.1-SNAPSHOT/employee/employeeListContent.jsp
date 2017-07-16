<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="table table-striped">
	<thead>
		<tr>
			<th>员工ID</th>
			<th>电话号码</th>
			<th>中文名</th>
			<th>英文名</th>
			<th>性别</th>
			<th>邮箱</th>
			<th>职位</th>
			<th>工作分所</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="employeeViews" var="employeeView">
			<tr>
				<td><s:property value="#employeeView.id" /></td>
				<td><s:property value="#employeeView.phoneNo" /></td>
				<td><s:property value="#employeeView.surname" /><s:property value="#employeeView.name" /></td>
				<td><s:property value="#employeeView.fristName" />·<s:property value="#employeeView.lastName" /></td>
				<td><s:if test="%{#employeeView.gender == 0}">男</s:if> <s:else>女</s:else></td>
				<td><s:property value="#employeeView.mail" /></td>
				<td><s:property value="#employeeView.positionName" /></td>
				<td><s:property value="#employeeView.yogaClubName" /></td>
				<td><a class="btn getDtlBtn" href="./showEmployeeDetail.action?id=<s:property value='#employeeView.id' />">查看 / 修改</a></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
