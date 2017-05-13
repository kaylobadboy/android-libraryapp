<?php 

  $username = $_POST['username'];
  $ISBN = $_POST['ISBN'];
  $quantity = $_POST['quantity'];
  
  $u = "root";
  $host     = "localhost";
  $conn     = mysqli_connect( $host, $u);
  

  if ( !$conn )
    die( 'Could not connect to MySQL: ' . mysqli_error( ) );
  else {
    mysqli_select_db( $conn, "library" );
	
	$sql = "select * from purchase where purchase.customer = '$username' and purchase.ISBN = '$ISBN'";
	$result = mysqli_query($conn,$sql);
	if (mysqli_num_rows($result) >0)
	{
		$sql = "UPDATE purchase SET purchase.quantity = purchase.quantity + '$quantity' WHERE purchase.customer = '$username' and purchase.ISBN = '$ISBN'";
		$result = mysqli_query($conn,$sql);
		echo "Already been purchased. Successfully Added Amount";
	}		
	else{
	
	
	
	$sql = "insert into purchase(CUSTOMER,ISBN, Quantity) VALUES ('$username','$ISBN','$quantity')";
	
	

    if ( $result = mysqli_query( $conn, $sql  ) ) {
      echo "Successfully Added Purchase";
    }	
	else{
		echo "Failed to add purchase";
	}
	
	
	
	
	}
	
	
    mysqli_close( $conn );
  }




 
 
 ?>