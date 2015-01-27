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



    // get a product from products table
    /*$a = mysql_query("SELECT selling_price FROM cow_animal_sales");
       $b = mysql_query("SELECT soldamount FROM cow_sales_product");
          $c = mysql_query("SELECT vac_amount FROM cow_vaccination_record");
          SELECT (COALESCE(ONE, 0) + COALESCE(TWO, 0)) FROM (TABLE);*/
             
    $result = mysql_query("SELECT COALESCE(cow_animal_sales.selling_price,0)+(COALESCE(cow_sales_product.soldamount,0)+COALESCE(cow_vaccination_record.vac_amount,0) FROM cow_animal_sales join cow_sales_product on cow_animal_sales.tag_id = cow_sales_product.tag_id join cow_vaccination_record on cow_sales_product.tag_id= cow_vaccination_record.tag_id");

echo $result;
    /*if (!empty($result)) {
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
}*/
?>
