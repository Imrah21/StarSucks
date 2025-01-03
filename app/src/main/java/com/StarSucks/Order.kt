package com.StarSucks

class Order() {
    lateinit var productName: String
    lateinit var customerName: String
    lateinit var customerCell: String
    lateinit var orderDate: String

    constructor(pName: String) : this() {
        productName = pName
    }

    constructor(pName: String, cName: String, cCell: String,
                oDate: String) : this(pName) {
        customerName = cName
        customerCell = cCell
        orderDate = oDate
    }

    override fun toString(): String {
        return "Customer Name: ${customerName}\n" +
                "Product Name: ${productName}\n" +
                "Order Date: ${orderDate}"
    }
}