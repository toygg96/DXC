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
		<s:url var="localeEN" namespace="/" action="localeAdmin">
			<s:param name="request_locale">en</s:param>
		</s:url>
		<s:a href="%{localeEN}">English</s:a>
		|
		<s:url var="localeDE" namespace="/" action="localeAdmin">
			<s:param name="request_locale">de</s:param>
		</s:url>
		<s:a href="%{localeDE}">German</s:a>
	</div>
	<h1>
		<s:text name="label.adminpageheader" />
	</h1>
	<br /> Hey <s:property value="User.getRealName" /> !
	<s:text name="label.adminpagemsg" /> :
	<br />
	<a href="/DXC/login.action"><button><s:text name="label.back" /></button></a>
</body>
</html>