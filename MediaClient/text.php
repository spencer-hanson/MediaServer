<?php
$file = $_GET["file"];
if(!isset($_GET["file"])) {
	$file = "no";
}
echo file_get_contents($file);
?>