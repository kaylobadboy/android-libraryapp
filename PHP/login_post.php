<?php 

  $username = $_POST['username'];
  $password = $_POST['password'];
  $u = "root";
  $host     = "localhost";
  $conn     = mysqli_connect( $host, $u);
  

  if ( !$conn )
    die( 'Could not connect to MySQL: ' . mysqli_error( ) );
  else {
    mysqli_select_db( $conn, "library" );

	$sql = "select * from user where user.name = '$username' and password='$password'";
	$result = mysqli_query($conn,$sql);
	if (mysqli_num_rows($result) >0)
	{
		echo "Successfully logged in";
	}		
	else{
	
	

		echo "Incorrect Login";

	
	}
	
	
    mysqli_close( $conn );
  }




 
 
 ?>