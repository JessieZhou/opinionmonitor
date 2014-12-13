<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>jQuery UI Datepicker - Default functionality</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>
<script>
	$(function() {
		$("#start_date").datepicker();
	});
</script>
<script>
	$(function() {
		$("#end_date").datepicker();
	});
</script>
</head>
<body>

	<p>
		Date: <input type="text" id="datepicker">
	</p>

	<input id="start_date" type="text" name="start_date">
	<input id="end_date" type="text" name="end_date">
	
</body>
</html>