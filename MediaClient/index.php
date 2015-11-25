<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Media Server</title>
  <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.1.1/css/bootstrap.min.css">

</head>
<body>
	<div class="container">
    <div align="center"><h1>Media Server</h1></div>
    <hr />
    <div class="page"></div>
	</div>

  

  
  <?php 
  
  $current_dir = $_GET["dir"];
  if(!isset($_GET["dir"])) {
	  $current_dir = "media";
  }
  
  
  function listAllDirs($dir, $path) {
	$path = $dir;
	$items = scandir($dir);
		foreach($items as &$item) {
			if($item != "." && $item != "..") {
				$file = $path . "/" . $item;
				if(is_dir($file)) {
					echo $file . " is a directory!<br />";
					listAllDirs($file, $path);
				} else {
					echo $file . " isn't a directory!<br />";
				}	
			}
		}
  }
  
  echo "<div align=\"center\">";
  echo "<div style=\"height:60vh;width:100vh;border:1px solid #ccc;overflow:auto;\">";

  
  echo "<table>";
  echo "<tr>";

  
  $dir = $current_dir;
  $rowcount = 0;
  $rowmax = 5;
  $colcount = 0;
  $media = scandir($dir);
  
  foreach($media as &$item) {
	  if($item != ".." && $item != ".") {
		 $file = $dir . "/" . $item;
		 
		if($rowcount < $rowmax) {
			echo "<td>";
			$image = "";
			$height = 0;
			if(is_dir($file)) {
				$image = "folder.png";
				
			} else {
				
				$image = "text.png";
				
			}
			list($width, $height, $type, $attr) = getimagesize("res/" . $image);
			echo "<a href=\"index.php?dir=" . $dir . "/" . $item . "\">";
			echo "<div align=\"center\"><p style=\"position:relative;top:" . ($height/2 - 20) .";color:#000;\">" . $item ."</p></div>";
			echo "<img src=\"res/" . $image . "\" alt=\"" . $item . "\" width=\"". $width/2 . "\">";
			echo "</a>";
			echo "</td>";
			
			$rowcount++;
		} else {
				echo "</tr><tr>";
				$rowcount = 0;
				$colcount++;
		}
	}
}
	echo "</table>";
  //listAllDirs($dir, "");
	echo "</div>";
	echo "</div>";
	
  ?>
  

</body>
</html> 