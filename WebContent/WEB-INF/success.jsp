<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Successful Login Page</title>
<link rel="stylesheet" href="css/style.css" media="screen"
	type="text/css" />
</head>
<body>
	<div id="locale" style="position: absolute; top: 10px; right: 10px;">
		<s:url var="localeEN" namespace="/" action="locale">
			<s:param name="request_locale">en</s:param>
		</s:url>
		<s:a href="%{localeEN}">English</s:a> |
		<s:url var="localeDE" namespace="/" action="locale">
			<s:param name="request_locale">de</s:param>
		</s:url>
		<s:a href="%{localeDE}">German</s:a>
	</div>
	<h1>
		<s:text name = "label.welcome" />
		<s:property value="User.getRealName" />
	</h1>
	<s:if test="User.getUserRole.equals('manager')">
		<s:a action="admin"><s:text name = "label.adminlinkmsg" /></s:a>
	</s:if>
	<br /> 
	<s:text name = "label.username" />:
	<s:property value="User.getUsername" />
	<br /> 
	<s:text name = "label.role" />:
	<s:property value="User.getUserRole" />
	<br />
	<s:form action="logout">
		<s:submit key="label.logout" />
	</s:form>
</body>
</html>