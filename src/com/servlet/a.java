package com.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class a  extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Configuration config=new Configuration();
		FileSystem fs = null; 	     
		String filename="hdfs://192.168.96.128:9000/demoDir/QQͼƬ20170410172123.png";

	  FSDataInputStream in=null;
	  try {
		fs = FileSystem.get(URI.create(filename),config);
		in=fs.open(new Path(filename));
		  final long fileLen = fs.getFileStatus(new Path(filename)).getLen();     
		 
		   resp.setHeader("Content-type"," image/png");
		   OutputStream out=resp.getOutputStream();    
		   resp.setContentType("application/octet-stream");
	         resp.setContentLength((int)fileLen);
	    	 IOUtils.copyBytes(in, out, fileLen, false);
	    	 in.close();
		        in = null;
		        out.close();
		        out = null;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
