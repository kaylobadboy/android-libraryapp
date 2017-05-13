<?php 

 if (isset($_POST["keywords"]))
  {
  $keys = $_POST['keywords'];
  $u = "root";
  $host     = "localhost";
  $conn     = mysqli_connect( $host, $u);
  


  if ( !$conn )
    die( 'Could not connect to MySQL: ' . mysqli_error( ) );
  else {
    mysqli_select_db( $conn, "library" );
	$token = strtok($keys, " ");
	$sql = "select * from books where books.TITLE like '%$token%' or books.ISBN like '%$token%' ";
	$token = strtok(" ");
	while ($token !== false)
	{	
		
		$sql.= "OR books.TITLE like '%$token%' or books.ISBN like '%$token%' ";
		$token = strtok(" ");
		
	}
		$sql.= "OR books.TITLE like '234jsdfk3A'";
		
	
	
	
	//echo $sql;
	
	$result = mysqli_query($conn,$sql);
	//echo (mysqli_num_rows($result));
	if (mysqli_num_rows($result) == 0)
	{
		echo "No Results Found";
		
	}		
	else{
		$i =0;
		while ($row = mysqli_fetch_row($result)) {
			//echo '<tr>';
		
				echo $row[0].",";
				echo $row[1].",";
				echo $row[2]."|"; //$row[1] 
				//$i++;
			//echo '</tr>';
		}
	
	
	}
	
	
    mysqli_close( $conn );
  }
  }




 
 
 ?>