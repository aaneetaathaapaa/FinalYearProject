<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

if (isset($_POST['Tagid'])  && isset($_POST['Milkproduced']) && isset($_POST['HomeUse'])&& isset($_POST['Soldamount'])&& isset($_POST['Dailysales'])) 
{
    
    $tagid = $_POST['Tagid'];
    $milk = $_POST['Milkproduced'] ;
    $homeuse = $_POST['HomeUse'];
    $soldamount= $_POST['Soldamount'];
    $dailysale = $_POST['Dailysales'];
   

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();
    
    $result = mysql_query("INSERT INTO cow_sales_product(tag_id, milkproduced, homeuse,  soldamount, dailysale ) VALUES('$tagid', '$milk',                  '$homeuse','$soldamount','$dailysale')");
    
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