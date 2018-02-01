package com.slife.httpclient;

import java.io.IOException;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Created by Duyp on 2015年8月10日.
 */

public class HttpUtils {
	Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	private static HttpUtils instance = null;

	// 连接池默认大小200
	private static final int DEFAULT_MAX_CONN_SIZE = 200;
	// 默认路由链路40，同一host最大并发连接数小于该数值
	private static final int DEFAULT_ROUTER_SIZE = 40;
	// timeout for waiting for data or, put differently, a maximum period
	// inactivity between two consecutive data packets 等待数据包返回时间
	private static final int DEFAULT_SOCKET_TIMEOUT = 5000;
	// Returns the timeout in milliseconds used when requesting a connection
	// from the connection manager.等待连接池空闲连接
	private static final int DEFAULT_REQ_TIMEOUT = 5000;
	// Determines the timeout in milliseconds until a connection is
	// established.等待连接建立
	private static final int DEFAULT_CONN_TIMEOUT = 5000;
	// 默认的请求配置
	private static final RequestConfig DEFAULT_REQUEST_CONFIG = RequestConfig.custom()
			.setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).setConnectionRequestTimeout(DEFAULT_REQ_TIMEOUT)
			.setConnectTimeout(DEFAULT_CONN_TIMEOUT).build();
	private int connectionSize = 0;
	private int routerSize = 0;
	// 日志trace开关，记录访问时间以及返回结果
	private boolean isDebug = true;
	private CloseableHttpClient httpClient = null;
	private PoolingHttpClientConnectionManager cm = null;

	private static final int nTries = 999999999;
	private static final int RETRY_WAIT_MS = 30 * 1000;

	private static final int INIT_WAIT_MS = 2 * 1000;

	public static HttpUtils getInstance() {
		if (instance == null) {
			instance = new HttpUtils();
		}
		return instance;
	}

	private HttpUtils() {
		this.cm = new PoolingHttpClientConnectionManager();
		this.cm.setMaxTotal(connectionSize > 0 ? connectionSize : DEFAULT_MAX_CONN_SIZE);
		this.cm.setDefaultMaxPerRoute(routerSize > 0 ? routerSize : DEFAULT_ROUTER_SIZE);
		this.httpClient = HttpClients.custom().setConnectionManager(cm).build();
	}

	public void setConnectionSize(int connectionSize) {
		this.connectionSize = connectionSize;
	}

	public void setRouterSize(int routerSize) {
		this.routerSize = routerSize;
	}

	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}

	/**
	 * http client close TODO: connect pool should be release,when dubbo service
	 * is shutting down
	 */
	public void destory() {
		try {
			this.httpClient.close();
		} catch (IOException e) {
			logger.error(String.format("close httpclient failed,msg:%s", e.getMessage()), e);
		}
	}

	private CloseableHttpResponse execute(HttpRequestBase request) throws IOException {
		request.setConfig(DEFAULT_REQUEST_CONFIG);
		return this.httpClient.execute(request);// response close已执行
	}

	private CloseableHttpResponse execute(HttpRequestBase request, RequestConfig config) throws IOException {
		request.setConfig(config);
		return this.httpClient.execute(request);
	}

	public String getResult(CloseableHttpResponse resp) {
		String result = null;
		if (resp != null) {
			try {
				result = EntityUtils.toString(resp.getEntity(), "utf-8");
				if (this.isDebug) {
					logger.debug(result);
				}
			} catch (IOException e) {
				logger.error(String.format("get result failed,%s", e.getMessage()), e);
			} finally {
				try {
					resp.close();
				} catch (IOException e) {
					logger.error(String.format("close resp failed,%s", e.getMessage()), e);
				}
			}
		}
		return result;
	}
	
	public String getResult(CloseableHttpResponse resp, String charset) {
		String result = null;
		if (resp != null) {
			try {
				result = EntityUtils.toString(resp.getEntity(), charset);
				if (this.isDebug) {
					logger.debug(result);
				}
			} catch (IOException e) {
				logger.error(String.format("get result failed,%s", e.getMessage()), e);
			} finally {
				try {
					resp.close();
				} catch (IOException e) {
					logger.error(String.format("close resp failed,%s", e.getMessage()), e);
				}
			}
		}
		return result;
	}
	
	public String get(String url, String charset) {
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = null;
		String result = null;
		try {
			response = execute(get);
			result = getResult(response, charset);
		} catch (IOException e) {
			logger.error(String.format("request failed,%s,%s", url, e.getMessage()), e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e1) {
					logger.error(String.format("close resp failed,%s", e1.getMessage()), e1);
				}
			}
		}
		return result;
	}

	public String get(String url, int timeout) {
		System.out.println(url);
		HttpGet get = new HttpGet(url);
		// get.setHeader("Cookie",
		// "ARRAffinity=7087a821f11009971732acc11cc8cf666283f1daf5e3f01308ecc6d52cca877f;
		// Hm_lvt_15b1a40a8d25f43208adae1c1e12a514=1447637386,1447668591,1447727274,1447843613;
		// Hm_lpvt_15b1a40a8d25f43208adae1c1e12a514=1447988187;
		// ajaxcheck=jQuery1102007324667228385806_1447987266107");
		RequestConfig config = RequestConfig.custom().setSocketTimeout(DEFAULT_SOCKET_TIMEOUT)
				.setConnectionRequestTimeout(DEFAULT_CONN_TIMEOUT).setConnectTimeout(timeout).build();
		CloseableHttpResponse response = null;
		String result = null;
		try {
			// long start = 0;
			if (this.isDebug) {
				// start = System.currentTimeMillis();
				// logger.info("GET,URL:{},start:{}===========>", url, start);
			}
			response = execute(get, config);
			if (this.isDebug) {
				// long end = System.currentTimeMillis();
				// logger.info("GET,URL:{},end:{},cost:{}===========>", url end,
				// end - start);
			}
			result = getResult(response);
		} catch (IOException e) {
			logger.error(String.format("request failed,%s,%s", url, e.getMessage()), e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e1) {
					logger.error(String.format("close resp failed,%s", e1.getMessage()), e1);
				}
			}
		}
		return result;
	}


	public boolean isInitCookie(CloseableHttpResponse response) {
		if (response != null) {
			int cookieNum = 0;
			Header[] allHeaders = response.getAllHeaders();
			for (int i = 0; i < allHeaders.length; i++) {
				if ("Set-Cookie".equals(allHeaders[i].getName())) {
					cookieNum++;
				}
			}
			if (cookieNum > 1) {
				return true;
			}
		}
		return false;
	}

	public String get(String url) {
		String result = null;
		try {
			HttpGet get = new HttpGet(url);

			CloseableHttpResponse response = null;
			try {
				// long start = System.currentTimeMillis();
				if (this.isDebug) {
					// logger.info("GET,URL:{},start:{}===========>", url,
					// start);
				}
				response = execute(get);
				if (this.isDebug) {
					// long end = System.currentTimeMillis();
					// logger.info("GET,URL:{},end:{},cost:{}===========>", url,
					// end, end - start);
				}
				if (HttpStatus.SC_MOVED_TEMPORARILY == response.getStatusLine().getStatusCode()) { // redirect
					try {
						for (int i = 0; i < nTries; i++) {
							Thread.sleep(RETRY_WAIT_MS);
							response = execute(get);
							if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
								break;
							}
						}
					} catch (InterruptedException e) {
						logger.error(String.format("GET,URL:{}, interrupted exception===========>", url), e);
					}
				}
				result = getResult(response);
			} catch (IOException e) {
				logger.error(String.format("request failed,%s,%s", url, e.getMessage()), e);
			} finally {
				if (response != null) {
					try {
						response.close();
					} catch (IOException e1) {
						logger.error(String.format("close resp failed,%s", e1.getMessage()), e1);
					}
				}
			}
		} catch (Exception e) {
			logger.error("httputils interrupt exception...", e);
		}
		// System.out.println(result.length());
		return result;
	}

	public String get(String url, Map<String, Object> headerMap) {
		String result = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = null;
			try {
				if (headerMap != null && headerMap.size() > 0) {
					for (String key : headerMap.keySet()) {
						httpGet.addHeader(key, String.valueOf(headerMap.get(key)));
					}
				}
				response = execute(httpGet);
				result = getResult(response);
				
				try {
					if (result.length() < 8 * 1000) {
						for (int i = 0; i < 3; i++) {
							Thread.sleep(10 * 1000);
							response = execute(httpGet);
							result = getResult(response);
							if (result.length() > 8 * 1000) {
								break;
							}
						}
					}
				} catch (InterruptedException e) {
					logger.error(String.format("GET,URL:{}, interrupted exception===========>", url), e);
				}
				
			} catch (IOException e) {
				logger.error(String.format("request failed,%s,%s", url, e.getMessage()), e);
			} finally {
				if (response != null) {
					try {
						response.close();
					} catch (IOException e1) {
						logger.error(String.format("close resp failed,%s", e1.getMessage()), e1);
					}
				}
			}
		} catch (Exception e) {
			logger.error("httputils interrupt exception...", e);
		}
		return result;
	}
	
	public String postXml(String postUrl, String xmlData, String charset){
		HttpPost post = new HttpPost(postUrl);
		post.setConfig(DEFAULT_REQUEST_CONFIG);
		CloseableHttpResponse response = null;
		try {
			
			StringEntity entity = new StringEntity(xmlData,charset);//解决中文乱码问题    
			entity.setContentEncoding(charset);    
			entity.setContentType("text/xml");   
			post.setEntity(entity);

			response = httpClient.execute(post);
			HttpEntity responseEntity = response.getEntity();
			String content = EntityUtils.toString(responseEntity,"UTF-8");
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("postRequest error",e);
		}finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e1) {
					logger.error(String.format("close resp failed,%s", e1.getMessage()), e1);
				}
			}
		}
		return null;
	}
	
	public String postData(String postUrl, String data, String charset){
		HttpPost post = new HttpPost(postUrl);
		post.setConfig(DEFAULT_REQUEST_CONFIG);
		CloseableHttpResponse response = null;
		try {
			
			StringEntity entity = new StringEntity(data,charset);//解决中文乱码问题    
			entity.setContentEncoding(charset);    
			entity.setContentType("application/x-www-form-urlencoded");   
			post.setEntity(entity);

			response = httpClient.execute(post);
			HttpEntity responseEntity = response.getEntity();
			String content = EntityUtils.toString(responseEntity,"UTF-8");
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("postRequest error",e);
		}finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e1) {
					logger.error(String.format("close resp failed,%s", e1.getMessage()), e1);
				}
			}
		}
		return null;
	}

	public static void main(String[] args) throws InterruptedException {
//		try {
//			for (int i = 0; i < 1000; i++) {
//				String url ="http://www.dianping.com/search/keyword/1/0_%E8%8E%98%E5%BA%84%E4%BB%B2%E7%9B%9B/p2?aid=5592582%2C6212826";
//				Map<String,Object> headerMap =new HashMap<>();
//				headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
//				headerMap.put("Referer", "http://www.dianping.com/search/keyword/1/0_%E8%8E%98%E5%BA%84%E4%BB%B2%E7%9B%9B/p3?aid=5592582%2C6212826");
////				headerMap.put("Set-Cookie", "s_ViewType=10; Domain=.dianping.com; Expires=Sun, 05-Jan-2020 14:43:40 GMT; Path=/");
//				/*System.out.println(*/HttpUtils.getInstance().get(url, headerMap)/*)*/;		
//				Thread.sleep(1000);
//			}			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		String url = "https://list.tmall.com/search_product.htm?spm=a3204.7084713.1996500281.1.YTOuRF&user_id=725677994&cat=51454011&active=1&style=g&acm=lb-zebra-27092-331834.1003.4.457096&sort=td&scm=1003.4.lb-zebra-27092-331834.OTHER_14434945515601_457096&industryCatId=51462017#J_Filter";
//		String url = "https://list.tmall.com/search_product.htm?cat=50514010&sort=s&s=0&style=g&acm=lb-zebra-27092-331834.1003.4.457096&search_condition=1&active=1&user_id=725677994&spm=1.1.0.0.dnvr45&smareaid=330100&scm=1003.4.lb-zebra-27092-331834.other_14434941668152_457096&industrycatid=51278010";
		System.out.println(HttpUtils.getInstance().get(url));
		
	}

}