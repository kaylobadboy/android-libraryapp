<?php 

   $isbn = $_POST['ISBN'];


  
  $u = "root";
  $host     = "localhost";
  $conn     = mysqli_connect( $host, $u);
  

  if ( !$conn )
    die( 'Could not connect to MySQL: ' . mysqli_error( ) );
  else {
    mysqli_select_db( $conn, "library" );
	
	$sql = "select * from books where books.ISBN like '%$isbn%'";
	$result = mysqli_query($conn,$sql);
	if (mysqli_num_rows($result) <1)
	{
		echo "No Book found";
	}		
	else{
		
		
	while ($row = mysqli_fetch_row($result)) {
	
				echo $row[0].",";
				echo $row[1].",";
				
			
				
				
				echo $row[2];

	
	
	
	
	}
	
	
    mysqli_close( $conn );
  }


  

 
 
 ?>