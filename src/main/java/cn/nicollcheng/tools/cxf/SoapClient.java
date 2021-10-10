package cn.nicollcheng.tools.cxf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author <a href="mailto:email.nicollcheng@gmail.com">nicollcheng</a>
 * <b>Creation Time:</b> 2021-09-09 21:04.
 * desc WebService SOAP 客户端
 */
public class SoapClient {
    /**
     * @param wsdlUrl wsdl地址
     * @param soapXml soap xml数据
     */
    public static String doSendWebService(String wsdlUrl, String soapXml) throws IOException {
        // 第一步：创建服务地址，不是WSDL地址
        URL url = new URL(wsdlUrl);
        // 2：打开到服务地址的一个连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 3：设置连接参数
        //3.1设置发送方式：POST必须大写
        connection.setRequestMethod("POST");
        // 3.2设置数据格式：Content-type
        connection.setRequestProperty("content-type", "text/xml;charset=utf-8");
        // 3.3设置输入输出，新创建的connection默认是没有读写权限的，
        connection.setDoInput(true);
        connection.setDoOutput(true);
        StringBuilder sb = new StringBuilder();
        try (OutputStream os = connection.getOutputStream()) {
            os.write(soapXml.getBytes());
            // 5：接收服务端的响应
            int responseCode = connection.getResponseCode();
            if (200 == responseCode) {//表示服务端响应成功
                try (InputStream is = connection.getInputStream()) {
                    try (InputStreamReader isr = new InputStreamReader(is)) {
                        try (BufferedReader br = new BufferedReader(isr)) {
                            String temp;
                            while (null != (temp = br.readLine())) {
                                sb.append(temp);
                            }
                        }
                    }
                }
            }
        }
        return sb.toString();
    }
}
