<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response

// include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();
$response = array();

/*
$tagid =33;
$buyerfamerid=45;
$solddate=67;
$sellingprice=88;
*/

if (isset($_POST['tagid']) && isset($_POST['buyerfamerid']) && isset($_POST['solddate'])&& isset($_POST['sellingprice'])) 
{
    
    $tagid = $_POST['tagid'];
    $buyerfamerid = $_POST['buyerfamerid'];
    $solddate = $_POST['solddate'];
    $sellingprice = $_POST['sellingprice'];
    

    
    
    $result = mysql_query("INSERT INTO cow_animal_sales(tag_id, buyer_farmerid, solddate, selling_price) VALUES('$tagid', '$buyerfamerid',                  '$solddate','$sellingprice')");
    
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