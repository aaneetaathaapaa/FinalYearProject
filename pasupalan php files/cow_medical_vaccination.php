<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

if (isset($_POST['tagid']) && isset($_POST['etDate']) && isset($_POST['etAmount'])&& isset($_POST['etVaccination'])) 
{
    
    $tagid = $_POST['tagid'];
    $etDate = $_POST['etDate'];
    $etAmount = $_POST['etAmount'];
    $etVaccination = $_POST['etVaccination'];
   

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();
    
    $result = mysql_query("INSERT INTO cow_vaccination_record(tag_id, vac_date, vac_amount, vac_name) VALUES('$tagid', '$etDate',                  '$etAmount','$etVaccination')");
    
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