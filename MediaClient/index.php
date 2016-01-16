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
  } else {
	  $current_dir = realpath($current_dir);
  }
  
  	
  
  function echoTableItem($type, $file, $item, $dir) {
	
	$picture_extensions = readExtensionFile("pics.txt");
	$movie_extensions = readExtensionFile("movies.txt");
	$music_extensions = readExtensionFile("music.txt");
	$game_extensions = readExtensionFile("games.txt");
	$doc_extensions = readExtensionFile("docs.txt");
	
	$type = "";
	
			$height = 0;
			if(is_dir($file)) {
				$type = "folder";
			} else {
				$found = false;
				foreach($picture_extensions as $ext) {
					
					if(endsWith($item, $ext)) {
						
						$type = "picture"; 
						$found = true;
					}
				}
				
				if(!$found) {
					foreach($movie_extensions as $ext) {
						if(endsWith($item, $ext)) {
								$type = "video"; 
								$found = true;
						}
					}
				}
				
				if(!$found){
					foreach($music_extensions as $ext) {
						if(endsWith($item, $ext)) {
								$type = "music"; 
								$found = true;
						}
					}
				}
				
				if(!$found) {
					foreach($game_extensions as $ext) {
						if(endsWith($item, $ext)) {
								$type = "zipfile"; 
								$found = true;
						}
					}
				}
				
				if(!$found) {
					foreach($doc_extensions as $ext) {
						if(endsWith($item, $ext)) {
							$type = "text";
							$found = true;
						}
					}
				}
				
				if(!$found) {
					$type = "unknown";
					$found = true;
				}
				
				
				}
			

			list($width, $height, $ttype, $attr) = getimagesize("res/" . $type . ".png");
			
			echo "<td style=\"word-break:break-all;\">";
			if(!is_dir($file)) {
				echo "<a href=\"" . $type . ".php?file=" . $file . "\">";
			} else {
				echo "<a href=\"index.php?dir=" . $dir . "/" . $item . "\">";
			}
			
			echo "<div align=\"center\"><img src=\"res/" . $type . ".png\" alt=\"" . $item . "\" width=\"". $width/2 . "\"></div>";
			echo "<div align=\"center\"><p style=\"color:#000;\">" . $item ."</p></div>";
			echo "</a>";
			echo "</td>";
}
  
  
  //http://theoryapp.com/string-startswith-and-endswith-in-php/
  function str_starts_with($haystack, $needle) {
    return substr_compare($haystack, $needle, 0, strlen($needle)) === 0;
}
function str_ends_with($haystack, $needle) {
    return substr_compare($haystack, $needle, -strlen($needle)) === 0;
}
  
  
  
  	function endsWith($string, $ending) {
    if(str_ends_with(strtolower($string), strtolower($ending))){
		return true;
	} else { 
		return false;
	}
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

	function readExtensionFile($file) {
		$lines = file($file);
		$array = array("None");
		$count = 0;
		foreach($lines as $line_num => $line) {
			$array[$count] = strtolower(trim($line));
			$count++;
		}
		return $array;
	}
  

	
	
	echo "<div align=\"center\">";
	echo "<div style=\"height:60vh;width:100vh;border:1px solid #ccc;overflow:auto;\">";

  
	echo "<table border=1>";
	echo "<tr>";
	
	

  $dir = $current_dir;
  $rowcount = 0;
  $rowmax = 5;
  $media = scandir(realpath($dir)) or die("Unable to read requested directory!");
  foreach($media as &$item) {
	  if($item != ".") {
		 $file = $dir . "/" . $item;
		 
		if($rowcount < $rowmax) {
			echoTableItem($type, $file, $item, $dir);
			$rowcount++;
			} else {
					echo "</tr>\n<tr>";
					echoTableItem($type, $file, $item, $dir);
					$rowcount = 0;
			}
		}
	}
	echo "</tr>";
	echo "</table>";
	echo "</div>";
	echo "</div>";
	
  ?>
  

</body>
</html> 
