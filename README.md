### Thực hành Automation Test với Selenium Webdriver
#### 🌻 YÊU CẦU: Viết chương trình kiểm thử tính năng đăng nhập hệ thống với tập dữ liệu đầu vào cho trước

![Eclipse](https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

##### 🔵 _LINK WEB:_ [Automation Exercise](https://automationexercise.com/products)
![web](https://github.com/khang77/ThucHanhAutomatinTestVoiSeleniumWebdriver/assets/92577611/45dca436-c7a7-47f3-885e-2d49836cf3eb)
##### 🔵 _Excel Data:_ [assignment2_data_test.xlsx](https://github.com/khang77/ThucHanhAutomatinTestVoiSeleniumWebdriver/files/12408571/assignment2_data_test.xlsx)
![data_excel](https://github.com/khang77/ThucHanhAutomatinTestVoiSeleniumWebdriver/assets/92577611/e7983d87-039e-4085-8fc3-ef570fc57d2d)

#### 🚩🚩🚩Các bước thực hiện:

![work_flow drawio](https://github.com/khang77/ThucHanhAutomatinTestVoiSeleniumWebdriver/assets/92577611/197432bc-ae48-4d44-9416-5af26a22e24e)

✍️ _1 Đoạn code Sử dụng Data driven đọc data test từ file excel:_  
```java
@DataProvider(name="Excel")
	public Object [][] testDataGenerator() throws Exception
	{
		FileInputStream file = new FileInputStream(PropertiesFileUtils.getProperty("DataTest"));
		
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet loginSheet = workbook.getSheet("Login");
		int numberOfRowData = loginSheet.getPhysicalNumberOfRows();
		
		Object[][] data = new Object[numberOfRowData][2];
		for(int i = 0; i < numberOfRowData; i++ ) {
			XSSFRow row = loginSheet.getRow(i);
			XSSFCell username = row.getCell(0);
			XSSFCell password = row.getCell(1);
			data[i][0] = username.getStringCellValue();
			data[i][1] = password.getStringCellValue();
		}
		workbook.close();
		return data;
	}
```  

💯 _TEST RESULTS REPORT_  

![test_result](https://github.com/khang77/ThucHanhAutomatinTestVoiSeleniumWebdriver/assets/92577611/18039801-25e1-49b5-98a8-34bdb6cf370d)
