package testCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import testBase.TestBase;

public class TC001_Get_All_Employees extends TestBase
{

    @BeforeClass
    void getAllEmployees() throws InterruptedException{
        logger.info("******************TC001 Execution started*********************");
        RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
        httprequest=RestAssured.given();
        response=httprequest.request(Method.GET,"/employees");
    }

    @Test
    void checkresponsebody(){
        logger.info("********************Checking Responsebody**************");
        String responsebody=response.getBody().asString();
        logger.info("The response body is :"+responsebody);

    }

    @Test
    void checkStatusCode(){
        logger.info("********************Checking status code**************");
        int statuscode=response.getStatusCode();
        logger.info("The Status code is :"+statuscode);
        Assert.assertEquals(statuscode,200);
    }

    @Test
    void checkResponseTime(){
        logger.info("****************Checking Response TIme***************");
        long responsetime=response.getTime();
        logger.info("The response time is :"+responsetime);
        if(responsetime>2000){
            logger.warn("The response time is greater than 2000");
        }
        Assert.assertTrue(responsetime<10000);
    }

    @Test
    void checkStatusLine(){
        logger.info("*******************Checking status Line******************");
        String statusline=response.getStatusLine();
        logger.info("The status line is :"+statusline);
        Assert.assertEquals(statusline,"HTTP/1.1 200 OK");
    }

    @Test
    void checkContentType(){
        logger.info("******************Checking content type*************");
        String contenttype=response.header("Content-Type");
        logger.info("The content type value is :"+contenttype);
        Assert.assertEquals(contenttype,"application/json");
    }

    @Test
    void checkservertype(){
        logger.info("*********************Checking server type*****************");
        String servertype=response.header("Server");
        logger.info("The server type is :"+servertype);
        Assert.assertEquals(servertype,"nginx");
    }

    @Test
    void checkContentEncoding(){
        logger.info("******************checking content Encoding*************");
        String contentencoding=response.header("Content-Encoding");
        logger.info("The content encoding value is :"+contentencoding);
        Assert.assertEquals(contentencoding,"gzip");
    }

    @Test
    void checkContentLength(){
        logger.info("***************Checking Content length******************");
        String contentlength=response.header("Content-Length");
        logger.info("The content length is :"+contentlength);
        if(Integer.parseInt(contentlength)<100){
            logger.warn("The Content length is less than 100");
        }
        Assert.assertTrue(Integer.parseInt(contentlength)>100);
    }

    @AfterClass
    void  teardown(){
        logger.info("***************The Test execution is completed******************");
    }


}
