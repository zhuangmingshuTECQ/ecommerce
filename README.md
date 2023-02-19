# Ecommerce application (Spring Boot in Kotlin)

## Getting Started
1. Clone the repository from `https://github.com/zhuangmingshuTECQ/ecommerce.git` in github desktop or git command line
2. Open the folder in your favourite IDE
3. Click the `EcommerceApplication.kt` file in the `src/main/kotlin/com/mingshu/ecommerce/EcommerceApplication.kt` path
4. Click the run button

## How to use the application?
This is a backend application to upload CSV and return data by search fields.
You can test out the APIs sending the following requests in Postman
1. upload CSV: (Post) http://localhost:8080/upload
body>form-data>key: "file"
body>form-data>key: select a csv file. a sample "data.csv" is included in the testing folder.
2. find invoices: (Get) http://localhost:8080/transactions?query=536366&page=0&size=30&filter=