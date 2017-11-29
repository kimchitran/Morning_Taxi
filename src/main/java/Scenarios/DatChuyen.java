package Scenarios;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.DieuXe;
import Pages.Login;
import Providers.ConstantsProvider;
import Providers.WebDriverProvider;
import Utils.Utils;

/*
 * Author: HuongNguyen
 * Date: 11/10/2017
 * Scenario: Book driver
	
		Setup:
			1. Dang nhap trang web TaxiMorning		
		TestCase:
			1. Click nut Tao Chuyen Moi
			2. Nhap ten khach hang
			2. Nhap so dien thoai
			3. Chon loai xe
			4. Nhap diem don
			5. Nhap ghi chu
			6. Click Huy chuyen
			7. Verify huy chuyen
			8. Nhap so dien thoai
			9. Chon loai xe
			4. Nhap diem don
			5. Nhap ghi chu
			6. Click Dat chuyen
			13. Verify dat chuyen thanh cong tren man hinh
			14. Verify data duoc luu trong CSDL
		Cleanup:
			1. Xoa du lieu trong DB*/

public class DatChuyen extends WebDriverProvider {
	
	DieuXe dieuXe = new DieuXe(wd);
	Utils utils = new Utils();
	
	@Test
	public void TestRunner() throws Exception{
		Login login = new Login(wd);
		List<HashMap<String, String>> listData = utils.getTestData(ConstantsProvider.pathFile,
				ConstantsProvider.sheetName,ConstantsProvider.tableName);
		for (int i = 0; i < listData.size(); i++) 
		 {
			// Go to website
			GoToURL(ConstantsProvider.URL);
			
			//Login
			login.LoginMorningTaxi(ConstantsProvider.userName, ConstantsProvider.passWord);
			login.VerifyLoginSuccess();
			
			//Tao chuyen moidieu
			dieuXe.DieuXe(listData.get(i).get("TenKhachHang"), listData.get(i).get("SoDienThoai"), 
					listData.get(i).get("LoaiXe"),listData.get(i).get("DiemDon"), listData.get(i).get("GhiChu"));
			//Verify tao chuyen thanh cong
			dieuXe.VerifyCreateTicketSuccess(listData.get(i).get("SoDienThoai"));

			//Verify data in database
			dieuXe.VerifyDataOfTicketInDB(listData.get(i).get("SoDienThoai"));
			
			// Delete data
			dieuXe.DeleteTicket();
			
			utils.writeFile(ConstantsProvider.pathFile, ConstantsProvider.sheetName, ConstantsProvider.result_pass, i+2, 7);
		 }
	}
	

}
