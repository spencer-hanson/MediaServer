<div align="center">
<table><tr><td>
<?php
$file = $_GET["file"];
if(!isset($_GET["file"])) {
	$file = "no";
}
//echo "<img src=\"/media/Pictures/8.png\">";
echo "<img src=\"" . $file . "\">";
?>
</td></tr>
</table></div>
