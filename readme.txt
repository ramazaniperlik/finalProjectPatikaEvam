---------------------------------------------------------- User Requests and Responses -----------------------------------------------

Request - GET :localhost:8080/v1/users/getUser

Response : 
[
    {
        "id": 1,
        "firstName": "Ramazan1",
        "lastName": "İperlik1",
        "invoiceList": []
    },
    {
        "id": 2,
        "firstName": "Ramazan1",
        "lastName": "İperlik1",
        "invoiceList": []
    },
    {
        "id": 3,
        "firstName": "Ramazan2",
        "lastName": "İperlik2",
        "invoiceList": []
    }
]

**Bu endpoint tüm user listesini getirir.Bu örnekte kullanıcılara henüz fatura atanmadığından invoiceList "[]" dönmektedir.
-------------------------------------------------------------------------




Request - GET : localhost:8080/v1/users/getUser/2
Response : 
{
    "id": 2,
    "firstName": "Ramazan",
    "lastName": "İperlik",
    "invoiceList": []
}

**Bu endpoint userId'ye göre kullanıcı listeler.
-------------------------------------------------------------------------




Request - POST : localhost:8080/v1/users/createUser

{
    "firstName":"Ramazan",
    "lastName":"İperlik"
}

Response : 
{
    "id": 2,
    "firstName": "Ramazan",
    "lastName": "İperlik",
    "invoiceList": null
}

**Bu endpoint yeni user oluşturur.
-------------------------------------------------------------------------




Request - PUT : localhost:8080/v1/users/updateUser/1

{
    "firstName":"Ramazan1",
    "lastName":"İperlik1"
}

Response :
{
    "id": 1,
    "firstName": "Ramazan1",
    "lastName": "İperlik1",
    "invoiceList": []
}

**Bu endpoint userId'ye göre güncelleme işlemi gerçekleştirir.
--------------------------------------------------------------------------


Request - DELETE : localhost:8080/v1/users/deleteUser/1
Response : "1 silindi"

**Bu endpoint userId'ye göre kullanıcı siler.
--------------------------------------------------------------------------



---------------------------------------------------------- Invoice Requests and Responses -----------------------------------------------


Request - POST : localhost:8080/v1/invoices/createInvoice/1

{
    "invoiceAmount":"10"
}

Response : 

{
    "id": 2,
    "invoiceAmount": "10",
    "date": "2022-07-25",
    "statu": "0",
    "user": {
        "id": 1,
        "firstName": "Ramazan",
        "lastName": "İperlik",
        "invoiceList": [
            2
        ]
    }
}

**Bu endpoint user id'ye göre fatura ekleme işlemi gerçekleştirir.Örnekte userId'si 1 olan kullanıcıya, tutarı 10 olan fatura eklenmiştir.
--------------------------------------------------------------------------




Request - GET : localhost:8080/v1/invoices/getInvoices

Response : 

[
    {
        "id": 2,
        "invoiceAmount": "10",
        "date": "2022-07-25",
        "statu": "0",
        "user": {
            "id": 1,
            "firstName": "Ramazan",
            "lastName": "İperlik",
            "invoiceList": [
                2,
                {
                    "id": 3,
                    "invoiceAmount": "20",
                    "date": "2022-07-25",
                    "statu": "0",
                    "user": 1
                }
            ]
        }
    },
    3,
    {
        "id": 5,
        "invoiceAmount": "30",
        "date": "2022-07-25",
        "statu": "0",
        "user": {
            "id": 4,
            "firstName": "Ramazan2",
            "lastName": "İperlik2",
            "invoiceList": [
                5,
                {
                    "id": 6,
                    "invoiceAmount": "350",
                    "date": "2022-07-25",
                    "statu": "0",
                    "user": 4
                }
            ]
        }
    },
    6
]

**Bu endpoint tüm faturaları listeler.
-----------------------------------------------------------------------------




Request - DELETE :  localhost:8080/v1/invoices/deleteInvoice/2

Response : Invioce is deleted!

**Bu endpoint invoice id'ye göre fatura siler.
-----------------------------------------------------------------------------



Request - PUT : localhost:8080/v1/invoices/updateInvoice/2

{
    "invoiceAmount":"10"
}

Response : 

{
    "id": 2,
    "invoiceAmount": "10",
    "date": "2022-07-25",
    "statu": "0",
    "user": {
        "id": 1,
        "firstName": "Ramazan",
        "lastName": "İperlik",
        "invoiceList": [
            2
        ]
    }
}

**Bu endpoint invoice id'ye göre tutar günceller.
-----------------------------------------------------------------------------


Request GET : localhost:8080/v1/invoices/getInvoice/4

Response : 

{
    "id": 4,
    "invoiceAmount": "350",
    "date": "2022-07-25",
    "statu": "0",
    "user": {
        "id": 1,
        "firstName": "Ramazan",
        "lastName": "İperlik",
        "invoiceList": [
            {
                "id": 3,
                "invoiceAmount": "350",
                "date": "2022-07-25",
                "statu": "0",
                "user": 1
            },
            4,
            {
                "id": 5,
                "invoiceAmount": "350",
                "date": "2022-07-25",
                "statu": "0",
                "user": 1
            }
        ]
    }
}

**Bu endpoint faturaya özel sorgu atar.Fatura id'sine göre sorgulama yapar.Örnekte fatura id'si 4 olan kayıt listelenmiştir. 
-------------------------------------------------------------------------------



Request - GET : localhost:8080/v1/invoices/user/1/invoice/2

Response :
"Invoice has been paid!" or "Invoice did not paid yet!"

**Bu endpoint ise fatura ödenmiş - ödenmemiş sorgusu yapar.Örnek kullanıcı no:1 ve fatura no:2 olan faturayı sorgular. 
---------------------------------------------------------------------------------




---------------------------------------------------------- Payment Requests and Responses -----------------------------------------------

Request - GET : localhost:8080/v1/payments/getAllDepts

Response :

[
    {
        "id": 1,
        "totalAmount": 240.0,
        "date": "2022-07-25",
        "user": {
            "id": 1,
            "firstName": "Ramazan2",
            "lastName": "İperlik2",
            "invoiceList": [
                {
                    "id": 2,
                    "invoiceAmount": "240",
                    "date": "2022-07-25",
                    "statu": "1",
                    "user": 1
                }
            ]
        }
    }
]

**Bu endpoint payment tablosundaki tüm kayıtları listeler.
-------------------------------------------------------------------------------




Request - POST : localhost:8080/v1/payments/pay/2

Response : 

"Invoice paid!" or "Invoice already paid." or "Invoice is not found!"


**Bu endpointe atılan istek fatura ödeme işlemidir.Bu örnekte 2 numaralı fatura ödeme işlemi gerçekleştirilmiştir.
Aynı fatura iki kez ödenemez.Fatura bulunmadığında hata mesajı döner.Ödeme işleminden sonra fatura statu'sü 1 olur ve payment tablosunda "totalAmount"
fatura tutarı kadar artış gösterir.
--------------------------------------------------------------------------------
