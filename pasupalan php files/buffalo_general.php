<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();
/*
$tagid1=77;
$herdid1 = 77;
$farmerid1 = 77;
$weight1=77;

$age1 = 77;
*/


if (isset($_POST['tagid1']) && isset($_POST['herdid1']) && isset($_POST['farmerid1'])&& isset($_POST['weight1'])&& isset($_POST['age1'])&& isset($_POST['birthInfo1'])) 
{
    
    $tagid1 = $_POST['tagid1'];
    $herdid1 = $_POST['herdid1'];
    $farmerid1= $_POST['farmerid1'];
    $weight1 = $_POST['weight1'];
    $age1 = $_POST['age1'];
    $birthInfo1= $_POST['birthInfo1'];


    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();
    
    $result = mysql_query("INSERT INTO buffalo_general (tag_Id, herd_Id, farmer_Id, Weight, Age, Birth_info) VALUES('$tagid1', '$herdid1',                  '$farmerid1','$weight1','$age1', '$birthInfo1')");
    
    // check if row inserted or not
        if ($result) {
        // successfully inserted into database
            $response["success"] = 1;
            $response["message"] = "Record successfully inserted";
        // echoing JSON response
        echo json_encode($response);
        
         //array_push($response);
                    } 
        else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        // echoing JSON response
        echo json_encode($response);
            }
}

else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}



?>