package org.sidao.common;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

//this class stops reading the request payload twice causing an exception
public class WrappedRequest extends HttpServletRequestWrapper
{
    private String _body;
//    private HttpServletRequest _request;

    public WrappedRequest(HttpServletRequest request) 
    {
        super(request);
//        _request = request;
        _body = "";
        
        try{
        	BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
                _body += line;
        }catch(Exception e){
        	e.printStackTrace();
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException
    {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(_body.getBytes());
        return new ServletInputStream()
        {
            public int read() throws IOException
            {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException
    {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
}