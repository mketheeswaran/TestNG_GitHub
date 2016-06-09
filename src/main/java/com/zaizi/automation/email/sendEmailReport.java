package com.zaizi.automation.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.testng.annotations.AfterSuite;

import com.zaizi.automation.alfresco.core.info.TestCaseProperties;

public class sendEmailReport {
	
	@AfterSuite
	public  void aftersuite() throws IOException
	{
		/*AppZip appZip = new AppZip();
		appZip.generateFileList(new File(TestCaseProperties.SOURCE_FOLDER));
		appZip.zipIt(TestCaseProperties.OUTPUT_ZIP_FILE);*/
		
		String dirpath = TestCaseProperties.SOURCE_FOLDER;
		String ZipName = TestCaseProperties.OUTPUT_ZIP_FILE;
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(ZipName));
		zipdirectory(dirpath,zos); 
		zos.close(); 
	
		emailReport a=new emailReport();
		a.sendEmail2();
	}

	
	public static void zipdirectory(String dirpath, ZipOutputStream zos) throws IOException
    {
File f = new File(dirpath);
String[] flist = f.list();
for(int i=0; i<flist.length; i++)
{
   File ff = new File(f,flist[i]);
   if(ff.isDirectory())
   {
     zipdirectory(ff.getPath(),zos);    
     continue;
   }
   String filepath = ff.getPath();
   ZipEntry entries = new ZipEntry(filepath);
   zos.putNextEntry(entries);
   FileInputStream fis = new FileInputStream(ff);
   int buffersize = 1024;
   byte[] buffer = new byte[buffersize];
   int count;
   while((count = fis.read(buffer)) != -1)
   {
zos.write(buffer,0,count);    
   }
           fis.close();
}
    }
}
