<?php 
/*
//Sample invocation
$filename = "output/backup" . date("Ymd_G-i-s") . ".zip";
echo "Opening $filename \n";
$zip = zipFromDir(".",$filename);
$zip->close();
*/
function zipFromDir($dir, $filename)
{ 
	$zip = new ZipArchive();

	if ($zip->open($filename, ZIPARCHIVE::CREATE)!==TRUE) {
		exit("cannot open <$filename>\n");
	}
	$pathParts = pathinfo($dir);
	$myPath = ltrim($pathParts['basename'],". ");
	if(strlen($myPath) == 0) $myPath = "thisDir";
	echo "adding $myPath <br/>";

	$zip->addEmptyDir($pathParts['basename']); 
	$nodes = glob($dir . '/*.*'); 
	foreach ($nodes as $node) 
	{ 
		if (is_file($node))
		{ 	
			$nodePathParts = pathinfo($node);
			$fName = $nodePathParts['basename'];
			if($zip->addFile($fName))  echo "$fName noreally <br/>";
			else echo "awwww<br/>";
		} 
	} 
	return $zip;
}


?>