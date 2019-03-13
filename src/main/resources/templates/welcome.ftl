<!DOCTYPE html>

<html lang="en">

<body>
	Date: ${time?date}
	<br>
	Time: ${time?time}
	<br>
	Message: ${message}


	<ul>
		<li>Success , user 10：<a href="user/10">user 10</a></li>
		<li>Error 500, user 0：<a href="user/0">user 0</a></li>
		<li>Error 404, a.txt：<a href="a.txt">a.txt</a></li>
		<li>Slow , User 0：<a href="user/0/slow">slow</a></li>
	</ul>
</body>

</html>