<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/style.css" media="screen"
	type="text/css" />
</head>
<body>
	<div id="locale" style="position:absolute; top: 10px; right: 10px;">
		<s:url var="localeEN" namespace="/" action="locale">
			<s:param name="request_locale">en</s:param>
		</s:url>
		<s:a href="%{localeEN}">English</s:a> |

		<s:url var="localeDE" namespace="/" action="locale">
			<s:param name="request_locale">de</s:param>
		</s:url>
		<s:a href="%{localeDE}">German</s:a>
	</div>
	
	<s:if test="#session['username']!=null">
		<script type="text/javascript">
			window.location.replace("http://127.0.0.1:8080/DXC/login.action")
		</script>
	</s:if>
	<s:property value="#session['username']" />
	<div class="wrap">
		<s:form action="login">
			<s:textfield name="username" key="label.username" required="required" />
			<s:password name="pass" key="label.password" required="required" />
			<s:submit key="label.submit" />
		</s:form>
	</div>
	<h2 style="text-align: center">${ errorMessage }</h2>
</body>
</html>