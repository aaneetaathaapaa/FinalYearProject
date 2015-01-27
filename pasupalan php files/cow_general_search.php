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
    //$tagid = 33 ;

    // get a product from products table
    $result = mysql_query("SELECT *FROM cow_general WHERE tag_id = '$tagid'");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $cow = array();
            $cow["herdid"] = $result["herd_id"];
            $cow["farmerid"] = $result["farmer_id"];
            $cow["weight"] = $result["weight"];
            $cow["age"] = $result["age"];
            $cow["birthinfo"] = $result["birth_info"];
            
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