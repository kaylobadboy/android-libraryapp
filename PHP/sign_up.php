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
	
	$sql = "select * from user where user.name = '$username'";
	$result = mysqli_query($conn,$sql);
	if (mysqli_num_rows($result) >0)
	{
		echo "User Already Exists.";
	}		
	else{
	
	
	
	
	
	
	
	
	$sql = "insert into user(name,password,role) VALUES ('$username','$password','user')" ;

    if ( $result = mysqli_query( $conn, $sql  ) ) {
      echo "Successfully Added User";
    }	
	else{
		echo "Failed to add user";
	}
	
	
	
	
	}
	
	
    mysqli_close( $conn );
  }




 
 
 ?>