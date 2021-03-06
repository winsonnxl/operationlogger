package niu.winson.controller;

import niu.winson.annotation.OperationLogger;
import niu.winson.commons.ReturnValue;
import niu.winson.entity.OperLog;
import niu.winson.entity.ResultVO;
import niu.winson.enumation.ErrorCode;
import niu.winson.enumation.OperationType;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/aop")
public class aopTestController {
    Logger log= LoggerFactory.getLogger(aopTestController.class);
    @OperationLogger(Name = "捕捉useApiLog", Type = OperationType.UPDATE)
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResultVO useApiLog(@RequestBody String args) {

        OperLog operLog=new OperLog();
        operLog.setOperUserID("803");
        operLog.setOperApiName("插入数据库API Name");
        operLog.setOperMethod("插入Method");
        try {
//            return ReturnValue.FAIL(ErrorCode.DATABASE_ERROR);
//            return ReturnValue.MESSAGE("9999","测试不检测SystemID",null,false)
            return ReturnValue.MESSAGE("9999","测试不检测SystemID",null);
        }catch (Exception e){
            log.error("aopTestController->useApiLog:Exception \n");e.getMessage();
            return ReturnValue.FAIL(ErrorCode.DATABASE_ERROR);
        }

    }

    @OperationLogger(Name = "捕捉hello",Type= OperationType.INSERT)
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public ResultVO hello(@RequestBody String args) {
        String data=new String();
        Map<String,Object> map=new HashMap<String,Object>();
        Map<String,Object> map1=new HashMap<String,Object>();
        map1.put("ddd","djfkdfj");
        map1.put("ccc","ccccccc");
        String encoded = "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ";
        byte[] decoded = Base64.decodeBase64(encoded);
        String getDecoded = new String(decoded);
        map.put("decoded", getDecoded);
        map.put("show_msg","Greate!!!");
        map.put("map1",map1);
//        return ReturnValue.SUCCESS(map);
        return ReturnValue.SUCCESS_NOT_CHECKSYSTEMID();
    }

    @OperationLogger(Name = "测试空参数",Type= OperationType.SELECT)
    @RequestMapping(value = "/getrequest",method = RequestMethod.GET)
    public ResultVO getEmptyArgs(){
        return ReturnValue.SUCCESS();
    }
}

