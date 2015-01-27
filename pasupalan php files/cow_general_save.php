<?php

/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
  $tagid = $_GET["tagid"];

$herdid = $_GET["herdid1"];
$farmerid = $_GET["farmerid1"];
$weight = $_GET["weight1"];
$age = $_GET["age1"];

 /*   $tagid = 33 ;

$herdid = 45;
$farmerid = 78;
$weight = 98;
$age = 35;*/


    // update value of table
    $result = mysql_query("UPDATE cow_general SET herd_id  = '$herdid', farmer_id = '$farmerid', weight = '$weight', age = '$age' WHERE tag_id = 
    '$tagid'");


    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $cow = array();
            $cow["herdid"] = $result["herd_id"];
            $cow["farmerid"] = $result["farmer_id"];
            $cow["weight"] = $result["weight"];
            $cow["age"] = $result["age"];
           
            
            // success
            $response["success"] = 1;

            // user node
           $response["general"] = array();

            array_push($response["general"], $cow);

            // echoing JSON response
            echo json_encode($response);
   } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No product found";

            // echo no users JSON
            echo json_encode($response);
        }
   

} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}

?>