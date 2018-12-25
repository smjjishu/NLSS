package com.smj.comm.tools;


import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpProxy {
    private static class SingletonHolder {
        static final HttpProxy instance = new HttpProxy();
    }

    public static HttpProxy getInstance() {
        return SingletonHolder.instance;
    }

    private static CloseableHttpClient httpClient;

    private static final String CONTENT_TYPE_JSON = "application/json";

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(800);
        cm.setDefaultMaxPerRoute(400);


        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .build();
        //缓存
        /*
 		CacheConfig cacheConfig = CacheConfig.custom()
	            .setMaxCacheEntries(1000)
	            .setMaxObjectSize(8192)
	            .build();
	    */
        httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(cm)
                .build();
    }

    public static HttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @param requestUrl 请求路径
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] get4Stream(String requestUrl) throws IOException {
        byte[] ret = null;
        HttpGet httpGet = new HttpGet(requestUrl);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //long len = entity.getContentLength();
                ret = EntityUtils.toByteArray(entity);
                EntityUtils.consume(entity);
            }
            return ret;
        } finally {
            response.close();
        }
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @param requestUrl 请求路径
     * @return 字符串
     * @throws Exception
     */
    public static String get4String(String requestUrl) throws IOException {
        String ret = null;
        HttpGet httpGet = new HttpGet(requestUrl);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //long len = entity.getContentLength();
                ret = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            }
            return ret;
        } finally {
            response.close();
        }
    }

    public static void main(String[] args) {
        try {
            get4String("10.50.50.11");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * <B>方法名称：</B>普通请求<BR>
     * <B>概要说明：</B>普通请求<BR>
     *
     * @param requestUrl     请求路径
     * @param requestContent 请求内容
     * @return 返回响应结果
     * @throws IOException
     */
    public static String post(String requestUrl, String requestContent) throws IOException {
        StringEntity requestEntity = new StringEntity(requestContent, Consts.UTF_8);
        return execute(requestUrl, requestEntity, null);
    }

    /**
     * <B>方法名称：</B>json请求<BR>
     * <B>概要说明：</B>json请求<BR>
     *
     * @param requestUrl  请求路径
     * @param jsonContent json内容
     * @return 返回响应结果
     * @throws IOException
     */
    public static String postJson(String requestUrl, String jsonContent) throws IOException {
        StringEntity requestEntity = new StringEntity(jsonContent, Consts.UTF_8);
        requestEntity.setContentEncoding("UTF-8");
        requestEntity.setContentType(CONTENT_TYPE_JSON);
        return execute(requestUrl, requestEntity, null);
    }

    /**
     * json请求，需要head
     *
     * @param requestUrl  请求路径
     * @param jsonContent json内容
     * @return 返回响应结果
     * @throws IOException
     */
    public static String postJson(String requestUrl, String jsonContent, Map<String, String> headMap) throws IOException {
        StringEntity requestEntity = new StringEntity(jsonContent, Consts.UTF_8);
        requestEntity.setContentEncoding("UTF-8");
        requestEntity.setContentType(CONTENT_TYPE_JSON);
        return execute(requestUrl, requestEntity, headMap);
    }

    /**
     * <B>方法名称：</B>模拟表单上传<BR>
     * <B>概要说明：</B>模拟表单上传<BR>
     *
     * @param requestUrl 请求路径
     * @param params     属性参数
     * @return 返回响应结果
     * @throws IOException
     */
    public static String post(String requestUrl, Map<String, String> params) throws IOException {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        EntityBuilder builder = EntityBuilder.create();
        builder.setParameters(nvps);
        HttpEntity httpEntity = builder.build();
        return execute(requestUrl, httpEntity, null);
    }

    /**
     * <B>方法名称：</B>上传文件<BR>
     * <B>概要说明：</B>上传文件<BR>
     *
     * @param requestUrl 请求路径
     * @param localFile  文件位置
     * @param username   用户名
     * @param password   密码
     * @return 响应信息
     * @throws IOException
     */
    public static String upload(String requestUrl, String localFile, String username, String password) throws IOException {
        // HttpPost httpPost = new HttpPost(requestUrl);
        // 把文件转换成流对象FileBody
        FileBody fileBody = new FileBody(new File(localFile));
        StringBody usernameInp = new StringBody(username, ContentType.create("text/plain", Consts.UTF_8));
        StringBody passwordInp = new StringBody(password, ContentType.create("text/plain", Consts.UTF_8));
        HttpEntity httpEntity = MultipartEntityBuilder.create()
                // 相当于<input type="file" name="file"/>
                .addPart("file", fileBody)
                // 相当于<input type="text" name="userName" value=userName>
                .addPart("username", usernameInp)
                .addPart("password", passwordInp)
                .build();
        return execute(requestUrl, httpEntity, null);
    }

    /**
     * <B>方法名称：</B>执行请求方法<BR>
     * <B>概要说明：</B>执行请求方法<BR>
     *
     * @param requestUrl 请求路径
     * @param httpEntity 请求实体对象
     * @return 返回响应结果
     * @throws IOException
     */
    private static String execute(String requestUrl, HttpEntity httpEntity, Map<String, String> headMap) throws IOException {
        String result = null;
        HttpPost httpPost = new HttpPost(requestUrl);
        httpPost.setEntity(httpEntity);
        //添加head信息
        if (headMap != null && headMap.size() > 0) {
            Set<String> keySet = headMap.keySet();
            for (String key : keySet) {
                httpPost.addHeader(key, headMap.get(key));
            }
        }
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            try {
                HttpEntity entity = httpResponse.getEntity();
                //System.out.println(httpResponse.getStatusLine().getStatusCode());
                if (httpResponse.getStatusLine().getReasonPhrase().equals("OK") && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
                //进行销毁
                EntityUtils.consume(entity);
            } finally {
                if (null != httpResponse) {
                    httpResponse.close();
                }
            }
        } catch (Exception e) {
            System.out.println("出现异常");
            e.printStackTrace();
        } finally {
            if (null != httpPost) {
                httpPost.releaseConnection();
            }
        }
        return result;
    }




    /**
     * @Description:使用HttpURLConnection发送post请求
     */
    public static String sendFormPost(String urlParam, Map<String, Object> params) {
        StringBuffer resultBuffer = null;
        String charset="UTF-8";
        // 构建请求参数
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> e : params.entrySet()) {
                sbParams.append(e.getKey());
                sbParams.append("=");
                sbParams.append(e.getValue());
                sbParams.append("&");
            }
        }
        HttpURLConnection con = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        // 发送请求
        try {
            URL url = new URL(urlParam);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if (sbParams != null && sbParams.length() > 0) {
                osw = new OutputStreamWriter(con.getOutputStream(), charset);
                osw.write(sbParams.substring(0, sbParams.length() - 1));
                osw.flush();
            }
            // 读取返回内容
            resultBuffer = new StringBuffer();
            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));
            if (contentLength > 0) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
                String temp;
                while ((temp = br.readLine()) != null) {
                    resultBuffer.append(temp);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    osw = null;
                    throw new RuntimeException(e);
                } finally {
                    if (con != null) {
                        con.disconnect();
                        con = null;
                    }
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                    throw new RuntimeException(e);
                } finally {
                    if (con != null) {
                        con.disconnect();
                        con = null;
                    }
                }
            }
        }
        return resultBuffer.toString();
    }


}