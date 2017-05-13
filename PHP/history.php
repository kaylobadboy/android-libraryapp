<?php 

  $username = $_POST['username'];


  $u = "root";
  $host     = "localhost";
  $conn     = mysqli_connect( $host, $u);
  

  if ( !$conn )
    die( 'Could not connect to MySQL: ' . mysqli_error( ) );
  else {
    mysqli_select_db( $conn, "library" );
	
	$sql = "select * from purchase where purchase.customer = '$username'";
	$result = mysqli_query($conn,$sql);
	if (mysqli_num_rows($result) <1)
	{
		echo "No Purchases";
	}		
	else{
		
		
	while ($row = mysqli_fetch_row($result)) {
			//echo '<tr>';
				
				echo $row[1].",";
				
				$sql2 = "select * from books where books.ISBN = '$row[1]'";
				$result2 = mysqli_query($conn,$sql2);
						while ($row2 = mysqli_fetch_row($result2)) {
									echo $row2[1].",";
									echo $row2[2].",";
									
								}
				
				
				
				echo $row[2]."|";

	
	
	
	
	}
	
	
    mysqli_close( $conn );
  }




 
 
 ?>