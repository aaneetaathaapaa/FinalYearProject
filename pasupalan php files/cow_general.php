<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

if (isset($_POST['tagid']) && isset($_POST['herdid']) && isset($_POST['farmerid'])&& isset($_POST['weight'])&& isset($_POST['age'])&& isset($_POST['birthInfo'])) 
{
    
    $tagid = $_POST['tagid'];
    $herdid = $_POST['herdid'];
    $farmerid = $_POST['farmerid'];
    $weight = $_POST['weight'];
    $age = $_POST['age'];
    $birthInfo= $_POST['birthInfo'];

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();
    
    $result = mysql_query("INSERT INTO cow_general(tag_id, herd_id, farmer_id, weight, age, birth_info) VALUES('$tagid', '$herdid',                  '$farmerid','$weight','$age', '$birthInfo')");
    
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